package satisfyu.herbalbrews.effects;

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
        for (LivingEntity livingEntity : entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(0.3D))) {
            if (livingEntity.isAlive() && livingEntity != entity) {
                if (!(livingEntity instanceof Player && ((Player) livingEntity).isCreative())) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 50, amplifier + 1));
                    livingEntity.setLastHurtByMob(entity);
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}