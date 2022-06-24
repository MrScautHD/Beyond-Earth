package net.mrscauthd.beyond_earth.guis.screens.lander;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.*;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.entities.LanderEntity;
import net.mrscauthd.beyond_earth.guis.helper.MenuHelper;
import net.mrscauthd.beyond_earth.registries.ContainersRegistry;
import org.jetbrains.annotations.NotNull;

public class LanderMenu {

	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			return new GuiContainer(id, inv, extraData);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		LanderEntity lander;

		public GuiContainer(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(ContainersRegistry.LANDER_GUI.get(), id);

			this.lander = (LanderEntity) inv.player.level.getEntity(extraData.readVarInt());

			IItemHandlerModifiable itemHandler = lander.getItemHandler();

			this.addSlot(new SlotItemHandler(itemHandler, 0, 31, 35) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return false;
				}
			});

			this.addSlot(new SlotItemHandler(itemHandler, 1, 80, 35) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return false;
				}
			});

			MenuHelper.createInventorySlots(inv, this::addSlot, 8, 84);
		}

		@Override
		public boolean stillValid(Player p_38874_) {
			return !lander.isRemoved();
		}

		@Override
		public ItemStack quickMoveStack(Player playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = this.slots.get(index);
			if (slot != null && slot.hasItem()) {
				ItemStack itemstack1 = slot.getItem();
				itemstack = itemstack1.copy();

				//TODO CHECK IF THAT WORK RIGHT ( rocket.getInventory().getSlots()) use as example HopperMenu or try just (to cast rocket)
				int containerIndex = lander.getInventory().getSlots();
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