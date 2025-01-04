package net.satisfy.herbalbrews.forge.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.io.File;

public class HerbalBrewsForgeConfig {
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static final ForgeConfigSpec.BooleanValue ITEMS_BANNER_GIVE_EFFECT;
    public static final ForgeConfigSpec.BooleanValue ITEMS_BANNER_SHOW_TOOLTIP;
    public static final ForgeConfigSpec.BooleanValue ITEMS_HAT_DAMAGE_REDUCTION_ENABLED;
    public static final ForgeConfigSpec.IntValue ITEMS_HAT_DAMAGE_REDUCTION_AMOUNT;
    
    public static final ForgeConfigSpec.IntValue BLOCKS_DRYING_DURATION;
    public static final ForgeConfigSpec.IntValue BLOCKS_BREWING_DURATION;
    public static final ForgeConfigSpec.IntValue BLOCKS_JUG_EFFECT_DURATION;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("items");

        builder.push("banner");
        ITEMS_BANNER_GIVE_EFFECT = builder
                .comment("Enable or disable the effect granted by banners.")
                .define("giveEffect", true);
        ITEMS_BANNER_SHOW_TOOLTIP = builder
                .comment("Enable or disable the tooltip display for banners.")
                .define("showTooltip", true);
        builder.pop();

        builder.push("hat");
        ITEMS_HAT_DAMAGE_REDUCTION_ENABLED = builder
                .comment("Enable or disable magic damage reduction provided by the hat.")
                .define("damageReductionEnabled", true);
        ITEMS_HAT_DAMAGE_REDUCTION_AMOUNT = builder
                .comment("Percentage of magic damage reduction provided by the hat (0-100).")
                .defineInRange("damageReductionAmount", 40, 0, 100);
        builder.pop();

        builder.pop(); 

        builder.push("blocks");
        BLOCKS_DRYING_DURATION = builder
                .comment("Duration of the drying process in ticks. (20 ticks = 1 second)")
                .defineInRange("dryingDuration", 900, 0, Integer.MAX_VALUE);
        BLOCKS_BREWING_DURATION = builder
                .comment("Duration of the brewing process in ticks. (20 ticks = 1 second)")
                .defineInRange("brewingDuration", 1200, 0, Integer.MAX_VALUE);
        BLOCKS_JUG_EFFECT_DURATION = builder
                .comment("Duration of the jug's effect in ticks. (20 ticks = 1 second)")
                .defineInRange("jugEffectDuration", 900, 0, Integer.MAX_VALUE);
        builder.pop();

        COMMON_CONFIG = builder.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) {
    }

    public static void loadConfig(ForgeConfigSpec spec, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path))
                .sync()
                .preserveInsertionOrder()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        file.load();
        spec.setConfig(file);
    }
}
