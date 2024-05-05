package satisfyu.herbalbrews.client.gui.handler;

import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import de.cristelknight.doapi.client.recipebook.handler.AbstractRecipeBookGUIScreenHandler;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.herbalbrews.client.recipebook.group.TeaKettleRecipeBookGroup;
import satisfyu.herbalbrews.blocks.entity.TeaKettleBlockEntity;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.RecipeTypeRegistry;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;

import java.util.List;
import java.util.stream.Stream;

public class TeaKettleGuiHandler extends AbstractRecipeBookGUIScreenHandler {
    public TeaKettleGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(7), new SimpleContainerData(2));
    }

    public TeaKettleGuiHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData propertyDelegate) {
        super(ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get(), syncId, 6, playerInventory, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(Inventory playerInventory, Container inventory) {
        this.addSlot(new FurnaceResultSlot(playerInventory.player, inventory, 0, 124, 26));
        for (int row = 0; row < 2; row++) {
            for (int slot = 0; slot < 3; slot++) {
                this.addSlot(new Slot(inventory, 1 + slot + row + (row * 2), 30 + (slot * 18), 17 + (row * 18)));
            }
        }
    }

    private void buildPlayerContainer(Inventory playerInventory) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public boolean isBeingBurned() {
        return propertyDelegate.get(1) != 0;
    }


    private boolean isItemIngredient(ItemStack stack) {
        return recipeStream().anyMatch(teaKettleRecipe -> teaKettleRecipe.getIngredients().stream().anyMatch(ingredient -> ingredient.test(stack)));
    }

    private Stream<TeaKettleRecipe> recipeStream() {
        return this.world.getRecipeManager().getAllRecipesFor(RecipeTypeRegistry.TEA_KETTLE_RECIPE_TYPE.get()).stream();
    }

    public int getScaledProgress(int arrowWidth) {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = TeaKettleBlockEntity.MAX_COOKING_TIME;
        if (progress == 0) {
            return 0;
        }
        return progress * arrowWidth/ totalProgress + 1;
    }


    @Override
    public List<IRecipeBookGroup> getGroups() {
        return TeaKettleRecipeBookGroup.TEAKETTLE_GROUPS;
    }

    @Override
    public boolean hasIngredient(Recipe<?> recipe) {
        if (recipe instanceof TeaKettleRecipe potRecipe) {
            for (Ingredient ingredient : potRecipe.getIngredients()) {
                boolean found = false;
                for (Slot slot : this.slots) {
                    if (ingredient.test(slot.getItem())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int getCraftingSlotCount() {
        return 6;
    }
}