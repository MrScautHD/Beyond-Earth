package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.armors.JetSuit;
import net.mrscauthd.beyond_earth.common.armors.NetheriteSpaceSuit;
import net.mrscauthd.beyond_earth.common.armors.SpaceSuit;
import net.mrscauthd.beyond_earth.common.armors.materials.JetSuitMaterial;
import net.mrscauthd.beyond_earth.common.armors.materials.NetheriteSpaceSuitMaterial;
import net.mrscauthd.beyond_earth.common.armors.materials.SpaceSuitMaterial;
import net.mrscauthd.beyond_earth.common.items.CoalTorchItem;
import net.mrscauthd.beyond_earth.common.items.GlobeItem;
import net.mrscauthd.beyond_earth.common.items.HammerItem;
import net.mrscauthd.beyond_earth.common.items.ModifiedBucketItem;
import net.mrscauthd.beyond_earth.common.items.RoverItem;
import net.mrscauthd.beyond_earth.common.items.Tier1RocketItem;
import net.mrscauthd.beyond_earth.common.items.Tier2RocketItem;
import net.mrscauthd.beyond_earth.common.items.Tier3RocketItem;
import net.mrscauthd.beyond_earth.common.items.Tier4RocketItem;

public class ItemsRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeyondEarth.MODID);

    /** ROCKET ITEMS */
    public static final RegistryObject<Tier1RocketItem> TIER_1_ROCKET_ITEM = ITEMS.register("rocket_t1", () -> new Tier1RocketItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Tier2RocketItem> TIER_2_ROCKET_ITEM = ITEMS.register("rocket_t2", () -> new Tier2RocketItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Tier3RocketItem> TIER_3_ROCKET_ITEM = ITEMS.register("rocket_t3", () -> new Tier3RocketItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Tier4RocketItem> TIER_4_ROCKET_ITEM = ITEMS.register("rocket_t4", () -> new Tier4RocketItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));

    /** ROVER ITEMS */
    public static final RegistryObject<RoverItem> ROVER_ITEM = ITEMS.register("rover", () -> new RoverItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    /** SPAWN EGG ITEMS */
    public static final RegistryObject<ForgeSpawnEggItem> ALIEN_SPAWN_EGG = ITEMS.register("alien_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.ALIEN::get, -13382401, -11650781, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> ALIEN_ZOMBIE_SPAWN_EGG = ITEMS.register("alien_zombie_spawn_egg",() -> new ForgeSpawnEggItem(EntityRegistry.ALIEN_ZOMBIE::get, -14804199, -16740159, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> STAR_CRAWLER_SPAWN_EGG = ITEMS.register("star_crawler_spawn_egg",() -> new ForgeSpawnEggItem(EntityRegistry.STAR_CRAWLER::get, -13421773, -16724788, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> PYGRO_SPAWN_EGG = ITEMS.register("pygro_spawn_egg",() -> new ForgeSpawnEggItem(EntityRegistry.PYGRO::get, -3381760, -6750208, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> PYGRO_BRUTE_SPAWN_EGG = ITEMS.register("pygro_brute_spawn_egg",() -> new ForgeSpawnEggItem(EntityRegistry.PYGRO_BRUTE::get, -3381760, -67208, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> MOGLER_SPAWN_EGG = ITEMS.register("mogler_spawn_egg",() -> new ForgeSpawnEggItem(EntityRegistry.MOGLER::get, -13312, -3407872, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> MARTIAN_RAPTOR_SPAWN_EGG = ITEMS.register("martian_raptor_spawn_egg",() -> new ForgeSpawnEggItem(EntityRegistry.MARTIAN_RAPTOR::get, 5349438, -13312, new Item.Properties()));

    /** GLOBE ITEMS */
    public static final RegistryObject<BlockItem> EARTH_GLOBE_ITEM = ITEMS.register("earth_globe", () -> new GlobeItem(BlockRegistry.EARTH_GLOBE_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/block/globes/earth_globe.png")));
    public static final RegistryObject<BlockItem> MOON_GLOBE_ITEM = ITEMS.register("moon_globe", () -> new GlobeItem(BlockRegistry.MOON_GLOBE_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/block/globes/moon_globe.png")));
    public static final RegistryObject<BlockItem> MARS_GLOBE_ITEM = ITEMS.register("mars_globe", () -> new GlobeItem(BlockRegistry.MARS_GLOBE_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/block/globes/mars_globe.png")));
    public static final RegistryObject<BlockItem> MERCURY_GLOBE_ITEM = ITEMS.register("mercury_globe", () -> new GlobeItem(BlockRegistry.MERCURY_GLOBE_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/block/globes/mercury_globe.png")));
    public static final RegistryObject<BlockItem> VENUS_GLOBE_ITEM = ITEMS.register("venus_globe", () -> new GlobeItem(BlockRegistry.VENUS_GLOBE_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/block/globes/venus_globe.png")));
    public static final RegistryObject<BlockItem> GLACIO_GLOBE_ITEM = ITEMS.register("glacio_globe", () -> new GlobeItem(BlockRegistry.GLACIO_GLOBE_BLOCK.get(), new Item.Properties().rarity(Rarity.EPIC).stacksTo(1), new ResourceLocation(BeyondEarth.MODID, "textures/block/globes/glacio_globe.png")));

    /** SPACE SUIT ITEMS */
    //public static final RegistryObject<Item> OXYGEN_MASK = ITEMS.register("oxygen_mask", () -> new SpaceSuit.Helmet(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> SPACE_HELMET = ITEMS.register("space_helmet", () -> new SpaceSuit.Helmet(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Properties()));
    public static final RegistryObject<Item> SPACE_SUIT = ITEMS.register("space_suit", () -> new SpaceSuit.Suit(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> SPACE_PANTS = ITEMS.register("space_pants", () -> new SpaceSuit.Pants(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> SPACE_BOOTS = ITEMS.register("space_boots", () -> new SpaceSuit.Boots(SpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Properties()));

    /** NETHERITE SPACE SUIT ITEMS */
    public static final RegistryObject<Item> NETHERITE_SPACE_HELMET = ITEMS.register("netherite_space_helmet", () -> new NetheriteSpaceSuit.Helmet(NetheriteSpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SPACE_SUIT = ITEMS.register("netherite_space_suit", () -> new NetheriteSpaceSuit.Suit(NetheriteSpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SPACE_PANTS = ITEMS.register("netherite_space_pants", () -> new NetheriteSpaceSuit.Pants(NetheriteSpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SPACE_BOOTS = ITEMS.register("netherite_space_boots", () -> new NetheriteSpaceSuit.Boots(NetheriteSpaceSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Properties().fireResistant()));

    /** JET SUIT ITEMS */
    public static final RegistryObject<Item> JET_HELMET = ITEMS.register("jet_helmet", () -> new JetSuit.Helmet(JetSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> JET_SUIT = ITEMS.register("jet_suit", () -> new JetSuit.Suit(JetSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> JET_PANTS = ITEMS.register("jet_pants", () -> new JetSuit.Pants(JetSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> JET_BOOTS = ITEMS.register("jet_boots", () -> new JetSuit.Boots(JetSuitMaterial.ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Properties().fireResistant()));


    public static final RegistryObject<BlockItem> ROCKET_LAUNCH_PAD_ITEM = ITEMS.register("rocket_launch_pad", () -> new BlockItem(BlockRegistry.ROCKET_LAUNCH_PAD.get(), new Item.Properties()));

    public static final RegistryObject<Item> FLAG_ITEM = ITEMS.register("flag", () -> new DoubleHighBlockItem(BlockRegistry.FLAG_BLOCK.get(), new Item.Properties()));


    /** NORMAL ITEMS */
    // SPECIAL ITEMS
    public static final RegistryObject<Item> COAL_TORCH_ITEM = ITEMS.register("coal_torch", () -> new CoalTorchItem(BlockRegistry.COAL_TORCH_BLOCK.get(), BlockRegistry.WALL_COAL_TORCH_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese", () -> new Item(new Item.Properties().food((new FoodProperties.Builder()).nutrition(4).saturationMod(3f).build())));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", () -> new HammerItem(new Item.Properties().durability(9).setNoRepair()));
    public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OXYGEN_GEAR = ITEMS.register("oxygen_gear", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OXYGEN_TANK = ITEMS.register("oxygen_tank", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENGINE_FRAME = ITEMS.register("engine_frame", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENGINE_FAN = ITEMS.register("engine_fan", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROCKET_NOSE_CONE = ITEMS.register("rocket_nose_cone", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_ENGINE = ITEMS.register("steel_engine", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DESH_ENGINE = ITEMS.register("desh_engine", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OSTRUM_ENGINE = ITEMS.register("ostrum_engine", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CALORITE_ENGINE = ITEMS.register("calorite_engine", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_TANK = ITEMS.register("steel_tank", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DESH_TANK = ITEMS.register("desh_tank", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OSTRUM_TANK = ITEMS.register("ostrum_tank", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CALORITE_TANK = ITEMS.register("calorite_tank", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROCKET_FIN = ITEMS.register("rocket_fin", () -> new Item(new Item.Properties()));

    // INGOTS
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DESH_INGOT = ITEMS.register("desh_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OSTRUM_INGOT = ITEMS.register("ostrum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CALORITE_INGOT = ITEMS.register("calorite_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ICE_SHARD = ITEMS.register("ice_shard", () -> new Item(new Item.Properties()));

    // PLATES
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DESH_PLATE = ITEMS.register("desh_plate", () -> new Item(new Item.Properties()));

    // COMPRESSED
    public static final RegistryObject<Item> COMPRESSED_STEEL = ITEMS.register("compressed_steel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COMPRESSED_DESH = ITEMS.register("compressed_desh", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COMPRESSED_OSTRUM = ITEMS.register("compressed_ostrum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COMPRESSED_CALORITE = ITEMS.register("compressed_calorite", () -> new Item(new Item.Properties()));

    // NUGGETS
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DESH_NUGGET = ITEMS.register("desh_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OSTRUM_NUGGET = ITEMS.register("ostrum_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CALORITE_NUGGET = ITEMS.register("calorite_nugget", () -> new Item(new Item.Properties()));

    // RAW MATERIALS
    public static final RegistryObject<Item> RAW_DESH = ITEMS.register("raw_desh", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_OSTRUM = ITEMS.register("raw_ostrum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_CALORITE = ITEMS.register("raw_calorite", () -> new Item(new Item.Properties()));

    /** BLOCK ITEMS */

    // SPECIAL BLOCK ITEMS
    public static final RegistryObject<BlockItem> COAL_LANTERN_ITEM = ITEMS.register("coal_lantern", () -> new BlockItem(BlockRegistry.COAL_LANTERN_BLOCK.get(), new Item.Properties()));

    //MACHINE BLOCK ITEMS
    public static final RegistryObject<BlockItem> NASA_WORKBENCH_ITEM = ITEMS.register("nasa_workbench", () -> new BlockItem(BlockRegistry.NASA_WORKBENCH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> SOLAR_PANEL_ITEM = ITEMS.register("solar_panel", () -> new BlockItem(BlockRegistry.SOLAR_PANEL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> COAL_GENERATOR_ITEM = ITEMS.register("coal_generator", () -> new BlockItem(BlockRegistry.COAL_GENERATOR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> COMPRESSOR_ITEM = ITEMS.register("compressor", () -> new BlockItem(BlockRegistry.COMPRESSOR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> FUEL_REFINERY_ITEM = ITEMS.register("fuel_refinery", () -> new BlockItem(BlockRegistry.FUEL_REFINERY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> OXYGEN_LOADER_ITEM = ITEMS.register("oxygen_loader", () -> new BlockItem(BlockRegistry.OXYGEN_LOADER_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> OXYGEN_BUBBLE_DISTRIBUTOR_ITEM = ITEMS.register("oxygen_bubble_distributor", () -> new BlockItem(BlockRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> WATER_PUMP_ITEM = ITEMS.register("water_pump", () -> new BlockItem(BlockRegistry.WATER_PUMP_BLOCK.get(), new Item.Properties()));

    // NORMAL BLOCK ITEMS
    public static final RegistryObject<BlockItem> STEEL_BLOCK_ITEM = ITEMS.register("steel_block", () -> new BlockItem(BlockRegistry.STEEL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DESH_BLOCK_ITEM = ITEMS.register("desh_block", () -> new BlockItem(BlockRegistry.DESH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> OSTRUM_BLOCK_ITEM = ITEMS.register("ostrum_block", () -> new BlockItem(BlockRegistry.OSTRUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CALORITE_BLOCK_ITEM = ITEMS.register("calorite_block", () -> new BlockItem(BlockRegistry.CALORITE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> RAW_DESH_BLOCK_ITEM = ITEMS.register("raw_desh_block", () -> new BlockItem(BlockRegistry.RAW_DESH_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> RAW_OSTRUM_BLOCK_ITEM = ITEMS.register("raw_ostrum_block", () -> new BlockItem(BlockRegistry.RAW_OSTRUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> RAW_CALORITE_BLOCK_ITEM = ITEMS.register("raw_calorite_block", () -> new BlockItem(BlockRegistry.RAW_CALORITE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> IRON_PLATING_BLOCK_ITEM = ITEMS.register("iron_plating_block", () -> new BlockItem(BlockRegistry.IRON_PLATING_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DESH_PILLAR_BLOCK_ITEM = ITEMS.register("desh_pillar", () -> new BlockItem(BlockRegistry.DESH_PILLAR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> DESH_PLATING_BLOCK_ITEM = ITEMS.register("desh_plating_block", () -> new BlockItem(BlockRegistry.DESH_PLATING_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BLUE_IRON_PILLAR_BLOCK_ITEM = ITEMS.register("blue_iron_pillar", () -> new BlockItem(BlockRegistry.BLUE_IRON_PILLAR.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> INFERNAL_SPIRE_ITEM = ITEMS.register("infernal_spire", () -> new BlockItem(BlockRegistry.INFERNAL_SPIRE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> BARRICADE_BLOCK_ITEM = ITEMS.register("barricade_block", () -> new BlockItem(BlockRegistry.BARRICADE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> IRON_MARK_BLOCK_ITEM = ITEMS.register("iron_mark_block", () -> new BlockItem(BlockRegistry.IRON_MARK_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> METEORITE_ITEM = ITEMS.register("meteorite", () -> new BlockItem(BlockRegistry.METEORITE.get(), new Item.Properties()));

    // MOON BLOCK ITEMS
    public static final RegistryObject<BlockItem> MOON_STONE_ITEM = ITEMS.register("moon_stone", () -> new BlockItem(BlockRegistry.MOON_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICKS_ITEM = ITEMS.register("moon_stone_bricks", () -> new BlockItem(BlockRegistry.MOON_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CRACKED_MOON_STONE_BRICKS_ITEM = ITEMS.register("cracked_moon_stone_bricks", () -> new BlockItem(BlockRegistry.CRACKED_MOON_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICK_SLAB_ITEM = ITEMS.register("moon_stone_brick_slab", () -> new BlockItem(BlockRegistry.MOON_STONE_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICK_STAIRS_ITEM = ITEMS.register("moon_stone_brick_stairs", () -> new BlockItem(BlockRegistry.MOON_STONE_BRICK_STAIRS.get(), new Item.Properties()));

    // MARS BLOCK ITEMS
    public static final RegistryObject<BlockItem> MARS_STONE_ITEM = ITEMS.register("mars_stone", () -> new BlockItem(BlockRegistry.MARS_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICKS_ITEM = ITEMS.register("mars_stone_bricks", () -> new BlockItem(BlockRegistry.MARS_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CRACKED_MARS_STONE_BRICKS_ITEM = ITEMS.register("cracked_mars_stone_bricks", () -> new BlockItem(BlockRegistry.CRACKED_MARS_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICK_SLAB_ITEM = ITEMS.register("mars_stone_brick_slab", () -> new BlockItem(BlockRegistry.MARS_STONE_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICK_STAIRS_ITEM = ITEMS.register("mars_stone_brick_stairs", () -> new BlockItem(BlockRegistry.MARS_STONE_BRICK_STAIRS.get(), new Item.Properties()));

    // MERCURY BLOCK ITEMS
    public static final RegistryObject<BlockItem> MERCURY_STONE_ITEM = ITEMS.register("mercury_stone", () -> new BlockItem(BlockRegistry.MERCURY_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICKS_ITEM = ITEMS.register("mercury_stone_bricks", () -> new BlockItem(BlockRegistry.MERCURY_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CRACKED_MERCURY_STONE_BRICKS_ITEM = ITEMS.register("cracked_mercury_stone_bricks", () -> new BlockItem(BlockRegistry.CRACKED_MERCURY_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICK_SLAB_ITEM = ITEMS.register("mercury_stone_brick_slab", () -> new BlockItem(BlockRegistry.MERCURY_STONE_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICK_STAIRS_ITEM = ITEMS.register("mercury_stone_brick_stairs", () -> new BlockItem(BlockRegistry.MERCURY_STONE_BRICK_STAIRS.get(), new Item.Properties()));

    // VENUS BLOCK ITEMS
    public static final RegistryObject<BlockItem> VENUS_STONE_ITEM = ITEMS.register("venus_stone", () -> new BlockItem(BlockRegistry.VENUS_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICKS_ITEM = ITEMS.register("venus_stone_bricks", () -> new BlockItem(BlockRegistry.VENUS_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CRACKED_VENUS_STONE_BRICKS_ITEM = ITEMS.register("cracked_venus_stone_bricks", () -> new BlockItem(BlockRegistry.CRACKED_VENUS_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICK_SLAB_ITEM = ITEMS.register("venus_stone_brick_slab", () -> new BlockItem(BlockRegistry.VENUS_STONE_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICK_STAIRS_ITEM = ITEMS.register("venus_stone_brick_stairs", () -> new BlockItem(BlockRegistry.VENUS_STONE_BRICK_STAIRS.get(), new Item.Properties()));

    // VENUS BLOCK SANDSTONE ITEMS
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_ITEM = ITEMS.register("venus_sandstone", () -> new BlockItem(BlockRegistry.VENUS_SANDSTONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICKS_ITEM = ITEMS.register("venus_sandstone_bricks", () -> new BlockItem(BlockRegistry.VENUS_SANDSTONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CRACKED_VENUS_SANDSTONE_BRICKS_ITEM = ITEMS.register("cracked_venus_sandstone_bricks", () -> new BlockItem(BlockRegistry.CRACKED_VENUS_SANDSTONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICK_SLAB_ITEM = ITEMS.register("venus_sandstone_brick_slab", () -> new BlockItem(BlockRegistry.VENUS_SANDSTONE_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICK_STAIRS_ITEM = ITEMS.register("venus_sandstone_brick_stairs", () -> new BlockItem(BlockRegistry.VENUS_SANDSTONE_BRICK_STAIRS.get(), new Item.Properties()));

    // GLACIO BLOCK ITEMS
    public static final RegistryObject<BlockItem> PERMAFROST_ITEM = ITEMS.register("permafrost", () -> new BlockItem(BlockRegistry.PERMAFROST.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_STONE_ITEM = ITEMS.register("glacio_stone", () -> new BlockItem(BlockRegistry.GLACIO_STONE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICKS_ITEM = ITEMS.register("glacio_stone_bricks", () -> new BlockItem(BlockRegistry.GLACIO_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CRACKED_GLACIO_STONE_BRICKS_ITEM = ITEMS.register("cracked_glacio_stone_bricks", () -> new BlockItem(BlockRegistry.CRACKED_GLACIO_STONE_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICK_SLAB_ITEM = ITEMS.register("glacio_stone_brick_slab", () -> new BlockItem(BlockRegistry.GLACIO_STONE_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICK_STAIRS_ITEM = ITEMS.register("glacio_stone_brick_stairs", () -> new BlockItem(BlockRegistry.GLACIO_STONE_BRICK_STAIRS.get(), new Item.Properties()));

    // SAND BLOCK ITEMS
    public static final RegistryObject<BlockItem> MOON_SAND_ITEM = ITEMS.register("moon_sand", () -> new BlockItem(BlockRegistry.MOON_SAND.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MARS_SAND_ITEM = ITEMS.register("mars_sand", () -> new BlockItem(BlockRegistry.MARS_SAND.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_SAND_ITEM = ITEMS.register("venus_sand", () -> new BlockItem(BlockRegistry.VENUS_SAND.get(), new Item.Properties()));

    // ORES BLOCK ITEMS
    public static final RegistryObject<BlockItem> MOON_CHEESE_ORE_ITEM = ITEMS.register("moon_cheese_ore", () -> new BlockItem(BlockRegistry.MOON_CHEESE_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOON_DESH_ORE_ITEM = ITEMS.register("moon_desh_ore", () -> new BlockItem(BlockRegistry.MOON_DESH_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOON_IRON_ORE_ITEM = ITEMS.register("moon_iron_ore", () -> new BlockItem(BlockRegistry.MOON_IRON_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MOON_ICE_SHARD_ITEM = ITEMS.register("moon_ice_shard_ore", () -> new BlockItem(BlockRegistry.MOON_ICE_SHARD_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MARS_IRON_ORE_ITEM = ITEMS.register("mars_iron_ore", () -> new BlockItem(BlockRegistry.MARS_IRON_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MARS_DIAMOND_ORE_ITEM = ITEMS.register("mars_diamond_ore", () -> new BlockItem(BlockRegistry.MARS_DIAMOND_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MARS_OSTRUM_ORE_ITEM = ITEMS.register("mars_ostrum_ore", () -> new BlockItem(BlockRegistry.MARS_OSTRUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MARS_ICE_SHARD_ORE_ITEM = ITEMS.register("mars_ice_shard_ore", () -> new BlockItem(BlockRegistry.MARS_ICE_SHARD_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> MERCURY_IRON_ORE_ITEM = ITEMS.register("mercury_iron_ore", () -> new BlockItem(BlockRegistry.MERCURY_IRON_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_COAL_ORE_ITEM = ITEMS.register("venus_coal_ore", () -> new BlockItem(BlockRegistry.VENUS_COAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_GOLD_ORE_ITEM = ITEMS.register("venus_gold_ore", () -> new BlockItem(BlockRegistry.VENUS_GOLD_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_DIAMOND_ORE_ITEM = ITEMS.register("venus_diamond_ore", () -> new BlockItem(BlockRegistry.VENUS_DIAMOND_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VENUS_CALORITE_ORE_ITEM = ITEMS.register("venus_calorite_ore", () -> new BlockItem(BlockRegistry.VENUS_CALORITE_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_ICE_SHARD_ORE_ITEM = ITEMS.register("glacio_ice_shard_ore", () -> new BlockItem(BlockRegistry.GLACIO_ICE_SHARD_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_COAL_ORE_ITEM = ITEMS.register("glacio_coal_ore", () -> new BlockItem(BlockRegistry.GLACIO_COAL_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_COPPER_ORE_ITEM = ITEMS.register("glacio_copper_ore", () -> new BlockItem(BlockRegistry.GLACIO_COPPER_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_IRON_ORE_ITEM = ITEMS.register("glacio_iron_ore", () -> new BlockItem(BlockRegistry.GLACIO_IRON_ORE.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> GLACIO_LAPIS_ORE_ITEM = ITEMS.register("glacio_lapis_ore", () -> new BlockItem(BlockRegistry.GLACIO_LAPIS_ORE.get(), new Item.Properties()));

    /** BUCKET ITEMS */
    public static final RegistryObject<BucketItem> FUEL_BUCKET = ITEMS.register("fuel_bucket", () -> new ModifiedBucketItem(FluidRegistry.FUEL_STILL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1), true));
    public static final RegistryObject<BucketItem> OIL_BUCKET = ITEMS.register("oil_bucket", () -> new ModifiedBucketItem(FluidRegistry.OIL_STILL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1), false));
}
