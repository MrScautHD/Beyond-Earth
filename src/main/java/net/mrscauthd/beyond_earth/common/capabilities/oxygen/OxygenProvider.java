package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OxygenProvider implements ICapabilityProvider, IOxygenStorageHolder {
    public static Capability<OxygenStorage> OXYGEN = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final String KEY_OXYGEN = "Energy"; // for compatible other code

    private final ItemStack itemStack;
    private final IOxygenStorage oxygenStorage;

    public OxygenProvider(ItemStack itemStack, int capacity) {
        this.itemStack = itemStack;
        this.oxygenStorage = new OxygenStorage(this, capacity);

        this.readOxygen();
    }

    private void readOxygen() {
        CompoundTag compound = this.getItemStack().getOrCreateTag();
        this.getOxygenStorage().setOxygen(compound.getInt(KEY_OXYGEN));
    }

    public void writeOxygen() {
        CompoundTag compound = this.getItemStack().getOrCreateTag();
        compound.putInt(KEY_OXYGEN, this.getOxygenStorage().getOxygen());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction direction) {
        LazyOptional<T> oxygenCapability = OxygenUtil.getOxygenCapability(capability, this::getOxygenStorage);

        if (oxygenCapability.isPresent()) {
            return oxygenCapability;
        }

        return LazyOptional.empty();
    }

    @Override
    public void onOxygenChanged(IOxygenStorage oxygenStorage, int oxygenDelta) {
        this.writeOxygen();
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public IOxygenStorage getOxygenStorage() {
        return this.oxygenStorage;
    }
}
