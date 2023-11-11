package satisfyu.herbalbrews.compat.rei;

import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.herbalbrews.compat.rei.category.TeaKettleCategory;
import satisfyu.herbalbrews.compat.rei.display.TeaKettleDisplay;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;

public class HerbalbrewsREIClientPlugin {
    public static void registerCategories(CategoryRegistry registry) {
        registry.add(new TeaKettleCategory());
        registry.addWorkstations(TeaKettleDisplay.TEA_KETTLE_DISPLAY, EntryStacks.of(ObjectRegistry.TEA_KETTLE.get()));
    }

    public static void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(TeaKettleRecipe.class, TeaKettleDisplay::new);
    }

    public static List<Ingredient> ingredients(Recipe<Container> recipe, ItemStack stack) {
        List<Ingredient> l = new ArrayList<>(recipe.getIngredients());
        l.add(0, Ingredient.of(stack.getItem()));
        return l;
    }
}