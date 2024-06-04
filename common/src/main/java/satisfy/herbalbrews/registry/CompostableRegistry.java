package satisfy.herbalbrews.registry;

import net.minecraft.world.level.block.ComposterBlock;

public class CompostableRegistry {
    public static void registerCompostable() {
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.TEA_LEAF_CRATE.get().asItem(), 2.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GREEN_TEA_LEAF_BLOCK.get().asItem(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BLACK_TEA_LEAF_BLOCK.get().asItem(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.MIXED_TEA_LEAF_BLOCK.get().asItem(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.OOLONG_TEA_LEAF_BLOCK.get().asItem(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DRIED_OUT_GREEN_TEA_LEAF_BLOCK.get().asItem(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DRIED_GREEN_TEA.get(), 0.2f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GREEN_TEA_LEAF.get(), 0.2f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.YERBA_MATE_LEAF.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.COFFEE_BEANS.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.TEA_BLOSSOM.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.ROOIBOS_LEAF.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WILD_COFFEE_PLANT.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WILD_ROOIBOS_PLANT.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WILD_YERBA_MATE_PLANT.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.HIBISCUS.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.LAVENDER.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.LAVENDER_BLOSSOM.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DRIED_OOLONG_TEA.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DRIED_BLACK_TEA.get(), 0.5f);
    }
}
