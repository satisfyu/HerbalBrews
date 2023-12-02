package satisfyu.herbalbrews.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import satisfyu.herbalbrews.client.gui.handler.CauldronGuiHandler;
import satisfyu.herbalbrews.recipe.CauldronRecipe;
import satisfyu.herbalbrews.recipe.TeaKettleRecipe;
import satisfyu.herbalbrews.registry.BlockEntityRegistry;
import satisfyu.herbalbrews.registry.RecipeTypeRegistry;
import satisfyu.herbalbrews.util.ImplementedInventory;

import static net.minecraft.world.item.ItemStack.isSameItemSameTags;

public class CauldronBlockEntity extends BlockEntity implements ImplementedInventory, BlockEntityTicker<CauldronBlockEntity>, MenuProvider {
    private static final int[] SLOTS_FOR_SIDE = new int[]{2};
    private static final int[] SLOTS_FOR_UP = new int[]{1};
    private static final int[] SLOTS_FOR_DOWN = new int[]{0};
    private NonNullList<ItemStack> inventory;
    public static final int CAPACITY = 5;
    private static final int INGREDIENTS_AREA = 4;
    private static final int OUTPUT_SLOT = 0;
    private int fermentationTime = 0;
    private int totalFermentationTime;
    protected float experience;

    private final ContainerData propertyDelegate = new ContainerData() {

        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> CauldronBlockEntity.this.fermentationTime;
                case 1 -> CauldronBlockEntity.this.totalFermentationTime;
                default -> 0;
            };
        }


        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> CauldronBlockEntity.this.fermentationTime = value;
                case 1 -> CauldronBlockEntity.this.totalFermentationTime = value;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public CauldronBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CAULDRON_BLOCK_ENTITY.get(), pos, state);
        this.inventory = NonNullList.withSize(CAPACITY, ItemStack.EMPTY);
    }

    public void dropExperience(ServerLevel world, Vec3 pos) {
        ExperienceOrb.award(world, pos, (int) experience);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        this.fermentationTime = nbt.getShort("FermentationTime");
        this.experience = nbt.getFloat("Experience");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory);
        nbt.putFloat("Experience", this.experience);
        nbt.putShort("FermentationTime", (short) this.fermentationTime);
    }

    @Override
    public void tick(Level world, BlockPos pos, BlockState state, CauldronBlockEntity blockEntity) {
        if (world.isClientSide) return;
        boolean dirty = false;
        final var recipeType = world.getRecipeManager()
                .getRecipeFor(RecipeTypeRegistry.CAULDRON_RECIPE_TYPE.get(), blockEntity, world)
                .orElse(null);
        RegistryAccess access = level.registryAccess();
        if (canCraft(recipeType, access)) {
            this.fermentationTime++;

            if (this.fermentationTime == this.totalFermentationTime) {
                this.fermentationTime = 0;
                craft(recipeType, access);
                dirty = true;
            }
        } else {
            this.fermentationTime = 0;
        }
        if (dirty) {
            setChanged();
        }

    }

    private boolean canCraft(Recipe<?> recipe, RegistryAccess access) {
        if (recipe == null || recipe.getResultItem(access).isEmpty()) {
            return false;
        }
        if (recipe instanceof CauldronRecipe cauldronRecipe) {
                    if (this.getItem(OUTPUT_SLOT).isEmpty()) {
                return true;
            } else {
                if (this.getItem(OUTPUT_SLOT).isEmpty()) {
                    return true;
                }
                final ItemStack recipeOutput = recipe.getResultItem(access);
                final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
                final int outputSlotCount = outputSlotStack.getCount();
                if (this.getItem(OUTPUT_SLOT).isEmpty()) {
                    return true;
                } else if (!isSameItemSameTags(outputSlotStack, recipeOutput)) {
                    return false;
                } else if (outputSlotCount < this.getMaxStackSize() && outputSlotCount < outputSlotStack.getMaxStackSize()) {
                    return true;
                } else {
                    return outputSlotCount < recipeOutput.getMaxStackSize();
                }
            }
        }
        return false;
    }


    private boolean areInputsEmpty() {
        int emptyStacks = 0;
        for (int i = 0; i < 4; i++) {
            if (this.getItem(i).isEmpty()) emptyStacks++;
        }
        return emptyStacks == 4;
    }


    private void craft(Recipe<?> recipe, RegistryAccess access) {
        if (!canCraft(recipe, access)) {
            return;
        }

        final ItemStack recipeOutput = recipe.getResultItem(access);
        final ItemStack outputSlotStack = getItem(OUTPUT_SLOT);

        if (outputSlotStack.isEmpty()) {
            setItem(OUTPUT_SLOT, recipeOutput.copy());
        } else if (outputSlotStack.is(recipeOutput.getItem())) {
            outputSlotStack.grow(recipeOutput.getCount());
        }

        final NonNullList<Ingredient> ingredients = recipe.getIngredients();
        boolean[] slotUsed = new boolean[INGREDIENTS_AREA];

        for (int i = 0; i < Math.min(INGREDIENTS_AREA, ingredients.size()); i++) {
            Ingredient ingredient = ingredients.get(i);
            ItemStack bestSlot = getItem(i);

            if (ingredient.test(bestSlot) && !slotUsed[i]) {
                slotUsed[i] = true;
                ItemStack remainderStack = getRemainderItem(bestSlot);
                bestSlot.shrink(1);

                if (!remainderStack.isEmpty()) {
                    setItem(i, remainderStack);
                }
            } else {
                for (int j = 0; j < INGREDIENTS_AREA; j++) {
                    ItemStack stack = getItem(j);

                    if (ingredient.test(stack) && !slotUsed[j]) {
                        slotUsed[j] = true;
                        ItemStack remainderStack = getRemainderItem(stack);
                        stack.shrink(1);

                        if (!remainderStack.isEmpty()) {
                            setItem(j, remainderStack);
                        }
                    }
                }
            }
        }
    }


    private ItemStack getRemainderItem(ItemStack stack) {
        if (stack.getItem().hasCraftingRemainingItem()) {
            return new ItemStack(stack.getItem().getCraftingRemainingItem());
        }
        return ItemStack.EMPTY;
    }


    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if(side.equals(Direction.UP)){
            return SLOTS_FOR_UP;
        } else if (side.equals(Direction.DOWN)){
            return SLOTS_FOR_DOWN;
        } else return SLOTS_FOR_SIDE;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        final ItemStack stackInSlot = this.inventory.get(slot);
        boolean dirty = !stack.isEmpty() && ItemStack.isSameItem(stack, stackInSlot) && ItemStack.matches(stack, stackInSlot);
        this.inventory.set(slot, stack);

        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (slot >= 1 && slot <= 5) {
            if (!dirty) {
                this.totalFermentationTime = 50;
                this.fermentationTime = 0;
                setChanged();
            }
        }
    }


    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5, (double)this.worldPosition.getY() + 0.5, (double)this.worldPosition.getZ() + 0.5) <= 64.0;
        }
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new CauldronGuiHandler(syncId, inv, this, this.propertyDelegate);
    }
}