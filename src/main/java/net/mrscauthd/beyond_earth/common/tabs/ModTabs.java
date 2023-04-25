package net.mrscauthd.beyond_earth.common.tabs;

import net.minecraftforge.event.CreativeModeTabEvent;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class ModTabs {
	public static void addCreativeTab(CreativeModeTabEvent.BuildContents builder) {
		if (builder.getTab() == TabsRegistry.DEFAULT) {
			builder.accept(ItemsRegistry.TIER_1_ROCKET_ITEM);
			builder.accept(ItemsRegistry.TIER_2_ROCKET_ITEM);
			builder.accept(ItemsRegistry.TIER_3_ROCKET_ITEM);
			builder.accept(ItemsRegistry.TIER_4_ROCKET_ITEM);
			builder.accept(ItemsRegistry.ROVER_ITEM);
			builder.accept(ItemsRegistry.SPACE_HELMET);
			builder.accept(ItemsRegistry.SPACE_SUIT);
			builder.accept(ItemsRegistry.SPACE_PANTS);
			builder.accept(ItemsRegistry.SPACE_BOOTS);
			builder.accept(ItemsRegistry.NETHERITE_SPACE_HELMET);
			builder.accept(ItemsRegistry.NETHERITE_SPACE_SUIT);
			builder.accept(ItemsRegistry.NETHERITE_SPACE_PANTS);
			builder.accept(ItemsRegistry.NETHERITE_SPACE_BOOTS);
			builder.accept(ItemsRegistry.JET_HELMET);
			builder.accept(ItemsRegistry.JET_SUIT);
			builder.accept(ItemsRegistry.JET_PANTS);
			builder.accept(ItemsRegistry.JET_BOOTS);
			builder.accept(ItemsRegistry.ROCKET_LAUNCH_PAD_ITEM);
			builder.accept(ItemsRegistry.FLAG_ITEM);
			builder.accept(ItemsRegistry.COAL_TORCH_ITEM);
			builder.accept(ItemsRegistry.COAL_LANTERN_ITEM);
			builder.accept(ItemsRegistry.CHEESE);
			builder.accept(ItemsRegistry.FUEL_BUCKET);
			builder.accept(ItemsRegistry.OIL_BUCKET);
		}

		if (builder.getTab() == TabsRegistry.MACHINES) {
			builder.accept(ItemsRegistry.NASA_WORKBENCH_ITEM);
			builder.accept(ItemsRegistry.SOLAR_PANEL_ITEM);
			builder.accept(ItemsRegistry.COAL_GENERATOR_ITEM);
			builder.accept(ItemsRegistry.COMPRESSOR_ITEM);
			builder.accept(ItemsRegistry.FUEL_REFINERY_ITEM);
			builder.accept(ItemsRegistry.OXYGEN_LOADER_ITEM);
			builder.accept(ItemsRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_ITEM);
			builder.accept(ItemsRegistry.WATER_PUMP_ITEM);
		}

		if (builder.getTab() == TabsRegistry.BASICS) {
			builder.accept(ItemsRegistry.COAL_TORCH_ITEM);
			builder.accept(ItemsRegistry.HAMMER);
			builder.accept(ItemsRegistry.IRON_ROD);
			builder.accept(ItemsRegistry.OXYGEN_GEAR);
			builder.accept(ItemsRegistry.OXYGEN_TANK);
			builder.accept(ItemsRegistry.WHEEL);
			builder.accept(ItemsRegistry.ENGINE_FAN);
			builder.accept(ItemsRegistry.ENGINE_FRAME);
			builder.accept(ItemsRegistry.ROCKET_FIN);
			builder.accept(ItemsRegistry.ROCKET_NOSE_CONE);
			builder.accept(ItemsRegistry.STEEL_ENGINE);
			builder.accept(ItemsRegistry.DESH_ENGINE);
			builder.accept(ItemsRegistry.OSTRUM_ENGINE);
			builder.accept(ItemsRegistry.CALORITE_ENGINE);
			builder.accept(ItemsRegistry.STEEL_TANK);
			builder.accept(ItemsRegistry.DESH_TANK);
			builder.accept(ItemsRegistry.OSTRUM_TANK);
			builder.accept(ItemsRegistry.CALORITE_TANK);
		}

		if (builder.getTab() == TabsRegistry.MATERIALS) {
			builder.accept(ItemsRegistry.STEEL_INGOT);
			builder.accept(ItemsRegistry.DESH_INGOT);
			builder.accept(ItemsRegistry.OSTRUM_INGOT);
			builder.accept(ItemsRegistry.CALORITE_INGOT);
			builder.accept(ItemsRegistry.ICE_SHARD);
			builder.accept(ItemsRegistry.IRON_PLATE);
			builder.accept(ItemsRegistry.DESH_PLATE);
			builder.accept(ItemsRegistry.COMPRESSED_STEEL);
			builder.accept(ItemsRegistry.COMPRESSED_DESH);
			builder.accept(ItemsRegistry.COMPRESSED_OSTRUM);
			builder.accept(ItemsRegistry.COMPRESSED_CALORITE);
			builder.accept(ItemsRegistry.STEEL_NUGGET);
			builder.accept(ItemsRegistry.DESH_NUGGET);
			builder.accept(ItemsRegistry.OSTRUM_NUGGET);
			builder.accept(ItemsRegistry.CALORITE_NUGGET);
			builder.accept(ItemsRegistry.RAW_DESH);
			builder.accept(ItemsRegistry.RAW_OSTRUM);
			builder.accept(ItemsRegistry.RAW_CALORITE);
		}

		if (builder.getTab() == TabsRegistry.GLOBES) {
			builder.accept(ItemsRegistry.EARTH_GLOBE_ITEM);
			builder.accept(ItemsRegistry.MOON_GLOBE_ITEM);
			builder.accept(ItemsRegistry.MARS_GLOBE_ITEM);
			builder.accept(ItemsRegistry.VENUS_GLOBE_ITEM);
			builder.accept(ItemsRegistry.MERCURY_GLOBE_ITEM);
			builder.accept(ItemsRegistry.GLACIO_GLOBE_ITEM);
		}

		if (builder.getTab() == TabsRegistry.BLOCKS) {
			builder.accept(ItemsRegistry.STEEL_BLOCK_ITEM);
			builder.accept(ItemsRegistry.DESH_BLOCK_ITEM);
			builder.accept(ItemsRegistry.OSTRUM_BLOCK_ITEM);
			builder.accept(ItemsRegistry.CALORITE_BLOCK_ITEM);
			builder.accept(ItemsRegistry.RAW_DESH_BLOCK_ITEM);
			builder.accept(ItemsRegistry.RAW_OSTRUM_BLOCK_ITEM);
			builder.accept(ItemsRegistry.RAW_CALORITE_BLOCK_ITEM);
			builder.accept(ItemsRegistry.IRON_PLATING_BLOCK_ITEM);
			builder.accept(ItemsRegistry.IRON_MARK_BLOCK_ITEM);
			builder.accept(ItemsRegistry.DESH_PILLAR_BLOCK_ITEM);
			builder.accept(ItemsRegistry.DESH_PLATING_BLOCK_ITEM);
			builder.accept(ItemsRegistry.BLUE_IRON_PILLAR_BLOCK_ITEM);
			builder.accept(ItemsRegistry.INFERNAL_SPIRE_ITEM);
			builder.accept(ItemsRegistry.BARRICADE_BLOCK_ITEM);
			builder.accept(ItemsRegistry.METEORITE_ITEM);
			builder.accept(ItemsRegistry.MOON_STONE_ITEM);
			builder.accept(ItemsRegistry.MOON_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.MOON_STONE_BRICK_SLAB_ITEM);
			builder.accept(ItemsRegistry.MOON_STONE_BRICK_STAIRS_ITEM);
			builder.accept(ItemsRegistry.CRACKED_MOON_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.MARS_STONE_ITEM);
			builder.accept(ItemsRegistry.MARS_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.MARS_STONE_BRICK_SLAB_ITEM);
			builder.accept(ItemsRegistry.MARS_STONE_BRICK_STAIRS_ITEM);
			builder.accept(ItemsRegistry.CRACKED_MARS_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.MERCURY_STONE_ITEM);
			builder.accept(ItemsRegistry.MERCURY_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.MERCURY_STONE_BRICK_SLAB_ITEM);
			builder.accept(ItemsRegistry.MERCURY_STONE_BRICK_STAIRS_ITEM);
			builder.accept(ItemsRegistry.CRACKED_MERCURY_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.VENUS_STONE_ITEM);
			builder.accept(ItemsRegistry.VENUS_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.VENUS_STONE_BRICK_SLAB_ITEM);
			builder.accept(ItemsRegistry.VENUS_STONE_BRICK_STAIRS_ITEM);
			builder.accept(ItemsRegistry.CRACKED_VENUS_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.VENUS_SANDSTONE_ITEM);
			builder.accept(ItemsRegistry.VENUS_SANDSTONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.VENUS_SANDSTONE_BRICK_SLAB_ITEM);
			builder.accept(ItemsRegistry.VENUS_SANDSTONE_BRICK_STAIRS_ITEM);
			builder.accept(ItemsRegistry.CRACKED_VENUS_SANDSTONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.PERMAFROST_ITEM);
			builder.accept(ItemsRegistry.GLACIO_STONE_ITEM);
			builder.accept(ItemsRegistry.GLACIO_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.GLACIO_STONE_BRICK_SLAB_ITEM);
			builder.accept(ItemsRegistry.GLACIO_STONE_BRICK_STAIRS_ITEM);
			builder.accept(ItemsRegistry.CRACKED_GLACIO_STONE_BRICKS_ITEM);
			builder.accept(ItemsRegistry.MOON_SAND_ITEM);
			builder.accept(ItemsRegistry.MARS_SAND_ITEM);
			builder.accept(ItemsRegistry.VENUS_SAND_ITEM);
			builder.accept(ItemsRegistry.MOON_CHEESE_ORE_ITEM);
			builder.accept(ItemsRegistry.MOON_DESH_ORE_ITEM);
			builder.accept(ItemsRegistry.MOON_IRON_ORE_ITEM);
			builder.accept(ItemsRegistry.MOON_ICE_SHARD_ITEM);
			builder.accept(ItemsRegistry.MARS_IRON_ORE_ITEM);
			builder.accept(ItemsRegistry.MARS_DIAMOND_ORE_ITEM);
			builder.accept(ItemsRegistry.MARS_OSTRUM_ORE_ITEM);
			builder.accept(ItemsRegistry.MARS_ICE_SHARD_ORE_ITEM);
			builder.accept(ItemsRegistry.MERCURY_IRON_ORE_ITEM);
			builder.accept(ItemsRegistry.VENUS_COAL_ORE_ITEM);
			builder.accept(ItemsRegistry.VENUS_GOLD_ORE_ITEM);
			builder.accept(ItemsRegistry.VENUS_DIAMOND_ORE_ITEM);
			builder.accept(ItemsRegistry.VENUS_CALORITE_ORE_ITEM);
			builder.accept(ItemsRegistry.GLACIO_ICE_SHARD_ORE_ITEM);
			builder.accept(ItemsRegistry.GLACIO_COAL_ORE_ITEM);
			builder.accept(ItemsRegistry.GLACIO_COPPER_ORE_ITEM);
			builder.accept(ItemsRegistry.GLACIO_IRON_ORE_ITEM);
			builder.accept(ItemsRegistry.GLACIO_LAPIS_ORE_ITEM);
		}

		if (builder.getTab() == TabsRegistry.SPAWN_EGG) {
			builder.accept(ItemsRegistry.ALIEN_SPAWN_EGG);
			builder.accept(ItemsRegistry.ALIEN_ZOMBIE_SPAWN_EGG);
			builder.accept(ItemsRegistry.STAR_CRAWLER_SPAWN_EGG);
			builder.accept(ItemsRegistry.PYGRO_SPAWN_EGG);
			builder.accept(ItemsRegistry.PYGRO_BRUTE_SPAWN_EGG);
			builder.accept(ItemsRegistry.MOGLER_SPAWN_EGG);
			builder.accept(ItemsRegistry.MARTIAN_RAPTOR_SPAWN_EGG);
		}
	}
}
