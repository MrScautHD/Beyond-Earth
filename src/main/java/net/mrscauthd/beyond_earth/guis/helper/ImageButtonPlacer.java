package net.mrscauthd.beyond_earth.guis.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGuiWindow;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ImageButtonPlacer extends Button {
    private ResourceLocation buttonTexture;
    private ResourceLocation hoverButtonTexture;

    private Types type;
    private List<String> list;

    private final int xTexStart;
    private final int yTexStart;

    private final int yDiffText;

    private final int textureWidth;
    private final int textureHeight;

    public ImageButtonPlacer(int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, Types type, List<String> list, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, Button.OnPress onPressIn) {
        this(xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, type, list, buttonTexture, hoverButtonTexture, 256, 256, onPressIn);
    }

    public ImageButtonPlacer(int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, Types type, List<String> list, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, int p_i51135_9_, int p_i51135_10_, Button.OnPress onPressIn) {
        this(xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, type, list, buttonTexture, hoverButtonTexture, p_i51135_9_, p_i51135_10_, onPressIn, TextComponent.EMPTY);
    }

    public ImageButtonPlacer(int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffText, Types type, List<String> list, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, int textureWidth, int textureHeight, Button.OnPress onPress, Component title) {
        this(x, y, width, height, xTexStart, yTexStart, yDiffText, type, list, buttonTexture, hoverButtonTexture, textureWidth, textureHeight, onPress, Button.NO_TOOLTIP, title);
    }

    public ImageButtonPlacer(int p_i244513_1_, int p_i244513_2_, int p_i244513_3_, int p_i244513_4_, int p_i244513_5_, int p_i244513_6_, int p_i244513_7_, Types type, List<String> list, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, int p_i244513_9_, int p_i244513_10_, Button.OnPress p_i244513_11_, net.minecraft.client.gui.components.Button.OnTooltip p_i244513_12_, Component p_i244513_13_) {
        super(p_i244513_1_, p_i244513_2_, p_i244513_3_, p_i244513_4_, p_i244513_13_, p_i244513_11_, p_i244513_12_);
        this.textureWidth = p_i244513_9_;
        this.textureHeight = p_i244513_10_;
        this.xTexStart = p_i244513_5_;
        this.yTexStart = p_i244513_6_;
        this.yDiffText = p_i244513_7_;
        this.type = type;
        this.list = list;
        this.buttonTexture = buttonTexture;
        this.hoverButtonTexture = hoverButtonTexture;
    }

    public void setPosition(int xIn, int yIn) {
        this.x = xIn;
        this.y = yIn;
    }

    /** IF YOU WANT NO ONE RETURN (null) */
    public enum Types {

        /** IF YOU USE THIS PUT 1 STRING IN THE LIST (CATEGORY) */
        MILKY_WAY_CATEGORY,

        /** IF YOU USE THIS PUT 2 STRINGS IN THE LIST (CATEGORY, PROVIDED) */
        SOLAR_SYSTEM_CATEGORY,

        /** idk right now */
        PLANET_CATEGORY
    }

    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableDepthTest();

        int i = this.yTexStart;
        if (this.isHoveredOrFocused()) {
            i += this.yDiffText;
        }

        ResourceLocation texture;

        if (this.isHovered) {
            texture = this.hoverButtonTexture;
        } else {
            texture = this.buttonTexture;
        }

        RenderSystem.setShaderTexture(0, texture);
        blit(poseStack, this.x, this.y, (float)this.xTexStart, (float)i, this.width, this.height, this.textureWidth, this.textureHeight);

        Font fontRenderer = minecraft.font;
        int j = getFGColor();
        drawCenteredString(poseStack, fontRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);

        /** TYPE SYSTEM */
        if (minecraft.screen instanceof PlanetSelectionGuiWindow) {
            this.milkyWayCategoryManager(minecraft, poseStack, mouseX, mouseY);
            this.solarSystemCategoryManager(minecraft, poseStack, mouseX, mouseY);
        }

        RenderSystem.disableDepthTest();
    }

    public void milkyWayCategoryManager(Minecraft minecraft, PoseStack poseStack, int mouseX, int mouseY) {
        if (this.isHovered && this.type == Types.MILKY_WAY_CATEGORY) {
            Screen screen = minecraft.screen;

            List<Component> list = new ArrayList<>();

            list.add(new TextComponent("\u00A79" + PlanetSelectionGuiWindow.CATEGORY_TEXT.getString() + ": \u00A7b" + this.list.get(0)));
            list.add(new TextComponent("\u00A79" + PlanetSelectionGuiWindow.TYPE_TEXT.getString() + ": \u00A73" + PlanetSelectionGuiWindow.SOLAR_SYSTEM_TEXT.getString()));

            screen.renderComponentTooltip(poseStack, list, mouseX, mouseY);
        }
    }

    public void solarSystemCategoryManager(Minecraft minecraft, PoseStack poseStack, int mouseX, int mouseY) {
        if (this.isHovered && this.type == Types.SOLAR_SYSTEM_CATEGORY) {
            Screen screen = minecraft.screen;

            List<Component> list = new ArrayList<>();

            list.add(new TextComponent("\u00A79" + PlanetSelectionGuiWindow.CATEGORY_TEXT.getString() + ": \u00A7b" + this.list.get(0)));
            list.add(new TextComponent("\u00A79" + PlanetSelectionGuiWindow.PROVIDED_TEXT.getString() + ": \u00A73" + this.list.get(1)));

            screen.renderComponentTooltip(poseStack, list, mouseX, mouseY);
        }
    }
}