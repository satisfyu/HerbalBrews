package satisfyu.herbalbrews.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import satisfyu.herbalbrews.client.model.WanderingGardenerModel;
import satisfyu.herbalbrews.entities.WanderingGardenerEntity;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;


@Environment(value = EnvType.CLIENT)
public class WanderingGardenerRenderer<T extends WanderingGardenerEntity> extends MobRenderer<T, WanderingGardenerModel<T>> {
    private static final ResourceLocation TEXTURE = new HerbalBrewsIdentifier("textures/entity/wandering_gardener.png");

    public WanderingGardenerRenderer(EntityRendererProvider.Context context) {
        super(context, new WanderingGardenerModel<>(context.bakeLayer(WanderingGardenerModel.LAYER_LOCATION)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(WanderingGardenerEntity entity) {
        return TEXTURE;
    }

}
