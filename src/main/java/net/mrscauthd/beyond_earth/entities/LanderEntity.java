package net.mrscauthd.beyond_earth.entities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.network.NetworkHooks;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.guis.screens.lander.LanderMenu;

import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import io.netty.buffer.Unpooled;

import net.minecraft.world.entity.Entity.RemovalReason;

public class LanderEntity extends VehicleEntity {

	public LanderEntity(EntityType type, Level world) {
		super(type, world);
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public void push(Entity p_21294_) {
	}

	@Deprecated
	public boolean canBeRiddenInWater() {
		return true;
	}

	@Override
	public boolean rideableUnderWater() {
		return true;
	}

	@Override
	public double getPassengersRidingOffset() {
		return super.getPassengersRidingOffset() - 0.25;
	}

	@Override
	public void kill() {
		this.dropEquipment();

		if (!this.level.isClientSide) {
			this.remove(RemovalReason.DISCARDED);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!source.isProjectile() && source.getEntity() != null && source.getEntity().isCrouching() && !this.isVehicle()) {
			this.dropEquipment();

			if (!this.level.isClientSide) {
				this.remove(RemovalReason.DISCARDED);
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean causeFallDamage(float p_150347_, float p_150348_, DamageSource p_150349_) {
		if (p_150347_ >= 3.0F) {

			if (!this.level.isClientSide) {
				this.level.explode(null, this.getX(), this.getY(), this.getZ(), 10, true, Explosion.BlockInteraction.BREAK);

				this.remove(RemovalReason.DISCARDED);
			}
		}

		return super.causeFallDamage(p_150347_, p_150348_, p_150349_);
	}

	protected void dropEquipment() {
		for (int i = 0; i < inventory.getSlots(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
				this.spawnAtLocation(itemstack);
			}
		}
	}

	private final ItemStackHandler inventory = new ItemStackHandler(2) {
		@Override
		public int getSlotLimit(int slot) {
			return 64;
		}
	};

	private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory);

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
		if (this.isAlive() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == null) {
			return LazyOptional.of(() -> combined).cast();
		}
		return super.getCapability(capability, side);
	}

	public IItemHandlerModifiable getItemHandler() {
		return (IItemHandlerModifiable) this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).resolve().get();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		compound.put("InventoryCustom", inventory.serializeNBT());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		Tag inventoryCustom = compound.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag) {
			inventory.deserializeNBT((CompoundTag) inventoryCustom);
		}
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
						return Component.translatable("container.entity." + BeyondEarth.MODID + ".lander");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
						packetBuffer.writeVarInt(LanderEntity.this.getId());
						return new LanderMenu.GuiContainer(id, inventory, packetBuffer);
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

	public ItemStackHandler getInventory() {
		return inventory;
	}
}
