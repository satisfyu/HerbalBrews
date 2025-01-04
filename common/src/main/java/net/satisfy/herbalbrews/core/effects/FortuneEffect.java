package net.satisfy.herbalbrews.core.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class FortuneEffect extends MobEffect {
    private static final UUID FORTUNE_EFFECT_UUID = UUID.fromString("d699bf1f-74a3-4b1d-a5f2-ccaa49d6d873");
    private static final String MODIFIER_NAME = "Fortune Effect";

    public FortuneEffect() {
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
                double fortuneModifierValue = 1.0 * (finalAmplifier + 1);
                AttributeInstance luckAttr = player.getAttribute(Attributes.LUCK);
                if (luckAttr != null) {
                    AttributeModifier existingModifier = luckAttr.getModifier(FORTUNE_EFFECT_UUID);
                    if (existingModifier != null) {
                        if (existingModifier.getAmount() != fortuneModifierValue) {
                            luckAttr.removeModifier(FORTUNE_EFFECT_UUID);
                            AttributeModifier fortuneModifier = new AttributeModifier(FORTUNE_EFFECT_UUID, MODIFIER_NAME, fortuneModifierValue, AttributeModifier.Operation.ADDITION);
                            luckAttr.addPermanentModifier(fortuneModifier);
                        }
                    } else {
                        AttributeModifier fortuneModifier = new AttributeModifier(FORTUNE_EFFECT_UUID, MODIFIER_NAME, fortuneModifierValue, AttributeModifier.Operation.ADDITION);
                        luckAttr.addPermanentModifier(fortuneModifier);
                    }
                }
            } else {
                AttributeInstance luckAttr = player.getAttribute(Attributes.LUCK);
                if (luckAttr != null) {
                    luckAttr.removeModifier(FORTUNE_EFFECT_UUID);
                }
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
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
