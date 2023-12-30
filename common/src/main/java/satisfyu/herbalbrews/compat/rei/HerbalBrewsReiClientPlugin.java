package satisfyu.herbalbrews.compat.rei;

import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import satisfyu.herbalbrews.compat.rei.category.CauldronCategory;
import satisfyu.herbalbrews.compat.rei.category.TeaKettleCategory;
import satisfyu.herbalbrews.compat.rei.display.CauldronDisplay;
import satisfyu.herbalbrews.compat.rei.display.TeaKettleDisplay;
import satisfyu.herbalbrews.recipe.CauldronRecipe;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.ObjectRegistry;

public class HerbalBrewsReiClientPlugin {

    public static void registerCategories(CategoryRegistry registry) {
        registry.add(new TeaKettleCategory());
        registry.add(new CauldronCategory());

        registry.addWorkstations(TeaKettleCategory.COOKING_CAULDRON_DISPLAY, EntryStacks.of(ObjectRegistry.TEA_KETTLE.get()));
        registry.addWorkstations(TeaKettleCategory.COOKING_CAULDRON_DISPLAY, EntryStacks.of(ObjectRegistry.COPPER_TEA_KETTLE.get()));

        registry.addWorkstations(CauldronDisplay.CAULDRON_DISPLAY, EntryStacks.of(ObjectRegistry.CAULDRON.get()));
    }

    public static void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(TeaKettleRecipe.class, TeaKettleDisplay::new);
        registry.registerFiller(CauldronRecipe.class, CauldronDisplay::new);

    }
}
