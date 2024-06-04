package satisfy.herbalbrews.compat.jei.category;

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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import satisfy.herbalbrews.HerbalBrews;
import satisfy.herbalbrews.client.gui.TeaKettleGui;
import satisfy.herbalbrews.compat.jei.HerbalBrewsJEIPlugin;
import satisfy.herbalbrews.blocks.entity.TeaKettleBlockEntity;
import satisfy.herbalbrews.recipe.TeaKettleRecipe;
import satisfy.herbalbrews.registry.ObjectRegistry;

public class TeaKettleCategory implements IRecipeCategory<TeaKettleRecipe> {
    public static final RecipeType<TeaKettleRecipe> TEA_KETTLE = RecipeType.create(HerbalBrews.MOD_ID, "cooking", TeaKettleRecipe.class);
    public static final int WIDTH = 124;
    public static final int HEIGHT = 55;
    public static final int WIDTH_OF = 26;
    public static final int HEIGHT_OF = 10;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable burnicon;
    private final IDrawableAnimated arrow;
    private final Component localizedName;

    public TeaKettleCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TeaKettleGui.BACKGROUND, WIDTH_OF, HEIGHT_OF, WIDTH, HEIGHT);
        this.arrow = helper.drawableBuilder(TeaKettleGui.BACKGROUND, 178, 16, 17, 29)
                .buildAnimated(TeaKettleBlockEntity.MAX_COOKING_TIME, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ObjectRegistry.TEA_KETTLE.get().asItem().getDefaultInstance());
        this.burnicon = helper.createDrawable(TeaKettleGui.BACKGROUND, 176, 0, 17, 15);
        this.localizedName = Component.translatable("rei.herbalbrews.tea_kettle_category");
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TeaKettleRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        int s = ingredients.size();

        for (int row = 0; row < 2; row++) {
            for (int slot = 0; slot < 3; slot++) {
                int current = slot + row + (row * 2);
                if(s - 1 < current) break;
                HerbalBrewsJEIPlugin.addSlot(builder,30 + (slot * 18) - WIDTH_OF, 17 + (row * 18) - HEIGHT_OF, ingredients.get(current));
            }
        }

        // Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - WIDTH_OF,  26 - HEIGHT_OF).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(TeaKettleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, TeaKettleGui.ARROW_X - WIDTH_OF, TeaKettleGui.ARROW_Y - HEIGHT_OF);
        burnicon.draw(guiGraphics, 124 - WIDTH_OF, 51 - HEIGHT_OF);
    }

    @Override
    public RecipeType<TeaKettleRecipe> getRecipeType() {
        return TEA_KETTLE;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }
}
