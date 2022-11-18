package net.mrscauthd.beyond_earth.common.items;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.RocketTier2Entity;
import net.mrscauthd.beyond_earth.common.registries.EntityRegistry;
import net.mrscauthd.beyond_earth.client.registries.ItemRendererRegistry;

public class Tier2RocketItem extends IRocketItem {
    public Tier2RocketItem(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public BlockEntityWithoutLevelRenderer getRenderer() {
        return ItemRendererRegistry.ROCKET_TIER_2_ITEM_RENDERER;
    }

    @Override
    public EntityType<? extends IRocketEntity> getEntityType() {
        return EntityRegistry.TIER_2_ROCKET.get();
    }

    @Override
    public IRocketEntity getRocket(Level level) {
        return new RocketTier2Entity(EntityRegistry.TIER_2_ROCKET.get(), level);
    }

    @Override
    public int getFuelBuckets() {
        return Config.ROCKET_TIER_2_FUEL_BUCKETS.get();
    }
}
