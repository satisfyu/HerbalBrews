package satisfyu.herbalbrews.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import satisfyu.herbalbrews.registry.BlockEntityRegistry;

import java.util.List;

public class CompletionistBannerEntity extends BlockEntity {
    public CompletionistBannerEntity(BlockPos blockPos, BlockState state) {
        super(BlockEntityRegistry.COMPLETIONIST_BANNER_ENTITY.get(), blockPos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CompletionistBannerEntity entity) {
        if (!level.isClientSide) {
            List<Player> players = level.getEntitiesOfClass(Player.class, new AABB(pos.offset(-8, -8, -8), pos.offset(8, 8, 8)));
            for (Player player : players) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1, false, false));
            }
        }
    }
}