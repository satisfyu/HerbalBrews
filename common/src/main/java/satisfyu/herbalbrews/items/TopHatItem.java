package satisfyu.herbalbrews.items;

import de.cristelknight.doapi.common.item.CustomHatItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

public class TopHatItem extends CustomHatItem {
    public TopHatItem(Properties settings) {
        super(ArmorMaterials.LEATHER, Type.HELMET, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide()) {
            if (entity instanceof Player player) {
                this.checkForItem(player);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void checkForItem(Player player) {
        if (this.hasHat(player)) {
            this.addStatusEffect(player, new MobEffectInstance(MobEffects.DIG_SPEED, 14 * 20, 1));
        }
    }

    private boolean hasHat(Player player) {
        if (player.getInventory().getArmor(2).isEmpty()) return false;
        Item item = player.getInventory().getArmor(2).getItem();
        if (item instanceof ArmorItem armorItem) {
            return armorItem.getMaterial() == ArmorMaterials.LEATHER;
        }
        return false;
    }

    private void addStatusEffect(Player player, MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());
        MobEffectInstance effect = player.getEffect(mapStatusEffect.getEffect());
        if (!hasPlayerEffect || effect != null && effect.getDuration() < 11 * 20) {
            player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier(), true, false, true));
        }
    }

    @Override
    public ResourceLocation getTexture() {
        return new HerbalBrewsIdentifier("textures/entity/top_hat.png");
    }

    @Override
    public Float getOffset() {
        return -1.9f;
    }
}
