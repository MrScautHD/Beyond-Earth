package net.mrscauthd.beyond_earth.guis.screens.planetselection;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.registries.ScreensRegistry;

public class PlanetSelectionGui {

	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			return new GuiContainer(id, inv, extraData);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		String rocket;
		Player player;

		public GuiContainer(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(ScreensRegistry.PLANET_SELECTION_GUI.get(), id);
			this.rocket = extraData.readUtf();
			this.player = inv.player;
		}

		@Override
		public boolean stillValid(Player player) {
			return !player.isDeadOrDying();
		}

		public String getRocket() {
			return rocket;
		}

		public Player getPlayer() {
			return player;
		}
	}
}
