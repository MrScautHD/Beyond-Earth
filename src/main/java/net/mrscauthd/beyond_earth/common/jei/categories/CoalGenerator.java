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
import net.mrscauthd.beyond_earth.client.util.GuiHelper;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.crafting.GeneratingRecipe;
import net.mrscauthd.beyond_earth.common.jei.Jei;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class CoalGenerator implements IRecipeCategory<GeneratingRecipe> {
    public static final ResourceLocation GUI = new ResourceLocation(BeyondEarth.MODID,
            "textures/jei/coal_generator.png");

    public static final int width = 128;
    public static final int height = 64;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;
    final IGuiHelper guiHelper;

    private final LoadingCache<Integer, IDrawableAnimated> cachedFlames;
    private final LoadingCache<Integer, IDrawableAnimated> cachedEnergy;

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

        this.cachedEnergy = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(Integer burnTime) {
                return guiHelper
                        .drawableBuilder(GuiHelper.ENERGY_PATH, 0, 0, GuiHelper.ENERGY_WIDTH, GuiHelper.ENERGY_HEIGHT)
                        .buildAnimated(burnTime, IDrawableAnimated.StartDirection.BOTTOM, false);
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

        int burnTime = recipe.getBurnTime() + 5;
        IDrawableAnimated flame = cachedFlames.getUnchecked(burnTime);
        flame.draw(stack, 31, 39);

        int energyTime = 1000 / Config.COAL_GENERATOR_ENERGY_GENERATION.get();
        IDrawableAnimated energy = cachedEnergy.getUnchecked(energyTime);
        energy.draw(stack, 91, 9);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GeneratingRecipe recipe, IFocusGroup focuses) {
        IRecipeSlotBuilder inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 30, 19);
        inputStack.addIngredients(recipe.getInput());
    }

}
