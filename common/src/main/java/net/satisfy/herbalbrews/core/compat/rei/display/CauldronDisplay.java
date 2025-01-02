package net.satisfy.herbalbrews.core.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;
import net.satisfy.herbalbrews.core.recipe.CauldronRecipe;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public class CauldronDisplay extends BasicDisplay {

    public static final CategoryIdentifier<CauldronDisplay> CAULDRON_DISPLAY = CategoryIdentifier.of(HerbalBrews.MOD_ID, "cauldron_display");

    public CauldronDisplay(CauldronRecipe recipe) {
        this(List.of(
                EntryIngredients.of(createPotionStack("minecraft:swiftness")),
                EntryIngredients.of(createPotionStack("minecraft:healing")),
                EntryIngredients.of(createPotionStack("minecraft:strength"))
        ), List.of(
                EntryIngredients.of(new ItemStack(ObjectRegistry.FLASK.get()))
        ), Optional.ofNullable(recipe.getId()));
    }

    public CauldronDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location) {
        super(inputs, outputs, location);
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
