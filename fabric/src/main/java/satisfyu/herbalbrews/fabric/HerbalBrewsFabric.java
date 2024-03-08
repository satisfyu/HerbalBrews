package satisfyu.herbalbrews.fabric;

import satisfyu.herbalbrews.HerbalBrews;
import net.fabricmc.api.ModInitializer;
import satisfyu.herbalbrews.fabric.world.HerbalbrewBiomeModification;
import satisfyu.herbalbrews.registry.CompostableRegistry;
import satisfyu.herbalbrews.registry.ObjectRegistry;

public class HerbalBrewsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        HerbalBrews.init();
        CompostableRegistry.registerCompostable();
        HerbalbrewBiomeModification.init();
    }
}
