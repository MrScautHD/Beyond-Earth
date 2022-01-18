package net.mrscauthd.beyond_earth.item;

import net.minecraft.world.entity.EntityType;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.entity.RocketAbstractEntity;
import net.mrscauthd.beyond_earth.entity.RocketTier3Entity;

public class RocketTier3Item extends RocketAbstractItem {

	public RocketTier3Item(Properties properties) {
		super(properties);
	}

	@Override
	public EntityType<? extends RocketAbstractEntity> getRocketEntityType() {
		return ModInit.TIER_3_ROCKET.get();
	}

	@Override
	public int getRocketTier() {
		return 3;
	}

	@Override
	public int getFuelBucketsOfFull() {
		return RocketTier3Entity.FUEL_BUCKETS;
	}

	@Override
	public int getFuelAmountPerBucket() {
		return 100;
	}
}
