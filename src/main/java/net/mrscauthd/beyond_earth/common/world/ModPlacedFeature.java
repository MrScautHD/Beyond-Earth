package net.mrscauthd.beyond_earth.common.world;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;

import static net.mrscauthd.beyond_earth.common.world.ModConfiguredFeature.*;

public class ModPlacedFeature {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURE =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, BeyondEarth.MODID);

    // GLACIO
    public static final RegistryObject<PlacedFeature> GLACIO_COAL_ORE_PLACED = PLACED_FEATURE.register("glacio_coal_ore", () -> new PlacedFeature(GLACIO_COAL_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(20,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(192)))));
    public static final RegistryObject<PlacedFeature> GLACIO_COPPER_ORE_PLACED = PLACED_FEATURE.register("glacio_copper_ore", () -> new PlacedFeature(GLACIO_COPPER_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(16,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-16), VerticalAnchor.aboveBottom(112)))));
    public static final RegistryObject<PlacedFeature> GLACIO_ICE_SHARD_PLACED = PLACED_FEATURE.register("glacio_ice_shard_ore", () -> new PlacedFeature(GLACIO_ICE_SHARD_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(8,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32)))));
    public static final RegistryObject<PlacedFeature> GLACIO_IRON_ORE_PLACED = PLACED_FEATURE.register("glacio_iron_ore", () -> new PlacedFeature(GLACIO_IRON_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(10,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-24), VerticalAnchor.aboveBottom(56)))));
    public static final RegistryObject<PlacedFeature> GLACIO_LAPIS_ORE_PLACED = PLACED_FEATURE.register("glacio_lapis_ore", () -> new PlacedFeature(GLACIO_LAPIS_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(2,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32)))));

    // MARS
    public static final RegistryObject<PlacedFeature> MARS_DIAMOND_ORE_PLACED = PLACED_FEATURE.register("mars_diamond_ore", () -> new PlacedFeature(MARS_DIAMOND_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(7,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> MARS_ICE_SHARD_PLACED = PLACED_FEATURE.register("mars_ice_shard_ore", () -> new PlacedFeature(MARS_ICE_SHARD_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(8,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32)))));
    public static final RegistryObject<PlacedFeature> MARS_IRON_ORE_PLACED = PLACED_FEATURE.register("mars_iron_ore", () -> new PlacedFeature(MARS_IRON_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(8,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-24), VerticalAnchor.aboveBottom(56)))));
    public static final RegistryObject<PlacedFeature> MARS_OSTRUM_ORE_PLACED = PLACED_FEATURE.register("mars_ostrum_ore", () -> new PlacedFeature(MARS_OSTRUM_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(6,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    // MERCURY
    public static final RegistryObject<PlacedFeature> MERCURY_IRON_ORE_PLACED = PLACED_FEATURE.register("mercury_iron_ore", () -> new PlacedFeature(MERCURY_IRON_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(20,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(192)))));

    // MOON
    public static final RegistryObject<PlacedFeature> MOON_CHEESE_ORE_PLACED = PLACED_FEATURE.register("moon_cheese_ore", () -> new PlacedFeature(MOON_CHEESE_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(20,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(192)))));
    public static final RegistryObject<PlacedFeature> MOON_DESH_ORE_PLACED = PLACED_FEATURE.register("moon_desh_ore", () -> new PlacedFeature(MOON_DESH_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(7,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> MOON_ICE_SHARD_PLACED = PLACED_FEATURE.register("moon_ice_shard_ore", () -> new PlacedFeature(MOON_ICE_SHARD_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(8,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32)))));
    public static final RegistryObject<PlacedFeature> MOON_IRON_ORE_PLACED = PLACED_FEATURE.register("moon_iron_ore", () -> new PlacedFeature(MOON_IRON_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(10,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-24), VerticalAnchor.aboveBottom(56)))));
    public static final RegistryObject<PlacedFeature> MOON_SOUL_SOIL = PLACED_FEATURE.register("moon_soul_soil", () -> new PlacedFeature(MOON_SOUL_SOIL_CONFIGURED.getHolder().get()
            , OrePlacement.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(100)))));

    // VENUS
    public static final RegistryObject<PlacedFeature> VENUS_CALORITE_ORE_PLACED = PLACED_FEATURE.register("venus_calorite_ore", () -> new PlacedFeature(VENUS_CALORITE_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(6,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> VENUS_COAL_ORE_PLACED = PLACED_FEATURE.register("venus_coal_ore", () -> new PlacedFeature(VENUS_COAL_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(20,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(192)))));
    public static final RegistryObject<PlacedFeature> VENUS_DIAMOND_ORE_PLACED = PLACED_FEATURE.register("venus_diamond_ore", () -> new PlacedFeature(VENUS_DIAMOND_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(7,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> VENUS_GOLD_ORE_PLACED = PLACED_FEATURE.register("venus_gold_ore", () -> new PlacedFeature(VENUS_GOLD_ORE.getHolder().get(),
            OrePlacement.commonOrePlacement(4,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(32)))));
}
