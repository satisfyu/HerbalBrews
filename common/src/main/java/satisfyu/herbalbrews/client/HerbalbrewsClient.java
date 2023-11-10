package satisfyu.herbalbrews.client;

import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import satisfyu.herbalbrews.client.gui.TeaKettleGui;
import satisfyu.herbalbrews.registry.ObjectRegistry;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;

@Environment(EnvType.CLIENT)
public class HerbalbrewsClient {

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                ObjectRegistry.CARDINAL.get(), ObjectRegistry.LAVENDER.get(), ObjectRegistry.CHAMOMILE.get(), ObjectRegistry.WILD_COFFEE_PLANT.get(),
                ObjectRegistry.WILD_ROOIBOS_PLANT.get(), ObjectRegistry.WILD_YERBA_MATE_PLANT.get(), ObjectRegistry.HIBISCUS.get(),
                ObjectRegistry.JOE_PYE.get(), ObjectRegistry.HYSSOP.get(), ObjectRegistry.MOUNTAIN_SNOWBELL.get(), ObjectRegistry.CARDINAL.get(),
                ObjectRegistry.BIRD_OF_PARADISE.get(), ObjectRegistry.WHITE_ORCHID.get(), ObjectRegistry.HIBISCUS_TEA.get(), ObjectRegistry.COFFEE.get(),
                ObjectRegistry.POTTED_CHAMOMILE.get(), ObjectRegistry.POTTED_HIBISCUS.get(),
                ObjectRegistry.POTTED_JOE_PYE.get(), ObjectRegistry.POTTED_HYSSOP.get(), ObjectRegistry.POTTED_LAVENDER.get(), ObjectRegistry.WILD_ROOIBOS_PLANT.get(),
                ObjectRegistry.POTTED_WILD_COFFEE.get(), ObjectRegistry.POTTED_WILD_YERBA_MATE.get(), ObjectRegistry.POTTED_MOUNTAIN_SNOWBELL.get(),
                ObjectRegistry.POTTED_WHITE_ORCHID.get(), ObjectRegistry.POTTED_BIRD_OF_PARADISE.get(), ObjectRegistry.COFFEE_PLANT.get(), ObjectRegistry.TEA_PLANT.get(),
                ObjectRegistry.YERBA_MATE_PLANT.get(), ObjectRegistry.ROOIBOS_PLANT.get(), ObjectRegistry.POTTED_WILD_ROOIBOS.get()

        );

        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get(), TeaKettleGui::new);

    }




    public static void preInitClient() {
    }

}

