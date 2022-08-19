package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTUtils;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.FuelRefiningRecipe;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_FUEL_REFINING)
@IRecipeHandler.For(FuelRefiningRecipe.class)
public class CTFuelRefiningRecipeManager extends CTRecipeManager<FuelRefiningRecipe> {

	public static final CTFuelRefiningRecipeManager INSTANCE = new CTFuelRefiningRecipeManager();

	@ZenCodeType.Method
	public void addRecipe(String name, CommandStringDisplayable fluidInput, IFluidStack fluidOutput) {
		this.addRecipe(new FuelRefiningRecipe(this.getId(name), CTUtils.toFluidIngredient(fluidInput), CTUtils.toFluidIngredient(fluidOutput.asFluidIngredient())));
	}

	@Override
	public RecipeType<FuelRefiningRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.FUEL_REFINING;
	}

	@Override
	public String dumpToCommandString(@SuppressWarnings("rawtypes") IRecipeManager manager, FuelRefiningRecipe recipe) {
		return this.buildCommandString(manager, recipe, recipe.getInput(), recipe.getOutput());
	}

}
