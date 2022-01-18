package net.mrscauthd.beyond_earth.entity;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.item.RocketAbstractItem;

public class RocketTier2Entity extends RocketAbstractEntity {

	public static final int FUEL_BUCKETS = 3;
	public static final double ROCKET_SPEED = 0.70D;

	public RocketTier2Entity(EntityType<? extends RocketTier2Entity> type, Level world) {
		super(type, world);
	}

	@Override
	public double getPassengersRidingOffset() {
		return super.getPassengersRidingOffset() - 2.50D;
	}

	@Override
	public double getRocketSpeed() {
		return ROCKET_SPEED;
	}

	@Override
	public RocketAbstractItem getRocketItem() {
		return ModInit.TIER_2_ROCKET_ITEM.get();
	}

	@Override
	protected void sendParticlesFlying(Vec3 vec, ServerLevel serverlevel, ServerPlayer p) {
		super.sendParticlesFlying(vec, serverlevel, p);
		serverlevel.sendParticles(p, (ParticleOptions) ModInit.LARGE_FLAME_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 1.6, this.getZ() - vec.z, 20, 0.1, 0.1, 0.1, 0.001);
		serverlevel.sendParticles(p, (ParticleOptions) ModInit.LARGE_SMOKE_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 2.6, this.getZ() - vec.z, 10, 0.1, 0.1, 0.1, 0.04);
	}
}