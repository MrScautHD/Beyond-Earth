package net.mrscauthd.beyond_earth.guis.screens.rover;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.*;

import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.entities.RoverEntity;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.fluids.FluidUtil2;
import net.mrscauthd.beyond_earth.guis.helper.ContainerHelper;
import net.mrscauthd.beyond_earth.registries.ScreensRegistry;
import net.mrscauthd.beyond_earth.registries.TagsRegistry;
import org.jetbrains.annotations.NotNull;

public class RoverGui {

	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			return new GuiContainer(id, inv, extraData);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		private final RoverEntity rover;

		public GuiContainer(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(ScreensRegistry.ROVER_GUI.get(), id);

			this.rover = (RoverEntity) inv.player.level.getEntity(extraData.readVarInt());

			IItemHandlerModifiable itemHandler = ((RoverEntity) rover).getItemHandler();
			this.addSlot(new SlotItemHandler(itemHandler, 0, 8, 63) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return Methods.tagCheck(FluidUtil2.findBucketFluid(stack.getItem()), TagsRegistry.FLUID_VEHICLE_FUEL_TAG);
				}
			});

			this.addSlot(new SlotItemHandler(itemHandler, 1, 151, 16));
			this.addSlot(new SlotItemHandler(itemHandler, 2, 133, 16));
			this.addSlot(new SlotItemHandler(itemHandler, 3, 115, 16));
			this.addSlot(new SlotItemHandler(itemHandler, 4, 97, 16));

			this.addSlot(new SlotItemHandler(itemHandler, 5, 151, 34));
			this.addSlot(new SlotItemHandler(itemHandler, 6, 133, 34));
			this.addSlot(new SlotItemHandler(itemHandler, 7, 115, 34));
			this.addSlot(new SlotItemHandler(itemHandler, 8, 97, 34));

			ContainerHelper.addInventorySlots(this, inv, 8, 93, this::addSlot);
		}

		@Override
		public boolean stillValid(Player p_38874_) {
			return !rover.isRemoved();
		}

		@Override
		public ItemStack quickMoveStack(Player playerIn, int index) {
			return ContainerHelper.transferStackInSlot(this, playerIn, index, 0, 9, this::moveItemStackTo);
		}
		
		public RoverEntity getRover() {
			return this.rover;
		}
	}
}