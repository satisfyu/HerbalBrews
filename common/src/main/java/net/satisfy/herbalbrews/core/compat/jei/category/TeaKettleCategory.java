package net.satisfy.herbalbrews.core.compat.jei.category;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.satisfy.herbalbrews.core.recipe.TeaKettleRecipe;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;
import net.satisfy.herbalbrews.core.registry.TagsRegistry;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class TeaKettleCategory implements IRecipeCategory<TeaKettleRecipe> {
    public static final RecipeType<TeaKettleRecipe> TEA_KETTLE_TYPE = RecipeType.create("herbalbrews", "tea_kettle_brewing", TeaKettleRecipe.class);

    private static final int BACKGROUND_WIDTH = 160;
    private static final int BACKGROUND_HEIGHT = 70;
    private static final int X_OFFSET = 10;
    private static final int Y_OFFSET = 10;
    private static final Vector2i ARROW_POS = new Vector2i(54, 22);
    private static final int ARROW_U = 176;
    private static final int ARROW_V = 14;
    private static final int ARROW_WIDTH = 24;
    private static final int ARROW_HEIGHT = 17;
    private static final int MAX_TIME = 200;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable burnIcon;
    private final IDrawableAnimated arrow;
    private final Component title;

    private final ResourceLocation texture = new ResourceLocation("herbalbrews", "textures/gui/tea_kettle.png");

    public TeaKettleCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(texture, X_OFFSET, Y_OFFSET, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        this.arrow = helper.drawableBuilder(texture, ARROW_U, ARROW_V, ARROW_WIDTH, ARROW_HEIGHT)
                .buildAnimated(MAX_TIME, IDrawableAnimated.StartDirection.LEFT, false);
        var kettleStack = new ItemStack(ObjectRegistry.TEA_KETTLE.get());
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, kettleStack);
        this.burnIcon = helper.createDrawable(texture, 176, 0, 14, 14);
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
        int s = Math.min(ingredients.size(), 4);

        assert Minecraft.getInstance().level != null;
        builder.addSlot(RecipeIngredientRole.OUTPUT, 91 - X_OFFSET, 22 - Y_OFFSET)
                .addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));

        ItemStack waterBottle = new ItemStack(Items.POTION);
        waterBottle.getOrCreateTag().putString("Potion", "minecraft:water");

        builder.addSlot(RecipeIngredientRole.INPUT, 118 - X_OFFSET, 43 - Y_OFFSET)
                .addIngredients(Ingredient.of(
                        new ItemStack(Items.WATER_BUCKET),
                        waterBottle
                ));

        int idx = 0;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                if (idx >= s) break;

                Ingredient ingredient = ingredients.get(idx);
                Ingredient filteredIngredient = filterOutTag(ingredient);

                if (!filteredIngredient.isEmpty()) {
                    int xPos = 13 + col * 18;
                    int yPos = 12 + row * 18;
                    builder.addSlot(RecipeIngredientRole.INPUT, xPos - X_OFFSET, yPos - Y_OFFSET)
                            .addIngredients(filteredIngredient);
                }

                idx++;
            }
        }

        if (containsTagIngredient(ingredients)) {
            builder.addSlot(RecipeIngredientRole.INPUT, 31 - X_OFFSET, 52 - Y_OFFSET)
                    .addIngredients(Ingredient.of(ItemTags.bind(TagsRegistry.CONTAINER_ITEMS.location().toString())));
        }

        if (containsTagIngredient(ingredients)) {
            builder.addSlot(RecipeIngredientRole.INPUT, 95 - X_OFFSET, 58 - Y_OFFSET)
                    .addIngredients(Ingredient.of(ItemTags.bind(TagsRegistry.HEAT_ITEMS.location().toString())));
        }
    }

    private Ingredient filterOutTag(Ingredient ingredient) {
        List<ItemStack> filteredStacks = new ArrayList<>();
        for (ItemStack stack : ingredient.getItems()) {
            if (!stack.is(TagsRegistry.CONTAINER_ITEMS)) {
                filteredStacks.add(stack);
            }
        }
        return filteredStacks.isEmpty() ? Ingredient.EMPTY : Ingredient.of(filteredStacks.toArray(new ItemStack[0]));
    }

    private boolean containsTagIngredient(NonNullList<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            for (ItemStack stack : ingredient.getItems()) {
                if (stack.is(TagsRegistry.CONTAINER_ITEMS)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void draw(TeaKettleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, ARROW_POS.x() - X_OFFSET, ARROW_POS.y() - Y_OFFSET);
        burnIcon.draw(guiGraphics, 152 - X_OFFSET, 62 - Y_OFFSET);

        int requiredWater = recipe.getRequiredWater();
        int fluidFillHeight = Math.min((requiredWater * 43) / 100, 43);
        guiGraphics.blit(texture, 141 - X_OFFSET, 16 - Y_OFFSET + (43 - fluidFillHeight), 183, 31 + (43 - fluidFillHeight), 8, fluidFillHeight);

        int requiredHeat = recipe.getRequiredHeat();
        int heatFillHeight = Math.min((requiredHeat * 43) / 100, 43);
        guiGraphics.blit(texture, 156 - X_OFFSET, 16 - Y_OFFSET + (43 - heatFillHeight), 176, 31 + (43 - heatFillHeight), 5, heatFillHeight);

        Rectangle requiredFluidPos = new Rectangle(141 - X_OFFSET, 16 - Y_OFFSET, 8, 43);
        Rectangle requiredHeatPos = new Rectangle(156 - X_OFFSET, 16 - Y_OFFSET, 5, 43);
    }
}
