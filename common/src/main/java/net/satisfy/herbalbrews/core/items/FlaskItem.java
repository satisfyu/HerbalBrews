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
import net.minecraft.world.entity.LivingEntity;
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
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.hasTag() || !Objects.requireNonNull(stack.getTag()).contains("CustomModelData")) {
            setRandomTexture(level, stack);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            applyPotionEffects(stack, player);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return stack;
    }

    private void setRandomTexture(@Nullable Level level, ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("CustomModelData")) {
            int randomTexture;
            if (level != null) {
                randomTexture = level.getRandom().nextInt(6) + 1;
            } else {
                randomTexture = (int) (Math.random() * 6) + 1;
            }
            tag.putInt("CustomModelData", randomTexture);
            System.out.println("Set CustomModelData: " + randomTexture);
        }
    }

    private void applyPotionEffects(ItemStack stack, Player player) {
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
                    player.addEffect(effectInstance);
                }
            }
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level world, Player player) {
        super.onCraftedBy(stack, world, player);
        setRandomTexture(world, stack);
    }

    @Override
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
                        effectName = Component.translatable("potion.withDuration", effectName, MobEffectUtil.formatDuration(effectInstance, 1.0f));
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
                AttributeModifier modifier = pair.getSecond();
                double d = modifier.getAmount();
                double e;
                if (modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    e = modifier.getAmount();
                } else {
                    e = modifier.getAmount() * 100.0;
                }
                if (d > 0.0) {
                    tooltip.add(Component.translatable("attribute.modifier.plus." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(e), Component.translatable(pair.getFirst().getDescriptionId())).withStyle(ChatFormatting.BLUE));
                } else if (d < 0.0) {
                    e *= -1.0;
                    tooltip.add(Component.translatable("attribute.modifier.take." + modifier.getOperation().toValue(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(e), Component.translatable(pair.getFirst().getDescriptionId())).withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isClientSide && (!stack.hasTag() || !Objects.requireNonNull(stack.getTag()).contains("CustomModelData"))) {
            setRandomTexture(world, stack);
        }
    }
}
