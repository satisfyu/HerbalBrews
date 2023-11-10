package satisfyu.herbalbrews.compat.rei;

import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import satisfyu.herbalbrews.compat.rei.category.TeaKettleCategory;
import satisfyu.herbalbrews.compat.rei.display.TeaKettleDisplay;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.ObjectRegistry;

public class HerbalbrewsREIClientPlugin {

    public static void registerCategories(CategoryRegistry registry) {
        registry.add(new TeaKettleCategory());

        registry.addWorkstations(TeaKettleCategory.TEA_KETTLE_DISPLAY, EntryStacks.of(ObjectRegistry.TEA_KETTLE.get()));
    }


    public static void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(TeaKettleRecipe.class, TeaKettleDisplay::new);
    }
}
