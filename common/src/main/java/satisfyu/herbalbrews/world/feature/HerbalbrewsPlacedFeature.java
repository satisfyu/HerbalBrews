package satisfyu.herbalbrews.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;


public class HerbalbrewsPlacedFeature {
    public static final ResourceKey<PlacedFeature> CARDINAL_FLOWER_PATCH_CHANCE_KEY = registerKey("cardinal_flower_chance");
    public static final ResourceKey<PlacedFeature> JOE_PYE_FLOWER_PATCH_CHANCE_KEY = registerKey("joe_pye_flower_chance");
    public static final ResourceKey<PlacedFeature> HIBISCUS_FLOWER_PATCH_CHANCE_KEY = registerKey("hibiscus_flower_chance");
    public static final ResourceKey<PlacedFeature> SNOWBELL_FLOWER_PATCH_CHANCE_KEY = registerKey("snowbell_flower_chance");
    public static final ResourceKey<PlacedFeature> LAVENDER_FLOWER_PATCH_CHANCE_KEY = registerKey("lavender_flower_chance");
    public static final ResourceKey<PlacedFeature> CHAMOMILE_FLOWER_PATCH_CHANCE_KEY = registerKey("chamomile_flower_chance");
    public static final ResourceKey<PlacedFeature> BIRD_OF_PARADISE_FLOWER_PATCH_CHANCE_KEY = registerKey("bird_of_paradise_flower_chance");
    public static final ResourceKey<PlacedFeature> WHITE_ORCHID_FLOWER_PATCH_CHANCE_KEY = registerKey("white_orchid_flower_chance");

    public static final ResourceKey<PlacedFeature> YERBA_MATE_PLANT_PATCH_CHANCE_KEY = registerKey("yerba_mate_chance");
    public static final ResourceKey<PlacedFeature> ROOIBOS_PLANT_PATCH_CHANCE_KEY = registerKey("rooibos_chance");
    public static final ResourceKey<PlacedFeature> COFFEE_PLANT_PATCH_CHANCE_KEY = registerKey("coffee_chance");

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new HerbalBrewsIdentifier(name));
    }
}


