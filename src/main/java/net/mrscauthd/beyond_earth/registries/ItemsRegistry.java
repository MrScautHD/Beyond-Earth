package net.mrscauthd.beyond_earth.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.armormaterials.JetSuitMaterial;
import net.mrscauthd.beyond_earth.armormaterials.NetheriteSpaceSuitMaterial;
import net.mrscauthd.beyond_earth.armormaterials.SpaceSuitMaterial;
import net.mrscauthd.beyond_earth.armors.JetSuit;
import net.mrscauthd.beyond_earth.armors.NetheriteSpaceSuit;
import net.mrscauthd.beyond_earth.armors.SpaceSuit;
import net.mrscauthd.beyond_earth.globe.GlobeItem;
import net.mrscauthd.beyond_earth.tabs.Tabs;
import net.mrscauthd.beyond_earth.items.*;

public class ItemsRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeyondEarth.MODID);

    /** ROCKET ITEMS */
    public static final RegistryObject<Tier1RocketItem> TIER_1_ROCKET_ITEM = ITEMS.register("rocket_t1", () -> new Tier1RocketItem(new Item.Properties().tab(Tabs.tab_normal).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Tier2RocketItem> TIER_2_ROCKET_ITEM = ITEMS.register("rocket_t2", () -> new Tier2RocketItem(new Item.Properties().tab(Tabs.tab_normal).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Tier3RocketItem> TIER_3_ROCKET_ITEM = ITEMS.register("rocket_t3", () -> new Tier3RocketItem(new Item.Properties().tab(Tabs.tab_normal).rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Tier4RocketItem> TIER_4_ROCKET_ITEM = ITEMS.register("rocket_t4", () -> new Tier4RocketItem(new Item.Properties().tab(Tabs.tab_normal).rarity(Rarity.RARE).stacksTo(1)));

    /** ROVER ITEMS */
    public static final RegistryObject<RoverItem> ROVER_ITEM = ITEMS.register("rover", () -> new RoverItem(new Item.Properties().tab(Tabs.tab_normal).stacksTo(1).rarity(Rarity.RARE)));

    /** SPAWN EGG ITEMS */
    public static final RegistryObject<ForgeSpawnEggItem> ALIEN_SPAWN_EGG = ITEMS.register("alien_spawn_egg", () -> new ForgeSpawnEggItem(EntitiesRegistry.ALIEN::get, -13382401, -11650781, new Item.Properties().tab(Tabs.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> ALIEN_ZOMBIE_SPAWN_EGG = ITEMS.register("alien_zombie_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.ALIEN_ZOMBIE::get, -14804199, -16740159, new Item.Properties().tab(Tabs.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> STAR_CRAWLER_SPAWN_EGG = ITEMS.register("star_crawler_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.STAR_CRAWLER::get, -13421773, -16724788, new Item.Properties().tab(Tabs.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> PYGRO_SPAWN_EGG = ITEMS.register("pygro_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.PYGRO::get, -3381760, -6750208, new Item.Properties().tab(Tabs.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> PYGRO_BRUTE_SPAWN_EGG = ITEMS.register("pygro_brute_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.PYGRO_BRUTE::get, -3381760, -67208, new Item.Properties().tab(Tabs.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> MOGLER_SPAWN_EGG = ITEMS.register("mogler_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.MOGLER::get, -13312, -3407872, new Item.Properties().tab(Tabs.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> MARTIAN_RAPTOR_SPAWN_EGG = ITEMS.register("martian_raptor_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.MARTIAN_RAPTOR::get, 5349438, -13312, new Item.Properties().tab(Tabs.tab_spawn_eggs)));

    /** GLOBE ITEMS */
    public static final RegistryObject<BlockItem> EARTH_GLOBE_ITEM = ITEMS.register("earth_globe", () -> new GlobeItem(BlocksRegistry.EARTH_GLOBE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_globes).rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/blocks/globes/earth_globe.png")));
    public static final RegistryObject<BlockItem> MOON_GLOBE_ITEM = ITEMS.register("moon_globe", () -> new GlobeItem(BlocksRegistry.MOON_GLOBE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_globes).rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/blocks/globes/moon_globe.png")));
    public static final RegistryObject<BlockItem> MARS_GLOBE_ITEM = ITEMS.register("mars_globe", () -> new GlobeItem(BlocksRegistry.MARS_GLOBE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_globes).rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/blocks/globes/mars_globe.png")));
    public static final RegistryObject<BlockItem> MERCURY_GLOBE_ITEM = ITEMS.register("mercury_globe", () -> new GlobeItem(BlocksRegistry.MERCURY_GLOBE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_globes).rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/blocks/globes/mercury_globe.png")));
    public static final RegistryObject<BlockItem> VENUS_GLOBE_ITEM = ITEMS.register("venus_globe", () -> new GlobeItem(BlocksRegistry.VENUS_GLOBE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_globes).rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/blocks/globes/venus_globe.png")));
    public static final RegistryObject<BlockItem> GLACIO_GLOBE_ITEM = ITEMS.register("glacio_globe", () -> new GlobeItem(BlocksRegistry.GLACIO_GLOBE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_globes).rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/blocks/globes/glacio_globe.png")));

    /** FLAG ITEMS */
    public static final RegistryObject<Item> FLAG_ITEM = ITEMS.register("flag", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_BLUE_ITEM = ITEMS.register("flag_blue", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_BLUE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_BROWN_ITEM = ITEMS.register("flag_brown", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_BROWN_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_CYAN_ITEM = ITEMS.register("flag_cyan", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_CYAN_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_GRAY_ITEM = ITEMS.register("flag_gray", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_GRAY_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_GREEN_ITEM = ITEMS.register("flag_green", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_GREEN_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_LIGHT_BLUE_ITEM = ITEMS.register("flag_light_blue", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_LIGHT_BLUE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_LIME_ITEM = ITEMS.register("flag_lime", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_LIME_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_MAGENTA_ITEM = ITEMS.register("flag_magenta", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_MAGENTA_BLOCk.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_ORANGE_ITEM = ITEMS.register("flag_orange", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_ORANGE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_PINK_ITEM = ITEMS.register("flag_pink", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_PINK_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_PURPLE_ITEM = ITEMS.register("flag_purple", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_PURPLE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_RED_ITEM = ITEMS.register("flag_red", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_RED_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));
    public static final RegistryObject<Item> FLAG_YELLOW_ITEM = ITEMS.register("flag_yellow", () -> new DoubleHighBlockItem(BlocksRegistry.FLAG_YELLOW_BLOCK.get(), new Item.Properties().tab(Tabs.tab_flags)));

    /** SPACE SUIT ITEMS */
    public static final RegistryObject<Item> OXYGEN_MASK = ITEMS.register("oxygen_mask", () -> new SpaceSuit.OxygenMask(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Properties().tab(Tabs.tab_normal)));
    public static final RegistryObject<Item> SPACE_SUIT = ITEMS.register("space_suit", () -> new SpaceSuit.Suit(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Properties().tab(Tabs.tab_normal)));
    public static final RegistryObject<Item> SPACE_PANTS = ITEMS.register("space_pants", () -> new SpaceSuit.Pants(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Properties().tab(Tabs.tab_normal)));
    public static final RegistryObject<Item> SPACE_BOOTS = ITEMS.register("space_boots", () -> new SpaceSuit.Boots(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Properties().tab(Tabs.tab_normal)));

    /** NETHERITE SPACE SUIT ITEMS */
    public static final RegistryObject<Item> NETHERITE_OXYGEN_MASK = ITEMS.register("netherite_oxygen_mask", () -> new NetheriteSpaceSuit.OxygenMask(NetheriteSpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Properties().tab(Tabs.tab_normal).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SPACE_SUIT = ITEMS.register("netherite_space_suit", () -> new NetheriteSpaceSuit.Suit(NetheriteSpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Properties().tab(Tabs.tab_normal).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SPACE_PANTS = ITEMS.register("netherite_space_pants", () -> new NetheriteSpaceSuit.Pants(NetheriteSpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Properties().tab(Tabs.tab_normal).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SPACE_BOOTS = ITEMS.register("netherite_space_boots", () -> new NetheriteSpaceSuit.Boots(NetheriteSpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Properties().tab(Tabs.tab_normal).fireResistant()));

    /** JET SUIT ITEMS */
    public static final RegistryObject<Item> JET_SUIT_OXYGEN_MASK = ITEMS.register("jet_suit_oxygen_mask", () -> new JetSuit.OxygenMask(JetSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Properties().tab(Tabs.tab_normal).fireResistant()));
    public static final RegistryObject<Item> JET_SUIT = ITEMS.register("jet_suit", () -> new JetSuit.Suit(JetSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Properties().tab(Tabs.tab_normal).fireResistant()));
    public static final RegistryObject<Item> JET_SUIT_PANTS = ITEMS.register("jet_suit_pants", () -> new JetSuit.Pants(JetSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Properties().tab(Tabs.tab_normal).fireResistant()));
    public static final RegistryObject<Item> JET_SUIT_BOOTS = ITEMS.register("jet_suit_boots", () -> new JetSuit.Boots(JetSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Properties().tab(Tabs.tab_normal).fireResistant()));

    /** NORMAL ITEMS */
    // SPECIAL ITEMS
    public static final RegistryObject<Item> COAL_TORCH_ITEM = ITEMS.register("coal_torch", () -> new CoalTorchItem(BlocksRegistry.COAL_TORCH_BLOCK.get(), BlocksRegistry.WALL_COAL_TORCH_BLOCK.get(),new Item.Properties().tab(Tabs.tab_basics)));

    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(Tabs.tab_normal).food((new FoodProperties.Builder()).nutrition(4).saturationMod(3f).build())));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", () -> new HammerItem(new Item.Properties().tab(Tabs.tab_basics).durability(9).setNoRepair()));
    public static final RegistryObject<Item> IRON_STICK = ITEMS.register("iron_stick", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> OXYGEN_GEAR = ITEMS.register("oxygen_gear", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> OXYGEN_TANK = ITEMS.register("oxygen_tank", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> ENGINE_FRAME = ITEMS.register("engine_frame", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> ENGINE_FAN = ITEMS.register("engine_fan", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> ROCKET_NOSE_CONE = ITEMS.register("rocket_nose_cone", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> STEEL_ENGINE = ITEMS.register("steel_engine", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> DESH_ENGINE = ITEMS.register("desh_engine", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> OSTRUM_ENGINE = ITEMS.register("ostrum_engine", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> CALORITE_ENGINE = ITEMS.register("calorite_engine", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> STEEL_TANK = ITEMS.register("steel_tank", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> DESH_TANK = ITEMS.register("desh_tank", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> OSTRUM_TANK = ITEMS.register("ostrum_tank", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> CALORITE_TANK = ITEMS.register("calorite_tank", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));
    public static final RegistryObject<Item> ROCKET_FIN = ITEMS.register("rocket_fin", () -> new Item(new Item.Properties().tab(Tabs.tab_basics)));

    // INGOTS
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> DESH_INGOT = ITEMS.register("desh_ingot", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> OSTRUM_INGOT = ITEMS.register("ostrum_ingot", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> CALORITE_INGOT = ITEMS.register("calorite_ingot", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));

    public static final RegistryObject<Item> ICE_SHARD = ITEMS.register("ice_shard", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));

    // PLATES
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> DESH_PLATE = ITEMS.register("desh_plate", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));

    // COMPRESSED
    public static final RegistryObject<Item> COMPRESSED_STEEL = ITEMS.register("compressed_steel", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> COMPRESSED_DESH = ITEMS.register("compressed_desh", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> COMPRESSED_OSTRUM = ITEMS.register("compressed_ostrum", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> COMPRESSED_CALORITE = ITEMS.register("compressed_calorite", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));

    // NUGGETS
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> DESH_NUGGET = ITEMS.register("desh_nugget", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> OSTRUM_NUGGET = ITEMS.register("ostrum_nugget", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> CALORITE_NUGGET = ITEMS.register("calorite_nugget", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));

    // RAW MATERIALS
    public static final RegistryObject<Item> RAW_DESH = ITEMS.register("raw_desh", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> RAW_OSTRUM = ITEMS.register("raw_ostrum", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));
    public static final RegistryObject<Item> RAW_CALORITE = ITEMS.register("raw_calorite", () -> new Item(new Item.Properties().tab(Tabs.tab_materials)));

    /** BLOCK ITEMS */

    // SPECIAL BLOCK ITEMS
    public static final RegistryObject<BlockItem> ROCKET_LAUNCH_PAD_ITEM = ITEMS.register("rocket_launch_pad", () -> new BlockItem(BlocksRegistry.ROCKET_LAUNCH_PAD.get(), new Item.Properties().tab(Tabs.tab_normal)));
    public static final RegistryObject<BlockItem> COAL_LANTERN_ITEM = ITEMS.register("coal_lantern", () -> new BlockItem(BlocksRegistry.COAL_LANTERN_BLOCK.get(), new Item.Properties().tab(Tabs.tab_basics)));

    //MACHINE BLOCK ITEMS

    //TODO DON'T FORGOT IT TO REWORK IT
    /*
    public static final RegistryObject<BlockItem> NASA_WORKBENCH_ITEM = ITEMS.register("nasa_workbench", () -> new BlockItem(BlocksRegistry.NASA_WORKBENCH_BLOCK.get(), new Item.Properties().tab(Tabs.tab_machines)));
    public static final RegistryObject<BlockItem> SOLAR_PANEL_ITEM = ITEMS.register("solar_panel", () -> new BlockItem(BlocksRegistry.SOLAR_PANEL_BLOCK.get(), new Item.Properties().tab(Tabs.tab_machines)));
    public static final RegistryObject<BlockItem> COAL_GENERATOR_ITEM = ITEMS.register("coal_generator", () -> new BlockItem(BlocksRegistry.COAL_GENERATOR_BLOCK.get(), new Item.Properties().tab(Tabs.tab_machines)));
    public static final RegistryObject<BlockItem> COMPRESSOR_ITEM = ITEMS.register("compressor", () -> new BlockItem(BlocksRegistry.COMPRESSOR_BLOCK.get(), new Item.Properties().tab(Tabs.tab_machines)));
    public static final RegistryObject<BlockItem> FUEL_REFINERY_ITEM = ITEMS.register("fuel_refinery", () -> new BlockItem(BlocksRegistry.FUEL_REFINERY_BLOCK.get(), new Item.Properties().tab(Tabs.tab_machines)));
    public static final RegistryObject<BlockItem> OXYGEN_LOADER_ITEM = ITEMS.register("oxygen_loader", () -> new BlockItem(BlocksRegistry.OXYGEN_LOADER_BLOCK.get(), new Item.Properties().tab(Tabs.tab_machines)));
    public static final RegistryObject<BlockItem> OXYGEN_BUBBLE_DISTRIBUTOR_ITEM = ITEMS.register("oxygen_bubble_distributor", () -> new BlockItem(BlocksRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK.get(), new Item.Properties().tab(Tabs.tab_machines)));
    public static final RegistryObject<BlockItem> WATER_PUMP_ITEM = ITEMS.register("water_pump", () -> new BlockItem(BlocksRegistry.WATER_PUMP_BLOCK.get(), new Item.Properties().tab(Tabs.tab_machines)));

    */
    // NORMAL BLOCK ITEMS
    public static final RegistryObject<BlockItem> STEEL_BLOCK_ITEM = ITEMS.register("steel_block", () -> new BlockItem(BlocksRegistry.STEEL_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> DESH_BLOCK_ITEM = ITEMS.register("desh_block", () -> new BlockItem(BlocksRegistry.DESH_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> OSTRUM_BLOCK_ITEM = ITEMS.register("ostrum_block", () -> new BlockItem(BlocksRegistry.OSTRUM_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> CALORITE_BLOCK_ITEM = ITEMS.register("calorite_block", () -> new BlockItem(BlocksRegistry.CALORITE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> RAW_DESH_BLOCK_ITEM = ITEMS.register("raw_desh_block", () -> new BlockItem(BlocksRegistry.RAW_DESH_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> RAW_OSTRUM_BLOCK_ITEM = ITEMS.register("raw_ostrum_block", () -> new BlockItem(BlocksRegistry.RAW_OSTRUM_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> RAW_CALORITE_BLOCK_ITEM = ITEMS.register("raw_calorite_block", () -> new BlockItem(BlocksRegistry.RAW_CALORITE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> IRON_PLATING_BLOCK_ITEM = ITEMS.register("iron_plating_block", () -> new BlockItem(BlocksRegistry.IRON_PLATING_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> RUSTED_IRON_PILLAR_BLOCK_ITEM = ITEMS.register("rusted_iron_pillar_block", () -> new BlockItem(BlocksRegistry.RUSTED_IRON_PILLAR_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> RUSTED_IRON_PLATING_BLOCK_ITEM = ITEMS.register("rusted_iron_plating_block", () -> new BlockItem(BlocksRegistry.RUSTED_IRON_PLATING_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> BLUE_IRON_PLATING_BLOCK_ITEM = ITEMS.register("blue_iron_plating_block", () -> new BlockItem(BlocksRegistry.BLUE_IRON_PLATING_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> INFERNAL_SPIRE_BLOCK_ITEM = ITEMS.register("infernal_spire_block", () -> new BlockItem(BlocksRegistry.INFERNAL_SPIRE_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> IRON_MARK_BLOCK_ITEM = ITEMS.register("iron_mark_block", () -> new BlockItem(BlocksRegistry.IRON_MARK_BLOCK.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> SKY_STONE_ITEM = ITEMS.register("sky_stone", () -> new BlockItem(BlocksRegistry.SKY_STONE.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    // MOON BLOCK ITEMS
    public static final RegistryObject<BlockItem> MOON_STONE_ITEM = ITEMS.register("moon_stone", () -> new BlockItem(BlocksRegistry.MOON_STONE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICKS_ITEM = ITEMS.register("moon_stone_bricks", () -> new BlockItem(BlocksRegistry.MOON_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_MOON_STONE_BRICKS_ITEM = ITEMS.register("cracked_moon_stone_bricks", () -> new BlockItem(BlocksRegistry.CRACKED_MOON_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICK_SLAB_ITEM = ITEMS.register("moon_stone_brick_slab", () -> new BlockItem(BlocksRegistry.MOON_STONE_BRICK_SLAB.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICK_STAIRS_ITEM = ITEMS.register("moon_stone_brick_stairs", () -> new BlockItem(BlocksRegistry.MOON_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    // MARS BLOCK ITEMS
    public static final RegistryObject<BlockItem> MARS_STONE_ITEM = ITEMS.register("mars_stone", () -> new BlockItem(BlocksRegistry.MARS_STONE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICKS_ITEM = ITEMS.register("mars_stone_bricks", () -> new BlockItem(BlocksRegistry.MARS_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_MARS_STONE_BRICKS_ITEM = ITEMS.register("cracked_mars_stone_bricks", () -> new BlockItem(BlocksRegistry.CRACKED_MARS_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICK_SLAB_ITEM = ITEMS.register("mars_stone_brick_slab", () -> new BlockItem(BlocksRegistry.MARS_STONE_BRICK_SLAB.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICK_STAIRS_ITEM = ITEMS.register("mars_stone_brick_stairs", () -> new BlockItem(BlocksRegistry.MARS_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    // MERCURY BLOCK ITEMS
    public static final RegistryObject<BlockItem> MERCURY_STONE_ITEM = ITEMS.register("mercury_stone", () -> new BlockItem(BlocksRegistry.MERCURY_STONE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICKS_ITEM = ITEMS.register("mercury_stone_bricks", () -> new BlockItem(BlocksRegistry.MERCURY_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_MERCURY_STONE_BRICKS_ITEM = ITEMS.register("cracked_mercury_stone_bricks", () -> new BlockItem(BlocksRegistry.CRACKED_MERCURY_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICK_SLAB_ITEM = ITEMS.register("mercury_stone_brick_slab", () -> new BlockItem(BlocksRegistry.MERCURY_STONE_BRICK_SLAB.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICK_STAIRS_ITEM = ITEMS.register("mercury_stone_brick_stairs", () -> new BlockItem(BlocksRegistry.MERCURY_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    // VENUS BLOCK ITEMS
    public static final RegistryObject<BlockItem> VENUS_STONE_ITEM = ITEMS.register("venus_stone", () -> new BlockItem(BlocksRegistry.VENUS_STONE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICKS_ITEM = ITEMS.register("venus_stone_bricks", () -> new BlockItem(BlocksRegistry.VENUS_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_VENUS_STONE_BRICKS_ITEM = ITEMS.register("cracked_venus_stone_bricks", () -> new BlockItem(BlocksRegistry.CRACKED_VENUS_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICK_SLAB_ITEM = ITEMS.register("venus_stone_brick_slab", () -> new BlockItem(BlocksRegistry.VENUS_STONE_BRICK_SLAB.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICK_STAIRS_ITEM = ITEMS.register("venus_stone_brick_stairs", () -> new BlockItem(BlocksRegistry.VENUS_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    // VENUS BLOCK SANDSTONE ITEMS
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_ITEM = ITEMS.register("venus_sandstone", () -> new BlockItem(BlocksRegistry.VENUS_SANDSTONE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICKS_ITEM = ITEMS.register("venus_sandstone_bricks", () -> new BlockItem(BlocksRegistry.VENUS_SANDSTONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_VENUS_SANDSTONE_BRICKS_ITEM = ITEMS.register("cracked_venus_sandstone_bricks", () -> new BlockItem(BlocksRegistry.CRACKED_VENUS_SANDSTONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICK_SLAB_ITEM = ITEMS.register("venus_sandstone_brick_slab", () -> new BlockItem(BlocksRegistry.VENUS_SANDSTONE_BRICK_SLAB.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICK_STAIRS_ITEM = ITEMS.register("venus_sandstone_brick_stairs", () -> new BlockItem(BlocksRegistry.VENUS_SANDSTONE_BRICK_STAIRS.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    // GLACIO BLOCK ITEMS
    public static final RegistryObject<BlockItem> PERMAFROST_ITEM = ITEMS.register("permafrost", () -> new BlockItem(BlocksRegistry.PERMAFROST.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_STONE_ITEM = ITEMS.register("glacio_stone", () -> new BlockItem(BlocksRegistry.GLACIO_STONE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICKS_ITEM = ITEMS.register("glacio_stone_bricks", () -> new BlockItem(BlocksRegistry.GLACIO_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_GLACIO_STONE_BRICKS_ITEM = ITEMS.register("cracked_glacio_stone_bricks", () -> new BlockItem(BlocksRegistry.CRACKED_GLACIO_STONE_BRICKS.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICK_SLAB_ITEM = ITEMS.register("glacio_stone_brick_slab", () -> new BlockItem(BlocksRegistry.GLACIO_STONE_BRICK_SLAB.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICK_STAIRS_ITEM = ITEMS.register("glacio_stone_brick_stairs", () -> new BlockItem(BlocksRegistry.GLACIO_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    // SAND BLOCK ITEMS
    public static final RegistryObject<BlockItem> MOON_SAND_ITEM = ITEMS.register("moon_sand", () -> new BlockItem(BlocksRegistry.MOON_SAND.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_SAND_ITEM = ITEMS.register("mars_sand", () -> new BlockItem(BlocksRegistry.MARS_SAND.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_SAND_ITEM = ITEMS.register("venus_sand", () -> new BlockItem(BlocksRegistry.VENUS_SAND.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    // ORES BLOCK ITEMS
    public static final RegistryObject<BlockItem> MOON_CHEESE_ORE_ITEM = ITEMS.register("moon_cheese_ore", () -> new BlockItem(BlocksRegistry.MOON_CHEESE_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_DESH_ORE_ITEM = ITEMS.register("moon_desh_ore", () -> new BlockItem(BlocksRegistry.MOON_DESH_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_IRON_ORE_ITEM = ITEMS.register("moon_iron_ore", () -> new BlockItem(BlocksRegistry.MOON_IRON_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_ICE_SHARD_ITEM = ITEMS.register("moon_ice_shard_ore", () -> new BlockItem(BlocksRegistry.MOON_ICE_SHARD_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_IRON_ORE_ITEM = ITEMS.register("mars_iron_ore", () -> new BlockItem(BlocksRegistry.MARS_IRON_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_DIAMOND_ORE_ITEM = ITEMS.register("mars_diamond_ore", () -> new BlockItem(BlocksRegistry.MARS_DIAMOND_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_OSTRUM_ORE_ITEM = ITEMS.register("mars_ostrum_ore", () -> new BlockItem(BlocksRegistry.MARS_OSTRUM_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_ICE_SHARD_ORE_ITEM = ITEMS.register("mars_ice_shard_ore", () -> new BlockItem(BlocksRegistry.MARS_ICE_SHARD_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> MERCURY_IRON_ORE_ITEM = ITEMS.register("mercury_iron_ore", () -> new BlockItem(BlocksRegistry.MERCURY_IRON_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_COAL_ORE_ITEM = ITEMS.register("venus_coal_ore", () -> new BlockItem(BlocksRegistry.VENUS_COAL_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_GOLD_ORE_ITEM = ITEMS.register("venus_gold_ore", () -> new BlockItem(BlocksRegistry.VENUS_GOLD_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_DIAMOND_ORE_ITEM = ITEMS.register("venus_diamond_ore", () -> new BlockItem(BlocksRegistry.VENUS_DIAMOND_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_CALORITE_ORE_ITEM = ITEMS.register("venus_calorite_ore", () -> new BlockItem(BlocksRegistry.VENUS_CALORITE_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_ICE_SHARD_ORE_ITEM = ITEMS.register("glacio_ice_shard_ore", () -> new BlockItem(BlocksRegistry.GLACIO_ICE_SHARD_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_COAL_ORE_ITEM = ITEMS.register("glacio_coal_ore", () -> new BlockItem(BlocksRegistry.GLACIO_COAL_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_COPPER_ORE_ITEM = ITEMS.register("glacio_copper_ore", () -> new BlockItem(BlocksRegistry.GLACIO_COPPER_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_IRON_ORE_ITEM = ITEMS.register("glacio_iron_ore", () -> new BlockItem(BlocksRegistry.GLACIO_IRON_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_LAPIS_ORE_ITEM = ITEMS.register("glacio_lapis_ore", () -> new BlockItem(BlocksRegistry.GLACIO_LAPIS_ORE.get(), new Item.Properties().tab(Tabs.tab_blocks)));

    /** BUCKET ITEMS */
    // FUEL BUCKET
    public static final RegistryObject<Item> FUEL_BUCKET = ITEMS.register("fuel_bucket", () -> new BucketItem(FluidsRegistry.FUEL_STILL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(Tabs.tab_normal)));

    // OIL BUCKET
    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket", () -> new BucketItem(FluidsRegistry.OIL_STILL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(Tabs.tab_normal)));
}
