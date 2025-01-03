package net.satisfy.herbalbrews.fabric.core.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "herbalbrews")
@Config.Gui.Background("herbalbrews:textures/block/green_tea_leaf0.png")
public class HerbalBrewsFabricConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public ItemsSettings items = new ItemsSettings();

    @ConfigEntry.Gui.CollapsibleObject
    public BlocksSettings blocks = new BlocksSettings();

    public static class ItemsSettings {
        @ConfigEntry.Gui.CollapsibleObject
        public BannerSettings banner = new BannerSettings();

        @ConfigEntry.Gui.CollapsibleObject
        public HatSettings hat = new HatSettings();

        public static class BannerSettings {
            public boolean giveEffect = true;
            public boolean showTooltip = true;

            public boolean isShowTooltipEnabled() {
                return giveEffect && showTooltip;
            }
        }

        public static class HatSettings {
            public boolean damageReductionEnabled = true;
            public int damageReductionAmount = 40;
        }
    }

    public static class BlocksSettings {
        @ConfigEntry.Gui.PrefixText
        public int brewingDuration = 1200;
        public int dryingDuration = 900;
    }
}
