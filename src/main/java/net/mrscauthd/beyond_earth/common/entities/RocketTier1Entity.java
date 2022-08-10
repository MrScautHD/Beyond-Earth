package net.mrscauthd.beyond_earth.common.entities;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.events.forge.SetRocketItemStackEvent;

import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.common.registries.ParticleRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagRegistry;

public class RocketTier1Entity extends IRocketEntity {

	public RocketTier1Entity(EntityType type, Level world) {
		super(type, world);
	}

	@Override
	public double getRocketSpeed() {
		return 0.63;
	}

	@Override
	public int getTier() {
		return 1;
	}

	@Override
	public double getPassengersRidingOffset() {
		return super.getPassengersRidingOffset() - 2.35;
	}

	@Override
	public ItemStack getRocketItem() {
		ItemStack itemStack = new ItemStack(ItemsRegistry.TIER_1_ROCKET_ITEM.get(), 1);
		itemStack.getOrCreateTag().putInt(BeyondEarth.MODID + ":fuel", this.getEntityData().get(FUEL));
		itemStack.getOrCreateTag().putInt(BeyondEarth.MODID + ":buckets", this.getEntityData().get(BUCKETS));
		MinecraftForge.EVENT_BUS.post(new SetRocketItemStackEvent(this, itemStack));

		return itemStack;
	}

	@Override
	public void spawnParticle() {
		Vec3 vec = this.getDeltaMovement();

		if (this.level instanceof ServerLevel) {
			if (this.entityData.get(START_TIMER) == 200) {
				for (ServerPlayer p : ((ServerLevel) this.level).getServer().getPlayerList().getPlayers()) {
					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticleRegistry.LARGE_FLAME_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 2.2, this.getZ() - vec.z, 20, 0.1, 0.1, 0.1, 0.001);
					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticleRegistry.LARGE_SMOKE_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 3.2, this.getZ() - vec.z, 10, 0.1, 0.1, 0.1, 0.04);
				}
			} else {
				for (ServerPlayer p : ((ServerLevel) this.level).getServer().getPlayerList().getPlayers()) {
					((ServerLevel) this.level).sendParticles(p, ParticleTypes.CAMPFIRE_COSY_SMOKE, true, this.getX() - vec.x, this.getY() - vec.y - 0.1, this.getZ() - vec.z, 6, 0.1, 0.1, 0.1, 0.023);
				}
			}
		}
	}

	@Override
	public void fillUpRocket() {
		if (this.getInventory().getStackInSlot(0).getItem() instanceof BucketItem) {
			if (((BucketItem) this.getInventory().getStackInSlot(0).getItem()).getFluid().is(TagRegistry.FLUID_VEHICLE_FUEL_TAG) && this.entityData.get(BUCKETS) != 1) {
				this.getInventory().setStackInSlot(0, new ItemStack(Items.BUCKET));
				this.getEntityData().set(BUCKETS, 1);
			}
		}

		if (this.getEntityData().get(BUCKETS) == 1 && this.getEntityData().get(FUEL) < 3000) {
			this.getEntityData().set(FUEL, this.getEntityData().get(FUEL) + 10);
		}
	}
}