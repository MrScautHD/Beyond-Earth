package net.mrscauthd.beyond_earth;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;
import net.mrscauthd.beyond_earth.armors.SpaceSuit;
import net.mrscauthd.beyond_earth.blocks.*;
import net.mrscauthd.beyond_earth.crafting.AlienTradingRecipeDyedItem;
import net.mrscauthd.beyond_earth.crafting.AlienTradingRecipeEnchantedBook;
import net.mrscauthd.beyond_earth.crafting.AlienTradingRecipeEnchantedItem;
import net.mrscauthd.beyond_earth.crafting.AlienTradingRecipeItemStack;
import net.mrscauthd.beyond_earth.crafting.AlienTradingRecipeMap;
import net.mrscauthd.beyond_earth.crafting.AlienTradingRecipePotionedItem;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.CompressingRecipeSerializer;
import net.mrscauthd.beyond_earth.crafting.FuelRefiningRecipeSerializer;
import net.mrscauthd.beyond_earth.crafting.GeneratingRecipeSerializer;
import net.mrscauthd.beyond_earth.crafting.OxygenBubbleDistributorRecipeSerializer;
import net.mrscauthd.beyond_earth.crafting.OxygenLoaderRecipeSerializer;
import net.mrscauthd.beyond_earth.crafting.WorkbenchingRecipeSerializer;
import net.mrscauthd.beyond_earth.crafting.RocketPart;
import net.mrscauthd.beyond_earth.crafting.SpaceStationRecipeSerializer;
import net.mrscauthd.beyond_earth.flag.FlagTileEntity;
import net.mrscauthd.beyond_earth.fluids.OilFluid;
import net.mrscauthd.beyond_earth.globe.GlobeBlock;
import net.mrscauthd.beyond_earth.globe.GlobeItem;
import net.mrscauthd.beyond_earth.globe.GlobeTileEntity;
import net.mrscauthd.beyond_earth.items.*;
import net.mrscauthd.beyond_earth.machines.*;
import net.mrscauthd.beyond_earth.machines.tile.CoalGeneratorBlockEntity;
import net.mrscauthd.beyond_earth.machines.tile.CompressorBlockEntity;
import net.mrscauthd.beyond_earth.machines.tile.FuelRefineryBlockEntity;
import net.mrscauthd.beyond_earth.machines.tile.NASAWorkbenchBlockEntity;
import net.mrscauthd.beyond_earth.machines.tile.OxygenBubbleDistributorBlockEntity;
import net.mrscauthd.beyond_earth.machines.tile.OxygenLoaderBlockEntity;
import net.mrscauthd.beyond_earth.machines.tile.SolarPanelBlockEntity;
import net.mrscauthd.beyond_earth.machines.tile.WaterPumpBlockEntity;
import net.mrscauthd.beyond_earth.flag.*;
import net.mrscauthd.beyond_earth.fluids.FuelFluid;
import net.mrscauthd.beyond_earth.armors.NetheriteSpaceSuit;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.mrscauthd.beyond_earth.itemgroups.ItemGroups;
import net.mrscauthd.beyond_earth.registries.EntitiesRegistry;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModInit {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, BeyondEarthMod.MODID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeyondEarthMod.MODID);

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, BeyondEarthMod.MODID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BeyondEarthMod.MODID);

    //Vehicle Items
    public static final RegistryObject<Tier1RocketItem> TIER_1_ROCKET_ITEM = ITEMS.register("rocket_t1", () -> new Tier1RocketItem(new Item.Properties().tab(ItemGroups.tab_normal).stacksTo(1)));
    public static final RegistryObject<Tier2RocketItem> TIER_2_ROCKET_ITEM = ITEMS.register("rocket_t2", () -> new Tier2RocketItem(new Item.Properties().tab(ItemGroups.tab_normal).stacksTo(1)));
    public static final RegistryObject<Tier3RocketItem> TIER_3_ROCKET_ITEM = ITEMS.register("rocket_t3", () -> new Tier3RocketItem(new Item.Properties().tab(ItemGroups.tab_normal).stacksTo(1)));
    public static final RegistryObject<Tier4RocketItem> TIER_4_ROCKET_ITEM = ITEMS.register("rocket_t4", () -> new Tier4RocketItem(new Item.Properties().tab(ItemGroups.tab_normal).stacksTo(1)));
    public static final RegistryObject<RoverItem> ROVER_ITEM = ITEMS.register("rover", () -> new RoverItem(new Item.Properties().tab(ItemGroups.tab_normal).stacksTo(1)));

    //Rocket Launch Pad
    public static final RegistryObject<Block> ROCKET_LAUNCH_PAD = BLOCKS.register("rocket_launch_pad", () -> new RocketLaunchPad(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));

    //Blocks
    public static final RegistryObject<Block> COAL_TORCH_BLOCK = BLOCKS.register("coal_torch",() -> new CoalTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WALL_COAL_TORCH_BLOCK = BLOCKS.register("wall_coal_torch",() -> new WallCoalTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD).lootFrom(COAL_TORCH_BLOCK::get)));
    public static final RegistryObject<Block> COAL_LANTERN_BLOCK = BLOCKS.register("coal_lantern",() -> new CoalLanternBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.5F).sound(SoundType.LANTERN).noOcclusion().requiresCorrectToolForDrops()));


    //Flag Blocks
    public static final RegistryObject<Block> FLAG_BLOCK = BLOCKS.register("flag",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_BLUE_BLOCK = BLOCKS.register("flag_blue",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_BROWN_BLOCK = BLOCKS.register("flag_brown",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_CYAN_BLOCK = BLOCKS.register("flag_cyan",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_GRAY_BLOCK = BLOCKS.register("flag_gray",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_GREEN_BLOCK = BLOCKS.register("flag_green",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_LIGHT_BLUE_BLOCK = BLOCKS.register("flag_light_blue",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_LIME_BLOCK = BLOCKS.register("flag_lime",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_MAGENTA_BLOCk = BLOCKS.register("flag_magenta",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_ORANGE_BLOCK = BLOCKS.register("flag_orange",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_PINK_BLOCK = BLOCKS.register("flag_pink",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_PURPLE_BLOCK = BLOCKS.register("flag_purple",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_RED_BLOCK = BLOCKS.register("flag_red",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> FLAG_YELLOW_BLOCK = BLOCKS.register("flag_yellow",() -> new FlagBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion().lightLevel(s -> 1).isRedstoneConductor((bs, br, bp) -> false)));

    //Tile Entity RegistryObject
    public static final RegistryObject<BlockEntityType<?>> FUEL_REFINERY = TILE_ENTITIES.register("fuel_refinery", () -> BlockEntityType.Builder.of(FuelRefineryBlockEntity::new, ModInit.FUEL_REFINERY_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> COMPRESSOR = TILE_ENTITIES.register("compressor", () -> BlockEntityType.Builder.of(CompressorBlockEntity::new, ModInit.COMPRESSOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> COAL_GENERATOR = TILE_ENTITIES.register("coal_generator", () -> BlockEntityType.Builder.of(CoalGeneratorBlockEntity::new, ModInit.COAL_GENERATOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> OXYGEN_LOADER = TILE_ENTITIES.register("oxygen_loader", () -> BlockEntityType.Builder.of(OxygenLoaderBlockEntity::new, ModInit.OXYGEN_LOADER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> SOLAR_PANEL = TILE_ENTITIES.register("solar_panel", () -> BlockEntityType.Builder.of(SolarPanelBlockEntity::new, ModInit.SOLAR_PANEL_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> NASA_WORKBENCH = TILE_ENTITIES.register("nasa_workbench", () -> BlockEntityType.Builder.of(NASAWorkbenchBlockEntity::new, ModInit.NASA_WORKBENCH_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<OxygenBubbleDistributorBlockEntity>> OXYGEN_BUBBLE_DISTRIBUTOR = TILE_ENTITIES.register("oxygen_bubble_distributor", () -> BlockEntityType.Builder.of(OxygenBubbleDistributorBlockEntity::new, ModInit.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> WATER_PUMP = TILE_ENTITIES.register("water_pump", () -> BlockEntityType.Builder.of(WaterPumpBlockEntity::new, ModInit.WATER_PUMP_BLOCK.get()).build(null));

    //Globe Blocks
    public static final RegistryObject<Block> EARTH_GLOBE_BLOCK = BLOCKS.register("earth_globe",() -> new GlobeBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MOON_GLOBE_BLOCK = BLOCKS.register("moon_globe",() -> new GlobeBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MARS_GLOBE_BLOCK = BLOCKS.register("mars_globe",() -> new GlobeBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MERCURY_GLOBE_BLOCK = BLOCKS.register("mercury_globe",() -> new GlobeBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_GLOBE_BLOCK = BLOCKS.register("venus_globe",() -> new GlobeBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLACIO_GLOBE_BLOCK = BLOCKS.register("glacio_globe",() -> new GlobeBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    //Globe Items
    public static final RegistryObject<BlockItem> EARTH_GLOBE_ITEM = ITEMS.register("earth_globe", () -> new GlobeItem(ModInit.EARTH_GLOBE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_globes).rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<BlockItem> MOON_GLOBE_ITEM = ITEMS.register("moon_globe", () -> new GlobeItem(ModInit.MOON_GLOBE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_globes).rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<BlockItem> MARS_GLOBE_ITEM = ITEMS.register("mars_globe", () -> new GlobeItem(ModInit.MARS_GLOBE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_globes).rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<BlockItem> MERCURY_GLOBE_ITEM = ITEMS.register("mercury_globe", () -> new GlobeItem(ModInit.MERCURY_GLOBE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_globes).rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<BlockItem> VENUS_GLOBE_ITEM = ITEMS.register("venus_globe", () -> new GlobeItem(ModInit.VENUS_GLOBE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_globes).rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<BlockItem> GLACIO_GLOBE_ITEM = ITEMS.register("glacio_globe", () -> new GlobeItem(ModInit.GLACIO_GLOBE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_globes).rarity(Rarity.EPIC).stacksTo(1)));

    //Globe Tile Entity
    public static final RegistryObject<BlockEntityType<GlobeTileEntity>> GLOBE = TILE_ENTITIES.register("globe", () -> BlockEntityType.Builder.of(GlobeTileEntity::new, ModInit.EARTH_GLOBE_BLOCK.get(), ModInit.MOON_GLOBE_BLOCK.get(), ModInit.MARS_GLOBE_BLOCK.get(), ModInit.MERCURY_GLOBE_BLOCK.get(), ModInit.VENUS_GLOBE_BLOCK.get(), ModInit.GLACIO_GLOBE_BLOCK.get()).build(null));

    //Flag Tile Entity
    public static final RegistryObject<BlockEntityType<FlagTileEntity>> FLAG = TILE_ENTITIES.register("flag", () -> BlockEntityType.Builder.of(FlagTileEntity::new, ModInit.FLAG_BLOCK.get(), ModInit.FLAG_BLUE_BLOCK.get(), ModInit.FLAG_BROWN_BLOCK.get(), ModInit.FLAG_CYAN_BLOCK.get(), ModInit.FLAG_GRAY_BLOCK.get(), ModInit.FLAG_GRAY_BLOCK.get(), ModInit.FLAG_GREEN_BLOCK.get(), ModInit.FLAG_LIGHT_BLUE_BLOCK.get(), ModInit.FLAG_LIME_BLOCK.get(), ModInit.FLAG_MAGENTA_BLOCk.get(), ModInit.FLAG_ORANGE_BLOCK.get(), ModInit.FLAG_PINK_BLOCK.get(), ModInit.FLAG_PURPLE_BLOCK.get(), ModInit.FLAG_RED_BLOCK.get(), ModInit.FLAG_YELLOW_BLOCK.get()).build(null));

    //Machines
    public static final RegistryObject<Block> FUEL_REFINERY_BLOCK = BLOCKS.register("fuel_refinery",() -> new FuelRefineryBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> COMPRESSOR_BLOCK = BLOCKS.register("compressor",() -> new CompressorBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> COAL_GENERATOR_BLOCK = BLOCKS.register("coal_generator",() -> new CoalGeneratorBlock(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OXYGEN_LOADER_BLOCK = BLOCKS.register("oxygen_loader",() -> new OxygenLoaderBlock(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SOLAR_PANEL_BLOCK = BLOCKS.register("solar_panel",() -> new SolarPanelBlock(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NASA_WORKBENCH_BLOCK = BLOCKS.register("nasa_workbench",() -> new NASAWorkbenchBlock(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 1f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false)));
    public static final RegistryObject<Block> OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK = BLOCKS.register("oxygen_bubble_distributor",() -> new OxygenBubbleDistributorBlock(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WATER_PUMP_BLOCK = BLOCKS.register("water_pump",() -> new WaterPump(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).noOcclusion().strength(5f, 1f).requiresCorrectToolForDrops()));


    //Block Item
    public static final RegistryObject<BlockItem> NASA_WORKBENCH_ITEM = ITEMS.register("nasa_workbench", () -> new BlockItem(ModInit.NASA_WORKBENCH_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_machines)));
    public static final RegistryObject<BlockItem> SOLAR_PANEL_ITEM = ITEMS.register("solar_panel", () -> new BlockItem(ModInit.SOLAR_PANEL_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_machines)));
    public static final RegistryObject<BlockItem> COAL_GENERATOR_ITEM = ITEMS.register("coal_generator", () -> new BlockItem(ModInit.COAL_GENERATOR_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_machines)));
    public static final RegistryObject<BlockItem> COMPRESSOR_ITEM = ITEMS.register("compressor", () -> new BlockItem(ModInit.COMPRESSOR_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_machines)));
    public static final RegistryObject<BlockItem> FUEL_REFINERY_ITEM = ITEMS.register("fuel_refinery", () -> new BlockItem(ModInit.FUEL_REFINERY_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_machines)));
    public static final RegistryObject<BlockItem> OXYGEN_LOADER_ITEM = ITEMS.register("oxygen_loader", () -> new BlockItem(ModInit.OXYGEN_LOADER_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_machines)));
    public static final RegistryObject<BlockItem> OXYGEN_BUBBLE_DISTRIBUTOR_ITEM = ITEMS.register("oxygen_bubble_distributor", () -> new BlockItem(ModInit.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_machines)));
    public static final RegistryObject<BlockItem> WATER_PUMP_ITEM = ITEMS.register("water_pump", () -> new BlockItem(ModInit.WATER_PUMP_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_machines)));

    //Fuel Fluid
    public static final RegistryObject<FlowingFluid> FLOWING_FUEL = FLUIDS.register("flowing_fuel", () -> new FuelFluid.Flowing());
    public static final RegistryObject<FlowingFluid> FUEL_STILL = FLUIDS.register("fuel", () -> new FuelFluid.Source());
    public static final RegistryObject<LiquidBlock> FUEL_BLOCK = BLOCKS.register("fuel",() -> new LiquidBlock(ModInit.FUEL_STILL, Block.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));
    public static final RegistryObject<Item> FUEL_BUCKET = ITEMS.register("fuel_bucket", () -> new BucketItem(ModInit.FUEL_STILL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroups.tab_normal)));

    //Oil Fluid
    public static final RegistryObject<FlowingFluid> FLOWING_OIL = FLUIDS.register("flowing_oil", () -> new OilFluid.Flowing());
    public static final RegistryObject<FlowingFluid> OIL_STILL = FLUIDS.register("oil", () -> new OilFluid.Source());
    public static final RegistryObject<LiquidBlock> OIL_BLOCK = BLOCKS.register("oil",() -> new LiquidBlock(ModInit.OIL_STILL, Block.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));
    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket", () -> new BucketItem(ModInit.OIL_STILL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroups.tab_normal)));

    //Item
    public static final RegistryObject<Item> COAL_TORCH_ITEM = ITEMS.register("coal_torch", () -> new CoalTorchItem(COAL_TORCH_BLOCK.get(), WALL_COAL_TORCH_BLOCK.get(),new Item.Properties().tab(ItemGroups.tab_basics)));
    //Lanterns
    public static final RegistryObject<BlockItem> COAL_LANTERN_ITEM = ITEMS.register("coal_lantern", () -> new BlockItem(ModInit.COAL_LANTERN_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_basics)));

    //Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> ALIEN_SPAWN_EGG = ITEMS.register("alien_spawn_egg", () -> new ForgeSpawnEggItem(EntitiesRegistry.ALIEN::get, -13382401, -11650781, new Item.Properties().tab(ItemGroups.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> ALIEN_ZOMBIE_SPAWN_EGG = ITEMS.register("alien_zombie_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.ALIEN_ZOMBIE::get, -14804199, -16740159, new Item.Properties().tab(ItemGroups.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> STAR_CRAWLER_SPAWN_EGG = ITEMS.register("star_crawler_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.STAR_CRAWLER::get, -13421773, -16724788, new Item.Properties().tab(ItemGroups.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> PYGRO_SPAWN_EGG = ITEMS.register("pygro_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.PYGRO::get, -3381760, -6750208, new Item.Properties().tab(ItemGroups.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> PYGRO_BRUTE_SPAWN_EGG = ITEMS.register("pygro_brute_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.PYGRO_BRUTE::get, -3381760, -67208, new Item.Properties().tab(ItemGroups.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> MOGLER_SPAWN_EGG = ITEMS.register("mogler_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.MOGLER::get, -13312, -3407872, new Item.Properties().tab(ItemGroups.tab_spawn_eggs)));
    public static final RegistryObject<ForgeSpawnEggItem> MARTIAN_RAPTOR_SPAWN_EGG = ITEMS.register("martian_raptor_spawn_egg",() -> new ForgeSpawnEggItem(EntitiesRegistry.MARTIAN_RAPTOR::get, 5349438, -13312, new Item.Properties().tab(ItemGroups.tab_spawn_eggs)));

    //Generel Items
    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(ItemGroups.tab_normal).food((new FoodProperties.Builder()).nutrition(4).saturationMod(3f).build())));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", () -> new HammerItem(new Item.Properties().tab(ItemGroups.tab_basics).durability(9).setNoRepair()));
    public static final RegistryObject<Item> IRON_STICK = ITEMS.register("iron_stick", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> OXYGEN_GEAR = ITEMS.register("oxygen_gear", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> OXYGEN_TANK = ITEMS.register("oxygen_tank", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> ENGINE_FRAME = ITEMS.register("engine_frame", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> ENGINE_FAN = ITEMS.register("engine_fan", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> ROCKET_NOSE_CONE = ITEMS.register("rocket_nose_cone", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> STEEL_ENGINE = ITEMS.register("steel_engine", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> DESH_ENGINE = ITEMS.register("desh_engine", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> OSTRUM_ENGINE = ITEMS.register("ostrum_engine", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> CALORITE_ENGINE = ITEMS.register("calorite_engine", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> STEEL_TANK = ITEMS.register("steel_tank", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> DESH_TANK = ITEMS.register("desh_tank", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> OSTRUM_TANK = ITEMS.register("ostrum_tank", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> CALORITE_TANK = ITEMS.register("calorite_tank", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));
    public static final RegistryObject<Item> ROCKET_FIN = ITEMS.register("rocket_fin", () -> new Item(new Item.Properties().tab(ItemGroups.tab_basics)));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> DESH_INGOT = ITEMS.register("desh_ingot", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> OSTRUM_INGOT = ITEMS.register("ostrum_ingot", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> CALORITE_INGOT = ITEMS.register("calorite_ingot", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));

    public static final RegistryObject<Item> ICE_SHARD = ITEMS.register("ice_shard", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> DESH_PLATE = ITEMS.register("desh_plate", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));

    public static final RegistryObject<Item> COMPRESSED_STEEL = ITEMS.register("compressed_steel", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> COMPRESSED_DESH = ITEMS.register("compressed_desh", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> COMPRESSED_OSTRUM = ITEMS.register("compressed_ostrum", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> COMPRESSED_CALORITE = ITEMS.register("compressed_calorite", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> DESH_NUGGET = ITEMS.register("desh_nugget", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> OSTRUM_NUGGET = ITEMS.register("ostrum_nugget", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> CALORITE_NUGGET = ITEMS.register("calorite_nugget", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));

    public static final RegistryObject<Item> RAW_DESH = ITEMS.register("raw_desh", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> RAW_OSTRUM = ITEMS.register("raw_ostrum", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));
    public static final RegistryObject<Item> RAW_CALORITE = ITEMS.register("raw_calorite", () -> new Item(new Item.Properties().tab(ItemGroups.tab_materials)));

    //Generel Blocks
    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DESH_BLOCK = BLOCKS.register("desh_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OSTRUM_BLOCK = BLOCKS.register("ostrum_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CALORITE_BLOCK = BLOCKS.register("calorite_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAW_DESH_BLOCK = BLOCKS.register("raw_desh_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAW_OSTRUM_BLOCK = BLOCKS.register("raw_ostrum_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RAW_CALORITE_BLOCK = BLOCKS.register("raw_calorite_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> IRON_PLATING_BLOCK = BLOCKS.register("iron_plating_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RUSTED_IRON_PILLAR_BLOCK = BLOCKS.register("rusted_iron_pillar_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> RUSTED_IRON_PLATING_BLOCK = BLOCKS.register("rusted_iron_plating_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BLUE_IRON_PLATING_BLOCK = BLOCKS.register("blue_iron_plating_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).lightLevel(state -> 15).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> IRON_MARK_BLOCK = BLOCKS.register("iron_mark_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(5f, 2.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MOON_STONE = BLOCKS.register("moon_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRACKED_MOON_STONE_BRICKS = BLOCKS.register("cracked_moon_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MOON_STONE_BRICKS = BLOCKS.register("moon_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MOON_STONE_BRICK_SLAB = BLOCKS.register("moon_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MOON_STONE_BRICK_STAIRS = BLOCKS.register("moon_stone_brick_stairs", () -> new StairBlock(() -> ModInit.MOON_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModInit.MOON_STONE_BRICKS.get()).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SKY_STONE = BLOCKS.register("sky_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MARS_STONE = BLOCKS.register("mars_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRACKED_MARS_STONE_BRICKS = BLOCKS.register("cracked_mars_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MARS_STONE_BRICKS = BLOCKS.register("mars_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MARS_STONE_BRICK_SLAB = BLOCKS.register("mars_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MARS_STONE_BRICK_STAIRS = BLOCKS.register("mars_stone_brick_stairs", () -> new StairBlock(() -> ModInit.MARS_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModInit.MARS_STONE_BRICKS.get()).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MERCURY_STONE = BLOCKS.register("mercury_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRACKED_MERCURY_STONE_BRICKS = BLOCKS.register("cracked_mercury_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MERCURY_STONE_BRICKS = BLOCKS.register("mercury_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MERCURY_STONE_BRICK_SLAB = BLOCKS.register("mercury_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MERCURY_STONE_BRICK_STAIRS = BLOCKS.register("mercury_stone_brick_stairs", () -> new StairBlock(() -> ModInit.MERCURY_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModInit.MERCURY_STONE_BRICKS.get()).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> VENUS_SANDSTONE = BLOCKS.register("venus_sandstone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRACKED_VENUS_SANDSTONE_BRICKS = BLOCKS.register("cracked_venus_sandstone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_SANDSTONE_BRICKS = BLOCKS.register("venus_sandstone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_SANDSTONE_BRICK_SLAB = BLOCKS.register("venus_sandstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_SANDSTONE_BRICK_STAIRS = BLOCKS.register("venus_sandstone_brick_stairs", () -> new StairBlock(() -> ModInit.VENUS_SANDSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModInit.VENUS_SANDSTONE_BRICKS.get()).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> VENUS_STONE = BLOCKS.register("venus_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRACKED_VENUS_STONE_BRICKS = BLOCKS.register("cracked_venus_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_STONE_BRICKS = BLOCKS.register("venus_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_STONE_BRICK_SLAB = BLOCKS.register("venus_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_STONE_BRICK_STAIRS = BLOCKS.register("venus_stone_brick_stairs", () -> new StairBlock(() -> ModInit.VENUS_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModInit.VENUS_STONE_BRICKS.get()).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GLACIO_STONE = BLOCKS.register("glacio_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PERMAFROST_STONE = BLOCKS.register("permafrost", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRACKED_GLACIO_STONE_BRICKS = BLOCKS.register("cracked_glacio_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLACIO_STONE_BRICKS = BLOCKS.register("glacio_stone_bricks", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLACIO_STONE_BRICK_SLAB = BLOCKS.register("glacio_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLACIO_STONE_BRICK_STAIRS = BLOCKS.register("glacio_stone_brick_stairs", () -> new StairBlock(() -> ModInit.VENUS_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ModInit.VENUS_STONE_BRICKS.get()).requiresCorrectToolForDrops()));


    public static final RegistryObject<Block> INFERNAL_SPIRE_BLOCK = BLOCKS.register("infernal_spire_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5f, 1f).requiresCorrectToolForDrops()));


    //Faling Block
    public static final RegistryObject<Block> MOON_SAND = BLOCKS.register("moon_sand", () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY).sound(SoundType.SAND).strength(0.5f, 0.5f)));
    public static final RegistryObject<Block> MARS_SAND = BLOCKS.register("mars_sand", () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_ORANGE).sound(SoundType.SAND).strength(0.5f, 0.5f)));
    public static final RegistryObject<Block> VENUS_SAND = BLOCKS.register("venus_sand", () -> new FallingBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_ORANGE).sound(SoundType.SAND).strength(0.5f, 0.5f)));


    //ORES
    public static final RegistryObject<Block> MOON_CHEESE_ORE = BLOCKS.register("moon_cheese_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MOON_DESH_ORE = BLOCKS.register("moon_desh_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MOON_IRON_ORE = BLOCKS.register("moon_iron_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MOON_ICE_SHARD_ORE = BLOCKS.register("moon_ice_shard_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> MARS_IRON_ORE = BLOCKS.register("mars_iron_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MARS_DIAMOND_ORE = BLOCKS.register("mars_diamond_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> MARS_OSTRUM_ORE = BLOCKS.register("mars_ostrum_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MARS_ICE_SHARD_ORE = BLOCKS.register("mars_ice_shard_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> MERCURY_IRON_ORE = BLOCKS.register("mercury_iron_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_COAL_ORE = BLOCKS.register("venus_coal_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> VENUS_GOLD_ORE = BLOCKS.register("venus_gold_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VENUS_DIAMOND_ORE = BLOCKS.register("venus_diamond_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> VENUS_CALORITE_ORE = BLOCKS.register("venus_calorite_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLACIO_ICE_SHARD_ORE = BLOCKS.register("glacio_ice_shard_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> GLACIO_COAL_ORE = BLOCKS.register("glacio_coal_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(0, 2)));
    public static final RegistryObject<Block> GLACIO_COPPER_ORE = BLOCKS.register("glacio_copper_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLACIO_IRON_ORE = BLOCKS.register("glacio_iron_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GLACIO_LAPIS_ORE = BLOCKS.register("glacio_lapis_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(2, 5)));

    //Generel Block Items
    public static final RegistryObject<BlockItem> STEEL_BLOCK_ITEM = ITEMS.register("steel_block", () -> new BlockItem(ModInit.STEEL_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> DESH_BLOCK_ITEM = ITEMS.register("desh_block", () -> new BlockItem(ModInit.DESH_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> OSTRUM_BLOCK_ITEM = ITEMS.register("ostrum_block", () -> new BlockItem(ModInit.OSTRUM_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> CALORITE_BLOCK_ITEM = ITEMS.register("calorite_block", () -> new BlockItem(ModInit.CALORITE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> RAW_DESH_BLOCK_ITEM = ITEMS.register("raw_desh_block", () -> new BlockItem(ModInit.RAW_DESH_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> RAW_OSTRUM_BLOCK_ITEM = ITEMS.register("raw_ostrum_block", () -> new BlockItem(ModInit.RAW_OSTRUM_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> RAW_CALORITE_BLOCK_ITEM = ITEMS.register("raw_calorite_block", () -> new BlockItem(ModInit.RAW_CALORITE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> IRON_PLATING_BLOCK_ITEM = ITEMS.register("iron_plating_block", () -> new BlockItem(ModInit.IRON_PLATING_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> RUSTED_IRON_PILLAR_BLOCK_ITEM = ITEMS.register("rusted_iron_pillar_block", () -> new BlockItem(ModInit.RUSTED_IRON_PILLAR_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> RUSTED_IRON_PLATING_BLOCK_ITEM = ITEMS.register("rusted_iron_plating_block", () -> new BlockItem(ModInit.RUSTED_IRON_PLATING_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> BLUE_IRON_PLATING_BLOCK_ITEM = ITEMS.register("blue_iron_plating_block", () -> new BlockItem(ModInit.BLUE_IRON_PLATING_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> INFERNAL_SPIRE_BLOCK_ITEM = ITEMS.register("infernal_spire_block", () -> new BlockItem(ModInit.INFERNAL_SPIRE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> IRON_MARK_BLOCK_ITEM = ITEMS.register("iron_mark_block", () -> new BlockItem(ModInit.IRON_MARK_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> SKY_STONE_ITEM = ITEMS.register("sky_stone", () -> new BlockItem(ModInit.SKY_STONE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));

    public static final RegistryObject<BlockItem> MOON_STONE_ITEM = ITEMS.register("moon_stone", () -> new BlockItem(ModInit.MOON_STONE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICKS_ITEM = ITEMS.register("moon_stone_bricks", () -> new BlockItem(ModInit.MOON_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_MOON_STONE_BRICKS_ITEM = ITEMS.register("cracked_moon_stone_bricks", () -> new BlockItem(ModInit.CRACKED_MOON_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICK_SLAB_ITEM = ITEMS.register("moon_stone_brick_slab", () -> new BlockItem(ModInit.MOON_STONE_BRICK_SLAB.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_STONE_BRICK_STAIRS_ITEM = ITEMS.register("moon_stone_brick_stairs", () -> new BlockItem(ModInit.MOON_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));

    public static final RegistryObject<BlockItem> MARS_STONE_ITEM = ITEMS.register("mars_stone", () -> new BlockItem(ModInit.MARS_STONE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICKS_ITEM = ITEMS.register("mars_stone_bricks", () -> new BlockItem(ModInit.MARS_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_MARS_STONE_BRICKS_ITEM = ITEMS.register("cracked_mars_stone_bricks", () -> new BlockItem(ModInit.CRACKED_MARS_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICK_SLAB_ITEM = ITEMS.register("mars_stone_brick_slab", () -> new BlockItem(ModInit.MARS_STONE_BRICK_SLAB.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_STONE_BRICK_STAIRS_ITEM = ITEMS.register("mars_stone_brick_stairs", () -> new BlockItem(ModInit.MARS_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));

    public static final RegistryObject<BlockItem> MERCURY_STONE_ITEM = ITEMS.register("mercury_stone", () -> new BlockItem(ModInit.MERCURY_STONE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICKS_ITEM = ITEMS.register("mercury_stone_bricks", () -> new BlockItem(ModInit.MERCURY_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_MERCURY_STONE_BRICKS_ITEM = ITEMS.register("cracked_mercury_stone_bricks", () -> new BlockItem(ModInit.CRACKED_MERCURY_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICK_SLAB_ITEM = ITEMS.register("mercury_stone_brick_slab", () -> new BlockItem(ModInit.MERCURY_STONE_BRICK_SLAB.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MERCURY_STONE_BRICK_STAIRS_ITEM = ITEMS.register("mercury_stone_brick_stairs", () -> new BlockItem(ModInit.MERCURY_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));

    public static final RegistryObject<BlockItem> VENUS_STONE_ITEM = ITEMS.register("venus_stone", () -> new BlockItem(ModInit.VENUS_STONE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICKS_ITEM = ITEMS.register("venus_stone_bricks", () -> new BlockItem(ModInit.VENUS_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_VENUS_STONE_BRICKS_ITEM = ITEMS.register("cracked_venus_stone_bricks", () -> new BlockItem(ModInit.CRACKED_VENUS_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICK_SLAB_ITEM = ITEMS.register("venus_stone_brick_slab", () -> new BlockItem(ModInit.VENUS_STONE_BRICK_SLAB.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_STONE_BRICK_STAIRS_ITEM = ITEMS.register("venus_stone_brick_stairs", () -> new BlockItem(ModInit.VENUS_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));

    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_ITEM = ITEMS.register("venus_sandstone", () -> new BlockItem(ModInit.VENUS_SANDSTONE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICKS_ITEM = ITEMS.register("venus_sandstone_bricks", () -> new BlockItem(ModInit.VENUS_SANDSTONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_VENUS_SANDSTONE_BRICKS_ITEM = ITEMS.register("cracked_venus_sandstone_bricks", () -> new BlockItem(ModInit.CRACKED_VENUS_SANDSTONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICK_SLAB_ITEM = ITEMS.register("venus_sandstone_brick_slab", () -> new BlockItem(ModInit.VENUS_SANDSTONE_BRICK_SLAB.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_SANDSTONE_BRICK_STAIRS_ITEM = ITEMS.register("venus_sandstone_brick_stairs", () -> new BlockItem(ModInit.VENUS_SANDSTONE_BRICK_STAIRS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));

    public static final RegistryObject<BlockItem> PERMAFROST_ITEM = ITEMS.register("permafrost", () -> new BlockItem(ModInit.PERMAFROST_STONE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_STONE_ITEM = ITEMS.register("glacio_stone", () -> new BlockItem(ModInit.GLACIO_STONE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICKS_ITEM = ITEMS.register("glacio_stone_bricks", () -> new BlockItem(ModInit.GLACIO_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> CRACKED_GLACIO_STONE_BRICKS_ITEM = ITEMS.register("cracked_glacio_stone_bricks", () -> new BlockItem(ModInit.CRACKED_GLACIO_STONE_BRICKS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICK_SLAB_ITEM = ITEMS.register("glacio_stone_brick_slab", () -> new BlockItem(ModInit.GLACIO_STONE_BRICK_SLAB.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_STONE_BRICK_STAIRS_ITEM = ITEMS.register("glacio_stone_brick_stairs", () -> new BlockItem(ModInit.GLACIO_STONE_BRICK_STAIRS.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));

    public static final RegistryObject<BlockItem> ROCKET_LAUNCH_PAD_ITEM = ITEMS.register("rocket_launch_pad", () -> new BlockItem(ModInit.ROCKET_LAUNCH_PAD.get(), new Item.Properties().tab(ItemGroups.tab_normal)));

    public static final RegistryObject<BlockItem> MOON_SAND_ITEM = ITEMS.register("moon_sand", () -> new BlockItem(ModInit.MOON_SAND.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_SAND_ITEM = ITEMS.register("mars_sand", () -> new BlockItem(ModInit.MARS_SAND.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_SAND_ITEM = ITEMS.register("venus_sand", () -> new BlockItem(ModInit.VENUS_SAND.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));

    public static final RegistryObject<BlockItem> MOON_CHEESE_ORE_ITEM = ITEMS.register("moon_cheese_ore", () -> new BlockItem(ModInit.MOON_CHEESE_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_DESH_ORE_ITEM = ITEMS.register("moon_desh_ore", () -> new BlockItem(ModInit.MOON_DESH_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_IRON_ORE_ITEM = ITEMS.register("moon_iron_ore", () -> new BlockItem(ModInit.MOON_IRON_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MOON_ICE_SHARD_ITEM = ITEMS.register("moon_ice_shard_ore", () -> new BlockItem(ModInit.MOON_ICE_SHARD_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_IRON_ORE_ITEM = ITEMS.register("mars_iron_ore", () -> new BlockItem(ModInit.MARS_IRON_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_DIAMOND_ORE_ITEM = ITEMS.register("mars_diamond_ore", () -> new BlockItem(ModInit.MARS_DIAMOND_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_OSTRUM_ORE_ITEM = ITEMS.register("mars_ostrum_ore", () -> new BlockItem(ModInit.MARS_OSTRUM_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MARS_ICE_SHARD_ORE_ITEM = ITEMS.register("mars_ice_shard_ore", () -> new BlockItem(ModInit.MARS_ICE_SHARD_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> MERCURY_IRON_ORE_ITEM = ITEMS.register("mercury_iron_ore", () -> new BlockItem(ModInit.MERCURY_IRON_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_COAL_ORE_ITEM = ITEMS.register("venus_coal_ore", () -> new BlockItem(ModInit.VENUS_COAL_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_GOLD_ORE_ITEM = ITEMS.register("venus_gold_ore", () -> new BlockItem(ModInit.VENUS_GOLD_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_DIAMOND_ORE_ITEM = ITEMS.register("venus_diamond_ore", () -> new BlockItem(ModInit.VENUS_DIAMOND_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> VENUS_CALORITE_ORE_ITEM = ITEMS.register("venus_calorite_ore", () -> new BlockItem(ModInit.VENUS_CALORITE_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_ICE_SHARD_ORE_ITEM = ITEMS.register("glacio_ice_shard_ore", () -> new BlockItem(ModInit.GLACIO_ICE_SHARD_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_COAL_ORE_ITEM = ITEMS.register("glacio_coal_ore", () -> new BlockItem(ModInit.GLACIO_COAL_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_COPPER_ORE_ITEM = ITEMS.register("glacio_copper_ore", () -> new BlockItem(ModInit.GLACIO_COPPER_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_IRON_ORE_ITEM = ITEMS.register("glacio_iron_ore", () -> new BlockItem(ModInit.GLACIO_IRON_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));
    public static final RegistryObject<BlockItem> GLACIO_LAPIS_ORE_ITEM = ITEMS.register("glacio_lapis_ore", () -> new BlockItem(ModInit.GLACIO_LAPIS_ORE.get(), new Item.Properties().tab(ItemGroups.tab_blocks)));


    //Space Suit Items
    public static final RegistryObject<Item> OXYGEN_MASK = ITEMS.register("oxygen_mask", () -> SpaceSuit.OXYGEN_MASK);
    public static final RegistryObject<Item> SPACE_SUIT = ITEMS.register("space_suit", () -> SpaceSuit.SPACE_SUIT);
    public static final RegistryObject<Item> SPACE_PANTS = ITEMS.register("space_pants", () -> SpaceSuit.SPACE_PANTS);
    public static final RegistryObject<Item> SPACE_BOOTS = ITEMS.register("space_boots", () -> SpaceSuit.SPACE_BOOTS);


    //Netherite Space Suit Items
    public static final RegistryObject<Item> NETHERITE_OXYGEN_MASK = ITEMS.register("netherite_oxygen_mask", () -> NetheriteSpaceSuit.NETHERITE_OXYGEN_MASK);
    public static final RegistryObject<Item> NETHERITE_SPACE_SUIT = ITEMS.register("netherite_space_suit", () -> NetheriteSpaceSuit.NETHERITE_SPACE_SUIT);
    public static final RegistryObject<Item> NETHERITE_SPACE_PANTS = ITEMS.register("netherite_space_pants", () -> NetheriteSpaceSuit.NETHERITE_SPACE_PANTS);
    public static final RegistryObject<Item> NETHERITE_SPACE_BOOTS = ITEMS.register("netherite_space_boots", () -> NetheriteSpaceSuit.NETHERITE_SPACE_BOOTS);

    //Flag Items
    public static final RegistryObject<Item> FLAG_ITEM = ITEMS.register("flag", () -> new DoubleHighBlockItem(FLAG_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_BLUE_ITEM = ITEMS.register("flag_blue", () -> new DoubleHighBlockItem(FLAG_BLUE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_BROWN_ITEM = ITEMS.register("flag_brown", () -> new DoubleHighBlockItem(FLAG_BROWN_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_CYAN_ITEM = ITEMS.register("flag_cyan", () -> new DoubleHighBlockItem(FLAG_CYAN_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_GRAY_ITEM = ITEMS.register("flag_gray", () -> new DoubleHighBlockItem(FLAG_GRAY_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_GREEN_ITEM = ITEMS.register("flag_green", () -> new DoubleHighBlockItem(FLAG_GREEN_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_LIGHT_BLUE_ITEM = ITEMS.register("flag_light_blue", () -> new DoubleHighBlockItem(FLAG_LIGHT_BLUE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_LIME_ITEM = ITEMS.register("flag_lime", () -> new DoubleHighBlockItem(FLAG_LIME_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_MAGENTA_ITEM = ITEMS.register("flag_magenta", () -> new DoubleHighBlockItem(FLAG_MAGENTA_BLOCk.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_ORANGE_ITEM = ITEMS.register("flag_orange", () -> new DoubleHighBlockItem(FLAG_ORANGE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_PINK_ITEM = ITEMS.register("flag_pink", () -> new DoubleHighBlockItem(FLAG_PINK_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_PURPLE_ITEM = ITEMS.register("flag_purple", () -> new DoubleHighBlockItem(FLAG_PURPLE_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_RED_ITEM = ITEMS.register("flag_red", () -> new DoubleHighBlockItem(FLAG_RED_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));
    public static final RegistryObject<Item> FLAG_YELLOW_ITEM = ITEMS.register("flag_yellow", () -> new DoubleHighBlockItem(FLAG_YELLOW_BLOCK.get(), new Item.Properties().tab(ItemGroups.tab_flags)));

    //RocketParts
    public static final DeferredRegister<RocketPart> ROCKET_PARTS = DeferredRegister.create(new ResourceLocation(BeyondEarthMod.MODID, "rocket_part"), BeyondEarthMod.MODID);
    public static final Supplier<IForgeRegistry<RocketPart>> ROCKET_PARTS_REGISTRY = ROCKET_PARTS.makeRegistry(RocketPart.class, RegistryBuilder::new);

    public static final RegistryObject<RocketPart> ROCKET_PART_EMPTY = ROCKET_PARTS.register("emtpy", () -> RocketPart.EMPTY);
    public static final RegistryObject<RocketPart> ROCKET_PART_NOSE = ROCKET_PARTS.register("nose", () -> new RocketPart(1));
	public static final RegistryObject<RocketPart> ROCKET_PART_BODY = ROCKET_PARTS.register("body", () -> new RocketPart(6));
	public static final RegistryObject<RocketPart> ROCKET_PART_TANK = ROCKET_PARTS.register("tank", () -> new RocketPart(2));
	public static final RegistryObject<RocketPart> ROCKET_PART_FIN_LEFT = ROCKET_PARTS.register("fin_left", () -> new RocketPart(2));
	public static final RegistryObject<RocketPart> ROCKET_PART_FIN_RIGHT = ROCKET_PARTS.register("fin_right", () -> new RocketPart(2));
	public static final RegistryObject<RocketPart> ROCKET_PART_ENGINE = ROCKET_PARTS.register("engine", () -> new RocketPart(1));
}
