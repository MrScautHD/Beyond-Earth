package net.mrscauthd.beyond_earth.common.world;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;

import java.util.List;

public class ModConfiguredFeature {
    // GLACIO
    public static final List<OreConfiguration.TargetBlockState> GLACIO_COAL_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_COAL_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> GLACIO_COPPER_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_COPPER_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> GLACIO_ICE_SHRAD_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_ICE_SHARD_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> GLACIO_IRON_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_IRON_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> GLACIO_LAPIS_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.GLACIO_STONE.get()), BlockRegistry.GLACIO_LAPIS_ORE.get().defaultBlockState()));

    // MARS
    public static final List<OreConfiguration.TargetBlockState> MARS_DIAMOND_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_DIAMOND_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> MARS_ICE_SHARD_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_ICE_SHARD_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> MARS_IRON_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_IRON_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> MARS_OSTRUM_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MARS_STONE.get()), BlockRegistry.MARS_OSTRUM_ORE.get().defaultBlockState()));

    // MERCURY
    public static final List<OreConfiguration.TargetBlockState> MERCURY_IRON_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MERCURY_STONE.get()), BlockRegistry.MERCURY_IRON_ORE.get().defaultBlockState()));

    // MOON
    public static final List<OreConfiguration.TargetBlockState> MOON_CHEESE_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_CHEESE_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> MOON_DESH_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_DESH_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> MOON_ICE_SHARD_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_ICE_SHARD_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> MOON_IRON_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.MOON_STONE.get()), BlockRegistry.MOON_IRON_ORE.get().defaultBlockState()));

    // VENUS
    public static final List<OreConfiguration.TargetBlockState> VENUS_CALORITE_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_CALORITE_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> VENUS_COAL_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_COAL_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> VENUS_DIAMOND_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_DIAMOND_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> VENUS_GOLD_ORE_REPLACEABLES = List.of(
            OreConfiguration.target(new BlockMatchTest(BlockRegistry.VENUS_STONE.get()), BlockRegistry.VENUS_GOLD_ORE.get().defaultBlockState()));



    // GLACIO
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> GLACIO_COAL_ORE = FeatureUtils.register("glacio_coal_ore", Feature.ORE, new OreConfiguration(GLACIO_COAL_ORE_REPLACEABLES, 17));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> GLACIO_COPPER_ORE = FeatureUtils.register("glacio_copper_ore", Feature.ORE, new OreConfiguration(GLACIO_COPPER_ORE_REPLACEABLES, 17));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> GLACIO_ICE_SHARD_ORE = FeatureUtils.register("glacio_ice_shard_ore", Feature.ORE, new OreConfiguration(GLACIO_ICE_SHRAD_ORE_REPLACEABLES, 10));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> GLACIO_IRON_ORE = FeatureUtils.register("glacio_iron_ore", Feature.ORE, new OreConfiguration(GLACIO_IRON_ORE_REPLACEABLES, 11));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> GLACIO_LAPIS_ORE = FeatureUtils.register("glacio_lapis_ore", Feature.ORE, new OreConfiguration(GLACIO_LAPIS_ORE_REPLACEABLES, 9));

    // MARS
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARS_DIAMOND_ORE = FeatureUtils.register("mars_diamond_ore", Feature.ORE, new OreConfiguration(MARS_DIAMOND_ORE_REPLACEABLES, 7));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARS_ICE_SHARD_ORE = FeatureUtils.register("mars_ice_shard_ore", Feature.ORE, new OreConfiguration(MARS_ICE_SHARD_ORE_REPLACEABLES, 10));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARS_IRON_ORE = FeatureUtils.register("mars_iron_ore", Feature.ORE, new OreConfiguration(MARS_IRON_ORE_REPLACEABLES, 11));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MARS_OSTRUM_ORE = FeatureUtils.register("mars_ostrum_ore", Feature.ORE, new OreConfiguration(MARS_OSTRUM_ORE_REPLACEABLES, 8));

    // MERCURY
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MERCURY_IRON_ORE = FeatureUtils.register("mercury_iron_ore", Feature.ORE, new OreConfiguration(MERCURY_IRON_ORE_REPLACEABLES, 8));

    // MOON
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MOON_CHEESE_ORE = FeatureUtils.register("moon_cheese_ore", Feature.ORE, new OreConfiguration(MOON_CHEESE_ORE_REPLACEABLES, 10));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MOON_DESH_ORE = FeatureUtils.register("moon_desh_ore", Feature.ORE, new OreConfiguration(MOON_DESH_ORE_REPLACEABLES, 9));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MOON_ICE_SHARD_ORE = FeatureUtils.register("moon_ice_shard_ore", Feature.ORE, new OreConfiguration(MOON_ICE_SHARD_ORE_REPLACEABLES, 10));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> MOON_IRON_ORE = FeatureUtils.register("moon_iron_ore", Feature.ORE, new OreConfiguration(MOON_IRON_ORE_REPLACEABLES, 11));

    // VENUS
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> VENUS_CALORITE_ORE = FeatureUtils.register("venus_calorite_ore", Feature.ORE, new OreConfiguration(VENUS_CALORITE_ORE_REPLACEABLES, 8));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> VENUS_COAL_ORE = FeatureUtils.register("venus_coal_ore", Feature.ORE, new OreConfiguration(VENUS_COAL_ORE_REPLACEABLES, 17));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> VENUS_DIAMOND_ORE = FeatureUtils.register("venus_diamond_ore", Feature.ORE, new OreConfiguration(VENUS_DIAMOND_ORE_REPLACEABLES, 9));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> VENUS_GOLD_ORE = FeatureUtils.register("venus_gold_ore", Feature.ORE, new OreConfiguration(VENUS_GOLD_ORE_REPLACEABLES, 10));

}

