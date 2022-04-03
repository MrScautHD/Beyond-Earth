package net.mrscauthd.beyond_earth.guis.helper;

import net.minecraft.world.item.ItemStack;

@FunctionalInterface
public interface IMergeItemStack {
	boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection);
}
