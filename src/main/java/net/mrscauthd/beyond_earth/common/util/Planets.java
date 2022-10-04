package net.mrscauthd.beyond_earth.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.events.forge.PlanetRegisterEvent;

public class Planets {
    /**
     * Mapping of level -> planet, where level is for in orbit, use
     * Planets::getLocationForOrbit to access this in most cases. This is public as
     * the keyset is a useful way to tell if you are in orbit.
     */
    public static Map<ResourceKey<Level>, Planet> PLANETS_BY_ORBIT = Maps.newHashMap();
    /**
     * Mapping of level -> planet, where level is for the planet, use
     * Planets::getLocationForPlanet to access this in most cases. This is public as
     * the keyset is a useful way to tell if you are on a planet
     */
    public static Map<ResourceKey<Level>, Planet> PLANETS_BY_PLANET = Maps.newHashMap();
    /**
     * This is a mapping of level -> planet, it contains both the orbits and the
     * planets. This is useful for determining if you are either on a level, or in
     * orbit.
     */
    public static Map<ResourceKey<Level>, Planet> BY_DIMENSION = Maps.newHashMap();
    /**
     * This map is for modifying fall distance for planets. Register values in here
     * with Planets.registerFallModifier
     */
    private static Object2FloatOpenHashMap<ResourceKey<Level>> FALL_MODIFIERS = new Object2FloatOpenHashMap<>();

    public static Int2ObjectOpenHashMap<ResourceKey<Level>> PLANET_ID_MAPS = new Int2ObjectOpenHashMap<>();
    public static Int2ObjectOpenHashMap<ResourceKey<Level>> ORBIT_ID_MAPS = new Int2ObjectOpenHashMap<>();
    public static Int2ObjectOpenHashMap<ResourceKey<Level>> STATION_ID_MAPS = new Int2ObjectOpenHashMap<>();

    public static Set<ResourceKey<Level>> LEVELS_WITHOUT_RAIN = new HashSet<>();

    public static Set<ResourceKey<Level>> LEVELS_WITHOUT_OXYGEN = new HashSet<>();

    public static Set<ResourceKey<Level>> SPACE_LEVELS = new HashSet<>();

    public static Map<String, StarSystem> STARS = Maps.newHashMap();
    public static List<StarSystem> ORDERED_STARS = new ArrayList<>();

    /** PLANET BAR TEXTURES */
    public static final ResourceLocation MOON_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/moon_planet_bar.png");
    public static final ResourceLocation MARS_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/mars_planet_bar.png");
    public static final ResourceLocation MERCURY_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/mercury_planet_bar.png");
    public static final ResourceLocation VENUS_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/venus_planet_bar.png");
    public static final ResourceLocation GLACIO_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/glacio_planet_bar.png");

    public static final ResourceLocation SUN_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/sun.png");
    public static final ResourceLocation MARS_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/mars.png");
    public static final ResourceLocation EARTH_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/earth.png");
    public static final ResourceLocation VENUS_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/venus.png");
    public static final ResourceLocation MERCURY_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/mercury.png");
    public static final ResourceLocation GLACIO_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/glacio.png");

    private static final AtomicInteger IDMAPPINGS = new AtomicInteger();

    public static void clear() {
        FALL_MODIFIERS.clear();
        BY_DIMENSION.clear();
        PLANETS_BY_PLANET.clear();
        PLANETS_BY_ORBIT.clear();

        STARS.clear();
        ORDERED_STARS.clear();
    }

    public static void generateDefaults() {
        clear();
        // Register default planets
        MinecraftForge.EVENT_BUS.post(new PlanetRegisterEvent.Generate());
        initIDs();
    }

    public static void initIDs() {
        IDMAPPINGS.set(0);
        PLANET_ID_MAPS.clear();
        ORBIT_ID_MAPS.clear();
        STATION_ID_MAPS.clear();

        LEVELS_WITHOUT_RAIN.clear();
        LEVELS_WITHOUT_OXYGEN.clear();
        SPACE_LEVELS.clear();

        ORDERED_STARS.forEach(star -> {
            star.planets.forEach(p -> p.initIDs(IDMAPPINGS));
        });
    }

    static {
        generateDefaults();
    }

    /**
     * 
     * @param level - level to test for orbit
     * @return PlanetLocation for this level, assuming the level is orbit
     */
    @Nullable
    public static Planet getLocationForOrbit(Level level) {
        if (!PLANETS_BY_ORBIT.containsKey(level.dimension()))
            return null;
        return PLANETS_BY_ORBIT.get(level.dimension());
    }

    /**
     * 
     * @param level - level to test for a planet
     * @return PlanetLocation for this level, assuming the level is the planet
     */
    @Nullable
    public static Planet getLocationForPlanet(Level level) {
        if (!PLANETS_BY_PLANET.containsKey(level.dimension()))
            return null;
        return PLANETS_BY_PLANET.get(level.dimension());
    }

    /**
     * 
     * @param level
     * @return amount to modify fall distance for this level
     */
    public static float getFallModifier(Level level) {
        // Return the registered value first, if that is not present, return 8.5
        // if orbit, otherwise 0
        return FALL_MODIFIERS.getOrDefault(level.dimension(), Methods.isOrbitLevel(level) ? 8.5f : 0);
    }

    /**
     * 
     * @param level
     * @return modified item gravity, -1 if no modification!
     */
    public static float getItemGravityForLocation(Level level) {
        var key = level.dimension();
        Planet planet = BY_DIMENSION.get(key);
        if (planet == null)
            return -1;
        return key == planet.orbit ? planet.orbitItemGravity : planet.planetItemGravity;
    }

    /**
     * 
     * @param level
     * @return modified entity gravity, -1 if no modification!
     */
    public static float getEntityGravityForLocation(Level level) {
        var key = level.dimension();
        Planet planet = BY_DIMENSION.get(key);
        if (planet == null)
            return -1;
        return key == planet.orbit ? planet.orbitEntityGravity : planet.planetEntityGravity;
    }

    /**
     * @param planet
     * @param moon
     * @return whether the planet was able to have the moon registered
     */
    public static boolean registerMoon(ResourceKey<Level> planet, ResourceKey<Level> moon) {
        Planet p = PLANETS_BY_PLANET.get(planet);
        if (p == null)
            return false;
        Planet m = PLANETS_BY_PLANET.get(moon);
        if (m == null)
            return false;
        if (p.moons.contains(m))
            return false;
        p.moons.add(m);
        return true;
    }

    /**
     * Registers a fall modifier for this location, see Planet.getFallModifier
     * 
     * @param level
     * @param value
     */
    public static void registerFallModifier(ResourceKey<Level> level, float value) {
        FALL_MODIFIERS.put(level, value);
    }

    public static void registerPlanet(ResourceKey<Level> location, ResourceKey<Level> orbit) {
        registerPlanet(location, orbit, -1, -1, -1, -1);
    }

    public static void registerPlanet(ResourceKey<Level> location, ResourceKey<Level> orbit, float planetItemGravity,
            float planetEntityGravity) {
        registerPlanet(location, orbit, planetItemGravity, planetEntityGravity, -1, -1);
    }

    public static void registerPlanet(ResourceKey<Level> location, ResourceKey<Level> orbit, float planetItemGravity,
            float planetEntityGravity, float orbitItemGravity, float orbitEntityGravity) {

        if (orbitEntityGravity == -1)
            orbitEntityGravity = 0.01f;
        if (orbitItemGravity == -1)
            orbitItemGravity = 0.05f;

        Planet planet = new Planet(location, orbit, planetItemGravity, planetEntityGravity, orbitItemGravity,
                orbitEntityGravity);
        planet.register();
    }

    public static void registerPlanetBar(ResourceKey<Level> planet, ResourceLocation planetBar) {
        Planet p = BY_DIMENSION.get(planet);
        if (p != null)
            p.planetBar = planetBar;
    }

    public static ResourceLocation getPlanetBar(Level level) {
        Planet p = BY_DIMENSION.get(level.dimension());
        if (p == null)
            return Planet.DEFAULT_PLANET_BAR;
        return p.getPlanetBar(Methods.isOrbitLevel(level));
    }

    /**
     * This stores the planet's location, as well as traits such as gravity amount
     * for orbit, planet gravity, etc.
     * 
     * @author Thutmose
     *
     */
    public static class Planet {

        private static final ResourceLocation ORBIT_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
                "textures/planet_bar/orbit_planet_bar.png");
        private static final ResourceLocation DEFAULT_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
                "textures/planet_bar/earth_planet_bar.png");

        public String name;

        public ResourceKey<Level> planet;
        public ResourceKey<Level> orbit;

        public float planetItemGravity;
        public float planetEntityGravity;
        public float orbitItemGravity;
        public float orbitEntityGravity;
        public float orbitRadius = 1;
        public float mass = 1;
        public float g = 1;
        public float temperature = 14;

        public int planetID;
        public int orbitID;
        public int stationID;

        public boolean hasOxygen = false;
        public boolean hasRain = true;
        public boolean spaceLevel = true;

        public List<Planet> moons = new ArrayList<>();

        // Gui related things
        public ResourceLocation planetBar;
        public ResourceLocation orbitBar;
        public ResourceLocation texture;
        public Component description;
        public float rotation = 0;
        public int[] orbitColour = { 255, 255, 255 };
        // Planet button tier
        public int tier = 0;
        public int button_category = -1;
        public String[] extra_text;

        public Planet(ResourceKey<Level> planet, ResourceKey<Level> orbit, float planetItemGravity,
                float planetEntityGravity, float orbitItemGravity, float orbitEntityGravity) {
            this.planet = planet;
            this.orbit = orbit;
            this.planetItemGravity = planetItemGravity;
            this.planetEntityGravity = planetEntityGravity;
            this.orbitItemGravity = orbitItemGravity;
            this.orbitEntityGravity = orbitEntityGravity;
            this.name = planet.location().getPath();
            if (planet == Level.OVERWORLD)
                this.name = "earth";
        }

        private void initIDs(AtomicInteger global) {
            this.planetID = global.getAndIncrement();
            this.orbitID = global.getAndIncrement();
            this.stationID = global.getAndIncrement();

            PLANET_ID_MAPS.put(planetID, planet);
            ORBIT_ID_MAPS.put(orbitID, orbit);
            STATION_ID_MAPS.put(stationID, orbit);

            if (!this.hasOxygen)
                LEVELS_WITHOUT_OXYGEN.add(planet);
            LEVELS_WITHOUT_OXYGEN.add(orbit);

            if (!this.hasRain)
                LEVELS_WITHOUT_RAIN.add(planet);
            LEVELS_WITHOUT_RAIN.add(orbit);

            if (this.spaceLevel)
                SPACE_LEVELS.add(planet);
            SPACE_LEVELS.add(orbit);

            this.moons.forEach(p -> p.initIDs(global));
        }

        public ResourceLocation getPlanetBar(boolean orbit) {
            if (orbit)
                return orbitBar != null ? orbitBar : ORBIT_PLANET_BAR;
            return planetBar != null ? planetBar : DEFAULT_PLANET_BAR;
        }

        public void register() {
            PLANETS_BY_ORBIT.put(orbit, this);
            PLANETS_BY_PLANET.put(planet, this);
            BY_DIMENSION.put(orbit, this);
            BY_DIMENSION.put(planet, this);

            // This is done here for when registered via data. When manually registered, we
            // don't have moons yet, so this is just empty.
            moons.forEach(m -> m.register());
        }
    };

    public static class StarSystem {
        public List<Planet> planets = new ArrayList<>();
        public String name;
        public ResourceLocation texture;
        public float mass = 1;
        public float g = 1;
        public float[] location = new float[3];
        public int[] colour = { 255, 255, 255 };

        public Component description;

        public void register() {
            STARS.put(name, this);
            ORDERED_STARS.add(this);
        }
    }
}
