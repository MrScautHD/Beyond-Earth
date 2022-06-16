package net.mrscauthd.beyond_earth.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.*;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.features.VenusDeltas;

import java.util.List;

public class FeatureRegistry {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BeyondEarth.MODID);
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, BeyondEarth.MODID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, BeyondEarth.MODID);

    /** FEATURES */
    public static final RegistryObject<VenusDeltas> VENUS_DELTAS_FEATURE = FEATURES.register("venus_deltas", () -> new VenusDeltas(ColumnFeatureConfiguration.CODEC));

    /** CONFIGURED FEATURES */
    public static final RegistryObject<ConfiguredFeature<?,?>> VENUS_DELTAS_SMALL_CONFIGURED_FEATURE = CONFIGURED_FEATURES.register("venus_deltas_small", () -> new ConfiguredFeature<>(VENUS_DELTAS_FEATURE.get(), new ColumnFeatureConfiguration(ConstantInt.of(1), UniformInt.of(1, 4))));
    public static final RegistryObject<ConfiguredFeature<?,?>> VENUS_DELTAS_LARGE_CONFIGURED_FEATURE = CONFIGURED_FEATURES.register("venus_deltas_large", () -> new ConfiguredFeature<>(VENUS_DELTAS_FEATURE.get(), new ColumnFeatureConfiguration(UniformInt.of(2, 3), UniformInt.of(5, 10))));

    /** PLACED FEATURES */
    public static final RegistryObject<PlacedFeature> VENUS_DELTAS_SMALL = PLACED_FEATURES.register("venus_deltas_small", () -> new PlacedFeature(Holder.direct(VENUS_DELTAS_SMALL_CONFIGURED_FEATURE.get()), List.of(CountOnEveryLayerPlacement.of(4), BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> VENUS_DELTAS_LARGE = PLACED_FEATURES.register("venus_deltas_large", () -> new PlacedFeature(Holder.direct(VENUS_DELTAS_LARGE_CONFIGURED_FEATURE.get()), List.of(CountOnEveryLayerPlacement.of(2), BiomeFilter.biome())));

}