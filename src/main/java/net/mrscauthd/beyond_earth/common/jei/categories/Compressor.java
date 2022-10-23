package net.mrscauthd.beyond_earth.common.jei.categories;

import java.util.List;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.common.Constants;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.data.recipes.CompressingRecipe;
import net.mrscauthd.beyond_earth.common.jei.Jei;
import net.mrscauthd.beyond_earth.common.jei.helper.EnergyIngredient;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class Compressor implements IRecipeCategory<CompressingRecipe> {
    public static final ResourceLocation GUI = new ResourceLocation(BeyondEarth.MODID, "textures/jei/jei_gui_1.png");

    public static final int width = 128;
    public static final int height = 64;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;
    final IGuiHelper guiHelper;

    private final LoadingCache<Integer, IDrawableAnimated> cachedArrow;

    public Compressor(final IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
        this.background = guiHelper.createDrawable(Compressor.GUI, 0, 64, Compressor.width, Compressor.height);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ItemsRegistry.COAL_GENERATOR_ITEM.get()));
        this.localizedName = I18n.get("container." + BeyondEarth.MODID + ".compressor");

        this.cachedArrow = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(Integer cookTime) {
                return guiHelper.drawableBuilder(Constants.RECIPE_GUI_VANILLA, 82, 128, 24, 17).buildAnimated(cookTime,
                        IDrawableAnimated.StartDirection.LEFT, false);
            }
        });
    }

    @Override
    public RecipeType<CompressingRecipe> getRecipeType() {
        return Jei.COMPRESS_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal(this.localizedName);
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public List<Component> getTooltipStrings(CompressingRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
            double mouseY) {
        return IRecipeCategory.super.getTooltipStrings(recipe, recipeSlotsView, mouseX, mouseY);
    }

    @Override
    public void draw(CompressingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX,
            double mouseY) {
        int compressTime = recipe.getCookTime();
        IDrawableAnimated arrow = cachedArrow.getUnchecked(compressTime);
        arrow.draw(stack, 38, 21);

        // Update the energy cost
        recipeSlotsView.getSlotViews(RecipeIngredientRole.INPUT).get(0).getIngredients(Jei.FE_INGREDIENT_TYPE)
                .forEach(i -> i.setAmount(Config.FUEL_REFINERY_ENERGY_USAGE.get()));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CompressingRecipe recipe, IFocusGroup focuses) {
        IRecipeSlotBuilder inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 15, 23);
        inputStack.addIngredients(recipe.getInput());

        inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 108, 9);
        inputStack.addIngredient(Jei.FE_INGREDIENT_TYPE, EnergyIngredient.INTANK);
        inputStack.setCustomRenderer(Jei.FE_INGREDIENT_TYPE, EnergyIngredient.INTANK);

        IRecipeSlotBuilder outputStack = builder.addSlot(RecipeIngredientRole.OUTPUT, 70, 23);
        outputStack.addItemStack(recipe.getOutput());
    }

}
