package satisfyu.herbalbrews.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

public class TagsRegistry {

    public static final TagKey<Block> ALLOWS_COOKING = TagKey.create(Registries.BLOCK, new HerbalBrewsIdentifier("allows_cooking"));
    public static final TagKey<Item> SHOVEL = TagKey.create(Registries.ITEM, new HerbalBrewsIdentifier("shovel"));
}
