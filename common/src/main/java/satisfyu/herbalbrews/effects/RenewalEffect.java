package satisfyu.herbalbrews.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class RenewalEffect extends MobEffect {
    public RenewalEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        for (LivingEntity living : entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(10.0D))) {
            if (living.isAlive() && !(living instanceof Player && ((Player) living).isCreative())) {
                living.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 50, amplifier + 1));
                living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 50, amplifier + 1));
                living.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 20, amplifier + 1));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
