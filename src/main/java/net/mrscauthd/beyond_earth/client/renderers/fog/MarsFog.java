package net.mrscauthd.beyond_earth.client.renderers.fog;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, value = Dist.CLIENT)
public class MarsFog {

    @SubscribeEvent
    public static void setupFog(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.level.dimension() == LevelRegistry.MARS) {
            float fogLevel = mc.level.getRainLevel((float) event.getPartialTick()) - 0.02F;
            float renderDistance = mc.gameRenderer.getRenderDistance();

            if (fogLevel > 0.0F) {
                float farPlaneDistance = Math.max(renderDistance - (fogLevel * renderDistance), 10);

                event.setNearPlaneDistance(event.getMode() == FogRenderer.FogMode.FOG_SKY ? 0.0F : farPlaneDistance * 0.9F);
                event.setFarPlaneDistance(farPlaneDistance + 0.8F);
                event.setFogShape(FogShape.CYLINDER);
                event.setCanceled(true);
            }
        }
    }
}
