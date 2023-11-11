package satisfyu.herbalbrews.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.herbalbrews.registry.ObjectRegistry;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum TeaKettleRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    SWEET(new ItemStack(Items.SUGAR)),
    MIXTURES(new ItemStack(ObjectRegistry.TEA_KETTLE.get()));

    public static final List<IRecipeBookGroup> POT_GROUPS = ImmutableList.of(SEARCH, SWEET, MIXTURES);

    private final List<ItemStack> icons;

    TeaKettleRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<? extends Container> recipe, RegistryAccess registryAccess) {
        return switch (this) {
            case SEARCH -> true;
            case SWEET ->
                    recipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(Items.SUGAR.getDefaultInstance()));
            case MIXTURES ->
                    recipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(ObjectRegistry.GREEN_TEA_LEAF.get().getDefaultInstance()));
        };
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}
