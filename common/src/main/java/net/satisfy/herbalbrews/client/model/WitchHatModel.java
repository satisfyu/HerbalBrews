package net.satisfy.herbalbrews.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;

public class WitchHatModel<T extends Entity> extends EntityModel<T> implements HatModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new HerbalBrewsIdentifier("witch_hat_helmet"), "main");
    private final ModelPart witchHat;

    public WitchHatModel(ModelPart root) {
        this.witchHat = root.getChild("witchHat");
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition witchHat = partdefinition.addOrReplaceChild("witchHat", CubeListBuilder.create()
                .texOffs(-16, 32).addBox(-8.0F, -7.0F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-4.0F, -11.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-3.0F, -16.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 20).addBox(-2.0F, -19.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(18, 9).addBox(-1.0F, -21.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }








    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        poseStack.scale(1.05F, 1.05F, 1.05F);
        witchHat.render(poseStack, buffer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void copyHead(ModelPart model) {
        witchHat.copyFrom(model);
    }
}