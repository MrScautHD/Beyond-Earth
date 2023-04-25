package net.mrscauthd.beyond_earth.common.data;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.events.forge.PlanetRegisterEvent;
import net.mrscauthd.beyond_earth.common.util.Planets;
import net.mrscauthd.beyond_earth.common.util.Planets.Planet;
import net.mrscauthd.beyond_earth.common.util.Planets.StarSystem;

public class PlanetData {
    public static class PlanetEntry {
        // This is the name that will be used for localisation/etc. If not present, will
        // try to figure it out from planet.
        String name;
        // This should be the resourcelocation id for the planet's dimension.
        String planet;
        // This should be the resourcelocation id for the orbit around the planet.
        String orbit;

        // What to render for the bars to go into orbit.
        String planet_bar;
        String orbit_bar;
        // Texture location for icon.
        String texture;
        // Phased texture for different planet/moon phases If not present, will just
        // render from texture.
        String phase_texture;

        // Orbit radius is in AU for planets, and lunar orbit radii for moons.
        double orbit_radius = 1;

        // Planetary mass in earth masses, or lunar masses if this is a moon
        double mass = 1;
        // Gravity in earth gs
        float g = 1;
        // g while in orbit
        float orbit_g = 0.05f;
        // Radius of the object in km
        double radius = 6e3;

        // This sets if we render fog, it is based on an assumed surface atmospheric
        // density scaled to that of earth.
        float air_density = 0;

        // Variety of environment related options
        boolean has_oxygen = false;
        boolean has_rain = false;
        boolean has_clouds = false;
        boolean space_level = true;
        boolean has_dust_storms = false;

        // Options for Rotation in the gui
        float rotation = 0;
        // Orbit line colour
        int[] orbit_colour = { 255, 255, 255 };
        // Adjustments for sunrise colours, Setting the first entry to -1 will prevent
        // sunrise colour effects (good for no-air places)
        public float[] sunriseColour = { 0.7f, 0.2f, 0.2f };

        // if a planet is tidally locked, it always faces the parent body. This is used
        // for moons to always render planet in the sky.
        boolean tidal_lock = false;

        // Planet button tier
        int tier = 0;

        public List<PlanetEntry> moons = new ArrayList<>();

        public PlanetEntry() {
        }

        public PlanetEntry(Planet planet) {
            this.mass = planet.mass;
            this.orbit_radius = planet.orbitRadius;

            this.name = planet.name;
            this.planet = planet.planet.location().toString();
            this.orbit = planet.orbit.location().toString();
            this.has_oxygen = planet.hasOxygen;
            this.space_level = planet.spaceLevel;
            this.has_clouds = planet.hasClouds;
            this.has_rain = planet.hasRain;
            this.has_dust_storms = planet.hasDustStorms;
            this.tier = planet.tier;
            this.g = planet.g;
            this.tidal_lock = planet.tidalLock;
            this.air_density = planet.airDensity;
            this.radius = planet.radius;

            this.orbit_colour = planet.orbitColour;
            this.sunriseColour = planet.sunriseColour;

            if (planet.planetBar != null)
                this.planet_bar = planet.planetBar.toString();
            if (planet.orbitBar != null)
                this.orbit_bar = planet.orbitBar.toString();
            if (planet.texture != null)
                this.texture = planet.texture.toString();
            if (planet.phaseTexture != null)
                this.phase_texture = planet.phaseTexture.toString();

            this.rotation = planet.rotation;

            planet.moons.forEach(p -> moons.add(new PlanetEntry(p)));
        }

        public Planet toPlanet(@Nullable Planet parent) {
            ResourceKey<Level> location = ResourceKey.create(Registries.DIMENSION,
                    new ResourceLocation(this.planet));
            ResourceKey<Level> orbit = ResourceKey.create(Registries.DIMENSION,
                    new ResourceLocation(this.orbit));
            Planet planet = new Planet(location, orbit);
            if (this.name != null)
                planet.name = this.name;
            if (this.planet_bar != null)
                planet.planetBar = new ResourceLocation(this.planet_bar);
            if (this.orbit_bar != null)
                planet.orbitBar = new ResourceLocation(this.orbit_bar);
            if (this.texture != null)
                planet.texture = new ResourceLocation(this.texture);
            if (this.phase_texture != null)
                planet.phaseTexture = new ResourceLocation(this.phase_texture);

            planet.mass = this.mass;
            planet.orbitRadius = this.orbit_radius;
            planet.g = this.g;
            planet.hasClouds = this.has_clouds;
            planet.hasDustStorms = this.has_dust_storms;
            planet.sunriseColour = this.sunriseColour;
            planet.tidalLock = this.tidal_lock;
            planet.airDensity = this.air_density;
            planet.radius = this.radius;

            planet.rotation = this.rotation;
            planet.orbitPhase = this.rotation;
            planet._initPhase = this.rotation;
            planet.hasOxygen = this.has_oxygen;
            planet.hasRain = this.has_rain;
            planet.spaceLevel = this.space_level;
            planet.tier = this.tier;
            planet.orbitColour = this.orbit_colour;

            if (parent != null)
                parent.addChild(planet);
            moons.forEach(p -> p.toPlanet(planet));
            return planet;
        }
    }

    public static class StarEntry {
        // Unique name of this star
        String name;
        // Texture location for icon.
        String texture;

        // List of planets for this star.
        public List<PlanetEntry> planets = new ArrayList<>();

        // These are coordinates of the star, in lightyears from centre
        public float[] location = new float[3];

        public int[] colour = { 255, 255, 255 };

        // Mass in solar masses
        double mass = 1;

        public StarEntry() {
        }

        public StarEntry(StarSystem star) {
            this.name = star.name;
            this.mass = star.mass;
            this.location = star.location;
            this.colour = star.colour;
            if (star.texture != null)
                this.texture = star.texture.toString();
            star.planets.forEach(p -> this.planets.add(new PlanetEntry(p)));
        }

        public StarSystem toStarSystem() {
            StarSystem star = new StarSystem();
            star.name = this.name;
            this.planets.forEach(p -> star.addChild(p.toPlanet(null)));
            star.mass = mass;
            star.location = this.location;
            star.colour = this.colour;
            if (this.texture != null)
                star.texture = new ResourceLocation(this.texture);
            star.register();
            return star;
        }
    }

    // Set this to true if you want to change the config!
    public boolean modified = false;
    // List of stars to include.
    public List<StarEntry> stars = new ArrayList<>();

    public void initPlanets(boolean event) {

        // We only clear and update the planets if the file was modified!
        if (!this.modified) {
            // First clear whatever loaded in.
            this.stars.clear();

            // This will generate the default planet set if it is missing, as we can get to
            // here without having generated them, if the file is already existing.
            Planets.getStarsList().forEach(s -> this.stars.add(new StarEntry(s)));

            // First clear all of the old planets.
            Planets.clear();

            // Now lets start generating stars systems.
            stars.forEach(StarEntry::toStarSystem);
            if (event)
                MinecraftForge.EVENT_BUS.post(new PlanetRegisterEvent.Load());

            Planets.initIDs();
            return;
        }

        // First clear all of the old planets.
        Planets.clear();

        // Now lets start generating stars systems.
        stars.forEach(StarEntry::toStarSystem);
        if (event)
            MinecraftForge.EVENT_BUS.post(new PlanetRegisterEvent.Load());

        Planets.initIDs();
    }

    public static void loadOrGenerateDefaults() {
        Path path = FMLPaths.CONFIGDIR.get().resolve(BeyondEarth.MODID).resolve("planets.json");
        try {
            var reader = Files.newBufferedReader(path);
            PlanetData data = PlanetDataHandler.gson.fromJson(reader, PlanetData.class);
            // Event here as we are loading planets.
            data.initPlanets(true);
        } catch (Exception e) {

            // This is expected first run, so don't throw then
            if (!(e instanceof NoSuchFileException))
                e.printStackTrace();

            Planets.generateDefaults();
            PlanetData defaults = new PlanetData();
            Planets.ORDERED_STARS.forEach(s -> defaults.stars.add(new StarEntry(s)));

            try {
                File folder = path.toFile().getParentFile();
                if (!folder.exists())
                    folder.mkdirs();
                String json = PlanetDataHandler.gson.toJson(defaults);
                var writer = Files.newBufferedWriter(path);
                writer.write(json);
                writer.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            // Mark the default one as "modified" and then force an init
            defaults.modified = true;
            defaults.initPlanets(true);
        }
    }

    public static class PlanetDataHandler {

        public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        public PlanetData data;

        public static void encode(PlanetDataHandler data, FriendlyByteBuf buffer) {
            buffer.writeUtf(gson.toJson(data.data));
        }

        public static PlanetDataHandler decode(FriendlyByteBuf buffer) {
            PlanetDataHandler ret = new PlanetDataHandler();
            ret.data = gson.fromJson(buffer.readUtf(), PlanetData.class);
            return ret;
        }

        public static void handle(PlanetDataHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                // No event on this side, as here we are syncing from server.
                message.data.initPlanets(false);
            });
        }
    }

}
