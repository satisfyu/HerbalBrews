package satisfyu.herbalbrews.client.gui.handler;

import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import de.cristelknight.doapi.client.recipebook.handler.AbstractRecipeBookGUIScreenHandler;
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
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import satisfyu.herbalbrews.client.gui.handler.slot.ExtendedSlot;
import satisfyu.herbalbrews.client.recipebook.group.TeaKettleRecipeBookGroup;
import satisfyu.herbalbrews.entities.TeaKettleBlockEntity;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;

import java.util.List;

public class TeaKettleGuiHandler extends AbstractRecipeBookGUIScreenHandler {
    private final ContainerData propertyDelegate;
    private static final int INPUTS = 5;
    private static final int HEATABLE_SLOT_INDEX = 8;

    public TeaKettleGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(9), new SimpleContainerData(2));
    }

    public TeaKettleGuiHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData propertyDelegate) {
        super(ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get(), syncId, INPUTS, playerInventory, inventory, propertyDelegate);

        buildBlockEntityContainer(inventory);
        buildPlayerContainer(playerInventory);

        this.propertyDelegate = propertyDelegate;
        this.addDataSlots(propertyDelegate);
    }

    private void buildBlockEntityContainer(Container inventory) {
        this.addSlot(new ExtendedSlot(inventory, 0, 126, 43, stack -> false));

        for (int row = 0; row < 1; row++) {
            for (int slot = 0; slot < 3; slot++) {
                this.addSlot(new Slot(inventory, 1 + slot + row, 30 + (slot * 18), 17));
            }
        }

        this.addSlot(new ExtendedSlot(inventory, HEATABLE_SLOT_INDEX, 48, 52, this::isItemValidForHeating));
        this.addSlot(new ExtendedSlot(inventory, 7,  87, 17, stack -> stack.is(Items.GLASS_BOTTLE)));
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

    private boolean isItemValidForHeating(ItemStack stack) {
        return isItemValidFuel(stack);
    }

    private boolean isItemValidFuel(ItemStack stack) {
        return AbstractFurnaceBlockEntity.isFuel(stack) || isBucketOfLava(stack) || isBlazeRod(stack);
    }

    private boolean isBucketOfLava(ItemStack stack) {
        return stack.is(Items.LAVA_BUCKET);
    }

    private boolean isBlazeRod(ItemStack stack) {
        return stack.is(Items.BLAZE_ROD);
    }

    public boolean isBeingBurned() {
        return propertyDelegate.get(1) != 0;
    }

    public int getScaledProgress(int arrowWidth) {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = TeaKettleBlockEntity.MAX_COOKING_TIME;
        if (progress == 0) {
            return 0;
        }
        return progress * arrowWidth / totalProgress + 1;
    }

    @Override
    public List<IRecipeBookGroup> getGroups() {
        return TeaKettleRecipeBookGroup.POT_GROUPS;
    }

    @Override
    public boolean hasIngredient(Recipe<?> recipe) {
        if (recipe instanceof TeaKettleRecipe teaKettleRecipe) {
            for (Ingredient ingredient : teaKettleRecipe.getIngredients()) {
                boolean found = false;
                for (Slot slot : this.slots) {
                    if (ingredient.test(slot.getItem())) {
                        found = true;
                    }
                }
                if (!found) {
                    return false;
                }
            }
            ItemStack container = teaKettleRecipe.getContainer();
            for (Slot slot : this.slots) {
                if (container.getItem() == slot.getItem().getItem()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getCraftingSlotCount() {
        return INPUTS;
    }
}
