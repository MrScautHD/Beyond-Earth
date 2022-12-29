package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IOxygenStorage extends INBTSerializable<CompoundTag> {

    int receiveOxygen(int maxReceive, boolean simulate);

    int extractOxygen(int maxExtract, boolean simulate);

    int getOxygen();

    void setOxygen(int oxygen);

    int getMaxCapacity();

    default double getOxygenStoredRatio() {
        return (double) this.getOxygen() / (double) this.getMaxCapacity();
    }
}
