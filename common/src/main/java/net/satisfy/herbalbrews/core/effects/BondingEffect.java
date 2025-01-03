package net.satisfy.herbalbrews.core.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class BondingEffect extends MobEffect {
    public BondingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player) {
            List<Player> players = entity.level().getEntitiesOfClass(
                    Player.class,
                    entity.getBoundingBox().inflate(10.0),
                    this::isAffectedEntity
            );

            int count = players.size();
            if (count == 0) return;

            int adjustedAmplifier = Math.max(0, (amplifier + 1) / count - 1);

            players.forEach(player -> {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 50, adjustedAmplifier, false, false, false));
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 50, adjustedAmplifier, false, false, false));
            });
        }
    }

    private boolean isAffectedEntity(Player player) {
        return player.isAlive() && !player.isCreative();
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
