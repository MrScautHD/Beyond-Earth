package net.mrscauthd.beyond_earth.items;

import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.gauge.IGaugeValue;

public interface IFuelVehicleItem {

	public int getFuel(ItemStack itemStack);

	public void setFuel(ItemStack itemStack, int fuel);

	public int getFuelCapacity(ItemStack itemStack);

	public default IGaugeValue getFuelGauge(ItemStack itemStack) {
		int fuel = this.getFuel(itemStack);
		int capacity = this.getFuelCapacity(itemStack);
		return GaugeValueHelper.getFuel(fuel, capacity);
	}

}
