package net.mrscauthd.beyond_earth.world.oregen;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.registries.BiomesRegistry;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID)
public class OreGeneration {

    /** MOON ORES: */
    public static final TagKey<Block> MOON_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "moon_ore_replaceables"));
    public static final RuleTest MOON_MATCH = new TagMatchTest(MOON_ORE_REPLACEABLES);

    public static final Supplier<Holder<PlacedFeature>> MOON_CHEESE_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":moon_cheese_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":moon_cheese_ore", Feature.ORE, new OreConfiguration(MOON_MATCH, ModInit.MOON_CHEESE_ORE.get().defaultBlockState(), 10)), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192))));
    public static final Supplier<Holder<PlacedFeature>> MOON_SOUL_SOIL = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":moon_soul_soil", FeatureUtils.register(BeyondEarthMod.MODID + ":moon_soul_soil", Feature.ORE, new OreConfiguration(MOON_MATCH, Blocks.SOUL_SOIL.defaultBlockState(), 60)), commonOrePlacement(22, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(100))));
    public static final Supplier<Holder<PlacedFeature>> MOON_ICE_SHARD_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":moon_ice_shard_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":moon_ice_shard_ore", Feature.ORE, new OreConfiguration(MOON_MATCH, ModInit.MOON_ICE_SHARD_ORE.get().defaultBlockState(), 10)), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
    public static final Supplier<Holder<PlacedFeature>> MOON_IRON_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":moon_iron_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":moon_iron_ore", Feature.ORE, new OreConfiguration(MOON_MATCH, ModInit.MOON_IRON_ORE.get().defaultBlockState(), 11)), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
    public static final Supplier<Holder<PlacedFeature>> MOON_DESH_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":moon_desh_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":moon_desh_ore", Feature.ORE, new OreConfiguration(MOON_MATCH, ModInit.MOON_DESH_ORE.get().defaultBlockState(), 9)), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));

    /** MARS ORES: */
    public static final TagKey<Block> MARS_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "mars_ore_replaceables"));
    public static final RuleTest MARS_MATCH = new TagMatchTest(MARS_ORE_REPLACEABLES);

    public static final Supplier<Holder<PlacedFeature>> MARS_ICE_SHARD_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":mars_ice_shard_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":mars_ice_shard_ore", Feature.ORE, new OreConfiguration(MARS_MATCH, ModInit.MARS_ICE_SHARD_ORE.get().defaultBlockState(), 10)), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))));
    public static final Supplier<Holder<PlacedFeature>> MARS_IRON_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":mars_iron_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":mars_iron_ore", Feature.ORE, new OreConfiguration(MARS_MATCH, ModInit.MARS_IRON_ORE.get().defaultBlockState(), 11)), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
    public static final Supplier<Holder<PlacedFeature>> MARS_DIAMOND_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":mars_diamond_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":mars_diamond_ore", Feature.ORE, new OreConfiguration(MARS_MATCH, ModInit.MARS_DIAMOND_ORE.get().defaultBlockState(), 7)), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
    public static final Supplier<Holder<PlacedFeature>> MARS_OSTRUM_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":mars_ostrum_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":mars_ostrum_ore", Feature.ORE, new OreConfiguration(MARS_MATCH, ModInit.MARS_OSTRUM_ORE.get().defaultBlockState(), 8)), commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    /** MERCURY ORES: */
    public static final TagKey<Block> MERCURY_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "mercury_ore_replaceables"));
    public static final RuleTest MERCURY_MATCH = new TagMatchTest(MERCURY_ORE_REPLACEABLES);

    public static final Supplier<Holder<PlacedFeature>> MERCURY_IRON_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":mercury_iron_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":mercury_iron_ore", Feature.ORE, new OreConfiguration(MERCURY_MATCH, ModInit.MERCURY_IRON_ORE.get().defaultBlockState(), 8)), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192))));

    /** VENUS ORES: */
    public static final TagKey<Block> VENUS_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "venus_ore_replaceables"));
    public static final RuleTest VENUS_MATCH = new TagMatchTest(VENUS_ORE_REPLACEABLES);

    public static final Supplier<Holder<PlacedFeature>> VENUS_COAL_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":venus_coal_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":venus_coal_ore", Feature.ORE, new OreConfiguration(VENUS_MATCH, ModInit.VENUS_COAL_ORE.get().defaultBlockState(), 17)), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192))));
    public static final Supplier<Holder<PlacedFeature>> VENUS_GOLD_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":venus_gold_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":venus_gold_ore", Feature.ORE, new OreConfiguration(VENUS_MATCH, ModInit.VENUS_GOLD_ORE.get().defaultBlockState(), 10)), commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));
    public static final Supplier<Holder<PlacedFeature>> VENUS_DIAMOND_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":venus_diamond_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":venus_diamond_ore", Feature.ORE, new OreConfiguration(VENUS_MATCH, ModInit.VENUS_DIAMOND_ORE.get().defaultBlockState(), 9)), commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
    public static final Supplier<Holder<PlacedFeature>> VENUS_CALORITE_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":venus_calorite_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":venus_calorite_ore", Feature.ORE, new OreConfiguration(VENUS_MATCH, ModInit.VENUS_CALORITE_ORE.get().defaultBlockState(), 8)), commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    /** GLACIO ORES: */
    public static final TagKey<Block> GLACIO_ORE_REPLACEABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BeyondEarthMod.MODID, "glacio_ore_replaceables"));
    public static final RuleTest GLACIO_MATCH = new TagMatchTest(GLACIO_ORE_REPLACEABLES);

    public static final Supplier<Holder<PlacedFeature>> GLACIO_ICE_SHARD_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":glacio_ice_shard_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":glacio_ice_shard_ore", Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_ICE_SHARD_ORE.get().defaultBlockState(), 10)), commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))));
    public static final Supplier<Holder<PlacedFeature>> GLACIO_COAL_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":glacio_coal_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":glacio_coal_ore", Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_COAL_ORE.get().defaultBlockState(), 17)), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192))));
    public static final Supplier<Holder<PlacedFeature>> GLACIO_COPPER_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":glacio_copper_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":glacio_copper_ore", Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_COPPER_ORE.get().defaultBlockState(), 17)), commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
    public static final Supplier<Holder<PlacedFeature>> GLACIO_IRON_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":glacio_iron_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":glacio_iron_ore", Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_IRON_ORE.get().defaultBlockState(), 12)), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
    public static final Supplier<Holder<PlacedFeature>> GLACIO_LAPIS_ORE  = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":glacio_lapis_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":glacio_lapis_ore", Feature.ORE, new OreConfiguration(GLACIO_MATCH, ModInit.GLACIO_LAPIS_ORE.get().defaultBlockState(), 9)), commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));

    public static final Supplier<Holder<PlacedFeature>> GLACIO_DEEPSLATE_COAL_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":deepslate_coal_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":deepslate_coal_ore", Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_COAL_ORE.defaultBlockState(), 17)), commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(192))));
    public static final Supplier<Holder<PlacedFeature>> GLACIO_DEEPSLATE_COPPER_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":deepslate_copper_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":deepslate_copper_ore", Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_COPPER_ORE.defaultBlockState(), 17)), commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(112))));
    public static final Supplier<Holder<PlacedFeature>> GLACIO_DEEPSLATE_IRON_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":deepslate_iron_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":deepslate_iron_ore", Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), 12)), commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(20))));
    public static final Supplier<Holder<PlacedFeature>> GLACIO_DEEPSLATE_LAPIS_ORE = () -> PlacementUtils.register(BeyondEarthMod.MODID + ":deepslate_lapis_ore", FeatureUtils.register(BeyondEarthMod.MODID + ":deepslate_lapis_ore", Feature.ORE, new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_LAPIS_ORE.defaultBlockState(), 9)), commonOrePlacement(2, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(10))));

    @SubscribeEvent
    public static void biomesLoading(BiomeLoadingEvent event) {
        ResourceLocation biome = event.getName();

        if (biome.equals(BiomesRegistry.MOON_DESERT)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_ICE_SHARD_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_IRON_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_DESH_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_CHEESE_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MOON_SOUL_SOIL.get());
        }

        if (biome.equals(BiomesRegistry.MARS_DESERT) || biome.equals(BiomesRegistry.MARS_ROCKY_PLAINS) || biome.equals(BiomesRegistry.MARS_ICE_SPIKES)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MARS_ICE_SHARD_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MARS_IRON_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MARS_DIAMOND_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MARS_OSTRUM_ORE.get());
        }

        if (biome.equals(BiomesRegistry.MERCURY)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(MERCURY_IRON_ORE.get());
        }

        if (biome.equals(BiomesRegistry.VENUS_DESERT) || biome.equals(BiomesRegistry.INFERNAL_VENUS_BARRENS)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(VENUS_COAL_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(VENUS_GOLD_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(VENUS_DIAMOND_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(VENUS_CALORITE_ORE.get());
        }

        if (biome.equals(BiomesRegistry.GLACIO) || biome.equals(BiomesRegistry.GLACIO_ICE_SPIKES)) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_ICE_SHARD_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_COAL_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_IRON_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_COPPER_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_LAPIS_ORE.get());

            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_DEEPSLATE_COAL_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_DEEPSLATE_IRON_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_DEEPSLATE_COPPER_ORE.get());
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(GLACIO_DEEPSLATE_LAPIS_ORE.get());
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
