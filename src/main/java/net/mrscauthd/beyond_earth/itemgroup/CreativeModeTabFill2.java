package net.mrscauthd.beyond_earth.itemgroup;

import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.item.IFillCategoryAltItem;

public abstract class CreativeModeTabFill2 extends CreativeModeTab {

	public CreativeModeTabFill2(String label) {
		super(label);
	}

	@SuppressWarnings("deprecation")
	public void fillItemList(NonNullList<ItemStack> list) {
		super.fillItemList(list);

		int columns = 9;
		int emptyCount = columns - (list.size() % columns);

		if (emptyCount > 0) {
			emptyCount += columns;
		}

		for (int i = 0; i < emptyCount; i++) {
			list.add(ItemStack.EMPTY);
		}

		for (Item item : Registry.ITEM) {
			if (item instanceof IFillCategoryAltItem) {
				((IFillCategoryAltItem) item).fillItemCategoryAlt(this, list);
			}
		}
	};
}
