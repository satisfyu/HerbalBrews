package satisfyu.herbalbrews.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.recipe.CauldronRecipe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CauldronDisplay extends BasicDisplay implements SimpleGridMenuDisplay {

    public static final CategoryIdentifier<CauldronDisplay> CAULDRON_DISPLAY = CategoryIdentifier.of(HerbalBrews.MOD_ID, "cauldron_display");

    public CauldronDisplay(CauldronRecipe recipe) {
        this(EntryIngredients.ofIngredients(recipe.getIngredients()), Collections.singletonList(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))), recipe);
    }

    public CauldronDisplay(List<EntryIngredient> input, List<EntryIngredient> output, CompoundTag tag) {
        this(input, output, RecipeManagerContext.getInstance().byId(tag, "location"));
    }

    public CauldronDisplay(List<EntryIngredient> input, List<EntryIngredient> output, Recipe<?> recipe) {
        super(input, output, Optional.ofNullable(recipe).map(Recipe::getId));
    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    public static <R extends CauldronDisplay> Serializer<R> serializer(Serializer.RecipeLessConstructor<R> constructor) {
        return Serializer.ofRecipeLess(constructor);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CAULDRON_DISPLAY;
    }
}
