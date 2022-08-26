package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

public interface IOxygenStorage {

	int receiveOxygen(int maxReceive, boolean simulate);

	int extractOxygen(int maxExtract, boolean simulate);

	int getOxygen();

	void setOxygen(int oxygen);

	int getMaxCapacity();

	public default double getOxygenStoredRatio() {
		return (double) this.getOxygen() / (double) this.getMaxCapacity();
	}
}
