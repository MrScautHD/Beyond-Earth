package net.mrscauthd.beyond_earth.skyrenderers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
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
public class EarthOrbitSky extends DimensionSpecialEffects {

    private static final ResourceLocation SUN = new ResourceLocation(BeyondEarth.MODID, "textures/sky/white_sun.png");
    private static final ResourceLocation EARTH = new ResourceLocation(BeyondEarth.MODID, "textures/sky/earth.png");
    private static final ResourceLocation MOON_PHASE = new ResourceLocation(BeyondEarth.MODID, "textures/sky/moon_phases.png");

    private final VertexBuffer starBuffer = StarHelper.createStars(0.1F, 6000, 13000, 190, 160, -1);

    public EarthOrbitSky(float p_108866_, boolean p_108867_, SkyType p_108868_, boolean p_108869_, boolean p_108870_) {
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
    public float[] getSunriseColor(float p_108872_, float p_108873_) {
        return null;
    }

    @Override
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
        return true;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        Minecraft mc = Minecraft.getInstance();
        Vec3 cameraPos = camera.getPosition();
        double x = cameraPos.x();
        double y = cameraPos.y();
        double z = cameraPos.z();

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

                /** STARS */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), null));
                RenderSystem.setShaderColor(0.8F, 0.8F, 0.8F, 0.8F);
                SkyHelper.drawStars(starBuffer, matrix4f, projectionMatrix, GameRenderer.getPositionColorShader(), true);

                /** SUN */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanet(SUN, new Vec3(255, 255, 255), bufferBuilder, matrix4f, 30, 100, true);

                /** MOON */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanetWithMoonPhaseAndWithLight(MOON_PHASE, new Vec3(255, 255, 255), bufferBuilder, matrix4f, 20, 20, 100, mc, false);

                /** EARTH */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.XP.rotationDegrees(180), null, null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                float posScale = -3000.0F + (float) y * 6F;
                float scale = 50 * (0.2F - posScale / 10000.0F);
                float yScale = Math.max(scale, 4.0F);

                SkyHelper.drawPlanetWithLight(EARTH, new Vec3(0, 177, 242), bufferBuilder, matrix4f, yScale, yScale * 3, 30, false);

                /** SHADER COLOR */
                SkyHelper.setUpShaderColor(mc, r, g, b);

                /** DISABLE DEPTH MASK */
                RenderSystem.depthMask(true);
            }
        }
        return true;
    }
}
