package net.mrscauthd.beyond_earth.events.forge;

import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class PlanetSelectionGuiInitEvent extends ScreenEvent {

    public PlanetSelectionGuiInitEvent(Screen screen) {
        super(screen);
    }

    @Cancelable
    public static class Pre extends PlanetSelectionGuiInitEvent {
        public Pre(Screen screen) {
            super(screen);
        }
    }

    public static class Post extends PlanetSelectionGuiInitEvent {
        public Post(Screen screen) {
            super(screen);
        }
    }
}
