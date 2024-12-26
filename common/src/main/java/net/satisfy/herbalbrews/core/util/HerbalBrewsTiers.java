package net.satisfy.herbalbrews.core.util;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum HerbalBrewsTiers implements Tier {
    GLASS(2, 2, 5.0f, 2.0f, 12, () -> Ingredient.of(Items.GLASS));

    private final int harvestLevel;
    private final int maxUses;
    private final float miningSpeedMultiplier;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    HerbalBrewsTiers(int harvestLevel, int maxUses, float miningSpeedMultiplier, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
