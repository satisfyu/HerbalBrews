package satisfyu.herbalbrews.fabric;

import satisfyu.herbalbrews.HerbalBrews;
import net.fabricmc.api.ModInitializer;
import satisfyu.herbalbrews.fabric.world.HerbalbrewBiomeModification;

public class HerbalBrewsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        HerbalBrews.init();
        HerbalbrewBiomeModification.init();
    }
}
