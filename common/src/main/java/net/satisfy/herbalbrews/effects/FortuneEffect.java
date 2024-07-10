package net.satisfy.herbalbrews.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Objects;
import java.util.UUID;

public class FortuneEffect extends MobEffect {

    private static final UUID FORTUNE_EFFECT_UUID = UUID.fromString("d699bf1f-74a3-4b1d-a5f2-ccaa49d6d873");

    public FortuneEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        double fortuneModifierValue = 1.0 * (amplifier + 1);

        AttributeModifier fortuneModifier = new AttributeModifier(FORTUNE_EFFECT_UUID, "Fortune Effect", fortuneModifierValue, AttributeModifier.Operation.ADDITION);
        if (entity.getAttribute(Attributes.LUCK) != null) {
            Objects.requireNonNull(entity.getAttribute(Attributes.LUCK)).addPermanentModifier(fortuneModifier);
        }
    }
}
