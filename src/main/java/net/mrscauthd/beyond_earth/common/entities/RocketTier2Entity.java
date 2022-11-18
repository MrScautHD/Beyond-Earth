package net.mrscauthd.beyond_earth.common.entities;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.events.forge.SetRocketItemStackEvent;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.common.registries.ParticleRegistry;

public class RocketTier2Entity extends IRocketEntity {

	public static final int DEFAULT_FUEL_BUCKETS = 3;

	public RocketTier2Entity(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Override
	public double getRocketSpeed() {
		return 0.7;
	}

	@Override
	public int getTier() {
		return 2;
	}

	@Override
	public int getBucketsOfFull() {
		return Config.ROCKET_TIER_2_FUEL_BUCKETS.get();
	}

	@Override
	public double getPassengersRidingOffset() {
		return super.getPassengersRidingOffset() - 2.5;
	}

	@Override
	public ItemStack getRocketItem() {
		ItemStack itemStack = new ItemStack(ItemsRegistry.TIER_2_ROCKET_ITEM.get(), 1);
		itemStack.getOrCreateTag().putInt(BeyondEarth.MODID + ":fuel", this.getEntityData().get(FUEL));
		MinecraftForge.EVENT_BUS.post(new SetRocketItemStackEvent(this, itemStack));

		return itemStack;
	}

	@Override
	public void spawnParticle() {
		Vec3 vec = this.getDeltaMovement();

		if (this.level instanceof ServerLevel) {
			if (this.entityData.get(START_TIMER) == 200) {
				for (ServerPlayer p : ((ServerLevel) this.level).getServer().getPlayerList().getPlayers()) {
					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticleRegistry.LARGE_FLAME_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 2.4, this.getZ() - vec.z, 20, 0.1, 0.1, 0.1, 0.001);
					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticleRegistry.LARGE_SMOKE_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 3.4, this.getZ() - vec.z, 10, 0.1, 0.1, 0.1, 0.04);
				}
			} else {
				for (ServerPlayer p : ((ServerLevel) this.level).getServer().getPlayerList().getPlayers()) {
					((ServerLevel) this.level).sendParticles(p, ParticleTypes.CAMPFIRE_COSY_SMOKE, true, this.getX() - vec.x, this.getY() - vec.y - 0.1, this.getZ() - vec.z, 6, 0.1, 0.1, 0.1, 0.023);
				}
			}
		}
	}
}