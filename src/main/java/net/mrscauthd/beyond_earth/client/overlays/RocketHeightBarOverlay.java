package net.mrscauthd.beyond_earth.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.util.Methods;
import net.mrscauthd.beyond_earth.common.util.Planets;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetOverlayEvent;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;

public class RocketHeightBarOverlay implements IGuiOverlay {
    /** ROCKET TEXTURE */
    public static final ResourceLocation ROCKET = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/rocket.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Player player = Minecraft.getInstance().player;

        if (Methods.isRocket(player.getVehicle()) || player.getVehicle() instanceof LanderEntity) {
            Level level = Minecraft.getInstance().level;

            float yHeight = (float) player.getY() / 5.3F;

            if (yHeight < 0) {
                yHeight = 0;
            } else if (yHeight > 113) {
                yHeight = 113;
            }

            ResourceLocation planet = Planets.getPlanetBar(level);

            PlanetOverlayEvent event = new PlanetOverlayEvent(gui, planet, poseStack, partialTick, width, height);
            MinecraftForge.EVENT_BUS.post(event);

            if (planet != event.getResourceLocation()) {
                planet = event.getResourceLocation();
            }

            /** ROCKET BAR IMAGE */
            RenderSystem.setShaderTexture(0, planet);
            ForgeGui.blit(poseStack, 0, (height / 2) - 128 / 2, 0, 0, 16, 128, 16, 128);

            /** ROCKET_Y IMAGE */
            RenderSystem.setShaderTexture(0, ROCKET);
            ScreenHelper.renderWithFloat.blit(poseStack, 4, (height / 2) + (103 / 2) - yHeight, 0, 0, 8, 11, 8, 11);
        }
    }
}