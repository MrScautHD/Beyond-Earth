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
public class CTOxygenLoadingRecipeManager extends CTOxygenMakingRecipeManager<OxygenLoaderRecipe> {

	public static final CTOxygenLoadingRecipeManager INSTANCE = new CTOxygenLoadingRecipeManager();

	public CTOxygenLoadingRecipeManager() {
		super(OxygenLoaderRecipe::new);
	}

	@ZenCodeType.Method
	public void addRecipePair(String name, int loadingOxygen, CommandStringDisplayable fluidInput) {
		CTOxygenBubbleDistributingRecipeManager.INSTANCE.addRecipePair(name, loadingOxygen, fluidInput);
	}

	@ZenCodeType.Method
	public void addRecipePair(String name, int loadingOxygen, int bubbleOxygen, CommandStringDisplayable fluidInput) {
		CTOxygenBubbleDistributingRecipeManager.INSTANCE.addRecipePair(name, loadingOxygen, bubbleOxygen, fluidInput);
	}

	@Override
	public RecipeType<OxygenLoaderRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.OXYGEN_LOADING;
	}

}
