package net.satisfy.herbalbrews.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.satisfy.herbalbrews.util.HerbalBrewsIdentifier;


public class HerbalbrewsPlacedFeature {
    public static final ResourceKey<PlacedFeature> LAVENDER_FLOWER_PATCH_CHANCE_KEY = registerKey("lavender_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> HIBISCUS_FLOWER_PATCH_CHANCE_KEY = registerKey("hibiscus_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> YERBA_MATE_PLANT_PATCH_CHANCE_KEY = registerKey("yerba_mate_patch_chance");
    public static final ResourceKey<PlacedFeature> ROOIBOS_PLANT_PATCH_CHANCE_KEY = registerKey("rooibos_patch_chance");
    public static final ResourceKey<PlacedFeature> COFFEE_PLANT_PATCH_CHANCE_KEY = registerKey("coffee_patch_chance");

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new HerbalBrewsIdentifier(name));
    }
}


