package net.satisfy.herbalbrews.core.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.satisfy.herbalbrews.core.recipe.CauldronRecipe;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;
import org.jetbrains.annotations.NotNull;

public class CauldronCategory implements IRecipeCategory<CauldronRecipe> {
    public static final RecipeType<CauldronRecipe> CAULDRON_TYPE = RecipeType.create("herbalbrews", "cauldron_brewing", CauldronRecipe.class);
    private static final int BACKGROUND_WIDTH = 147;
    private static final int BACKGROUND_HEIGHT = 64;
    private static final int X_OFFSET = 26;
    private static final int Y_OFFSET = 13;
    private final IDrawable background;
    private final IDrawable icon;
    private final Component title;

    public CauldronCategory(IGuiHelper helper) {
        var texture = new net.minecraft.resources.ResourceLocation("herbalbrews", "textures/gui/cauldron.png");
        this.background = helper.createDrawable(texture, X_OFFSET, Y_OFFSET, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        var cauldronStack = new ItemStack(ObjectRegistry.CAULDRON.get());
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, cauldronStack);
        this.title = ObjectRegistry.TEA_KETTLE.get().getName();
    }

    @NotNull
    @Override
    public RecipeType<CauldronRecipe> getRecipeType() {
        return CAULDRON_TYPE;
    }

    @NotNull
    @Override
    public Component getTitle() {
        return title;
    }

    @NotNull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CauldronRecipe recipe, IFocusGroup focuses) {
        ItemStack potion1 = new ItemStack(Items.POTION);
        potion1.getOrCreateTag().putString("Potion", "minecraft:swiftness");

        ItemStack potion2 = new ItemStack(Items.POTION);
        potion2.getOrCreateTag().putString("Potion", "minecraft:healing");

        ItemStack potion3 = new ItemStack(Items.POTION);
        potion3.getOrCreateTag().putString("Potion", "minecraft:strength");

        builder.addSlot(RecipeIngredientRole.INPUT, 79 - X_OFFSET, 22 - Y_OFFSET)
                .addItemStack(potion1);
        builder.addSlot(RecipeIngredientRole.INPUT, 57 - X_OFFSET, 16 - Y_OFFSET)
                .addItemStack(potion2);
        builder.addSlot(RecipeIngredientRole.INPUT, 101 - X_OFFSET, 16 - Y_OFFSET)
                .addItemStack(potion3);

        ItemStack outputFlask = new ItemStack(ObjectRegistry.FLASK.get());
        outputFlask.getOrCreateTag().putString("Effect", "minecraft:regeneration");
        builder.addSlot(RecipeIngredientRole.OUTPUT, 79 - X_OFFSET, 58 - Y_OFFSET)
                .addItemStack(outputFlask);

        ItemStack herbalInfusion = new ItemStack(ObjectRegistry.HERBAL_INFUSION.get());
        builder.addSlot(RecipeIngredientRole.INPUT, 148 - X_OFFSET, 42 - Y_OFFSET)
                .addItemStack(herbalInfusion);
    }

}
