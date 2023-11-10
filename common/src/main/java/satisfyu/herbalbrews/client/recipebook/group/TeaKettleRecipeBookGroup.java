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
    JAM(new ItemStack(ObjectRegistry.TEA_KETTLE.get())),
    CHOCOLATE(new ItemStack(ObjectRegistry.TEA_KETTLE.get()));

    public static final List<IRecipeBookGroup> POT_GROUPS = ImmutableList.of(SEARCH, JAM, CHOCOLATE);

    private final List<ItemStack> icons;

    TeaKettleRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<? extends Container> recipe, RegistryAccess registryAccess) {
        return switch (this) {
            case SEARCH -> true;
            case JAM ->
                    recipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(ObjectRegistry.TEA_KETTLE.get().asItem().getDefaultInstance()));
            case CHOCOLATE ->
                    recipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(Items.MILK_BUCKET.getDefaultInstance()));
        };
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}
