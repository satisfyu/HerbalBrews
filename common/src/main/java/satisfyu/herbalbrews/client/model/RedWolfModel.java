package satisfyu.herbalbrews.client.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.animal.Wolf;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

@Environment(EnvType.CLIENT)
public class RedWolfModel<T extends Wolf> extends HierarchicalModel<T> {
        public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new HerbalBrewsIdentifier("red_wolf"), "main");
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart upperBody;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart tail;


    public RedWolfModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.upperBody = root.getChild("upperBody");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 0).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).mirror().addBox(-4.0F, -7.0F, 0.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).addBox(1.0F, -7.0F, 0.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 0).addBox(-2.0F, -0.0156F, -7.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 13.5F, -7.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -5.0F, 6.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition upperBody = partdefinition.addOrReplaceChild("upperBody", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 14.0F, 2.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(8, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 16.0F, 7.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(16, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 16.0F, 7.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, -0.99F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 16.0F, -4.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(28, 7).addBox(-1.0F, 0.0F, -0.99F, 2.0F, 8.0F, 2.01F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 16.0F, -4.0F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 33).addBox(-2.0F, 0.0F, -4.25F, 4.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(29, 19).addBox(-2.5F, -1.0F, -3.75F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 12.0F, 8.0F, 0.9599F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        upperBody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root();
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {

    }
}