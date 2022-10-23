package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.entities.FlagBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.GlobeTileEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.CoalGeneratorBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.CompressorBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.FuelRefineryBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.NASAWorkbenchBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.OxygenDistributorBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.OxygenLoaderBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.SolarPanelBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.WaterPumpBlockEntity;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BeyondEarth.MODID);

    /** BLOCK ENTITIES (Machines) */
    public static final RegistryObject<BlockEntityType<?>> FUEL_REFINERY_BLOCK_ENTITY = BLOCK_ENTITIES.register("fuel_refinery", () -> BlockEntityType.Builder.of(FuelRefineryBlockEntity::new, BlockRegistry.FUEL_REFINERY_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> COMPRESSOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("compressor", () -> BlockEntityType.Builder.of(CompressorBlockEntity::new, BlockRegistry.COMPRESSOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> COAL_GENERATOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("coal_generator", () -> BlockEntityType.Builder.of(CoalGeneratorBlockEntity::new, BlockRegistry.COAL_GENERATOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> OXYGEN_LOADER_BLOCK_ENTITY = BLOCK_ENTITIES.register("oxygen_loader", () -> BlockEntityType.Builder.of(OxygenLoaderBlockEntity::new, BlockRegistry.OXYGEN_LOADER_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> SOLAR_PANEL_BLOCK_ENTITY = BLOCK_ENTITIES.register("solar_panel", () -> BlockEntityType.Builder.of(SolarPanelBlockEntity::new, BlockRegistry.SOLAR_PANEL_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> NASA_WORKBENCH_BLOCK_ENTITY = BLOCK_ENTITIES.register("nasa_workbench", () -> BlockEntityType.Builder.of(NASAWorkbenchBlockEntity::new, BlockRegistry.NASA_WORKBENCH_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<OxygenDistributorBlockEntity>> OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK_ENTITY = BLOCK_ENTITIES.register("oxygen_bubble_distributor", () -> BlockEntityType.Builder.of(OxygenDistributorBlockEntity::new, BlockRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK.get()).build(null));
    public static final RegistryObject<BlockEntityType<?>> WATER_PUMP_BLOCK_ENTITY = BLOCK_ENTITIES.register("water_pump", () -> BlockEntityType.Builder.of(WaterPumpBlockEntity::new, BlockRegistry.WATER_PUMP_BLOCK.get()).build(null));


    /** BLOCK ENTITIES (Globes) */
    public static final RegistryObject<BlockEntityType<GlobeTileEntity>> GLOBE_BLOCK_ENTITY = BLOCK_ENTITIES.register("globe", () -> BlockEntityType.Builder.of(GlobeTileEntity::new, BlockRegistry.EARTH_GLOBE_BLOCK.get(), BlockRegistry.MOON_GLOBE_BLOCK.get(), BlockRegistry.MARS_GLOBE_BLOCK.get(), BlockRegistry.MERCURY_GLOBE_BLOCK.get(), BlockRegistry.VENUS_GLOBE_BLOCK.get(), BlockRegistry.GLACIO_GLOBE_BLOCK.get()).build(null));

    /** BLOCK ENTITIES (Flags) */
    public static final RegistryObject<BlockEntityType<FlagBlockEntity>> FLAG_BLOCK_ENTITY = BLOCK_ENTITIES.register("flag", () -> BlockEntityType.Builder.of(FlagBlockEntity::new, BlockRegistry.FLAG_BLOCK.get()).build(null));
}
