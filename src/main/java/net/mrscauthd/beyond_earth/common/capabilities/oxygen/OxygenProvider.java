package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class OxygenProvider implements INBTSerializable<CompoundTag> {
    public static Capability<OxygenStorage> OXYGEN = CapabilityManager.get(new CapabilityToken<>(){});

    private OxygenStorage oxygenStorage;
    private final int capacity;
    private final LazyOptional<OxygenStorage> cap;

    public OxygenProvider(int capacity) {
        this.capacity = capacity;
        this.cap = LazyOptional.of(this::getOxygenStorage);
    }

    private OxygenStorage getOxygenStorage() {
        if (this.oxygenStorage == null) {
            this.oxygenStorage = new OxygenStorage();
            this.oxygenStorage.setMaxCapacity(this.capacity);
        }

        return this.oxygenStorage;
    }

    public LazyOptional<OxygenStorage> getCap() {
        return this.cap.cast();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("oxygenStorage", this.getOxygenStorage().getOxygen());
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.getOxygenStorage().setOxygen(nbt.getInt("oxygenStorage"));
    }
}
