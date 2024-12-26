package net.satisfy.herbalbrews.core.compat.jei.category;

import net.satisfy.herbalbrews.core.recipe.CauldronRecipe;
import org.joml.Vector2i;
import org.jetbrains.annotations.NotNull;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;

public class CauldronCategory implements IRecipeCategory<CauldronRecipe> {
    public static final RecipeType<CauldronRecipe> CAULDRON_TYPE = RecipeType.create("herbalbrews", "cauldron_brewing", CauldronRecipe.class);

    private static final int BACKGROUND_WIDTH = 124;
    private static final int BACKGROUND_HEIGHT = 60;
    private static final int X_OFFSET = 26;
    private static final int Y_OFFSET = 13;
    private static final int ARROW_U = 177;
    private static final int ARROW_V = 17;
    private static final int ARROW_WIDTH = 23;
    private static final int ARROW_HEIGHT = 10;
    private static final Vector2i ARROW_POS = new Vector2i(94, 37);
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final Component title;

    public CauldronCategory(IGuiHelper helper) {
        var texture = new net.minecraft.resources.ResourceLocation("herbalbrews", "textures/gui/cauldron.png");
        this.background = helper.createDrawable(texture, X_OFFSET, Y_OFFSET, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        this.arrow = helper.drawableBuilder(texture, ARROW_U, ARROW_V, ARROW_WIDTH, ARROW_HEIGHT)
                .buildAnimated(50, IDrawableAnimated.StartDirection.LEFT, false);
        var cauldronStack = new ItemStack(Items.CAULDRON);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, cauldronStack);
        this.title = Component.translatable("rei.herbalbrews.cauldron_category");
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
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        int s = ingredients.size();

        builder.addSlot(RecipeIngredientRole.INPUT, 79 - X_OFFSET, 51 - Y_OFFSET)
                .addItemStack(new ItemStack(Items.GLASS_BOTTLE));

        if (s > 0) builder.addSlot(RecipeIngredientRole.INPUT, 33 - X_OFFSET, 26 - Y_OFFSET).addIngredients(ingredients.get(0));
        if (s > 1) builder.addSlot(RecipeIngredientRole.INPUT, 51 - X_OFFSET, 26 - Y_OFFSET).addIngredients(ingredients.get(1));
        if (s > 2) builder.addSlot(RecipeIngredientRole.INPUT, 33 - X_OFFSET, 44 - Y_OFFSET).addIngredients(ingredients.get(2));
        if (s > 3) builder.addSlot(RecipeIngredientRole.INPUT, 51 - X_OFFSET, 44 - Y_OFFSET).addIngredients(ingredients.get(3));

        assert Minecraft.getInstance().level != null;
        builder.addSlot(RecipeIngredientRole.OUTPUT, 128 - X_OFFSET, 35 - Y_OFFSET)
                .addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }

    @Override
    public void draw(CauldronRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, ARROW_POS.x() - X_OFFSET, ARROW_POS.y() - Y_OFFSET);
    }
}
