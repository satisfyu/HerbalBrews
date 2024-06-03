package satisfyu.herbalbrews.player.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import satisfyu.herbalbrews.HerbalBrews;

public class TopHatHelmetModel<T extends Entity> extends EntityModel<T> {

    public static final ResourceLocation TOP_HAT_TEXTURE = new ResourceLocation(HerbalBrews.MOD_ID, "textures/entity/top_hat.png");

    private final ModelPart top_hat;

    public TopHatHelmetModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.top_hat = root.getChild("top_hat");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition top_hat = partdefinition.addOrReplaceChild("top_hat", CubeListBuilder.create().texOffs(-14, 13).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 0.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        top_hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}