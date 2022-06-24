package net.mrscauthd.beyond_earth.registries.client;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.skyrenderers.MoonSky;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SkyRenderersRegistry {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        event.enqueueWork(()-> {
            DimensionSpecialEffects.EFFECTS.put(new ResourceLocation(BeyondEarth.MODID, "moon"), new MoonSky(0, true, DimensionSpecialEffects.SkyType.NORMAL, false, false));
        });
    }
}
