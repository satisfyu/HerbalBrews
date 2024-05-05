package satisfyu.herbalbrews.client;

import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import satisfyu.herbalbrews.client.gui.CauldronGui;
import satisfyu.herbalbrews.client.gui.TeaKettleGui;
import satisfyu.herbalbrews.registry.ArmorRegistry;
import satisfyu.herbalbrews.registry.ObjectRegistry;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;

@Environment(EnvType.CLIENT)
public class HerbalbrewsClient {

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                ObjectRegistry.LAVENDER.get(), ObjectRegistry.WILD_COFFEE_PLANT.get(),
                ObjectRegistry.WILD_ROOIBOS_PLANT.get(), ObjectRegistry.WILD_YERBA_MATE_PLANT.get(), ObjectRegistry.HIBISCUS.get(),
                ObjectRegistry.HIBISCUS_TEA_BLOCK.get(), ObjectRegistry.MILK_COFFEE_BLOCK.get(), ObjectRegistry.POTTED_HIBISCUS.get(),
                ObjectRegistry.POTTED_LAVENDER.get(), ObjectRegistry.WILD_ROOIBOS_PLANT.get(), ObjectRegistry.POTTED_WILD_COFFEE.get(),
                ObjectRegistry.POTTED_WILD_YERBA_MATE.get(), ObjectRegistry.COFFEE_PLANT.get(), ObjectRegistry.TEA_PLANT.get(),
                ObjectRegistry.YERBA_MATE_PLANT.get(), ObjectRegistry.ROOIBOS_PLANT.get(), ObjectRegistry.POTTED_WILD_ROOIBOS.get(),
                ObjectRegistry.COPPER_TEA_KETTLE.get(), ObjectRegistry.COPPER_TEA_KETTLE.get(), ObjectRegistry.TEA_KETTLE.get(),
                ObjectRegistry.CAULDRON.get(), ObjectRegistry.JUG.get()
        );

        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get(), TeaKettleGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), CauldronGui::new);

    }


    public static void preInitClient() {
        ArmorRegistry.registerArmorModelLayers();
    }
}

