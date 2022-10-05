package net.mrscauthd.beyond_earth.common.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.crafting.CompressingRecipe;
import net.mrscauthd.beyond_earth.common.crafting.FuelRefiningRecipe;
import net.mrscauthd.beyond_earth.common.crafting.GeneratingRecipe;
import net.mrscauthd.beyond_earth.common.jei.categories.CoalGenerator;
import net.mrscauthd.beyond_earth.common.jei.categories.Compressor;
import net.mrscauthd.beyond_earth.common.jei.categories.FuelRefining;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;

@JeiPlugin
public class Jei implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(BeyondEarth.MODID, "jei");

    public static final RecipeType<GeneratingRecipe> COAL_TYPE;
    public static final RecipeType<CompressingRecipe> COMPRESS_TYPE;
    public static final RecipeType<FuelRefiningRecipe> REFINE_TYPE;

    static {
        COAL_TYPE = RecipeType.create(BeyondEarth.MODID, "coal_generator", GeneratingRecipe.class);
        COMPRESS_TYPE = RecipeType.create(BeyondEarth.MODID, "compressor", CompressingRecipe.class);
        REFINE_TYPE = RecipeType.create(BeyondEarth.MODID, "fuel_refining", FuelRefiningRecipe.class);
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
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(COAL_TYPE,
                RecipeTypeRegistry.COAL_GENERATING.get().getRecipes(Minecraft.getInstance().level));
        registration.addRecipes(COMPRESS_TYPE,
                RecipeTypeRegistry.COMPRESSING.get().getRecipes(Minecraft.getInstance().level));
        registration.addRecipes(REFINE_TYPE,
                RecipeTypeRegistry.FUEL_REFINING.get().getRecipes(Minecraft.getInstance().level));
    }
}
