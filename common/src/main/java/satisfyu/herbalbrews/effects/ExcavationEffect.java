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
        if (entity instanceof Player && !((Player) entity).isCreative()) {
            applyEffects((Player) entity, amplifier);
        }

        entity.level().getEntitiesOfClass(Player.class, entity.getBoundingBox().inflate(10.0D)).stream()
                .filter(player -> player.isAlive() && player != entity && !player.isCreative())
                .forEach(player -> applyEffects(player, amplifier));
    }

    private void applyEffects(Player player, int amplifier) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 50, amplifier + 1, false, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 50, amplifier + 1, false, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 50, amplifier + 1, false, false, false));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
