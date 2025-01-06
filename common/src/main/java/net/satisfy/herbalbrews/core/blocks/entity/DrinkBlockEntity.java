package net.satisfy.herbalbrews.core.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.herbalbrews.core.registry.EntityTypeRegistry;

public class DrinkBlockEntity extends BlockEntity {
    
    private CompoundTag storedNbt;

    public DrinkBlockEntity(BlockPos pos, BlockState state) {
        super(EntityTypeRegistry.DRINK_BLOCK_ENTITY.get(), pos, state);
    }

    public void setStoredNbt(CompoundTag tag) {
        this.storedNbt = tag;
    }

    public CompoundTag getStoredNbt() {
        return storedNbt;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("StoredNbt")) {
            storedNbt = tag.getCompound("StoredNbt");
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (storedNbt != null) {
            tag.put("StoredNbt", storedNbt);
        }
    }
}
