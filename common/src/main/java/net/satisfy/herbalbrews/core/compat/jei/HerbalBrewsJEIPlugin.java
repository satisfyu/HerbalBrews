package net.satisfy.herbalbrews.core.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfy.herbalbrews.client.gui.handler.CauldronGuiHandler;
import net.satisfy.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import net.satisfy.herbalbrews.core.compat.jei.category.CauldronCategory;
import net.satisfy.herbalbrews.core.compat.jei.category.TeaKettleCategory;
import net.satisfy.herbalbrews.core.recipe.CauldronRecipe;
import net.satisfy.herbalbrews.core.recipe.TeaKettleRecipe;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;
import net.satisfy.herbalbrews.core.registry.RecipeTypeRegistry;
import net.satisfy.herbalbrews.core.registry.ScreenHandlerTypeRegistry;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class HerbalBrewsJEIPlugin implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new TeaKettleCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CauldronCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<TeaKettleRecipe> cookingCauldronRecipes = rm.getAllRecipesFor(RecipeTypeRegistry.TEA_KETTLE_RECIPE_TYPE.get());
        registration.addRecipes(TeaKettleCategory.TEA_KETTLE_TYPE, cookingCauldronRecipes);

        List<CauldronRecipe> cauldronRecipes = rm.getAllRecipesFor(RecipeTypeRegistry.CAULDRON_RECIPE_TYPE.get());
        registration.addRecipes(CauldronCategory.CAULDRON_TYPE, cauldronRecipes);
    }


    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new HerbalBrewsIdentifier("jei_plugin");
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(TeaKettleGuiHandler.class, ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get(), TeaKettleCategory.TEA_KETTLE_TYPE, 1, 6, 7, 36);
        registration.addRecipeTransferHandler(CauldronGuiHandler.class, ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), CauldronCategory.CAULDRON_TYPE, 1, 3, 5, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ObjectRegistry.CAULDRON.get().asItem().getDefaultInstance(), CauldronCategory.CAULDRON_TYPE);
        registration.addRecipeCatalyst(ObjectRegistry.TEA_KETTLE.get().asItem().getDefaultInstance(), TeaKettleCategory.TEA_KETTLE_TYPE);
        registration.addRecipeCatalyst(ObjectRegistry.COPPER_TEA_KETTLE.get().asItem().getDefaultInstance(), TeaKettleCategory.TEA_KETTLE_TYPE);
    }
}
