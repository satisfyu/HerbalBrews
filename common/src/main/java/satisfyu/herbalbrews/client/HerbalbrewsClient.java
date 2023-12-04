package satisfyu.herbalbrews.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;
import satisfyu.herbalbrews.client.gui.CauldronGui;
import satisfyu.herbalbrews.client.gui.TeaKettleGui;
import satisfyu.herbalbrews.client.model.RedWolfModel;
import satisfyu.herbalbrews.client.model.WanderingGardenerModel;
import satisfyu.herbalbrews.client.render.block.FlowerBoxBlockEntityRenderer;
import satisfyu.herbalbrews.client.render.block.FlowerPotBigBlockEntityRenderer;
import satisfyu.herbalbrews.client.render.entity.RedWolfRenderer;
import satisfyu.herbalbrews.client.render.entity.WanderingGardenerRenderer;
import satisfyu.herbalbrews.registry.*;

@Environment(EnvType.CLIENT)
public class HerbalbrewsClient {

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                ObjectRegistry.CARDINAL.get(), ObjectRegistry.LAVENDER.get(), ObjectRegistry.MOUNTAIN_LAUREL.get(), ObjectRegistry.WILD_COFFEE_PLANT.get(),
                ObjectRegistry.WILD_ROOIBOS_PLANT.get(), ObjectRegistry.WILD_YERBA_MATE_PLANT.get(), ObjectRegistry.HIBISCUS.get(),
                ObjectRegistry.JOE_PYE.get(), ObjectRegistry.HYSSOP.get(), ObjectRegistry.MOUNTAIN_SNOWBELL.get(), ObjectRegistry.CARDINAL.get(),
                ObjectRegistry.BIRD_OF_PARADISE.get(), ObjectRegistry.WHITE_ORCHID.get(), ObjectRegistry.HIBISCUS_TEA.get(), ObjectRegistry.MILK_COFFEE.get(),
                ObjectRegistry.POTTED_MOUNTAIN_LAUREL.get(), ObjectRegistry.POTTED_HIBISCUS.get(),
                ObjectRegistry.POTTED_JOE_PYE.get(), ObjectRegistry.POTTED_HYSSOP.get(), ObjectRegistry.POTTED_LAVENDER.get(), ObjectRegistry.WILD_ROOIBOS_PLANT.get(),
                ObjectRegistry.POTTED_WILD_COFFEE.get(), ObjectRegistry.POTTED_WILD_YERBA_MATE.get(), ObjectRegistry.POTTED_MOUNTAIN_SNOWBELL.get(),
                ObjectRegistry.POTTED_WHITE_ORCHID.get(), ObjectRegistry.POTTED_BIRD_OF_PARADISE.get(), ObjectRegistry.COFFEE_PLANT.get(), ObjectRegistry.TEA_PLANT.get(),
                ObjectRegistry.YERBA_MATE_PLANT.get(), ObjectRegistry.ROOIBOS_PLANT.get(), ObjectRegistry.POTTED_WILD_ROOIBOS.get(), ObjectRegistry.COPPER_TEA_KETTLE.get(),
                ObjectRegistry.BEGONIE.get(), ObjectRegistry.GENISTEAE.get(), ObjectRegistry.GOATSBEARD.get(), ObjectRegistry.BLUEBELL.get(), ObjectRegistry.DAPHNE.get(),
                ObjectRegistry.BOTTLEBRUSHES.get(), ObjectRegistry.FOXGLOVE_WHITE.get(), ObjectRegistry.FOXGLOVE_PINK.get(), ObjectRegistry.FREESIA_YELLOW.get(),
                ObjectRegistry.FREESIA_PINK.get(), ObjectRegistry.LUPINE_BLUE.get(), ObjectRegistry.LUPINE_PURPLE.get(), ObjectRegistry.COPPER_TEA_KETTLE.get(),
                ObjectRegistry.LARCH_DOOR.get(), ObjectRegistry.TEA_KETTLE.get(), ObjectRegistry.POTTED_BEGONIE.get(), ObjectRegistry.POTTED_GENISTEAE.get(),
                ObjectRegistry.POTTED_GOATSBEARD.get(), ObjectRegistry.POTTED_BLUEBELL.get(), ObjectRegistry.POTTED_DAPHNE.get(), ObjectRegistry.POTTED_BOTTLEBRUSHES.get(),
                ObjectRegistry.POTTED_FOXGLOVE_WHITE.get(), ObjectRegistry.POTTED_FOXGLOVE_PINK.get(), ObjectRegistry.POTTED_FREESIA_YELLOW.get(),
                ObjectRegistry.POTTED_FREESIA_PINK.get(), ObjectRegistry.POTTED_LUPINE_BLUE.get(), ObjectRegistry.POTTED_LUPINE_PURPLE.get(),
                ObjectRegistry.POTTED_LARCH_SAPLING.get(), ObjectRegistry.LARCH_SAPLING.get(), ObjectRegistry.CAULDRON.get(), ObjectRegistry.SWAMP_OAK_TRAPDOOR.get(),
                ObjectRegistry.SWAMP_OAK_WINDOW.get(), ObjectRegistry.SWAMP_OAK_DOOR.get(), ObjectRegistry.SWAMP_OAK_SAPLING.get(), ObjectRegistry.LARCH_WINDOW.get(),
                ObjectRegistry.TALL_MOUNTAIN_LAUREL.get(), ObjectRegistry.TALL_LUPINE_BLUE.get(), ObjectRegistry.TALL_LUPINE_PURPLE.get()
        );

        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> FoliageColor.get(0.5, 1.0), ObjectRegistry.SWAMP_OAK_LEAVES.get());
        ColorHandlerRegistry.registerBlockColors((state, world, pos, tintIndex) -> { if (world == null || pos == null) {return -1;}return BiomeColors.getAverageFoliageColor(world, pos);},
                ObjectRegistry.SWAMP_OAK_LEAVES.get()
        );


        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.TEA_KETTLE_SCREEN_HANDLER.get(), TeaKettleGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.CAULDRON_SCREEN_HANDLER.get(), CauldronGui::new);

    }


    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayer();
        registerBlockRenderer();
        ArmorRegistry.registerArmorModelLayers();
    }

    private static void registerBlockRenderer() {
        BlockEntityRendererRegistry.register(BlockEntityRegistry.FLOWER_BOX_ENTITY.get(), FlowerBoxBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(BlockEntityRegistry.FLOWER_POT_BIG_ENTITY.get(), FlowerPotBigBlockEntityRenderer::new);

    }


        public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityRegistry.WANDERING_GARDENER, WanderingGardenerRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.RED_WOLF, RedWolfRenderer::new);
        }

    public static void registerEntityModelLayer() {
        EntityModelLayerRegistry.register(WanderingGardenerModel.LAYER_LOCATION, WanderingGardenerModel::getTexturedModelData);
        EntityModelLayerRegistry.register(RedWolfModel.LAYER_LOCATION, RedWolfModel::getTexturedModelData);

    }

}

