package net.mrscauthd.beyond_earth.items;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.entities.RocketTier3Entity;
import net.mrscauthd.beyond_earth.itemgroups.ItemGroups;
import net.mrscauthd.beyond_earth.registries.EntitiesRegistry;

public class Tier3RocketItem extends IRocketItem implements FilledAltVehicleItem {

    public Tier3RocketItem(Properties properties) {
        super(properties);
    }

	@Override
	public IRocketEntity createRocketEntity(UseOnContext context) {
		return new RocketTier3Entity(EntitiesRegistry.TIER_3_ROCKET.get(), context.getLevel());
	}

    @Override
    public void fillItemCategoryAlt(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        if (this.allowdedIn(p_41391_)) {
            ItemStack itemStack = new ItemStack(this);
            itemStack.getOrCreateTag().putInt(fuelTag, 300);
            p_41392_.add(itemStack);
        }
    }

    @Override
    public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        if (p_41391_ != ItemGroups.tab_normal) {
            super.fillItemCategory(p_41391_, p_41392_);
        }
    }

    @Override
    public void itemCategoryAlt(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        if (this.allowdedIn(p_41391_)) {
            p_41392_.add(new ItemStack(this));
        }
    }
}
