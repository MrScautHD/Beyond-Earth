package net.mrscauthd.beyond_earth.common.config.data;

import java.util.ArrayList;
import java.util.List;

public class PlanetData {
    public static class PlanetEntry {
        // This should be the resourcelocation id for the planet's dimension.
        String planet;
        // This should be the resourcelocation id for the orbit around the planet.
        String orbit;

        // This set of vaiues are for gravity, -1 means use default.

        float planet_item_gravity = -1;
        float planet_enitity_gravity = -1;
        float orbit_item_gravity = 0.05f;
        float orbit_enitity_gravity = 0.01f;

        // Orbit radius is in AU for planets, and lunar orbit radii for moons.
        float orbit_radius = 1;

        public List<PlanetEntry> moons = new ArrayList<>();
    }

    public static class SystemEntry {
        public List<PlanetEntry> planets = new ArrayList<>();

        // These are coordinates of the star, in lightyears from centre
        float x = 0;
        float y = 0;
        float z = 0;
    }

    // List of systems to include.
    List<SystemEntry> systems = new ArrayList<>();
}
