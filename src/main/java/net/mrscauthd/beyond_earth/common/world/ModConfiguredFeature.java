package net.mrscauthd.beyond_earth.common.world;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;

import java.util.List;

public class ModConfiguredFeature {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, BeyondEarth.MODID);

    // GLACIO
    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_COAL_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_COAL_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_COPPER_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_COPPER_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_ICE_SHRAD_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_ICE_SHARD_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_IRON_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_IRON_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> GLACIO_LAPIS_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_LAPIS_ORE.get().defaultBlockState())));

    // MARS
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MARS_DIAMOND_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_DIAMOND_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MARS_ICE_SHARD_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_ICE_SHARD_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MARS_IRON_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_IRON_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MARS_OSTRUM_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_OSTRUM_ORE.get().defaultBlockState())));

    // MERCURY
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MERCURY_IRON_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MERCURY_STONE.get()), BlockRegistry.MERCURY_IRON_ORE.get().defaultBlockState())));

    // MOON
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_CHEESE_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_CHEESE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_DESH_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_DESH_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_ICE_SHARD_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_ICE_SHARD_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_IRON_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_IRON_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> MOON_SOUL_SOIL_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), Blocks.SOUL_SOIL.defaultBlockState())));

    // VENUS
    public static final Supplier<List<OreConfiguration.TargetBlockState>> VENUS_CALORITE_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_CALORITE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> VENUS_COAL_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_COAL_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> VENUS_DIAMOND_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_DIAMOND_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> VENUS_GOLD_ORE_REPLACEABLES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_GOLD_ORE.get().defaultBlockState())));



    // GLACIO
    public static final RegistryObject<ConfiguredFeature<?, ?>> GLACIO_COAL_ORE = CONFIGURED_FEATURES.register("glacio_coal_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(GLACIO_COAL_ORE_REPLACEABLES.get(), 17)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GLACIO_COPPER_ORE = CONFIGURED_FEATURES.register("glacio_copper_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(GLACIO_COPPER_ORE_REPLACEABLES.get(), 17)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GLACIO_ICE_SHARD_ORE = CONFIGURED_FEATURES.register("glacio_ice_shard_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(GLACIO_ICE_SHRAD_ORE_REPLACEABLES.get(), 10)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GLACIO_IRON_ORE = CONFIGURED_FEATURES.register("glacio_iron_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(GLACIO_IRON_ORE_REPLACEABLES.get(), 11)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> GLACIO_LAPIS_ORE = CONFIGURED_FEATURES.register("glacio_lapis_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(GLACIO_LAPIS_ORE_REPLACEABLES.get(), 9)));

    // MARS
    public static final RegistryObject<ConfiguredFeature<?, ?>> MARS_DIAMOND_ORE = CONFIGURED_FEATURES.register("mars_diamond_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MARS_DIAMOND_ORE_REPLACEABLES.get(), 7)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MARS_ICE_SHARD_ORE = CONFIGURED_FEATURES.register("mars_ice_shard_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MARS_ICE_SHARD_ORE_REPLACEABLES.get(), 10)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MARS_IRON_ORE = CONFIGURED_FEATURES.register("mars_iron_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MARS_IRON_ORE_REPLACEABLES.get(), 11)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MARS_OSTRUM_ORE = CONFIGURED_FEATURES.register("mars_ostrum_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MARS_OSTRUM_ORE_REPLACEABLES.get(), 8)));

    // MERCURY
    public static final RegistryObject<ConfiguredFeature<?, ?>> MERCURY_IRON_ORE = CONFIGURED_FEATURES.register("mercury_iron_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MERCURY_IRON_ORE_REPLACEABLES.get(), 8)));

    // MOON
    public static final RegistryObject<ConfiguredFeature<?, ?>> MOON_CHEESE_ORE = CONFIGURED_FEATURES.register("moon_cheese_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MOON_CHEESE_ORE_REPLACEABLES.get(), 10)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MOON_DESH_ORE = CONFIGURED_FEATURES.register("moon_desh_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MOON_DESH_ORE_REPLACEABLES.get(), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MOON_ICE_SHARD_ORE = CONFIGURED_FEATURES.register("moon_ice_shard_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MOON_ICE_SHARD_ORE_REPLACEABLES.get(), 10)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MOON_IRON_ORE = CONFIGURED_FEATURES.register("moon_iron_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MOON_IRON_ORE_REPLACEABLES.get(), 11)));
    public static final RegistryObject<ConfiguredFeature<?,?>> MOON_SOUL_SOIL_CONFIGURED = CONFIGURED_FEATURES.register("moon_soul_soil", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MOON_SOUL_SOIL_REPLACEABLES.get(), 60)));

    // VENUS
    public static final RegistryObject<ConfiguredFeature<?, ?>> VENUS_CALORITE_ORE = CONFIGURED_FEATURES.register("venus_calorite_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(VENUS_CALORITE_ORE_REPLACEABLES.get(), 8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> VENUS_COAL_ORE = CONFIGURED_FEATURES.register("venus_coal_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(VENUS_COAL_ORE_REPLACEABLES.get(), 17)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> VENUS_DIAMOND_ORE = CONFIGURED_FEATURES.register("venus_diamond_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(VENUS_DIAMOND_ORE_REPLACEABLES.get(), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> VENUS_GOLD_ORE = CONFIGURED_FEATURES.register("venus_gold_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(VENUS_GOLD_ORE_REPLACEABLES.get(), 10)));

}

