package net.satisfy.herbalbrews.platform.forge;

import net.satisfy.herbalbrews.forge.config.HerbalBrewsForgeConfig;
import net.satisfy.herbalbrews.platform.PlatformHelper;

public class PlatformHelperImpl extends PlatformHelper {
    public static boolean shouldGiveEffect() {
        return HerbalBrewsForgeConfig.ITEMS_BANNER_GIVE_EFFECT.get();
    }

    public static boolean shouldShowTooltip() {
        return HerbalBrewsForgeConfig.ITEMS_BANNER_SHOW_TOOLTIP.get();
    }

    public static int getDryingDuration() {
        return HerbalBrewsForgeConfig.BLOCKS_DRYING_DURATION.get();
    }

    public static int getBrewingDuration() {
        return HerbalBrewsForgeConfig.BLOCKS_BREWING_DURATION.get();
    }

    public static boolean isHatDamageReductionEnabled() {
        return HerbalBrewsForgeConfig.ITEMS_HAT_DAMAGE_REDUCTION_ENABLED.get();
    }

    public static int getHatDamageReductionAmount() {
        return HerbalBrewsForgeConfig.ITEMS_HAT_DAMAGE_REDUCTION_AMOUNT.get();
    }

    public static int getJugEffectDuration() {
        return HerbalBrewsForgeConfig.BLOCKS_JUG_EFFECT_DURATION.get();
    }
}
