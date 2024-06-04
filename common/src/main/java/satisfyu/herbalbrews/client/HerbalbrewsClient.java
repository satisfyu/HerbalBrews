package satisfyu.herbalbrews.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import satisfyu.herbalbrews.client.block.CompletionistBannerRenderer;
import satisfyu.herbalbrews.client.gui.CauldronGui;
import satisfyu.herbalbrews.client.gui.TeaKettleGui;
import satisfyu.herbalbrews.client.model.TopHatModel;
import satisfyu.herbalbrews.client.model.WitchHatModel;
import satisfyu.herbalbrews.registry.ObjectRegistry;
import satisfyu.herbalbrews.registry.ScreenHandlerTypeRegistry;

import static satisfyu.herbalbrews.registry.BlockEntityRegistry.COMPLETIONIST_BANNER_ENTITY;

@Environment(EnvType.CLIENT)
public class HerbalbrewsClient {

    public static final ModelLayerLocation TOP_HAT_LAYER = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "top_hat_helmet");

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
        registerEntityRenderers();
        registerEntityModelLayer();
    }

    public static void registerEntityRenderers() {
        BlockEntityRendererRegistry.register(COMPLETIONIST_BANNER_ENTITY.get(), CompletionistBannerRenderer::new);
    }

    public static void registerEntityModelLayer() {
        EntityModelLayerRegistry.register(CompletionistBannerRenderer.LAYER_LOCATION, CompletionistBannerRenderer::createBodyLayer);
        EntityModelLayerRegistry.register(TopHatModel.LAYER_LOCATION, TopHatModel::createBodyLayer);
        EntityModelLayerRegistry.register(WitchHatModel.LAYER_LOCATION, WitchHatModel::createBodyLayer);
    }
}

