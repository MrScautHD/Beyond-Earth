package net.mrscauthd.beyond_earth.skyrenderers;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class GlacioSky {

    private static final ResourceLocation DIM_RENDER_INFO = new ResourceLocation(BeyondEarthMod.MODID, "glacio");

    @Nullable
    public static VertexBuffer starBuffer;

    private static final ResourceLocation SUN_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/blue_sun.png");
    private static final ResourceLocation VICINUS_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/vicinus.png");
    private static final ResourceLocation VICINUS_LIGHT_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/vicinus_light.png");

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void clientSetup(FMLClientSetupEvent event) {
        DimensionSpecialEffects.EFFECTS.put(DIM_RENDER_INFO, new DimensionSpecialEffects(192, true, DimensionSpecialEffects.SkyType.NORMAL, false, false) {

            @Override
            public Vec3 getBrightnessDependentFogColor(Vec3 p_108878_, float p_108879_) {
                return p_108878_.multiply(p_108879_ * 0.94 + 0.06, p_108879_ * 0.94 + 0.06, p_108879_ * 0.91 + 0.09);
            }

            @Override
            public boolean isFoggyAt(int p_108874_, int p_108875_) {
                return false;
            }

            @Override
            public ISkyRenderHandler getSkyRenderHandler() {
                return new ISkyRenderHandler() {
                    @Override
                    public void render(int ticks, float p_181412_, PoseStack p_181410_, ClientLevel level, Minecraft minecraft) {
                        Camera camera = minecraft.gameRenderer.getMainCamera();
                        FogType fogtype = camera.getFluidInCamera();

                        if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA) {
                            Entity entity = camera.getEntity();
                            if (entity instanceof LivingEntity) {
                                LivingEntity livingentity = (LivingEntity) entity;
                                if (livingentity.hasEffect(MobEffects.BLINDNESS)) {
                                    return;
                                }
                            }

                            Matrix4f matrix4f = RenderSystem.getProjectionMatrix();
                            Matrix4f starMatrix4f = RenderSystem.getProjectionMatrix();
                            RenderSystem.disableTexture();
                            Vec3 vec3 = level.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), p_181412_);
                            float f = (float) vec3.x;
                            float f1 = (float) vec3.y;
                            float f2 = (float) vec3.z;
                            FogRenderer.levelFogColor();
                            BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
                            RenderSystem.depthMask(false);
                            RenderSystem.setShaderColor(f, f1, f2, 1.0F);
                            ShaderInstance shaderinstance = RenderSystem.getShader();
                            minecraft.levelRenderer.skyBuffer.drawWithShader(p_181410_.last().pose(), matrix4f, shaderinstance);

                            /** ENABLE BLEND SYSTEM */
                            RenderSystem.enableBlend();
                            RenderSystem.defaultBlendFunc();

                            /** COLOR SYSTEM */
                            float[] afloat = level.effects().getSunriseColor(level.getTimeOfDay(p_181412_), p_181412_);
                            if (afloat != null) {
                                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                                RenderSystem.disableTexture();
                                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                                p_181410_.pushPose();
                                p_181410_.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                                float f3 = Mth.sin(level.getSunAngle(p_181412_)) < 0.0F ? 180.0F : 0.0F;
                                p_181410_.mulPose(Vector3f.ZP.rotationDegrees(f3));
                                p_181410_.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
                                float f4 = afloat[0];
                                float f5 = afloat[1];
                                float f6 = afloat[2];
                                matrix4f = p_181410_.last().pose();
                                bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
                                bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();

                                for (int j = 0; j <= 16; ++j) {
                                    float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                                    float f8 = Mth.sin(f7);
                                    float f9 = Mth.cos(f7);
                                    bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
                                }

                                bufferbuilder.end();
                                BufferUploader.end(bufferbuilder);
                                p_181410_.popPose();
                            }

                            /** STAR ROT */
                            p_181410_.pushPose();
                            p_181410_.mulPose(Vector3f.YP.rotationDegrees(0.0F));
                            p_181410_.mulPose(Vector3f.ZP.rotationDegrees(level.getTimeOfDay(p_181412_) * 360.0F));
                            p_181410_.mulPose(Vector3f.XP.rotationDegrees(-30.0F));

                            /** STAR */
                            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                            FogRenderer.setupNoFog();
                            minecraft.levelRenderer.starBuffer.drawWithShader(p_181410_.last().pose(), starMatrix4f, GameRenderer.getPositionShader());
                            p_181410_.popPose();

                            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

                            p_181410_.pushPose();
                            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                            RenderSystem.enableTexture();

                            /** VICINUS ROT */
                            p_181410_.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
                            p_181410_.mulPose(Vector3f.XP.rotationDegrees(180.0F));
                            p_181410_.mulPose(Vector3f.ZP.rotationDegrees(30.0F));
                            Matrix4f matrix4f1 = p_181410_.last().pose();

                            RenderSystem.setShader(GameRenderer::getPositionTexShader);

                            /** VICINUS LIGHT */
                            RenderSystem.setShaderTexture(0, VICINUS_LIGHT_TEXTURE);
                            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                            bufferbuilder.vertex(matrix4f1, -48.0F, -100.0F, 48.0F).uv(0.0F, 0.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, 48.0F, -100.0F, 48.0F).uv(1.0F, 0.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, 48.0F, -100.0F, -48.0F).uv(1.0F, 1.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, -48.0F, -100.0F, -48.0F).uv(0.0F, 1.0F).endVertex();
                            bufferbuilder.end();
                            BufferUploader.end(bufferbuilder);

                            /** VICINUS */
                            RenderSystem.disableBlend();

                            RenderSystem.setShaderTexture(0, VICINUS_TEXTURE);
                            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                            bufferbuilder.vertex(matrix4f1, -16.0F, -100.0F, 16.0F).uv(0.0F, 0.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, 16.0F, -100.0F, 16.0F).uv(1.0F, 0.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, 16.0F, -100.0F, -16.0F).uv(1.0F, 1.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, -16.0F, -100.0F, -16.0F).uv(0.0F, 1.0F).endVertex();
                            bufferbuilder.end();
                            BufferUploader.end(bufferbuilder);

                            RenderSystem.enableBlend();

                            /** DEFAULT ROT */
                            p_181410_.mulPose(Vector3f.YP.rotationDegrees(0.0F));
                            p_181410_.mulPose(Vector3f.ZP.rotationDegrees(-30.0F));
                            p_181410_.mulPose(Vector3f.XP.rotationDegrees(level.getTimeOfDay(p_181412_) * 360.0F));
                            matrix4f1 = p_181410_.last().pose();

                            /** SUN */
                            float f12 = 30.0F;

                            RenderSystem.setShaderTexture(0, SUN_TEXTURE);
                            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                            bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(0.0F, 0.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(1.0F, 0.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(1.0F, 1.0F).endVertex();
                            bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(0.0F, 1.0F).endVertex();
                            bufferbuilder.end();
                            BufferUploader.end(bufferbuilder);

                            RenderSystem.disableTexture();
                            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                            RenderSystem.disableBlend();
                            p_181410_.popPose();

                            /** CUT AWAY SYSTEM */
                            RenderSystem.disableTexture();
                            RenderSystem.setShaderColor(0.0F, 0.0F, 0.0F, 1.0F);

                            double d0 = minecraft.player.getEyePosition(p_181412_).y - level.getLevelData().getHorizonHeight(level);

                            if (d0 < 0.0D) {
                                p_181410_.pushPose();
                                p_181410_.translate(0.0D, 12.0D, 0.0D);
                                minecraft.levelRenderer.darkBuffer.drawWithShader(p_181410_.last().pose(), matrix4f, shaderinstance);
                                p_181410_.popPose();
                            }

                            if (level.effects().hasGround()) {
                                RenderSystem.setShaderColor(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F, 1.0F);
                            } else {
                                RenderSystem.setShaderColor(f, f1, f2, 1.0F);
                            }

                            RenderSystem.enableTexture();
                            RenderSystem.depthMask(true);
                        }
                    }
                };
            }
        });
    }
}
