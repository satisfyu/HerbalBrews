package satisfyu.herbalbrews.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import satisfyu.herbalbrews.entities.CauldronBlockEntity;
import satisfyu.herbalbrews.util.GeneralUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CauldronBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public CauldronBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }


    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        final BlockEntity entity = world.getBlockEntity(pos);
        if (entity instanceof MenuProvider factory) {
            player.openMenu(factory);
            return InteractionResult.sidedSuccess(world.isClientSide());
        } else {
            return InteractionResult.PASS;
        }
    }


    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.is(newState.getBlock())) {
            return;
        }
        final BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CauldronBlockEntity entity) {
            if (world instanceof ServerLevel) {
                Containers.dropContents(world, pos, entity);
            }
            world.updateNeighbourForOutputSignal(pos, this);
        }
        super.onRemove(state, world, pos, newState, moved);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return (theWorld, pos, theState, blockEntity) -> {
            if (blockEntity instanceof BlockEntityTicker<?>) {
                ((BlockEntityTicker) blockEntity).tick(theWorld, pos, theState, blockEntity);
            }
        };
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CauldronBlockEntity(pos, state);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double blockX = (double) pos.getX() + 0.5;
        double blockY = pos.getY() + 0.24;
        double blockZ = (double) pos.getZ() + 0.5;

        if (random.nextDouble() < 0.1)
            world.playLocalSound(blockX, blockY, blockZ, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0f, 1.0f, false);
        world.playLocalSound(blockX, blockY, blockZ, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1.0f, 1.0f, false);
        world.playLocalSound(blockX, blockY, blockZ, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 1.0f, 1.0f, false);

        for (Direction direction : Direction.values()) {
            if (direction != Direction.DOWN) {
                double offsetX = random.nextDouble() * 0.6 - 0.3;
                double offsetY = random.nextDouble() * 6.0 / 16.0;
                double offsetZ = random.nextDouble() * 0.6 - 0.3;

                double particleX = blockX + offsetX + 0.5 * direction.getStepX();
                double particleY = blockY + offsetY;
                double particleZ = blockZ + offsetZ + 0.5 * direction.getStepZ();

                world.addParticle(ParticleTypes.SMOKE, particleX, particleY, particleZ, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, particleX, particleY, particleZ, 0.0, 0.0, 0.0);

                if (random.nextDouble() < 0.6) {
                    double middleParticleX = blockX + offsetX + 0.5 * direction.getStepX() * random.nextDouble();
                    double middleParticleY = blockY + offsetY + 0.7 * random.nextDouble();
                    double middleParticleZ = blockZ + offsetZ + 0.5 * direction.getStepZ() * random.nextDouble();

                    world.addParticle(ParticleTypes.BUBBLE_POP, middleParticleX, middleParticleY, middleParticleZ, 0.0, 0.0, 0.0);
                }

                if (random.nextDouble() < 0.1) {
                    SimpleParticleType simpleParticleType = ParticleTypes.CAMPFIRE_COSY_SMOKE;
                    world.addAlwaysVisibleParticle(simpleParticleType, true, pos.getX() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1), pos.getY() + random.nextDouble() + random.nextDouble(), pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);
                    world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5 + random.nextDouble() / 4.0 * (random.nextBoolean() ? 1 : -1), pos.getY() + 2.2, pos.getZ() + 0.5 + random.nextDouble() / 4.0 * (random.nextBoolean() ? 1 : -1), 0.0, 0.005, 0.0);
                }
            }
        }
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.fireImmune() && entity instanceof LivingEntity livingEntity &&
                !EnchantmentHelper.hasFrostWalker(livingEntity)) {
            entity.hurt(world.damageSources().hotFloor(), 1.f);
        }

        super.stepOn(world, pos, state, entity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("tooltip.herbalbrews.cauldron").withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.herbalbrews.canbeplaced").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }

    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0, 0, 0, 0.125, 0.1875, 0.25));
        shape = Shapes.or(shape, Shapes.box(0.125, 0, 0, 0.25, 0.1875, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.75, 0, 0, 1, 0.1875, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.875, 0, 0.125, 1, 0.1875, 0.25));
        shape = Shapes.or(shape, Shapes.box(0.875, 0, 0.75, 1, 0.1875, 1));
        shape = Shapes.or(shape, Shapes.box(0.75, 0, 0.875, 0.875, 0.1875, 1));
        shape = Shapes.or(shape, Shapes.box(0, 0, 0.875, 0.25, 0.1875, 1));
        shape = Shapes.or(shape, Shapes.box(0, 0, 0.75, 0.125, 0.1875, 0.875));
        shape = Shapes.or(shape, Shapes.box(0, 0.1875, 0, 1, 0.6875, 1));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0.6875, 0.0625, 0.9375, 0.8125, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0, 0.8125, 0.8125, 1, 1, 1));
        shape = Shapes.or(shape, Shapes.box(0, 0.8125, 0, 1, 1, 0.1875));
        shape = Shapes.or(shape, Shapes.box(0, 0.8125, 0.1875, 0.1875, 1, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0.8125, 0.1875, 1, 1, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.25, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0.000625, 0.0625, 0.9375, 0.063125, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.0625, 0.6875, 0.8125, 0.1875, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.0625, 0.4375, 0.8125, 0.1875, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.0625, 0.1875, 0.8125, 0.1875, 0.3125));
        return shape;
    };

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL.stream().toList()) {
            map.put(direction, GeneralUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(FACING));
    }
}