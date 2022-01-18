package net.mrscauthd.beyond_earth.item;

import net.minecraft.world.entity.EntityType;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.entity.RocketAbstractEntity;
import net.mrscauthd.beyond_earth.entity.RocketTier4Entity;

public class RocketTier4Item extends RocketAbstractItem {

	public RocketTier4Item(Properties properties) {
		super(properties);
	}

	@Override
	public EntityType<? extends RocketAbstractEntity> getRocketEntityType() {
		return ModInit.TIER_4_ROCKET.get();
	}

	@Override
	public int getRocketTier() {
		return 4;
	}

	@Override
	public int getFuelBucketsOfFull() {
		return RocketTier4Entity.FUEL_BUCKETS;
	}

	@Override
	public int getFuelAmountPerBucket() {
		return 100;
	}
}
