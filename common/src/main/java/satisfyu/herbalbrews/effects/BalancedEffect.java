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
        if (entity instanceof Player) {
            entity.level().getEntitiesOfClass(Player.class, entity.getBoundingBox().inflate(10.0), Player::isAlive)
                    .forEach(player -> player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 50, 1, false, false, false)));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
