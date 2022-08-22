package net.mrscauthd.beyond_earth.entities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.fluids.FluidUtil2;
import net.mrscauthd.beyond_earth.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.gauge.GaugeValueSimple;
import net.mrscauthd.beyond_earth.gauge.IGaugeValue;
import net.mrscauthd.beyond_earth.registries.TagsRegistry;

public abstract class IFuelRocketEntity extends IRocketEntity implements IFuelVehicleEntity {

	public IFuelRocketEntity(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
	}

	@Override
	public void tick() {
		super.tick();

		this.fillUpRocket();
	}

	public void fillUpRocket() {
		if (Methods.tagCheck(FluidUtil2.findBucketFluid(this.getInventory().getStackInSlot(0).getItem()), TagsRegistry.FLUID_VEHICLE_FUEL_TAG) && this.canPutFuelMuchAsBucket()) {

			if (this.getFuel() == this.getBuckets() * this.getFuelOfBucket()) {
				this.getInventory().setStackInSlot(0, new ItemStack(Items.BUCKET));
				this.putFuelMuchAsBucket();
			}
		}

		int fillUpInto = this.getBuckets() * this.getFuelOfBucket();

		if (this.getFuel() < fillUpInto) {
			this.setFuel(Math.min(this.getFuel() + this.getFillUpSpeed(), fillUpInto));
		}
	}

	@Override
	public boolean canStart() {
		return this.getFuel() >= this.getFuelCapacity();
	}

	@Override
	public void sendCantStartMessage(Player player) {
		Methods.noFuelMessage(player);
	}

	@Override
	public List<IGaugeValue> getGaugeValues() {
		List<IGaugeValue> list = new ArrayList<>();
		int fuel = this.getFuel();
		int capacity = this.getFuelCapacity();
		list.add(new GaugeValueSimple(GaugeValueHelper.FUEL_NAME, (int) Math.round(fuel / (capacity / 100.0D)), 100, (Component) null, "%").color(GaugeValueHelper.FUEL_COLOR));
		return list;
	}

	@Override
	public int getFuel() {
		return this.getEntityData().get(FUEL);
	}

	@Override
	public void setFuel(int fuel) {
		this.getEntityData().set(FUEL, Mth.clamp(fuel, 0, this.getFuelCapacity()));
	}
	
	@Override
	public int getFuelCapacity() {
		return this.getRequiredFuelBuckets() * this.getFuelOfBucket();
	}

	protected int getBuckets() {
		return this.getEntityData().get(BUCKETS);
	}

	public void setBucket(int bucket) {
		this.getEntityData().set(BUCKETS, Mth.clamp(bucket, 0, this.getRequiredFuelBuckets()));
	}

	public abstract int getRequiredFuelBuckets();

	public abstract int getFuelOfBucket();

	@Override
	public void putFuelMuchAsBucket() {
		this.setBucket(this.getBuckets() + 1);
	}

	@Override
	public boolean canPutFuelMuchAsBucket() {
		return (this.getBuckets() + 1) <= this.getRequiredFuelBuckets();
	}

	protected int getFillUpSpeed() {
		return 1;
	}

}
