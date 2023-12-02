package satisfyu.herbalbrews.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.entities.RedWolfEntity;
import satisfyu.herbalbrews.entities.WanderingGardenerEntity;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;


import java.util.function.Supplier;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.ENTITY_TYPE);


    public static final RegistrySupplier<EntityType<WanderingGardenerEntity>> WANDERING_GARDENER = create("wandering_gardener",
            () -> EntityType.Builder.of(WanderingGardenerEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .clientTrackingRange(10)
                    .build(new HerbalBrewsIdentifier("wandering_baker").toString()));

    public static final RegistrySupplier<EntityType<RedWolfEntity>> RED_WOLF = create("red_wolf",
            () -> EntityType.Builder.of(RedWolfEntity::new, MobCategory.CREATURE)
                    .sized(0.2f, 1.5f)
                    .clientTrackingRange(10)
                    .build(new HerbalBrewsIdentifier("red_wolf").toString()));



    public static <T extends EntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(new HerbalBrewsIdentifier(path), type);
    }

    public static void init() {
        HerbalBrews.LOGGER.debug("Registering Entities for " + HerbalBrews.MOD_ID);
        ENTITY_TYPES.register();
        EntityAttributeRegistry.register(WANDERING_GARDENER, WanderingGardenerEntity::createMobAttributes);
        EntityAttributeRegistry.register(RED_WOLF, RedWolfEntity::createMobAttributes);

    }
}
