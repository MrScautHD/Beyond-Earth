package net.mrscauthd.beyond_earth.common.util;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
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
    /**
     * This map is for modifying fall distance for planets. Register values in here
     * with Planets.registerFallModifier
     */
    private static Object2FloatOpenHashMap<ResourceKey<Level>> FALL_MODIFIERS = new Object2FloatOpenHashMap<>();

    static {
	// Register our default planets
	registerPlanet(Level.OVERWORLD, LevelRegistry.EARTH_ORBIT);
	registerPlanet(LevelRegistry.MOON, LevelRegistry.MOON_ORBIT, 0.05f, 0.02f);
	registerPlanet(LevelRegistry.MARS, LevelRegistry.MARS_ORBIT, 0.06f, 0.05f);
	registerPlanet(LevelRegistry.MERCURY, LevelRegistry.MERCURY_ORBIT, 0.05f, 0.02f);
	registerPlanet(LevelRegistry.VENUS, LevelRegistry.VENUS_ORBIT);
	registerPlanet(LevelRegistry.GLACIO, LevelRegistry.GLACIO_ORBIT, 0.05f, 0.03f);

	registerFallModifier(LevelRegistry.MOON, 5.5f);
	registerFallModifier(LevelRegistry.MARS, 5.0f);
	registerFallModifier(LevelRegistry.MERCURY, 5.5f);
	registerFallModifier(LevelRegistry.GLACIO, 5.0f);
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

    /**
     * This stores the planet's location, as well as traits such as gravity amount
     * for orbit, planet gravity, etc.
     * 
     * @author Thutmose
     *
     */
    public static class Planet {
	ResourceKey<Level> planet;
	ResourceKey<Level> orbit;
	float planetItemGravity;
	float planetEntityGravity;
	float orbitItemGravity;
	float orbitEntityGravity;

	public Planet(ResourceKey<Level> planet, ResourceKey<Level> orbit, float planetItemGravity,
		float planetEntityGravity, float orbitItemGravity, float orbitEntityGravity) {
	    this.planet = planet;
	    this.orbit = orbit;
	    this.planetItemGravity = planetItemGravity;
	    this.planetEntityGravity = planetEntityGravity;
	    this.orbitItemGravity = orbitItemGravity;
	    this.orbitEntityGravity = orbitEntityGravity;
	}

	private void register() {
	    PLANETS_BY_ORBIT.put(orbit, this);
	    PLANETS_BY_PLANET.put(planet, this);
	    BY_DIMENSION.put(orbit, this);
	    BY_DIMENSION.put(planet, this);
	}
    };
}
