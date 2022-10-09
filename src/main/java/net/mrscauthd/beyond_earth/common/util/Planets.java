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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.events.forge.PlanetRegisterEvent;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;

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

    public static Int2ObjectOpenHashMap<ResourceKey<Level>> PLANET_ID_MAPS = new Int2ObjectOpenHashMap<>();
    public static Int2ObjectOpenHashMap<ResourceKey<Level>> ORBIT_ID_MAPS = new Int2ObjectOpenHashMap<>();
    public static Int2ObjectOpenHashMap<ResourceKey<Level>> STATION_ID_MAPS = new Int2ObjectOpenHashMap<>();

    public static Set<ResourceKey<Level>> LEVELS_WITHOUT_RAIN = new HashSet<>();

    public static Set<ResourceKey<Level>> LEVELS_WITHOUT_OXYGEN = new HashSet<>();

    public static Set<ResourceKey<Level>> SPACE_LEVELS = new HashSet<>();

    public static Map<String, StarSystem> STARS = Maps.newHashMap();
    public static List<StarSystem> ORDERED_STARS = new ArrayList<>();

    /** PLANET BAR TEXTURES */
    private static final ResourceLocation MOON_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/moon_planet_bar.png");
    private static final ResourceLocation MARS_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/mars_planet_bar.png");
    private static final ResourceLocation MERCURY_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/mercury_planet_bar.png");
    private static final ResourceLocation VENUS_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/venus_planet_bar.png");
    private static final ResourceLocation GLACIO_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
            "textures/planet_bar/glacio_planet_bar.png");

    private static final ResourceLocation SUN_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/sun.png");
    private static final ResourceLocation MARS_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/mars.png");
//    private static final ResourceLocation PHOBOS_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
//            "textures/environment/planet/phobos.png");
//    private static final ResourceLocation DEIMOS_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
//            "textures/environment/planet/deimos.png");
    private static final ResourceLocation EARTH_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/earth.png");
    private static final ResourceLocation MOON_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/moon.png");
    private static final ResourceLocation VENUS_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/venus.png");
    private static final ResourceLocation MERCURY_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/mercury.png");
    private static final ResourceLocation GLACIO_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/glacio.png");

    private static final AtomicInteger IDMAPPINGS = new AtomicInteger();

    /**
     * Here we register default planets. We are set to HIGHEST so that we fire
     * first, and then addons can adjust things.
     * 
     * @param event
     */
    private static void registerDefaultPlanets() {
        Planets.registerPlanet(Level.OVERWORLD, LevelRegistry.EARTH_ORBIT);
        Planets.registerPlanet(LevelRegistry.MOON, LevelRegistry.MOON_ORBIT);
        Planets.registerPlanet(LevelRegistry.MARS, LevelRegistry.MARS_ORBIT);
//        Planets.registerPlanet(LevelRegistry.PHOBOS, LevelRegistry.PHOBOS_ORBIT);
//        Planets.registerPlanet(LevelRegistry.DEIMOS, LevelRegistry.DEIMOS_ORBIT);
        Planets.registerPlanet(LevelRegistry.MERCURY, LevelRegistry.MERCURY_ORBIT);
        Planets.registerPlanet(LevelRegistry.VENUS, LevelRegistry.VENUS_ORBIT);
        Planets.registerPlanet(LevelRegistry.GLACIO, LevelRegistry.GLACIO_ORBIT);

        Planets.registerPlanetBar(LevelRegistry.MOON, Planets.MOON_PLANET_BAR);
//        Planets.registerPlanetBar(LevelRegistry.PHOBOS, Planets.MOON_PLANET_BAR);
//        Planets.registerPlanetBar(LevelRegistry.DEIMOS, Planets.MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MARS, Planets.MARS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MERCURY, Planets.MERCURY_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.VENUS, Planets.VENUS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.GLACIO, Planets.GLACIO_PLANET_BAR);

        StarSystem sol = new StarSystem();
        sol.name = "sun";
        sol.texture = Planets.SUN_TEXTURE;
        Planet mercury = Planets.BY_DIMENSION.get(LevelRegistry.MERCURY);
        mercury.orbitRadius = 0.39f;
        mercury.mass = 0.055f;
        mercury.texture = Planets.MERCURY_TEXTURE;
        mercury.rotation = 270;
        mercury.tier = 3;
        mercury.g = 0.38f;
        mercury.temperature = 430;
        mercury.orbitColour = new int[] { 179, 49, 44 };
        Planet venus = Planets.BY_DIMENSION.get(LevelRegistry.VENUS);
        venus.orbitRadius = 0.72f;
        venus.mass = 0.81f;
        venus.texture = Planets.VENUS_TEXTURE;
        venus.rotation = 180;
        venus.tier = 3;
        venus.g = 0.904f;
        venus.temperature = 482;
        venus.orbitColour = new int[] { 235, 136, 68 };
        Planet earth = Planets.BY_DIMENSION.get(LevelRegistry.EARTH);
        earth.texture = Planets.EARTH_TEXTURE;
        earth.rotation = 90;
        earth.tier = 1;
        earth.hasOxygen = true;
        earth.spaceLevel = false;
        earth.hasRain = true;
        earth.orbitColour = new int[] { 53, 163, 79 };
        Planet mars = Planets.BY_DIMENSION.get(LevelRegistry.MARS);
        mars.orbitRadius = 1.52f;
        mars.mass = 0.107f;
        mars.texture = Planets.MARS_TEXTURE;
        mars.tier = 2;
        mars.g = 0.3794f;
        mars.temperature = -63;
        mars.hasRain = true;
        mars.orbitColour = new int[] { 37, 49, 146 };

//        Planet phobos = Planets.BY_DIMENSION.get(LevelRegistry.PHOBOS);
//        phobos.g = 0.04f;
//        phobos.rotation = 180;
//        phobos.temperature = -160;
//        phobos.texture = Planets.PHOBOS_TEXTURE;
//        phobos.orbitRadius = 0.5f;
//        phobos.orbitColour = new int[] { 37, 49, 146 };
//        mars.moons.add(phobos);
//
//        Planet deimos = Planets.BY_DIMENSION.get(LevelRegistry.DEIMOS);
//        deimos.g = 0.04f;
//        deimos.temperature = -160;
//        deimos.texture = Planets.DEIMOS_TEXTURE;
//        deimos.orbitRadius = 1.5f;
//        deimos.orbitColour = new int[] { 37, 49, 146 };
//        mars.moons.add(deimos);

        Planet moon = Planets.BY_DIMENSION.get(LevelRegistry.MOON);
        moon.g = 0.1654f;
        moon.temperature = -160;
        moon.texture = Planets.MOON_TEXTURE;
        moon.orbitColour = new int[] { 255, 49, 30 };
        earth.moons.add(moon);
        sol.planets.add(mercury);
        sol.planets.add(venus);
        sol.planets.add(earth);
        sol.planets.add(mars);
        sol.register();

        StarSystem proxima_centauri = new StarSystem();
        proxima_centauri.name = "proxima_centauri";
        proxima_centauri.location[0] = 4.25f;
        proxima_centauri.mass = 0.122f;
        Planet glacio = Planets.BY_DIMENSION.get(LevelRegistry.GLACIO);
        glacio.texture = Planets.GLACIO_TEXTURE;
        glacio.mass = 0.08f;
        glacio.orbitRadius = 0.39f;
        glacio.rotation = 180;
        glacio.tier = 4;
        glacio.g = 0.3794f;
        glacio.temperature = -20;
        glacio.hasRain = true;
        glacio.orbitColour = new int[] { 37, 49, 146 };
        proxima_centauri.planets.add(glacio);
        proxima_centauri.register();
    }

    public static void clear() {
        BY_DIMENSION.clear();
        PLANETS_BY_PLANET.clear();
        PLANETS_BY_ORBIT.clear();

        STARS.clear();
        ORDERED_STARS.clear();
    }

    public static void generateDefaults() {
        clear();
        // Register default planets
        registerDefaultPlanets();
        // Post the event for others to listen for
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
     * @return modified item gravity, -1 if no modification!
     */
    public static float getItemGravityForLocation(Level level) {
        var key = level.dimension();
        Planet planet = BY_DIMENSION.get(key);
        if (planet == null)
            return -1;
        float scale = key == planet.orbit ? planet.orbitG : planet.g;
        scale = Math.max(scale, 0.04f);
        float base = (float) ItemGravity.DEFAULT_ITEM_GRAVITY;
        return scale * base;
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
        float scale = key == planet.orbit ? planet.orbitG : planet.g;
        scale = Math.max(scale, 0.04f);
        float base = (float) ForgeMod.ENTITY_GRAVITY.get().getDefaultValue();
        return scale * base;
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

    public static void registerPlanet(ResourceKey<Level> location, ResourceKey<Level> orbit) {
        Planet planet = new Planet(location, orbit);
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

    public static List<StarSystem> getStarsList() {
        if (ORDERED_STARS.isEmpty())
            generateDefaults();
        return ORDERED_STARS;
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

        public float orbitRadius = 1;
        public float mass = 1;
        public float g = 1;
        public float orbitG = 0.05f;
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

        public float _distance = 0;
        public float _xPos = 0;
        public float _yPos = 0;

        // Planet button tier
        public int tier = 0;
        public int button_category = -1;
        public String[] extra_text;

        public Planet(ResourceKey<Level> planet, ResourceKey<Level> orbit) {
            this.planet = planet;
            this.orbit = orbit;
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
