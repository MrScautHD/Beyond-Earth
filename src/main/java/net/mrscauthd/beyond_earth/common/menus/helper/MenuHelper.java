package net.mrscauthd.beyond_earth.common.menus.helper;

import java.util.List;
import java.util.function.Function;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

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

    public static boolean isEmpty(IItemHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            if (!handler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public static NonNullList<ItemStack> getStacks(IItemHandler handler) {
        NonNullList<ItemStack> list = NonNullList.withSize(handler.getSlots(), ItemStack.EMPTY);

        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            list.set(i, stack);
        }

        return list;
    }

    public interface MenuTransfer {
        boolean moveItemStackTo(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection);
    }

    public static ItemStack transferStackInSlot(AbstractContainerMenu container, Player player, int slotNumber,
            int containerIndex, int containerSize, MenuTransfer mergeItemStack) {
        ItemStack itemStack = ItemStack.EMPTY;
        List<Slot> inventorySlots = container.slots;
        Slot slot = inventorySlots.get(slotNumber);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemStack = slotStack.copy();

            int playerInventoryStartIndex = containerIndex + containerSize;

            // Click Player Inventory
            if (slotNumber < playerInventoryStartIndex) {
                if (!mergeItemStack.moveItemStackTo(slotStack, playerInventoryStartIndex, inventorySlots.size(),
                        true)) {
                    return ItemStack.EMPTY;
                }

            }
            // Click Container Inventory
            else if (!mergeItemStack.moveItemStackTo(slotStack, containerIndex, playerInventoryStartIndex, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, slotStack);
        }

        return itemStack;
    }

    public static ItemStack transferStackInSlot(AbstractContainerMenu container, Player player, int slotNumber,
            int containerIndex, Container inventory, MenuTransfer mergeItemStack) {
        int containerSize = inventory.getContainerSize();
        return transferStackInSlot(container, player, slotNumber, containerIndex, containerSize, mergeItemStack);
    }

    public static ItemStack transferStackInSlot(AbstractContainerMenu container, Player player, int slotNumber,
            Container inventory, MenuTransfer mergeItemStack) {
        return transferStackInSlot(container, player, slotNumber, 0, inventory, mergeItemStack);
    }
}
