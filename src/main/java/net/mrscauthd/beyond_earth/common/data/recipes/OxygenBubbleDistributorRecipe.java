package net.mrscauthd.beyond_earth.common.data.recipes;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.common.registries.RecipeSerializersRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;

public class OxygenBubbleDistributorRecipe extends OxygenMakingRecipeAbstract {

	public OxygenBubbleDistributorRecipe(ResourceLocation id, JsonObject json) {
		super(id, json);
	}

	public OxygenBubbleDistributorRecipe(ResourceLocation id, FriendlyByteBuf buffer) {
		super(id, buffer);
	}

	public OxygenBubbleDistributorRecipe(ResourceLocation id, FluidIngredient ingredient, int oxygen) {
		super(id, ingredient, oxygen);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return RecipeSerializersRegistry.RECIPE_SERIALIZER_OXYGEN_BUBBLE_DISTRIBUTOR.get();
	}

	@Override
	public RecipeType<?> getType() {
		return RecipeTypeRegistry.OXYGEN_BUBBLE_DISTRIBUTING.get();
	}

}
