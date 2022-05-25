package net.mrscauthd.beyond_earth.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.entities.RocketTier1Entity;
import net.mrscauthd.beyond_earth.entities.renderer.rockettier1.RocketTier1ItemRenderer;
import net.mrscauthd.beyond_earth.itemgroups.ItemGroups;
import net.mrscauthd.beyond_earth.registries.EntitiesRegistry;

public class Tier1RocketItem extends IRocketItem implements FilledAltVehicleItem {

    @OnlyIn(Dist.CLIENT)
    public static final RocketTier1ItemRenderer ITEM_RENDERER = new RocketTier1ItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());

    public Tier1RocketItem(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityWithoutLevelRenderer getRenderer() {
        return ITEM_RENDERER;
    }

    @Override
    public int getRocketHigh() {
        return 4;
    }

    @Override
    public IRocketEntity getRocket(Level level) {
        return new RocketTier1Entity(EntitiesRegistry.TIER_1_ROCKET.get(), level);
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
