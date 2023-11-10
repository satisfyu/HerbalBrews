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

    public static final RegistrySupplier<CreativeModeTab> HERBALBREWS_TAB = HERBALBREWS_TABS.register("herbalbrews", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ObjectRegistry.TEA_KETTLE.get()))
            .title(Component.translatable("creative_tab.herbalbrews"))
            .displayItems((parameters, out) -> {
                out.accept(ObjectRegistry.STOVE.get());
                out.accept(ObjectRegistry.TEA_LEAF_CRATE.get());
                out.accept(ObjectRegistry.GREEN_TEA_LEAF_BLOCK.get());
                out.accept(ObjectRegistry.BLACK_TEA_LEAF_BLOCK.get());
                out.accept(ObjectRegistry.MIXED_TEA_LEAF_BLOCK.get());
                out.accept(ObjectRegistry.OOLONG_TEA_LEAF_BLOCK.get());
                out.accept(ObjectRegistry.WILD_COFFEE_PLANT.get());
                out.accept(ObjectRegistry.WILD_YERBA_MATE_PLANT.get());
                out.accept(ObjectRegistry.WILD_ROOIBOS_PLANT.get());
                /** --- Plants, Crops - 9 ---  **/


                out.accept(ObjectRegistry.HIBISCUS.get());
                out.accept(ObjectRegistry.JOE_PYE.get());
                out.accept(ObjectRegistry.LAVENDER.get());
                out.accept(ObjectRegistry.CHAMOMILE.get());
                out.accept(ObjectRegistry.HYSSOP.get());
                out.accept(ObjectRegistry.MOUNTAIN_SNOWBELL.get());
                out.accept(ObjectRegistry.CARDINAL.get());
                out.accept(ObjectRegistry.BIRD_OF_PARADISE.get());
                out.accept(ObjectRegistry.WHITE_ORCHID.get());
                /** --- Flowers 9 ---  **/

                out.accept(ObjectRegistry.TEA_KETTLE.get());
                out.accept(ObjectRegistry.COPPER_TEA_KETTLE.get());
                out.accept(ObjectRegistry.TEA_BLOSSOM.get());
                out.accept(ObjectRegistry.GREEN_TEA_LEAF.get());
                out.accept(ObjectRegistry.YERBA_MATE_LEAF.get());
                out.accept(ObjectRegistry.ROOIBOS_LEAF.get());
                out.accept(ObjectRegistry.COFFEE_BEANS.get());
                out.accept(ObjectRegistry.DRIED_BLACK_TEA.get());
                out.accept(ObjectRegistry.DRIED_OOLONG_TEA.get());
                /** --- Tea Stuff 9 ---  **/

                out.accept(ObjectRegistry.GREEN_TEA.get());
                out.accept(ObjectRegistry.BLACK_TEA.get());
                out.accept(ObjectRegistry.HIBISCUS_TEA.get());
                out.accept(ObjectRegistry.LAVENDER_TEA.get());
                out.accept(ObjectRegistry.YERBA_MATE_TEA.get());
                out.accept(ObjectRegistry.ROOIBOS_TEA.get());
                out.accept(ObjectRegistry.OOLONG_TEA.get());
                out.accept(ObjectRegistry.COFFEE.get());
                out.accept(ObjectRegistry.HERBALBREWS_STANDARD.get());

                /** --- Tea 9 ---  **/


            })
            .build());

    public static void init() {
        HERBALBREWS_TABS.register();
    }
}
