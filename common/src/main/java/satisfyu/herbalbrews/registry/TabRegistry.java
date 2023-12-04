package satisfyu.herbalbrews.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import satisfyu.herbalbrews.HerbalBrews;

public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> HERBALBREWS_TABS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> HERBALBREWS_TAB = HERBALBREWS_TABS.register("herbalbrews", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
            .icon(() -> new ItemStack(ObjectRegistry.TEA_KETTLE.get()))
            .title(Component.translatable("creative_tab.herbalbrews"))
            .displayItems((parameters, out) -> {
                out.accept(ObjectRegistry.STOVE.get());

                out.accept(ObjectRegistry.RED_BRICKS.get());
                out.accept(ObjectRegistry.RED_BRICK_SLAB.get());
                out.accept(ObjectRegistry.RED_BRICK_STAIRS.get());
                out.accept(ObjectRegistry.RED_BRICK_WALL.get());
                out.accept(ObjectRegistry.CALCITE_BRICKS.get());
                out.accept(ObjectRegistry.CALCITE_BRICK_SLAB.get());
                out.accept(ObjectRegistry.CALCITE_BRICK_STAIRS.get());
                out.accept(ObjectRegistry.CALCITE_BRICK_WALL.get());

                out.accept(ObjectRegistry.LARCH_LOG.get());
                out.accept(ObjectRegistry.LARCH_WOOD.get());
                out.accept(ObjectRegistry.STRIPPED_LARCH_WOOD.get());
                out.accept(ObjectRegistry.STRIPPED_LARCH_LOG.get());
                out.accept(ObjectRegistry.LARCH_PLANKS.get());
                out.accept(ObjectRegistry.LARCH_STAIRS.get());
                out.accept(ObjectRegistry.LARCH_SLAB.get());
                out.accept(ObjectRegistry.LARCH_PRESSURE_PLATE.get());
                out.accept(ObjectRegistry.LARCH_BUTTON.get());
                out.accept(ObjectRegistry.LARCH_TRAPDOOR.get());
                out.accept(ObjectRegistry.LARCH_DOOR.get());
                out.accept(ObjectRegistry.LARCH_WINDOW.get());
                out.accept(ObjectRegistry.LARCH_FENCE.get());
                out.accept(ObjectRegistry.LARCH_FENCE_GATE.get());
                out.accept(ObjectRegistry.LARCH_LEAVES.get());
                out.accept(ObjectRegistry.LARCH_SAPLING.get());
                out.accept(BoatsAndSignsRegistry.LARCH_SIGN_ITEM.get());
                out.accept(BoatsAndSignsRegistry.LARCH_HANGING_SIGN_ITEM.get());
                out.accept(ObjectRegistry.SWAMP_OAK_LOG.get());
                out.accept(ObjectRegistry.SWAMP_OAK_WOOD.get());
                out.accept(ObjectRegistry.STRIPPED_SWAMP_OAK_WOOD.get());
                out.accept(ObjectRegistry.STRIPPED_SWAMP_OAK_LOG.get());
                out.accept(ObjectRegistry.SWAMP_OAK_PLANKS.get());
                out.accept(ObjectRegistry.SWAMP_OAK_STAIRS.get());
                out.accept(ObjectRegistry.SWAMP_OAK_SLAB.get());
                out.accept(ObjectRegistry.SWAMP_OAK_PRESSURE_PLATE.get());
                out.accept(ObjectRegistry.SWAMP_OAK_BUTTON.get());
                out.accept(ObjectRegistry.SWAMP_OAK_TRAPDOOR.get());
                out.accept(ObjectRegistry.SWAMP_OAK_DOOR.get());
                out.accept(ObjectRegistry.SWAMP_OAK_WINDOW.get());
                out.accept(ObjectRegistry.SWAMP_OAK_FENCE.get());
                out.accept(ObjectRegistry.SWAMP_OAK_FENCE_GATE.get());
                out.accept(ObjectRegistry.ORANGE_LEAVES.get());
                out.accept(ObjectRegistry.SWAMP_OAK_SAPLING.get());
                out.accept(BoatsAndSignsRegistry.SWAMP_OAK_SIGN_ITEM.get());
                out.accept(BoatsAndSignsRegistry.SWAMP_OAK_HANGING_SIGN_ITEM.get());

                out.accept(ObjectRegistry.TEA_LEAF_CRATE.get());
                out.accept(ObjectRegistry.GREEN_TEA_LEAF_BLOCK.get());
                out.accept(ObjectRegistry.BLACK_TEA_LEAF_BLOCK.get());
                out.accept(ObjectRegistry.MIXED_TEA_LEAF_BLOCK.get());
                out.accept(ObjectRegistry.OOLONG_TEA_LEAF_BLOCK.get());
                out.accept(ObjectRegistry.WITCH_HAT.get());
                out.accept(ObjectRegistry.TOP_HAT.get());
                out.accept(ObjectRegistry.FLOWER_POT_BIG.get());
                out.accept(ObjectRegistry.FLOWER_BOX.get());
                out.accept(ObjectRegistry.WILD_COFFEE_PLANT.get());
                out.accept(ObjectRegistry.WILD_YERBA_MATE_PLANT.get());
                out.accept(ObjectRegistry.WILD_ROOIBOS_PLANT.get());
                out.accept(ObjectRegistry.BEGONIE.get());
                out.accept(ObjectRegistry.HIBISCUS.get());
                out.accept(ObjectRegistry.JOE_PYE.get());
                out.accept(ObjectRegistry.LAVENDER.get());
                out.accept(ObjectRegistry.MOUNTAIN_LAUREL.get());
                out.accept(ObjectRegistry.TALL_MOUNTAIN_LAUREL.get());
                out.accept(ObjectRegistry.HYSSOP.get());
                out.accept(ObjectRegistry.MOUNTAIN_SNOWBELL.get());
                out.accept(ObjectRegistry.CARDINAL.get());
                out.accept(ObjectRegistry.BOTTLEBRUSHES.get());
                out.accept(ObjectRegistry.DAPHNE.get());

                out.accept(ObjectRegistry.BIRD_OF_PARADISE.get());
                out.accept(ObjectRegistry.WHITE_ORCHID.get());
                out.accept(ObjectRegistry.BLUEBELL.get());
                out.accept(ObjectRegistry.GOATSBEARD.get());
                out.accept(ObjectRegistry.GENISTEAE.get());
                out.accept(ObjectRegistry.FOXGLOVE_WHITE.get());
                out.accept(ObjectRegistry.FOXGLOVE_PINK.get());
                out.accept(ObjectRegistry.FREESIA_YELLOW.get());
                out.accept(ObjectRegistry.FREESIA_PINK.get());
                out.accept(ObjectRegistry.LUPINE_BLUE.get());
                out.accept(ObjectRegistry.TALL_LUPINE_BLUE.get());
                out.accept(ObjectRegistry.LUPINE_PURPLE.get());
                out.accept(ObjectRegistry.TALL_LUPINE_PURPLE.get());
                out.accept(ObjectRegistry.TEA_BLOSSOM.get());
                out.accept(ObjectRegistry.GREEN_TEA_LEAF.get());
                out.accept(ObjectRegistry.YERBA_MATE_LEAF.get());
                out.accept(ObjectRegistry.ROOIBOS_LEAF.get());
                out.accept(ObjectRegistry.COFFEE_BEANS.get());
                out.accept(ObjectRegistry.DRIED_BLACK_TEA.get());
                out.accept(ObjectRegistry.DRIED_OOLONG_TEA.get());
                out.accept(ObjectRegistry.TEA_KETTLE.get());
                out.accept(ObjectRegistry.COPPER_TEA_KETTLE.get());
                out.accept(ObjectRegistry.CAULDRON.get());
                out.accept(ObjectRegistry.HEALTH_FLASK.get());
                out.accept(ObjectRegistry.HEALTH_FLASK_BIG.get());
                out.accept(ObjectRegistry.REGENERATION_FLASK.get());
                out.accept(ObjectRegistry.REGENERATION_FLASK_BIG.get());
                out.accept(ObjectRegistry.FIRE_PROTECTION_FLASK.get());
                out.accept(ObjectRegistry.FIRE_PROTECTION_FLASK_BIG.get());
                out.accept(ObjectRegistry.GREEN_TEA.get());
                out.accept(ObjectRegistry.BLACK_TEA.get());
                out.accept(ObjectRegistry.HIBISCUS_TEA.get());
                out.accept(ObjectRegistry.LAVENDER_TEA.get());
                out.accept(ObjectRegistry.YERBA_MATE_TEA.get());
                out.accept(ObjectRegistry.ROOIBOS_TEA.get());
                out.accept(ObjectRegistry.OOLONG_TEA.get());
                out.accept(ObjectRegistry.COFFEE.get());
                out.accept(ObjectRegistry.MILK_COFFEE.get());



                out.accept(BoatsAndSignsRegistry.LARCH_CHEST_BOAT.get());
                out.accept(BoatsAndSignsRegistry.LARCH_BOAT.get());
                out.accept(BoatsAndSignsRegistry.SWAMP_OAK_CHEST_BOAT.get());
                out.accept(BoatsAndSignsRegistry.SWAMP_OAK_BOAT.get());
                out.accept(ObjectRegistry.WANDERING_GARDENER_SPAWN_EGG.get());
                out.accept(ObjectRegistry.HERBALBREWS_STANDARD.get());


            })
            .build());

    public static void init() {
        HERBALBREWS_TABS.register();
    }
}
