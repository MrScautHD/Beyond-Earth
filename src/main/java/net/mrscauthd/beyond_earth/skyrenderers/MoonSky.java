package net.mrscauthd.beyond_earth.skyrenderers;

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
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.skyrenderers.helper.SkyHelper;
import net.mrscauthd.beyond_earth.skyrenderers.helper.StarHelper;
import org.apache.commons.lang3.tuple.Triple;

@OnlyIn(Dist.CLIENT)
public class MoonSky extends DimensionSpecialEffects {

    private static final ResourceLocation SUN = new ResourceLocation(BeyondEarth.MODID, "textures/sky/white_sun.png");
    private static final ResourceLocation EARTH = new ResourceLocation(BeyondEarth.MODID, "textures/sky/earth.png");
    private final VertexBuffer starBuffer = StarHelper.createStars(0.1F, 6000, 13000, 190, 160, -1);

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

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
        return true;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        Minecraft mc = Minecraft.getInstance();

        if (!isFoggy) {
            FogType fogtype = camera.getFluidInCamera();
            if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA && !mc.levelRenderer.doesMobEffectBlockSky(camera)) {

                /** SKY COLOR */
                Vec3 vec3 = mc.level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTick);
                float r = (float) vec3.x;
                float g = (float) vec3.y;
                float b = (float) vec3.z;

                /** DEFAULT VARIABLES */
                BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
                ShaderInstance shaderInstance = RenderSystem.getShader();
                Matrix4f matrix4f = poseStack.last().pose();

                /** SET FOG COLOR */
                FogRenderer.levelFogColor();

                /** ENABLE DEPTH MASK */
                RenderSystem.depthMask(false);

                /** DRAW SKY */
                RenderSystem.setShaderColor(r, g, b, 1.0F);
                SkyHelper.drawSky(mc, matrix4f, projectionMatrix, shaderInstance);

                /** SUN RISE COLOR */
                SkyHelper.setUpSunRiseColor(poseStack, bufferBuilder, partialTick, mc, true);

                /** STARS */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), null));
                RenderSystem.setShaderColor(0.8F, 0.8F, 0.8F, 0.8F);
                SkyHelper.drawStars(starBuffer, matrix4f, projectionMatrix, GameRenderer.getPositionColorShader(), true);

                /** SUN */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanet(SUN, new Vec3(255, 255, 255), bufferBuilder, matrix4f, 30, 100, true);

                /** EARTH */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.XP.rotationDegrees(30), null, null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanetWithLight(EARTH, new Vec3(0, 177, 242), bufferBuilder, matrix4f, 8, 8 * 3, 100, false);

                /** CUT OFF SKY SYSTEM */
                SkyHelper.drawDarkSky(mc, poseStack, projectionMatrix, shaderInstance, partialTick);

                /** SHADER COLOR */
                SkyHelper.setUpShaderColor(mc, r, g, b);

                /** DISABLE DEPTH MASK */
                RenderSystem.depthMask(true);
            }
        }

        return true;
    }
}
