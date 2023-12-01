package satisfyu.herbalbrews.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.recipe.CauldronRecipe;

import java.util.function.Supplier;

public class RecipeTypeRegistry {

    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.RECIPE_SERIALIZER);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.RECIPE_TYPE);

    public static final RegistrySupplier<RecipeType<TeaKettleRecipe>> TEA_KETTLE_RECIPE_TYPE = create("kettle_brewing");
    public static final RegistrySupplier<RecipeSerializer<TeaKettleRecipe>> TEAK_KETTLE_RECIPE_SERIALIZER = create("kettle_brewing", TeaKettleRecipe.Serializer::new);
    public static final RegistrySupplier<RecipeType<CauldronRecipe>> CAULDRON_RECIPE_TYPE = create("cauldron_brewing");
    public static final RegistrySupplier<RecipeSerializer<CauldronRecipe>> CAULDRON_RECIPE_SERIALIZER = create("cauldron_brewing", CauldronRecipe.Serializer::new);

    private static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> create(String name, Supplier<RecipeSerializer<T>> serializer) {
        return RECIPE_SERIALIZERS.register(name, serializer);
    }

    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> create(String name) {
        Supplier<RecipeType<T>> type = () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        return RECIPE_TYPES.register(name, type);
    }

    public static void init() {
        RECIPE_SERIALIZERS.register();
        RECIPE_TYPES.register();
    }


}
