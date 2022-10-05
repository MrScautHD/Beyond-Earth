package net.mrscauthd.beyond_earth.common.jei.helper;

import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.common.util.TickTimer;
import net.minecraftforge.fluids.FluidStack;
import net.mrscauthd.beyond_earth.client.util.GuiHelper;

public class DrawableFluidAnimated implements IDrawableAnimated {

    private final FluidStack fluid;
    private final ITickTimer tickTimer;
    boolean drain;

    public DrawableFluidAnimated(int timer, boolean drain, FluidStack fluid) {
        this.drain = drain;
        this.tickTimer = new TickTimer(timer, GuiHelper.FLUID_TANK_HEIGHT, drain);
        this.fluid = fluid;
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
    public void draw(PoseStack poseStack, int xOffset, int yOffset) {

        int animationValue = tickTimer.getValue();

        int fluidY = this.drain ? yOffset - +animationValue + this.getHeight()
                : this.getHeight() - animationValue + yOffset;

        GuiHelper.drawFluid(poseStack, xOffset, fluidY, GuiHelper.FLUID_TANK_WIDTH, animationValue, fluid);
        GuiHelper.drawFluidTankOverlay(poseStack, xOffset, yOffset);
    }
}
