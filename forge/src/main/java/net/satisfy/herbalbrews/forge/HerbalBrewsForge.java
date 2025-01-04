package net.satisfy.herbalbrews.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.core.registry.CompostableRegistry;
import net.satisfy.herbalbrews.forge.config.HerbalBrewsForgeConfig;

@Mod(HerbalBrews.MOD_ID)
public class HerbalBrewsForge {
    public HerbalBrewsForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(HerbalBrews.MOD_ID, modEventBus);
        HerbalBrews.init();
        HerbalBrewsForgeConfig.loadConfig(HerbalBrewsForgeConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("herbalbrews.toml").toString());
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(CompostableRegistry::registerCompostable);
    }
}
