package net.mrscauthd.beyond_earth.jei.jeiguihandlers;

import org.jetbrains.annotations.Nullable;

import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import net.mrscauthd.beyond_earth.guis.helper.GuiHelper;
import net.mrscauthd.beyond_earth.guis.screens.oxygenbubbledistributor.OxygenBubbleDistributorGuiWindow;
import net.mrscauthd.beyond_earth.machines.tile.OxygenBubbleDistributorBlockEntity;

public class OxygenBubbleDistributorGuiContainerHandler implements IGuiContainerHandler<OxygenBubbleDistributorGuiWindow> {

	public OxygenBubbleDistributorGuiContainerHandler() {

	}

	@Override
	public @Nullable Object getIngredientUnderMouse(OxygenBubbleDistributorGuiWindow containerScreen, double mouseX, double mouseY) {
		OxygenBubbleDistributorBlockEntity blockEntity = containerScreen.getMenu().getBlockEntity();

		if (GuiHelper.isHover(containerScreen.getInputTankBounds(), mouseX, mouseY)) {
			return blockEntity.getInputTank().getFluid();
		} else {
			return IGuiContainerHandler.super.getIngredientUnderMouse(containerScreen, mouseX, mouseY);
		}
	}
}