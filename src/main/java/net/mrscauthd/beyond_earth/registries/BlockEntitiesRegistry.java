package net.mrscauthd.beyond_earth.registries;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.flag.FlagTileEntity;
import net.mrscauthd.beyond_earth.globe.GlobeTileEntity;

public class BlockEntitiesRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, BeyondEarth.MODID);

    /** BLOCK ENTITIES (Machines) */

    //TODO FORGOT TO REWORK IT NOT
    /*
    public static final RegistryObject<BlockEntityType<?>> FUEL_REFINERY_BLOCK_ENTITY = BLOCK_ENTITIES.register("fuel_refinery", () -> BlockEntityType.Builder.of(FuelRefineryBlockEntity::new, BlocksRegistry.FUEL_REFINERY_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> COMPRESSOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("compressor", () -> BlockEntityType.Builder.of(CompressorBlockEntity::new, BlocksRegistry.COMPRESSOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> COAL_GENERATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("coal_generator", () -> BlockEntityType.Builder.of(CoalGeneratorBlockEntity::new, BlocksRegistry.COAL_GENERATOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> OXYGEN_LOADER_BLOCK_ENTITY = BLOCK_ENTITIES.register("oxygen_loader", () -> BlockEntityType.Builder.of(OxygenLoaderBlockEntity::new, BlocksRegistry.OXYGEN_LOADER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> SOLAR_PANEL_BLOCK_ENTITY = BLOCK_ENTITIES.register("solar_panel", () -> BlockEntityType.Builder.of(SolarPanelBlockEntity::new, BlocksRegistry.SOLAR_PANEL_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> NASA_WORKBENCH_BLOCK_ENTITY = BLOCK_ENTITIES.register("nasa_workbench", () -> BlockEntityType.Builder.of(NASAWorkbenchBlockEntity::new, BlocksRegistry.NASA_WORKBENCH_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<OxygenBubbleDistributorBlockEntity>> OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("oxygen_bubble_distributor", () -> BlockEntityType.Builder.of(OxygenBubbleDistributorBlockEntity::new, BlocksRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> WATER_PUMP_BLOCK_ENTITY = BLOCK_ENTITIES.register("water_pump", () -> BlockEntityType.Builder.of(WaterPumpBlockEntity::new, BlocksRegistry.WATER_PUMP_BLOCK.get()).build(null));
*/

    /** BLOCK ENTITIES (Globes) */
    public static final RegistryObject<BlockEntityType<GlobeTileEntity>> GLOBE_BLOCK_ENTITY = BLOCK_ENTITIES.register("globe", () -> BlockEntityType.Builder.of(GlobeTileEntity::new, BlocksRegistry.EARTH_GLOBE_BLOCK.get(), BlocksRegistry.MOON_GLOBE_BLOCK.get(), BlocksRegistry.MARS_GLOBE_BLOCK.get(), BlocksRegistry.MERCURY_GLOBE_BLOCK.get(), BlocksRegistry.VENUS_GLOBE_BLOCK.get(), BlocksRegistry.GLACIO_GLOBE_BLOCK.get()).build(null));

    /** BLOCK ENTITIES (Flags) */
    public static final RegistryObject<BlockEntityType<FlagTileEntity>> FLAG_BLOCK_ENTITY = BLOCK_ENTITIES.register("flag", () -> BlockEntityType.Builder.of(FlagTileEntity::new, BlocksRegistry.FLAG_BLOCK.get(), BlocksRegistry.FLAG_BLUE_BLOCK.get(), BlocksRegistry.FLAG_BROWN_BLOCK.get(), BlocksRegistry.FLAG_CYAN_BLOCK.get(), BlocksRegistry.FLAG_GRAY_BLOCK.get(), BlocksRegistry.FLAG_GRAY_BLOCK.get(), BlocksRegistry.FLAG_GREEN_BLOCK.get(), BlocksRegistry.FLAG_LIGHT_BLUE_BLOCK.get(), BlocksRegistry.FLAG_LIME_BLOCK.get(), BlocksRegistry.FLAG_MAGENTA_BLOCk.get(), BlocksRegistry.FLAG_ORANGE_BLOCK.get(), BlocksRegistry.FLAG_PINK_BLOCK.get(), BlocksRegistry.FLAG_PURPLE_BLOCK.get(), BlocksRegistry.FLAG_RED_BLOCK.get(), BlocksRegistry.FLAG_YELLOW_BLOCK.get()).build(null));
}
