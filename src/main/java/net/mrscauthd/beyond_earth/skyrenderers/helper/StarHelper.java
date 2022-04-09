package net.mrscauthd.beyond_earth.skyrenderers.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;

import java.util.Random;

public class StarHelper {
    public static VertexBuffer createStars(VertexBuffer starBuffer, float scale, int amountFast, int amountFancy, boolean amountDefault) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        if (starBuffer != null) {
            starBuffer.close();
        }

        starBuffer = new VertexBuffer();
        drawStars(bufferbuilder, scale, amountFast, amountFancy, amountDefault);
        bufferbuilder.end();
        starBuffer.upload(bufferbuilder);
        return starBuffer;
    }

    private static void drawStars(BufferBuilder p_109555_, float scale, int amountFast, int amountFancy, boolean amountDefault) {
        Random random = new Random(10842L);
        p_109555_.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);

        GraphicsStatus graphicsMode = Minecraft.getInstance().options.graphicsMode;

        int stars = 1500;

        if (!amountDefault) {
            if (graphicsMode == GraphicsStatus.FANCY || graphicsMode == GraphicsStatus.FABULOUS) {
                stars = amountFancy;
            } else {
                stars = amountFast;
            }
        }

        for(int i = 0; i < stars; ++i) {
            double d0 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d1 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d2 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d3 = (double)(scale + random.nextFloat() * 0.1F);
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d4 < 1.0D && d4 > 0.01D) {
                d4 = 1.0D / Math.sqrt(d4);
                d0 *= d4;
                d1 *= d4;
                d2 *= d4;
                double d5 = d0 * 100.0D;
                double d6 = d1 * 100.0D;
                double d7 = d2 * 100.0D;
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                for(int j = 0; j < 4; ++j) {
                    double d17 = 0.0D;
                    double d18 = (double)((j & 2) - 1) * d3;
                    double d19 = (double)((j + 1 & 2) - 1) * d3;
                    double d20 = 0.0D;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12 + 0.0D * d13;
                    double d24 = 0.0D * d12 - d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    p_109555_.vertex(d5 + d25, d6 + d23, d7 + d26).endVertex();
                }
            }
        }
    }
}
