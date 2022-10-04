package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.menus.CoalGeneratorMenu;
import net.mrscauthd.beyond_earth.common.menus.CompressorMenu;
import net.mrscauthd.beyond_earth.common.menus.FuelRefineryMenu;
import net.mrscauthd.beyond_earth.common.menus.LanderMenu;
import net.mrscauthd.beyond_earth.common.menus.OxygenBubbleDistributorMenu;
import net.mrscauthd.beyond_earth.common.menus.OxygenLoaderMenu;
import net.mrscauthd.beyond_earth.common.menus.RocketMenu;
import net.mrscauthd.beyond_earth.common.menus.RoverMenu;
import net.mrscauthd.beyond_earth.common.menus.SolarPanelMenu;
import net.mrscauthd.beyond_earth.common.menus.WaterPumpMenu;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.NasaWorkbenchMenu;
import net.mrscauthd.beyond_earth.common.menus.planetselection.PlanetSelectionMenu;

public class ContainerRegistry {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BeyondEarth.MODID);

    /** SCREENS */
    public static final RegistryObject<MenuType<RocketMenu.GuiContainer>> ROCKET_GUI = CONTAINERS.register("rocket_gui", () -> new MenuType<>(new RocketMenu.GuiContainerFactory()));
    
    public static final RegistryObject<MenuType<CompressorMenu.GuiContainer>> COMPRESSOR_GUI = CONTAINERS.register("compressor_gui", () -> new MenuType<>(new CompressorMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<FuelRefineryMenu.GuiContainer>> FUEL_REFINERY_GUI = CONTAINERS.register("fuel_refinery_gui", () -> new MenuType<>(new FuelRefineryMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<CoalGeneratorMenu.GuiContainer>> COAL_GENERATOR_GUI = CONTAINERS.register("coal_generator_gui", () -> new MenuType<>(new CoalGeneratorMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<NasaWorkbenchMenu.GuiContainer>> NASA_WORKBENCH_GUI = CONTAINERS.register("nasa_workbench_gui", () -> new MenuType<>(new NasaWorkbenchMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<OxygenLoaderMenu.GuiContainer>> OXYGEN_LOADER_GUI = CONTAINERS.register("oxygen_loader_gui", () -> new MenuType<>(new OxygenLoaderMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<SolarPanelMenu.GuiContainer>> SOLAR_PANEL_GUI = CONTAINERS.register("solar_panel_gui", () -> new MenuType<>(new SolarPanelMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<WaterPumpMenu.GuiContainer>> WATER_PUMP_GUI = CONTAINERS.register("water_pump_gui", () -> new MenuType<>(new WaterPumpMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<OxygenBubbleDistributorMenu.GuiContainer>> OXYGEN_BUBBLE_DISTRIBUTOR_GUI = CONTAINERS.register("oxygen_bubble_distributor_gui", () -> new MenuType<>(new OxygenBubbleDistributorMenu.GuiContainerFactory()));
    
    public static final RegistryObject<MenuType<LanderMenu.GuiContainer>> LANDER_GUI = CONTAINERS.register("lander_gui", () -> new MenuType<>(new LanderMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<RoverMenu.GuiContainer>> ROVER_GUI = CONTAINERS.register("rover_gui", () -> new MenuType<>(new RoverMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<PlanetSelectionMenu.GuiContainer>> PLANET_SELECTION_GUI = CONTAINERS.register("planet_selection_gui", () -> new MenuType<>(new PlanetSelectionMenu.GuiContainerFactory()));
}
