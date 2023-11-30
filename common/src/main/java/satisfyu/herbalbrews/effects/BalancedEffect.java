package satisfyu.herbalbrews.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import satisfyu.herbalbrews.registry.EffectRegistry;

public class BalancedEffect extends MobEffect {
    public BalancedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        for (LivingEntity livingEntity : entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(10.0D))) {
            if (livingEntity.isAlive() && livingEntity instanceof Player) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 50, amplifier + 1));
            }
        }
        entity.addEffect(new MobEffectInstance(EffectRegistry.BALANCED.get(), 50, amplifier + 1));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}