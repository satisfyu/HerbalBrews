package satisfyu.herbalbrews.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class BalancedEffect extends MobEffect {
    public BalancedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        for (LivingEntity livingEntity : entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(10.0D))) {
            if (livingEntity.isAlive() && livingEntity instanceof Player) {
                int reducedDuration = 25;
                int reducedAmplifier = 1;

                livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, reducedDuration, reducedAmplifier));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
