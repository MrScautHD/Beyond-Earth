package net.mrscauthd.beyond_earth.registries.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.guis.screens.lander.LanderScreen;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionScreen;
import net.mrscauthd.beyond_earth.guis.screens.rocket.RocketScreen;
import net.mrscauthd.beyond_earth.guis.screens.rover.RoverScreen;
import net.mrscauthd.beyond_earth.registries.ContainersRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ScreensRegistry {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        MenuScreens.register(ContainersRegistry.ROCKET_GUI.get(), RocketScreen::new);
        //TODO ADD BACK LAITER
		/*
		MenuScreens.register(ScreensRegistry.COMPRESSOR_GUI.get(), CompressorScreen::new);
		MenuScreens.register(ScreensRegistry.FUEL_REFINERY_GUI.get(), FuelRefineryScreen::new);
		MenuScreens.register(ScreensRegistry.COAL_GENERATOR_GUI.get(), CoalGeneratorScreen::new);
		MenuScreens.register(ScreensRegistry.NASA_WORKBENCH_GUI.get(), NasaWorkbenchGuiWindow::new);
		MenuScreens.register(ScreensRegistry.OXYGEN_LOADER_GUI.get(), OxygenLoaderScreen::new);
		MenuScreens.register(ScreensRegistry.SOLAR_PANEL_GUI.get(), SolarPanelScreen::new);
		MenuScreens.register(ScreensRegistry.WATER_PUMP_GUI.get(), WaterPumpScreen::new);
		MenuScreens.register(ScreensRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_GUI.get(), OxygenBubbleDistributorScreen::new);
		*/
        MenuScreens.register(ContainersRegistry.LANDER_GUI.get(), LanderScreen::new);
        MenuScreens.register(ContainersRegistry.ROVER_GUI.get(), RoverScreen::new);
        MenuScreens.register(ContainersRegistry.PLANET_SELECTION_GUI.get(), PlanetSelectionScreen::new);
    }
}
