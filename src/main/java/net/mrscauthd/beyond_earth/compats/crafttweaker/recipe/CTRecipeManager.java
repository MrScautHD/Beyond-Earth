package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import java.util.List;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.fluid.MCFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.MCItemStack;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTUtils;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CraftTweakerCompat;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipe;
import net.mrscauthd.beyond_earth.crafting.FluidIngredient;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER)
public abstract class CTRecipeManager<T extends BeyondEarthRecipe> implements IRecipeManager<T>, IRecipeHandler<T> {

	public CTRecipeManager() {

	}

	public void addRecipe(T recipe) {
		CraftTweakerAPI.apply(new ActionAddRecipe<T>(this, recipe));
	}

	public ResourceLocation getId(String name) {
		return CraftTweakerCompat.rl(this.fixRecipeName(name));
	}

	protected String buildCommandString(IRecipeManager<?> manager, T recipe, Object... values) {
		return buildCommandString(manager, "addRecipe", recipe, values);
	}

	protected String buildCommandString(IRecipeManager<?> manager, String method, T recipe, Object... values) {
		StringBuilder builder = new StringBuilder(manager.getCommandString()).append('.').append(method).append("(\"").append(recipe.getId().getPath()).append('"');

		for (Object value : values) {
			builder.append(", ").append(this.toStringValue(value));
		}

		return builder.append(");").toString();
	}

	protected String toStringValue(Object value) {
		if (value instanceof List<?> list) {
			StringBuilder builder = new StringBuilder("[");

			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					builder.append(", ");
				}

				Object child = list.get(i);
				builder.append(this.toStringValue(child));
			}
			builder.append("]");
			return builder.toString();

		} else if (value instanceof ItemStack itemStack) {
			return this.toStringValue(new MCItemStack(itemStack));

		} else if (value instanceof Ingredient itemIngredient) {
			return this.toStringValue(IIngredient.fromIngredient(itemIngredient));

		} else if (value instanceof FluidStack fluidStack) {
			return this.toStringValue(new MCFluidStack(fluidStack));

		} else if (value instanceof FluidIngredient fluidIngredient) {
			return this.toStringValue(CTUtils.toFluidIngredient(fluidIngredient));

		} else if (value instanceof CommandStringDisplayable commandStringDisplayable) {
			return commandStringDisplayable.getCommandString();

		} else {
			return String.valueOf(value);
		}
	}
}
