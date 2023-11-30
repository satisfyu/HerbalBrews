package satisfyu.herbalbrews.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import static satisfyu.herbalbrews.HerbalBrews.MOD_ID;

public class HerbalBrewsSurfaceRules {

    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final ResourceKey<NormalNoise.NoiseParameters> SURFACE = register("surface");
    private static final ResourceKey<NormalNoise.NoiseParameters> CRACKS = register("cracks");


    public static final ResourceKey<Biome> LARCH_FOREST_KEY = createBiomeKey("larch_forest");

    private static ResourceKey<Biome> createBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(MOD_ID, name));
    }

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);


        SurfaceRules.RuleSource clearing = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(CRACKS, -0.15, 0.15),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, 0.2, Double.parseDouble("1.7976931348623157e+308")), STONE)));


        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(LARCH_FOREST_KEY), SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.steep(), STONE), clearing)))));
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    private static ResourceKey<NormalNoise.NoiseParameters> register(String name) {
        return ResourceKey.create(Registries.NOISE, new ResourceLocation(MOD_ID, name));
    }
}