package net.satisfy.herbalbrews.core.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.core.recipe.CauldronRecipe;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CauldronDisplay extends BasicDisplay {

    public static final CategoryIdentifier<CauldronDisplay> CAULDRON_DISPLAY = CategoryIdentifier.of(HerbalBrews.MOD_ID, "cauldron_display");

    public CauldronDisplay(CauldronRecipe recipe) {
        super(createInputs(), createOutputs(), Optional.of(recipe.getId()));
    }

    private static List<EntryIngredient> createInputs() {
        List<EntryIngredient> inputs = new ArrayList<>();
        inputs.add(EntryIngredients.of(createPotionStack("minecraft:swiftness")));
        inputs.add(EntryIngredients.of(createPotionStack("minecraft:healing")));
        inputs.add(EntryIngredients.of(createPotionStack("minecraft:strength")));
        inputs.add(EntryIngredients.of(new ItemStack(ObjectRegistry.HERBAL_INFUSION.get())));

        return inputs;
    }


    private static List<EntryIngredient> createOutputs() {
        List<EntryIngredient> outputs = new ArrayList<>();
        outputs.add(EntryIngredients.of(new ItemStack(ObjectRegistry.FLASK.get())));
        return outputs;
    }

    private static ItemStack createPotionStack(String potionType) {
        ItemStack potion = new ItemStack(Items.POTION);
        potion.getOrCreateTag().putString("Potion", potionType);
        return potion;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CAULDRON_DISPLAY;
    }
}
