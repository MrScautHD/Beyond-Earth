package net.mrscauthd.beyond_earth.common.jei.categories;

import java.util.Collections;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.data.recipes.RocketPart;
import net.mrscauthd.beyond_earth.common.data.recipes.WorkbenchingRecipe;
import net.mrscauthd.beyond_earth.common.jei.Jei;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.common.registries.RocketPartsRegistry;

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

        RocketPart nose = RocketPartsRegistry.ROCKET_PART_NOSE.get();
        RocketPart body = RocketPartsRegistry.ROCKET_PART_BODY.get();
        RocketPart tank = RocketPartsRegistry.ROCKET_PART_TANK.get();
        RocketPart left = RocketPartsRegistry.ROCKET_PART_FIN_LEFT.get();
        RocketPart right = RocketPartsRegistry.ROCKET_PART_FIN_RIGHT.get();
        RocketPart engine = RocketPartsRegistry.ROCKET_PART_ENGINE.get();

        int dx = -7;
        int dy = -9;

        var list = recipe.getParts().getOrDefault(nose, Collections.emptyList());
        IRecipeSlotBuilder inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 40 + dx, 18 + dy);
        inputStack.addIngredients(list.get(0));

        list = recipe.getParts().getOrDefault(body, Collections.emptyList());
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 2; x++) {
                int i = y * 2 + x;
                inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 31 + dx + x * 18, 36 + dy + y * 18);
                inputStack.addIngredients(list.get(i));
            }

        list = recipe.getParts().getOrDefault(tank, Collections.emptyList());
        for (int x = 0; x < 2; x++) {
            inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 31 + dx + x * 18, 90 + dy);
            inputStack.addIngredients(list.get(x));
        }

        list = recipe.getParts().getOrDefault(left, Collections.emptyList());
        for (int y = 0; y < 2; y++) {
            inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 13 + dx, 90 + dy + y * 18);
            inputStack.addIngredients(list.get(y));
        }

        list = recipe.getParts().getOrDefault(right, Collections.emptyList());
        for (int y = 0; y < 2; y++) {
            inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 67 + dx, 90 + dy + y * 18);
            inputStack.addIngredients(list.get(y));
        }

        inputStack = builder.addSlot(RecipeIngredientRole.INPUT, 40 + dx, 108 + dy);
        list = recipe.getParts().getOrDefault(engine, Collections.emptyList());
        inputStack.addIngredients(list.get(0));

        IRecipeSlotBuilder outputStack = builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 28);
        outputStack.addItemStack(recipe.getOutput());
    }

}
