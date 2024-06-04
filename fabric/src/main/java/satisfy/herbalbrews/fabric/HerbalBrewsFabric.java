package satisfy.herbalbrews.fabric;

import net.fabricmc.api.ModInitializer;
import satisfy.herbalbrews.HerbalBrews;
import satisfy.herbalbrews.fabric.world.HerbalbrewBiomeModification;
import satisfy.herbalbrews.registry.CompostableRegistry;

public class HerbalBrewsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        HerbalBrews.init();
        CompostableRegistry.registerCompostable();
        HerbalbrewBiomeModification.init();
    }
}
