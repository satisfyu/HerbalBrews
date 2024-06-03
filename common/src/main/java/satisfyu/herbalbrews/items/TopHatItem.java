package satisfyu.herbalbrews.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class TopHatItem extends Item implements Equipable {

    protected final ArmorItem.Type type;

    public TopHatItem(ArmorMaterial pMaterial, ArmorItem.Type pType, Properties pProperties) {
        super(pProperties.defaultDurability(pMaterial.getDurabilityForType(pType)));
        this.type = pType;
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return this.type.getSlot();
    }
}