package net.satisfy.herbalbrews.fabric.core.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "bakery")
@Config.Gui.Background("minecraft:textures/block/bricks.png")
public class HerbalBrewsFabricConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public ItemsSettings items = new ItemsSettings();

    public static class ItemsSettings {
        @ConfigEntry.Gui.CollapsibleObject
        public BannerSettings banner = new BannerSettings();

        public static class BannerSettings {
            public boolean giveEffect = true;
            public boolean showTooltip = true;

            public boolean isShowTooltipEnabled() {
                return giveEffect && showTooltip;
            }

        }
    }
}
