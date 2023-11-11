package satisfyu.herbalbrews.compat.jei.categorys;

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
import net.minecraft.world.item.crafting.Ingredient;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.client.gui.TeaKettleGui;
import satisfyu.herbalbrews.compat.jei.HerbalbrewsJEIPlugin;
import satisfyu.herbalbrews.entities.TeaKettleBlockEntity;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.ObjectRegistry;

public class TeaKettleCategory implements IRecipeCategory<TeaKettleRecipe> {
    public static final RecipeType<TeaKettleRecipe> TEA_KETTLE = RecipeType.create(HerbalBrews.MOD_ID, "tea_brewing", TeaKettleRecipe.class);
    public static final int WIDTH = 124;
    public static final int HEIGHT = 60;
    public static final int WIDTH_OF = 26;
    public static final int HEIGHT_OF = 13;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final Component localizedName;

    public TeaKettleCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TeaKettleGui.BACKGROUND, WIDTH_OF, HEIGHT_OF, WIDTH, HEIGHT);
        this.arrow = helper.drawableBuilder(TeaKettleGui.BACKGROUND, 178, 15, 18, 30)
                .buildAnimated(TeaKettleBlockEntity.MAX_COOKING_TIME, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ObjectRegistry.TEA_KETTLE.get().asItem().getDefaultInstance());
        this.localizedName = Component.translatable("rei.herbalbrews.tea_kettle_category");
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TeaKettleRecipe recipe, IFocusGroup focuses) {

        // Wine input
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        int s = ingredients.size();

        builder.addSlot(RecipeIngredientRole.INPUT, 95 - WIDTH_OF, 55 - HEIGHT_OF).addItemStack(recipe.getContainer());

        for (int row = 0; row < 2; row++) {
            for (int slot = 0; slot < 3; slot++) {
                int current = slot + row + (row * 2);
                if(s - 1 < current) break;
                HerbalbrewsJEIPlugin.addSlot(builder,30 + (slot * 18) - WIDTH_OF, 17 + (row * 18) - HEIGHT_OF, ingredients.get(current));
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124 - WIDTH_OF,  28 - HEIGHT_OF).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }

    @Override
    public void draw(TeaKettleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, TeaKettleGui.ARROW_X - WIDTH_OF, TeaKettleGui.ARROW_Y - HEIGHT_OF);
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
