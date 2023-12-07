package satisfyu.herbalbrews;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfyu.herbalbrews.registry.*;

public class HerbalBrews {
    public static final String MOD_ID = "herbalbrews";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        ObjectRegistry.init();
        BlockEntityRegistry.init();
        SoundEventRegistry.init();
        EffectRegistry.init();
        RecipeTypeRegistry.init();
        ScreenHandlerTypeRegistry.init();
        TabRegistry.init();
    }

    public static void commonInit() {
        ObjectRegistry.registerCompostable();
    }
}

