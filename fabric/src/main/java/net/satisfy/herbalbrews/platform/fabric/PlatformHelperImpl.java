package net.satisfy.herbalbrews.platform.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import net.satisfy.herbalbrews.fabric.core.config.HerbalBrewsFabricConfig;
import net.satisfy.herbalbrews.platform.PlatformHelper;

public class PlatformHelperImpl extends PlatformHelper {
    public static boolean shouldGiveEffect() {
        HerbalBrewsFabricConfig config = AutoConfig.getConfigHolder(HerbalBrewsFabricConfig.class).getConfig();
        return config.items.banner.giveEffect;
    }

    public static boolean shouldShowTooltip() {
        HerbalBrewsFabricConfig config = AutoConfig.getConfigHolder(HerbalBrewsFabricConfig.class).getConfig();
        return config.items.banner.isShowTooltipEnabled();
    }

    public static int getDryingDuration() {
        HerbalBrewsFabricConfig config = AutoConfig.getConfigHolder(HerbalBrewsFabricConfig.class).getConfig();
        return config.blocks.dryingDuration;
    }

    public static int getBrewingDuration() {
        HerbalBrewsFabricConfig config = AutoConfig.getConfigHolder(HerbalBrewsFabricConfig.class).getConfig();
        return config.blocks.brewingDuration;
    }

    public static boolean isHatDamageReductionEnabled() {
        HerbalBrewsFabricConfig config = AutoConfig.getConfigHolder(HerbalBrewsFabricConfig.class).getConfig();
        return config.items.hat.damageReductionEnabled;
    }

    public static int getHatDamageReductionAmount() {
        HerbalBrewsFabricConfig config = AutoConfig.getConfigHolder(HerbalBrewsFabricConfig.class).getConfig();
        return config.items.hat.damageReductionAmount;
    }

    public static int getJugEffectDuration() {
        HerbalBrewsFabricConfig config = AutoConfig.getConfigHolder(HerbalBrewsFabricConfig.class).getConfig();
        return config.blocks.jugEffectDuration;
    }
}
