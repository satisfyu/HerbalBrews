package satisfy.herbalbrews.forge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import satisfy.herbalbrews.HerbalBrews;
import satisfy.herbalbrews.client.HerbalbrewsClient;

@Mod.EventBusSubscriber(modid = HerbalBrews.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HerbalbrewsClientForge {
    @SubscribeEvent
    public static void beforeClientSetup(RegisterEvent event) {
        HerbalbrewsClient.preInitClient();
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        HerbalbrewsClient.initClient();
    }
}
