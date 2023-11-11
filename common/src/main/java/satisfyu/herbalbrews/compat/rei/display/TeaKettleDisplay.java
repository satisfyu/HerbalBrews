package satisfyu.herbalbrews.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.compat.rei.HerbalbrewsREIClientPlugin;
import satisfyu.herbalbrews.compat.rei.category.TeaKettleCategory;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TeaKettleDisplay extends BasicDisplay {

    public static final CategoryIdentifier<TeaKettleDisplay> TEA_KETTLE_DISPLAY = CategoryIdentifier.of(HerbalBrews.MOD_ID, "tea_kettle_display");


    public TeaKettleDisplay(Recipe<Container> recipe) {
        this(EntryIngredients.ofIngredients(HerbalbrewsREIClientPlugin.ingredients(recipe, getContainer(recipe))), Collections.singletonList(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))), Optional.ofNullable(recipe.getId()));
    }

    public TeaKettleDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location) {
        super(inputs, outputs, location);
    }


    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return TEA_KETTLE_DISPLAY;
    }

    public static ItemStack getContainer(Recipe<Container> recipe) {
        if (recipe instanceof TeaKettleRecipe c) {
            return c.getContainer();
        } else return ItemStack.EMPTY;
    }

}