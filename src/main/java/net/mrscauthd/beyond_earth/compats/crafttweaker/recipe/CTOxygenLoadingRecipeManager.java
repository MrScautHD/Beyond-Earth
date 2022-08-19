package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;

import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.OxygenLoaderRecipe;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_OXYGEN_LOADING)
@IRecipeHandler.For(OxygenLoaderRecipe.class)
public class CTOxygenLoadingRecipeManager extends CTRecipeManagerOxygen<OxygenLoaderRecipe> {

	public static final CTOxygenLoadingRecipeManager INSTANCE = new CTOxygenLoadingRecipeManager();

	public CTOxygenLoadingRecipeManager() {
		super(OxygenLoaderRecipe::new);
	}

	@ZenCodeType.Method
	public void addRecipePair(String name, CommandStringDisplayable fluidInput, int loadingOxygen) {
		CTOxygenBubbleDistributingRecipeManager.INSTANCE.addRecipePair(name, fluidInput, loadingOxygen);
	}

	@ZenCodeType.Method
	public void addRecipePair(String name, CommandStringDisplayable fluidInput, int loadingOxygen, int bubbleOxygen) {
		CTOxygenBubbleDistributingRecipeManager.INSTANCE.addRecipePair(name, fluidInput, loadingOxygen, bubbleOxygen);
	}

	@Override
	public RecipeType<OxygenLoaderRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.OXYGEN_LOADING;
	}

}
