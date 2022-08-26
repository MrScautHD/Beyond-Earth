package net.mrscauthd.beyond_earth.common.armors;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenUtil;

public class SpaceSuitCapabilityProvider implements ICapabilityProvider {

	public static final String KEY_OXYGENSTORAGE = "oyxgenstorage";

	private final ItemStack itemStack;
	private final OxygenStorage oxygenStorage;

	public SpaceSuitCapabilityProvider(ItemStack itemStack, int oxygenCapacity) {
		this.itemStack = itemStack;
		this.oxygenStorage = new OxygenStorage(oxygenCapacity) {
			@Override
			protected void setChanged() {
				super.setChanged();
				writeOxygen();
			}
		};
		this.readOxygen();
	}

	private void readOxygen() {
		CompoundTag compound = this.getItemStack().getOrCreateTag();
		this.oxygenStorage.deserializeNBT(compound.getCompound(KEY_OXYGENSTORAGE));
	}

	public void writeOxygen() {
		CompoundTag compound = this.getItemStack().getOrCreateTag();
		compound.put(KEY_OXYGENSTORAGE, this.oxygenStorage.serializeNBT());
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction direction) {
		LazyOptional<T> oxygenStorageOptional = OxygenUtil.getOptionalOxygenStorage(capability, direction, this::getOxygenStorage);

		if (oxygenStorageOptional.isPresent()) {
			return oxygenStorageOptional;
		}

		return LazyOptional.empty();
	}

	public ItemStack getItemStack() {
		return this.itemStack;
	}

	public IOxygenStorage getOxygenStorage() {
		return this.oxygenStorage;
	}
}
