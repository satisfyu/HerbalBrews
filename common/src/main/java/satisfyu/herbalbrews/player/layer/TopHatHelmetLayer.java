package satisfyu.herbalbrews.player.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import satisfyu.herbalbrews.client.HerbalbrewsClient;
import satisfyu.herbalbrews.player.model.TopHatHelmetModel;

public class TopHatHelmetLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {

    private final TopHatHelmetModel<T> model;

    public TopHatHelmetLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
        this.model = new TopHatHelmetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(HerbalbrewsClient.TOP_HAT_LAYER));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean shouldRender = false;

        for (ItemStack stack : ((Player) entity).getInventory().armor) {
            if (stack.getItem().getDescriptionId().toLowerCase().contains("top_hat")) {
                shouldRender = true;
                break;
            }
        }

        if (shouldRender) {
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            poseStack.pushPose();
            poseStack.translate(0.0d, 0.0d, 0.0d);
            renderColoredCutoutModel(this.model, getTextureLocation(entity), poseStack, multiBufferSource, i, entity, 1.0f, 1.0f, 1.0f);
            poseStack.popPose();
        }
    }

    @Override
    protected @NotNull ResourceLocation getTextureLocation(T entity) {
        return TopHatHelmetModel.TOP_HAT_TEXTURE;
    }
}
