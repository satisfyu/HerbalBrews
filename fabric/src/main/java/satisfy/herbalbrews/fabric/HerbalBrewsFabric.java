package satisfy.herbalbrews.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import satisfy.herbalbrews.HerbalBrews;
import net.fabricmc.api.ModInitializer;
import satisfy.herbalbrews.fabric.client.HatArmorRenderer;
import satisfy.herbalbrews.fabric.world.HerbalbrewBiomeModification;
import satisfy.herbalbrews.registry.CompostableRegistry;
import satisfy.herbalbrews.registry.ObjectRegistry;

public class HerbalBrewsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        HerbalBrews.init();
        CompostableRegistry.registerCompostable();
        HerbalbrewBiomeModification.init();

        ArmorRenderer.register(new HatArmorRenderer(), ObjectRegistry.TOP_HAT.get(), ObjectRegistry.WITCH_HAT.get());
    }
}
