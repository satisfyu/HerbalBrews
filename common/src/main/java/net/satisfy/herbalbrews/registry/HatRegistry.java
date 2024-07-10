package net.satisfy.herbalbrews.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.item.Item;
import net.satisfy.herbalbrews.client.model.HatModel;
import net.satisfy.herbalbrews.client.model.TopHatModel;
import net.satisfy.herbalbrews.client.model.WitchHatModel;

import java.util.HashMap;
import java.util.Map;

public class HatRegistry {
    private static final Map<Item, HatModel> models = new HashMap<>();

    public static Model getHatModel(Item item, ModelPart baseHead) {
        EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();
        HatModel model = models.computeIfAbsent(item, key -> {
            if (key == ObjectRegistry.TOP_HAT.get()) {
                return new TopHatModel<>(modelSet.bakeLayer(TopHatModel.LAYER_LOCATION));
            } else if (key == ObjectRegistry.WITCH_HAT.get()) {
                return new WitchHatModel<>(modelSet.bakeLayer(WitchHatModel.LAYER_LOCATION));
            } else {
                return null;
            }
        });

        assert model != null;
        model.copyHead(baseHead);

        return (Model) model;
    }
}
