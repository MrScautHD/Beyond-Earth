package net.mrscauthd.beyond_earth.client.registries;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.overlays.OxygenTankOverlay;
import net.mrscauthd.beyond_earth.client.overlays.RocketHeightBarOverlay;
import net.mrscauthd.beyond_earth.client.overlays.RocketTimerOverlay;
import net.mrscauthd.beyond_earth.client.overlays.WarningOverlay;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlaysRegistry {

    @SubscribeEvent
    public static void register(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("warning", new WarningOverlay());
        event.registerAboveAll("rocket_timer", new RocketTimerOverlay());
        event.registerBelowAll("oxygen_tank", new OxygenTankOverlay());
        event.registerBelowAll("rocket_height", new RocketHeightBarOverlay());
    }
}
