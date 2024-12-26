package net.satisfy.herbalbrews.core.compat.jei.category;

import net.satisfy.herbalbrews.core.recipe.TeaKettleRecipe;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;

public class TeaKettleCategory implements IRecipeCategory<TeaKettleRecipe> {
    public static final RecipeType<TeaKettleRecipe> TEA_KETTLE_TYPE = RecipeType.create("herbalbrews", "tea_kettle_brewing", TeaKettleRecipe.class);

    private static final int BACKGROUND_WIDTH = 124;
    private static final int BACKGROUND_HEIGHT = 55;
    private static final int X_OFFSET = 26;
    private static final int Y_OFFSET = 10;
    private static final Vector2i ARROW_POS = new Vector2i(92, 10);
    private static final int ARROW_U = 178;
    private static final int ARROW_V = 16;
    private static final int ARROW_WIDTH = 17;
    private static final int ARROW_HEIGHT = 29;
    private static final int MAX_TIME = 200; // Example cooking time
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable burnIcon;
    private final IDrawableAnimated arrow;
    private final Component title;

    public TeaKettleCategory(IGuiHelper helper) {
        var texture = new net.minecraft.resources.ResourceLocation("herbalbrews", "textures/gui/tea_kettle.png");
        this.background = helper.createDrawable(texture, X_OFFSET, Y_OFFSET, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        this.arrow = helper.drawableBuilder(texture, ARROW_U, ARROW_V, ARROW_WIDTH, ARROW_HEIGHT)
                .buildAnimated(MAX_TIME, IDrawableAnimated.StartDirection.LEFT, false);
        var kettleStack = new ItemStack(net.minecraft.world.item.Items.CAULDRON);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, kettleStack);
        this.burnIcon = helper.createDrawable(texture, 176, 0, 17, 15);
        this.title = Component.translatable("rei.herbalbrews.tea_kettle_category");
    }

    @NotNull
    @Override
    public RecipeType<TeaKettleRecipe> getRecipeType() {
        return TEA_KETTLE_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, TeaKettleRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        int s = ingredients.size();

        assert Minecraft.getInstance().level != null;
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - X_OFFSET, 26 - Y_OFFSET).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));

        int idx = 0;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                if (idx >= s) break;
                int xPos = 30 + col * 18;
                int yPos = 17 + row * 18;
                builder.addSlot(RecipeIngredientRole.INPUT, xPos - X_OFFSET, yPos - Y_OFFSET)
                        .addIngredients(ingredients.get(idx));
                idx++;
            }
        }
    }

    @Override
    public void draw(TeaKettleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, ARROW_POS.x() - X_OFFSET, ARROW_POS.y() - Y_OFFSET);
        burnIcon.draw(guiGraphics, 124 - X_OFFSET, 51 - Y_OFFSET);
    }
}
