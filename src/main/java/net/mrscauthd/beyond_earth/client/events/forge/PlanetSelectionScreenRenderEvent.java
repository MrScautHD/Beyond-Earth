package net.mrscauthd.beyond_earth.client.events.forge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class PlanetSelectionScreenRenderEvent extends ScreenEvent {

    private final PoseStack poseStack;
    private final float partialTicks;
    private final int mouseX;
    private final int mouseY;

    public PlanetSelectionScreenRenderEvent(Screen screen, PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        super(screen);
        this.poseStack = poseStack;
        this.partialTicks = partialTicks;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public PoseStack getPoseStack() {
        return poseStack;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    @Cancelable
    public static class Pre extends PlanetSelectionScreenRenderEvent {
        public Pre(Screen screen, PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
            super(screen, poseStack, partialTicks, mouseX, mouseY);
        }
    }

    public static class Post extends PlanetSelectionScreenRenderEvent {
        public Post(Screen screen, PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
            super(screen, poseStack, partialTicks, mouseX, mouseY);
        }
    }
}
