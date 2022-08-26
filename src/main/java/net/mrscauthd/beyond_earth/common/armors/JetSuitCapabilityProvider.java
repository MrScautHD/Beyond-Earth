package net.mrscauthd.beyond_earth.common.armors;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.mrscauthd.beyond_earth.common.capabilities.energy.EnergyStorageBasic;

public class JetSuitCapabilityProvider extends SpaceSuitCapabilityProvider {

	public static final String KEY_ENERGYSTORAGE = "energystorage";

	private final EnergyStorage energyStorage;

	public JetSuitCapabilityProvider(ItemStack itemStack, int oxygenCapacity, int energyCapacity) {
		super(itemStack, oxygenCapacity);
		this.energyStorage = new EnergyStorageBasic(energyCapacity) {
			@Override
			protected void setChanged() {
				super.setChanged();
				writeEnergy();
			}
		};
		this.readEnergy();
	}

	private void readEnergy() {
		CompoundTag compound = this.getItemStack().getOrCreateTag();
		this.energyStorage.deserializeNBT(compound.getCompound(KEY_ENERGYSTORAGE));
	}

	public void writeEnergy() {
		CompoundTag compound = this.getItemStack().getOrCreateTag();
		compound.put(KEY_ENERGYSTORAGE, this.energyStorage.serializeNBT());
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction direction) {
		if (capability == ForgeCapabilities.ENERGY) {
			return LazyOptional.of(this::getEnergyStorage).cast();
		}

		return super.getCapability(capability, direction);
	}

	public IEnergyStorage getEnergyStorage() {
		return this.energyStorage;
	}

}
