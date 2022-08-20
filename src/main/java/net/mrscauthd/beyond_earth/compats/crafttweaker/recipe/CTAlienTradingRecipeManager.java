package net.mrscauthd.beyond_earth.compats.crafttweaker.recipe;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.openzen.zencode.java.ZenCodeGlobals;
import org.openzen.zencode.java.ZenCodeType;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.custom.RecipeTypeBracketHandler;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.compats.crafttweaker.CTConstants;
import net.mrscauthd.beyond_earth.crafting.AlienTradingRecipe;
import net.mrscauthd.beyond_earth.crafting.AlienTradingRecipeType;

@ZenRegister
@ZenCodeType.Name(CTConstants.RECIPE_MANAGER_ALIEN_TRADING)
public class CTAlienTradingRecipeManager {

	@ZenCodeGlobals.Global(BeyondEarthMod.MODID + "_alien_trading")
	public static final CTAlienTradingRecipeManager INSTANCE = new CTAlienTradingRecipeManager();

	@ZenCodeType.Method
	public void addJsonRecipe(String name, MapData mapData) {
		Recipe<?> recipe = CTRecipeUtils.parseRecipe(new ResourceLocation(CraftTweakerConstants.MOD_ID, name), mapData);
		CTRecipeUtils.addRecipe(recipe);
	}

	@ZenCodeType.Method
	public void removeAll() {
		this.foreach(IRecipeManager::removeAll);
	}

	@ZenCodeType.Method
	public void removeByJob(VillagerProfession job) {
		this.foreach(recipeManager -> CraftTweakerAPI.apply(new ActionRemoveRecipe<>(recipeManager, r -> r.getJob() == job)));
	}

	@ZenCodeType.Method
	public void removeByJob(String job) {
		this.removeByJob(ForgeRegistries.PROFESSIONS.getValue(new ResourceLocation(job)));
	}

	@ZenCodeType.Method
	public void removeByJobWithLevel(VillagerProfession job, int level) {
		this.foreach(recipeManager -> CraftTweakerAPI.apply(new ActionRemoveRecipe<>(recipeManager, r -> r.getJob() == job && r.getLevel() == level)));
	}

	@ZenCodeType.Method
	public void removeByJobWithLevel(String job, int level) {
		this.removeByJobWithLevel(ForgeRegistries.PROFESSIONS.getValue(new ResourceLocation(job)), level);
	}

	@ZenCodeType.Method
	public void remove(IIngredient output) {
		this.foreach(recipeManager -> recipeManager.remove(output));
	}

	@ZenCodeType.Method
	public void removeByInput(IItemStack input) {
		this.foreach(recipeManager -> recipeManager.removeByInput(input));
	}

	@ZenCodeType.Method
	public void removeByModid(String modid, @ZenCodeType.Optional("(name as string) as bool => false") Predicate<String> exclude) {
		this.foreach(recipeManager -> recipeManager.removeByModid(modid, exclude));
	}

	@ZenCodeType.Method
	public void removeByName(String... names) {
		this.foreach(recipeManager -> recipeManager.removeByName(names));
	}

	@ZenCodeType.Method
	public void removeByRegex(String regex, @ZenCodeType.Optional("(name as string) as bool => false") Predicate<String> exclude) {
		this.foreach(recipeManager -> recipeManager.removeByRegex(regex, exclude));
	}

	@SuppressWarnings("unchecked")
	public <T extends AlienTradingRecipe> void foreach(Consumer<IRecipeManager<T>> consumer) {
		for (AlienTradingRecipeType<?> recipeType : AlienTradingRecipeType.getTypes()) {
			IRecipeManager<?> recipeManager = RecipeTypeBracketHandler.getOrDefault(recipeType);
			consumer.accept((IRecipeManager<T>) recipeManager);
		}
	}

}
