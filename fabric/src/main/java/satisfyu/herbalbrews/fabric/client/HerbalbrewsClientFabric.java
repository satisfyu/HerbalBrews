package satisfyu.herbalbrews.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import satisfyu.herbalbrews.client.HerbalbrewsClient;
import satisfyu.herbalbrews.player.layer.TopHatHelmetLayer;
import satisfyu.herbalbrews.player.model.TopHatHelmetModel;

public class HerbalbrewsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HerbalbrewsClient.preInitClient();
        HerbalbrewsClient.initClient();

        EntityModelLayerRegistry.registerModelLayer(HerbalbrewsClient.TOP_HAT_LAYER, TopHatHelmetModel::createBodyLayer);

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if(entityRenderer instanceof PlayerRenderer renderer) {
                registrationHelper.register(new TopHatHelmetLayer<>(renderer));
            }
        });
    }
}
