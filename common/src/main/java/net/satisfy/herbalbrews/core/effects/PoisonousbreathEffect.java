package net.satisfy.herbalbrews.core.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class PoisonousbreathEffect extends MobEffect {
    public PoisonousbreathEffect() {
        super(MobEffectCategory.NEUTRAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(0.5), livingEntity ->
                        isAffectedEntity(livingEntity, entity))
                .forEach(livingEntity -> {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 50, amplifier + 2));
                    livingEntity.setLastHurtByMob(entity);
                });
    }

    private boolean isAffectedEntity(LivingEntity target, LivingEntity source) {
        return target.isAlive() && target != source && !(target instanceof Player && ((Player) target).isCreative());
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
