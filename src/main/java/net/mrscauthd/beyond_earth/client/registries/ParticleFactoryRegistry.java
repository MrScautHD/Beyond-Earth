package net.mrscauthd.beyond_earth.client.registries;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.particles.*;
import net.mrscauthd.beyond_earth.common.registries.ParticleRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoryRegistry {

    @SubscribeEvent
    public static void register(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.VENUS_RAIN_PARTICLE.get(), VenusRainParticle.ParticleFactory::new);
        event.registerSpriteSet(ParticleRegistry.LARGE_FLAME_PARTICLE.get(), LargeFlameParticle.ParticleFactory::new);
        event.registerSpriteSet(ParticleRegistry.LARGE_SMOKE_PARTICLE.get(), LargeSmokeParticle.ParticleFactory::new);
        event.registerSpriteSet(ParticleRegistry.SMALL_FLAME_PARTICLE.get(), SmallFlameParticle.ParticleFactory::new);
        event.registerSpriteSet(ParticleRegistry.SMALL_SMOKE_PARTICLE.get(), SmallSmokeParticle.ParticleFactory::new);
    }
}
