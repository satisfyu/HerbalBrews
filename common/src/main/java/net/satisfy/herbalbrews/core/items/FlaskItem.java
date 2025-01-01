package net.satisfy.herbalbrews.core.items;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FlaskItem extends Item {
    public FlaskItem(Properties properties) {
        super(properties.stacksTo(16));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!stack.hasTag() || !Objects.requireNonNull(stack.getTag()).contains("CustomModelData")) {
            CompoundTag tag = stack.getOrCreateTag();
            int randomTexture = level.random.nextInt(6) + 1;
            tag.putInt("CustomModelData", randomTexture);
            stack.setTag(tag);

            // Debugging-Ausgabe
            System.out.println("Set CustomModelData: " + randomTexture);
        }

        return super.use(level, player, hand);
    }


    @Override
    @SuppressWarnings("unused")
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("CustomPotionEffects", 9)) {
            ListTag effects = tag.getList("CustomPotionEffects", 10);
            for (int i = 0; i < effects.size(); i++) {
                CompoundTag effectTag = effects.getCompound(i);
                int effectId = effectTag.getInt("Id");
                MobEffect effect = BuiltInRegistries.MOB_EFFECT.byId(effectId);
                if (effect != null) {
                    int duration = effectTag.getInt("Duration");
                    int amplifier = effectTag.getByte("Amplifier");
                    MobEffectInstance effectInstance = new MobEffectInstance(effect, duration, amplifier);

                    MutableComponent effectName = Component.translatable(effect.getDescriptionId());
                    if (effectInstance.getDuration() > 20) {
                        effectName = Component.translatable(
                                "potion.withDuration",
                                effectName,
                                MobEffectUtil.formatDuration(effectInstance, 1.0f)
                        );
                    }
                    tooltip.add(effectName.withStyle(effect.getCategory().getTooltipFormatting()));
                }
            }
        } else {
            tooltip.add(Component.translatable("effect.none").withStyle(ChatFormatting.GRAY));
        }

        List<Pair<Attribute, AttributeModifier>> list3 = Lists.newArrayList();
        if (tag != null && tag.contains("AttributeModifiers")) {
            ListTag modifiers = tag.getList("AttributeModifiers", 10);
            for (int i = 0; i < modifiers.size(); i++) {
                CompoundTag modifierTag = modifiers.getCompound(i);
                String attributeName = modifierTag.getString("AttributeName");
                Attribute attribute = BuiltInRegistries.ATTRIBUTE.get(new ResourceLocation(attributeName));
                if (attribute != null) {
                    String operation = modifierTag.getString("Operation");
                    double amount = modifierTag.getDouble("Amount");
                    AttributeModifier.Operation op = AttributeModifier.Operation.values()[modifierTag.getByte("Operation")];
                    String uuid = modifierTag.getString("UUID");
                    AttributeModifier modifier = new AttributeModifier(UUID.fromString(uuid), modifierTag.getString("Name"), amount, op);
                    list3.add(new Pair<>(attribute, modifier));
                }
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
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isClientSide) {
            updateTextureIndex(stack);
        }
    }

    private void updateTextureIndex(ItemStack stack) {
        if (stack.getOrCreateTag().contains("Effect") && stack.getOrCreateTag().contains("EffectDuration")) {
            String effectId = stack.getOrCreateTag().getString("Effect");
            int duration = stack.getOrCreateTag().getInt("EffectDuration");
            int hash = Math.abs(effectId.hashCode() + duration) % 6 + 1;
            stack.getOrCreateTag().putInt("CustomModelData", hash);
        } else {
            stack.getOrCreateTag().putInt("CustomModelData", 1);
        }
    }
}
