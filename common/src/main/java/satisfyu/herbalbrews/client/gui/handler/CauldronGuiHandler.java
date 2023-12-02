package satisfyu.herbalbrews.client.gui.handler;

import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import de.cristelknight.doapi.client.recipebook.handler.AbstractRecipeBookGUIScreenHandler;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.herbalbrews.client.gui.handler.slot.OutputSlot;
import satisfyu.herbalbrews.client.recipebook.group.CauldronRecipeBookGroup;
import satisfyu.herbalbrews.recipe.CauldronRecipe;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;

import java.util.List;

public class CauldronGuiHandler extends AbstractRecipeBookGUIScreenHandler {

    public CauldronGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(5), new SimpleContainerData(2));
    }
    public CauldronGuiHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData propertyDelegate) {
        super(ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), syncId, 4, playerInventory, inventory, propertyDelegate);

        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(Inventory playerInventory, Container inventory) {
        this.addSlot(new OutputSlot(playerInventory.player, inventory, 0, 128,  35));
        this.addSlot(new Slot(inventory, 1, 55, 26));
        this.addSlot(new Slot(inventory, 2, 55, 44));
        this.addSlot(new Slot(inventory, 3, 37, 26));
        this.addSlot(new Slot(inventory, 4, 37, 44));
    }

    private void buildPlayerContainer(Container playerInventory) {
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

    public int getBrewingXProgress() {
        int progress = this.propertyDelegate.get(0);
        int totalProgress = this.propertyDelegate.get(1);
        if (totalProgress == 0 || progress == 0) {
            return 0;
        }
        return progress * 22 / totalProgress + 1;
    }

    public int getBrewingYProgress() {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = this.propertyDelegate.get(1);
        if (totalProgress == 0 || progress == 0) {
            return 0;
        }
        return progress * 20 / totalProgress + 1;
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
            return true;
        }
        return false;
    }

    @Override
    public int getCraftingSlotCount() {
        return 4;
    }
}