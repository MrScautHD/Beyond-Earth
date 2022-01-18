package net.mrscauthd.beyond_earth.item;

import net.minecraft.world.entity.EntityType;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.entity.RocketAbstractEntity;
import net.mrscauthd.beyond_earth.entity.RocketTier1Entity;

public class RocketTier1Item extends RocketAbstractItem {

	public RocketTier1Item(Properties properties) {
		super(properties);
	}

	@Override
	public EntityType<? extends RocketAbstractEntity> getRocketEntityType() {
		return ModInit.TIER_1_ROCKET.get();
	}

	@Override
	public int getRocketTier() {
		return 1;
	}

	@Override
	public int getFuelBucketsOfFull() {
		return RocketTier1Entity.FUEL_BUCKETS;
	}

	@Override
	public int getFuelAmountPerBucket() {
		return 300;
	}
}
