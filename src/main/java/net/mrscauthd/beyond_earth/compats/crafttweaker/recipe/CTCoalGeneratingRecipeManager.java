package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.GeneratingRecipe;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_COAL_GENERATING)
@IRecipeHandler.For(GeneratingRecipe.class)
public class CTCoalGeneratingRecipeManager extends CTRecipeManager<GeneratingRecipe> {

	public static final CTCoalGeneratingRecipeManager INSTANCE = new CTCoalGeneratingRecipeManager();

	@ZenCodeType.Method
	public void addRecipe(String name, IIngredient ingredient, int burnTime) {
		this.addRecipe(new GeneratingRecipe(this.getId(name), ingredient.asVanillaIngredient(), burnTime));
	}

	@Override
	public RecipeType<GeneratingRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.COAL_GENERATING;
	}

	@Override
	public String dumpToCommandString(@SuppressWarnings("rawtypes") IRecipeManager manager, GeneratingRecipe recipe) {
		return this.buildCommandString(manager, recipe, recipe.getInput(), recipe.getBurnTime());
	}

}
