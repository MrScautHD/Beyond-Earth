package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.crafting.ItemStackToItemStackRecipe;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_IS2IS)
public abstract class CTRecipeManagerItemStackToItemStack<T extends ItemStackToItemStackRecipe> extends CTRecipeManager<T> {

	private final ItemStackToItemStackRecipeConstructor<T> constructor;

	public CTRecipeManagerItemStackToItemStack(ItemStackToItemStackRecipeConstructor<T> constructor) {
		this.constructor = constructor;
	}

	public int getDefaultCookTime() {
		return 200;
	}

	@ZenCodeType.Method
	public void addRecipe(String name, IItemStack output, IIngredient input) {
		this.addRecipe(name, output, input, this.getDefaultCookTime());
	}

	@ZenCodeType.Method
	public void addRecipe(String name, IItemStack output, IIngredient input, int cookTime) {
		ResourceLocation id = this.getId(name);
		T recipe = this.getConstructor().construct(id, input.asVanillaIngredient(), output.getImmutableInternal(), cookTime);
		this.addRecipe(recipe);
	}

	@Override
	public String dumpToCommandString(@SuppressWarnings("rawtypes") IRecipeManager manager, T recipe) {
		return this.buildCommandString(manager, recipe, recipe.getInput(), recipe.getOutput(), recipe.getCookTime());
	}

	public ItemStackToItemStackRecipeConstructor<T> getConstructor() {
		return this.constructor;
	}

	public interface ItemStackToItemStackRecipeConstructor<T> {
		public T construct(ResourceLocation id, Ingredient ingredient, ItemStack output, int cookTime);
	}
}
