package satisfy.herbalbrews;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfy.herbalbrews.event.CommonEvents;
import satisfy.herbalbrews.registry.*;

public class HerbalBrews {
    public static final String MOD_ID = "herbalbrews";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        ObjectRegistry.init();
        EffectRegistry.init();
        BlockEntityRegistry.init();
        RecipeTypeRegistry.init();
        ScreenHandlerTypeRegistry.init();
        TabRegistry.init();
        CommonEvents.init();
    }
}
