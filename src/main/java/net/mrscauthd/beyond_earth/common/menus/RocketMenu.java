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

import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagRegistry;
import org.jetbrains.annotations.NotNull;

public class RocketMenu {

	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			return new GuiContainer(id, inv, extraData);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		public IRocketEntity rocket;

		public GuiContainer(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(ContainerRegistry.ROCKET_GUI.get(), id);

			this.rocket = (IRocketEntity) inv.player.level.getEntity(extraData.readVarInt());

			IItemHandlerModifiable itemHandler = rocket.getItemHandler();

			/** FUEL SLOT 1 */
			this.addSlot(new SlotItemHandler(itemHandler, 0, 20, 24) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					if (stack.getItem() instanceof BucketItem) {
						return ((BucketItem) stack.getItem()).getFluid().is(TagRegistry.FLUID_VEHICLE_FUEL_TAG);
					}
					return false;
				}
			});

			/** FUEL SLOT 2 */
			this.addSlot(new SlotItemHandler(itemHandler, 1, 20, 54) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return false;
				}
			});

			/** TOP */
			this.addSlot(new SlotItemHandler(itemHandler, 2, 86, 31));
			this.addSlot(new SlotItemHandler(itemHandler, 3, 104, 31));
			this.addSlot(new SlotItemHandler(itemHandler, 4, 122, 31));
			this.addSlot(new SlotItemHandler(itemHandler, 5, 140, 31));

			/** BOTTOM */
			this.addSlot(new SlotItemHandler(itemHandler, 6, 86, 49));
			this.addSlot(new SlotItemHandler(itemHandler, 7, 104, 49));
			this.addSlot(new SlotItemHandler(itemHandler, 8, 122, 49));
			this.addSlot(new SlotItemHandler(itemHandler, 9, 140, 49));

			/** INV */
			MenuHelper.createInventorySlots(inv, this::addSlot, 8, 92);
		}

		@Override
		public boolean stillValid(Player p_38874_) {
			return !rocket.isRemoved();
		}

		@Override
		public ItemStack quickMoveStack(Player playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = this.slots.get(index);
			if (slot != null && slot.hasItem()) {
				ItemStack itemstack1 = slot.getItem();
				itemstack = itemstack1.copy();

				int containerIndex = rocket.getInventory().getSlots();
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
