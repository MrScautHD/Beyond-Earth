package net.mrscauthd.beyond_earth.guis.screens.rover;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.*;

import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.entities.RoverEntity;
import net.mrscauthd.beyond_earth.guis.helper.MenuHelper;
import net.mrscauthd.beyond_earth.registries.ContainersRegistry;
import net.mrscauthd.beyond_earth.registries.TagsRegistry;
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
			super(ContainersRegistry.ROVER_GUI.get(), id);

			this.rover = (RoverEntity) inv.player.level.getEntity(extraData.readVarInt());

			IItemHandlerModifiable itemHandler = rover.getItemHandler();

			this.addSlot(new SlotItemHandler(itemHandler, 0, 8, 63) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					if (stack.getItem() instanceof BucketItem) {
						return ((BucketItem) stack.getItem()).getFluid().is(TagsRegistry.FLUID_VEHICLE_FUEL_TAG);
					}
					return false;
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

			//TODO CHECK IF IT WORKS RIGHT
			/** CREATE INVENTORY SLOTS */
			MenuHelper.createInventorySlots(inv, this::addSlot, 36, 137);
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