package net.mrscauthd.beyond_earth.common.config.data;

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
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
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

        // This set of vaiues are for gravity, -1 means use default.

        float planet_item_gravity = -1;
        float planet_enitity_gravity = -1;
        float orbit_item_gravity = 0.05f;
        float orbit_enitity_gravity = 0.01f;

        // Orbit radius is in AU for planets, and lunar orbit radii for moons.
        float orbit_radius = 1;

        // Planetary mass in earth masses, or lunar masses if this is a moon
        float mass = 1;
        // Gravity in earth gs
        float g = 1;

        boolean has_oxygen = false;
        boolean has_rain = false;
        boolean space_level = true;

        // Rotation in the gui
        public float rotation = 0;
        // Orbit line colour
        public int[] orbit_colour = { 255, 255, 255 };
        // Planet button tier
        public int tier = 0;

        public String[] extra_text;

        public List<PlanetEntry> moons = new ArrayList<>();

        public PlanetEntry() {
        }

        public PlanetEntry(Planet planet) {

            this.orbit_enitity_gravity = planet.orbitEntityGravity;
            this.orbit_item_gravity = planet.orbitItemGravity;
            this.planet_enitity_gravity = planet.planetEntityGravity;
            this.planet_item_gravity = planet.planetItemGravity;

            this.mass = planet.mass;
            this.orbit_radius = planet.orbitRadius;

            this.name = planet.name;
            this.planet = planet.planet.location().toString();
            this.orbit = planet.orbit.location().toString();
            this.has_oxygen = planet.hasOxygen;
            this.space_level = planet.spaceLevel;
            this.has_rain = planet.hasRain;
            this.tier = planet.tier;

            this.orbit_colour = planet.orbitColour;

            if (planet.planetBar != null)
                this.planet_bar = planet.planetBar.toString();
            if (planet.orbitBar != null)
                this.orbit_bar = planet.orbitBar.toString();
            if (planet.texture != null)
                this.texture = planet.texture.toString();

            this.rotation = planet.rotation;

            this.extra_text = planet.extra_text;

            planet.moons.forEach(p -> moons.add(new PlanetEntry(p)));
        }

        public Planet toPlanet(@Nullable Planet parent) {
            ResourceKey<Level> location = ResourceKey.create(Registry.DIMENSION_REGISTRY,
                    new ResourceLocation(this.planet));
            ResourceKey<Level> orbit = ResourceKey.create(Registry.DIMENSION_REGISTRY,
                    new ResourceLocation(this.orbit));
            Planet planet = new Planet(location, orbit, planet_item_gravity, planet_enitity_gravity, orbit_item_gravity,
                    orbit_enitity_gravity);
            if (this.name != null)
                planet.name = this.name;
            if (this.planet_bar != null)
                planet.planetBar = new ResourceLocation(this.planet_bar);
            if (this.orbit_bar != null)
                planet.orbitBar = new ResourceLocation(this.orbit_bar);
            if (this.texture != null)
                planet.texture = new ResourceLocation(this.texture);

            planet.mass = this.mass;
            planet.orbitRadius = this.orbit_radius;
            planet.register();

            planet.rotation = this.rotation;
            planet.extra_text = this.extra_text;
            planet.hasOxygen = this.has_oxygen;
            planet.hasRain = this.has_rain;
            planet.spaceLevel = this.space_level;
            planet.tier = this.tier;
            planet.orbitColour = this.orbit_colour;

            if (parent != null)
                parent.moons.add(planet);
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
        float mass = 1;

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
            this.planets.forEach(p -> star.planets.add(p.toPlanet(null)));
            star.mass = mass;
            star.location = this.location;
            star.colour = this.colour;
            if (this.texture != null)
                star.texture = new ResourceLocation(this.texture);
            star.register();
            return star;
        }
    }

    // List of stars to include.
    public List<StarEntry> stars = new ArrayList<>();

    public void initPlanets(boolean event) {
        // First clear all of the old planets.
        Planets.clear();

        // Now lets start generating stars systems.
        stars.forEach(StarEntry::toStarSystem);
        if (event)
            MinecraftForge.EVENT_BUS.post(new PlanetRegisterEvent.Load());

        Planets.initIDs();
    }

    public static void loadOrGenerateDefaults(FMLLoadCompleteEvent event) {
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
