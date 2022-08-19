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

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTUtils;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.RocketPart;
import net.mrscauthd.beyond_earth.crafting.WorkbenchingRecipe;
import net.mrscauthd.beyond_earth.registries.RocketPartsRegistry;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_NASA_WORKBENCHING)
@IRecipeHandler.For(WorkbenchingRecipe.class)
public class CTNASAWorkbenchingRecipeManager extends CTRecipeManager<WorkbenchingRecipe> {

	public static final CTNASAWorkbenchingRecipeManager INSTANCE = new CTNASAWorkbenchingRecipeManager();

	@ZenCodeType.Method
	public void addRecipe(String name, IItemStack output, Map<String, CommandStringDisplayable[]> map) {
		Map<RocketPart, List<Ingredient>> parts = new HashMap<>();

		for (Entry<String, CommandStringDisplayable[]> entry : map.entrySet()) {
			RocketPart part = RocketPartsRegistry.ROCKET_PARTS_REGISTRY.get().getValue(new ResourceLocation(entry.getKey()));
			List<Ingredient> ingredients = new ArrayList<>();

			for (CommandStringDisplayable ingredient : entry.getValue()) {
				ingredients.add(CTUtils.toItemIngredientStack(ingredient).getIngredient());
			}

			parts.put(part, ingredients);
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

		StringBuilder mapBuilder = new StringBuilder("{");

		List<Entry<RocketPart, List<Ingredient>>> entrySet = recipe.getParts().entrySet().stream().toList();

		for (int j = 0; j < entrySet.size(); j++) {
			if (j > 0) {
				mapBuilder.append(", \n");
			}

			Entry<RocketPart, List<Ingredient>> entry = entrySet.get(j);
			mapBuilder.append("\"").append(entry.getKey().getRegistryName().toString()).append("\": [");
			List<Ingredient> ingredients = entry.getValue();

			for (int i = 0; i < ingredients.size(); i++) {
				if (i > 0) {
					mapBuilder.append(", ");
				}

				values.add(this.toStringValue(ingredients.get(i)));
			}

			mapBuilder.append("]");
		}

		mapBuilder.append("}");
		values.add(mapBuilder.toString());
		return this.buildCommandString(manager, recipe, values);
	}

}
