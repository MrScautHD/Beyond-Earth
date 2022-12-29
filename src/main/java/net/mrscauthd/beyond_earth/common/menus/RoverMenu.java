package net.mrscauthd.beyond_earth.common.menus;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.*;

import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.entities.RoverEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagRegistry;
import org.jetbrains.annotations.NotNull;

public class RoverMenu {

	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			return new GuiContainer(id, inv, extraData);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		public RoverEntity rover;

		public GuiContainer(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(ContainerRegistry.ROVER_GUI.get(), id);

			this.rover = (RoverEntity) inv.player.level.getEntity(extraData.readVarInt());

			IItemHandlerModifiable itemHandler = rover.getItemHandler();

			this.addSlot(new SlotItemHandler(itemHandler, 0, 20, 26) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					if (stack.getItem() instanceof BucketItem) {
						return ((BucketItem) stack.getItem()).getFluid().is(TagRegistry.FLUID_VEHICLE_FUEL_TAG);
					}
					return false;
				}
			});

			this.addSlot(new SlotItemHandler(itemHandler, 1, 20, 56) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return false;
				}
			});

			/** CREATE INVENTORY SLOTS */
			MenuHelper.createInventorySlots(inv, this::addSlot, 8, 99);
		}

		@Override
		public boolean stillValid(Player p_38874_) {
			return !rover.isRemoved();
		}

		@Override
		public ItemStack quickMoveStack(Player playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = this.slots.get(index);
			if (slot != null && slot.hasItem()) {
				ItemStack itemstack1 = slot.getItem();
				itemstack = itemstack1.copy();

				//TODO CHECK IF THAT WORK RIGHT ( rocket.getInventory().getSlots()) use as example HopperMenu or try just (to cast rocket)
				int containerIndex = rover.getInventory().getSlots();
				if (index < containerIndex) {
					if (!this.moveItemStackTo(itemstack1, containerIndex, this.slots.size(), true)) {
						return ItemStack.EMPTY;
					}
				} else if (!this.moveItemStackTo(itemstack1, 0, containerIndex, false)) {
					return ItemStack.EMPTY;
				}

				if (itemstack1.isEmpty()) {
					slot.set(ItemStack.EMPTY);
				} else {
					slot.setChanged();
				}
			}

			return itemstack;
		}
	}
}