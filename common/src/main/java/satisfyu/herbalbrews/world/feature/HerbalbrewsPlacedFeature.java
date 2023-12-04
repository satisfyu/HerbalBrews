package satisfyu.herbalbrews.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;


public class HerbalbrewsPlacedFeature {
    public static final ResourceKey<PlacedFeature> CARDINAL_FLOWER_PATCH_CHANCE_KEY = registerKey("cardinal_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> JOE_PYE_FLOWER_PATCH_CHANCE_KEY = registerKey("joe_pye_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> HIBISCUS_FLOWER_PATCH_CHANCE_KEY = registerKey("hibiscus_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> SNOWBELL_FLOWER_PATCH_CHANCE_KEY = registerKey("snowbell_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> LAVENDER_FLOWER_PATCH_CHANCE_KEY = registerKey("lavender_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> MOUNTAIN_LAUREL_FLOWER_PATCH_CHANCE_KEY = registerKey("mountain_laurel_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> BIRD_OF_PARADISE_FLOWER_PATCH_CHANCE_KEY = registerKey("bird_of_paradise_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> WHITE_ORCHID_FLOWER_PATCH_CHANCE_KEY = registerKey("white_orchid_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> HYSSOP_FLOWER_PATCH_CHANCE_KEY = registerKey("hyssop_flower_patch_chance");

    public static final ResourceKey<PlacedFeature> LARCH_GRASS_PATCH_CHANCE_KEY = registerKey("larch_grass_patch_chance");
    public static final ResourceKey<PlacedFeature> GOATSBEARD_FLOWER_PATCH_CHANCE_KEY = registerKey("goatsbeard_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> GENISTEAE_FLOWER_PATCH_CHANCE_KEY = registerKey("genisteae_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> FOXGLOVE_WHITE_FLOWER_PATCH_CHANCE_KEY = registerKey("foxglove_white_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> FOXGLOVE_PINK_FLOWER_PATCH_CHANCE_KEY = registerKey("foxglove_pink_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> LUPINE_BLUE_FLOWER_PATCH_CHANCE_KEY = registerKey("lupine_blue_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> LUPINE_PURPLE_FLOWER_PATCH_CHANCE_KEY = registerKey("lupine_purple_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> BOTTLEBRUSHES_FLOWER_PATCH_CHANCE_KEY = registerKey("bottlebrushes_flower_patch_chance");

    public static final ResourceKey<PlacedFeature> BEGONIE_FLOWER_PATCH_CHANCE_KEY = registerKey("begonie_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> MYOSOTIS_FLOWER_PATCH_CHANCE_KEY = registerKey("myosotis_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> FREESIA_YELLOW_FLOWER_PATCH_CHANCE_KEY = registerKey("freesia_yellow_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> FREESIA_PINK_FLOWER_PATCH_CHANCE_KEY = registerKey("freesia_pink_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> DAPHNE_FLOWER_PATCH_CHANCE_KEY = registerKey("daphne_flower_patch_chance");
    public static final ResourceKey<PlacedFeature> BLUEBELL_FLOWER_PATCH_CHANCE_KEY = registerKey("bluebell_flower_patch_chance");

    public static final ResourceKey<PlacedFeature> YERBA_MATE_PLANT_PATCH_CHANCE_KEY = registerKey("yerba_mate_patch_chance");
    public static final ResourceKey<PlacedFeature> ROOIBOS_PLANT_PATCH_CHANCE_KEY = registerKey("rooibos_patch_chance");
    public static final ResourceKey<PlacedFeature> COFFEE_PLANT_PATCH_CHANCE_KEY = registerKey("coffee_patch_chance");

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new HerbalBrewsIdentifier(name));
    }
}


