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

public class TopHatModel<T extends Entity> extends EntityModel<T> implements HatModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new HerbalBrewsIdentifier("top_hat_helmet"), "main");
    private final ModelPart topHat;

    public TopHatModel(ModelPart root) {
        this.topHat = root.getChild("topHat");
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition topHat = partdefinition.addOrReplaceChild("topHat", CubeListBuilder.create().texOffs(-15, 14).addBox(-7.5F, -6.0F, -7.5F, 15.0F, 0.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.5F, -11.0F, -4.5F, 9.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        poseStack.scale(1F, 1F, 1F);
        topHat.render(poseStack, buffer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {

    }

    @Override
    public void copyHead(ModelPart model) {
        topHat.copyFrom(model);
    }
}