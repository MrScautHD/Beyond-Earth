package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;

import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.CompressingRecipe;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_COMPRESSING)
@IRecipeHandler.For(CompressingRecipe.class)
public class CTCompressingRecipeManager extends CTRecipeManagerItemStackToItemStack<CompressingRecipe> {

	public static final CTCompressingRecipeManager INSTANCE = new CTCompressingRecipeManager();

	private CTCompressingRecipeManager() {
		super(CompressingRecipe::new);
	}

	@Override
	public RecipeType<CompressingRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.COMPRESSING;
	}

}
