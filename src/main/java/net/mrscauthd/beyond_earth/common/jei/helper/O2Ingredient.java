package net.mrscauthd.beyond_earth.common.jei.helper;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.util.GuiHelper;
import net.mrscauthd.beyond_earth.common.jei.Jei;

public class O2Ingredient implements IIngredientHelper<O2Ingredient>, IIngredientRenderer<O2Ingredient> {

    public static final class DummyRenderer implements IIngredientRenderer<O2Ingredient> {
        @Override
        public void render(PoseStack stack, O2Ingredient ingredient) {
            // DO NOT USE THIS, THIS IS BECAUSE JEI REQUIRES SIZE 16
        }

        @Override
        public List<Component> getTooltip(O2Ingredient ingredient, TooltipFlag tooltipFlag) {
            return Lists.newArrayList();
        }
    }

    public static final O2Ingredient INSTANCE = new O2Ingredient();

    public static final O2Ingredient INTANK = new O2Ingredient();
    public static final O2Ingredient OUTTANK = new O2Ingredient();

    static final List<O2Ingredient> ALL = Lists.newArrayList(INTANK, OUTTANK);

    public static Collection<O2Ingredient> getIngredients() {
        return ALL;
    }

    private final LoadingCache<Integer, DrawableFluidAnimated> cachedTank;
    private int amount = 0;

    public O2Ingredient() {
        this.cachedTank = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public DrawableFluidAnimated load(Integer stack) {
                return new DrawableFluidAnimated(100 * stack, O2Ingredient.this == INTANK, FluidStack.EMPTY, true);
            }
        });
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void render(PoseStack stack, O2Ingredient ingredient) {
        int amount = Math.max(ingredient.amount, 1);
        DrawableFluidAnimated fluid = cachedTank.getUnchecked(amount);
        fluid.draw(stack);
    }

    @Override
    public List<Component> getTooltip(O2Ingredient ingredient, TooltipFlag tooltipFlag) {
        List<Component> tooltip = new ArrayList<>();
        try {
            String displayName = this.getDisplayName(ingredient);
            tooltip.add(Component.literal(displayName));

            long amount = ingredient.amount;
            long milliBuckets = (amount * 1000) / FluidType.BUCKET_VOLUME;
            NumberFormat nf = NumberFormat.getIntegerInstance();
            MutableComponent amountString = Component.translatable("jei.tooltip.liquid.amount",
                    nf.format(milliBuckets));
            tooltip.add(amountString.withStyle(ChatFormatting.GRAY));

        } catch (RuntimeException e) {

        }
        return tooltip;
    }

    @Override
    public int getWidth() {
        return GuiHelper.FLUID_TANK_WIDTH;
    }

    @Override
    public int getHeight() {
        return GuiHelper.FLUID_TANK_HEIGHT;
    }

    @Override
    public IIngredientType<O2Ingredient> getIngredientType() {
        return Jei.O2_INGREDIENT_TYPE;
    }

    @Override
    public String getDisplayName(O2Ingredient ingredient) {
        return I18n.get("general." + BeyondEarth.MODID + ".oxygen");
    }

    @Override
    public String getUniqueId(O2Ingredient ingredient, UidContext context) {
        return "beyond_earth:oxygen";
    }

    @Override
    public ResourceLocation getResourceLocation(O2Ingredient ingredient) {
        return new ResourceLocation("beyond_earth:oxygen");
    }

    @Override
    public O2Ingredient copyIngredient(O2Ingredient ingredient) {
        return ingredient;
    }

    @Override
    public String getErrorInfo(@Nullable O2Ingredient ingredient) {
        return "error with oxygen?";
    }
}
