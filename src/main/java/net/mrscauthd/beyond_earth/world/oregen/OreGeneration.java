package net.mrscauthd.beyond_earth.world.oregen;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.registries.BiomesRegistry;
import net.mrscauthd.beyond_earth.registries.FeatureRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID)
public class OreGeneration {

    /** MOON ORES: */
    public static final TagKey<Block> MOON_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "moon_ore_replaceables"));
    public static final RuleTest MOON_MATCH = new TagMatchTest(MOON_ORE_REPLACEABLES);

    // MOON_CHEESE_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MOON_CHEESE_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("moon_cheese_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MOON_MATCH, ModInit.MOON_CHEESE_ORE.get().defaultBlockState(), 10)));
    public static final RegistryObject<PlacedFeature> MOON_CHEESE_ORE = FeatureRegistry.PLACED_FEATURES.register("moon_cheese_ore", () -> new PlacedFeature(MOON_CHEESE_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192)))));

    // MOON_SOUL_SOIL
    public static final RegistryObject<ConfiguredFeature<?,?>> MOON_SOUL_SOIL_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("moon_soul_soil", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MOON_MATCH, Blocks.SOUL_SOIL.defaultBlockState(), 60)));
    public static final RegistryObject<PlacedFeature> MOON_SOUL_SOIL = FeatureRegistry.PLACED_FEATURES.register("moon_soul_soil", () -> new PlacedFeature(MOON_SOUL_SOIL_CONFIGURED.getHolder().get(), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(100)))));

    // MOON_ICE_SHARD_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MOON_ICE_SHARD_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("moon_ice_shard_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MOON_MATCH, ModInit.MOON_ICE_SHARD_ORE.get().defaultBlockState(), 10)));
    public static final RegistryObject<PlacedFeature> MOON_ICE_SHARD_ORE = FeatureRegistry.PLACED_FEATURES.register("moon_ice_shard_ore", () -> new PlacedFeature(MOON_ICE_SHARD_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)))));

    // MOON_IRON_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MOON_IRON_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("moon_iron_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MOON_MATCH, ModInit.MOON_IRON_ORE.get().defaultBlockState(), 11)));
    public static final RegistryObject<PlacedFeature> MOON_IRON_ORE = FeatureRegistry.PLACED_FEATURES.register("moon_iron_ore", () -> new PlacedFeature(MOON_IRON_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));

    // MOON_IRON_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MOON_DESH_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("moon_desh_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MOON_MATCH, ModInit.MOON_DESH_ORE.get().defaultBlockState(), 9)));
    public static final RegistryObject<PlacedFeature> MOON_DESH_ORE = FeatureRegistry.PLACED_FEATURES.register("moon_desh_ore", () -> new PlacedFeature(MOON_DESH_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)))));

    /** MARS ORES: */
    public static final TagKey<Block> MARS_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "mars_ore_replaceables"));
    public static final RuleTest MARS_MATCH = new TagMatchTest(MARS_ORE_REPLACEABLES);

    // MARS_ICE_SHARD_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MARS_ICE_SHARD_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("mars_ice_shard_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MARS_MATCH, ModInit.MARS_ICE_SHARD_ORE.get().defaultBlockState(), 10)));
    public static final RegistryObject<PlacedFeature> MARS_ICE_SHARD_ORE = FeatureRegistry.PLACED_FEATURES.register("mars_ice_shard_ore", () -> new PlacedFeature(MARS_ICE_SHARD_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32)))));

    // MARS_IRON_ORE_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MARS_IRON_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("mars_iron_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MARS_MATCH, ModInit.MARS_IRON_ORE.get().defaultBlockState(), 11)));
    public static final RegistryObject<PlacedFeature> MARS_IRON_ORE = FeatureRegistry.PLACED_FEATURES.register("mars_iron_ore", () -> new PlacedFeature(MARS_IRON_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));

    // MARS_DIAMOND_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MARS_DIAMOND_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("mars_diamond_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MARS_MATCH, ModInit.MARS_DIAMOND_ORE.get().defaultBlockState(), 7)));
    public static final RegistryObject<PlacedFeature> MARS_DIAMOND_ORE = FeatureRegistry.PLACED_FEATURES.register("mars_diamond_ore", () -> new PlacedFeature(MARS_DIAMOND_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    // MARS_OSTRUM_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MARS_OSTRUM_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("mars_ostrum_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MARS_MATCH, ModInit.MARS_OSTRUM_ORE.get().defaultBlockState(), 8)));
    public static final RegistryObject<PlacedFeature> MARS_OSTRUM_ORE = FeatureRegistry.PLACED_FEATURES.register("mars_ostrum_ore", () -> new PlacedFeature(MARS_OSTRUM_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    /** MERCURY ORES: */
    public static final TagKey<Block> MERCURY_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "mercury_ore_replaceables"));
    public static final RuleTest MERCURY_MATCH = new TagMatchTest(MERCURY_ORE_REPLACEABLES);

    // MERCURY_IRON_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> MERCURY_IRON_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("mercury_iron_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(MERCURY_MATCH, ModInit.MERCURY_IRON_ORE.get().defaultBlockState(), 8)));
    public static final RegistryObject<PlacedFeature> MERCURY_IRON_ORE = FeatureRegistry.PLACED_FEATURES.register("mercury_iron_ore", () -> new PlacedFeature(MERCURY_IRON_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192)))));

    /** VENUS ORES: */
    public static final TagKey<Block> VENUS_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "venus_ore_replaceables"));
    public static final RuleTest VENUS_MATCH = new TagMatchTest(VENUS_ORE_REPLACEABLES);

    // VENUS_COAL_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> VENUS_COAL_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("venus_coal_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(VENUS_MATCH, ModInit.VENUS_COAL_ORE.get().defaultBlockState(), 17)));
    public static final RegistryObject<PlacedFeature> VENUS_COAL_ORE = FeatureRegistry.PLACED_FEATURES.register("venus_coal_ore", () -> new PlacedFeature(VENUS_COAL_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192)))));

    // VENUS_GOLD_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> VENUS_GOLD_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("venus_gold_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(VENUS_MATCH, ModInit.VENUS_GOLD_ORE.get().defaultBlockState(), 10)));
    public static final RegistryObject<PlacedFeature> VENUS_GOLD_ORE = FeatureRegistry.PLACED_FEATURES.register("venus_gold_ore", () -> new PlacedFeature(VENUS_GOLD_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)))));

    // VENUS_DIAMOND_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> VENUS_DIAMOND_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("venus_diamond_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(VENUS_MATCH, ModInit.VENUS_DIAMOND_ORE.get().defaultBlockState(), 9)));
    public static final RegistryObject<PlacedFeature> VENUS_DIAMOND_ORE = FeatureRegistry.PLACED_FEATURES.register("venus_diamond_ore", () -> new PlacedFeature(VENUS_DIAMOND_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    // VENUS_DIAMOND_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> VENUS_CALORITE_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("venus_calorite_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(VENUS_MATCH, ModInit.VENUS_CALORITE_ORE.get().defaultBlockState(), 8)));
    public static final RegistryObject<PlacedFeature> VENUS_CALORITE_ORE = FeatureRegistry.PLACED_FEATURES.register("venus_calorite_ore", () -> new PlacedFeature(VENUS_CALORITE_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    /** GLACIO ORES: */
    public static final TagKey<Block> GLACIO_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "glacio_ore_replaceables"));
    public static final RuleTest GLACIO_MATCH = new TagMatchTest(GLACIO_ORE_REPLACEABLES);

    // GLACIO_ICE_SHARD_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_ICE_SHARD_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_ice_shard_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_ICE_SHARD_ORE.get().defaultBlockState(), 10)));
    public static final RegistryObject<PlacedFeature> GLACIO_ICE_SHARD_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_ice_shard_ore", () -> new PlacedFeature(GLACIO_ICE_SHARD_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32)))));

    // GLACIO_COAL_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_COAL_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_coal_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_COAL_ORE.get().defaultBlockState(), 17)));
    public static final RegistryObject<PlacedFeature> GLACIO_COAL_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_coal_ore", () -> new PlacedFeature(GLACIO_COAL_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192)))));

    // GLACIO_COPPER_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_COPPER_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_copper_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_COPPER_ORE.get().defaultBlockState(), 17)));
    public static final RegistryObject<PlacedFeature> GLACIO_COPPER_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_copper_ore", () -> new PlacedFeature(GLACIO_COPPER_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(16)))));

    // GLACIO_IRON_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_IRON_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_iron_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_IRON_ORE.get().defaultBlockState(), 12)));
    public static final RegistryObject<PlacedFeature> GLACIO_IRON_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_iron_ore", () -> new PlacedFeature(GLACIO_IRON_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));

    // GLACIO_LAPIS_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_LAPIS_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_lapis_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_IRON_ORE.get().defaultBlockState(), 9)));
    public static final RegistryObject<PlacedFeature> GLACIO_LAPIS_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_lapis_ore", () -> new PlacedFeature(GLACIO_LAPIS_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)))));

    /** GLACIO DEEPSLATE ORES */

    // GLACIO_DEEPSLATE_COAL_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_DEEPSLATE_COAL_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_deepslate_coal_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_COAL_ORE.defaultBlockState(), 17)));
    public static final RegistryObject<PlacedFeature> GLACIO_DEEPSLATE_COAL_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_deepslate_coal_ore", () -> new PlacedFeature(GLACIO_DEEPSLATE_COAL_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192)))));

    // GLACIO_DEEPSLATE_COPPER_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_DEEPSLATE_COPPER_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_deepslate_copper_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_COPPER_ORE.defaultBlockState(), 17)));
    public static final RegistryObject<PlacedFeature> GLACIO_DEEPSLATE_COPPER_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_deepslate_copper_ore", () -> new PlacedFeature(GLACIO_DEEPSLATE_COPPER_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(112)))));

    // GLACIO_DEEPSLATE_IRON_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_DEEPSLATE_IRON_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_deepslate_iron_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), 12)));
    public static final RegistryObject<PlacedFeature> GLACIO_DEEPSLATE_IRON_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_deepslate_iron_ore", () -> new PlacedFeature(GLACIO_DEEPSLATE_IRON_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(20)))));

    // GLACIO_DEEPSLATE_IRON_ORE
    public static final RegistryObject<ConfiguredFeature<?,?>> GLACIO_DEEPSLATE_LAPIS_ORE_CONFIGURED = FeatureRegistry.CONFIGURED_FEATURES.register("glacio_deepslate_lapis_ore", () -> new ConfiguredFeature(Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), 9)));
    public static final RegistryObject<PlacedFeature> GLACIO_DEEPSLATE_LAPIS_ORE = FeatureRegistry.PLACED_FEATURES.register("glacio_deepslate_lapis_ore", () -> new PlacedFeature(GLACIO_DEEPSLATE_LAPIS_ORE_CONFIGURED.getHolder().get(), commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(10)))));

    @SubscribeEvent
    public static void biomesLoading(BiomeLoadingEvent event) {
        ResourceLocation biome = event.getName();

        if (biome.equals(BiomesRegistry.MOON_DESERT)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_ICE_SHARD_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_IRON_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_DESH_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_CHEESE_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_SOUL_SOIL.getHolder().get());
        }

        if (biome.equals(BiomesRegistry.MARS_DESERT) || biome.equals(BiomesRegistry.MARS_ROCKY_PLAINS) || biome.equals(BiomesRegistry.MARS_ICE_SPIKES)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MARS_ICE_SHARD_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MARS_IRON_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MARS_DIAMOND_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MARS_OSTRUM_ORE.getHolder().get());
        }

        if (biome.equals(BiomesRegistry.MERCURY)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MERCURY_IRON_ORE.getHolder().get());
        }

        if (biome.equals(BiomesRegistry.VENUS_DESERT) || biome.equals(BiomesRegistry.INFERNAL_VENUS_BARRENS)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(VENUS_COAL_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(VENUS_GOLD_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(VENUS_DIAMOND_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(VENUS_CALORITE_ORE.getHolder().get());
        }

        if (biome.equals(BiomesRegistry.GLACIO) || biome.equals(BiomesRegistry.GLACIO_ICE_SPIKES)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_ICE_SHARD_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_COAL_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_IRON_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_COPPER_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_LAPIS_ORE.getHolder().get());

            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_DEEPSLATE_COAL_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_DEEPSLATE_IRON_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_DEEPSLATE_COPPER_ORE.getHolder().get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_DEEPSLATE_LAPIS_ORE.getHolder().get());
        }
    }

    /** ORE PLACEMENTS */
    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
}
