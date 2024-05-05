package satisfyu.herbalbrews.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

@Environment(EnvType.CLIENT)
public class TopHatModel<T extends Entity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new HerbalBrewsIdentifier("top_hat"), "main");

	private final ModelPart witch_hat;

	public TopHatModel(ModelPart root) {
		this.witch_hat = root.getChild("top_hat");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition top_hat = partdefinition.addOrReplaceChild("top_hat", CubeListBuilder.create().texOffs(-14, 13).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 0.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -5.1F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		witch_hat.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}