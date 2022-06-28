package net.mrscauthd.beyond_earth.skyrenderers.helper;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.apache.commons.lang3.tuple.Triple;

public class SkyHelper {

    public static void drawStars(VertexBuffer vertexBuffer, Matrix4f matrix4f, Matrix4f projectionMatrix, boolean blend) {
        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        FogRenderer.setupNoFog();
        vertexBuffer.bind();
        vertexBuffer.drawWithShader(matrix4f, projectionMatrix, GameRenderer.getPositionColorShader());
        VertexBuffer.unbind();

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void drawPlanetWithMoonPhaseAndWithLight(ResourceLocation texture, ResourceLocation lightTexture, BufferBuilder bufferBuilder, Matrix4f matrix4f, float scale, float lightScale, float y, Minecraft mc, boolean blend) {
        drawPlanetWithMoonPhase(texture, bufferBuilder, matrix4f, scale, y, mc, blend);
        drawPlanetWithMoonPhase(lightTexture, bufferBuilder, matrix4f, lightScale, y, mc, true);
    }

    public static void drawPlanetWithMoonPhase(ResourceLocation texture, BufferBuilder bufferBuilder, Matrix4f matrix4f, float scale, float y, Minecraft mc, boolean blend) {
        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        RenderSystem.enableTexture();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        int k = mc.level.getMoonPhase();
        int l = k % 4;
        int i1 = k / 4 % 2;
        float f13 = (float)(l + 0) / 4.0F;
        float f14 = (float)(i1 + 0) / 2.0F;
        float f15 = (float)(l + 1) / 4.0F;
        float f16 = (float)(i1 + 1) / 2.0F;
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f, -scale, -y, scale).uv(f15, f16).endVertex();
        bufferBuilder.vertex(matrix4f, scale, -y, scale).uv(f13, f16).endVertex();
        bufferBuilder.vertex(matrix4f, scale, -y, -scale).uv(f13, f14).endVertex();
        bufferBuilder.vertex(matrix4f, -scale, -y, -scale).uv(f15, f14).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        RenderSystem.disableTexture();

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void drawPlanetWithLight(ResourceLocation texture, ResourceLocation lightTexture, BufferBuilder bufferBuilder, Matrix4f matrix4f, float scale, float lightScale, float y, boolean blend) {
        drawPlanet(texture, bufferBuilder, matrix4f, scale, y, blend);
        drawPlanet(lightTexture, bufferBuilder, matrix4f, lightScale, y, true);
    }

    public static void drawPlanet(ResourceLocation texture, BufferBuilder bufferBuilder, Matrix4f matrix4f, float scale, float y, boolean blend) {
        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        RenderSystem.enableTexture();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f, -scale, y, -scale).uv(0.0F, 0.0F).endVertex();
        bufferBuilder.vertex(matrix4f, scale, y, -scale).uv(1.0F, 0.0F).endVertex();
        bufferBuilder.vertex(matrix4f, scale, y, scale).uv(1.0F, 1.0F).endVertex();
        bufferBuilder.vertex(matrix4f, -scale, y, scale).uv(0.0F, 1.0F).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        RenderSystem.disableTexture();

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void setUpSunRiseColor(PoseStack poseStack, BufferBuilder bufferBuilder, float partialTick, Minecraft mc, boolean blend) {
        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
        }

        float[] afloat = mc.level.effects().getSunriseColor(mc.level.getTimeOfDay(partialTick), partialTick);
        if (afloat != null) {
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.disableTexture();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.pushPose();
            poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            float f3 = Mth.sin(mc.level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(f3));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            float f4 = afloat[0];
            float f5 = afloat[1];
            float f6 = afloat[2];
            Matrix4f matrix4f = poseStack.last().pose();
            bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            bufferBuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
            int i = 16;

            for(int j = 0; j <= i; ++j) {
                float f7 = (float)j * ((float)Math.PI * 2F) / 16.0F;
                float f8 = Mth.sin(f7);
                float f9 = Mth.cos(f7);
                bufferBuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            BufferUploader.drawWithShader(bufferBuilder.end());
            poseStack.popPose();
        }

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void setUpShaderColor(Minecraft mc, float r, float g, float b) {
        if (mc.level.effects().hasGround()) {
            RenderSystem.setShaderColor(r * 0.2F + 0.04F, g * 0.2F + 0.04F, b * 0.6F + 0.1F, 1.0F);
        } else {
            RenderSystem.setShaderColor(r, g, b, 1.0F);
        }
    }

    public static void drawSky(Minecraft mc, Matrix4f matrix4f, Matrix4f projectionMatrix, ShaderInstance shaderInstance) {
        mc.levelRenderer.skyBuffer.bind();
        mc.levelRenderer.skyBuffer.drawWithShader(matrix4f, projectionMatrix, shaderInstance);
        VertexBuffer.unbind();
    }

    public static void drawDarkSky(Minecraft mc, PoseStack poseStack, Matrix4f projectionMatrix, ShaderInstance shaderInstance, float partialTick) {
        RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
        double d0 = mc.player.getEyePosition(partialTick).y - mc.level.getLevelData().getHorizonHeight(mc.level);
        if (d0 < 0.0D) {
            poseStack.pushPose();
            poseStack.translate(0.0D, 12.0D, 0.0D);
            mc.levelRenderer.darkBuffer.bind();
            mc.levelRenderer.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderInstance);
            VertexBuffer.unbind();
            poseStack.popPose();
        }
    }

    public static Matrix4f setMatrixRot(PoseStack poseStack, Triple<Quaternion, Quaternion, Quaternion> quaternionTriple) {
        poseStack.pushPose();

        Quaternion left = quaternionTriple.getLeft();
        Quaternion middle = quaternionTriple.getMiddle();
        Quaternion right = quaternionTriple.getRight();

        if (left != null) {
            poseStack.mulPose(quaternionTriple.getLeft());
        }

        if (middle != null) {
            poseStack.mulPose(quaternionTriple.getMiddle());
        }

        if (right != null) {
            poseStack.mulPose(quaternionTriple.getRight());
        }

        Matrix4f matrix4f = poseStack.last().pose();
        poseStack.popPose();

        return matrix4f;
    }
}
