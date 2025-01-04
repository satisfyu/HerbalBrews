package net.satisfy.herbalbrews.core.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.herbalbrews.core.items.DrinkBlockItem;
import net.satisfy.herbalbrews.core.registry.EntityTypeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JugBlockEntity extends BlockEntity {
    private final List<ItemStack> drinks = new ArrayList<>();

    public JugBlockEntity(BlockPos pos, BlockState state) {
        super(EntityTypeRegistry.JUG_BLOCK_ENTITY.get(), pos, state);
    }

    public void addDrink(ItemStack drink) {
        if (drinks.size() < 3 && drink.getItem() instanceof DrinkBlockItem) {
            drinks.add(drink.copy());
            setChanged();
            if (level != null) {
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    }

    public List<ItemStack> getDrinks() {
        return drinks;
    }

    public void clearDrinks() {
        drinks.clear();
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        ListTag drinkList = new ListTag();
        for (ItemStack drink : drinks) {
            CompoundTag drinkTag = new CompoundTag();
            drink.save(drinkTag);
            drinkList.add(drinkTag);
        }
        tag.put("Drinks", drinkList);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        drinks.clear();
        if (tag.contains("Drinks", 9)) {
            ListTag drinkList = tag.getList("Drinks", 10);
            for (int i = 0; i < drinkList.size(); i++) {
                CompoundTag drinkTag = drinkList.getCompound(i);
                ItemStack drink = ItemStack.of(drinkTag);
                drinks.add(drink);
            }
        }
    }

    public void applyEffects(LivingEntity user, int durationTicks) {
        for (ItemStack drink : drinks) {
            if (drink.getItem() instanceof DrinkBlockItem) {
                CompoundTag tag = drink.getTag();
                if (tag != null && tag.contains("Effect") && tag.contains("EffectDuration")) {
                    ResourceLocation effectId = new ResourceLocation(tag.getString("Effect"));
                    MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(effectId);
                    if (effect != null) {
                        user.addEffect(new MobEffectInstance(effect, durationTicks));
                    }
                }
            }
        }
    }
}
