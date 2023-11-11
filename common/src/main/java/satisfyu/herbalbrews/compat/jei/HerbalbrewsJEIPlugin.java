package satisfyu.herbalbrews.compat.jei;

import dev.architectury.registry.registries.RegistrySupplier;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import satisfyu.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import satisfyu.herbalbrews.compat.jei.categorys.TeaKettleCategory;
import satisfyu.herbalbrews.compat.jei.transfer.CookingTransferInfo;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.ObjectRegistry;
import satisfyu.herbalbrews.registry.RecipeTypeRegistry;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

import java.util.List;
import java.util.Objects;


@JeiPlugin
public class HerbalbrewsJEIPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new TeaKettleCategory(registration.getJeiHelpers().getGuiHelper()));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<TeaKettleRecipe> fridgeRecipes = rm.getAllRecipesFor(RecipeTypeRegistry.TEA_KETTLE_RECIPE_TYPE.get());
        registration.addRecipes(TeaKettleCategory.TEA_KETTLE, fridgeRecipes);

    }

    @Override
    public ResourceLocation getPluginUid() {
        return new HerbalBrewsIdentifier("jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        addCatalyst(registration, ObjectRegistry.TEA_KETTLE, TeaKettleCategory.TEA_KETTLE);
        addCatalyst(registration, ObjectRegistry.COPPER_TEA_KETTLE, TeaKettleCategory.TEA_KETTLE);

    }

    private static void addCatalyst(IRecipeCatalystRegistration registration, RegistrySupplier<Block> block, RecipeType<?>... recipeTypes){
        registration.addRecipeCatalyst(block.get().asItem().getDefaultInstance(), recipeTypes);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(new CookingTransferInfo());
    }

    public static void addSlot(IRecipeLayoutBuilder builder, int x, int y, Ingredient ingredient){
        builder.addSlot(RecipeIngredientRole.INPUT, x, y).addIngredients(ingredient);
    }
}