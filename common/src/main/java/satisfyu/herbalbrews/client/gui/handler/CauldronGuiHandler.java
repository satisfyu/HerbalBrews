package satisfyu.herbalbrews.client.gui.handler;

import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import de.cristelknight.doapi.client.recipebook.handler.AbstractRecipeBookGUIScreenHandler;
import de.cristelknight.doapi.common.world.slot.ExtendedSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.herbalbrews.client.gui.handler.slot.OutputSlot;
import satisfyu.herbalbrews.client.recipebook.group.CauldronRecipeBookGroup;
import satisfyu.herbalbrews.recipe.CauldronRecipe;
import satisfyu.herbalbrews.registry.RecipeTypeRegistry;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;

import java.util.List;

public class CauldronGuiHandler extends AbstractRecipeBookGUIScreenHandler {

    public CauldronGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(6), new SimpleContainerData(2));
    }
    public CauldronGuiHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData propertyDelegate) {
        super(ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), syncId, 5, playerInventory, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }


    private void buildBlockEntityContainer(Inventory playerInventory, Container inventory) {
        this.addSlot(new ExtendedSlot(inventory, 0, 79, 51, stack -> stack.is(Items.GLASS_BOTTLE)));
        this.addSlot(new ExtendedSlot(inventory, 1, 33, 26, this::isIngredient));
        this.addSlot(new ExtendedSlot(inventory, 2, 51, 26, this::isIngredient));
        this.addSlot(new ExtendedSlot(inventory, 3, 33, 44, this::isIngredient));
        this.addSlot(new ExtendedSlot(inventory, 4, 51, 44, this::isIngredient));
        this.addSlot(new OutputSlot(playerInventory.player, inventory, 5, 128,  35));
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

    private boolean isIngredient(ItemStack stack) {
        return this.world.getRecipeManager().getAllRecipesFor(RecipeTypeRegistry.CAULDRON_RECIPE_TYPE.get()).stream().anyMatch(recipe -> recipe.getIngredients().stream().anyMatch(x -> x.test(stack)));
    }

    public int getScaledProgress(int arrowWidth) {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = this.propertyDelegate.get(1);
        if (progress == 0) {
            return 0;
        }
        return progress * arrowWidth/ totalProgress + 1;
    }

    @Override
    public List<IRecipeBookGroup> getGroups() {
        return CauldronRecipeBookGroup.CAULDRON_GROUPS;
    }

    @Override
    public boolean hasIngredient(Recipe<?> recipe) {
        if (recipe instanceof CauldronRecipe cauldronRecipe) {
            for (Ingredient ingredient : cauldronRecipe.getIngredients()) {
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
            for (Slot slot : this.slots) {
                if (slot.getItem().getItem() == Items.GLASS_BOTTLE.asItem()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getCraftingSlotCount() {
        return 5;
    }
}
