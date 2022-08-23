package net.mrscauthd.beyond_earth.items;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.config.Config;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.entities.RocketTier2Entity;
import net.mrscauthd.beyond_earth.events.ClientEventBusSubscriber;
import net.mrscauthd.beyond_earth.registries.EntitiesRegistry;

public class Tier2RocketItem extends IFuelRocketItem {
	public Tier2RocketItem(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public BlockEntityWithoutLevelRenderer getRenderer() {
		return ClientEventBusSubscriber.ROCKET_TIER_2_ITEM_RENDERER;
	}

	@Override
	public EntityType getEntityType() {
		return EntitiesRegistry.TIER_2_ROCKET.get();
	}

	@Override
	public IRocketEntity getRocket(Level level) {
		return new RocketTier2Entity(EntitiesRegistry.TIER_2_ROCKET.get(), level);
	}

	@Override
	public int getFuelOfBucket(ItemStack itemStack) {
		return RocketTier2Entity.FUEL_OF_BUCKET;
	}

	@Override
	public int getRequiredFuelBuckets(ItemStack itemStack) {
		return Config.ROCKET_TIER_2_FUEL_BUCKETS.get();
	}
}
