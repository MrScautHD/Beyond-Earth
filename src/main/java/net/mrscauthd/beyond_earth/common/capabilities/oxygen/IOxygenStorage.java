package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

public interface IOxygenStorage {

    int receiveOxygen(int maxReceive, boolean simulate);

    int extractOxygen(int maxExtract, boolean simulate);

    int getOxygenStored();

    int getMaxOxygenStored();

    boolean canExtract();

    boolean canReceive();
}
