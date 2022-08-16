package net.mrscauthd.beyond_earth.jei.jeiguihandlers;

import org.jetbrains.annotations.Nullable;

import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import net.mrscauthd.beyond_earth.guis.helper.GuiHelper;
import net.mrscauthd.beyond_earth.guis.screens.fuelrefinery.FuelRefineryGuiWindow;
import net.mrscauthd.beyond_earth.machines.tile.FuelRefineryBlockEntity;

public class FuelRefineryGuiContainerHandler implements IGuiContainerHandler<FuelRefineryGuiWindow> {

	public FuelRefineryGuiContainerHandler() {

	}

	@Override
	public @Nullable Object getIngredientUnderMouse(FuelRefineryGuiWindow containerScreen, double mouseX, double mouseY) {
		FuelRefineryBlockEntity blockEntity = containerScreen.getMenu().getBlockEntity();

		if (GuiHelper.isHover(containerScreen.getInputTankBounds(), mouseX, mouseY)) {
			return blockEntity.getInputTank().getFluid();
		} else if (GuiHelper.isHover(containerScreen.getOutputTankBounds(), mouseX, mouseY)) {
			return blockEntity.getOutputTank().getFluid();
		} else {
			return IGuiContainerHandler.super.getIngredientUnderMouse(containerScreen, mouseX, mouseY);
		}
	}
}