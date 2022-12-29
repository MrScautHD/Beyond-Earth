package net.mrscauthd.beyond_earth.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
    private static final ResourceLocation PHOBOS_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/phobos.png");
    private static final ResourceLocation DEIMOS_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/deimos.png");
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

    public static final float MOON_ORBIT_SCALE = 384399;
    public static final float PLANET_ORBIT_SCALE = 149598023;

    private static final double MOON_MASS_SCALE = 7.34e22;
    private static final double PLANET_MASS_SCALE = 5.9e24;
    private static final double STAR_MASS_SCALE = 2e30;

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
        Planets.registerPlanet(LevelRegistry.PHOBOS, LevelRegistry.PHOBOS_ORBIT);
        Planets.registerPlanet(LevelRegistry.DEIMOS, LevelRegistry.DEIMOS_ORBIT);
        Planets.registerPlanet(LevelRegistry.MERCURY, LevelRegistry.MERCURY_ORBIT);
        Planets.registerPlanet(LevelRegistry.VENUS, LevelRegistry.VENUS_ORBIT);
        Planets.registerPlanet(LevelRegistry.GLACIO, LevelRegistry.GLACIO_ORBIT);

        Planets.registerPlanetBar(LevelRegistry.MOON, Planets.MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.PHOBOS, Planets.MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.DEIMOS, Planets.MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MARS, Planets.MARS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MERCURY, Planets.MERCURY_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.VENUS, Planets.VENUS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.GLACIO, Planets.GLACIO_PLANET_BAR);

        StarSystem sol = new StarSystem();
        sol.name = "sun";
        sol.texture = Planets.SUN_TEXTURE;
        Planet mercury = Planets.BY_DIMENSION.get(LevelRegistry.MERCURY);
        mercury.orbitRadius = 0.39f * PLANET_ORBIT_SCALE;
        mercury.mass = 0.055f * PLANET_MASS_SCALE;
        mercury.texture = Planets.MERCURY_TEXTURE;
        mercury.rotation = 270;
        mercury.tier = 3;
        mercury.g = 0.38f;
        mercury.radius = 2439.7;
        mercury.temperature = 430;
        mercury.orbitColour = new int[] { 179, 49, 44 };
        Planet venus = Planets.BY_DIMENSION.get(LevelRegistry.VENUS);
        venus.orbitRadius = 0.72f * PLANET_ORBIT_SCALE;
        venus.mass = 0.81f * PLANET_MASS_SCALE;
        venus.texture = Planets.VENUS_TEXTURE;
        venus.rotation = 180;
        venus.tier = 3;
        venus.g = 0.904f;
        venus.radius = 6051.8;
        venus.temperature = 482;
        venus.airDensity = 100;
        venus.orbitColour = new int[] { 235, 136, 68 };
        Planet earth = Planets.BY_DIMENSION.get(LevelRegistry.EARTH);
        earth.texture = Planets.EARTH_TEXTURE;
        earth.rotation = 90;
        earth.tier = 1;
        earth.radius = 6371.0;
        earth.airDensity = 1;
        earth.hasOxygen = true;
        earth.spaceLevel = false;
        earth.hasRain = true;
        earth.orbitColour = new int[] { 53, 163, 79 };
        Planet mars = Planets.BY_DIMENSION.get(LevelRegistry.MARS);
        mars.orbitRadius = 1.52f * PLANET_ORBIT_SCALE;
        mars.mass = 0.107f * PLANET_MASS_SCALE;
        mars.texture = Planets.MARS_TEXTURE;
        mars.tier = 2;
        mars.radius = 3389.5;
        mars.g = 0.3794f;
        mars.temperature = -63;
        mars.airDensity = 0.001f;
        mars.hasRain = true;
        mars.hasDustStorms = true;
        mars.orbitColour = new int[] { 37, 49, 146 };
        mars.sunriseColour = new float[] { 0, 0.55f, 0.8f };

        Planet phobos = Planets.BY_DIMENSION.get(LevelRegistry.PHOBOS);
        phobos.g = 0.04f;
        phobos.radius = 11.2;
        phobos.mass = 10.6e15;
        phobos.rotation = 180;
        phobos.temperature = -160;
        phobos.texture = Planets.PHOBOS_TEXTURE;
        phobos.orbitRadius = 0.0244f * MOON_ORBIT_SCALE;
        phobos.orbitColour = mars.orbitColour;
        phobos.tidalLock = true;
        mars.addChild(phobos);

        Planet deimos = Planets.BY_DIMENSION.get(LevelRegistry.DEIMOS);
        deimos.g = 0.04f;
        deimos.radius = 6.2;
        deimos.mass = 1.476e15;
        deimos.temperature = -160;
        deimos.texture = Planets.DEIMOS_TEXTURE;
        deimos.orbitRadius = 0.0610f * MOON_ORBIT_SCALE;
        deimos.orbitColour = mars.orbitColour;
        deimos.tidalLock = true;
        mars.addChild(deimos);

        Planet moon = Planets.BY_DIMENSION.get(LevelRegistry.MOON);
        moon.g = 0.1654f;
        moon.radius = 1737.4;
        moon.mass = MOON_MASS_SCALE;
        moon.temperature = -160;
        moon.texture = Planets.MOON_TEXTURE;
        moon.orbitRadius = MOON_ORBIT_SCALE;
        moon.orbitColour = earth.orbitColour;
        moon.tidalLock = true;
        moon.phaseTexture = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/moon_phases.png");
        earth.addChild(moon);
        sol.addChild(mercury);
        sol.addChild(venus);
        sol.addChild(earth);
        sol.addChild(mars);
        sol.register();

        StarSystem proxima_centauri = new StarSystem();
        proxima_centauri.name = "proxima_centauri";
        proxima_centauri.location[0] = 4.25f;
        proxima_centauri.mass = 0.122f * STAR_MASS_SCALE;
        proxima_centauri.colour = new int[] { 255, 127, 63 };
        Planet glacio = Planets.BY_DIMENSION.get(LevelRegistry.GLACIO);
        glacio.texture = Planets.GLACIO_TEXTURE;
        glacio.mass = 0.08f * PLANET_MASS_SCALE;
        glacio.orbitRadius = 0.39f * PLANET_ORBIT_SCALE;
        glacio.rotation = 180;
        glacio.tier = 4;
        glacio.g = 0.3794f;
        glacio.temperature = -20;
        glacio.hasRain = true;
        glacio.orbitColour = new int[] { 37, 49, 146 };
        proxima_centauri.addChild(glacio);
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
            star.init();
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
        p.addChild(m);
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
    public static class Planet extends CelestialBody {

        private static final ResourceLocation ORBIT_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
                "textures/planet_bar/orbit_planet_bar.png");
        private static final ResourceLocation DEFAULT_PLANET_BAR = new ResourceLocation(BeyondEarth.MODID,
                "textures/planet_bar/earth_planet_bar.png");

        public ResourceKey<Level> planet;
        public ResourceKey<Level> orbit;

        public int planetID;
        public int orbitID;
        public int stationID;

        public boolean hasOxygen = false;
        public boolean hasRain = false;
        public boolean hasClouds = false;
        public boolean spaceLevel = true;
        public boolean hasDustStorms = false;

        // Gui related things
        public ResourceLocation planetBar;
        public ResourceLocation orbitBar;
        public ResourceLocation phaseTexture;
        public float rotation = 0;

        // Colour of the orbit line
        public int[] orbitColour = { 255, 255, 255 };
        // Adjustments for sunrise colours, Setting the first entry to -1 will prevent
        // sunrise colour effects (good for no-air places)
        public float[] sunriseColour = { 0.7f, 0.2f, 0.2f };

        // Misc rendering related things

        // These are for planet selection screen
        public float _distance = 0;
        public float _xPos = 0;
        public float _yPos = 0;

        // This is where we are in orbit around the parent body, used for rendering in
        // the sky box.
        public float orbitPhase = 0;
        // This is the initial offset to the phase.
        public float _initPhase = 0;

        // Planet button tier
        public int tier = 0;
        public int button_category = -1;

        public List<Planet> moons;

        public Planet(ResourceKey<Level> planet, ResourceKey<Level> orbit) {
            this.planet = planet;
            this.orbit = orbit;
            this.name = planet.location().getPath();
            if (planet == Level.OVERWORLD)
                this.name = "earth";
            moons = this.children;
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

            // Sort moons by distance.
            moons.sort(null);
        }

        @Override
        protected void init() {
            super.init();
            moons.forEach(moon -> moon.init());
        }
    }

    public static class StarSystem extends CelestialBody {
        public List<Planet> planets;

        public StarSystem() {
            planets = this.children;
        }

        public void register() {
            STARS.put(name, this);
            ORDERED_STARS.add(this);
            this.planets.forEach(p -> p.register());
        }

        @Override
        protected void init() {
            super.init();
            planets.forEach(planet -> planet.init());
        }
    }

    public static class CelestialBody implements Comparable<CelestialBody> {
        public String name;
        public ResourceLocation texture;
        public float[] location = new float[3];
        public int[] colour = { 232, 219, 176 };
        public Component description;

        public double mass = PLANET_MASS_SCALE;
        public double radius = 6e3;
        public double orbitRadius = PLANET_ORBIT_SCALE;

        public float g = 1;
        public float orbitG = 0.05f;
        public float temperature = 14;
        public boolean tidalLock = false;
        public float airDensity = 0;

        public double orbitT;
        public double orbitL;

        public CelestialBody _parent = null;

        public List<Planet> children = new ArrayList<>();
        public Set<String> childNames = Sets.newHashSet();

        protected Vec3 colourCache = null;

        public Vec3 getLightColour() {
            if (colourCache == null) {
                colourCache = new Vec3(colour[0], colour[1], colour[2]);
            }
            return colourCache;
        }

        protected void init() {
            double parentMass = this._parent == null ? PLANET_MASS_SCALE : _parent.mass;
            // Need radius in m, not km
            double radius = this.orbitRadius * 1e3;
            // G is gravitational constant.
            double GM = parentMass * 6.674e-11;
            // Lets leave this one in km.
            orbitL = (Math.PI * radius * 2) / 1000;
            // This is now in seconds.
            orbitT = 2 * Math.PI * Math.sqrt((radius * radius * radius) / GM);
            // Now it is in days.
            orbitT /= 86400;
        }

        public void addChild(Planet planet) {

            if (!childNames.add(planet.name)) {
                new IllegalStateException("Error, adding duplicate " + planet.name).printStackTrace();
                return;
            }

            this.children.add(planet);
            planet._parent = this;
            planet.colour = this.colour;
            planet.colourCache = this.getLightColour();

            // Sort planets by distance.
            children.sort(null);

            System.out.println("Adding child " + planet.name + " to " + this.name);
        }

        @Override
        public int compareTo(CelestialBody o) {
            return Double.compare(this.orbitRadius, o.orbitRadius);
        }
    }

    public static float getRotation(Planet planet, float timer, float scale) {

        // Convert incoming time to seconds
        timer /= 20f;

        // 1 "day" in minecraft is 1200s. Ths means we scale period by
        // 1200/86400
        scale /= 1200f / 86400f;

        // This should be a reasonable rate for normal motion.
        double T = planet.orbitT * scale;

        return (float) (planet._initPhase + 360 * timer / T) % 360f;
    }

    private static void updateLocation(Level level, Planet planet) {
        var key = level.dimension();
        if (!key.equals(planet.orbit) || key.equals(planet.planet))
            return;
        planet.orbitPhase = getRotation(planet, level.dayTime(), 1);
        planet.moons.forEach(moon -> updateLocation(level, moon));
    }

    public static void updatePlanetLocations(Level level) {
        var list = Planets.getStarsList();
        // The synchronized blocks here are for cases in single player where the player
        // can load the world before the config finishes updating.
        synchronized (list) {
            for (StarSystem system : list) {
                var list2 = system.planets;
                synchronized (list2) {
                    list2.forEach(planet -> {
                        updateLocation(level, planet);
                    });
                }
            }
        }
    }
}
