package net.mrscauthd.beyond_earth.registries.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.overlays.Overlays;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlaysRegistry {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        OverlayRegistry.registerOverlayTop("warning", Overlays.WARNING);
        OverlayRegistry.registerOverlayTop("rocket_timer", Overlays.ROCKET_TIMER);
        OverlayRegistry.registerOverlayBottom("oxygen_tank", Overlays.OXYGEN_TANK);
        OverlayRegistry.registerOverlayBottom("rocket_height", Overlays.ROCKET_HEIGHT);
    }
}
