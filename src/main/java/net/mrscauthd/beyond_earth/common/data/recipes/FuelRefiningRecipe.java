package net.mrscauthd.beyond_earth.common.data.recipes;

import java.util.function.Predicate;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;
import net.mrscauthd.beyond_earth.common.registries.RecipeSerializersRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;

public class FuelRefiningRecipe extends BeyondEarthRecipe implements Predicate<FluidStack>{
	private final FluidIngredient input;
	private final FluidIngredient output;

	public FuelRefiningRecipe(ResourceLocation id, JsonObject json) {
		super(id, json);

		this.input = FluidIngredient.deserialize(GsonHelper.getAsJsonObject(json, "input"));
		this.output = FluidIngredient.deserialize(GsonHelper.getAsJsonObject(json, "output"));
	}

	public FuelRefiningRecipe(ResourceLocation id, FriendlyByteBuf buffer) {
		super(id, buffer);

		this.input = FluidIngredient.read(buffer);
		this.output = FluidIngredient.read(buffer);
	}

	public FuelRefiningRecipe(ResourceLocation id, FluidIngredient input, FluidIngredient output) {
		super(id);

		this.input = input;
		this.output = output;
	}
	
	@Override
	public void write(FriendlyByteBuf buffer) {
		super.write(buffer);

		this.input.write(buffer);
		this.output.write(buffer);
	}

	@Override
	public boolean test(FluidStack stack) {
		return this.input.test(stack);
	}
	
	@Override
	public boolean canCraftInDimensions(int var1, int var2) {
		return false;
	}

	public FluidIngredient getInput() {
		return this.input;
	}

	public FluidIngredient getOutput() {
		return this.output;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return RecipeSerializersRegistry.RECIPE_SERIALIZER_FUEL_REFINERY.get();
	}

	@Override
	public RecipeType<?> getType() {
		return RecipeTypeRegistry.FUEL_REFINING.get();
	}

}
