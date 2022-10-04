package net.mrscauthd.beyond_earth.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.mrscauthd.beyond_earth.common.blocks.entities.CoalGeneratorBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.CompressorBlockEntity;

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

        public static final ForgeConfigSpec.ConfigValue<Integer> COMPRESSOR_ENERGY_USAGE;
        public static final ForgeConfigSpec.ConfigValue<Integer> COMPRESSOR_ENERGY_CAPACITY;
        public static final ForgeConfigSpec.ConfigValue<Integer> COMPRESSOR_ENERGY_TRANSFER;
	
        /** Steel Management */
        public static final ForgeConfigSpec.ConfigValue<Integer> STEEL_MANAGEMENT;

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

                BUILDER.pop();
		
	              /** Machines */
                BUILDER.push("Machines");

                BUILDER.push("Coal Generator");
                COAL_GENERATOR_ENERGY_GENERATION = BUILDER.comment("Set energy generation per tick, default: " + CoalGeneratorBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyGeneration", CoalGeneratorBlockEntity.DEFAULT_ENERGY_USAGE);
                COAL_GENERATOR_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + CoalGeneratorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", CoalGeneratorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
                COAL_GENERATOR_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + CoalGeneratorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", CoalGeneratorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
                BUILDER.pop();
                BUILDER.push("Compressor");
                COMPRESSOR_ENERGY_USAGE = BUILDER.comment("Set energy usage per tick, default: " + CompressorBlockEntity.DEFAULT_ENERGY_USAGE + " FE/t").define("EnergyUsage", CompressorBlockEntity.DEFAULT_ENERGY_USAGE);
                COMPRESSOR_ENERGY_CAPACITY = BUILDER.comment("Set energy capacity, default: " + CompressorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY + " FE").define("EnergyCapacity", CompressorBlockEntity.DEFAULT_ENERGY_STORAGE_CAPACITY);
                COMPRESSOR_ENERGY_TRANSFER = BUILDER.comment("Set energy transfer, default: " + CompressorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER + " FE").define("EnergyTransfer", CompressorBlockEntity.DEFAULT_ENERGY_STORAGE_TRANSFER);
                BUILDER.pop();
                
                BUILDER.pop();
		

                BUILDER.push("Steel");
                /** Steel Management */
                STEEL_MANAGEMENT = BUILDER.comment("Management Steel Items", "0: Default, Nothing special, Use steel items as-is", "1: Iron Ingot can't blasting into be steel ingot", "2: Additionally, Steel Block and Ingot and Nugget are can't conversion to each shape", "    And hide Steel Block, Ingot, Nugget in JEI").defineInRange("Steel Management", 0, 0, 2);

                BUILDER.pop();
		SPEC = BUILDER.build();
	}
}