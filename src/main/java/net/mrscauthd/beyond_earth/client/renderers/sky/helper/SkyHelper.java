package net.mrscauthd.beyond_earth.client.renderers.sky.helper;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.util.Planets;
import net.mrscauthd.beyond_earth.common.util.Planets.CelestialBody;
import net.mrscauthd.beyond_earth.common.util.Planets.Planet;
import org.apache.commons.lang3.tuple.Triple;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class SkyHelper {

    /** PLANETS */
    public static final ResourceLocation WHITE_SUN = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/white_sun.png");
    public static final ResourceLocation SUN = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/sun.png");
    public static final ResourceLocation MOON_PHASE = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/moon_phases.png");
    public static final ResourceLocation EARTH = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/earth.png");
    public static final ResourceLocation MARS = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/mars.png");
    public static final ResourceLocation PHOBOS = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/phobos.png");
    public static final ResourceLocation DEIMOS = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/deimos.png");

    /** LIGHTS */
    public static final ResourceLocation PLANET_LIGHT = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/planet_light.png");
    public static final ResourceLocation PLANET_PHASE_LIGHT = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/planet/planet_phases_light.png");

    /** RAIN */
    public static final ResourceLocation MARS_DUST = new ResourceLocation(BeyondEarth.MODID,
            "textures/environment/mars_dust.png");
    public static final ResourceLocation SNOW = new ResourceLocation("textures/environment/snow.png");

    private static int alpha = 255;

    public static void drawStars(VertexBuffer vertexBuffer, Matrix4f matrix4f, Matrix4f projectionMatrix,
            ShaderInstance shaderInstance, Runnable setupFog, boolean blend) {
        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        FogRenderer.setupNoFog();
        vertexBuffer.bind();
        vertexBuffer.drawWithShader(matrix4f, projectionMatrix, shaderInstance);
        VertexBuffer.unbind();
        setupFog.run();

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void drawPlanetWithMoonPhaseAndWithLight(ResourceLocation texture, Vec3 lightColor,
            BufferBuilder bufferBuilder, Matrix4f matrix4f, float size, float lightSize, float y, Minecraft mc,
            boolean blend) {
        drawPlanetWithMoonPhaseAndWithLight(texture, lightColor, bufferBuilder, matrix4f, size, lightSize, y, mc, blend,
                mc.level.getMoonPhase());
    }

    public static void drawPlanetWithMoonPhaseAndWithLight(ResourceLocation texture, Vec3 lightColor,
            BufferBuilder bufferBuilder, Matrix4f matrix4f, float size, float lightSize, float y, Minecraft mc,
            boolean blend, int phase) {
        drawPlanetWithMoonPhase(texture, new Vec3(255, 255, 255), bufferBuilder, matrix4f, size, y, mc, blend, phase);
        drawPlanetWithMoonPhase(PLANET_PHASE_LIGHT, lightColor, bufferBuilder, matrix4f, lightSize, y, mc, true, phase);
    }

    public static void drawPlanetWithMoonPhase(ResourceLocation texture, Vec3 color, BufferBuilder bufferBuilder,
            Matrix4f matrix4f, float size, float y, Minecraft mc, boolean blend, int phase) {
        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        int r = (int) color.x();
        int g = (int) color.y();
        int b = (int) color.z();

        RenderSystem.enableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderTexture(0, texture);
        int l = phase % 4;
        int i1 = phase / 4 % 2;
        float f13 = (float) (l) / 4.0F;
        float f14 = (float) (i1) / 2.0F;
        float f15 = (float) (l + 1) / 4.0F;
        float f16 = (float) (i1 + 1) / 2.0F;
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        bufferBuilder.vertex(matrix4f, -size, -y, size).color(r, g, b, 255).uv(f15, f16).endVertex();
        bufferBuilder.vertex(matrix4f, size, -y, size).color(r, g, b, 255).uv(f13, f16).endVertex();
        bufferBuilder.vertex(matrix4f, size, -y, -size).color(r, g, b, 255).uv(f13, f14).endVertex();
        bufferBuilder.vertex(matrix4f, -size, -y, -size).color(r, g, b, 255).uv(f15, f14).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        RenderSystem.disableTexture();

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void drawSunWithLight(ResourceLocation texture, Vec3 sunColor, Vec3 lightColor,
            BufferBuilder bufferBuilder, Matrix4f matrix4f, float size, float lightSize, float y, boolean blend) {
        drawPlanet(texture, sunColor, bufferBuilder, matrix4f, size, y, blend);
        drawPlanet(SkyHelper.PLANET_LIGHT, lightColor, bufferBuilder, matrix4f, lightSize, y, true);
    }

    public static void drawPlanetWithLight(ResourceLocation texture, Vec3 lightColor, BufferBuilder bufferBuilder,
            Matrix4f matrix4f, float size, float lightSize, float y, boolean blend) {
        drawPlanet(texture, new Vec3(255, 255, 255), bufferBuilder, matrix4f, size, y, blend);
        drawPlanet(SkyHelper.PLANET_LIGHT, lightColor, bufferBuilder, matrix4f, lightSize, y, true);
    }

    public static void drawPlanet(ResourceLocation texture, Vec3 color, BufferBuilder bufferBuilder, Matrix4f matrix4f,
            float size, float y, boolean blend) {
        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }
        int r = (int) color.x();
        int g = (int) color.y();
        int b = (int) color.z();
        int a = alpha;

        RenderSystem.enableTexture();
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderTexture(0, texture);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        bufferBuilder.vertex(matrix4f, -size, y, -size).color(r, g, b, a).uv(1.0F, 0.0F).endVertex();
        bufferBuilder.vertex(matrix4f, size, y, -size).color(r, g, b, a).uv(0.0F, 0.0F).endVertex();
        bufferBuilder.vertex(matrix4f, size, y, size).color(r, g, b, a).uv(0.0F, 1.0F).endVertex();
        bufferBuilder.vertex(matrix4f, -size, y, size).color(r, g, b, a).uv(1.0F, 1.0F).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
        RenderSystem.disableTexture();

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void drawParentsPlanetsBeforeParent(PoseStack poseStack, BufferBuilder bufferBuilder, float dayAngle,
            float skyLight, CelestialBody parent, Planet planet, Object2ObjectArrayMap<Planet, float[]> renderLater) {
        if (parent == null)
            return;
        boolean inner = true;

        // Loop over siblings first.
        for (Planet p : planet._parent.children) {
            if (p == planet) {
                inner = false;
                continue;
            }
            float distance = (float) (inner ? planet.orbitRadius / p.orbitRadius : p.orbitRadius / planet.orbitRadius);
            // This is anglular difference between our orbit and the other planet's orbit.
            float phase = p.orbitPhase - planet.orbitPhase;
            float dAngle = 90 * Mth.sin(phase) / distance;
            float angle = dayAngle + (inner ? dAngle : phase);
            float lighting = 3 * dAngle * skyLight / 90;

            boolean render = !inner || phase < 180f;

            if (render) {
                float inclination = (float) (10 * Math.sin(Math.toRadians(phase)));
                Matrix4f matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Axis.YP.rotationDegrees(-90),
                        Axis.XP.rotationDegrees(angle), Axis.ZP.rotationDegrees(inclination)));
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
                alpha = (int) (255 * skyLight);
                SkyHelper.drawPlanetWithLight(p.texture, p.getLightColour(), bufferBuilder, matrix4f, 3, lighting,
                        100 * distance, true);
                alpha = 255;
            } else {
                renderLater.put(p, new float[] { distance, angle, dAngle });
            }
        }

        // Now add things belonging to our parent (this applies if we are a moon)
        if (parent instanceof Planet parent2)
            drawParentsPlanetsBeforeParent(poseStack, bufferBuilder, dayAngle, skyLight, parent2._parent, parent2,
                    renderLater);
    }

    public static void drawPlanetsAndParents(PoseStack poseStack, BufferBuilder bufferBuilder, Camera camera,
            float dayAngle, float skyLight, float worldTime, Planet planet) {
        drawPlanetsAndParents(poseStack, bufferBuilder, camera, dayAngle, skyLight, worldTime, planet, 1, true);
    }

    public static void drawPlanetsAndParents(PoseStack poseStack, BufferBuilder bufferBuilder, Camera camera,
            float dayAngle, float skyLight, float worldTime, Planet planet, int parentDepth, boolean includeMoons) {
        if (planet != null) {
            Object2ObjectArrayMap<Planet, float[]> renderAfterSun = new Object2ObjectArrayMap<>();
            CelestialBody parent = planet._parent;

            if (parent != null && parent instanceof Planet planet2) {
                drawPlanetsAndParents(poseStack, bufferBuilder, camera, dayAngle, skyLight, worldTime, planet2,
                        parentDepth + 1, false);
            }
            // First draw sibling planets which should be in front of the parent
            SkyHelper.drawParentsPlanetsBeforeParent(poseStack, bufferBuilder, dayAngle, skyLight, planet._parent,
                    planet, renderAfterSun);
            float scale = (float) (10 * planet.orbitRadius / Planets.PLANET_ORBIT_SCALE);
            if (parent instanceof Planet) {
                scale = (float) (0.15 * Math.sqrt(parent.radius * Planets.MOON_ORBIT_SCALE / planet.orbitRadius));
            }

            float angle = dayAngle;
            if (planet.tidalLock) {
                Vec3 cameraPos = camera.getPosition();
                double y = 50 * cameraPos.x() / planet.radius;
                angle = (float) (-30 + y) % 360;
            }
            // TODO decide on this based on maybe rotation rate of the planet?
            if (parent instanceof Planet)
                angle += parentDepth * 10;

            // Then draw ours.
            ResourceLocation texture = planet._parent.texture != null ? planet._parent.texture : SkyHelper.WHITE_SUN;
            Matrix4f matrix4f = SkyHelper.setMatrixRot(poseStack,
                    Triple.of(Axis.YP.rotationDegrees(-90), Axis.XP.rotationDegrees(angle), null));

            Vec3 colour = parent.getLightColour();
            if (!(parent instanceof Planet)) {
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawSunWithLight(texture, colour, colour, bufferBuilder, matrix4f, scale, 5 * 4, 100, true);
                SkyHelper.drawParentsPlanetsAfterParent(poseStack, bufferBuilder, dayAngle, skyLight, planet._parent,
                        planet, renderAfterSun);
            } else {
                float lighting = 3 * skyLight / 90;
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                SkyHelper.drawParentsPlanetsAfterParent(poseStack, bufferBuilder, dayAngle, skyLight, planet._parent,
                        planet, renderAfterSun);
                SkyHelper.drawPlanetWithLight(texture, colour, bufferBuilder, matrix4f, scale, lighting, 100, false);
            }

            // Then draw the sibling planets that should be behind the parent
            // Finally draw our moons
            if (includeMoons)
                SkyHelper.drawSatellitesForPlanet(poseStack, bufferBuilder, dayAngle, skyLight, worldTime, planet);
        }
    }

    public static void drawParentsPlanetsAfterParent(PoseStack poseStack, BufferBuilder bufferBuilder, float dayAngle,
            float skyLight, CelestialBody parent, Planet planet, Object2ObjectArrayMap<Planet, float[]> renderLater) {
        // draw planets that are infront of the sun
        for (var pair : renderLater.entrySet()) {
            Planet p = pair.getKey();
            float[] params = pair.getValue();
            float distance = params[0];
            float angle = params[1];
            float inclination = (float) (10 * Math.sin(p.orbitPhase));
            Matrix4f matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Axis.YP.rotationDegrees(-90),
                    Axis.XP.rotationDegrees(angle), Axis.ZP.rotationDegrees(inclination)));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            alpha = (int) (255 * skyLight);
            SkyHelper.drawPlanetWithLight(p.texture, p.getLightColour(), bufferBuilder, matrix4f, 3, 3 * 4,
                    100 * distance, true);
            alpha = 255;
        }
    }

    public static void drawSatellitesForPlanet(PoseStack poseStack, BufferBuilder bufferBuilder, float dayAngle,
            float skyLight, float worldTime, CelestialBody parent) {
        // draw moons
        for (Planet p : parent.children) {
            // Here we have partial ticks, so we can smooth out the orbits.
            // Update the phase to include the partialTick.
            p.orbitPhase = Planets.getRotation(p, worldTime, 1);

            // Now we scale size of the moon based on distance.
            float moonSize = (float) (3.5e3 * p.radius / p.orbitRadius);

            // This is how far away in angle the moon is from the sun
            float phase = dayAngle + p.orbitPhase;
            alpha = (int) (255 * skyLight);

            float inclination = (float) (10 * Math.sin(Math.toRadians(phase)));
            Matrix4f matrix4f = SkyHelper.setMatrixRot(poseStack, Triple.of(Axis.YP.rotationDegrees(-90),
                    Axis.XP.rotationDegrees(phase), Axis.ZP.rotationDegrees(inclination)));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            if (p.phaseTexture != null) {
                phase = phase - dayAngle;
                int moonPhase = ((int) (45 + phase % 360) / 45);
                // Original size was scaled down by 2 assuming no phase, as moon phase model
                // only has the icon taking up the middle.
                moonSize *= 2;
                Minecraft mc = Minecraft.getInstance();
                SkyHelper.drawPlanetWithMoonPhaseAndWithLight(p.phaseTexture, p.getLightColour(), bufferBuilder,
                        matrix4f, moonSize, moonSize, 100, mc, false, moonPhase);
            } else {
                SkyHelper.drawPlanetWithLight(p.texture, p.getLightColour(), bufferBuilder, matrix4f, moonSize, moonSize,
                        100, false);
            }
            alpha = 255;
        }
    }

    public static void drawSunWithLightAndSatellites(Minecraft mc, ResourceLocation texture, Vec3 color,
            Vec3 lightColor, Vec3 satelliteColor, BufferBuilder bufferBuilder, Matrix4f matrix4f, float size,
            float lightSize, float satelliteSize, int satellites, float satelliteSpeed, float xAngle, float yAngle,
            float zAngle, float y, boolean blend, boolean satelliteBlend) {
        drawPlanetWithSatellites(mc, texture, color, satelliteColor, bufferBuilder, matrix4f, size, satelliteSize,
                satellites, satelliteSpeed, xAngle, yAngle, zAngle, y, blend, satelliteBlend);
        drawPlanet(SkyHelper.PLANET_LIGHT, lightColor, bufferBuilder, matrix4f, lightSize, y, true);
    }

    public static void drawPlanetWithLightAndSatellites(Minecraft mc, ResourceLocation texture, Vec3 lightColor,
            Vec3 satelliteColor, BufferBuilder bufferBuilder, Matrix4f matrix4f, float size, float lightSize,
            float satelliteSize, int satellites, float satelliteSpeed, float xAngle, float yAngle, float zAngle,
            float y, boolean blend, boolean satelliteBlend) {
        drawPlanetWithSatellites(mc, texture, new Vec3(255, 255, 255), satelliteColor, bufferBuilder, matrix4f, size,
                satelliteSize, satellites, satelliteSpeed, xAngle, yAngle, zAngle, y, blend, satelliteBlend);
        drawPlanet(SkyHelper.PLANET_LIGHT, lightColor, bufferBuilder, matrix4f, lightSize, y, true);
    }

    public static void drawPlanetWithSatellites(Minecraft mc, ResourceLocation texture, Vec3 color, Vec3 satelliteColor,
            BufferBuilder bufferBuilder, Matrix4f matrix4f, float size, float satelliteSize, int satellites,
            float satelliteSpeed, float xAngle, float yAngle, float zAngle, float y, boolean blend,
            boolean satelliteBlend) {
        float gameTick = (mc.level.getGameTime() + mc.getPartialTick()) / satelliteSpeed;
        float distance = 6.3F / satellites;

        for (int i = 0; i < satellites; i++) {
            float sinTick = (float) Math.sin(gameTick + (distance * i));
            float cosTick = (float) Math.cos(gameTick + (distance * i));
            float yPos = y + cosTick * yAngle;

            if (y < yPos) {
                SkyHelper.drawSatellites(satelliteColor, bufferBuilder, matrix4f, sinTick, cosTick, xAngle, yAngle,
                        zAngle, satelliteSize, y, satelliteBlend);
            }
        }

        SkyHelper.drawPlanet(texture, color, bufferBuilder, matrix4f, size, y, blend);

        for (int i = 0; i < satellites; i++) {
            float sinTick = (float) Math.sin(gameTick + (distance * i));
            float cosTick = (float) Math.cos(gameTick + (distance * i));

            float yPos = y + cosTick * yAngle;

            if (y > yPos) {
                SkyHelper.drawSatellites(satelliteColor, bufferBuilder, matrix4f, sinTick, cosTick, xAngle, yAngle,
                        zAngle, satelliteSize, y, satelliteBlend);
            }
        }
    }

    public static void drawSatellites(Vec3 color, BufferBuilder bufferBuilder, Matrix4f matrix4f, float sinTick,
            float cosTick, float xAngle, float yAngle, float zAngle, float size, float y, boolean blend) {
        if (blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        int r = (int) color.x();
        int g = (int) color.y();
        int b = (int) color.z();

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, sinTick * xAngle - size, y + cosTick * yAngle, cosTick * zAngle - size)
                .color(r, g, b, 255).endVertex();
        bufferBuilder.vertex(matrix4f, sinTick * xAngle + size, y + cosTick * yAngle, cosTick * zAngle - size)
                .color(r, g, b, 255).endVertex();
        bufferBuilder.vertex(matrix4f, sinTick * xAngle + size, y + cosTick * yAngle, cosTick * zAngle + size)
                .color(r, g, b, 255).endVertex();
        bufferBuilder.vertex(matrix4f, sinTick * xAngle - size, y + cosTick * yAngle, cosTick * zAngle + size)
                .color(r, g, b, 255).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void setupSunRiseColor(PoseStack poseStack, BufferBuilder bufferBuilder, float partialTick,
            Minecraft mc, boolean blend) {
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
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            float f3 = Mth.sin(mc.level.getSunAngle(partialTick)) < 0.0F ? 180.0F : 0.0F;
            poseStack.mulPose(Axis.ZP.rotationDegrees(f3));
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
            float f4 = afloat[0];
            float f5 = afloat[1];
            float f6 = afloat[2];
            Matrix4f matrix4f = poseStack.last().pose();
            bufferBuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            bufferBuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f5, f6, afloat[3]).endVertex();
            int i = 16;

            for (int j = 0; j <= i; ++j) {
                float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                float f8 = Mth.sin(f7);
                float f9 = Mth.cos(f7);
                bufferBuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3])
                        .color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }

            BufferUploader.drawWithShader(bufferBuilder.end());
            poseStack.popPose();
        }

        if (blend) {
            RenderSystem.disableBlend();
        }
    }

    public static void setupShaderColor(Minecraft mc, float r, float g, float b) {
        if (mc.level.effects().hasGround()) {
            RenderSystem.setShaderColor(r * 0.2F + 0.04F, g * 0.2F + 0.04F, b * 0.6F + 0.1F, 1.0F);
        } else {
            RenderSystem.setShaderColor(r, g, b, 1.0F);
        }
    }

    public static void drawSky(Minecraft mc, Matrix4f matrix4f, Matrix4f projectionMatrix,
                               ShaderInstance shaderInstance) {
        mc.levelRenderer.skyBuffer.bind();
        mc.levelRenderer.skyBuffer.drawWithShader(matrix4f, projectionMatrix, shaderInstance);
        VertexBuffer.unbind();
    }

    public static void drawDarkSky(Minecraft mc, PoseStack poseStack, Matrix4f projectionMatrix,
            ShaderInstance shaderInstance, float partialTick) {
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

    public static void setupRainSize(float[] rainSizeX, float[] rainSizeZ) {
        for (int i = 0; i < 32; ++i) {
            for (int j = 0; j < 32; ++j) {
                float f9 = (float) (j - 16);
                float f1 = (float) (i - 16);
                float f2 = Mth.sqrt(f9 * f9 + f1 * f1);
                rainSizeX[i << 5 | j] = -f1 / f2;
                rainSizeZ[i << 5 | j] = f9 / f2;
            }
        }
    }

    public static Matrix4f setMatrixRot(PoseStack poseStack,
            Triple<Quaternionf, Quaternionf, Quaternionf> quaternionTriple) {
        poseStack.pushPose();

        Quaternionf left = quaternionTriple.getLeft();
        Quaternionf middle = quaternionTriple.getMiddle();
        Quaternionf right = quaternionTriple.getRight();

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
