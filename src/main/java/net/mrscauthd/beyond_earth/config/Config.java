package net.mrscauthd.beyond_earth.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.mrscauthd.beyond_earth.entities.RoverEntity;
import net.mrscauthd.beyond_earth.machines.tile.*;

public class Config {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	/** Entities */
	public static final ForgeConfigSpec.ConfigValue<Boolean> ALIEN_SPAWN;
	public static final ForgeConfigSpec.ConfigValue<Boolean> ALIEN_ZOMBIE_SPAWN;
	public static final ForgeConfigSpec.ConfigValue<Boolean> STAR_CRAWLER_SPAWN;
	public static final ForgeConfigSpec.ConfigValue<Boolean> PYGRO_SPAWN;
	public static final ForgeConfigSpec.ConfigValue<Boolean> PYGRO_BRUTE_SPAWN;
	public static final ForgeConfigSpec.ConfigValue<Boolean> MOGLER_SPAWN;
	public static final ForgeConfigSpec.ConfigValue<Boolean> MARTIAN_RAPTOR_SPAWN;

	/** Entity Systems */
	public static final ForgeConfigSpec.ConfigValue<Boolean> PLAYER_OXYGEN_SYSTEM;
	public static final ForgeConfigSpec.ConfigValue<Boolean> ENTITY_OXYGEN_SYSTEM;

	/** Machines */
	public static final ForgeConfigSpec.ConfigValue<Integer> COAL_GENERATOR_ENERGY_GENERATION;
	public static final ForgeConfigSpec.ConfigValue<Integer> COAL_GENERATOR_ENERGY_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> COAL_GENERATOR_ENERGY_TRANSFER;

	public static final ForgeConfigSpec.ConfigValue<Integer> SOLAR_PANEL_ENERGY_GENERATION;
	public static final ForgeConfigSpec.ConfigValue<Integer> SOLAR_PANEL_ENERGY_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> SOLAR_PANEL_ENERGY_TRANSFER;

	public static final ForgeConfigSpec.ConfigValue<Integer> COMPRESSOR_ENERGY_USAGE;
	public static final ForgeConfigSpec.ConfigValue<Integer> COMPRESSOR_ENERGY_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> COMPRESSOR_ENERGY_TRANSFER;

	public static final ForgeConfigSpec.ConfigValue<Integer> FUEL_REFINERY_ENERGY_USAGE;
	public static final ForgeConfigSpec.ConfigValue<Integer> FUEL_REFINERY_ENERGY_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> FUEL_REFINERY_ENERGY_TRANSFER;
	public static final ForgeConfigSpec.ConfigValue<Integer> FUEL_REFINERY_TANK_INPUT_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> FUEL_REFINERY_TANK_OUTPUT_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> FUEL_REFINERY_TANK_TRANSFER;

	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_USAGE;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_TRANSFER;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_BUBBLE_DISTRIBUTOR_TANK_FLUID_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_BUBBLE_DISTRIBUTOR_TANK_OXYGEN_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_BUBBLE_DISTRIBUTOR_TANK_TRANSFER;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MIN;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MAX;

	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_LOADER_ENERGY_USAGE;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_LOADER_ENERGY_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_LOADER_ENERGY_TRANSFER;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_LOADER_TANK_FLUID_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_LOADER_TANK_OXYGEN_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_LOADER_TANK_TRANSFER;

	public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PUMP_ENERGY_USAGE;
	public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PUMP_ENERGY_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PUMP_ENERGY_TRANSFER;
	public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PUMP_TANK_CAPACITY;
	public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PUMP_TANK_TRANSFER;
	public static final ForgeConfigSpec.ConfigValue<Integer> WATER_PUMP_WORK_INTERVAL;

	/** Vehicles */
	public static final ForgeConfigSpec.ConfigValue<Integer> ROVER_FUEL_BUCKETS;
	public static final ForgeConfigSpec.ConfigValue<Integer> ROVER_FUEL_USE_TICKS;
	public static final ForgeConfigSpec.ConfigValue<Integer> ROVER_FUEL_USE_AMOUNT;

	static {
		BUILDER.push("Beyond Earth Config");

		/** Entities */
		ALIEN_SPAWN = BUILDER.comment("Enable or Disable Alien to Spawn").define("Alien Spawn", true);
		ALIEN_ZOMBIE_SPAWN = BUILDER.comment("Enable or Disable Alien Zombie to Spawn").define("Alien Zombie Spawn", true);
		STAR_CRAWLER_SPAWN = BUILDER.comment("Enable or Disable Star Crawler to Spawn").define("Star Crawler Spawn", true);
		PYGRO_SPAWN = BUILDER.comment("Enable or Disable Pygro to Spawn").define("Pygro Spawn", true);
		PYGRO_BRUTE_SPAWN = BUILDER.comment("Enable or Disable Pygro Brute to Spawn").define("Pygro Brute Spawn", true);
		MOGLER_SPAWN = BUILDER.comment("Enable or Disable Mogler to Spawn").define("Mogler Spawn", true);
		MARTIAN_RAPTOR_SPAWN = BUILDER.comment("Enable or Disable Martian Raptor to Spawn").define("Martian Raptor Spawn", true);

		/** Entity Systems */
		PLAYER_OXYGEN_SYSTEM = BUILDER.comment("Enable or Disable Player Oxygen System").define("Player Oxygen System", true);
		ENTITY_OXYGEN_SYSTEM = BUILDER.comment("Enable or Disable Entity Oxygen System").define("Entity Oxygen System", true);

		/** Machines */
		BUILDER.push("Machines");

		BUILDER.push("Coal Generator");
		COAL_GENERATOR_ENERGY_GENERATION = BUILDER.comment("Set energy generation per tick, default: " + CoalGeneratorBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyGeneration", CoalGeneratorBlockEntity.DEFAULT_ENERGY_USAGE);
		COAL_GENERATOR_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + CoalGeneratorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", CoalGeneratorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
		COAL_GENERATOR_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + CoalGeneratorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", CoalGeneratorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
		BUILDER.pop();

		BUILDER.push("Solar Panel");
		SOLAR_PANEL_ENERGY_GENERATION = BUILDER.comment("Set energy generation per tick, default: " + SolarPanelBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyGeneration", SolarPanelBlockEntity.DEFAULT_ENERGY_USAGE);
		SOLAR_PANEL_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + SolarPanelBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", SolarPanelBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
		SOLAR_PANEL_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + SolarPanelBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", SolarPanelBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
		BUILDER.pop();

		BUILDER.push("Compressor");
		COMPRESSOR_ENERGY_USAGE = BUILDER.comment("Set energy usage per tick, default: " + CompressorBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyUsage", CompressorBlockEntity.DEFAULT_ENERGY_USAGE);
		COMPRESSOR_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + CompressorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", CompressorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
		COMPRESSOR_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + CompressorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", CompressorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
		BUILDER.pop();

		BUILDER.push("Fuel Refinery");
		FUEL_REFINERY_ENERGY_USAGE = BUILDER.comment("Set energy usage per tick, default: " + FuelRefineryBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyUsage", FuelRefineryBlockEntity.DEFAULT_ENERGY_USAGE);
		FUEL_REFINERY_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + FuelRefineryBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", FuelRefineryBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
		FUEL_REFINERY_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + FuelRefineryBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", FuelRefineryBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
		FUEL_REFINERY_TANK_INPUT_CAPACITY = BUILDER.comment("Set fluid input tank capacity, default: " + FuelRefineryBlockEntity.DEFAULT_TANK_CAPACITY + " mB").define("TankInputCapacity", FuelRefineryBlockEntity.DEFAULT_TANK_CAPACITY);
		FUEL_REFINERY_TANK_OUTPUT_CAPACITY = BUILDER.comment("Set fluid output tank capacity, default: " + FuelRefineryBlockEntity.DEFAULT_TANK_CAPACITY + " mB").define("TankOutputCapacity", FuelRefineryBlockEntity.DEFAULT_TANK_CAPACITY);
		FUEL_REFINERY_TANK_TRANSFER = BUILDER.comment("Set tank transfer, default: " + FuelRefineryBlockEntity.DEFAULT_TANK_TRANSFER + " mB").define("TankTransfer", FuelRefineryBlockEntity.DEFAULT_TANK_TRANSFER);
		BUILDER.pop();

		BUILDER.push("Oxygen Bubble Distributor");
		OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_USAGE = BUILDER.comment("Set energy usage per tick, default: " + OxygenBubbleDistributorBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyUsage", OxygenBubbleDistributorBlockEntity.DEFAULT_ENERGY_USAGE);
		OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + OxygenBubbleDistributorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", OxygenBubbleDistributorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
		OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + OxygenBubbleDistributorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", OxygenBubbleDistributorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
		OXYGEN_BUBBLE_DISTRIBUTOR_TANK_FLUID_CAPACITY = BUILDER.comment("Set fluid input tank capacity, default: " + OxygenBubbleDistributorBlockEntity.DEFAULT_TANK_CAPACITY + " mB").define("FluidCapacity", OxygenBubbleDistributorBlockEntity.DEFAULT_TANK_CAPACITY);
		OXYGEN_BUBBLE_DISTRIBUTOR_TANK_OXYGEN_CAPACITY = BUILDER.comment("Set oxygen output tank capacity, default: " + OxygenBubbleDistributorBlockEntity.DEFAULT_TANK_CAPACITY + " mB").define("OxygenCapacity", OxygenBubbleDistributorBlockEntity.DEFAULT_TANK_CAPACITY);
		OXYGEN_BUBBLE_DISTRIBUTOR_TANK_TRANSFER = BUILDER.comment("Set tank transfer, default: " + OxygenBubbleDistributorBlockEntity.DEFAULT_TANK_TRANSFER + " mB").define("FluidTransfer", OxygenBubbleDistributorBlockEntity.DEFAULT_TANK_TRANSFER);
		BUILDER.comment("Range is mean distance of each side from center", "Working area will be (range * 2) + 1", "For example, when range is 2, then working area is 3x3x3 blocks", "range is 15, then working area is 31x31x31 blocks");
		BUILDER.push("Range");
		OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MIN = BUILDER.comment("Default: " + OxygenBubbleDistributorBlockEntity.DEFAULT_RANGE_MIN + " blocks").defineInRange("RangeMin", OxygenBubbleDistributorBlockEntity.DEFAULT_RANGE_MIN, 1, 49);
		OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MAX = BUILDER.comment("Default: " + OxygenBubbleDistributorBlockEntity.DEFAULT_RANGE_MAX + " blocks").defineInRange("RangeMax", OxygenBubbleDistributorBlockEntity.DEFAULT_RANGE_MAX, 1, 49);
		BUILDER.pop();
		BUILDER.pop();

		BUILDER.push("Oxygen Loader");
		OXYGEN_LOADER_ENERGY_USAGE = BUILDER.comment("Set energy usage per tick, default: " + OxygenLoaderBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyUsage", OxygenLoaderBlockEntity.DEFAULT_ENERGY_USAGE);
		OXYGEN_LOADER_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + OxygenLoaderBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", OxygenLoaderBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
		OXYGEN_LOADER_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + OxygenLoaderBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", OxygenLoaderBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
		OXYGEN_LOADER_TANK_FLUID_CAPACITY = BUILDER.comment("Set fluid input tank capacity, default: " + OxygenLoaderBlockEntity.DEFAULT_TANK_CAPACITY + " mB").define("FluidCapacity", OxygenLoaderBlockEntity.DEFAULT_TANK_CAPACITY);
		OXYGEN_LOADER_TANK_OXYGEN_CAPACITY = BUILDER.comment("Set oxygen output tank capacity, default: " + OxygenLoaderBlockEntity.DEFAULT_TANK_CAPACITY + " mB").define("OxygenCapacity", OxygenLoaderBlockEntity.DEFAULT_TANK_CAPACITY);
		OXYGEN_LOADER_TANK_TRANSFER = BUILDER.comment("Set tank transfer, default: " + OxygenLoaderBlockEntity.DEFAULT_TANK_TRANSFER + " mB").define("FluidTransfer", OxygenLoaderBlockEntity.DEFAULT_TANK_TRANSFER);
		BUILDER.pop();

		BUILDER.push("Water Pump");
		WATER_PUMP_ENERGY_USAGE = BUILDER.comment("Set energy usage per tick, default: " + WaterPumpBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyUsage", WaterPumpBlockEntity.DEFAULT_ENERGY_USAGE);
		WATER_PUMP_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + WaterPumpBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", WaterPumpBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
		WATER_PUMP_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + WaterPumpBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", WaterPumpBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
		WATER_PUMP_TANK_CAPACITY = BUILDER.comment("Set water tank capacity, default: " + WaterPumpBlockEntity.DEFAULT_TANK_CAPACITY + " mB").define("TankCapacity", WaterPumpBlockEntity.DEFAULT_TANK_CAPACITY);
		WATER_PUMP_TANK_TRANSFER = BUILDER.comment("Set water tank transfer, default: " + WaterPumpBlockEntity.DEFAULT_TANK_TRANSFER + " mB").define("TankTransfer", WaterPumpBlockEntity.DEFAULT_TANK_TRANSFER);
		WATER_PUMP_WORK_INTERVAL = BUILDER.comment("Set pumping interval ticks, default: " + WaterPumpBlockEntity.DEFAULT_WORK_INTERVAL + " ticks").define("WorkInterval", WaterPumpBlockEntity.DEFAULT_WORK_INTERVAL);
		BUILDER.pop();

		BUILDER.pop();
		/** End of Machines */

		/** Vehicles */
		BUILDER.push("Vehicles");

		BUILDER.push("Rover");
		ROVER_FUEL_BUCKETS = BUILDER.comment("Set required buckets for fuel full, default: " + RoverEntity.DEFAULT_FUEL_BUCKETS + " buckets").define("FuelBuckets", RoverEntity.DEFAULT_FUEL_BUCKETS);
		ROVER_FUEL_USE_TICKS = BUILDER.comment("Set rover's fuel using interval ticks, default: " + RoverEntity.DEFAULT_FUEL_USE_TICKS + " ticks").define("FuelUseTicks", RoverEntity.DEFAULT_FUEL_USE_TICKS);
		ROVER_FUEL_USE_AMOUNT = BUILDER.comment("Set rover's fuel using amount at every interval, default: " + RoverEntity.DEFAULT_FUEL_USE_AMOUNT + " mb").define("FuelUseAmount", RoverEntity.DEFAULT_FUEL_USE_AMOUNT);
		BUILDER.pop();

		BUILDER.pop();
		/** End of Vehicles */

		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}