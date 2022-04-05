package net.mrscauthd.beyond_earth.registries;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

public class ParticlesRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BeyondEarthMod.MODID);

    /** PARTICLES */
    public static final RegistryObject<ParticleType<SimpleParticleType>> VENUS_RAIN_PARTICLE = PARTICLES.register("venus_rain", () -> new SimpleParticleType(true));
    public static final RegistryObject<ParticleType<SimpleParticleType>> LARGE_FLAME_PARTICLE = PARTICLES.register("large_flame", () -> new SimpleParticleType(true));
    public static final RegistryObject<ParticleType<SimpleParticleType>> LARGE_SMOKE_PARTICLE = PARTICLES.register("large_smoke", () -> new SimpleParticleType(true));
    public static final RegistryObject<ParticleType<SimpleParticleType>> SMALL_FLAME_PARTICLE = PARTICLES.register("small_flame", () -> new SimpleParticleType(true));
    public static final RegistryObject<ParticleType<SimpleParticleType>> SMALL_SMOKE_PARTICLE = PARTICLES.register("small_smoke", () -> new SimpleParticleType(true));
}
