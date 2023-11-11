package satisfyu.herbalbrews.registry;

import de.cristelknight.doapi.Util;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import satisfyu.herbalbrews.HerbalBrews;
import satisfyu.herbalbrews.blocks.*;
import satisfyu.herbalbrews.items.*;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.ITEM);
    public static final Registrar<Item> ITEM_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(HerbalBrews.MOD_ID, Registries.BLOCK);
    public static final Registrar<Block> BLOCK_REGISTRAR = BLOCKS.getRegistrar();

    public static final RegistrySupplier<Block> STOVE = registerWithItem("stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0).randomTicks()));
    public static final RegistrySupplier<Block> TEA_LEAF_CRATE = registerWithItem("tea_leaf_crate", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RED_WOOL)));
    public static final RegistrySupplier<Block> GREEN_TEA_LEAF_BLOCK = registerWithItem("green_tea_leaf_block", () -> new TeaLeafBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
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
    public static final RegistrySupplier<Block> HIBISCUS = registerWithItem("hibiscus", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> JOE_PYE = registerWithItem("joe_pye", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> HYSSOP = registerWithItem("hyssop", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> MOUNTAIN_SNOWBELL = registerWithItem("mountain_snowbell", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> CARDINAL = registerWithItem("cardinal", () -> new TallFlowerBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> CHAMOMILE = registerWithItem("chamomile", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> LAVENDER = registerWithItem("lavender", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> BIRD_OF_PARADISE = registerWithItem("bird_of_paradise", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> WHITE_ORCHID = registerWithItem("white_orchid", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> COPPER_TEA_KETTLE = registerWithItem("copper_tea_kettle", () -> new TeaKettleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<Block> TEA_KETTLE = registerWithItem("tea_kettle", () -> new TeaKettleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistrySupplier<Item> TEA_BLOSSOM = registerItem("tea_blossom", () -> new SeedItem(TEA_PLANT.get(), getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> GREEN_TEA_LEAF = registerItem("green_tea_leaf", () -> new TooltipItem(getSettings()));
    public static final RegistrySupplier<Item> YERBA_MATE_LEAF = registerItem("yerba_mate_leaf", () -> new SeedItem(YERBA_MATE_PLANT.get(), getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> ROOIBOS_LEAF = registerItem("rooibos_leaf", () -> new SeedItem(ROOIBOS_PLANT.get(), getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> DRIED_BLACK_TEA = registerItem("dried_black_tea", () -> new TooltipItem(getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> DRIED_OOLONG_TEA = registerItem("dried_oolong_tea", () -> new TooltipItem(getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item> COFFEE_BEANS = registerItem("coffee_beans", () -> new SeedItem(COFFEE_PLANT.get(), getSettings().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fast().alwaysEat().build())));
    public static final RegistrySupplier<Item>  GREEN_TEA = registerItem("green_tea", () -> new DrinkItem(getFoodItemSettings(4, 0.7f, EffectRegistry.BALANCED.get(), 60 * 15)));
    public static final RegistrySupplier<Item>  BLACK_TEA = registerItem("black_tea", () -> new DrinkItem(getFoodItemSettings(4, 0.7f, EffectRegistry.REVITALIZING.get(), 60 * 15)));
    public static final RegistrySupplier<Item>  LAVENDER_TEA = registerItem("lavender_tea", () -> new DrinkItem(getFoodItemSettings(4, 0.7f, EffectRegistry.FORTUNE.get(), 60 * 15)));
    public static final RegistrySupplier<Item>  YERBA_MATE_TEA = registerItem("yerba_mate_tea", () -> new DrinkItem(getFoodItemSettings(4, 0.7f, EffectRegistry.POISONOUSBREATH.get(), 60 * 15)));
    public static final RegistrySupplier<Item>  OOLONG_TEA = registerItem("oolong_tea", () -> new DrinkItem(getFoodItemSettings(4, 0.7f, EffectRegistry.RENEWAL.get(), 60 * 15)));
    public static final RegistrySupplier<Item>  ROOIBOS_TEA = registerItem("rooibos_tea", () -> new DrinkItem(getFoodItemSettings(4, 0.7f, EffectRegistry.LASTSTAND.get(), 60 * 15)));
    public static final RegistrySupplier<Block>  HIBISCUS_TEA = registerTea("hibiscus_tea", () -> new TeaCupBlock(getTeaSettings()),(EffectRegistry.FERAL.get()));
    public static final RegistrySupplier<Block>  COFFEE = registerTea("coffee", () -> new TeaCupBlock(getTeaSettings()),(EffectRegistry.TOUGH.get()));
    public static final RegistrySupplier<Item>  HERBALBREWS_STANDARD = registerItem("herbalbrews_standard", () -> new HerbalbrewsStandardItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
    public static final RegistrySupplier<Block> POTTED_HIBISCUS = registerWithoutItem("potted_hibiscus", () -> new FlowerPotBlock(HIBISCUS.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_JOE_PYE = registerWithoutItem("potted_joe_pye", () -> new FlowerPotBlock(JOE_PYE.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_HYSSOP = registerWithoutItem("potted_hyssop", () -> new FlowerPotBlock(HYSSOP.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_MOUNTAIN_SNOWBELL = registerWithoutItem("potted_mountain_snowbell", () -> new FlowerPotBlock(MOUNTAIN_SNOWBELL.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_CHAMOMILE = registerWithoutItem("potted_chamomile", () -> new FlowerPotBlock(CHAMOMILE.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_LAVENDER = registerWithoutItem("potted_lavender", () -> new FlowerPotBlock(LAVENDER.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WHITE_ORCHID = registerWithoutItem("potted_white_orchid", () -> new FlowerPotBlock(WHITE_ORCHID.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_BIRD_OF_PARADISE = registerWithoutItem("potted_bird_of_paradise", () -> new FlowerPotBlock(BIRD_OF_PARADISE.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WILD_ROOIBOS = registerWithoutItem("potted_wild_rooibos", () -> new FlowerPotBlock(WILD_ROOIBOS_PLANT.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WILD_COFFEE = registerWithoutItem("potted_wild_coffee", () -> new FlowerPotBlock(WILD_COFFEE_PLANT.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WILD_YERBA_MATE = registerWithoutItem("potted_wild_yerba_mate", () -> new FlowerPotBlock(WILD_YERBA_MATE_PLANT.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));


    public static void init() {
        ITEMS.register();
        BLOCKS.register();
    }

    public static void registerCompostable() {
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GREEN_TEA_LEAF_BLOCK.get(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BLACK_TEA_LEAF_BLOCK.get(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.MIXED_TEA_LEAF_BLOCK.get(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.OOLONG_TEA_LEAF_BLOCK.get(), 0.8f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GREEN_TEA_LEAF.get(), 0.2f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.YERBA_MATE_LEAF.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.COFFEE_BEANS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.TEA_BLOSSOM.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WILD_COFFEE_PLANT.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WILD_ROOIBOS_PLANT.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WILD_YERBA_MATE_PLANT.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.HIBISCUS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.LAVENDER.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.JOE_PYE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHAMOMILE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.HYSSOP.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CARDINAL.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WHITE_ORCHID.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.MOUNTAIN_SNOWBELL.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BIRD_OF_PARADISE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DRIED_OOLONG_TEA.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DRIED_BLACK_TEA.get(), 0.5f);
    }

    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }

    private static Item.Properties getSettingsWithoutTab(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }

    static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }

    private static Item.Properties getFoodItemSettings(int nutrition, float saturationMod, MobEffect effect, int duration) {
        return getFoodItemSettings(nutrition, saturationMod, effect, duration, true, false);
    }

    private static Item.Properties getFoodItemSettings(int nutrition, float saturationMod, MobEffect effect, int duration, boolean alwaysEat, boolean fast) {
        return getSettings().food(createFood(nutrition, saturationMod, effect, duration, alwaysEat, fast));
    }

    private static FoodProperties createFood(int nutrition, float saturationMod, MobEffect effect, int duration, boolean alwaysEat, boolean fast) {
        FoodProperties.Builder food = new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturationMod);
        if (alwaysEat) food.alwaysEat();
        if (fast) food.fast();
        if (effect != null) food.effect(new MobEffectInstance(effect, duration), 1.0f);
        return food.build();
    }


    private static Item.Properties getSettingsWithoutTab() {
        return getSettingsWithoutTab(settings -> {
        });
    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        return Util.registerWithItem(BLOCKS, BLOCK_REGISTRAR, ITEMS, ITEM_REGISTRAR, new HerbalBrewsIdentifier(name), block);
    }


    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return Util.registerWithoutItem(BLOCKS, BLOCK_REGISTRAR, new HerbalBrewsIdentifier(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return Util.registerItem(ITEMS, ITEM_REGISTRAR, new HerbalBrewsIdentifier(path), itemSupplier);
    }

    private static FoodProperties teaFoodComponent(MobEffect effect) {
        FoodProperties.Builder component = new FoodProperties.Builder().nutrition(2).saturationMod(2);
        if (effect != null) component.effect(new MobEffectInstance(effect, 90 * 20), 1.0f);
        return component.build();
    }

    private static BlockBehaviour.Properties getTeaSettings() {
        return BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion().instabreak();
    }

    private static <T extends Block> RegistrySupplier<T> registerTea(String name, Supplier<T> block, MobEffect effect) {
        RegistrySupplier<T> toReturn = registerWithoutItem(name, block);
        registerItem(name, () -> new DrinkBlockItem(toReturn.get(), getSettings(settings -> settings.food(teaFoodComponent(effect)))));
        return toReturn;
    }

}

