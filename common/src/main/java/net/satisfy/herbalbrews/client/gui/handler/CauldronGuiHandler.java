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
import net.minecraft.world.item.Items;
import net.satisfy.herbalbrews.core.registry.ScreenHandlerTypeRegistry;
import org.jetbrains.annotations.NotNull;

public class CauldronGuiHandler extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;

    public CauldronGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(6), new SimpleContainerData(2));
    }

    public CauldronGuiHandler(int syncId, Inventory playerInventory, Container container, ContainerData data) {
        super(ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), syncId);
        this.container = container;
        this.data = data;
        addDataSlots(this.data);
        addSlot(new Slot(container, 0, 79, 51) {
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Items.GLASS_BOTTLE);
            }
        });
        addSlot(new Slot(container, 1, 33, 26) {
            public boolean mayPlace(ItemStack stack) {
                return isIngredient(stack);
            }
        });
        addSlot(new Slot(container, 2, 51, 26) {
            public boolean mayPlace(ItemStack stack) {
                return isIngredient(stack);
            }
        });
        addSlot(new Slot(container, 3, 33, 44) {
            public boolean mayPlace(ItemStack stack) {
                return isIngredient(stack);
            }
        });
        addSlot(new Slot(container, 4, 51, 44) {
            public boolean mayPlace(ItemStack stack) {
                return isIngredient(stack);
            }
        });
        addSlot(new Slot(container, 5, 128, 35) {
            public boolean mayPlace(ItemStack stack) {
                return false;
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

    public int getScaledProgress(int arrowWidth) {
        int progress = data.get(0);
        int total = data.get(1);
        if (progress == 0) {
            return 0;
        }
        return progress * arrowWidth / total + 1;
    }

    private boolean isIngredient(ItemStack stack) {
        return stack.is(Items.SUGAR) || stack.is(Items.NETHER_WART) || stack.is(Items.REDSTONE);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }
}
