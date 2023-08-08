package net.mrscauthd.beyond_earth.client.renderers.sky;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.client.renderers.sky.helper.SkyHelper;
import net.mrscauthd.beyond_earth.client.renderers.sky.helper.StarHelper;
import net.mrscauthd.beyond_earth.common.util.Planets;
import net.mrscauthd.beyond_earth.common.util.Planets.Planet;
import org.apache.commons.lang3.tuple.Triple;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class OrbitSky extends DimensionSpecialEffects {

    private final VertexBuffer starBuffer = StarHelper.createStars(0.1F, 6000, 13000, 190, 160, -1);

    private Planet planet;

    public OrbitSky(Planet planet) {
        super(Float.NaN, false, SkyType.NONE, false, false);
        this.planet = planet;
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
    public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX,
            double camY, double camZ, Matrix4f projectionMatrix) {
        return true;
    }

    @Override
    public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
        return true;
    }

    @Override
    public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture,
            double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera,
            Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        Minecraft mc = Minecraft.getInstance();
        Vec3 cameraPos = camera.getPosition();
        double y = cameraPos.y();

        setupFog.run();
        if (!isFoggy) {
            FogType fogtype = camera.getFluidInCamera();
            if (fogtype != FogType.POWDER_SNOW && fogtype != FogType.LAVA
                    && !mc.levelRenderer.doesMobEffectBlockSky(camera)) {

                /** SKY COLOR */
                Vec3 vec3 = mc.level.getSkyColor(mc.gameRenderer.getMainCamera().getPosition(), partialTick);
                float r = (float) vec3.x;
                float g = (float) vec3.y;
                float b = (float) vec3.z;

                /** DEFAULT VARIABLES */
                BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
                ShaderInstance shaderInstance = RenderSystem.getShader();
                Matrix4f matrix4f = poseStack.last().pose();
                float dayTime = level.getTimeOfDay(partialTick);
                float dayAngle = dayTime * 360f % 360f;

                /** SET FOG COLOR */
                FogRenderer.levelFogColor();

                /** ENABLE DEPTH MASK */
                RenderSystem.depthMask(false);

                /** DRAW SKY */
                RenderSystem.setShaderColor(r, g, b, 1.0F);
                SkyHelper.drawSky(mc, matrix4f, projectionMatrix, shaderInstance);

                /** STARS */
                matrix4f = SkyHelper.setMatrixRot(poseStack,
                        Triple.of(Axis.YP.rotationDegrees(-90), Axis.XP.rotationDegrees(dayTime), null));
                RenderSystem.setShaderColor(0.8F, 0.8F, 0.8F, 0.8F);
                SkyHelper.drawStars(starBuffer, matrix4f, projectionMatrix, GameRenderer.getPositionColorShader(),
                        setupFog, true);

                /** SUN */
                matrix4f = SkyHelper.setMatrixRot(poseStack,
                        Triple.of(Axis.YP.rotationDegrees(-90), Axis.XP.rotationDegrees(dayTime), null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanet(SkyHelper.WHITE_SUN, new Vec3(255, 255, 255), bufferBuilder, matrix4f, 30, 100,
                        true);

                /** MOON */
                planet = Planets.getLocationForOrbit(level);

                if (planet != null) {
                    // draw planets
                    if (planet._parent != null) {
                        boolean inner = true;
                        for (Planet p : planet._parent.children) {
                            if (p == planet) {
                                inner = false;
                                continue;
                            }
                            float distance = (float) (inner ? planet.orbitRadius / p.orbitRadius
                                    : p.orbitRadius / planet.orbitRadius);
                            float phase = p.orbitPhase - planet.orbitPhase;
                            float dAngle = 90 * Mth.sin(phase) / distance;
                            float angle = dayAngle + (inner ? dAngle : phase);

                            matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Axis.YP.rotationDegrees(-90),
                                    Axis.XP.rotationDegrees(angle), null));
                            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                            SkyHelper.drawPlanetWithLight(p.texture, new Vec3(232, 219, 176), bufferBuilder, matrix4f,
                                    3, 3 * 4, 100 * distance, false);
                        }
                    }
                    // draw moons
                    for (Planet p : planet.moons) {
                        // Here we have partial ticks, so we can smooth out the orbits.
                        // Update the phase to include the partialTick.
                        p.orbitPhase = Planets.getRotation(p, dayTime, 1);

                        matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Axis.YP.rotationDegrees(-90),
                                Axis.XP.rotationDegrees(p.orbitPhase), Axis.ZP.rotationDegrees(1)));
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                        SkyHelper.drawPlanetWithLight(p.texture, new Vec3(232, 219, 176), bufferBuilder, matrix4f, 3,
                                3 * 4, 100, false);
                    }
                }

                /** PLANET BELOW */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Axis.XP.rotationDegrees(180), null, null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                float posScale = -3000.0F + (float) y * 6F;
                float scale = 50 * (0.2F - posScale / 10000.0F);
                float yScale = Math.max(scale, 4.0F);

                SkyHelper.drawPlanetWithLight(planet.texture, new Vec3(0, 177, 242), bufferBuilder, matrix4f, yScale,
                        yScale * 3, 30, false);

                /** SHADER COLOR */
                SkyHelper.setupShaderColor(mc, r, g, b);

                /** DISABLE DEPTH MASK */
                RenderSystem.depthMask(true);
            }
        }
        return true;
    }
}
