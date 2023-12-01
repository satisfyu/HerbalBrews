package satisfyu.herbalbrews;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfyu.herbalbrews.registry.*;

public class HerbalBrews {
    public static final String MOD_ID = "herbalbrews";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static ResourceLocation ID(String path) {
        return new ResourceLocation(HerbalBrews.MOD_ID, path);
    }

    public static void init() {
        ObjectRegistry.init();
        BoatsAndSignsRegistry.init();
        BlockEntityRegistry.init();
        EntityRegistry.init();
        EffectRegistry.init();
        RecipeTypeRegistry.init();
        ScreenHandlerTypeRegistry.init();
        TabRegistry.init();
    }

    public static void commonInit() {
        ObjectRegistry.registerCompostable();
    }
}

