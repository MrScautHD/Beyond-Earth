package net.mrscauthd.beyond_earth.guis.screens.planetselection.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.simple.SimpleChannel;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.guis.helper.GuiHelper;
import net.mrscauthd.beyond_earth.guis.helper.ImageButtonPlacer;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGui;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGuiWindow;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PlanetSelectionGuiHelper {

    //TODO NOT USED YET
    public static List<Button> solarSystemButtons = new ArrayList<>();

    public static List<Button> sunButtons = new ArrayList<>();
    public static List<Button> proximaCentauriButtons = new ArrayList<>();

    /** USE IT FOR CATEGORY BUTTONS */
    public static ImageButtonPlacer addCategoryButton(PlanetSelectionGuiWindow screen, int x, int y, int width, int height, int newCategory, boolean condition, ImageButtonPlacer.Types type, List<String> list, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, Component title) {
        ImageButtonPlacer button = screen.addButton(x, (screen.height / 2) + y / 2, width, height, condition, type, list, buttonTexture, hoverButtonTexture, title, (onPress) -> {
            if (condition) {
                screen.category = newCategory;
            }
        });

        return button;
    }

    /** USE IT FOR TELEPORT BUTTONS */
    public static ImageButtonPlacer addHandlerButton(PlanetSelectionGuiWindow screen, int x, int y, int width, int height, boolean condition, SimpleChannel simpleChannel, PlanetSelectionGui.AbstractNetworkHandler handler, ImageButtonPlacer.Types type, List<String> list, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, Component title) {
        ImageButtonPlacer button = screen.addButton(x, (screen.height / 2) + y / 2, width, height, condition, type, list, buttonTexture, hoverButtonTexture, title, (onPress) -> {
            if (condition) {
                callPacketHandler(simpleChannel, handler);
            }
        });

        return button;
    }

    /** USE IT FOR BACK BUTTONS */
    public static ImageButtonPlacer addBackButton(PlanetSelectionGuiWindow screen, int x, int y, int width, int height, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, Component title, Button.OnPress onPress) {
        ImageButtonPlacer button = screen.addButton(x, (screen.height / 2) + y / 2, width, height, false,null, null, buttonTexture, hoverButtonTexture, title, onPress);
        return button;
    }

    /** USE THIS TO ROTATE TEXTURES (LIKE PLANETS) */
    public static void addRotatedObject(PlanetSelectionGuiWindow screen, PoseStack ms, ResourceLocation texture, float x, float y, int width, int height, float rotation) {
        ms.pushPose();

        ms.translate(screen.width / 2, screen.height / 2, 0);
        ms.mulPose(new Quaternion(Vector3f.ZP, rotation, true));

        RenderSystem.setShaderTexture(0, texture);
        GuiHelper.blit(ms, x, y, 0, 0, width, height, width, height);

        ms.translate(-screen.width / 2, -screen.height / 2, 0);
        ms.popPose();
    }

    /** USE THIS TO ADD TEXTURES */
    public static void addTexture(PoseStack poseStack, int x, int y, int width, int height, ResourceLocation texture) {
        RenderSystem.setShaderTexture(0, texture);
        GuiComponent.blit(poseStack, x, y, 0, 0, width, height, width, height);
    }

    /** USE THIS TO ENABLE THE BLEND SYSTEM */
    public static void enableRenderSystem() {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
    }

    /** USE THIS TO DISABLE THE BLEND SYSTEM */
    public static void disableRenderSystem() {
        RenderSystem.disableBlend();
    }

    /** USE THIS TO CHECK THE CATEGORY RANGE */
    public static boolean categoryRange(int category, int start, int end) {
        if (category >= start && category <= end) {
            return true;
        }
        return false;
    }

    /** USE THIS TO CHECK ROCKET TIERS (IF YOU ADDED A OWN ROCKET DO A NEW METHOD) */
    public static boolean checkTier(String rocketType, int stage) {
        int tier = 0;

        if (rocketType.equals("entity." + BeyondEarthMod.MODID + ".rocket_t1")) {
            tier = 1;
        }
        else if (rocketType.equals("entity." + BeyondEarthMod.MODID + ".rocket_t2")) {
            tier = 2;
        }
        else if (rocketType.equals("entity." + BeyondEarthMod.MODID + ".rocket_t3")) {
            tier = 3;
        }
        else if (rocketType.equals("entity." + BeyondEarthMod.MODID + ".rocket_t4")) {
            tier = 4;
        }

        return tier >= stage;
    }

    /** ADDON MODS SHOULD USE A OWN TL METHOD */
    public static Component tl(String string) {
        return new TranslatableComponent("gui." + BeyondEarthMod.MODID + ".planet_selection." + string);
    }

    /** ADDON MODS SHOULD DO A OWN HANDLER EXTENDED OF "AbstractNetworkHandler" */
    public static void callPacketHandler(SimpleChannel simpleChannel, PlanetSelectionGui.AbstractNetworkHandler handler) {
        simpleChannel.sendToServer(handler);
    }

    /** ADDON MODS SHOULD RETURN A OWN NETWORK HANDLER */
    public static PlanetSelectionGui.NetworkHandler getNetworkHandler(int handler) {
        return new PlanetSelectionGui.NetworkHandler(handler);
    }
}
