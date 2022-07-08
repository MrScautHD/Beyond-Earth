package net.mrscauthd.beyond_earth.items;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.entities.RocketTier2Entity;
import net.mrscauthd.beyond_earth.events.ClientEventBusSubscriber;
import net.mrscauthd.beyond_earth.registries.EntitiesRegistry;

import net.minecraft.world.item.Item.Properties;

public class Tier2RocketItem extends IRocketItem {
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
    public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        super.fillItemCategory(p_41391_, p_41392_);
        if (this.allowedIn(p_41391_)) {
            ItemStack itemStack = new ItemStack(this);
            itemStack.getOrCreateTag().putInt(FUEL_TAG, 3000);
            p_41392_.add(itemStack);
        }
    }
}
