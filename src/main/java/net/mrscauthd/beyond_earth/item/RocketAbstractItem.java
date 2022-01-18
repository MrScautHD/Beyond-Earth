package net.mrscauthd.beyond_earth.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.block.RocketLaunchPad;
import net.mrscauthd.beyond_earth.entity.RocketAbstractEntity;
import net.mrscauthd.beyond_earth.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.gauge.IGaugeValue;

public abstract class RocketAbstractItem extends Item {

	public static String TAG_FUEL = BeyondEarthMod.MODID + ":fuel";
	public static String TAG_BUCKETS = BeyondEarthMod.MODID + ":buckets";

	public RocketAbstractItem(Properties properties) {
		super(properties);
	}

	public abstract EntityType<? extends RocketAbstractEntity> getRocketEntityType();

	public abstract int getRocketTier();

	public abstract int getFuelBucketsOfFull();

	public abstract int getFuelAmountPerBucket();

	public int getFuelLoadSpeed() {
		return 1;
	}

	public int getFuelAmountOfBucket(int buckets) {
		return buckets * this.getFuelAmountPerBucket();
	}

	public IGaugeValue getFuelGauge(int fuel) {
		int fuelFull = this.getFuelAmountOfBucket(this.getFuelBucketsOfFull());
		return GaugeValueHelper.getFuel(fuel, fuelFull);
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
		super.fillItemCategory(tab, list);

		if (this.allowdedIn(tab)) {
			ItemStack itemstack = new ItemStack(this);
			CompoundTag compound = itemstack.getOrCreateTag();
			int buckets = this.getFuelBucketsOfFull();
			compound.putInt(TAG_FUEL, this.getFuelAmountOfBucket(buckets));
			compound.putInt(TAG_BUCKETS, buckets);
			list.add(itemstack);
		}
	}

	@Override
	public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		CompoundTag compound = itemstack.getOrCreateTag();
		int fuel = compound.getInt(TAG_FUEL);
		list.add(GaugeTextHelper.buildBlockTooltip(GaugeTextHelper.getPercentText(this.getFuelGauge(fuel))));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		ItemStack itemStack = context.getItemInHand();

		if (level instanceof ServerLevel) {
			ServerLevel serverlevel = (ServerLevel) level;
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			if (state.getBlock() instanceof RocketLaunchPad && state.getValue(RocketLaunchPad.STAGE)) {

				BlockPos pos1 = new BlockPos(x, y + 1, z);
				BlockPos pos2 = new BlockPos(x, y + 2, z);
				BlockPos pos3 = new BlockPos(x, y + 3, z);
				BlockPos pos4 = new BlockPos(x, y + 4, z);

				if (level.getBlockState(pos1).isAir() && level.getBlockState(pos2).isAir() && level.getBlockState(pos3).isAir() && level.getBlockState(pos4).isAir()) {

					AABB scanAbove = new AABB(x - 0, y - 0, z - 0, x + 1, y + 1, z + 1);
					List<Entity> entities = player.getCommandSenderWorld().getEntitiesOfClass(Entity.class, scanAbove);

					if (entities.isEmpty()) {
						RocketAbstractEntity rocket = this.getRocketEntityType().create(serverlevel, null, null, player, pos, MobSpawnType.MOB_SUMMONED, true, true);
						rocket.yHeadRot = player.getYRot();
						rocket.yBodyRot = player.getYRot();
						this.applyItemNBT(itemStack, rocket);
						level.addFreshEntity(rocket);
						rocketPlaceSound(pos, level);

						if (!player.isCreative()) {
							itemStack.shrink(1);
						}
					}
				}
			}
		} else {
			return InteractionResult.PASS;
		}

		return super.useOn(context);
	}

	public void fetchItemNBT(ItemStack itemStack, RocketAbstractEntity rocket) {
		CompoundTag compound = itemStack.getOrCreateTag();
		compound.putInt(TAG_FUEL, rocket.getFuelAmount());
		compound.putInt(TAG_BUCKETS, rocket.getFuelBuckets());
	}

	public void applyItemNBT(ItemStack itemStack, RocketAbstractEntity rocket) {
		CompoundTag compound = itemStack.getOrCreateTag();
		rocket.setFuelAmount(compound.getInt(TAG_FUEL));
		rocket.setFuelBuckets(compound.getInt(TAG_BUCKETS));
	}

	public static void rocketPlaceSound(BlockPos pos, Level world) {
		world.playSound(null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1, 1);
	}
}
