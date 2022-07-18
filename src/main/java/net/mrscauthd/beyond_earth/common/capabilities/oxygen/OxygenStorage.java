package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class OxygenStorage implements IOxygenStorage, INBTSerializable<Tag> {

    protected int oxygen;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public OxygenStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public OxygenStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public OxygenStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public OxygenStorage(int capacity, int maxReceive, int maxExtract, int oxygen) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.oxygen = Math.max(0 , Math.min(capacity, oxygen));
    }

    @Override
    public int receiveOxygen(int maxReceive, boolean simulate) {
        if (!canReceive()) {
            return 0;
        }

        int energyReceived = Math.min(capacity - oxygen, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            oxygen += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractOxygen(int maxExtract, boolean simulate) {
        if (!canExtract()) {
            return 0;
        }

        int energyExtracted = Math.min(oxygen, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            oxygen -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    public int getOxygenStored() {
        return oxygen;
    }

    @Override
    public int getMaxOxygenStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }

    @Override
    public Tag serializeNBT() {
        return IntTag.valueOf(this.getOxygenStored());
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (!(nbt instanceof IntTag intNbt)) {
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        }
        this.oxygen = intNbt.getAsInt();
    }
}
