package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class OxygenStorage implements IOxygenStorage, INBTSerializable<CompoundTag> {
	private static final String KEY_OXYGEN = "oxygen";

	protected int oxygen;
	protected int capacity;

	public OxygenStorage(int capacity) {
		this(capacity, 0);
	}

	public OxygenStorage(int capacity, int oxygen) {
		this.capacity = capacity;
		this.oxygen = Math.max(0, Math.min(capacity, oxygen));
	}

	public int receiveOxygen(int maxReceive, boolean simulate) {
		int oxygen = this.getOxygen();
		int oxygenReceived = Math.min(this.getMaxCapacity() - oxygen, Math.max(0, maxReceive));

		if (!simulate) {
			this.setOxygen(oxygen + oxygenReceived);
		}

		return oxygenReceived;
	}

	public int extractOxygen(int maxExtract, boolean simulate) {
		int oxygen = this.getOxygen();
		int oxygenExtracted = Math.min(oxygen, Math.max(0, maxExtract));

		if (!simulate) {
			this.setOxygen(oxygen - oxygenExtracted);
		}

		return oxygenExtracted;
	}

	protected void onOxygenChanged(int oxygenDelta) {
		this.setChanged();
	}

	protected void setChanged() {

	}

	public int getOxygen() {
		return this.oxygen;
	}

	@Override
	public void setOxygen(int oxygen) {
		oxygen = Math.max(Math.min(oxygen, this.getMaxCapacity()), 0);
		int oxygenPrev = this.getOxygen();

		if (oxygenPrev != oxygen) {
			this.oxygen = oxygen;
			this.onOxygenChanged(oxygen - oxygenPrev);
		}
	}

	public int getMaxCapacity() {
		return this.capacity;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag compound = new CompoundTag();
		compound.putInt(KEY_OXYGEN, this.oxygen);
		return compound;
	}

	@Override
	public void deserializeNBT(CompoundTag compound) {
		this.oxygen = compound.getInt(KEY_OXYGEN);
	}

}
