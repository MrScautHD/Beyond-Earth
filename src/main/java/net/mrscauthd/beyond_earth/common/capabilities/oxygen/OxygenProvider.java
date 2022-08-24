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
    public static Capability<OxygenStorage> OXYGEN = CapabilityManager.get(new CapabilityToken<>(){});

    private OxygenStorage oxygen;
    private int capacity;
    private final LazyOptional<OxygenStorage> cap;

    public OxygenProvider(int capacity) {
        this.capacity = capacity;
        this.cap = LazyOptional.of(this::getCap);
    }

    private OxygenStorage getCap() {
        if (this.oxygen == null) {
            this.oxygen = new OxygenStorage();
            this.oxygen.setMaxCapacity(this.capacity);
        }

        return this.oxygen;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == OXYGEN) {
            return this.cap.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("oxygen", this.getCap().getOxygen());
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.getCap().setOxygen(nbt.getInt("oxygen"));
    }
}
