package net.mrscauthd.beyond_earth.common.data.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.common.registries.RecipeSerializersRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;

public class RocketUpgraderRecipe extends OxygenMakingRecipeAbstract {

	public RocketUpgraderRecipe(ResourceLocation id, JsonObject json) {
		super(id, json);
	}

	public RocketUpgraderRecipe(ResourceLocation id, FriendlyByteBuf buffer) {
		super(id, buffer);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return RecipeSerializersRegistry.RECIPE_SERIALIZER_ROCKET_UPGRADER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return RecipeTypeRegistry.ROCKET_UPGRADER.get();
	}

}
