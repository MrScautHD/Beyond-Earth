package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTUtils;
import net.mrscauthd.beyond_earth.crafting.FluidIngredient;
import net.mrscauthd.beyond_earth.crafting.OxygenMakingRecipeAbstract;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_OXYGEN)
public abstract class CTRecipeManagerOxygen<T extends OxygenMakingRecipeAbstract> extends CTRecipeManager<T> {

	private final OxygenMakingRecipeConstructor<T> constructor;

	public CTRecipeManagerOxygen(OxygenMakingRecipeConstructor<T> constructor) {
		this.constructor = constructor;
	}

	@ZenCodeType.Method
	public void addRecipe(String name, int oxygenOutput, CommandStringDisplayable fluidInput) {
		ResourceLocation id = this.getId(name);
		T recipe = this.getConstructor().construct(id, CTUtils.toFluidIngredient(fluidInput), oxygenOutput);
		this.addRecipe(recipe);
	}

	@Override
	public String dumpToCommandString(@SuppressWarnings("rawtypes") IRecipeManager manager, T recipe) {
		return this.buildCommandString(manager, recipe, recipe.getInput(), recipe.getOxygen());
	}

	public OxygenMakingRecipeConstructor<T> getConstructor() {
		return this.constructor;
	}

	public interface OxygenMakingRecipeConstructor<T> {
		public T construct(ResourceLocation id, FluidIngredient input, int oxygen);
	}
}
