package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.data.StringData;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTUtils;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.IngredientStack;
import net.mrscauthd.beyond_earth.crafting.SpaceStationRecipe;
import net.mrscauthd.beyond_earth.registries.RecipeSerializersRegistry;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_SPACE_STATION)
@IRecipeHandler.For(SpaceStationRecipe.class)
public class CTSpaceStationRecipeManager extends CTRecipeManager<SpaceStationRecipe> {

	public static final CTSpaceStationRecipeManager INSTANCE = new CTSpaceStationRecipeManager();

	@Override
	public RecipeType<SpaceStationRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.SPACE_STATION;
	}

	@ZenCodeType.Method
	public void setJsonRecipe(MapData mapData) {
		mapData.put("type", new StringData(RecipeSerializersRegistry.RECIPE_SERIALIZER_SPACE_STATION.getId().toString()));
		Recipe<?> recipe = CTRecipeUtils.parseRecipe(SpaceStationRecipe.KEY, mapData);
		this.removeAll();
		CTRecipeUtils.addRecipe(recipe);
	}

	@ZenCodeType.Method
	public void setRecipe(CommandStringDisplayable... ingredients) {
		NonNullList<IngredientStack> ingredientStacks = NonNullList.withSize(ingredients.length, IngredientStack.EMPTY);

		for (int i = 0; i < ingredients.length; i++) {
			ingredientStacks.set(i, CTUtils.toItemIngredientStack(ingredients[i]));
		}

		this.removeAll();
		this.addRecipe(new SpaceStationRecipe(SpaceStationRecipe.KEY, ingredientStacks));
	}

	@Override
	public String dumpToCommandString(@SuppressWarnings("rawtypes") IRecipeManager manager, SpaceStationRecipe recipe) {
		return null;
	}

}