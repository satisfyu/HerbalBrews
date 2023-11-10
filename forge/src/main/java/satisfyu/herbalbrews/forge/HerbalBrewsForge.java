package satisfyu.herbalbrews.forge;

import dev.architectury.platform.forge.EventBuses;
import satisfyu.herbalbrews.HerbalBrews;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HerbalBrews.MOD_ID)
public class HerbalBrewsForge {
    public HerbalBrewsForge() {
        EventBuses.registerModEventBus(HerbalBrews.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        HerbalBrews.init();
    }
}
