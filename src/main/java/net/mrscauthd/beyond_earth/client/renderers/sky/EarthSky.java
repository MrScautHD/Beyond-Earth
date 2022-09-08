package net.mrscauthd.beyond_earth.client.renderers.sky;

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
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.client.renderers.sky.helper.SkyHelper;
import net.mrscauthd.beyond_earth.client.renderers.sky.helper.StarHelper;
import org.apache.commons.lang3.tuple.Triple;

@OnlyIn(Dist.CLIENT)
public class EarthSky extends DimensionSpecialEffects {

    private final VertexBuffer starBuffer = StarHelper.createStars(0.15F, 190, 160, -1);

    public EarthSky(float p_108866_, boolean p_108867_, SkyType p_108868_, boolean p_108869_, boolean p_108870_) {
        super(p_108866_, p_108867_, p_108868_, p_108869_, p_108870_);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 p_108878_, float p_108879_) {
        return p_108878_.multiply((double)(p_108879_ * 0.94F + 0.06F), (double)(p_108879_ * 0.94F + 0.06F), (double)(p_108879_ * 0.91F + 0.09F));
    }

    @Override
    public boolean isFoggyAt(int p_108874_, int p_108875_) {
        return false;
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        Minecraft mc = Minecraft.getInstance();

        setupFog.run();
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
                SkyHelper.setupSunRiseColor(poseStack, bufferBuilder, partialTick, mc, true);

                /** STARS */
                float rainLevel = 1.0F - mc.level.getRainLevel(partialTick);
                float starLight = mc.level.getStarBrightness(partialTick) * rainLevel;

                if (starLight > 0.0F) {
                    matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), null));
                    RenderSystem.setShaderColor(starLight, starLight, starLight, starLight);
                    SkyHelper.drawStars(starBuffer, matrix4f, projectionMatrix, GameRenderer.getPositionColorShader(), setupFog, true);
                }

                /** SUN */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawSunWithLightAndSatellites(mc, SkyHelper.SUN, new Vec3(247, 202 ,56),  new Vec3(244, 220, 85), new Vec3(210, 210, 210), bufferBuilder, matrix4f, 5, 5 * 4, 1.2F, 15, 30, 30, 30, 2, 100, false, false);

                /** MOON */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), null));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanetWithMoonPhaseAndWithLight(SkyHelper.MOON_PHASE, new Vec3(255, 255, 255), bufferBuilder, matrix4f, 20, 20, 100, mc, true);

                /** CUT OFF SKY SYSTEM */
                SkyHelper.drawDarkSky(mc, poseStack, projectionMatrix, shaderInstance, partialTick);

                /** SHADER COLOR */
                SkyHelper.setupShaderColor(mc, r, g, b);

                /** DISABLE DEPTH MASK */
                RenderSystem.depthMask(true);
            }
        }

        return true;
    }
}
