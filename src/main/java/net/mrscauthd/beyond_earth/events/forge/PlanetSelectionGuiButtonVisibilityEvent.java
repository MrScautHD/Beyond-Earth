package net.mrscauthd.beyond_earth.events.forge;

import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class PlanetSelectionGuiButtonVisibilityEvent extends ScreenEvent {

    public PlanetSelectionGuiButtonVisibilityEvent(Screen screen) {
        super(screen);
    }

    @Cancelable
    public static class Pre extends PlanetSelectionGuiButtonVisibilityEvent {
        public Pre(Screen screen) {
            super(screen);
        }
    }

    public static class Post extends PlanetSelectionGuiButtonVisibilityEvent {
        public Post(Screen screen) {
            super(screen);
        }
    }
}
