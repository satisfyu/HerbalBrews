package net.satisfy.herbalbrews;

import net.satisfy.herbalbrews.core.event.CommonEvents;
import net.satisfy.herbalbrews.core.registry.*;

public class HerbalBrews {
    public static final String MOD_ID = "herbalbrews";

    public static void init() {
        ObjectRegistry.init();
        EntityTypeRegistry.init();
        EffectRegistry.init();
        RecipeTypeRegistry.init();
        SoundEventRegistry.init();
        ScreenHandlerTypeRegistry.init();
        TabRegistry.init();
        CommonEvents.init();
    }
}
