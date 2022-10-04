package net.mrscauthd.beyond_earth.client.screens.helper;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImageButtonPlacer extends Button {
    private ResourceLocation buttonTexture;
    private ResourceLocation hoverButtonTexture;

    private static final Component EMPTY = Component.literal("");

    /**
     * USE ROW 0 FOR (NO ROW SYSTEM), USE ROW 1 FOR (CATEGORIES, PLANETS), USE ROW 2
     * FOR (ORBITS), USE ROW 3 FOR (SPACE STATIONS)
     */
    public int row;

    private final int xTexStart;
    private final int yTexStart;

    private final int yDiffText;

    private final int textureWidth;
    private final int textureHeight;

    public ImageButtonPlacer(int xIn, int yIn, int row, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn,
            int yDiffTextIn, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, int p_i51135_9_,
            int p_i51135_10_, Button.OnPress onPressIn) {
        this(xIn, yIn, row, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, false, null, null, buttonTexture,
                hoverButtonTexture, p_i51135_9_, p_i51135_10_, onPressIn);
    }

    public ImageButtonPlacer(int xIn, int yIn, int row, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn,
            int yDiffTextIn, boolean rocketCondition, Types type, List<String> list, ResourceLocation buttonTexture,
            ResourceLocation hoverButtonTexture, int textureWidth, int textureHeight, Button.OnPress onPressIn) {
        this(xIn, yIn, row, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, rocketCondition, type, list,
                buttonTexture, hoverButtonTexture, textureWidth, textureHeight, onPressIn, EMPTY);
    }

    public ImageButtonPlacer(int xIn, int yIn, int row, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn,
            int yDiffTextIn, boolean rocketCondition, Types type, List<String> list, ResourceLocation buttonTexture,
            ResourceLocation hoverButtonTexture, int textureWidth, int textureHeight, Button.OnPress onPressIn,
            Component title) {
        this(xIn, yIn, row, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, rocketCondition, type, list,
                buttonTexture, hoverButtonTexture, textureWidth, textureHeight, onPressIn, Button.NO_TOOLTIP, title);
    }

    public ImageButtonPlacer(int p_i244513_1_, int p_i244513_2_, int row, int p_i244513_3_, int p_i244513_4_,
            int p_i244513_5_, int p_i244513_6_, int p_i244513_7_, boolean rocketCondition, Types type,
            List<String> list, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, int textureWidth,
            int textureHeight, Button.OnPress p_i244513_11_, Button.OnTooltip p_i244513_12_, Component p_i244513_13_) {
        super(p_i244513_1_, p_i244513_2_, p_i244513_3_, p_i244513_4_, p_i244513_13_, p_i244513_11_, p_i244513_12_);
        this.row = row;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.xTexStart = p_i244513_5_;
        this.yTexStart = p_i244513_6_;
        this.yDiffText = p_i244513_7_;
        this.buttonTexture = buttonTexture;
        this.hoverButtonTexture = hoverButtonTexture;
    }

    public void setPosition(int xIn, int yIn) {
        this.x = xIn;
        this.y = yIn;
    }

    /** IF YOU WANT NO ONE RETURN (NULL) */
    public enum Types {

        /** IF YOU USE THIS PUT 1 STRING IN THE LIST (CATEGORY) */
        MILKY_WAY_CATEGORY,

        /** IF YOU USE THIS PUT 2 STRINGS IN THE LIST (CATEGORY, PROVIDED) */
        SOLAR_SYSTEM_CATEGORY,

        /**
         * IF YOU USE THIS PUT 4 STRINGS IN THE LIST (TYPE, GRAVITY, OXYGEN,
         * TEMPERATURE)
         */
        PLANET_CATEGORY,

        /**
         * IF YOU USE THIS PUT 4 STRINGS IN THE LIST (TYPE, GRAVITY, OXYGEN,
         * TEMPERATURE)
         */
        PLANET_SPACE_STATION_CATEGORY
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

        /** TEXTURE MANAGER */
        ResourceLocation texture;

        texture = this.getTypeTexture(this.isHovered, this.buttonTexture, this.hoverButtonTexture);

        /** TEXTURE RENDERER */
        RenderSystem.setShaderTexture(0, texture);
        blit(poseStack, this.x, this.y, (float) this.xTexStart, (float) i, this.width, this.height, this.textureWidth,
                this.textureHeight);

        /** FONT RENDERER */
        Font fontRenderer = minecraft.font;
        int j = getFGColor();
        GuiComponent.drawCenteredString(poseStack, fontRenderer, this.getMessage(), this.x + this.width / 2,
                this.y + (this.height - 8) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);

        RenderSystem.disableDepthTest();
    }

    /** TYPE TEXTURE MANAGER */
    private ResourceLocation getTypeTexture(boolean hover, ResourceLocation buttonTexture,
            ResourceLocation hoverButtonTexture) {
        if (hover) {
            return hoverButtonTexture;
        } else {
            return buttonTexture;
        }
    }
}
