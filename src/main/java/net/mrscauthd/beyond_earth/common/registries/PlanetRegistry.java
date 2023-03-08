package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.util.Planets;

import static net.mrscauthd.beyond_earth.common.util.Planets.BY_DIMENSION;

public class PlanetRegistry {

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





    /**
     * Here we register default planets. We are set to HIGHEST so that we fire
     * first, and then addons can adjust things.
     *
     * @param event
     */
    public static void registerDefaultPlanets() {
        Planets.registerPlanet(Level.OVERWORLD, LevelRegistry.EARTH_ORBIT);
        Planets.registerPlanet(LevelRegistry.MOON, LevelRegistry.MOON_ORBIT);
        Planets.registerPlanet(LevelRegistry.MARS, LevelRegistry.MARS_ORBIT);
        Planets.registerPlanet(LevelRegistry.PHOBOS, LevelRegistry.PHOBOS_ORBIT);
        Planets.registerPlanet(LevelRegistry.DEIMOS, LevelRegistry.DEIMOS_ORBIT);
        Planets.registerPlanet(LevelRegistry.MERCURY, LevelRegistry.MERCURY_ORBIT);
        Planets.registerPlanet(LevelRegistry.VENUS, LevelRegistry.VENUS_ORBIT);
        Planets.registerPlanet(LevelRegistry.GLACIO, LevelRegistry.GLACIO_ORBIT);

        Planets.registerPlanetBar(LevelRegistry.MOON, MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.PHOBOS, MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.DEIMOS, MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MARS, MARS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MERCURY, MERCURY_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.VENUS, VENUS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.GLACIO, GLACIO_PLANET_BAR);

        Planets.StarSystem sol = new Planets.StarSystem();
        sol.name = "sun";
        sol.texture = SUN_TEXTURE;
        Planets.Planet mercury = BY_DIMENSION.get(LevelRegistry.MERCURY);
        mercury.orbitRadius = 0.39f * Planets.PLANET_ORBIT_SCALE;
        mercury.mass = 0.055f * Planets.PLANET_MASS_SCALE;
        mercury.texture = MERCURY_TEXTURE;
        mercury.rotation = 270;
        mercury.tier = 3;
        mercury.g = 0.38f;
        mercury.radius = 2439.7;
        mercury.temperature = 430;
        mercury.orbitColour = new int[] { 179, 49, 44 };
        Planets.Planet venus = BY_DIMENSION.get(LevelRegistry.VENUS);
        venus.orbitRadius = 0.72f * Planets.PLANET_ORBIT_SCALE;
        venus.mass = 0.81f * Planets.PLANET_MASS_SCALE;
        venus.texture = VENUS_TEXTURE;
        venus.rotation = 180;
        venus.tier = 3;
        venus.g = 0.904f;
        venus.radius = 6051.8;
        venus.temperature = 482;
        venus.airDensity = 100;
        venus.orbitColour = new int[] { 235, 136, 68 };
        Planets.Planet earth = BY_DIMENSION.get(LevelRegistry.EARTH);
        earth.texture = EARTH_TEXTURE;
        earth.rotation = 90;
        earth.tier = 1;
        earth.radius = 6371.0;
        earth.airDensity = 1;
        earth.hasOxygen = true;
        earth.spaceLevel = false;
        earth.hasRain = true;
        earth.orbitColour = new int[] { 53, 163, 79 };
        Planets.Planet mars = BY_DIMENSION.get(LevelRegistry.MARS);
        mars.orbitRadius = 1.52f * Planets.PLANET_ORBIT_SCALE;
        mars.mass = 0.107f * Planets.PLANET_MASS_SCALE;
        mars.texture = MARS_TEXTURE;
        mars.tier = 2;
        mars.radius = 3389.5;
        mars.g = 0.3794f;
        mars.temperature = -63;
        mars.airDensity = 0.001f;
        mars.hasRain = true;
        mars.hasDustStorms = true;
        mars.orbitColour = new int[] { 37, 49, 146 };
        mars.sunriseColour = new float[] { 0, 0.55f, 0.8f };

        Planets.Planet phobos = BY_DIMENSION.get(LevelRegistry.PHOBOS);
        phobos.g = 0.04f;
        phobos.radius = 11.2;
        phobos.mass = 10.6e15;
        phobos.rotation = 180;
        phobos.temperature = -160;
        phobos.texture = PHOBOS_TEXTURE;
        phobos.orbitRadius = 0.0244f * Planets.MOON_ORBIT_SCALE;
        phobos.orbitColour = mars.orbitColour;
        phobos.tidalLock = true;
        mars.addChild(phobos);

        Planets.Planet deimos = BY_DIMENSION.get(LevelRegistry.DEIMOS);
        deimos.g = 0.04f;
        deimos.radius = 6.2;
        deimos.mass = 1.476e15;
        deimos.temperature = -160;
        deimos.texture = DEIMOS_TEXTURE;
        deimos.orbitRadius = 0.0610f * Planets.MOON_ORBIT_SCALE;
        deimos.orbitColour = mars.orbitColour;
        deimos.tidalLock = true;
        mars.addChild(deimos);

        Planets.Planet moon = BY_DIMENSION.get(LevelRegistry.MOON);
        moon.g = 0.1654f;
        moon.radius = 1737.4;
        moon.mass = Planets.MOON_MASS_SCALE;
        moon.temperature = -160;
        moon.texture = MOON_TEXTURE;
        moon.orbitRadius = Planets.MOON_ORBIT_SCALE;
        moon.orbitColour = earth.orbitColour;
        moon.tidalLock = true;
        moon.phaseTexture = new ResourceLocation(BeyondEarth.MODID, "textures/environment/planet/moon_phases.png");
        earth.addChild(moon);
        sol.addChild(mercury);
        sol.addChild(venus);
        sol.addChild(earth);
        sol.addChild(mars);
        sol.register();

        Planets.StarSystem proxima_centauri = new Planets.StarSystem();
        proxima_centauri.name = "proxima_centauri";
        proxima_centauri.location[0] = 4.25f;
        proxima_centauri.mass = 0.122f * Planets.STAR_MASS_SCALE;
        proxima_centauri.colour = new int[] { 255, 127, 63 };
        Planets.Planet glacio = BY_DIMENSION.get(LevelRegistry.GLACIO);
        glacio.texture = GLACIO_TEXTURE;
        glacio.mass = 0.08f * Planets.PLANET_MASS_SCALE;
        glacio.orbitRadius = 0.39f * Planets.PLANET_ORBIT_SCALE;
        glacio.rotation = 180;
        glacio.tier = 4;
        glacio.g = 0.3794f;
        glacio.temperature = -20;
        glacio.hasRain = true;
        glacio.orbitColour = new int[] { 37, 49, 146 };
        proxima_centauri.addChild(glacio);
        proxima_centauri.register();
    }

}
