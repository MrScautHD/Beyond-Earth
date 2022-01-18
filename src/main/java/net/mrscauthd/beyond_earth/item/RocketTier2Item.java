package net.mrscauthd.beyond_earth.item;

import net.minecraft.world.entity.EntityType;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.entity.RocketAbstractEntity;
import net.mrscauthd.beyond_earth.entity.RocketTier2Entity;

public class RocketTier2Item extends RocketAbstractItem {

	public RocketTier2Item(Properties properties) {
		super(properties);
	}

	@Override
	public EntityType<? extends RocketAbstractEntity> getRocketEntityType() {
		return ModInit.TIER_2_ROCKET.get();
	}

	@Override
	public int getRocketTier() {
		return 2;
	}

	@Override
	public int getFuelBucketsOfFull() {
		return RocketTier2Entity.FUEL_BUCKETS;
	}

	@Override
	public int getFuelAmountPerBucket() {
		return 100;
	}
}
