package net.mrscauthd.beyond_earth.registries;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.features.MarsBlockBlobFeature;
import net.mrscauthd.beyond_earth.features.VenusDeltas;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID)
public class FeatureRegistry {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BeyondEarthMod.MODID);

    /** MARS ROCK */
    public static final RegistryObject<MarsBlockBlobFeature> MARS_ROCK_FEATURE = FEATURES.register("mars_rock", () -> new MarsBlockBlobFeature(BlockStateConfiguration.CODEC));
    public static final Supplier<Holder<PlacedFeature>> MARS_ROCK = () -> PlacementUtils.register("mars_rock", FeatureUtils.register("mars_rock", MARS_ROCK_FEATURE.get(), new BlockStateConfiguration(Blocks.POLISHED_GRANITE.defaultBlockState())), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    /** VENUS DELTAS */
    public static final RegistryObject<VenusDeltas> VENUS_DELTAS_FEATURE = FEATURES.register("venus_deltas", () -> new VenusDeltas(ColumnFeatureConfiguration.CODEC));
    public static final Supplier<Holder<PlacedFeature>> VENUS_DELTAS_SMALL = () -> PlacementUtils.register("venus_deltas_small", FeatureUtils.register("venus_deltas_small", VENUS_DELTAS_FEATURE.get(), new ColumnFeatureConfiguration(ConstantInt.of(1), UniformInt.of(1, 4))), CountOnEveryLayerPlacement.of(4), BiomeFilter.biome());
    public static final Supplier<Holder<PlacedFeature>> VENUS_DELTAS_BIG = () -> PlacementUtils.register("venus_deltas_big", FeatureUtils.register("venus_deltas_big", VENUS_DELTAS_FEATURE.get(), new ColumnFeatureConfiguration(UniformInt.of(2, 3), UniformInt.of(5, 10))), CountOnEveryLayerPlacement.of(2), BiomeFilter.biome());

    @SubscribeEvent
    public static void biomesLoading(BiomeLoadingEvent event) {
        ResourceLocation name = event.getName();

        /** MARS ROCK */
        if (name.equals(BiomesRegistry.MARS_ROCKY_PLAINS)) {
            event.getGeneration().addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, MARS_ROCK.get());
        }

        /** VENUS DELTAS */
        if (name.equals(BiomesRegistry.INFERNAL_VENUS_BARRENS)) {
            event.getGeneration().addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, VENUS_DELTAS_SMALL.get());
            event.getGeneration().addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, VENUS_DELTAS_BIG.get());
        }
    }
}
