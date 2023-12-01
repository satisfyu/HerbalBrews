package satisfyu.herbalbrews.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import satisfyu.herbalbrews.recipe.CauldronRecipe;
import satisfyu.herbalbrews.registry.ObjectRegistry;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum CauldronRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    POTION(new ItemStack(Items.POTION)),
    FLASKS(new ItemStack(ObjectRegistry.CAULDRON.get()));

    public static final List<IRecipeBookGroup> CAULDRON_GROUPS = ImmutableList.of(SEARCH, POTION, FLASKS);

    private final List<ItemStack> icons;

    CauldronRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<?> recipe, RegistryAccess registryAccess) {
        if (recipe instanceof CauldronRecipe cauldronRecipe) {
            switch (this) {
                case SEARCH -> {
                    return true;
                }
                case POTION -> {
                    if (cauldronRecipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultInstance()))) {
                        return true;
                    }
                }
                case FLASKS -> {
                    if (cauldronRecipe.getIngredients().stream().noneMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultInstance()))) {
                        return true;
                    }
                }
                default -> {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}
