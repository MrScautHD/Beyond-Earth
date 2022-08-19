package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;

import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.OxygenBubbleDistributorRecipe;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_OXYGEN_BUBBLE_DISTRIBUTING)
@IRecipeHandler.For(OxygenBubbleDistributorRecipe.class)
public class CTOxygenBubbleDistributingRecipeManager extends CTOxygenMakingRecipeManager<OxygenBubbleDistributorRecipe> {

	public static final CTOxygenBubbleDistributingRecipeManager INSTANCE = new CTOxygenBubbleDistributingRecipeManager();

	public CTOxygenBubbleDistributingRecipeManager() {
		super(OxygenBubbleDistributorRecipe::new);
	}

	@ZenCodeType.Method
	public void addRecipePair(String name, int loadingOxygen, CommandStringDisplayable fluidInput) {
		this.addRecipePair(name, loadingOxygen, loadingOxygen * 4, fluidInput);
	}

	@ZenCodeType.Method
	public void addRecipePair(String name, int loadingOxygen, int bubbleOxygen, CommandStringDisplayable fluidInput) {
		CTOxygenLoadingRecipeManager.INSTANCE.addRecipe(name + "/loading", loadingOxygen, fluidInput);
		CTOxygenBubbleDistributingRecipeManager.INSTANCE.addRecipe(name + "/bubble_distributing", bubbleOxygen, fluidInput);
	}

	@Override
	public RecipeType<OxygenBubbleDistributorRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.OXYGEN_BUBBLE_DISTRIBUTING;
	}

}
