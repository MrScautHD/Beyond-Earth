package net.mrscauthd.beyond_earth.client.registries;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.renderers.sky.EarthSky;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, value = Dist.CLIENT)
public class VanillaSkyRendererRegistry {

    public static final DimensionSpecialEffects EARTH_EFFECT = new EarthSky(192.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);

    @SubscribeEvent
    public static void overrideVanillaEffects(LevelEvent.Load event) {
        if (event.getLevel() instanceof ClientLevel clientLevel) {
            if (clientLevel.dimension() == Level.OVERWORLD) {
                clientLevel.effects = EARTH_EFFECT;
            }
        }
    }
}
