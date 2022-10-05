package net.mrscauthd.beyond_earth.common.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.crafting.CompressingRecipe;
import net.mrscauthd.beyond_earth.common.crafting.FuelRefiningRecipe;
import net.mrscauthd.beyond_earth.common.crafting.GeneratingRecipe;
import net.mrscauthd.beyond_earth.common.crafting.OxygenBubbleDistributorRecipe;
import net.mrscauthd.beyond_earth.common.crafting.OxygenLoaderRecipe;
import net.mrscauthd.beyond_earth.common.crafting.WorkbenchingRecipe;
import net.mrscauthd.beyond_earth.common.jei.categories.CoalGenerator;
import net.mrscauthd.beyond_earth.common.jei.categories.Compressor;
import net.mrscauthd.beyond_earth.common.jei.categories.FuelRefining;
import net.mrscauthd.beyond_earth.common.jei.categories.NASAWorkbench;
import net.mrscauthd.beyond_earth.common.jei.categories.OxygenBubbleLoader;
import net.mrscauthd.beyond_earth.common.jei.categories.OxygenLoader;
import net.mrscauthd.beyond_earth.common.jei.helper.EnergyIngredient;
import net.mrscauthd.beyond_earth.common.jei.helper.O2Ingredient;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;

@JeiPlugin
public class Jei implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(BeyondEarth.MODID, "jei");

    public static final IIngredientType<O2Ingredient> O2_INGREDIENT_TYPE = () -> O2Ingredient.class;
    public static final IIngredientType<EnergyIngredient> FE_INGREDIENT_TYPE = () -> EnergyIngredient.class;

    public static final RecipeType<GeneratingRecipe> COAL_TYPE;
    public static final RecipeType<CompressingRecipe> COMPRESS_TYPE;
    public static final RecipeType<FuelRefiningRecipe> REFINE_TYPE;
    public static final RecipeType<OxygenLoaderRecipe> OXYGEN_LOADER_TYPE;
    public static final RecipeType<OxygenBubbleDistributorRecipe> OXYGEN_BUBBLE_TYPE;
    public static final RecipeType<WorkbenchingRecipe> WORKBENCH_TYPE;

    static {
        COAL_TYPE = RecipeType.create(BeyondEarth.MODID, "coal_generator", GeneratingRecipe.class);
        COMPRESS_TYPE = RecipeType.create(BeyondEarth.MODID, "compressor", CompressingRecipe.class);
        REFINE_TYPE = RecipeType.create(BeyondEarth.MODID, "fuel_refining", FuelRefiningRecipe.class);
        OXYGEN_LOADER_TYPE = RecipeType.create(BeyondEarth.MODID, "oxygen_loading", OxygenLoaderRecipe.class);
        OXYGEN_BUBBLE_TYPE = RecipeType.create(BeyondEarth.MODID, "oxygen_bubble_distributing",
                OxygenBubbleDistributorRecipe.class);
        WORKBENCH_TYPE = RecipeType.create(BeyondEarth.MODID, "nasa_workbenching", WorkbenchingRecipe.class);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        final IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new CoalGenerator(helper));
        registration.addRecipeCategories(new Compressor(helper));
        registration.addRecipeCategories(new FuelRefining(helper));
        registration.addRecipeCategories(new OxygenLoader(helper));
        registration.addRecipeCategories(new OxygenBubbleLoader(helper));
        registration.addRecipeCategories(new NASAWorkbench(helper));
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registration) {
        registration.register(O2_INGREDIENT_TYPE, O2Ingredient.getIngredients(), O2Ingredient.INSTANCE,
                new O2Ingredient.DummyRenderer());
        registration.register(FE_INGREDIENT_TYPE, EnergyIngredient.getIngredients(), EnergyIngredient.INSTANCE,
                new EnergyIngredient.DummyRenderer());
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(COAL_TYPE,
                RecipeTypeRegistry.COAL_GENERATING.get().getRecipes(Minecraft.getInstance().level));
        registration.addRecipes(COMPRESS_TYPE,
                RecipeTypeRegistry.COMPRESSING.get().getRecipes(Minecraft.getInstance().level));
        registration.addRecipes(REFINE_TYPE,
                RecipeTypeRegistry.FUEL_REFINING.get().getRecipes(Minecraft.getInstance().level));
        registration.addRecipes(OXYGEN_LOADER_TYPE,
                RecipeTypeRegistry.OXYGEN_LOADING.get().getRecipes(Minecraft.getInstance().level));
        registration.addRecipes(OXYGEN_BUBBLE_TYPE,
                RecipeTypeRegistry.OXYGEN_BUBBLE_DISTRIBUTING.get().getRecipes(Minecraft.getInstance().level));
        registration.addRecipes(WORKBENCH_TYPE,
                RecipeTypeRegistry.NASA_WORKBENCHING.get().getRecipes(Minecraft.getInstance().level));
    }
}
