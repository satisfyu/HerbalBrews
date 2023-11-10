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

        world.add(ModificationPhase.ADDITIONS, temperateBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.CHAMOMILE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, swampBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.CARDINAL_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, swampBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.JOE_PYE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.HIBISCUS_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.COFFEE_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.WHITE_ORCHID_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.BIRD_OF_PARADISE_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, savannaBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.ROOIBOS_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.YERBA_MATE_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.SNOWBELL_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.LAVENDER_FLOWER_PATCH_CHANCE_KEY));

    }

    private static Predicate<BiomeSelectionContext> getHerbalbrewsSelector(String path) {
        return BiomeSelectors.tag(TagKey.create(Registries.BIOME, new HerbalBrewsIdentifier(path)));
    }


}
