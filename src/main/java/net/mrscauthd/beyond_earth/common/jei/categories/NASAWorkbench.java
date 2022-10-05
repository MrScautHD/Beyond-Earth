package net.mrscauthd.beyond_earth.common.jei.categories;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.crafting.WorkbenchingRecipe;
import net.mrscauthd.beyond_earth.common.jei.Jei;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class NASAWorkbench implements IRecipeCategory<WorkbenchingRecipe> {
    public static final ResourceLocation GUI = new ResourceLocation(BeyondEarth.MODID, "textures/jei/jei_gui_1.png");

    public static final int width = 128;
    public static final int height = 123;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;
    final IGuiHelper guiHelper;

    public NASAWorkbench(final IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
        this.background = guiHelper.createDrawable(NASAWorkbench.GUI, 128, 0, NASAWorkbench.width,
                NASAWorkbench.height);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ItemsRegistry.COAL_GENERATOR_ITEM.get()));
        this.localizedName = I18n.get("container." + BeyondEarth.MODID + ".nasa_workbench");
    }

    @Override
    public RecipeType<WorkbenchingRecipe> getRecipeType() {
        return Jei.WORKBENCH_TYPE;
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
    public List<Component> getTooltipStrings(WorkbenchingRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX,
            double mouseY) {
        return IRecipeCategory.super.getTooltipStrings(recipe, recipeSlotsView, mouseX, mouseY);
    }

    @Override
    public void draw(WorkbenchingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX,
            double mouseY) {
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, WorkbenchingRecipe recipe, IFocusGroup focuses) {
//        IRecipeSlotBuilder inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 30, 19);
//        inputStack.addIngredientsUnsafe(recipe.getIngredients());
//        
        
    }

}
