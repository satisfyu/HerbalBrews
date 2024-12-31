package net.satisfy.herbalbrews.core.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.satisfy.herbalbrews.client.gui.handler.TeaKettleGuiHandler;
import net.satisfy.herbalbrews.core.blocks.TeaKettleBlock;
import net.satisfy.herbalbrews.core.recipe.TeaKettleRecipe;
import net.satisfy.herbalbrews.core.registry.EntityTypeRegistry;
import net.satisfy.herbalbrews.core.registry.RecipeTypeRegistry;
import net.satisfy.herbalbrews.core.registry.TagsRegistry;
import net.satisfy.herbalbrews.core.world.ImplementedInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TeaKettleBlockEntity extends BlockEntity implements ImplementedInventory, MenuProvider {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(MAX_CAPACITY, ItemStack.EMPTY);
    private static final int MAX_CAPACITY = 8;
    private static final int MAX_HEAT_LEVEL = 100;
    private static final int HEAT_CONSUMPTION_THRESHOLD = 70;
    private static final int HEAT_PER_ITEM = 35;
    private int cookingTime;
    private int requiredDuration;
    public static final int OUTPUT_SLOT = 0;
    private static final int INGREDIENTS_AREA = 5;
    private static final int WATER_SLOT = 6;
    private static final int HEATING_SLOT = 7;
    private boolean isBeingBurned;
    protected float experience;
    private int waterLevel;
    private int heatLevel;
    private int heatDecreaseCounter = 0;
    private static final int HEAT_DECREASE_INTERVAL = 200;
    private final ContainerData delegate;
    public boolean doEffect;

    public TeaKettleBlockEntity(BlockPos pos, BlockState state) {
        super(EntityTypeRegistry.TEA_KETTLE_BLOCK_ENTITY.get(), pos, state);
        this.isBeingBurned = false;
        this.heatLevel = 0;
        this.delegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> cookingTime;
                    case 1 -> isBeingBurned ? 1 : 0;
                    case 2 -> waterLevel;
                    case 3 -> heatLevel;
                    case 4 -> requiredDuration;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch(index){
                    case 0:
                        cookingTime = value;
                        break;
                    case 1:
                        isBeingBurned = value != 0;
                        heatLevel = isBeingBurned ? 30 : 0;
                        break;
                    case 2:
                        waterLevel = value;
                        break;
                    case 3:
                        heatLevel = value;
                        break;
                    case 4:
                        requiredDuration = value;
                        break;
                }
            }
            @Override
            public int getCount() {
                return 5;
            }
        };
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        this.cookingTime = nbt.getInt("CookingTime");
        this.isBeingBurned = nbt.getBoolean("IsBeingBurned");
        this.waterLevel = nbt.getInt("WaterLevel");
        this.heatLevel = nbt.getInt("HeatLevel");
        this.experience = nbt.getFloat("Experience");
        this.requiredDuration = nbt.getInt("RequiredDuration");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory);
        nbt.putInt("CookingTime", this.cookingTime);
        nbt.putBoolean("IsBeingBurned", this.isBeingBurned);
        nbt.putInt("WaterLevel", this.waterLevel);
        nbt.putInt("HeatLevel", this.heatLevel);
        nbt.putFloat("Experience", this.experience);
        nbt.putInt("RequiredDuration", this.requiredDuration);
    }

    @SuppressWarnings("deprecation")
    public boolean isBeingBurned() {
        if (getLevel() == null) throw new NullPointerException("Null world invoked");
        BlockState belowState = this.getLevel().getBlockState(getBlockPos().below());
        var optionalList = BuiltInRegistries.BLOCK.getTag(TagsRegistry.ALLOWS_COOKING);
        var entryList = optionalList.orElse(null);
        if (entryList == null) return false;
        else return entryList.contains(belowState.getBlock().builtInRegistryHolder());
    }

    private boolean canCraft(TeaKettleRecipe recipe) {
        if (recipe == null || recipe.getResultItem().isEmpty()) return false;
        else if (this.getItem(OUTPUT_SLOT).isEmpty()) {
            return waterLevel >= recipe.getRequiredWater() && heatLevel >= recipe.getRequiredHeat();
        } else {
            ItemStack recipeOutput = recipe.getResultItem();
            ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
            int outputSlotCount = outputSlotStack.getCount();
            if (!ItemStack.isSameItem(outputSlotStack, recipeOutput)) return false;
            else if (outputSlotCount < this.getMaxStackSize() && outputSlotCount < outputSlotStack.getMaxStackSize()) {
                return waterLevel >= recipe.getRequiredWater() && heatLevel >= recipe.getRequiredHeat();
            } else {
                if (waterLevel < recipe.getRequiredWater() || heatLevel < recipe.getRequiredHeat()) return false;
                return outputSlotCount < recipeOutput.getMaxStackSize();
            }
        }
    }

    private void craft(TeaKettleRecipe recipe) {
        if (!canCraft(recipe)) return;
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
                    if (inputStack.isEmpty()) setItem(i + 1, remainderStack);
                    ingredientConsumed = true;
                    ingredients.set(i, inputStack);
                    break;
                }
            }
            if (!ingredientConsumed) return;
        }
        ItemStack recipeOutput = recipe.assemble();
        ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) setItem(OUTPUT_SLOT, recipeOutput);
        else if (outputSlotStack.is(recipeOutput.getItem())) outputSlotStack.grow(recipeOutput.getCount());
        waterLevel -= recipe.getRequiredWater();
        if (waterLevel < 0) waterLevel = 0;
        requiredDuration = recipe.getRequiredDuration();
        cookingTime = 0;
    }

    private ItemStack getRemainderItem(ItemStack stack) {
        if (stack.getItem().hasCraftingRemainingItem()) {
            return new ItemStack(Objects.requireNonNull(stack.getItem().getCraftingRemainingItem()));
        }
        return ItemStack.EMPTY;
    }

    public void consumeHeatItem() {
        ItemStack heatingItem = inventory.get(HEATING_SLOT);
        if (!heatingItem.isEmpty() && heatingItem.is(TagsRegistry.HEAT_ITEMS)) {
            heatingItem.shrink(1);
            inventory.set(HEATING_SLOT, heatingItem);
            doEffect = true;
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        }
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putBoolean("DoEffect", this.doEffect);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void tick(Level world, BlockPos pos, BlockState state) {
        if (world.isClientSide()) return;
        boolean previousBurned = isBeingBurned;
        boolean currentBurned = isBeingBurned();
        this.isBeingBurned = currentBurned;
        if (!previousBurned && currentBurned) {
            world.setBlock(pos, state.setValue(TeaKettleBlock.LIT, true), Block.UPDATE_ALL);
            this.heatLevel = Math.max(this.heatLevel, 30);
        } else if (previousBurned && !currentBurned) {
            world.setBlock(pos, state.setValue(TeaKettleBlock.LIT, false), Block.UPDATE_ALL);
            this.heatLevel = 0;
        }

        ItemStack heatingItem = getItem(HEATING_SLOT);

        if (this.heatLevel < HEAT_CONSUMPTION_THRESHOLD && !heatingItem.isEmpty() && heatingItem.is(TagsRegistry.HEAT_ITEMS)) {
            this.heatLevel = Math.min(this.heatLevel + HEAT_PER_ITEM, MAX_HEAT_LEVEL);
            consumeHeatItem();
            setChanged();
        }

        heatDecreaseCounter++;
        int decreaseInterval = this.isBeingBurned ? HEAT_DECREASE_INTERVAL : HEAT_DECREASE_INTERVAL / 6;
        if (heatDecreaseCounter >= decreaseInterval) {
            heatDecreaseCounter = 0;
            if (this.heatLevel > (this.isBeingBurned ? 30 : 0)) {
                this.heatLevel = Math.max(this.heatLevel - 1, this.isBeingBurned ? 30 : 0);
                setChanged();
            }
        }

        TeaKettleRecipe recipe = world.getRecipeManager().getRecipeFor(RecipeTypeRegistry.TEA_KETTLE_RECIPE_TYPE.get(), this, world).orElse(null);
        boolean canCraft = canCraft(recipe);
        if (canCraft) {
            this.cookingTime++;
            if (this.cookingTime >= requiredDuration) {
                this.cookingTime = 0;
                craft(recipe);
            }
            world.setBlock(pos, state.setValue(TeaKettleBlock.COOKING, true).setValue(TeaKettleBlock.LIT, this.isBeingBurned), Block.UPDATE_ALL);
        } else {
            this.cookingTime = 0;
            world.setBlock(pos, state.setValue(TeaKettleBlock.COOKING, false), Block.UPDATE_ALL);
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
        this.delegate.set(3, this.heatLevel);
        this.delegate.set(4, this.requiredDuration);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    public int getWaterLevel() {
        return this.waterLevel;
    }

    public int getHeatLevel() {
        return this.heatLevel;
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level == null) return false;
        if (this.level.getBlockEntity(this.worldPosition) != this) return false;
        return player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5) <= 64.0;
    }

    public void dropExperience(ServerLevel world, Vec3 pos) {
        ExperienceOrb.award(world, pos, (int) experience);
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
