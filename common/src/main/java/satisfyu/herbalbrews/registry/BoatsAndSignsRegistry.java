package satisfyu.herbalbrews.registry;


import de.cristelknight.doapi.DoApiExpectPlatform;
import de.cristelknight.doapi.terraform.boat.TerraformBoatType;
import de.cristelknight.doapi.terraform.boat.item.TerraformBoatItemHelper;
import de.cristelknight.doapi.terraform.sign.TerraformSignHelper;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.level.block.Block;
import satisfyu.herbalbrews.util.HerbalBrewsIdentifier;

public class BoatsAndSignsRegistry {

    public static ResourceLocation LARCH_BOAT_TYPE = new HerbalBrewsIdentifier("larch");
    public static ResourceLocation SWAMP_OAK_BOAT_TYPE = new HerbalBrewsIdentifier("swamp_oak");

    public static final ResourceLocation LARCH_SIGN_TEXTURE = new HerbalBrewsIdentifier("entity/signs/larch");
    public static final ResourceLocation SWAMP_OAK_SIGN_TEXTURE = new HerbalBrewsIdentifier("entity/signs/swamp_oak");

    public static final RegistrySupplier<Block> LARCH_SIGN = ObjectRegistry.registerWithoutItem("larch_sign", () -> TerraformSignHelper.getSign(LARCH_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> LARCH_WALL_SIGN = ObjectRegistry.registerWithoutItem("larch_wall_sign", () -> TerraformSignHelper.getWallSign(LARCH_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> LARCH_SIGN_ITEM = ObjectRegistry.registerItem("larch_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), LARCH_SIGN.get(), LARCH_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> SWAMP_OAK_SIGN = ObjectRegistry.registerWithoutItem("swamp_oak_sign", () -> TerraformSignHelper.getSign(SWAMP_OAK_SIGN_TEXTURE));
    public static final RegistrySupplier<Block> SWAMP_OAK_WALL_SIGN = ObjectRegistry.registerWithoutItem("swamp_oak_wall_sign", () -> TerraformSignHelper.getWallSign(SWAMP_OAK_SIGN_TEXTURE));
    public static final RegistrySupplier<Item> SWAMP_OAK_SIGN_ITEM = ObjectRegistry.registerItem("swamp_oak_sign", () -> new SignItem(ObjectRegistry.getSettings().stacksTo(16), SWAMP_OAK_SIGN.get(), SWAMP_OAK_WALL_SIGN.get()));

    public static final ResourceLocation LARCH_HANGING_SIGN_TEXTURE = new HerbalBrewsIdentifier("entity/signs/hanging/larch");
    public static final ResourceLocation LARCH_HANGING_SIGN_GUI_TEXTURE = new HerbalBrewsIdentifier("textures/gui/hanging_signs/larch");
    public static final ResourceLocation SWAMP_OAK_HANGING_SIGN_TEXTURE = new HerbalBrewsIdentifier("entity/signs/hanging/swamp_oak");
    public static final ResourceLocation SWAMP_OAK_HANGING_SIGN_GUI_TEXTURE = new HerbalBrewsIdentifier("textures/gui/hanging_signs/swamp_oak");

    public static final RegistrySupplier<Block> LARCH_HANGING_SIGN = ObjectRegistry.registerWithoutItem("larch_hanging_sign", () -> TerraformSignHelper.getHangingSign(LARCH_HANGING_SIGN_TEXTURE, LARCH_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> LARCH_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("larch_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(LARCH_HANGING_SIGN_TEXTURE, LARCH_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> LARCH_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("larch_hanging_sign", () -> new HangingSignItem(LARCH_HANGING_SIGN.get(), LARCH_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));
    public static final RegistrySupplier<Block> SWAMP_OAK_HANGING_SIGN = ObjectRegistry.registerWithoutItem("swamp_oak_hanging_sign", () -> TerraformSignHelper.getHangingSign(SWAMP_OAK_HANGING_SIGN_TEXTURE, SWAMP_OAK_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Block> SWAMP_OAK_WALL_HANGING_SIGN = ObjectRegistry.registerWithoutItem("swamp_oak_wall_hanging_sign", () -> TerraformSignHelper.getWallHangingSign(SWAMP_OAK_HANGING_SIGN_TEXTURE, SWAMP_OAK_HANGING_SIGN_GUI_TEXTURE));
    public static final RegistrySupplier<Item> SWAMP_OAK_HANGING_SIGN_ITEM = ObjectRegistry.registerItem("swamp_oak_hanging_sign", () -> new HangingSignItem(SWAMP_OAK_HANGING_SIGN.get(), SWAMP_OAK_WALL_HANGING_SIGN.get(), ObjectRegistry.getSettings().stacksTo(16)));

    public static RegistrySupplier<Item> LARCH_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "larch_boat", LARCH_BOAT_TYPE, false);
    public static RegistrySupplier<Item> LARCH_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "larch_chest_boat", LARCH_BOAT_TYPE, true);
    public static RegistrySupplier<Item> SWAMP_OAK_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "swamp_oak_boat", SWAMP_OAK_BOAT_TYPE, false);
    public static RegistrySupplier<Item> SWAMP_OAK_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "swamp_oak_chest_boat", SWAMP_OAK_BOAT_TYPE, true);


    public static void init() {
        DoApiExpectPlatform.registerBoatType(LARCH_BOAT_TYPE, new TerraformBoatType.Builder().item(LARCH_BOAT).chestItem(LARCH_CHEST_BOAT).build());
    }
}
