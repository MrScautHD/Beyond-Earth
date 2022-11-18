package net.mrscauthd.beyond_earth.common.registries;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.ChunkOxygen;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenProvider;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry {

    public static Capability<ChunkOxygen> CHUNK_OXYGEN = CapabilityManager.get(new CapabilityToken<>() {
    });
    
    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(OxygenProvider.class);
        event.register(ChunkOxygen.class);
    }
}
