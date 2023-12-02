package satisfyu.herbalbrews.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import satisfyu.herbalbrews.client.model.RedWolfModel;
import satisfyu.herbalbrews.client.model.WanderingGardenerModel;
import satisfyu.herbalbrews.entities.RedWolfEntity;
import satisfyu.herbalbrews.entities.WanderingGardenerEntity;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;


@Environment(value = EnvType.CLIENT)

public class RedWolfRenderer extends MobRenderer<RedWolfEntity, RedWolfModel<RedWolfEntity>> {
    private static final ResourceLocation TEXTURE = new HerbalBrewsIdentifier("textures/entity/red_wolf.png");

    public RedWolfRenderer(EntityRendererProvider.Context context) {
        super(context, new RedWolfModel(context.bakeLayer(RedWolfModel.LAYER_LOCATION)), 0.7f);
    }



    @Override
    public ResourceLocation getTextureLocation(RedWolfEntity entity) {
        return TEXTURE;
    }
}
