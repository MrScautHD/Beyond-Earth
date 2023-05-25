package net.mrscauthd.beyond_earth.common.data.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class RocketUpgraderRecipeSerializer extends BeyondEarthRecipeSerializer<RocketUpgraderRecipe> {

	@Override
	public RocketUpgraderRecipe fromJson(ResourceLocation id, JsonObject json) {
		return new RocketUpgraderRecipe(id, json);
	}

	@Override
	public RocketUpgraderRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
		return new RocketUpgraderRecipe(id, buffer);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, RocketUpgraderRecipe recipe) {
		recipe.write(buffer);
	}

}
