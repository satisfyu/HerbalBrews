package net.satisfy.herbalbrews.fabric.core.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;
import net.satisfy.herbalbrews.core.world.feature.HerbalbrewsPlacedFeature;

import java.util.function.Predicate;


public class HerbalbrewBiomeModification {

    public static void init() {
        BiomeModification world = BiomeModifications.create(new HerbalBrewsIdentifier("world_features"));
        Predicate<BiomeSelectionContext> jungleBiomes = getHerbalbrewsSelector("jungle_biomes");
        Predicate<BiomeSelectionContext> savannaBiomes = getHerbalbrewsSelector("savanna_biomes");
        Predicate<BiomeSelectionContext> mountainBiomes = getHerbalbrewsSelector("mountain_biomes");
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.COFFEE_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, savannaBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.ROOIBOS_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.YERBA_MATE_PLANT_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, jungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.HIBISCUS_FLOWER_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, mountainBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HerbalbrewsPlacedFeature.LAVENDER_FLOWER_PATCH_CHANCE_KEY));
    }

    private static Predicate<BiomeSelectionContext> getHerbalbrewsSelector(String path) {
        return BiomeSelectors.tag(TagKey.create(Registries.BIOME, new HerbalBrewsIdentifier(path)));
    }
}
