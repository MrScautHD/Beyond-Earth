package net.mrscauthd.beyond_earth.entities;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkHooks;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.config.Config;
import io.netty.buffer.Unpooled;
import net.mrscauthd.beyond_earth.events.forge.RocketPickResultEvent;
import net.mrscauthd.beyond_earth.guis.screens.rocket.RocketGui;
import net.mrscauthd.beyond_earth.items.IFuelRocketItem;
import net.mrscauthd.beyond_earth.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.registries.ParticlesRegistry;

public class RocketTier2Entity extends IFuelRocketEntity {

	public static final int DEFAULT_FUEL_BUCKETS = 3;
	public static final int DEFAULT_FILL_UP_SPPED = 1;
	public static final int FUEL_OF_BUCKET = 100;

	public RocketTier2Entity(EntityType type, Level world) {
		super(type, world);
		this.setRocketSpeed(0.7);
	}

	@Override
	public double getPassengersRidingOffset() {
		return super.getPassengersRidingOffset() - 2.5;
	}

	@Override
	public ItemStack getPickResult() {
		ItemStack itemStack = new ItemStack(ItemsRegistry.TIER_2_ROCKET_ITEM.get(), 1);
		itemStack.getOrCreateTag().putInt(IFuelRocketItem.fuelTag, this.getFuel());
		itemStack.getOrCreateTag().putInt(IFuelRocketItem.bucketTag, this.getBuckets());
		MinecraftForge.EVENT_BUS.post(new RocketPickResultEvent(this, itemStack));

		return itemStack;
	}

	@Override
	protected void spawnRocketItem() {
		ItemStack itemStack = this.getPickedResult(null);
		ItemEntity entityToSpawn = new ItemEntity(level, this.getX(), this.getY(), this.getZ(), itemStack);
		entityToSpawn.setPickUpDelay(10);
		level.addFreshEntity(entityToSpawn);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		super.interact(player, hand);
		InteractionResult result = InteractionResult.sidedSuccess(this.level.isClientSide);

		if (!this.level.isClientSide) {
			if (player.isCrouching()) {
				NetworkHooks.openGui((ServerPlayer) player, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return new TranslatableComponent("container.entity." + BeyondEarthMod.MODID + ".rocket_t2");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
						packetBuffer.writeVarInt(RocketTier2Entity.this.getId());
						return new RocketGui.GuiContainer(id, inventory, packetBuffer);
					}
				}, buf -> {
					buf.writeVarInt(this.getId());
				});

				return InteractionResult.CONSUME;
			}

			player.startRiding(this);
			return InteractionResult.CONSUME;
		}

		return result;
	}

	@Override
	public void particleSpawn() {
		Vec3 vec = this.getDeltaMovement();

		if (level instanceof ServerLevel) {
			if (this.entityData.get(START_TIMER) == 200) {
				for (ServerPlayer p : ((ServerLevel) level).getServer().getPlayerList().getPlayers()) {
					((ServerLevel) level).sendParticles(p, (ParticleOptions) ParticlesRegistry.LARGE_FLAME_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 2.4, this.getZ() - vec.z, 20, 0.1, 0.1, 0.1, 0.001);
					((ServerLevel) level).sendParticles(p, (ParticleOptions) ParticlesRegistry.LARGE_SMOKE_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 3.4, this.getZ() - vec.z, 10, 0.1, 0.1, 0.1, 0.04);
				}
			} else {
				for (ServerPlayer p : ((ServerLevel) level).getServer().getPlayerList().getPlayers()) {
					((ServerLevel) level).sendParticles(p, ParticleTypes.CAMPFIRE_COSY_SMOKE, true, this.getX() - vec.x, this.getY() - vec.y - 0.1, this.getZ() - vec.z, 6, 0.1, 0.1, 0.1, 0.023);
				}
			}
		}
	}

	@Override
	public int getFuelOfBucket() {
		return FUEL_OF_BUCKET;
	}

	@Override
	public int getRequiredFuelBuckets() {
		return Config.ROCKET_TIER_2_FUEL_BUCKETS.get();
	}

	@Override
	protected int getFillUpSpeed() {
		return Config.ROCKET_TIER_2_FILL_UP_SPEED.get();
	}
}