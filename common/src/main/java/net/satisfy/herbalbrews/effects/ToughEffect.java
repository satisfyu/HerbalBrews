package net.satisfy.herbalbrews.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ToughEffect extends MobEffect {
    public ToughEffect() {
        super(MobEffectCategory.NEUTRAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player) {
            entity.level().getEntitiesOfClass(Player.class, entity.getBoundingBox().inflate(10.0), this::isAffectedEntity)
                    .forEach(player -> applyEffects(player, amplifier));
        }
    }

    private boolean isAffectedEntity(Player player) {
        return player.isAlive() && !player.isCreative();
    }

    private void applyEffects(Player player, int amplifier) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 50, amplifier+1, false, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 50, amplifier+1, false, false, false));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
