package satisfyu.herbalbrews.items;

import de.cristelknight.doapi.common.item.CustomHatItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterials;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

public class WitchHatItem extends CustomHatItem {


    public WitchHatItem(Properties settings) {
        super(ArmorMaterials.LEATHER, Type.HELMET, settings);
    }

    @Override
    public ResourceLocation getTexture() {
        return new HerbalBrewsIdentifier("textures/entity/witch_hat.png");
    }

    @Override
    public Float getOffset() {
        return -1.85f;
    }
}
