package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

public class OxygenStorage implements IOxygenStorage {
    private int oxygen;
    private int capacity;

    private final IOxygenStorageHolder listener;

    public OxygenStorage(IOxygenStorageHolder holder, int capacity) {
        this(holder, capacity, 0);
    }

    public OxygenStorage(IOxygenStorageHolder holder, int capacity, int oxygen) {
        this.listener = holder;
        this.capacity = capacity;
        this.oxygen = Math.max(0, Math.min(capacity, oxygen));
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
        oxygen = Mth.clamp(oxygen, 0, this.getMaxCapacity());
        int dO2 = oxygen - this.oxygen;
        this.oxygen = oxygen;
        if (listener != null)
            listener.onOxygenChanged(this, dO2);
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
        int oxygen = (int) Mth.clamp((long) this.oxygen + maxReceive, 0L, this.getMaxCapacity());
        int dO2 = oxygen - this.oxygen;
        if (!simulate) {
            this.setOxygen(oxygen);
        }
        return dO2;
    }

    @Override
    public int extractOxygen(int maxExtract, boolean simulate) {
        int oxygen = (int) Mth.clamp((long) this.oxygen - maxExtract, 0L, this.getMaxCapacity());
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
