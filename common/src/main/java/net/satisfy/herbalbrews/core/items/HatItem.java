package net.satisfy.herbalbrews.core.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.satisfy.herbalbrews.platform.PlatformHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HatItem extends ArmorItem {
    private final ResourceLocation hatTexture;

    public HatItem(ArmorMaterial armorMaterial, Type type, Properties properties, ResourceLocation hatTexture) {
        super(armorMaterial, type, properties);
        this.hatTexture = hatTexture;
    }

    public ResourceLocation getHatTexture() {
        return hatTexture;
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return this.type.getSlot();
    }

    public static void applyMagicResistance(LivingEntity entity, float damageAmount, boolean isMagic) {
        if (entity instanceof Player player) {
            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
            if (helmet.getItem() instanceof HatItem && isMagic && PlatformHelper.isHatDamageReductionEnabled()) {
                float reduction = PlatformHelper.getHatDamageReductionAmount() / 100f;
                float reducedDamage = damageAmount * (1 - reduction);
                entity.setHealth(entity.getHealth() + (damageAmount - reducedDamage));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        if (PlatformHelper.isHatDamageReductionEnabled()) {
            int reductionAmount = PlatformHelper.getHatDamageReductionAmount();
            Component magicDamage = Component.literal("[Magical Damage]").setStyle(Style.EMPTY.withColor(TextColor.parseColor("#AA00FF")));
            Component damageReduction = Component.literal("When worn: Reduces ").append(magicDamage).append(" by ").append(Component.literal(reductionAmount + "%"));
            tooltip.add(damageReduction);
        }
    }
}
