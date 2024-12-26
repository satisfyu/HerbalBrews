package net.satisfy.herbalbrews.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.fabric.core.config.HerbalBrewsFabricConfig;
import net.satisfy.herbalbrews.fabric.core.world.HerbalbrewBiomeModification;
import net.satisfy.herbalbrews.core.registry.CompostableRegistry;

public class HerbalBrewsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoConfig.register(HerbalBrewsFabricConfig.class, GsonConfigSerializer::new);

        HerbalBrews.init();
        CompostableRegistry.registerCompostable();
        HerbalbrewBiomeModification.init();
    }
}
