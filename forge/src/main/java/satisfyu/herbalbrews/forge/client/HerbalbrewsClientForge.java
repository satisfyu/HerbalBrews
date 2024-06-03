package satisfyu.herbalbrews.forge.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.client.HerbalbrewsClient;
import satisfyu.herbalbrews.player.layer.TopHatHelmetLayer;
import satisfyu.herbalbrews.player.model.TopHatHelmetModel;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = HerbalBrews.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HerbalbrewsClientForge {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HerbalbrewsClient.TOP_HAT_LAYER, TopHatHelmetModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void constructLayers(EntityRenderersEvent.AddLayers event) {
        addLayerToPlayerSkin(event, "default", TopHatHelmetLayer::new);
        addLayerToPlayerSkin(event, "slim", TopHatHelmetLayer::new);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <E extends Player, M extends HumanoidModel<E>>
    void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, String skinName, Function<LivingEntityRenderer<E, M>, ? extends RenderLayer<E, M>> factory)
    {
        LivingEntityRenderer renderer = event.getSkin(skinName);
        if (renderer != null) renderer.addLayer(factory.apply(renderer));
    }

    @SubscribeEvent
    public static void beforeClientSetup(RegisterEvent event) {
        HerbalbrewsClient.preInitClient();
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        HerbalbrewsClient.initClient();
    }
}
