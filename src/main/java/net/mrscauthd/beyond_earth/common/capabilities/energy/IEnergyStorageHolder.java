package net.mrscauthd.beyond_earth.common.capabilities.energy;

import net.minecraftforge.energy.IEnergyStorage;

public interface IEnergyStorageHolder {

    void onEnergyChanged(IEnergyStorage energyStorage, int energyDelta);

}
