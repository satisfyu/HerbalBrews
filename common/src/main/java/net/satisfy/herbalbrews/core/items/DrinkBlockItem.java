package net.satisfy.herbalbrews.core.items;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class DrinkBlockItem extends BlockItem {

    public DrinkBlockItem(Block block, Properties settings) {
        super(block, settings.stacksTo(16));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("Effect") && tag.contains("EffectDuration")) {
            ResourceLocation effectId = new ResourceLocation(tag.getString("Effect"));
            MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(effectId);
            int duration = tag.getInt("EffectDuration");
            if (effect != null) {
                user.addEffect(new MobEffectInstance(effect, duration));
            }
        }
        List<Pair<MobEffectInstance, Float>> effects = getFoodProperties() != null ? getFoodProperties().getEffects() : Lists.newArrayList();
        for (Pair<MobEffectInstance, Float> effectPair : effects) {
            if (world.random.nextFloat() < effectPair.getSecond()) {
                user.addEffect(new MobEffectInstance(effectPair.getFirst()));
            }
        }
        return super.finishUsingItem(stack, world, user);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("Effect") && tag.contains("EffectDuration")) {
            ResourceLocation effectId = new ResourceLocation(tag.getString("Effect"));
            MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(effectId);
            int duration = tag.getInt("EffectDuration");
            if (effect != null) {
                MobEffectInstance effectInstance = new MobEffectInstance(effect, duration);
                MutableComponent effectName = Component.translatable(effect.getDescriptionId());
                if (effectInstance.getDuration() > 20) {
                    effectName = Component.translatable("potion.withDuration", effectName, MobEffectUtil.formatDuration(effectInstance, 1.0f));
                }
                tooltip.add(effectName.withStyle(effect.getCategory().getTooltipFormatting()));
            }
        } else {
            tooltip.add(Component.translatable("effect.none").withStyle(ChatFormatting.GRAY));
        }

        List<Pair<Attribute, AttributeModifier>> attributeModifiers = Lists.newArrayList();
        if (tag != null && tag.contains("AttributeModifiers")) {
            ListTag modifiers = tag.getList("AttributeModifiers", 10);
            for (int i = 0; i < modifiers.size(); i++) {
                CompoundTag modifierTag = modifiers.getCompound(i);
                String attributeName = modifierTag.getString("AttributeName");
                Attribute attribute = BuiltInRegistries.ATTRIBUTE.get(new ResourceLocation(attributeName));
                if (attribute != null) {
                    AttributeModifier.Operation operation = AttributeModifier.Operation.values()[modifierTag.getByte("Operation")];
                    double amount = modifierTag.getDouble("Amount");
                    UUID uuid = UUID.fromString(modifierTag.getString("UUID"));
                    AttributeModifier modifier = new AttributeModifier(uuid, modifierTag.getString("Name"), amount, operation);
                    attributeModifiers.add(new Pair<>(attribute, modifier));
                }
            }
        }

        if (!attributeModifiers.isEmpty()) {
            tooltip.add(Component.empty());
            tooltip.add(Component.translatable("potion.whenDrank").withStyle(ChatFormatting.DARK_PURPLE));
            for (Pair<Attribute, AttributeModifier> pair : attributeModifiers) {
                AttributeModifier modifier = pair.getSecond();
                double amount = modifier.getAmount();
                double displayAmount = (modifier.getOperation() == AttributeModifier.Operation.MULTIPLY_BASE || modifier.getOperation() == AttributeModifier.Operation.MULTIPLY_TOTAL)
                        ? amount * 100.0
                        : amount;
                if (amount > 0.0) {
                    tooltip.add(Component.translatable(
                                    "attribute.modifier.plus." + modifier.getOperation().toValue(),
                                    ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount),
                                    Component.translatable(pair.getFirst().getDescriptionId()))
                            .withStyle(ChatFormatting.BLUE));
                } else if (amount < 0.0) {
                    tooltip.add(Component.translatable(
                                    "attribute.modifier.take." + modifier.getOperation().toValue(),
                                    ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(-displayAmount),
                                    Component.translatable(pair.getFirst().getDescriptionId()))
                            .withStyle(ChatFormatting.RED));
                }
            }
        }
        tooltip.add(Component.translatable("tooltip.herbalbrews.canbeplaced").withStyle(style -> style.withColor(TextColor.fromRgb(0xCD7F32)).withItalic(true)));
    }
}
