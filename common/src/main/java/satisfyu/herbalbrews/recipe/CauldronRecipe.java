package satisfyu.herbalbrews.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import satisfyu.herbalbrews.registry.RecipeTypeRegistry;
import satisfyu.herbalbrews.util.GeneralUtil;

public class CauldronRecipe implements Recipe<Container> {

    final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public CauldronRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        return GeneralUtil.matchesRecipe(inventory, inputs, 1, 4);
    }

    @Override
    public ItemStack assemble(Container inventory, RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.CAULDRON_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.CAULDRON_RECIPE_TYPE.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<CauldronRecipe> {

        @Override
        public CauldronRecipe fromJson(ResourceLocation id, JsonObject json) {
            final var ingredients = GeneralUtil.deserializeIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for Cauldron Recipe");
            } else if (ingredients.size() > 4) {
                throw new JsonParseException("Too many ingredients for Cauldron Recipe");
            } else {
                return new CauldronRecipe(id, ingredients, ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result")));
            }
        }

        @Override
        public CauldronRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            final var ingredients  = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            return new CauldronRecipe(id, ingredients, buf.readItem());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CauldronRecipe recipe) {
            buf.writeVarInt(recipe.inputs.size());
            for (Ingredient ingredient : recipe.inputs) {
                ingredient.toNetwork(buf);
            }

            buf.writeItem(recipe.output);
        }
    }
}