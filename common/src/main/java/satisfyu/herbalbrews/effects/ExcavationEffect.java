package satisfyu.herbalbrews.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ExcavationEffect extends MobEffect {
    public ExcavationEffect() {
        super(MobEffectCategory.NEUTRAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.isAlive() && entity instanceof Player && !((Player) entity).isCreative()) {
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 50, amplifier + 1));
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 50, amplifier + 1));
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, amplifier + 1));
        }

        for (LivingEntity living : entity.level().getEntitiesOfClass(Player.class, entity.getBoundingBox().inflate(10.0D))) {
            if (living.isAlive() && living != entity && !(living instanceof Player && ((Player) living).isCreative())) {
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 50, amplifier + 1));
                living.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 50, amplifier + 1));
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, amplifier + 1));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
