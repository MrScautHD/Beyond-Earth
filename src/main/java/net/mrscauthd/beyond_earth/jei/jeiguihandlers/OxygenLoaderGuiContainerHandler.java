package net.mrscauthd.beyond_earth.jei.jeiguihandlers;

import org.jetbrains.annotations.Nullable;

import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import net.mrscauthd.beyond_earth.guis.helper.GuiHelper;
import net.mrscauthd.beyond_earth.guis.screens.oxygenloader.OxygenLoaderGuiWindow;
import net.mrscauthd.beyond_earth.machines.tile.OxygenLoaderBlockEntity;

public class OxygenLoaderGuiContainerHandler implements IGuiContainerHandler<OxygenLoaderGuiWindow> {

	public OxygenLoaderGuiContainerHandler() {

	}

	@Override
	public @Nullable Object getIngredientUnderMouse(OxygenLoaderGuiWindow containerScreen, double mouseX, double mouseY) {
		OxygenLoaderBlockEntity blockEntity = containerScreen.getMenu().getBlockEntity();

		if (GuiHelper.isHover(containerScreen.getInputTankBounds(), mouseX, mouseY)) {
			return blockEntity.getInputTank().getFluid();
		} else {
			return IGuiContainerHandler.super.getIngredientUnderMouse(containerScreen, mouseX, mouseY);
		}
	}
}