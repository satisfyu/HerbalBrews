package net.satisfy.herbalbrews.client.gui.handler;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.satisfy.herbalbrews.core.registry.ObjectRegistry;
import net.satisfy.herbalbrews.core.registry.ScreenHandlerTypeRegistry;
import org.jetbrains.annotations.NotNull;

public class CauldronGuiHandler extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;

    public CauldronGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(5), new SimpleContainerData(2));
    }

    public CauldronGuiHandler(int syncId, Inventory playerInventory, Container container, ContainerData data) {
        super(ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), syncId);
        this.container = container;
        this.data = data;
        addDataSlots(this.data);
        addSlot(new Slot(container, 0, 57, 16) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof PotionItem;
            }
        });
        addSlot(new Slot(container, 1, 79, 22) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof PotionItem;
            }
        });
        addSlot(new Slot(container, 2, 101, 16) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof PotionItem;
            }
        });
        addSlot(new Slot(container, 3, 79, 58) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        addSlot(new Slot(container, 4, 148, 42) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() == ObjectRegistry.HERBAL_INFUSION.get();
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

    public int getScaledProgress(int maxHeight) {
        int progress = data.get(0);
        int total = data.get(1);
        if (progress == 0 || total == 0) {
            return 0;
        }
        return progress * maxHeight / total;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            stack = originalStack.copy();
            if (index < 5) { 
                if (!this.moveItemStackTo(originalStack, 5, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (originalStack.getItem() == ObjectRegistry.HERBAL_INFUSION.get()) {
                    if (!this.moveItemStackTo(originalStack, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }
            } else {
                    for (int i = 0; i < 3; i++) {
                        Slot inputSlot = this.slots.get(i);
                        if (!inputSlot.hasItem()) {
                            if (!this.moveItemStackTo(originalStack, i, i + 1, false)) {
                                return ItemStack.EMPTY;
                            }
                        }
                    }
                }
            }
            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }

    public int getCookingTime() {
        return data.get(0);
    }

    public int getRequiredDuration() {
        return data.get(1);
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }
}
