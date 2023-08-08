package net.mrscauthd.beyond_earth.common.data.recipes;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class BeyondEarthRecipe implements Recipe<Container> {

	private final ResourceLocation id;

	public BeyondEarthRecipe(ResourceLocation id, JsonObject json) {
		this.id = id;
	}

	public BeyondEarthRecipe(ResourceLocation id, FriendlyByteBuf buffer) {
		this.id = id;
	}

	public BeyondEarthRecipe(ResourceLocation id) {
		this.id = id;
	}

	public void write(FriendlyByteBuf buffer) {

	}

	@Override
	public final ResourceLocation getId() {
		return this.id;
	}
	
	@Override
	public String getGroup() {
		return ForgeRegistries.RECIPE_SERIALIZERS.getKey(this.getSerializer()).toString();
	}
}
