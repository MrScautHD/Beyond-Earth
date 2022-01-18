package net.mrscauthd.beyond_earth.entity;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.item.RocketAbstractItem;

public class RocketTier4Entity extends RocketAbstractEntity {

	public static final int FUEL_BUCKETS = 3;
	public static final double ROCKET_SPEED = 0.9;

	public RocketTier4Entity(EntityType<? extends RocketTier4Entity> type, Level world) {
		super(type, world);
	}

	@Override
	public double getPassengersRidingOffset() {
		return super.getPassengersRidingOffset() - 2.85D;
	}

	@Override
	public double getRocketSpeed() {
		return ROCKET_SPEED;
	}

	@Override
	public RocketAbstractItem getRocketItem() {
		return ModInit.TIER_3_ROCKET_ITEM.get();
	}

	@Override
	protected void sendParticlesFlying(Vec3 vec, ServerLevel serverlevel, ServerPlayer p) {
		super.sendParticlesFlying(vec, serverlevel, p);
		float f2 = Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * (0.7F + 0.21F * (float) 1);
		float f3 = Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * (0.7F + 0.21F * (float) 1);

		serverlevel.sendParticles(p, (ParticleOptions) ModInit.LARGE_FLAME_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 1.9, this.getZ() - vec.z, 20, 0.1, 0.1, 0.1, 0.001);
		serverlevel.sendParticles(p, (ParticleOptions) ModInit.LARGE_SMOKE_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 2.9, this.getZ() - vec.z, 10, 0.1, 0.1, 0.1, 0.04);

		serverlevel.sendParticles(p, (ParticleOptions) ModInit.SMALL_FLAME_PARTICLE.get(), true, this.getX() + f2, this.getY() - vec.y - 1.3, this.getZ() + f3, 20, 0.1, 0.1, 0.1, 0.001);
		serverlevel.sendParticles(p, (ParticleOptions) ModInit.SMALL_SMOKE_PARTICLE.get(), true, this.getX() + f2, this.getY() - vec.y - 2.3, this.getZ() + f3, 10, 0.1, 0.1, 0.1, 0.04);

		serverlevel.sendParticles(p, (ParticleOptions) ModInit.SMALL_FLAME_PARTICLE.get(), true, this.getX() - f2, this.getY() - vec.y - 1.3, this.getZ() - f3, 20, 0.1, 0.1, 0.1, 0.001);
		serverlevel.sendParticles(p, (ParticleOptions) ModInit.SMALL_SMOKE_PARTICLE.get(), true, this.getX() - f2, this.getY() - vec.y - 2.3, this.getZ() - f3, 10, 0.1, 0.1, 0.1, 0.04);
	}
}