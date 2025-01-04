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

    @ExpectPlatform
    public static int getDryingDuration() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getBrewingDuration() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isHatDamageReductionEnabled() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getHatDamageReductionAmount() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getJugEffectDuration() {
        throw new AssertionError();
    }
}
