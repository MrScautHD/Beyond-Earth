package net.mrscauthd.beyond_earth.common.registries;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.mrscauthd.beyond_earth.BeyondEarth;

public class LevelRegistry {
    /** MOON */
    public static final ResourceKey<Level> MOON = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "moon"));
    public static final ResourceKey<Level> MOON_ORBIT = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "moon_orbit"));

    /** MARS */
    public static final ResourceKey<Level> MARS = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "mars"));
    public static final ResourceKey<Level> MARS_ORBIT = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "mars_orbit"));

    public static final ResourceKey<Level> PHOBOS = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "phobos"));
    public static final ResourceKey<Level> PHOBOS_ORBIT = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "phobos_orbit"));

    public static final ResourceKey<Level> DEIMOS = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "deimos"));
    public static final ResourceKey<Level> DEIMOS_ORBIT = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "deimos_orbit"));

    /** MERCURY */
    public static final ResourceKey<Level> MERCURY = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "mercury"));
    public static final ResourceKey<Level> MERCURY_ORBIT = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "mercury_orbit"));

    /** VENUS */
    public static final ResourceKey<Level> VENUS = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "venus"));
    public static final ResourceKey<Level> VENUS_ORBIT = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "venus_orbit"));

    /** GLACIO */
    public static final ResourceKey<Level> GLACIO = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "glacio"));
    public static final ResourceKey<Level> GLACIO_ORBIT = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID, "glacio_orbit"));

    /** EARTH */
    public static final ResourceKey<Level> EARTH = Level.OVERWORLD;
    public static final ResourceKey<Level> EARTH_ORBIT = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BeyondEarth.MODID,"earth_orbit"));
}
