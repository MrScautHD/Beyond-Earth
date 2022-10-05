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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fluids.FluidStack;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.util.GuiHelper;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.crafting.FuelRefiningRecipe;
import net.mrscauthd.beyond_earth.common.jei.Jei;
import net.mrscauthd.beyond_earth.common.jei.helper.DrawableFluidAnimated;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class FuelRefining implements IRecipeCategory<FuelRefiningRecipe> {
    public static final ResourceLocation GUI = new ResourceLocation(BeyondEarth.MODID, "textures/jei/jei_gui_1.png");

    public static final int width = 128;
    public static final int height = 64;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;
    final IGuiHelper guiHelper;

    private final LoadingCache<Integer, IDrawableAnimated> cachedArrow;
    private final LoadingCache<Integer, IDrawableAnimated> cachedEnergy;

    private final LoadingCache<FluidStack, IDrawableAnimated> cachedInTank;
    private final LoadingCache<FluidStack, IDrawableAnimated> cachedOutTank;

    public FuelRefining(final IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
        this.background = guiHelper.createDrawable(FuelRefining.GUI, 0, 128, FuelRefining.width, FuelRefining.height);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ItemsRegistry.COAL_GENERATOR_ITEM.get()));
        this.localizedName = I18n.get("container." + BeyondEarth.MODID + ".fuel_refinery");

        this.cachedEnergy = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(Integer burnTime) {
                return guiHelper
                        .drawableBuilder(GuiHelper.ENERGY_PATH, 0, 0, GuiHelper.ENERGY_WIDTH, GuiHelper.ENERGY_HEIGHT)
                        .buildAnimated(burnTime, IDrawableAnimated.StartDirection.TOP, true);
            }
        });

        this.cachedArrow = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(Integer cookTime) {
                return guiHelper.drawableBuilder(Constants.RECIPE_GUI_VANILLA, 82, 128, 24, 17).buildAnimated(cookTime,
                        IDrawableAnimated.StartDirection.LEFT, false);
            }
        });

        this.cachedInTank = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(FluidStack stack) {
                return new DrawableFluidAnimated(100 * stack.getAmount(), true, stack);
            }
        });

        this.cachedOutTank = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(FluidStack stack) {
                return new DrawableFluidAnimated(100 * stack.getAmount(), false, stack);
            }
        });
    }

    @Override
    public RecipeType<FuelRefiningRecipe> getRecipeType() {
        return Jei.REFINE_TYPE;
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
    public List<Component> getTooltipStrings(FuelRefiningRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
            double mouseY) {
        return IRecipeCategory.super.getTooltipStrings(recipe, recipeSlotsView, mouseX, mouseY);
    }

    @Override
    public void draw(FuelRefiningRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX,
            double mouseY) {
        int energyTime = 100 / Config.FUEL_REFINERY_ENERGY_USAGE.get();
        IDrawableAnimated energy = cachedEnergy.getUnchecked(energyTime);
        energy.draw(stack, 108, 9);

        int compressTime = energyTime;
        IDrawableAnimated arrow = cachedArrow.getUnchecked(compressTime);
        arrow.draw(stack, 40, 22);

        IDrawableAnimated fluid = cachedInTank.getUnchecked(recipe.getInput().toStack());
        fluid.draw(stack, 20, 8);
        fluid = cachedOutTank.getUnchecked(recipe.getOutput().toStack());
        fluid.draw(stack, 73, 8);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FuelRefiningRecipe recipe, IFocusGroup focuses) {
        IRecipeSlotBuilder inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 15, 23);
        // Dummy ingredient to stop JEI thinking this recipe doesn't exist.
        inputStack.addIngredients(Ingredient.of(new ItemStack(Blocks.AIR)));
    }

}
