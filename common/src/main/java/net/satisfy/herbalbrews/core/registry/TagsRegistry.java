package net.satisfy.herbalbrews.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;

public class TagsRegistry {
    public static final TagKey<Block> ALLOWS_COOKING = TagKey.create(Registries.BLOCK, new HerbalBrewsIdentifier("allows_cooking"));
}
