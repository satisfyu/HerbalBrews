package satisfyu.herbalbrews;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfyu.herbalbrews.event.CommonEvents;
import satisfyu.herbalbrews.registry.*;

public class HerbalBrews {
    public static final String MOD_ID = "herbalbrews";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        EffectRegistry.init();
        ObjectRegistry.init();
        BlockEntityRegistry.init();
        SoundEventRegistry.init();
        RecipeTypeRegistry.init();
        ScreenHandlerTypeRegistry.init();
        TabRegistry.init();
        CommonEvents.init();
    }
}

