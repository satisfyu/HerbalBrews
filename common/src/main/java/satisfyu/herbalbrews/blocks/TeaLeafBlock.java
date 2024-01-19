package satisfyu.herbalbrews.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import satisfyu.herbalbrews.registry.ObjectRegistry;

@SuppressWarnings("deprecation")
public class TeaLeafBlock extends Block {
    public static IntegerProperty DRYING = IntegerProperty.create("drying", 0, 4);

    public TeaLeafBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(super.defaultBlockState().setValue(DRYING, 0));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DRYING);
        super.createBlockStateDefinition(builder);
    }

    public int getMaxDryingStage() {
        return 4;
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isClientSide) return;

        float chance = calculateDryingChance(level, pos);

        if (random.nextFloat() <= chance) {
            performDrying(level, pos, state);
        }
    }

    private float calculateDryingChance(ServerLevel level, BlockPos pos) {
        int maxLight = calculateMaxLight(level, pos);
        float baseChance = maxLight > 8 ? 0.1F : 0.04F;

        BlockPos upperBlock = pos.above();
        BlockState aboveState = level.getBlockState(upperBlock);
        Block aboveBlock = aboveState.getBlock();
        if (aboveBlock == Blocks.TORCH || aboveBlock == Blocks.MAGMA_BLOCK ||
                aboveBlock == Blocks.JACK_O_LANTERN || aboveBlock == Blocks.LANTERN) {
            baseChance *= 0.5f;
        }

        return baseChance;
    }

    private int calculateMaxLight(ServerLevel level, BlockPos pos) {
        int maxLight = 0;
        for (BlockPos neighborPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            int light = level.getBrightness(LightLayer.SKY, neighborPos.above());
            maxLight = Math.max(maxLight, light);
        }
        return maxLight;
    }

    private void performDrying(ServerLevel level, BlockPos pos, BlockState state) {
        if (state.getValue(DRYING) == getMaxDryingStage()) {
            Block endBlock = getEndBlock(state.getBlock());

            if (endBlock != null) {
                level.setBlock(pos, endBlock.defaultBlockState(), 3);
            }
        } else {
            level.setBlock(pos, state.setValue(DRYING, state.getValue(DRYING) + 1), 3);
        }
    }

    private Block getEndBlock(Block startBlock) {
        if (startBlock == ObjectRegistry.GREEN_TEA_LEAF_BLOCK.get()) {
            return ObjectRegistry.DRIED_OUT_GREEN_TEA_LEAF_BLOCK.get();
        } else if (startBlock == ObjectRegistry.MIXED_TEA_LEAF_BLOCK.get()) {
            return ObjectRegistry.OOLONG_TEA_LEAF_BLOCK.get();
        } else if (startBlock == ObjectRegistry.DRIED_GREEN_TEA_LEAF_BLOCK.get()) {
            return ObjectRegistry.BLACK_TEA_LEAF_BLOCK.get();
        }
        return null;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return getMaxDryingStage() + 1 - blockState.getValue(DRYING);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0) {
            level.addParticle(ParticleTypes.COMPOSTER, pos.getX() + random.nextFloat(), pos.getY() + 1.1D, pos.getZ() + random.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }
}
