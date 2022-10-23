package net.mrscauthd.beyond_earth.client.registries;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.CoalGeneratorScreen;
import net.mrscauthd.beyond_earth.client.screens.CompressorScreen;
import net.mrscauthd.beyond_earth.client.screens.FuelRefineryScreen;
import net.mrscauthd.beyond_earth.client.screens.LanderScreen;
import net.mrscauthd.beyond_earth.client.screens.NasaWorkbenchScreen;
import net.mrscauthd.beyond_earth.client.screens.OxygenDistributorScreen;
import net.mrscauthd.beyond_earth.client.screens.OxygenLoaderScreen;
import net.mrscauthd.beyond_earth.client.screens.RocketScreen;
import net.mrscauthd.beyond_earth.client.screens.RoverScreen;
import net.mrscauthd.beyond_earth.client.screens.SolarPanelScreen;
import net.mrscauthd.beyond_earth.client.screens.WaterPumpScreen;
import net.mrscauthd.beyond_earth.client.screens.planetselection.PlanetSelectionScreen;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ScreenRegistry {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        MenuScreens.register(ContainerRegistry.ROCKET_GUI.get(), RocketScreen::new);

        MenuScreens.register(ContainerRegistry.COMPRESSOR_GUI.get(), CompressorScreen::new);
        MenuScreens.register(ContainerRegistry.FUEL_REFINERY_GUI.get(), FuelRefineryScreen::new);
        MenuScreens.register(ContainerRegistry.COAL_GENERATOR_GUI.get(), CoalGeneratorScreen::new);
        MenuScreens.register(ContainerRegistry.NASA_WORKBENCH_GUI.get(), NasaWorkbenchScreen::new);
        MenuScreens.register(ContainerRegistry.OXYGEN_LOADER_GUI.get(), OxygenLoaderScreen::new);
        MenuScreens.register(ContainerRegistry.SOLAR_PANEL_GUI.get(), SolarPanelScreen::new);
        MenuScreens.register(ContainerRegistry.WATER_PUMP_GUI.get(), WaterPumpScreen::new);
        MenuScreens.register(ContainerRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_GUI.get(), OxygenDistributorScreen::new);

        MenuScreens.register(ContainerRegistry.LANDER_GUI.get(), LanderScreen::new);
        MenuScreens.register(ContainerRegistry.ROVER_GUI.get(), RoverScreen::new);
        MenuScreens.register(ContainerRegistry.PLANET_SELECTION_GUI.get(), PlanetSelectionScreen::new);
    }
}
