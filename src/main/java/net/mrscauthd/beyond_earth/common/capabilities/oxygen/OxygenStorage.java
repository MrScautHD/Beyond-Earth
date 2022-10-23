package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.nbt.CompoundTag;

public class OxygenStorage implements IOxygenStorage {
    private int oxygen;
    private int capacity;

    private IOxygenStorageHolder listener;

    public OxygenStorage() {
        this.listener = null;
    }

    public OxygenStorage(IOxygenStorageHolder listener) {
        this.listener = listener;
    }

    public void setMaxCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getOxygen() {
        return this.oxygen;
    }

    @Override
    public void setOxygen(int oxygen) {
        int dO2 = oxygen - this.oxygen;
        if (listener != null)
            listener.onOxygenChanged(this, dO2);
        this.oxygen = oxygen;
    }

    @Override
    public int getMaxCapacity() {
        return this.capacity;
    }

    @Override
    public double getOxygenStoredRatio() {
        return (double) this.getOxygen() / (double) this.getMaxCapacity();
    }

    @Override
    public int receiveOxygen(int maxReceive, boolean simulate) {
        int oxygen = this.oxygen;
        oxygen = Math.min(maxReceive + oxygen, getMaxCapacity());
        int dO2 = oxygen - this.oxygen;
        if (!simulate) {
            this.setOxygen(oxygen);
        }
        return dO2;
    }

    @Override
    public int extractOxygen(int maxExtract, boolean simulate) {
        int oxygen = this.oxygen;
        oxygen = Math.max(oxygen - maxExtract, 0);
        int dO2 = this.oxygen - oxygen;
        if (!simulate) {
            this.setOxygen(oxygen);
        }
        return dO2;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("oxygenStorage", this.getOxygen());
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        // no setOxygen as to not trigger the listener!
        this.oxygen = nbt.getInt("oxygenStorage");
    }
}
