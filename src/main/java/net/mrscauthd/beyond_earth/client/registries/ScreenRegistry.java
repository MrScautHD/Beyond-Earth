package net.mrscauthd.beyond_earth.client.registries;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.LanderScreen;
import net.mrscauthd.beyond_earth.client.screens.planetselection.PlanetSelectionScreen;
import net.mrscauthd.beyond_earth.client.screens.RocketScreen;
import net.mrscauthd.beyond_earth.client.screens.RoverScreen;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ScreenRegistry {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        MenuScreens.register(ContainerRegistry.ROCKET_GUI.get(), RocketScreen::new);
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
        MenuScreens.register(ContainerRegistry.LANDER_GUI.get(), LanderScreen::new);
        MenuScreens.register(ContainerRegistry.ROVER_GUI.get(), RoverScreen::new);
        MenuScreens.register(ContainerRegistry.PLANET_SELECTION_GUI.get(), PlanetSelectionScreen::new);
    }
}
