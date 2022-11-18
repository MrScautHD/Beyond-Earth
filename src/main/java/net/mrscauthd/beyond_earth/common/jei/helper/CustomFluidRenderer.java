package net.mrscauthd.beyond_earth.common.jei.helper;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.mrscauthd.beyond_earth.client.util.GuiHelper;

public class CustomFluidRenderer implements IIngredientRenderer<FluidStack> {

    private final LoadingCache<Integer, DrawableFluidAnimated> cachedTank;
    final boolean inTank;

    public CustomFluidRenderer(boolean inTank, boolean O2) {
        this.inTank = inTank;
        this.cachedTank = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
            @Override
            public DrawableFluidAnimated load(Integer stack) {
                return new DrawableFluidAnimated(100 * stack, inTank, FluidStack.EMPTY, O2);
            }
        });
    }

    public CustomFluidRenderer(boolean inTank) {
        this(inTank, false);
    }

    @Override
    public void render(PoseStack stack, FluidStack ingredient) {
        DrawableFluidAnimated fluid = cachedTank.getUnchecked(ingredient.getAmount());
        fluid.setFluid(ingredient);
        fluid.draw(stack);
    }

    @Override
    public List<Component> getTooltip(FluidStack ingredient, TooltipFlag tooltipFlag) {
        List<Component> tooltip = new ArrayList<>();
        Fluid fluidType = ingredient.getFluid();
        try {
            if (fluidType.isSame(Fluids.EMPTY)) {
                return tooltip;
            }

            Component displayName = ingredient.getDisplayName();
            tooltip.add(displayName);

            long amount = ingredient.getAmount();
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

}
