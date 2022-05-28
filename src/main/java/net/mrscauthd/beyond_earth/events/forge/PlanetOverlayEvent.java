package net.mrscauthd.beyond_earth.events.forge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.Event;

public class PlanetOverlayEvent extends Event {

    private ForgeIngameGui gui;
    private ResourceLocation resourceLocation;
    private PoseStack poseStack;
    private float partialTicks;
    private int width;
    private int height;

    public PlanetOverlayEvent(ForgeIngameGui gui, ResourceLocation resourceLocation, PoseStack poseStack, float partialTicks, int width, int height) {
        this.gui = gui;
        this.resourceLocation = resourceLocation;
        this.poseStack = poseStack;
        this.partialTicks = partialTicks;
        this.width = width;
        this.height = width;
    }

    public ForgeIngameGui getGui() {
        return gui;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public PoseStack getPoseStack() {
        return poseStack;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }
}
