package satisfyu.herbalbrews.items;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class DrinkBlockItem extends BlockItem {
    private final MobEffectInstance customEffect;

    public DrinkBlockItem(Block block, Properties settings, MobEffectInstance customEffect) {
        super(block, settings.stacksTo(16));
        this.customEffect = customEffect;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (this.getFoodProperties() != null) {
            for (Pair<MobEffectInstance, Float> effectPair : this.getFoodProperties().getEffects()) {
                if (world.random.nextFloat() < effectPair.getSecond()) {
                    user.addEffect(new MobEffectInstance(effectPair.getFirst()));
                }
            }
        }
        if (customEffect != null) {
            user.addEffect(new MobEffectInstance(customEffect));
        }
        return super.finishUsingItem(stack, world, user);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        List<Pair<MobEffectInstance, Float>> list2 = getFoodProperties() != null ? getFoodProperties().getEffects() : Lists.newArrayList();
        List<Pair<Attribute, AttributeModifier>> list3 = Lists.newArrayList();

        if (list2.isEmpty() && customEffect == null) {
            tooltip.add(Component.translatable("effect.none").withStyle(ChatFormatting.GRAY));
        } else {
            if (customEffect != null) {
                addEffectToTooltip(customEffect, tooltip, list3);
            }
            for (Pair<MobEffectInstance, Float> statusEffectInstance : list2) {
                addEffectToTooltip(statusEffectInstance.getFirst(), tooltip, list3);
            }
        }

        if (!list3.isEmpty()) {
            tooltip.add(Component.empty());
            tooltip.add(Component.translatable("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE));

            for (Pair<Attribute, AttributeModifier> pair : list3) {
                AttributeModifier entityAttributeModifier3 = pair.getSecond();
                double d = entityAttributeModifier3.getAmount();
                double e;
                if (entityAttributeModifier3.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && entityAttributeModifier3.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    e = entityAttributeModifier3.getAmount();
                } else {
                    e = entityAttributeModifier3.getAmount() * 100.0;
                }

                if (d > 0.0) {
                    tooltip.add(
                            Component.translatable(
                                            "attribute.modifier.plus." + entityAttributeModifier3.getOperation().toValue(),
                                            ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(e), Component.translatable(pair.getFirst().getDescriptionId()))
                                    .withStyle(ChatFormatting.BLUE)
                    );
                } else if (d < 0.0) {
                    e *= -1.0;
                    tooltip.add(
                            Component.translatable(
                                            "attribute.modifier.take." + entityAttributeModifier3.getOperation().toValue(),
                                            ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(e), Component.translatable(pair.getFirst().getDescriptionId()))
                                    .withStyle(ChatFormatting.RED)
                    );
                }
            }
        }
        tooltip.add(Component.translatable("tooltip.herbalbrews.canbeplaced").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }

    private void addEffectToTooltip(MobEffectInstance effectInstance, List<Component> tooltip, List<Pair<Attribute, AttributeModifier>> attributeModifiers) {
        MutableComponent mutableText = Component.translatable(effectInstance.getDescriptionId());
        MobEffect statusEffect = effectInstance.getEffect();
        Map<Attribute, AttributeModifier> map = statusEffect.getAttributeModifiers();

        if (!map.isEmpty()) {
            for (Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
                AttributeModifier entityAttributeModifier = entry.getValue();
                AttributeModifier entityAttributeModifier2 = new AttributeModifier(
                        entityAttributeModifier.getName(),
                        statusEffect.getAttributeModifierValue(effectInstance.getAmplifier(), entityAttributeModifier),
                        entityAttributeModifier.getOperation()
                );
                attributeModifiers.add(new Pair<>(entry.getKey(), entityAttributeModifier2));
            }
        }

        if (effectInstance.getDuration() > 20) {
            mutableText = Component.translatable(
                    "potion.withDuration",
                    mutableText, MobEffectUtil.formatDuration(effectInstance, 1.0f)
            );
        }

        tooltip.add(mutableText.withStyle(statusEffect.getCategory().getTooltipFormatting()));
    }
}
