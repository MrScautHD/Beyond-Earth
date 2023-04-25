package net.mrscauthd.beyond_earth.common.entities;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.netty.buffer.Unpooled;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.network.NetworkHooks;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.keybinds.KeyVariables;
import net.mrscauthd.beyond_earth.common.menus.LanderMenu;

public class LanderEntity extends IVehicleEntity {

	public static Consumer<LanderEntity> playBoost = e -> {
	};

	public static Consumer<LanderEntity> playBeep = e -> {
	};

	public LanderEntity(EntityType<?> type, Level level) {
		super(type, level);
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
		if (!source.isProjectile() && source.getEntity() != null && source.getEntity().isCrouching()
				&& !this.isVehicle()) {
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
				this.level.explode(null, this.getX(), this.getY(), this.getZ(), 10, true,
						Level.ExplosionInteraction.TNT);

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

	private final ItemStackHandler inventory = new ItemStackHandler(11) {
		@Override
		public int getSlotLimit(int slot) {
			return 64;
		}
	};

	private final CombinedInvWrapper combined = new CombinedInvWrapper(this.inventory);

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
		if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && side == null) {
			return LazyOptional.of(() -> combined).cast();
		}
		return super.getCapability(capability, side);
	}

	public IItemHandlerModifiable getItemHandler() {
		return (IItemHandlerModifiable) this.getCapability(ForgeCapabilities.ITEM_HANDLER, null).resolve().get();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		compound.put("InventoryCustom", this.inventory.serializeNBT());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		Tag inventoryCustom = compound.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag) {
			this.inventory.deserializeNBT((CompoundTag) inventoryCustom);
		}
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		super.interact(player, hand);
		InteractionResult result = InteractionResult.sidedSuccess(this.level.isClientSide);

		if (!this.level.isClientSide) {
			if (player.isCrouching()) {
				NetworkHooks.openScreen((ServerPlayer) player, new MenuProvider() {
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
		return this.inventory;
	}

	@Override
	public void tick() {
		super.tick();
		this.slowDownLander();
	}

	@Override
	public void onAddedToWorld() {
		super.onAddedToWorld();
		this.beepWarningSound();
		this.boostSound();
	}

	public void beepWarningSound() {
		if (level.isClientSide())
			playBeep.accept(this);
	}

	public void boostSound() {
		if (level.isClientSide())
			playBoost.accept(this);
	}

	public Player getFirstPlayerPassenger() {
		if (!this.getPassengers().isEmpty() && this.getPassengers().get(0) instanceof Player player) {

			return player;
		}

		return null;
	}

	public void slowDownLander() {
		Player player = this.getFirstPlayerPassenger();

		if (player != null) {
			if (KeyVariables.isHoldingJump(player)) {

				Vec3 vec = this.getDeltaMovement();

				if (!this.isOnGround() && !this.isEyeInFluid(FluidTags.WATER)) {
					if (vec.y() < -0.05) {
						this.setDeltaMovement(vec.x(), vec.y() * 0.85, vec.z());
					}

					this.fallDistance = (float) (vec.y() * (-1) * 4.5);

					if (this.level instanceof ServerLevel) {
						for (ServerPlayer p : ((ServerLevel) player.level).getServer().getPlayerList().getPlayers()) {
							((ServerLevel) this.level).sendParticles(p, ParticleTypes.SPIT, true, this.getX(),
									this.getY() - 0.3, this.getZ(), 3, 0.1, 0.1, 0.1, 0.001);
						}
					}
				}
			}
		}
	}
}
