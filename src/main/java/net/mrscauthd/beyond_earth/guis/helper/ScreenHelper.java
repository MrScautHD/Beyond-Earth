package net.mrscauthd.beyond_earth.guis.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;

public class ScreenHelper {

    /** USE IT TO DRAW WITH FLOAT AND NOT INT */
    public static class renderWithFloat {

        public static void blit(PoseStack p_93201_, float p_93202_, float p_93203_, float p_93204_, float p_93205_, float p_93206_, TextureAtlasSprite p_93207_) {
            innerBlit(p_93201_.last().pose(), p_93202_, p_93202_ + p_93205_, p_93203_, p_93203_ + p_93206_, p_93204_, p_93207_.getU0(), p_93207_.getU1(), p_93207_.getV0(), p_93207_.getV1());
        }

        public void blit(PoseStack p_93229_, float p_93230_, float p_93231_, float blitOffset, float p_93232_, float p_93233_, float p_93234_, float p_93235_) {
            blit(p_93229_, p_93230_, p_93231_, blitOffset, p_93232_, p_93233_, p_93234_, p_93235_, 256, 256);
        }

        public static void blit(PoseStack p_93144_, float p_93145_, float p_93146_, float p_93147_, float p_93148_, float p_93149_, float p_93150_, float p_93151_, float p_93152_, float p_93153_) {
            innerBlit(p_93144_, p_93145_, p_93145_ + p_93150_, p_93146_, p_93146_ + p_93151_, p_93147_, p_93150_, p_93151_, p_93148_, p_93149_, p_93152_, p_93153_);
        }

        public static void blit(PoseStack p_93161_, float p_93162_, float p_93163_, float p_93164_, float p_93165_, float p_93166_, float p_93167_, float p_93168_, float p_93169_, float p_93170_, float p_93171_) {
            innerBlit(p_93161_, p_93162_, p_93162_ + p_93164_, p_93163_, p_93163_ + p_93165_, 0, p_93168_, p_93169_, p_93166_, p_93167_, p_93170_, p_93171_);
        }

        public static void blit(PoseStack p_93134_, float p_93135_, float p_93136_, float p_93137_, float p_93138_, float p_93139_, float p_93140_, float p_93141_, float p_93142_) {
            blit(p_93134_, p_93135_, p_93136_, p_93139_, p_93140_, p_93137_, p_93138_, p_93139_, p_93140_, p_93141_, p_93142_);
        }

        private static void innerBlit(PoseStack p_93188_, float p_93189_, float p_93190_, float p_93191_, float p_93192_, float p_93193_, float p_93194_, float p_93195_, float p_93196_, float p_93197_, float p_93198_, float p_93199_) {
            innerBlit(p_93188_.last().pose(), p_93189_, p_93190_, p_93191_, p_93192_, p_93193_, (p_93196_ + 0.0F) / (float) p_93198_, (p_93196_ + (float) p_93194_) / (float) p_93198_, (p_93197_ + 0.0F) / (float) p_93199_, (p_93197_ + (float) p_93195_) / (float) p_93199_);
        }

        private static void innerBlit(Matrix4f p_93113_, float p_93114_, float p_93115_, float p_93116_, float p_93117_, float p_93118_, float p_93119_, float p_93120_, float p_93121_, float p_93122_) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder.vertex(p_93113_, (float) p_93114_, (float) p_93117_, (float) p_93118_).uv(p_93119_, p_93122_).endVertex();
            bufferbuilder.vertex(p_93113_, (float) p_93115_, (float) p_93117_, (float) p_93118_).uv(p_93120_, p_93122_).endVertex();
            bufferbuilder.vertex(p_93113_, (float) p_93115_, (float) p_93116_, (float) p_93118_).uv(p_93120_, p_93121_).endVertex();
            bufferbuilder.vertex(p_93113_, (float) p_93114_, (float) p_93116_, (float) p_93118_).uv(p_93119_, p_93121_).endVertex();
            BufferUploader.drawWithShader(bufferbuilder.end());
        }
    }

    public static class renderFluid {

        /** USE IT TO DRAW FLUID */
        public static void drawFluid(PoseStack poseStack, FluidStack fluidStack, int leftPos, int topPos, int width, int height, int xOffset, int yOffset) {
            if (fluidStack.getFluid() == null) {
                return;
            }

            Minecraft mc = Minecraft.getInstance();
            TextureAtlasSprite sprite = mc.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStack.getFluid().getAttributes().getStillTexture());
            RenderSystem.setShaderTexture(0, sprite.atlas().location());
            int color = fluidStack.getFluid().getAttributes().getColor();
            int scale = (int) mc.getWindow().getGuiScale();

            int screenX = mc.getWindow().getScreenWidth();
            int maxX = ((screenX / 2) + xOffset / 2) * scale;

            int screenY = mc.getWindow().getScreenHeight();
            int maxY = ((screenY / 2) + yOffset / 2) * scale;

            System.out.println(maxX + " " + maxY + " " + ((width - 2) * scale) + " " +  ((height - 2) * scale));

            /** CUT IT EXACT */
            RenderSystem.enableScissor(leftPos * scale, maxY, width * scale, ((height - 1) * scale));

            /** SET COLOR */
            RenderSystem.setShaderColor((color >> 16 & 255) / 255.0f, (float) (color >> 8 & 255) / 255.0f, (float) (color & 255) / 255.0f, 1.0f);

            /** RENDER FLUID NOT EXACT */
            for (int f1 = leftPos; f1 < leftPos + width; f1 += 16) {
                for (int f2 = topPos; f2 < topPos + height; f2 += 16) {

                    /** RENDERER */
                    GuiComponent.blit(poseStack, f1, f2, 0, sprite.getWidth(), sprite.getHeight(), sprite);
                }
            }

            RenderSystem.disableScissor();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    /** USE IT TO DRAW VERTICAL */
    public static void drawVertical(PoseStack poseStack, int leftPos, int topPos, int width, int height, double min, double max, ResourceLocation resourceLocation) {
        double ratio = min / max;
        int ratioHeight = (int) Math.ceil(height * ratio);
        int remainHeight = height - ratioHeight;

        RenderSystem.setShaderTexture(0, resourceLocation);
        GuiComponent.blit(poseStack, leftPos, topPos + remainHeight, 0, remainHeight, width, ratioHeight, width, height);
    }

    /** USE IT TO DRAW HORIZONTAL */
    public static void drawHorizontal(PoseStack poseStack, int leftPos, int topPos, int width, int height, double min, double max, ResourceLocation resourceLocation) {
        double ratio = min / max;
        int ratioWidth = (int) Math.ceil(width * ratio);

        RenderSystem.setShaderTexture(0, resourceLocation);
        GuiComponent.blit(poseStack, leftPos, topPos, 0, 0, ratioWidth, height, width, height);
    }

    /** USE IT TO RENDER TEXTURES */
    public static void drawTexture(PoseStack poseStack, int leftPos, int topPos, int width, int height, ResourceLocation texture) {
        RenderSystem.setShaderTexture(0, texture);
        GuiComponent.blit(poseStack, leftPos, topPos, 0, 0, width, height, width, height);
    }

    /** USE IT TO CHECK IF MOUSE IN A AREA */
    public static boolean isInArea(float mouseX, float mouseY, float x, float y, float width, float height) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }
}
