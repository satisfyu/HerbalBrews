package net.satisfy.herbalbrews.compat.jei.category;

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
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.client.gui.CauldronGui;
import net.satisfy.herbalbrews.compat.jei.HerbalBrewsJEIPlugin;
import net.satisfy.herbalbrews.recipe.CauldronRecipe;
import net.satisfy.herbalbrews.registry.ObjectRegistry;

public class CauldronCategory implements IRecipeCategory<CauldronRecipe> {
    public static final RecipeType<CauldronRecipe> CAULDRON = RecipeType.create(HerbalBrews.MOD_ID, "cauldron_brewing", CauldronRecipe.class);
    public static final int WIDTH = 124;
    public static final int HEIGHT = 60;
    public static final int WIDTH_OF = 26;
    public static final int HEIGHT_OF = 13;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final Component localizedName;

    public CauldronCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(CauldronGui.BACKGROUND, WIDTH_OF, HEIGHT_OF, WIDTH, HEIGHT);
        this.arrow = helper.drawableBuilder(CauldronGui.BACKGROUND, 177, 17, 23, 10)
                .buildAnimated(50, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ObjectRegistry.CAULDRON.get().asItem().getDefaultInstance());
        this.localizedName = Component.translatable("rei.herbalbrews.cauldron_category");
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CauldronRecipe recipe, IFocusGroup focuses) {
        // Wine input
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        int s = ingredients.size();

        builder.addSlot(RecipeIngredientRole.INPUT, 79 - WIDTH_OF, 51 - HEIGHT_OF).addItemStack(Items.GLASS_BOTTLE.getDefaultInstance());
        if(s > 0) HerbalBrewsJEIPlugin.addSlot(builder, 33 - WIDTH_OF, 26 - HEIGHT_OF, ingredients.get(0));
        if(s > 1) HerbalBrewsJEIPlugin.addSlot(builder, 51 - WIDTH_OF, 26 - HEIGHT_OF, ingredients.get(1));
        if(s > 2) HerbalBrewsJEIPlugin.addSlot(builder, 33 - WIDTH_OF, 44 - HEIGHT_OF, ingredients.get(2));
        if(s > 3) HerbalBrewsJEIPlugin.addSlot(builder, 51 - WIDTH_OF, 44 - HEIGHT_OF, ingredients.get(3));

        // Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 128 - WIDTH_OF,  35 - HEIGHT_OF).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }

    @Override
    public void draw(CauldronRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, CauldronGui.ARROW_X - WIDTH_OF, CauldronGui.ARROW_Y - HEIGHT_OF);
    }

    @Override
    public RecipeType<CauldronRecipe> getRecipeType() {
        return CAULDRON;
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
