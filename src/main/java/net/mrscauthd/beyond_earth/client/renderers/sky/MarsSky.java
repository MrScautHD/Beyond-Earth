package net.mrscauthd.beyond_earth.client.renderers.sky;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.mrscauthd.beyond_earth.client.renderers.sky.helper.SkyHelper;
import net.mrscauthd.beyond_earth.client.renderers.sky.helper.StarHelper;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class MarsSky extends DimensionSpecialEffects {

    private final VertexBuffer starBuffer = StarHelper.createStars(0.1F, 190, 160, -1);
    private final float[] sunriseCol = new float[4];
    private final float[] rainSizeX = new float[1024];
    private final float[] rainSizeZ = new float[1024];

    public MarsSky(float p_108866_, boolean p_108867_, SkyType p_108868_, boolean p_108869_, boolean p_108870_) {
        super(p_108866_, p_108867_, p_108868_, p_108869_, p_108870_);
        SkyHelper.setupRainSize(this.rainSizeX, this.rainSizeZ);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 p_108878_, float p_108879_) {
        return p_108878_.multiply(p_108879_ * 0.94F + 0.06F, p_108879_ * 0.94F + 0.06F, p_108879_ * 0.91F + 0.09F);
    }

    @Nullable
    @Override
    public float[] getSunriseColor(float p_108872_, float p_108873_) {
        float f1 = Mth.cos(p_108872_ * ((float)Math.PI * 2F)) - 0.0F;

        if (f1 >= -0.4F && f1 <= 0.4F) {
            float f3 = (f1 - -0.0F) / 0.4F * 0.5F + 0.5F;
            float f4 = 1.0F - (1.0F - Mth.sin(f3 * (float)Math.PI)) * 0.99F;
            f4 *= f4;
            this.sunriseCol[0] = f3 * 0.3F;
            this.sunriseCol[1] = f3 * f3 * 0.7F + 0.55F;
            this.sunriseCol[2] = f3 * f3 * 0.0F + 0.8F;
            this.sunriseCol[3] = f4;
            return this.sunriseCol;
        } else {
            return null;
        }
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
                SkyHelper.drawSunWithLight(SkyHelper.SUN, new Vec3(109, 131, 255), new Vec3(109, 131, 255), bufferBuilder, matrix4f, 5, 5 * 4, 100, true);

                /** PHOBOS */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(-40), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), Vector3f.ZP.rotationDegrees(30)));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanetWithLight(SkyHelper.PHOBOS, new Vec3(232, 219, 176), bufferBuilder, matrix4f, 3, 3 * 4, 100, false);

                /** DEIMOS */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), Vector3f.ZP.rotationDegrees(10)));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanetWithLight(SkyHelper.DEIMOS, new Vec3(232, 219, 176), bufferBuilder, matrix4f, 2, 2 * 4, 100, false);

                /** EARTH */
                matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Vector3f.YP.rotationDegrees(90), Vector3f.XP.rotationDegrees(level.getTimeOfDay(partialTick) * 360.0F), Vector3f.ZP.rotationDegrees(-30)));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawPlanetWithLight(SkyHelper.EARTH, new Vec3(0, 177, 242), bufferBuilder, matrix4f, 1, 1 * 4, 100, false);

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

    @Override
    public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
        Minecraft mc = Minecraft.getInstance();
        float f = mc.level.getRainLevel(partialTick);

        if (!(f <= 0.0F)) {
            lightTexture.turnOnLightLayer();
            int i = Mth.floor(camX);
            int j = Mth.floor(camY);
            int k = Mth.floor(camZ);
            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferbuilder = tesselator.getBuilder();
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            int l = 5;
            if (Minecraft.useFancyGraphics()) {
                l = 10;
            }

            RenderSystem.depthMask(Minecraft.useShaderTransparency());
            int i1 = -1;
            float f1 = (float) ticks + partialTick;
            RenderSystem.setShader(GameRenderer::getParticleShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int j1 = k - l; j1 <= k + l; ++j1) {
                for(int k1 = i - l; k1 <= i + l; ++k1) {
                    int l1 = (j1 - k + 16) * 32 + k1 - i + 16;
                    double d0 = (double)this.rainSizeX[l1] * 0.5D;
                    double d1 = (double)this.rainSizeZ[l1] * 0.5D;
                    blockpos$mutableblockpos.set((double)k1, camY, (double)j1);
                    Biome biome = level.getBiome(blockpos$mutableblockpos).value();
                    if (biome.getPrecipitation() != Biome.Precipitation.NONE) {
                        int i2 = level.getHeight(Heightmap.Types.MOTION_BLOCKING, k1, j1);
                        int j2 = j - l;
                        int k2 = j + l;
                        if (j2 < i2) {
                            j2 = i2;
                        }

                        if (k2 < i2) {
                            k2 = i2;
                        }

                        int l2 = i2;
                        if (i2 < j) {
                            l2 = j;
                        }

                        if (j2 != k2) {
                            RandomSource randomsource = RandomSource.create((long)(k1 * k1 * 3121 + k1 * 45238971 ^ j1 * j1 * 418711 + j1 * 13761));
                            blockpos$mutableblockpos.set(k1, j2, j1);

                            if (biome.warmEnoughToRain(blockpos$mutableblockpos)) {
                                if (i1 != 0) {
                                    if (i1 >= 0) {
                                        tesselator.end();
                                    }

                                    i1 = 0;
                                    RenderSystem.setShaderTexture(0, SkyHelper.MARS_DUST);
                                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
                                }

                                float f5 = -((float) (ticks & 511) + partialTick) / 512.0F;
                                float f6 = (float) (randomsource.nextDouble() + (double) f1 * 0.08D * (double) ((float) randomsource.nextGaussian()));
                                float f7 = (float) (randomsource.nextDouble() + (double) (f1 * (float) randomsource.nextGaussian()) * 0.008D);
                                double d3 = (double) k1 + 0.5D - camX;
                                double d5 = (double) j1 + 0.5D - camZ;
                                float f8 = (float) Math.sqrt(d3 * d3 + d5 * d5) / (float) l;
                                float f9 = ((1.0F - f8 * f8) * 0.3F + 0.5F) * f;
                                blockpos$mutableblockpos.set(k1, l2, j1);
                                int k3 = LevelRenderer.getLightColor(level, blockpos$mutableblockpos);
                                int l3 = k3 >> 16 & '\uffff';
                                int i4 = k3 & '\uffff';
                                int j4 = (l3 * 3 + 240) / 4;
                                int k4 = (i4 * 3 + 240) / 4;
                                bufferbuilder.vertex((double) k1 - camX - d0 + 0.5D, (double) k2 - camY, (double) j1 - camZ - d1 + 0.5D).uv(0.0F + f6, (float) j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                bufferbuilder.vertex((double) k1 - camX + d0 + 0.5D, (double) k2 - camY, (double) j1 - camZ + d1 + 0.5D).uv(1.0F + f6, (float) j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                bufferbuilder.vertex((double) k1 - camX + d0 + 0.5D, (double) j2 - camY, (double) j1 - camZ + d1 + 0.5D).uv(1.0F + f6, (float) k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                bufferbuilder.vertex((double) k1 - camX - d0 + 0.5D, (double) j2 - camY, (double) j1 - camZ - d1 + 0.5D).uv(0.0F + f6, (float) k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                            } else {
                                if (i1 != 1) {
                                    if (i1 >= 0) {
                                        tesselator.end();
                                    }

                                    i1 = 1;
                                    RenderSystem.setShaderTexture(0, SkyHelper.SNOW);
                                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
                                }

                                float f5 = -((float)(ticks & 511) + partialTick) / 512.0F;
                                float f6 = (float)(randomsource.nextDouble() + (double)f1 * 0.01D * (double)((float)randomsource.nextGaussian()));
                                float f7 = (float)(randomsource.nextDouble() + (double)(f1 * (float)randomsource.nextGaussian()) * 0.001D);
                                double d3 = (double)k1 + 0.5D - camX;
                                double d5 = (double)j1 + 0.5D - camZ;
                                float f8 = (float)Math.sqrt(d3 * d3 + d5 * d5) / (float)l;
                                float f9 = ((1.0F - f8 * f8) * 0.3F + 0.5F) * f;
                                blockpos$mutableblockpos.set(k1, l2, j1);
                                int k3 = LevelRenderer.getLightColor(level, blockpos$mutableblockpos);
                                int l3 = k3 >> 16 & '\uffff';
                                int i4 = k3 & '\uffff';
                                int j4 = (l3 * 3 + 240) / 4;
                                int k4 = (i4 * 3 + 240) / 4;
                                bufferbuilder.vertex((double)k1 - camX - d0 + 0.5D, (double)k2 - camY, (double)j1 - camZ - d1 + 0.5D).uv(0.0F + f6, (float)j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                bufferbuilder.vertex((double)k1 - camX + d0 + 0.5D, (double)k2 - camY, (double)j1 - camZ + d1 + 0.5D).uv(1.0F + f6, (float)j2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                bufferbuilder.vertex((double)k1 - camX + d0 + 0.5D, (double)j2 - camY, (double)j1 - camZ + d1 + 0.5D).uv(1.0F + f6, (float)k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                                bufferbuilder.vertex((double)k1 - camX - d0 + 0.5D, (double)j2 - camY, (double)j1 - camZ - d1 + 0.5D).uv(0.0F + f6, (float)k2 * 0.25F + f5 + f7).color(1.0F, 1.0F, 1.0F, f9).uv2(k4, j4).endVertex();
                            }
                        }
                    }
                }
            }

            if (i1 >= 0) {
                tesselator.end();
            }

            RenderSystem.enableCull();
            RenderSystem.disableBlend();
            lightTexture.turnOffLightLayer();
        }

        return true;
    }

    @Override
    public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
        return true;
    }
}