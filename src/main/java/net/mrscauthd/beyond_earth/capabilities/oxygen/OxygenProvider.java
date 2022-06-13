package net.mrscauthd.beyond_earth.capabilities.oxygen;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OxygenProvider implements ICapabilityProvider {

    protected IOxygenStorage oxygenStorage;

    public OxygenProvider(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public OxygenProvider(int capacity, int maxReceive) {
        this(capacity, maxReceive, maxReceive, 0);
    }

    public OxygenProvider(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public OxygenProvider(int capacity, int maxReceive, int maxExtract, int oxygen) {
        this.oxygenStorage = new OxygenStorage(capacity, maxReceive, maxExtract, oxygen);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == OxygenCapability.OXYGEN) {
            return LazyOptional.of(() -> oxygenStorage).cast();
        }

        return LazyOptional.empty();
    }
}
