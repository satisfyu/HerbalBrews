package net.satisfy.herbalbrews.core.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class FeralEffect extends MobEffect {
    private static final UUID ARMOR_UUID = UUID.fromString("710D4861-7021-47DE-9F52-62F48D2B61EB");
    private static final UUID DAMAGE_UUID = UUID.fromString("CE752B4A-A279-452D-853A-73C26FB4BA46");

    public FeralEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && player.isAlive()) {
            Level world = player.level();
            double radius = 10.0;
            List<Player> nearbyPlayers = world.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(radius));
            int playerCount = nearbyPlayers.size() - 1;
            if (playerCount < 0) playerCount = 0;
            int finalAmplifier = calculateFinalAmplifier(playerCount);
            if (finalAmplifier > 0) {
                addAttributeModifiers(entity, entity.getAttributes(), finalAmplifier);
            }
        }
    }

    private int calculateFinalAmplifier(int playerCount) {
        float baseAmplifier = 1.0f;
        float reductionPerPlayer = 0.1f;
        float calculatedAmplifier = baseAmplifier - (playerCount * reductionPerPlayer);
        if (calculatedAmplifier < 0) {
            calculatedAmplifier = 0;
        }
        int finalAmplifier = Math.round(calculatedAmplifier);
        return Math.max(0, Math.min(finalAmplifier, 3));
    }

    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
        if (modifier.getId().equals(DAMAGE_UUID))
            return (amplifier + 1) * 2.0F;
        if (modifier.getId().equals(ARMOR_UUID))
            return (amplifier + 1) * 4.0F;
        return amplifier + 1;
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
