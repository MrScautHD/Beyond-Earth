package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.menus.LanderMenu;
import net.mrscauthd.beyond_earth.common.menus.planetselection.PlanetSelectionMenu;
import net.mrscauthd.beyond_earth.common.menus.RocketMenu;
import net.mrscauthd.beyond_earth.common.menus.RoverMenu;

public class ContainerRegistry {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BeyondEarth.MODID);

    /** SCREENS */
    //TODO REWORK THIS WITH MACHINES

    public static final RegistryObject<MenuType<RocketMenu.GuiContainer>> ROCKET_GUI = CONTAINERS.register("rocket_gui", () -> new MenuType(new RocketMenu.GuiContainerFactory()));
    /*
    public static final RegistryObject<MenuType<CompressorMenu.GuiContainer>> COMPRESSOR_GUI = SCREENS.register("compressor_gui", () -> new MenuType(new CompressorMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<FuelRefineryMenu.GuiContainer>> FUEL_REFINERY_GUI = SCREENS.register("fuel_refinery_gui", () -> new MenuType(new FuelRefineryMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<CoalGeneratorMenu.GuiContainer>> COAL_GENERATOR_GUI = SCREENS.register("coal_generator_gui", () -> new MenuType(new CoalGeneratorMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<NasaWorkbenchGui.GuiContainer>> NASA_WORKBENCH_GUI = SCREENS.register("nasa_workbench_gui", () -> new MenuType(new NasaWorkbenchGui.GuiContainerFactory()));
    public static final RegistryObject<MenuType<OxygenLoaderMenu.GuiContainer>> OXYGEN_LOADER_GUI = SCREENS.register("oxygen_loader_gui", () -> new MenuType(new OxygenLoaderMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<SolarPanelMenu.GuiContainer>> SOLAR_PANEL_GUI = SCREENS.register("solar_panel_gui", () -> new MenuType(new SolarPanelMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<WaterPumpMenu.GuiContainer>> WATER_PUMP_GUI = SCREENS.register("water_pump_gui", () -> new MenuType(new WaterPumpMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<OxygenBubbleDistributorMenu.GuiContainer>> OXYGEN_BUBBLE_DISTRIBUTOR_GUI = SCREENS.register("oxygen_bubble_distributor_gui", () -> new MenuType(new OxygenBubbleDistributorMenu.GuiContainerFactory()));
    */
    public static final RegistryObject<MenuType<LanderMenu.GuiContainer>> LANDER_GUI = CONTAINERS.register("lander_gui", () -> new MenuType(new LanderMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<RoverMenu.GuiContainer>> ROVER_GUI = CONTAINERS.register("rover_gui", () -> new MenuType(new RoverMenu.GuiContainerFactory()));
    public static final RegistryObject<MenuType<PlanetSelectionMenu.GuiContainer>> PLANET_SELECTION_GUI = CONTAINERS.register("planet_selection_gui", () -> new MenuType(new PlanetSelectionMenu.GuiContainerFactory()));
}
