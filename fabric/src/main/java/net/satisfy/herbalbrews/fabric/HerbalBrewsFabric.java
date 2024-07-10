package net.satisfy.herbalbrews.fabric;

import net.fabricmc.api.ModInitializer;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.fabric.world.HerbalbrewBiomeModification;
import net.satisfy.herbalbrews.registry.CompostableRegistry;

public class HerbalBrewsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        HerbalBrews.init();
        CompostableRegistry.registerCompostable();
        HerbalbrewBiomeModification.init();
    }
}
