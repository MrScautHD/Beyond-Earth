package net.mrscauthd.beyond_earth.skyrenderers;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ICloudRenderHandler;
import net.minecraftforge.client.ISkyRenderHandler;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.skyrenderers.helper.StarHelper;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class MoonSky extends DimensionSpecialEffects {

    private static final ResourceLocation SUN = new ResourceLocation(BeyondEarth.MODID, "textures/sky/no_a_sun.png");
    private static final ResourceLocation EARTH = new ResourceLocation(BeyondEarth.MODID, "textures/sky/earth.png");
    private static final ResourceLocation EARTH_LIGHT = new ResourceLocation(BeyondEarth.MODID, "textures/sky/earth_light.png");
    //private final VertexBuffer starBuffer = StarHelper.createStars(0.1F, 6000, 13000, 190, 160, -1);

    public MoonSky(float p_108866_, boolean p_108867_, SkyType p_108868_, boolean p_108869_, boolean p_108870_) {
        super(p_108866_, p_108867_, p_108868_, p_108869_, p_108870_);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 p_108878_, float p_108879_) {
        return p_108878_;
    }

    @Override
    public boolean isFoggyAt(int p_108874_, int p_108875_) {
        return false;
    }

    @Nullable
    @Override
    public ICloudRenderHandler getCloudRenderHandler() {
        return new ICloudRenderHandler() {
            @Override
            public void render(int ticks, float partialTick, PoseStack poseStack, ClientLevel level, Minecraft minecraft, double camX, double camY, double camZ) {

            }
        };
    }

    @Nullable
    @Override
    public ISkyRenderHandler getSkyRenderHandler() {
        return new ISkyRenderHandler() {
            @Override
            public void render(int ticks, float partialTick, PoseStack poseStack, ClientLevel level, Minecraft minecraft) {
                Matrix4f projectionMatrix = RenderSystem.getProjectionMatrix();
                Camera camera = minecraft.gameRenderer.getMainCamera();
                Vec3 cameraPos = camera.getPosition();
                double x = cameraPos.x();
                double y = cameraPos.y();
                double z = cameraPos.z();
                boolean flag = minecraft.level.effects().isFoggyAt(Mth.floor(x), Mth.floor(y)) || minecraft.gui.getBossOverlay().shouldCreateWorldFog();

                if (!flag) {
                    FogType fogtype = camera.getFluidInCamera();
                    if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !minecraft.levelRenderer.doesMobEffectBlockSky(camera)) {
                        RenderSystem.disableTexture();
                        Vec3 vec3 = minecraft.level.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), partialTick);
                        float f = (float)vec3.x;
                        float f1 = (float)vec3.y;
                        float f2 = (float)vec3.z;
                        FogRenderer.levelFogColor();
                        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
                        RenderSystem.depthMask(false);
                        RenderSystem.setShaderColor(f, f1, f2, 1.0F);
                        ShaderInstance shaderinstance = RenderSystem.getShader();
                        minecraft.levelRenderer.skyBuffer.bind();
                        minecraft.levelRenderer.skyBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
                        VertexBuffer.unbind();
                        RenderSystem.enableBlend();
                        RenderSystem.defaultBlendFunc();

                        float[] afloat = minecraft.level.effects().getSunriseColor(minecraft.level.getTimeOfDay(partialTick), partialTick);
                        if (afloat != null) {
                            RenderSystem.setShader(GameRenderer::getPositionColorShader);
                            RenderSystem.disableTexture();
                            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                            poseStack.pushPose();
                            poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                            float f3 = Mth.sin(minecraft.level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
                            poseStack.mulPose(Vector3f.ZP.rotationDegrees(f3));
                            poseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
                            float f4 = afloat[0];
                            float f5 = afloat[1];
                            float f6 = afloat[2];
                            Matrix4f matrix4f = poseStack.last().pose();
                            bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
                            int i = 16;

                            for(int j = 0; j <= 16; ++j) {
                                float f7 = (float)j * ((float)Math.PI * 2F) / 16.0F;
                                float f8 = Mth.sin(f7);
                                float f9 = Mth.cos(f7);
                                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
                            }

                            BufferUploader.drawWithShader(bufferbuilder.end());
                            poseStack.popPose();
                        }

                        RenderSystem.enableTexture();
                        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                        poseStack.pushPose();
                        float f11 = 1.0F - minecraft.level.getRainLevel(partialTick);
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);
                        poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
                        poseStack.mulPose(Vector3f.XP.rotationDegrees(minecraft.level.getTimeOfDay(partialTick) * 360.0F));
                        Matrix4f matrix4f1 = poseStack.last().pose();
                        float f12 = 30.0F;
                        RenderSystem.setShader(GameRenderer::getPositionTexShader);

                        RenderSystem.setShaderTexture(0, SUN);
                        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0.0F, 0.0F).endVertex();
                        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1.0F, 0.0F).endVertex();
                        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1.0F, 1.0F).endVertex();
                        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0.0F, 1.0F).endVertex();
                        BufferUploader.drawWithShader(bufferbuilder.end());
                        f12 = 20.0F;

                        RenderSystem.setShaderTexture(0, EARTH);
                        int k = minecraft.level.getMoonPhase();
                        int l = k % 4;
                        int i1 = k / 4 % 2;
                        float f13 = (float)(l + 0) / 4.0F;
                        float f14 = (float)(i1 + 0) / 2.0F;
                        float f15 = (float)(l + 1) / 4.0F;
                        float f16 = (float)(i1 + 1) / 2.0F;
                        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f15, f16).endVertex();
                        bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f16).endVertex();
                        bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f14).endVertex();
                        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f15, f14).endVertex();
                        BufferUploader.drawWithShader(bufferbuilder.end());
                        RenderSystem.disableTexture();

                        /*
                        RenderSystem.setShaderColor(0.8F, 0.8F, 0.8F, 0.8F);
                        FogRenderer.setupNoFog();
                        MoonSky.this.starBuffer.bind();
                        MoonSky.this.starBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, GameRenderer.getPositionColorShader());
                        VertexBuffer.unbind();*/

                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                        RenderSystem.disableBlend();
                        poseStack.popPose();
                        RenderSystem.disableTexture();

                        RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);
                        double d0 = minecraft.player.getEyePosition(partialTick).y - minecraft.level.getLevelData().getHorizonHeight(minecraft.level);
                        if (d0 < 0.0D) {
                            poseStack.pushPose();
                            poseStack.translate(0.0D, 12.0D, 0.0D);
                            minecraft.levelRenderer.darkBuffer.bind();
                            minecraft.levelRenderer.darkBuffer.drawWithShader(poseStack.last().pose(), projectionMatrix, shaderinstance);
                            VertexBuffer.unbind();
                            poseStack.popPose();
                        }

                        if (minecraft.level.effects().hasGround()) {
                            RenderSystem.setShaderColor(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F, 1.0F);
                        } else {
                            RenderSystem.setShaderColor(f, f1, f2, 1.0F);
                        }

                        RenderSystem.enableTexture();
                        RenderSystem.depthMask(true);
                    }
                }
            }
        };
    }
}
