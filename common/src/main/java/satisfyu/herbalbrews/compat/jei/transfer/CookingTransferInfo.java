package satisfyu.herbalbrews.compat.jei.transfer;



import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import satisfyu.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import satisfyu.herbalbrews.compat.jei.categorys.TeaKettleCategory;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookingTransferInfo implements IRecipeTransferInfo<TeaKettleGuiHandler, TeaKettleRecipe> {
    @Override
    public Class<? extends TeaKettleGuiHandler> getContainerClass() {
        return TeaKettleGuiHandler.class;
    }

    @Override
    public Optional<MenuType<TeaKettleGuiHandler>> getMenuType() {
        return Optional.of(ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get());
    }

    @Override
    public RecipeType<TeaKettleRecipe> getRecipeType() {
        return TeaKettleCategory.TEA_KETTLE;
    }

    @Override
    public boolean canHandle(TeaKettleGuiHandler container, TeaKettleRecipe recipe) {
        return true;
    }

    @Override
    public List<Slot> getRecipeSlots(TeaKettleGuiHandler container, TeaKettleRecipe recipe) {
        List<Slot> slots = new ArrayList<>();
        slots.add(container.getSlot(7));
        for(int i = 1; i <= recipe.getIngredients().size() && i < 7; i++){
            slots.add(container.getSlot(i));
        }
        return slots;
    }

    @Override
    public List<Slot> getInventorySlots(TeaKettleGuiHandler container, TeaKettleRecipe recipe) {
        List<Slot> slots = new ArrayList<>();
        for (int i = 8; i < 8 + 36; i++) {
            Slot slot = container.getSlot(i);
            slots.add(slot);
        }
        return slots;
    }
}
