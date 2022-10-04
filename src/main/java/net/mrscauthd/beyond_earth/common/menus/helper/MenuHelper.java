package net.mrscauthd.beyond_earth.common.menus.helper;

import java.util.function.Function;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.common.blocks.entities.AbstractMachineBlockEntity;

public class MenuHelper {
    public static void createInventorySlots(Inventory inventory, Function<Slot, Slot> slotCreator, int left, int top) {
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 9; ++l) {
                slotCreator.apply(new Slot(inventory, l + k * 9 + 9, left + l * 18, top + k * 18));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            slotCreator.apply(new Slot(inventory, i1, left + i1 * 18, top + 58));
        }
    }

    public static interface MenuTransfer {
        boolean moveItemStackTo(ItemStack stack, int number, int slots, boolean bool);
    }

    public static ItemStack transferStackInSlot(AbstractContainerMenu guiContainer, Player playerIn, int index,
            AbstractMachineBlockEntity blockEntity, MenuTransfer transfer) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = guiContainer.slots.get(index);
        if (slot != null && slot.hasItem()) {
            final ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            final int slotCount = 1;
            if (index < slotCount) {
                if (!transfer.moveItemStackTo(itemstack1, slotCount, guiContainer.slots.size(), false))
                    return ItemStack.EMPTY;
            } else if (!transfer.moveItemStackTo(itemstack1, 0, slotCount, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();
        }
        return itemstack;
    }
}
