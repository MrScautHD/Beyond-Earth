package net.mrscauthd.beyond_earth.common.menus;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.*;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;
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
			super(ContainerRegistry.LANDER_GUI.get(), id);

			this.lander = (LanderEntity) inv.player.level.getEntity(extraData.readVarInt());

			IItemHandlerModifiable itemHandler = lander.getItemHandler();

			/** MAIN SLOT */
			this.addSlot(new SlotItemHandler(itemHandler, 0, 34, 27) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return false;
				}
			});

			/** FUEL SLOT 1 */
			this.addSlot(new SlotItemHandler(itemHandler, 1, 19, 58) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return false;
				}
			});

			/** FUEL SLOT 2 */
			this.addSlot(new SlotItemHandler(itemHandler, 2, 48, 58) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return false;
				}
			});

			/** TOP */
			this.addSlot(new SlotItemHandler(itemHandler, 3, 85, 31));
			this.addSlot(new SlotItemHandler(itemHandler, 4, 103, 31));
			this.addSlot(new SlotItemHandler(itemHandler, 5, 121, 31));
			this.addSlot(new SlotItemHandler(itemHandler, 6, 139, 31));

			/** BOTTOM */
			this.addSlot(new SlotItemHandler(itemHandler, 7, 85, 49));
			this.addSlot(new SlotItemHandler(itemHandler, 8, 103, 49));
			this.addSlot(new SlotItemHandler(itemHandler, 9, 121, 49));
			this.addSlot(new SlotItemHandler(itemHandler, 10, 139, 49));

			/** INV */
			MenuHelper.createInventorySlots(inv, this::addSlot, 8, 92);
		}

		@Override
		public boolean stillValid(Player p_38874_) {
			return !this.lander.isRemoved();
		}

		@Override
		public ItemStack quickMoveStack(Player playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = this.slots.get(index);
			if (slot != null && slot.hasItem()) {
				ItemStack itemstack1 = slot.getItem();
				itemstack = itemstack1.copy();

				int containerIndex = this.lander.getInventory().getSlots();
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