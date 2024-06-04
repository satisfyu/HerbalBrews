package satisfy.herbalbrews.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import satisfy.herbalbrews.recipe.TeaKettleRecipe;
import satisfy.herbalbrews.registry.ObjectRegistry;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum TeaKettleRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    FLASKS(new ItemStack(ObjectRegistry.FERAL_FLASK.get()));

    public static final List<IRecipeBookGroup> TEAKETTLE_GROUPS = ImmutableList.of(SEARCH, FLASKS);

    private final List<ItemStack> icons;

    TeaKettleRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<?> recipe, RegistryAccess registryAccess) {
        if (recipe instanceof TeaKettleRecipe teaKettleRecipe) {
            switch (this) {
                case SEARCH -> {
                    return true;
                }
                case FLASKS -> {
                    if (teaKettleRecipe.getIngredients().stream().noneMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultInstance()))) {
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
