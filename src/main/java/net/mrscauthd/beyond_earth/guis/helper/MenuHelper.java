package net.mrscauthd.beyond_earth.guis.helper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import java.util.function.Function;

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
}
