package net.mrscauthd.beyond_earth.common.capabilities.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageBasic extends EnergyStorage {

	public EnergyStorageBasic(int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
	}

	public EnergyStorageBasic(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}

	public EnergyStorageBasic(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}

	public EnergyStorageBasic(int capacity) {
		super(capacity);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (!this.canReceive()) {
			return 0;
		} else {
			int energyReceived = Math.min(this.getMaxEnergyStored() - this.getEnergyStored(), Math.min(this.getMaxReceive(), maxReceive));
			if (!simulate && energyReceived > 0) {
				this.energy += energyReceived;
				this.onEnergyChanged(+energyReceived);
			}

			return energyReceived;
		}
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (!this.canExtract()) {
			return 0;
		} else {
			int energyExtracted = Math.min(this.getEnergyStored(), Math.min(this.getMaxExtract(), maxExtract));
			if (!simulate && energyExtracted > 0) {
				this.energy -= energyExtracted;
				this.onEnergyChanged(-energyExtracted);
			}

			return energyExtracted;
		}
	}

	public int getMaxExtract() {
		return this.maxExtract;
	}

	@Override
	public boolean canExtract() {
		return this.getMaxExtract() > 0;
	}

	public int getMaxReceive() {
		return this.maxReceive;
	}

	@Override
	public boolean canReceive() {
		return this.getMaxReceive() > 0;
	}

	protected void onEnergyChanged(int energyDelta) {
		this.setChanged();
	}

	protected void setChanged() {

	}

	@Override
	public Tag serializeNBT() {
		CompoundTag compound = new CompoundTag();
		compound.putInt("energy", this.energy);
		return compound;
	}

	@Override
	public void deserializeNBT(Tag tag) {
		if (tag instanceof CompoundTag) {
			this.energy = ((CompoundTag) tag).getInt("energy");
		}
	}
}
