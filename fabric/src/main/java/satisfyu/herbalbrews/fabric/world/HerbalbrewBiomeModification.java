package satisfyu.herbalbrews.fabric.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;
import satisfyu.herbalbrews.world.feature.HerbalbrewsPlacedFeature;

import java.util.function.Predicate;


public class HerbalbrewBiomeModification {

    public static void init() {
        BiomeModification world = BiomeModifications.create(new HerbalBrewsIdentifier("world_features"));
        Predicate<BiomeSelectionContext> swampBiomes = getHerbalbrewsSelector("swamp_biomes");
        Predicate<BiomeSelectionContext> jungleBiomes = getHerbalbrewsSelector("jungle_biomes");
        Predicate<BiomeSelectionContext> savannaBiomes = getHerbalbrewsSelector("savanna_biomes");
        Predicate<BiomeSelectionContext> mountainBiomes = getHerbalbrewsSelector("cherry_blossom_biomes");
        Predicate<BiomeSelectionContext> temperateBiomes = getHerbalbrewsSelector("temperate_biomes");
        Predicate<BiomeSelectionContext> larchBiomes = getHerbalbrewsSelector("larch_biomes");
        Predicate<BiomeSelectionContext> taigaBiomes = getHerbalbrewsSelector("taiga_biomes");
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.COFFEE_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, savannaBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.ROOIBOS_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, savannaBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.BEGONIE_FLOWER_PATCH_CHANCE_KEY));

        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.YERBA_MATE_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.BOTTLEBRUSHES_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, temperateBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.DAPHNE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, temperateBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.MOUNTAIN_LAUREL_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, swampBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.CARDINAL_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, swampBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.HYSSOP_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, swampBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.JOE_PYE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.HIBISCUS_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.WHITE_ORCHID_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.BIRD_OF_PARADISE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.SNOWBELL_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.LAVENDER_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.BLUEBELL_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, taigaBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.MYOSOTIS_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, taigaBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.LUPINE_BLUE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, taigaBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.LUPINE_PURPLE_FLOWER_PATCH_CHANCE_KEY));

        world.add(ModificationPhase.ADDITIONS, larchBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.LARCH_GRASS_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, larchBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.GOATSBEARD_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, larchBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.GENISTEAE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, larchBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.FOXGLOVE_WHITE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, swampBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.FOXGLOVE_PINK_FLOWER_PATCH_CHANCE_KEY));


        world.add(ModificationPhase.ADDITIONS, larchBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.FREESIA_YELLOW_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, larchBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.FREESIA_PINK_FLOWER_PATCH_CHANCE_KEY));

    }

    private static Predicate<BiomeSelectionContext> getHerbalbrewsSelector(String path) {
        return BiomeSelectors.tag(TagKey.create(Registries.BIOME, new HerbalBrewsIdentifier(path)));
    }


}
