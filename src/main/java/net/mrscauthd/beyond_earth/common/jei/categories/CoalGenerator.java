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
import net.mrscauthd.beyond_earth.common.data.recipes.GeneratingRecipe;
import net.mrscauthd.beyond_earth.common.jei.Jei;
import net.mrscauthd.beyond_earth.common.jei.helper.EnergyIngredient;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class CoalGenerator implements IRecipeCategory<GeneratingRecipe> {
    public static final ResourceLocation GUI = new ResourceLocation(BeyondEarth.MODID, "textures/jei/jei_gui_1.png");

    public static final int width = 128;
    public static final int height = 64;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;
    final IGuiHelper guiHelper;

    private final LoadingCache<Integer, IDrawableAnimated> cachedFlames;

    public CoalGenerator(final IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
        this.background = guiHelper.createDrawable(CoalGenerator.GUI, 0, 0, CoalGenerator.width, CoalGenerator.height);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ItemsRegistry.COAL_GENERATOR_ITEM.get()));
        this.localizedName = I18n.get("container." + BeyondEarth.MODID + ".coal_generator");

        this.cachedFlames = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(Integer burnTime) {
                return guiHelper.drawableBuilder(Constants.RECIPE_GUI_VANILLA, 82, 114, 14, 14).buildAnimated(burnTime,
                        IDrawableAnimated.StartDirection.TOP, true);
            }
        });
    }

    @Override
    public RecipeType<GeneratingRecipe> getRecipeType() {
        return Jei.COAL_TYPE;
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
    public List<Component> getTooltipStrings(GeneratingRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
            double mouseY) {
        return IRecipeCategory.super.getTooltipStrings(recipe, recipeSlotsView, mouseX, mouseY);
    }

    @Override
    public void draw(GeneratingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX,
            double mouseY) {
        int burnTime = recipe.getBurnTime();
        IDrawableAnimated flame = cachedFlames.getUnchecked(burnTime);
        flame.draw(stack, 31, 39);
        recipeSlotsView.getSlotViews(RecipeIngredientRole.OUTPUT).get(0).getIngredients(Jei.FE_INGREDIENT_TYPE)
                .forEach(i -> i.setAmount(Config.COAL_GENERATOR_ENERGY_GENERATION.get()));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GeneratingRecipe recipe, IFocusGroup focuses) {
        IRecipeSlotBuilder inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 30, 19);
        inputStack.addIngredients(recipe.getInput());

        IRecipeSlotBuilder outputStack = builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 9);
        outputStack.addIngredient(Jei.FE_INGREDIENT_TYPE, EnergyIngredient.OUTTANK);
        outputStack.setCustomRenderer(Jei.FE_INGREDIENT_TYPE, EnergyIngredient.OUTTANK);
    }

}
