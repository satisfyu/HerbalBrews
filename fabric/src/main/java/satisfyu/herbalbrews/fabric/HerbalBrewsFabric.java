package satisfyu.herbalbrews.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import satisfyu.herbalbrews.HerbalBrews;
import net.fabricmc.api.ModInitializer;
import satisfyu.herbalbrews.fabric.client.HatArmorRenderer;
import satisfyu.herbalbrews.fabric.world.HerbalbrewBiomeModification;
import satisfyu.herbalbrews.registry.CompostableRegistry;
import satisfyu.herbalbrews.registry.ObjectRegistry;

public class HerbalBrewsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        HerbalBrews.init();
        CompostableRegistry.registerCompostable();
        HerbalbrewBiomeModification.init();

        ArmorRenderer.register(new HatArmorRenderer(), ObjectRegistry.TOP_HAT.get(), ObjectRegistry.WITCH_HAT.get());
    }
}
