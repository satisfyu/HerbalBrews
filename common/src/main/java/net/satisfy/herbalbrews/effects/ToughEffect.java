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
        if (entity instanceof Player player && isAffectedEntity(player)) {
            if (player.getHealth() <= 8.0F) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0, false, false, false)); // Schwache Heilung
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 0, false, false, false)); // Geringer Widerstand
            }
        }
    }

    private boolean isAffectedEntity(Player player) {
        return player.isAlive() && !player.isCreative();
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 100 == 0;
    }
}
