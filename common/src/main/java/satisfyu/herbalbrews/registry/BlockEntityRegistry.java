package satisfyu.herbalbrews.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.blocks.entity.CauldronBlockEntity;
import satisfyu.herbalbrews.blocks.entity.TeaKettleBlockEntity;
import satisfyu.herbalbrews.blocks.entity.TeaLeafBlockEntity;

import java.util.function.Supplier;

public class BlockEntityRegistry {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<TeaKettleBlockEntity>> TEA_KETTLE_BLOCK_ENTITY = create("tea_kettle", () -> BlockEntityType.Builder.of(TeaKettleBlockEntity::new, ObjectRegistry.TEA_KETTLE.get(), ObjectRegistry.COPPER_TEA_KETTLE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CauldronBlockEntity>> CAULDRON_BLOCK_ENTITY = create("cauldron", () -> BlockEntityType.Builder.of(CauldronBlockEntity::new, ObjectRegistry.CAULDRON.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<TeaLeafBlockEntity>> TEA_LEAF_BLOCK_ENTITY = create("tea_leaf", () -> BlockEntityType.Builder.of(TeaLeafBlockEntity::new, ObjectRegistry.GREEN_TEA_LEAF_BLOCK.get(), ObjectRegistry.MIXED_TEA_LEAF_BLOCK.get(), ObjectRegistry.DRIED_GREEN_TEA_LEAF_BLOCK.get()).build(null));

    private static <T extends BlockEntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(path, type);
    }

    public static void init() {
        HerbalBrews.LOGGER.debug("Registering Mod BlockEntities for " + HerbalBrews.MOD_ID);
        BLOCK_ENTITY_TYPES.register();
    }

}
