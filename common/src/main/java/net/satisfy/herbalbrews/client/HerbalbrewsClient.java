package net.satisfy.herbalbrews.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.satisfy.herbalbrews.client.gui.CauldronGui;
import net.satisfy.herbalbrews.client.gui.TeaKettleGui;
import net.satisfy.herbalbrews.client.model.TopHatModel;
import net.satisfy.herbalbrews.client.model.WitchHatModel;
import net.satisfy.herbalbrews.client.renderer.CompletionistBannerRenderer;
import net.satisfy.herbalbrews.core.registry.EntityTypeRegistry;
import net.satisfy.herbalbrews.core.registry.ScreenHandlerTypeRegistry;

import static net.satisfy.herbalbrews.core.registry.ObjectRegistry.*;

@Environment(EnvType.CLIENT)
public class HerbalbrewsClient {
    public static void onInitializeClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                LAVENDER.get(), WILD_COFFEE_PLANT.get(), WILD_ROOIBOS_PLANT.get(),
                WILD_YERBA_MATE_PLANT.get(), HIBISCUS.get(), HIBISCUS_TEA_BLOCK.get(),
                MILK_COFFEE_BLOCK.get(), POTTED_HIBISCUS.get(), POTTED_LAVENDER.get(),
                POTTED_WILD_COFFEE.get(), POTTED_WILD_YERBA_MATE.get(),
                COFFEE_PLANT.get(), TEA_PLANT.get(), YERBA_MATE_PLANT.get(),
                ROOIBOS_PLANT.get(), POTTED_WILD_ROOIBOS.get(), COPPER_TEA_KETTLE.get(),
                TEA_KETTLE.get(), CAULDRON.get(), JUG.get(), GREEN_TEA_BLOCK.get(),
                BLACK_TEA_BLOCK.get(), LAVENDER_TEA_BLOCK.get(), COFFEE_BLOCK.get(), ROOIBOS_TEA_BLOCK.get(),
                YERBA_MATE_TEA_BLOCK.get()
        );


        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get(), TeaKettleGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), CauldronGui::new);
        BlockEntityRendererRegistry.register(EntityTypeRegistry.HERBALBREWS_BANNER.get(), CompletionistBannerRenderer::new);
    }

    public static void preInitClient() {
        EntityModelLayerRegistry.register(TopHatModel.LAYER_LOCATION, TopHatModel::createBodyLayer);
        EntityModelLayerRegistry.register(WitchHatModel.LAYER_LOCATION, WitchHatModel::createBodyLayer);
        EntityModelLayerRegistry.register(CompletionistBannerRenderer.LAYER_LOCATION, CompletionistBannerRenderer::createBodyLayer);
    }
}

