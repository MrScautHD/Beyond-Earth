package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OxygenProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<OxygenStorage> OXYGEN = CapabilityManager.get(new CapabilityToken<>() {
    });

    private OxygenStorage oxygenStorage;
    private final int capacity;

    public OxygenProvider(int capacity) {
        this.capacity = capacity;
    }

    private OxygenStorage getOxygenStorage() {
        if (this.oxygenStorage == null) {
            this.oxygenStorage = new OxygenStorage();
            this.oxygenStorage.setMaxCapacity(this.capacity);
        }

        return this.oxygenStorage;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        LazyOptional<T> oxygenCapability = OxygenUtil.getOxygenCapability(cap, this::getOxygenStorage);

        if (oxygenCapability.isPresent()) {
            return oxygenCapability;
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.getOxygenStorage().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.getOxygenStorage().deserializeNBT(nbt);
    }
}
