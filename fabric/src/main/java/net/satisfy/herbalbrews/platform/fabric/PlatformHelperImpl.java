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
}
