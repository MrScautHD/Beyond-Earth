package net.mrscauthd.beyond_earth.entities;

import net.mrscauthd.beyond_earth.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.gauge.IGaugeValue;
import net.mrscauthd.beyond_earth.gauge.IGaugeValuesProvider;

public interface IFuelVehicleEntity extends IGaugeValuesProvider {
	public int getFuel();

	public void setFuel(int fuel);

	public int getFuelCapacity();

	public void putFuelMuchAsBucket();

	public boolean canPutFuelMuchAsBucket();

	public default IGaugeValue getFuelGauge() {
		int fuel = this.getFuel();
		int capacity = this.getFuelCapacity();
		return GaugeValueHelper.getFuel(fuel, capacity);
	}
}
