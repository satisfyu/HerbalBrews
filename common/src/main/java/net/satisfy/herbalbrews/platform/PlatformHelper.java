package net.satisfy.herbalbrews.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class PlatformHelper {
    @ExpectPlatform
    public static boolean shouldGiveEffect() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean shouldShowTooltip() {
        throw new AssertionError();
    }

}
