package net.mrscauthd.beyond_earth.capabilities.oxygen;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class OxygenCapability {
    public static Capability<OxygenStorage> OXYGEN = CapabilityManager.get(new CapabilityToken<>() {
    });
}
