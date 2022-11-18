package net.mrscauthd.beyond_earth.common.jei.helper;

import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.common.util.TickTimer;
import net.mrscauthd.beyond_earth.client.util.GuiHelper;

public class DrawableEnergyAnimated implements IDrawableAnimated {

    private final ITickTimer tickTimer;
    boolean drain;

    public DrawableEnergyAnimated(int timer, boolean drain) {
        this.drain = drain;
        this.tickTimer = new TickTimer(timer, GuiHelper.ENERGY_HEIGHT, drain);
    }

    @Override
    public int getWidth() {
        return GuiHelper.ENERGY_WIDTH;
    }

    @Override
    public int getHeight() {
        return GuiHelper.ENERGY_HEIGHT;
    }

    @Override
    public void draw(PoseStack poseStack, int xOffset, int yOffset) {
        int animationValue = tickTimer.getValue();
        double ratio = (animationValue / (double) GuiHelper.ENERGY_HEIGHT);
        GuiHelper.drawEnergy(poseStack, xOffset, yOffset, ratio);
    }
}
