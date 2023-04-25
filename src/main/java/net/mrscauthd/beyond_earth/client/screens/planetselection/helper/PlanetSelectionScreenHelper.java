package net.mrscauthd.beyond_earth.client.screens.planetselection.helper;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.simple.SimpleChannel;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.buttons.ModifiedButton;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.client.screens.planetselection.PlanetSelectionScreen;
import net.mrscauthd.beyond_earth.client.util.ClientMethods;
import net.mrscauthd.beyond_earth.common.menus.planetselection.PlanetSelectionMenuNetworkHandler;
import net.mrscauthd.beyond_earth.common.menus.planetselection.helper.PlanetSelectionMenuNetworkHandlerHelper;
import net.mrscauthd.beyond_earth.common.util.Planets.Planet;
import org.joml.Quaternionf;

@OnlyIn(Dist.CLIENT)
public class PlanetSelectionScreenHelper {

    /** USE IT FOR CATEGORY BUTTONS */
    public static ModifiedButton addCategoryButton(PlanetSelectionScreen screen, CategoryHelper categoryHelper, int x, int row, int width, int height, int newCategory, boolean pressCondition, boolean startVisibility, ModifiedButton.ButtonTypes type, List<String> list, ResourceLocation buttonTexture, ModifiedButton.ColorTypes colorType, Component title) {
        ModifiedButton button = screen.addButton(x, 0, row, width, height, pressCondition, type, list, buttonTexture, colorType, title, (onPress) -> {
            if (pressCondition) {
                categoryHelper.set(newCategory);
                screen.scrollIndex = 0;
                screen.updateButtonVisibility();
            }
        });

        screen.visibleButton(button, startVisibility);
        return button;
    }

    /** USE IT FOR TELEPORT BUTTONS */
    public static ModifiedButton addHandlerButton(PlanetSelectionScreen screen, int x, int row, int width, int height, boolean pressCondition, boolean startVisibility, boolean holdKeyMessage, SimpleChannel simpleChannel, PlanetSelectionMenuNetworkHandlerHelper handler, ModifiedButton.ButtonTypes type, List<String> list, ResourceLocation buttonTexture, ModifiedButton.ColorTypes colorType, Component title) {
        ModifiedButton button = screen.addButton(x, 0, row, width, height, pressCondition, type, list, buttonTexture, colorType, title, (onPress) -> {
            if (pressCondition) {
                callPacketHandler(simpleChannel, handler);

                if (holdKeyMessage) {
                    ClientMethods.sendPressKeyMessage();
                }

                screen.scrollIndex = 0;
                screen.updateButtonVisibility();
            }
        });

        screen.visibleButton(button, startVisibility);
        return button;
    }

    /** USE IT FOR BACK BUTTONS */
    public static ModifiedButton addBackButton(PlanetSelectionScreen screen, int x, int row, int width, int height, boolean startVisibility, ResourceLocation buttonTexture, ModifiedButton.ColorTypes colorType, Component title, Button.OnPress onPress) {
        ModifiedButton button = screen.addButton(x, 0, row, width, height, false,null, null, buttonTexture, colorType, title, onPress);

        screen.visibleButton(button, startVisibility);
        return button;
    }

    /** USE IT TO RENDER A CIRCLE */
    public static void drawCircle(double x, double y, double radius, int sides, float lineWidth, Vec3 color) {
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        int r = (int) color.x();
        int g = (int) color.y();
        int b = (int) color.z();

        bufferBuilder.begin(VertexFormat.Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);

        double width = radius - 0.5;
        for (double f1 = width; f1 < width + lineWidth; f1 += 0.1 * lineWidth) {

            for (int f2 = 0; f2 <= sides; f2++) {
                double angle = (Math.PI * 2 * f2 / sides) + Math.toRadians(180);
                bufferBuilder.vertex(x + Math.sin(angle) * f1, y + Math.cos(angle) * f1, 0).color(r, g, b, 255)
                        .endVertex();
            }
        }
        BufferUploader.drawWithShader(bufferBuilder.end());
    }

    /** USE THIS TO ROTATE PLANETS */
    public static void drawPlanet(PoseStack ms, Planet planet, int width, int height, boolean showName) {
        ResourceLocation texture = planet.texture;

        /** TEXTURE */
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        ScreenHelper.renderWithFloat.blit(ms, planet._xPos, planet._yPos, 0, 0, width, height, width, height);

        /** TEXT */
        if (showName) {
            Font font = Minecraft.getInstance().font;
            Component name = planet.description;
            font.draw(ms, name, planet._xPos - font.width(name) / 3, planet._yPos + 13, 0xFFFFFF);
        }
    }

    /** USE THIS TO ROTATE GALAXIES */
    public static void drawGalaxy(PlanetSelectionScreen screen, PoseStack ms, ResourceLocation texture, float x, float y, int width, int height, float rotation) {
        ms.pushPose();

        ms.translate(screen.width / 2, screen.height / 2, 0);
        ms.mulPose(Axis.ZP.rotationDegrees(rotation));

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        ScreenHelper.renderWithFloat.blit(ms, x, y, 0, 0, width, height, width, height);

        ms.popPose();
    }

    /** USE THIS TO CHECK THE CATEGORY RANGE */
    public static boolean categoryRange(int category, int start, int end) {
        return category >= start && category <= end;
    }

    /** ADDON MODS SHOULD USE A OWN TL METHOD */
    public static Component tl(String text) {
        return Component.translatable("gui." + BeyondEarth.MODID + ".planet_selection." + text);
    }

    /** ADDON MODS SHOULD DO A OWN HANDLER EXTENDED OF "AbstractNetworkHandler" */
    public static void callPacketHandler(SimpleChannel simpleChannel, PlanetSelectionMenuNetworkHandlerHelper handler) {
        simpleChannel.sendToServer(handler);
    }

    /** ADDON MODS SHOULD RETURN A OWN NETWORK HANDLER */
    public static PlanetSelectionMenuNetworkHandler getNetworkHandler(int handler) {
        return new PlanetSelectionMenuNetworkHandler(handler);
    }
}
