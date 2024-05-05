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
public class WitchHatModel<T extends Entity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new HerbalBrewsIdentifier("witch_hat"), "main");

	private final ModelPart witch_hat;


	public WitchHatModel(ModelPart root) {
		this.witch_hat = root.getChild("witch_hat");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition witch_hat = partdefinition.addOrReplaceChild("witch_hat", CubeListBuilder.create().texOffs(-16, 32).addBox(-7.9834F, 8.1F, -1.3306F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).mirror().addBox(-3.9834F, 4.0F, 2.6694F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.2F)).mirror(false)
				.texOffs(18, 9).addBox(-0.9651F, -2.7081F, 9.0388F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(-0.0166F, 16.0F, -6.6694F));

		PartDefinition cube_r1 = witch_hat.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 20).addBox(0.0F, -2.0F, -3.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(-1.9834F, -1.4194F, 9.6134F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = witch_hat.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-1.0F, -3.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(-1.9834F, 3.2F, 7.7F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}
	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		witch_hat.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}