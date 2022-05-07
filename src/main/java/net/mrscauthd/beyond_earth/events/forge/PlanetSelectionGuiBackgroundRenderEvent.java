package net.mrscauthd.beyond_earth.events.forge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class PlanetSelectionGuiBackgroundRenderEvent extends ScreenEvent {
    private PoseStack poseStack;
    private float partialTicks;
    private int mouseX;
    private int mouseY;

    public PlanetSelectionGuiBackgroundRenderEvent(Screen screen, PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
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
    public static class Pre extends PlanetSelectionGuiBackgroundRenderEvent {
        public Pre(Screen screen, PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
            super(screen, poseStack, partialTicks, mouseX, mouseY);
        }
    }

    public static class Post extends PlanetSelectionGuiBackgroundRenderEvent {
        public Post(Screen screen, PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
            super(screen, poseStack, partialTicks, mouseX, mouseY);
        }
    }
}
