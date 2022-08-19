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
public class CTOxygenBubbleDistributingRecipeManager extends CTRecipeManagerOxygen<OxygenBubbleDistributorRecipe> {

	public static final CTOxygenBubbleDistributingRecipeManager INSTANCE = new CTOxygenBubbleDistributingRecipeManager();

	public CTOxygenBubbleDistributingRecipeManager() {
		super(OxygenBubbleDistributorRecipe::new);
	}

	@ZenCodeType.Method
	public void addRecipePair(String name, CommandStringDisplayable fluidInput, int loadingOxygen) {
		this.addRecipePair(name, fluidInput, loadingOxygen, loadingOxygen * 4);
	}

	@ZenCodeType.Method
	public void addRecipePair(String name, CommandStringDisplayable fluidInput, int loadingOxygen, int bubbleOxygen) {
		CTOxygenLoadingRecipeManager.INSTANCE.addRecipe(name + "/loading", fluidInput, loadingOxygen);
		CTOxygenBubbleDistributingRecipeManager.INSTANCE.addRecipe(name + "/bubble_distributing", fluidInput, bubbleOxygen);
	}

	@Override
	public RecipeType<OxygenBubbleDistributorRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.OXYGEN_BUBBLE_DISTRIBUTING;
	}

}
