package net.mrscauthd.beyond_earth.jei.jeiguihandlers;

import org.jetbrains.annotations.Nullable;

import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import net.mrscauthd.beyond_earth.guis.helper.GuiHelper;
import net.mrscauthd.beyond_earth.guis.screens.waterpump.WaterPumpGuiWindow;
import net.mrscauthd.beyond_earth.machines.tile.WaterPumpBlockEntity;

public class WaterPumpGuiContainerHandler implements IGuiContainerHandler<WaterPumpGuiWindow> {

	public WaterPumpGuiContainerHandler() {

	}

	@Override
	public @Nullable Object getIngredientUnderMouse(WaterPumpGuiWindow containerScreen, double mouseX, double mouseY) {
		WaterPumpBlockEntity blockEntity = containerScreen.getMenu().getBlockEntity();

		if (GuiHelper.isHover(containerScreen.getOutputTankBounds(), mouseX, mouseY)) {
			return blockEntity.getWaterTank().getFluid();
		} else {
			return IGuiContainerHandler.super.getIngredientUnderMouse(containerScreen, mouseX, mouseY);
		}
	}
}