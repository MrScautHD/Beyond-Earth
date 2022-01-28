package net.mrscauthd.beyond_earth.gui.screens.planetselection;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.crafting.IngredientStack;
import net.mrscauthd.beyond_earth.crafting.SpaceStationRecipe;
import net.mrscauthd.beyond_earth.events.Methods;

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
			super(ModInit.PLANET_SELECTION_GUI.get(), id);

			this.rocket = extraData.readUtf();
			this.player = inv.player;
		}

		@Override
		public boolean stillValid(Player player) {
			return !player.isRemoved();
		}
	}

	public static class NetworkMessage {
		private ResourceLocation dimension;
		private boolean createSpaceStation;

		public NetworkMessage() {
			this.dimension = null;
			this.createSpaceStation = false;
		}

		public NetworkMessage(ResourceLocation dimension) {
			this();
			this.setDimension(dimension);
		}

		public NetworkMessage(ResourceLocation dimension, boolean createSpaceStation) {
			this(dimension);
			this.setCreateSpaceStation(createSpaceStation);
		}

		public NetworkMessage(FriendlyByteBuf buffer) {
			this.setDimension(buffer.readResourceLocation());
			this.setCreateSpaceStation(buffer.readBoolean());
		}

		public ResourceLocation getDimension() {
			return this.dimension;
		}

		public void setDimension(ResourceLocation dimension) {
			this.dimension = dimension;
		}

		public boolean isCreateSpaceStation() {
			return this.createSpaceStation;
		}

		public void setCreateSpaceStation(boolean createSpaceStation) {
			this.createSpaceStation = createSpaceStation;
		}

		public static NetworkMessage decode(FriendlyByteBuf buffer) {
			return new NetworkMessage(buffer);
		}

		public static void encode(NetworkMessage message, FriendlyByteBuf buffer) {
			buffer.writeResourceLocation(message.getDimension());
			buffer.writeBoolean(message.isCreateSpaceStation());
		}

		public static void handle(NetworkMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			defaultOptions(context.getSender());

			if (message.isCreateSpaceStation()) {
				deleteItems(context.getSender());
			}

			ResourceKey<Level> key = ResourceKey.create(Registry.DIMENSION_REGISTRY, message.getDimension());
			Methods.teleportButton(context.getSender(), key, message.isCreateSpaceStation());

			context.setPacketHandled(true);
		}
	}

	public static void defaultOptions(ServerPlayer player) {
		Methods.holdSpaceMessage(player);
		player.setNoGravity(false);
		player.closeContainer();
	}

	public static void deleteItems(Player player) {
		if (player.getAbilities().instabuild) {
			return;
		}

		Inventory inv = player.getInventory();
		SpaceStationRecipe recipe = (SpaceStationRecipe) player.level.getRecipeManager().byKey(SpaceStationRecipe.KEY).orElse(null);

		for (IngredientStack ingredientStack : recipe.getIngredientStacks()) {
			inv.clearOrCountMatchingItems(ingredientStack::testWithoutCount, ingredientStack.getCount(), inv);
		}

	}
}
