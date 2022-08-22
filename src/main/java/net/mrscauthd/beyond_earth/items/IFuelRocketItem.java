package net.mrscauthd.beyond_earth.items;

import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.entities.IFuelRocketEntity;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.gauge.IGaugeValue;

public abstract class IFuelRocketItem extends IRocketItem implements IFuelVehicleItem {

	public static final String fuelTag = BeyondEarthMod.MODID + ":fuel";
	public static final String bucketTag = BeyondEarthMod.MODID + ":buckets";

	public IFuelRocketItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);

		IGaugeValue fuelGauge = this.getFuelGauge(itemstack);
		list.add(GaugeTextHelper.buildBlockTooltip(GaugeTextHelper.getPercentText(fuelGauge)));
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
		super.fillItemCategory(tab, list);
		if (this.allowdedIn(tab)) {
			ItemStack itemStack = new ItemStack(this);
			this.setFuel(itemStack, this.getFuelCapacity(itemStack));
			list.add(itemStack);
		}
	}

	@Override
	protected void onRocketPlaced(IRocketEntity rocket, ItemStack itemStack) {
		super.onRocketPlaced(rocket, itemStack);

		if (rocket instanceof IFuelRocketEntity fuelRocket) {
			fuelRocket.setFuel(this.getFuel(itemStack));
			fuelRocket.setBucket(this.getBucket(itemStack));
		}

	}

	@Override
	public int getFuel(ItemStack itemStack) {
		return itemStack.getOrCreateTag().getInt(fuelTag);
	}

	@Override
	public void setFuel(ItemStack itemStack, int fuel) {
		itemStack.getOrCreateTag().putInt(fuelTag, Mth.clamp(fuel, 0, this.getFuelCapacity(itemStack)));
	}

	@Override
	public int getFuelCapacity(ItemStack itemStack) {
		return this.getRequiredFuelBuckets(itemStack) * this.getFuelOfBucket(itemStack);
	}

	public abstract int getRequiredFuelBuckets(ItemStack itemStack);

	public abstract int getFuelOfBucket(ItemStack itemStack);

	public int getBucket(ItemStack itemStack) {
		return itemStack.getOrCreateTag().getInt(bucketTag);
	}

}
