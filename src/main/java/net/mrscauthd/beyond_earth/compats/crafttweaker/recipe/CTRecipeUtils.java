package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.bracket.custom.RecipeTypeBracketHandler;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.data.base.visitor.DataToJsonStringVisitor;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

public class CTRecipeUtils {

	private static final Gson JSON_RECIPE_GSON = new GsonBuilder().create();

	public static void addRecipe(Recipe<?> recipe) {
		RecipeType<?> recipeType = recipe.getType();
		IRecipeManager<Recipe<?>> recipeManager = RecipeTypeBracketHandler.getOrDefault(recipeType);
		CraftTweakerAPI.apply(new ActionAddRecipe<>(recipeManager, recipe, ""));
	}

	public static Recipe<?> parseRecipe(ResourceLocation id, MapData mapData) {
		JsonObject recipeObject = JSON_RECIPE_GSON.fromJson(mapData.accept(DataToJsonStringVisitor.INSTANCE), JsonObject.class);
		Recipe<?> recipe = parseRecipe(id, recipeObject);
		return recipe;
	}

	public static Recipe<?> parseRecipe(ResourceLocation id, JsonObject json) {
		Recipe<?> recipe = RecipeManager.fromJson(id, json, IContext.EMPTY);
		return recipe;
	}

}
