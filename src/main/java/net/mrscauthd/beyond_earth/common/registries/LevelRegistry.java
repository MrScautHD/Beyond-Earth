package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.mrscauthd.beyond_earth.BeyondEarth;

import java.util.Arrays;
import java.util.List;

public class LevelRegistry {
    /** MOON */
    public static final ResourceKey<Level> MOON = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "moon"));
    public static final ResourceKey<Level> MOON_ORBIT = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "moon_orbit"));

    /** MARS */
    public static final ResourceKey<Level> MARS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "mars"));
    public static final ResourceKey<Level> MARS_ORBIT = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "mars_orbit"));

    /** MERCURY */
    public static final ResourceKey<Level> MERCURY = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "mercury"));
    public static final ResourceKey<Level> MERCURY_ORBIT = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "mercury_orbit"));

    /** VENUS */
    public static final ResourceKey<Level> VENUS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "venus"));
    public static final ResourceKey<Level> VENUS_ORBIT = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "venus_orbit"));

    /** GLACIO */
    public static final ResourceKey<Level> GLACIO = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "glacio"));
    public static final ResourceKey<Level> GLACIO_ORBIT = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "glacio_orbit"));

    /** EARTH */
    public static final ResourceKey<Level> EARTH_ORBIT = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondEarth.MODID,"earth_orbit"));

    public static List<ResourceKey<Level>> LEVELS_WITHOUT_RAIN = Arrays.asList(
            MOON,
            MOON_ORBIT,
            MARS_ORBIT,
            MERCURY,
            MERCURY_ORBIT,
            VENUS_ORBIT,
            GLACIO_ORBIT,
            EARTH_ORBIT
    );

    public static List<ResourceKey<Level>> LEVELS_WITHOUT_OXYGEN = Arrays.asList(
            MOON,
            MOON_ORBIT,
            MARS,
            MARS_ORBIT,
            MERCURY,
            MERCURY_ORBIT,
            VENUS,
            VENUS_ORBIT,
            GLACIO_ORBIT,
            EARTH_ORBIT
    );

    public static List<ResourceKey<Level>> SPACE_LEVELS = Arrays.asList(
            MOON,
            MOON_ORBIT,
            MARS,
            MARS_ORBIT,
            MERCURY,
            MERCURY_ORBIT,
            VENUS,
            VENUS_ORBIT,
            GLACIO,
            GLACIO_ORBIT,
            EARTH_ORBIT
    );
}
