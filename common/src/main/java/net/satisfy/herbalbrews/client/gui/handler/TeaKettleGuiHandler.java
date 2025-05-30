package net.satisfy.herbalbrews.client.gui.handler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.satisfy.herbalbrews.core.registry.ScreenHandlerTypeRegistry;
import net.satisfy.herbalbrews.core.registry.TagsRegistry;
import org.jetbrains.annotations.NotNull;

public class TeaKettleGuiHandler extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData propertyDelegate;

    public TeaKettleGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(8), new SimpleContainerData(5));
    }

    public TeaKettleGuiHandler(int syncId, Inventory playerInventory, Container container, ContainerData propertyDelegate) {
        super(ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get(), syncId);
        this.container = container;
        this.propertyDelegate = propertyDelegate;
        addDataSlots(this.propertyDelegate);
        addSlot(new FurnaceResultSlot(playerInventory.player, container, 0, 91, 22));
        addSlot(new Slot(container, 1, 13, 12));
        addSlot(new Slot(container, 2, 31, 12));
        addSlot(new Slot(container, 3, 13, 30));
        addSlot(new Slot(container, 4, 31, 30));
        addSlot(new Slot(container, 5, 31, 52) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(TagsRegistry.CONTAINER_ITEMS);
            }
        });
        addSlot(new Slot(container, 6, 118, 43) {

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(TagsRegistry.SMALL_WATER_FILL) || stack.is(TagsRegistry.LARGE_WATER_FILL);
            }
        });
        addSlot(new Slot(container, 7, 95, 58) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(TagsRegistry.HEAT_ITEMS);
            }
        });
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public boolean isBeingBurned() {
        return propertyDelegate.get(1) != 0;
    }

    public int getScaledProgress(int arrowWidth) {
        int progress = propertyDelegate.get(0);
        int total = propertyDelegate.get(4);
        if (total <= 0) {
            return 0;
        }
        return progress * arrowWidth / total;
    }

    public int getWaterLevel() {
        return propertyDelegate.get(2);
    }

    public int getHeatLevel() {
        return propertyDelegate.get(3);
    }

    public int getCookingTime() {
        return propertyDelegate.get(0);
    }

    public int getRequiredDuration() {
        return propertyDelegate.get(4);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack item = slot.getItem();
        ItemStack copy = item.copy();

        if (index < 8) {
            if (!this.moveItemStackTo(item, 8, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(item, copy);
        } else {
            if (index < this.slots.size()) {
                if (item.is(TagsRegistry.CONTAINER_ITEMS)) {
                    if (!this.moveItemStackTo(item, 5, 6, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (item.is(TagsRegistry.HEAT_ITEMS)) {
                    if (!this.moveItemStackTo(item, 7, 8, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (item.is(TagsRegistry.SMALL_WATER_FILL) || item.is(TagsRegistry.LARGE_WATER_FILL)) {
                    if (!this.moveItemStackTo(item, 6, 7, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(item, 0, 5, false)) {
                    return ItemStack.EMPTY;
                }
            }
        }

        if (item.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        slot.onTake(player, item);
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }
}
