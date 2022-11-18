package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraftforge.eventbus.api.Event;

public abstract class PlanetRegisterEvent extends Event {
    public static class Generate extends PlanetRegisterEvent {

    }

    public static class Load extends PlanetRegisterEvent {
    }
}
