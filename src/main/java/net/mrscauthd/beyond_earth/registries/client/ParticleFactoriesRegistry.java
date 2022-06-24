package net.mrscauthd.beyond_earth.registries.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.particles.*;
import net.mrscauthd.beyond_earth.registries.ParticlesRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoriesRegistry {

    @SubscribeEvent
    public static void register(ParticleFactoryRegisterEvent event) {
        Minecraft mc = Minecraft.getInstance();
        mc.particleEngine.register(ParticlesRegistry.VENUS_RAIN_PARTICLE.get(), VenusRainParticle.ParticleFactory::new);
        mc.particleEngine.register(ParticlesRegistry.LARGE_FLAME_PARTICLE.get(), LargeFlameParticle.ParticleFactory::new);
        mc.particleEngine.register(ParticlesRegistry.LARGE_SMOKE_PARTICLE.get(), LargeSmokeParticle.ParticleFactory::new);
        mc.particleEngine.register(ParticlesRegistry.SMALL_FLAME_PARTICLE.get(), SmallFlameParticle.ParticleFactory::new);
        mc.particleEngine.register(ParticlesRegistry.SMALL_SMOKE_PARTICLE.get(), SmallSmokeParticle.ParticleFactory::new);
    }
}
