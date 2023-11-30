package satisfyu.herbalbrews.registry;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import satisfyu.herbalbrews.client.model.WitchHatModel;

import java.util.Map;

public class ArmorRegistry {

    public static void registerArmorModelLayers(){
        EntityModelLayerRegistry.register(WitchHatModel.LAYER_LOCATION, WitchHatModel::getTexturedModelData);
    }

    public static  <T extends LivingEntity> void registerHatModels(Map<Item, EntityModel<T>> models, EntityModelSet modelLoader) {
        models.put(ObjectRegistry.WITCH_HAT.get(), new WitchHatModel<>(modelLoader.bakeLayer(WitchHatModel.LAYER_LOCATION)));
    }
}