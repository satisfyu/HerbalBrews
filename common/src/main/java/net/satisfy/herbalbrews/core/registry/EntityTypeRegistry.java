package net.satisfy.herbalbrews.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.core.blocks.entity.*;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;

import java.util.function.Supplier;

public class EntityTypeRegistry {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<TeaKettleBlockEntity>> TEA_KETTLE_BLOCK_ENTITY = registerBlockEntity("tea_kettle", () -> BlockEntityType.Builder.of(TeaKettleBlockEntity::new, ObjectRegistry.TEA_KETTLE.get(), ObjectRegistry.COPPER_TEA_KETTLE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CauldronBlockEntity>> CAULDRON_BLOCK_ENTITY = registerBlockEntity("cauldron", () -> BlockEntityType.Builder.of(CauldronBlockEntity::new, ObjectRegistry.CAULDRON.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<TeaLeafBlockEntity>> TEA_LEAF_BLOCK_ENTITY = registerBlockEntity("tea_leaf", () -> BlockEntityType.Builder.of(TeaLeafBlockEntity::new, ObjectRegistry.GREEN_TEA_LEAF_BLOCK.get(), ObjectRegistry.MIXED_TEA_LEAF_BLOCK.get(), ObjectRegistry.DRIED_GREEN_TEA_LEAF_BLOCK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CompletionistBannerEntity>> HERBALBREWS_BANNER = registerBlockEntity("herbalbrews_banner", () -> BlockEntityType.Builder.of(CompletionistBannerEntity::new, ObjectRegistry.HERBALBREWS_BANNER.get(), ObjectRegistry.HERBALBREWS_WALL_BANNER.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<StoveBlockEntity>> STOVE_BLOCK_ENTITY = registerBlockEntity("stove_block_entity", () -> BlockEntityType.Builder.of(StoveBlockEntity::new, ObjectRegistry.STOVE.get()).build(null));

    private static <T extends BlockEntityType<?>> RegistrySupplier<T> registerBlockEntity(String name, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(new HerbalBrewsIdentifier(name), type);
    }

    public static void init() {
        BLOCK_ENTITY_TYPES.register();
    }
}
