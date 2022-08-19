package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTUtils;
import net.mrscauthd.beyond_earth.compats.crafttweaker.bracket.CTBracketHandlers;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.RocketPart;
import net.mrscauthd.beyond_earth.crafting.WorkbenchingRecipe;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_NASA_WORKBENCHING)
@IRecipeHandler.For(WorkbenchingRecipe.class)
public class CTNASAWorkbenchingRecipeManager extends CTRecipeManager<WorkbenchingRecipe> {

	public static final CTNASAWorkbenchingRecipeManager INSTANCE = new CTNASAWorkbenchingRecipeManager();

	@ZenCodeType.Method
	public void addRecipe(String name, IItemStack output, Map<RocketPart, CommandStringDisplayable[]> map) {
		Map<RocketPart, List<Ingredient>> parts = new HashMap<>();

		for (Entry<RocketPart, CommandStringDisplayable[]> entry : map.entrySet()) {
			List<Ingredient> ingredients = new ArrayList<>();

			for (CommandStringDisplayable ingredient : entry.getValue()) {
				ingredients.add(CTUtils.toItemIngredientStack(ingredient).getIngredient());
			}

			parts.put(entry.getKey(), ingredients);
		}

		this.addRecipe(new WorkbenchingRecipe(this.getId(name), parts, output.getImmutableInternal()));
	}

	@Override
	public RecipeType<WorkbenchingRecipe> getRecipeType() {
		return BeyondEarthRecipeTypes.NASA_WORKBENCHING;
	}

	@Override
	public String dumpToCommandString(@SuppressWarnings("rawtypes") IRecipeManager manager, WorkbenchingRecipe recipe) {
		List<String> values = new ArrayList<>();
		values.add(this.toStringValue(recipe.getOutput()));
		values.add(this.toStringValues(recipe.getParts().entrySet().stream().toList(), "{", "}", this::toStringEntry));

		return this.buildCommandString(manager, recipe, values);
	}

	private String toStringEntry(Entry<RocketPart, List<Ingredient>> entry) {
		return new StringBuilder().append(CTBracketHandlers.toString(entry.getKey())).append(": ").append(this.toStringValue(entry.getValue())).toString();
	}

}
