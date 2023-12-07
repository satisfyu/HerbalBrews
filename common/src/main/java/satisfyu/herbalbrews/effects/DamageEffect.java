package satisfyu.herbalbrews.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class DamageEffect extends MobEffect {
    private static final String DAMAGE_UUID = "CE752B4A-A279-452D-853A-73C26FB4BA46";

    public DamageEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, DAMAGE_UUID, 1.0F, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
        if (modifier.getId().equals(UUID.fromString(DAMAGE_UUID)))
            return (amplifier + 1) * 2.0F;
        return amplifier + 1;
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        this.addAttributeModifiers(entity, entity.getAttributes(), amplifier);
    }
}