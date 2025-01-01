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
import net.satisfy.herbalbrews.core.registry.ScreenHandlerTypeRegistry;
import org.jetbrains.annotations.NotNull;

public class CauldronGuiHandler extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;

    public CauldronGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(4), new SimpleContainerData(2));
    }

    public CauldronGuiHandler(int syncId, Inventory playerInventory, Container container, ContainerData data) {
        super(ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), syncId);
        this.container = container;
        this.data = data;
        addDataSlots(this.data);
        addSlot(new Slot(container, 0, 57, 21) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof PotionItem;
            }
        });
        addSlot(new Slot(container, 1, 79, 27) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof PotionItem;
            }
        });
        addSlot(new Slot(container, 2, 101, 21) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof PotionItem;
            }
        });
        addSlot(new Slot(container, 3, 79, 68) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
        // Spieler-Inventar hinzufügen
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 89 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public int getScaledProgress(int maxHeight) {
        int progress = data.get(0);
        int total = data.get(1);
        if (progress == 0) {
            return 0;
        }
        return progress * maxHeight / total;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }
}
