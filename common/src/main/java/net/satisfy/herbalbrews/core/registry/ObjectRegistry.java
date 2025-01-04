package net.satisfy.herbalbrews.core.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.satisfy.herbalbrews.HerbalBrews;
import net.satisfy.herbalbrews.core.blocks.*;
import net.satisfy.herbalbrews.core.blocks.CauldronBlock;
import net.satisfy.herbalbrews.core.items.*;
import net.satisfy.herbalbrews.core.util.HerbalBrewsIdentifier;
import net.satisfy.herbalbrews.core.util.HerbalBrewsUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.ITEM);
    public static final Registrar<Item> ITEM_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.BLOCK);
    public static final Registrar<Block> BLOCK_REGISTRAR = BLOCKS.getRegistrar();

    public static final RegistrySupplier<Block> STOVE = registerWithItem("stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0).randomTicks()));
    public static final RegistrySupplier<Block> TEA_LEAF_CRATE = registerWithItem("tea_leaf_crate", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> GREEN_TEA_LEAF_BLOCK = registerWithItem("green_tea_leaf_block", () -> new TeaLeafBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistrySupplier<Block> DRIED_GREEN_TEA_LEAF_BLOCK = registerWithItem("dried_green_tea_leaf_block", () -> new TeaLeafBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistrySupplier<Block> DRIED_OUT_GREEN_TEA_LEAF_BLOCK = registerWithItem("dried_out_green_tea_leaf_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistrySupplier<Block> BLACK_TEA_LEAF_BLOCK = registerWithItem("black_tea_leaf_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistrySupplier<Block> MIXED_TEA_LEAF_BLOCK = registerWithItem("mixed_tea_leaf_block", () -> new TeaLeafBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistrySupplier<Block> OOLONG_TEA_LEAF_BLOCK = registerWithItem("oolong_tea_leaf_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistrySupplier<Block> WILD_COFFEE_PLANT = registerWithItem("wild_coffee_plant", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> COFFEE_PLANT = registerWithoutItem("coffee_plant", () -> new CoffeeCropBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistrySupplier<Block> WILD_ROOIBOS_PLANT = registerWithItem("wild_rooibos_plant", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ROOIBOS_PLANT = registerWithoutItem("rooibos_plant", () -> new RooibosCropBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistrySupplier<Block> WILD_YERBA_MATE_PLANT = registerWithItem("wild_yerba_mate_plant", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> YERBA_MATE_PLANT = registerWithoutItem("yerba_mate_plant", () -> new YerbaMateCropBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistrySupplier<Block> TEA_PLANT = registerWithoutItem("tea_plant", () -> new TeaCropBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistrySupplier<Block> HIBISCUS = registerWithItem("hibiscus", () -> new BonemealableFlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> LAVENDER = registerWithItem("lavender", () -> new BonemealableFlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> JUG = registerWithoutItem("jug", () -> new JugBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
    public static final RegistrySupplier<Item> JUG_ITEM = registerItem("jug", () -> new JugItem(JUG.get(), getSettings()));
    public static final RegistrySupplier<Block> COPPER_TEA_KETTLE = registerWithItem("copper_tea_kettle", () -> new TeaKettleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<Block> TEA_KETTLE = registerWithItem("tea_kettle", () -> new TeaKettleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<Block> CAULDRON = registerWithItem("cauldron", () -> new CauldronBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).lightLevel((blockState) -> 11)));
    public static final RegistrySupplier<Item> FLASK = registerItem("flask", () -> new FlaskItem(getSettings()));
    public static final RegistrySupplier<Item> TEA_BLOSSOM = registerItem("tea_blossom", () -> new BlockItem(TEA_PLANT.get(), getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> GREEN_TEA_LEAF = registerItem("green_tea_leaf", () -> new Item(getSettings()));
    public static final RegistrySupplier<Item> YERBA_MATE_LEAF = registerItem("yerba_mate_leaf", () -> new BlockItem(YERBA_MATE_PLANT.get(), getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> ROOIBOS_LEAF = registerItem("rooibos_leaf", () -> new BlockItem(ROOIBOS_PLANT.get(), getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> LAVENDER_BLOSSOM = registerItem("lavender_blossom", () -> new Item(getSettings()));
    public static final RegistrySupplier<Item> DRIED_GREEN_TEA = registerItem("dried_green_tea", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> DRIED_BLACK_TEA = registerItem("dried_black_tea", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> DRIED_OOLONG_TEA = registerItem("dried_oolong_tea", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> COFFEE_BEANS = registerItem("coffee_beans", () -> new BlockItem(COFFEE_PLANT.get(), getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Block> GREEN_TEA_BLOCK = registerWithoutItem("green_tea_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Block> BLACK_TEA_BLOCK = registerWithoutItem("black_tea_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Block> LAVENDER_TEA_BLOCK = registerWithoutItem("lavender_tea_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Block> YERBA_MATE_TEA_BLOCK = registerWithoutItem("yerba_mate_tea_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Block> OOLONG_TEA_BLOCK = registerWithoutItem("oolong_tea_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Block> ROOIBOS_TEA_BLOCK = registerWithoutItem("rooibos_tea_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Block> HIBISCUS_TEA_BLOCK = registerWithoutItem("hibiscus_tea_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Block> MILK_COFFEE_BLOCK = registerWithoutItem("milk_coffee_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Block> COFFEE_BLOCK = registerWithoutItem("coffee_block", () -> new TeaCupBlock(getTeaSettings()));
    public static final RegistrySupplier<Item> GREEN_TEA = registerItem("green_tea", () -> new DrinkBlockItem(GREEN_TEA_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> BLACK_TEA = registerItem("black_tea", () -> new DrinkBlockItem(BLACK_TEA_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> LAVENDER_TEA = registerItem("lavender_tea", () -> new DrinkBlockItem(LAVENDER_TEA_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> YERBA_MATE_TEA = registerItem("yerba_mate_tea", () -> new DrinkBlockItem(YERBA_MATE_TEA_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> OOLONG_TEA = registerItem("oolong_tea", () -> new DrinkBlockItem(OOLONG_TEA_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> ROOIBOS_TEA = registerItem("rooibos_tea", () -> new DrinkBlockItem(ROOIBOS_TEA_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> HIBISCUS_TEA = registerItem("hibiscus_tea", () -> new DrinkBlockItem(HIBISCUS_TEA_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> MILK_COFFEE = registerItem("milk_coffee", () -> new DrinkBlockItem(MILK_COFFEE_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> COFFEE = registerItem("coffee", () -> new DrinkBlockItem(COFFEE_BLOCK.get(), getSettings().food(new FoodProperties.Builder().nutrition(Foods.SWEET_BERRIES.getNutrition()).saturationMod(Foods.SWEET_BERRIES.getSaturationModifier()).alwaysEat().build())));
    public static final RegistrySupplier<Item> WITCH_HAT = registerItem("witch_hat", () -> new HatItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.EPIC), new HerbalBrewsIdentifier("textures/models/armor/witch_hat.png")));
    public static final RegistrySupplier<Item> TOP_HAT = registerItem("top_hat", () -> new HatItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.EPIC), new HerbalBrewsIdentifier("textures/models/armor/top_hat.png")));
    public static final RegistrySupplier<Block> HERBALBREWS_BANNER = registerWithItem("herbalbrews_banner", () -> new CompletionistBannerBlock(BlockBehaviour.Properties.of().strength(1F).instrument(NoteBlockInstrument.BASS).noCollission().sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> HERBALBREWS_WALL_BANNER = registerWithoutItem("herbalbrews_wall_banner", () -> new CompletionistWallBannerBlock(BlockBehaviour.Properties.of().strength(1F).instrument(NoteBlockInstrument.BASS).noCollission().sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> POTTED_LAVENDER = registerWithoutItem("potted_lavender", () -> new FlowerPotBlock(LAVENDER.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_HIBISCUS = registerWithoutItem("potted_hibiscus", () -> new FlowerPotBlock(HIBISCUS.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WILD_ROOIBOS = registerWithoutItem("potted_wild_rooibos", () -> new FlowerPotBlock(WILD_ROOIBOS_PLANT.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WILD_COFFEE = registerWithoutItem("potted_wild_coffee", () -> new FlowerPotBlock(WILD_COFFEE_PLANT.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WILD_YERBA_MATE = registerWithoutItem("potted_wild_yerba_mate", () -> new FlowerPotBlock(WILD_YERBA_MATE_PLANT.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static void init() {
        ITEMS.register();
        BLOCKS.register();
    }

    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }

    static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        return HerbalBrewsUtil.registerWithItem(BLOCKS, BLOCK_REGISTRAR, ITEMS, ITEM_REGISTRAR, new HerbalBrewsIdentifier(name), block);
    }

    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return HerbalBrewsUtil.registerWithoutItem(BLOCKS, BLOCK_REGISTRAR, new HerbalBrewsIdentifier(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return HerbalBrewsUtil.registerItem(ITEMS, ITEM_REGISTRAR, new HerbalBrewsIdentifier(path), itemSupplier);
    }

    private static BlockBehaviour.Properties getTeaSettings() {
        return BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion().instabreak();
    }
}
