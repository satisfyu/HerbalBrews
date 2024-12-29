package net.satisfy.herbalbrews.core.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.satisfy.herbalbrews.core.blocks.TeaKettleBlock;
import net.satisfy.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import net.satisfy.herbalbrews.core.recipe.TeaKettleRecipe;
import net.satisfy.herbalbrews.core.registry.EntityTypeRegistry;
import net.satisfy.herbalbrews.core.registry.RecipeTypeRegistry;
import net.satisfy.herbalbrews.core.registry.TagsRegistry;
import net.satisfy.herbalbrews.core.world.ImplementedInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class TeaKettleBlockEntity extends BlockEntity implements BlockEntityTicker<TeaKettleBlockEntity>, ImplementedInventory, MenuProvider {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(MAX_CAPACITY, ItemStack.EMPTY);
    private static final int MAX_CAPACITY = 7;
    public static final int MAX_COOKING_TIME = 600;
    private int cookingTime;
    public static final int OUTPUT_SLOT = 0;
    private static final int INGREDIENTS_AREA = 5;
    private static final int[] INPUT_SLOTS = new int[]{1, 2, 3, 4, 5};
    private static final int WATER_SLOT = 6;
    private boolean isBeingBurned;
    protected float experience;
    private int waterLevel;

    private final ContainerData delegate;

    public TeaKettleBlockEntity(BlockPos pos, BlockState state) {
        super(EntityTypeRegistry.TEA_KETTLE_BLOCK_ENTITY.get(), pos, state);
        this.delegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> cookingTime;
                    case 1 -> isBeingBurned ? 1 : 0;
                    case 2 -> waterLevel;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> cookingTime = value;
                    case 1 -> isBeingBurned = value != 0;
                    case 2 -> waterLevel = value;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public void dropExperience(ServerLevel world, Vec3 pos) {
        ExperienceOrb.award(world, pos, (int) experience);
    }

    @Override
    public int @NotNull [] getSlotsForFace(Direction side) {
        return INPUT_SLOTS;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        this.cookingTime = nbt.getInt("CookingTime");
        this.isBeingBurned = nbt.getBoolean("IsBeingBurned");
        this.waterLevel = nbt.getInt("WaterLevel");
        this.experience = nbt.getFloat("Experience");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory);
        nbt.putInt("CookingTime", this.cookingTime);
        nbt.putBoolean("IsBeingBurned", this.isBeingBurned);
        nbt.putInt("WaterLevel", this.waterLevel);
        nbt.putFloat("Experience", this.experience);
    }

    public boolean isBeingBurned() {
        if (getLevel() == null)
            throw new NullPointerException("Null world invoked");
        final BlockState belowState = this.getLevel().getBlockState(getBlockPos().below());
        final var optionalList = BuiltInRegistries.BLOCK.getTag(TagsRegistry.ALLOWS_COOKING);
        final var entryList = optionalList.orElse(null);
        if (entryList == null) {
            return false;
        } else return entryList.contains(belowState.getBlock().builtInRegistryHolder());
    }

    private boolean canCraft(TeaKettleRecipe recipe) {
        if (recipe == null || recipe.getResultItem().isEmpty()) {
            return false;
        } else if (this.getItem(OUTPUT_SLOT).isEmpty()) {
            return waterLevel >= recipe.getRequiredWater();
        } else {
            final ItemStack recipeOutput = recipe.getResultItem();
            final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
            final int outputSlotCount = outputSlotStack.getCount();

            if (!ItemStack.isSameItem(outputSlotStack, recipeOutput)) {
                return false;
            } else if (outputSlotCount < this.getMaxStackSize() && outputSlotCount < outputSlotStack.getMaxStackSize()) {
                return waterLevel >= recipe.getRequiredWater();
            } else {
                if (waterLevel < recipe.getRequiredWater()) {
                    return false;
                }
                return outputSlotCount < recipeOutput.getMaxStackSize();
            }
        }
    }

    private void craft(TeaKettleRecipe recipe) {
        if (!canCraft(recipe)) {
            return;
        }

        NonNullList<ItemStack> ingredients = NonNullList.create();
        for (int i = 1; i <= INGREDIENTS_AREA; i++) {
            ingredients.add(getItem(i));
        }

        for (Ingredient ingredient : recipe.getIngredients()) {
            boolean ingredientConsumed = false;
            for (int i = 0; i < ingredients.size(); i++) {
                ItemStack inputStack = ingredients.get(i);
                if (!inputStack.isEmpty() && ingredient.test(inputStack)) {
                    ItemStack remainderStack = getRemainderItem(inputStack);
                    inputStack.shrink(1);
                    if (inputStack.isEmpty()) {
                        setItem(i + 1, remainderStack);
                    }
                    ingredientConsumed = true;
                    ingredients.set(i, inputStack);
                    break;
                }
            }
            if (!ingredientConsumed) {
                return;
            }
        }

        final ItemStack recipeOutput = recipe.assemble();
        final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            setItem(OUTPUT_SLOT, recipeOutput);
        } else if (outputSlotStack.is(recipeOutput.getItem())) {
            outputSlotStack.grow(recipeOutput.getCount());
        }

        waterLevel -= recipe.getRequiredWater();
        if (waterLevel < 0) {
            waterLevel = 0;
        }
    }

    private ItemStack getRemainderItem(ItemStack stack) {
        if (stack.getItem().hasCraftingRemainingItem()) {
            return new ItemStack(Objects.requireNonNull(stack.getItem().getCraftingRemainingItem()));
        }
        return ItemStack.EMPTY;
    }

    public void tick(Level world, BlockPos pos, BlockState state, TeaKettleBlockEntity blockEntity) {
        if (world.isClientSide()) {
            return;
        }
        boolean previousBurned = isBeingBurned;
        this.isBeingBurned = isBeingBurned();
        if (!previousBurned && this.isBeingBurned) {
            world.setBlock(pos, state.setValue(TeaKettleBlock.LIT, true), Block.UPDATE_ALL);
        }
        if (this.isBeingBurned) {
            TeaKettleRecipe recipe = world.getRecipeManager().getRecipeFor(RecipeTypeRegistry.TEA_KETTLE_RECIPE_TYPE.get(), this, world).orElse(null);

            boolean canCraft = canCraft(recipe);
            if (canCraft) {
                this.cookingTime++;
                if (this.cookingTime >= MAX_COOKING_TIME) {
                    this.cookingTime = 0;
                    craft(recipe);
                }
            } else {
                this.cookingTime = 0;
            }
            if (canCraft) {
                world.setBlock(pos, state.setValue(TeaKettleBlock.COOKING, true).setValue(TeaKettleBlock.LIT, true), Block.UPDATE_ALL);
            } else {
                world.setBlock(pos, state.setValue(TeaKettleBlock.COOKING, false).setValue(TeaKettleBlock.LIT, true), Block.UPDATE_ALL);
            }
        }

        if (!getItem(WATER_SLOT).isEmpty()) {
            ItemStack waterItem = getItem(WATER_SLOT);
            if (waterItem.is(TagsRegistry.SMALL_WATER_FILL)) {
                waterLevel = Math.min(waterLevel + 25, 100);
                waterItem.shrink(1);
                setChanged();
            } else if (waterItem.is(TagsRegistry.LARGE_WATER_FILL)) {
                waterLevel = Math.min(waterLevel + 50, 100);
                waterItem.shrink(1);
                setChanged();
            }
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    public int getWaterLevel() {
        return this.waterLevel;
    }

    @Override
    public boolean stillValid(Player player) {
        assert this.level != null;
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double) this.worldPosition.getX() + 0.5, (double) this.worldPosition.getY() + 0.5, (double) this.worldPosition.getZ() + 0.5) <= 64.0;
        }
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new TeaKettleGuiHandler(syncId, inv, this, this.delegate);
    }
}
