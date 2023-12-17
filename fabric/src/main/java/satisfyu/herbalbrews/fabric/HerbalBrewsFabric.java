package satisfyu.herbalbrews.fabric;

import satisfyu.herbalbrews.HerbalBrews;
import net.fabricmc.api.ModInitializer;
import satisfyu.herbalbrews.fabric.world.HerbalbrewBiomeModification;
import satisfyu.herbalbrews.registry.CompostableRegistry;

public class HerbalBrewsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        HerbalBrews.init();
        HerbalbrewBiomeModification.init();
        CompostableRegistry.registerCompostable();
    }
}
