package net.mrscauthd.beyond_earth.entities;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.blocks.RocketLaunchPad;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.events.forge.SetPlanetSelectionMenuNeededNbtEvent;
import net.mrscauthd.beyond_earth.registries.SoundsRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public abstract class IRocketEntity extends VehicleEntity {

    //TODO REWORK ANIMTION
    public double ar = 0;
    public double ay = 0;
    public double ap = 0;

    public static final EntityDataAccessor<Boolean> ROCKET_START = SynchedEntityData.defineId(IRocketEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> BUCKETS = SynchedEntityData.defineId(IRocketEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> FUEL = SynchedEntityData.defineId(IRocketEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> START_TIMER = SynchedEntityData.defineId(IRocketEntity.class, EntityDataSerializers.INT);

    public IRocketEntity(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
        this.entityData.define(ROCKET_START, false);
        this.entityData.define(BUCKETS, 0);
        this.entityData.define(FUEL, 0);
        this.entityData.define(START_TIMER, 0);
    }

    public abstract double getRocketSpeed();

    public abstract int getTier();

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public void push(Entity p_21294_) {

    }

    @Override
    public void kill() {
        this.dropEquipment();
        this.spawnRocketItem();

        if (!this.level.isClientSide) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float p_21017_) {
        Entity sourceEntity = source.getEntity();

        if (!source.isProjectile() && sourceEntity != null && sourceEntity.isCrouching() && !this.isVehicle()) {

            this.spawnRocketItem();
            this.dropEquipment();

            if (!this.level.isClientSide) {
                this.remove(RemovalReason.DISCARDED);
            }

            return true;
        }

        return false;
    }

    public abstract ItemStack getRocketItem();

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return this.getRocketItem();
    }

    protected void spawnRocketItem() {
        ItemEntity entityToSpawn = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), this.getRocketItem());
        entityToSpawn.setPickUpDelay(10);

        this.level.addFreshEntity(entityToSpawn);
    }

    protected void dropEquipment() {
        for (int i = 0; i < this.inventory.getSlots(); ++i) {
            ItemStack itemstack = this.inventory.getStackInSlot(i);
            if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
                this.spawnAtLocation(itemstack);
            }
        }
    }

    private final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        public int getSlotLimit(int slot) {
            return 64;
        }
    };

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    private final CombinedInvWrapper combined = new CombinedInvWrapper(this.inventory);

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
        if (this.isAlive() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == null) {
            return LazyOptional.of(() -> this.combined).cast();
        }
        return super.getCapability(capability, side);
    }

    public IItemHandlerModifiable getItemHandler() {
        return (IItemHandlerModifiable) this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).resolve().get();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.put("InventoryCustom", this.inventory.serializeNBT());

        compound.putBoolean("rocket_start", this.getEntityData().get(ROCKET_START));
        compound.putInt("buckets", this.getEntityData().get(BUCKETS));
        compound.putInt("fuel", this.getEntityData().get(FUEL));
        compound.putInt("start_timer", this.getEntityData().get(START_TIMER));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        Tag inventoryCustom = compound.get("InventoryCustom");
        if (inventoryCustom instanceof CompoundTag) {
            this.inventory.deserializeNBT((CompoundTag) inventoryCustom);
        }

        this.getEntityData().set(ROCKET_START, compound.getBoolean("rocket_start"));
        this.getEntityData().set(BUCKETS, compound.getInt("buckets"));
        this.getEntityData().set(FUEL, compound.getInt("fuel"));
        this.getEntityData().set(START_TIMER, compound.getInt("start_timer"));
    }

    public abstract void particleSpawn();

    public abstract void fillUpRocket();

    public boolean doesDrop(BlockState state, BlockPos pos) {
        if (this.isOnGround() || this.isInFluidType()) {

            BlockState state2 = this.level.getBlockState(new BlockPos(Math.floor(this.getX()), this.getY() - 0.2, Math.floor(this.getZ())));

            if (!this.level.isEmptyBlock(pos) && ((state2.getBlock() instanceof RocketLaunchPad && !state2.getValue(RocketLaunchPad.STAGE)) || !(state.getBlock() instanceof RocketLaunchPad))) {

                this.dropEquipment();
                this.spawnRocketItem();

                if (!this.level.isClientSide) {
                    this.remove(RemovalReason.DISCARDED);
                }

                return true;
            }
        }

        return false;
    }

    protected void checkOnBlocks() {
        AABB aabb = this.getBoundingBox();
        BlockPos blockPos1 = new BlockPos(aabb.minX + 0.001D, (aabb.minY - 0.2) + 0.001D, aabb.minZ + 0.001D);
        BlockPos blockPos2 = new BlockPos(aabb.maxX - 0.001D, aabb.minY - 0.001D, aabb.maxZ - 0.001D);

        if (this.level.hasChunksAt(blockPos1, blockPos2)) {
            for (int i = blockPos1.getX(); i <= blockPos2.getX(); ++i) {
                for (int j = blockPos1.getY(); j <= blockPos2.getY(); ++j) {
                    for (int k = blockPos1.getZ(); k <= blockPos2.getZ(); ++k) {
                        BlockPos pos = new BlockPos(i, j, k);
                        BlockState state = this.level.getBlockState(pos);

                        if (this.doesDrop(state, pos)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public void rocketAnimation() {
        this.ar = this.ar + 1;
        if (this.ar == 1) {
            this.ay = this.ay + 0.006;
            this.ap = this.ap + 0.006;
        } else if (this.ar == 2) {
            this.ar = 0;
            this.ay = 0;
            this.ap = 0;
        }
    }

    public void startTimerAndFlyMovement() {
        if (this.entityData.get(START_TIMER) < 200) {
            this.entityData.set(START_TIMER, this.entityData.get(START_TIMER) + 1);
        }

        if (this.entityData.get(START_TIMER) == 200) {
            if (this.getDeltaMovement().y < this.getRocketSpeed() - 0.1) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y + 0.1, this.getDeltaMovement().z);
            } else {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getRocketSpeed(), this.getDeltaMovement().z);
            }
        }
    }

    public void openPlanetSelectionMenu() {
        if (this.getY() > 600 && !this.getPassengers().isEmpty()) {
            if (this.getPassengers().get(0) instanceof Player) {

                Player pass = (Player) this.getPassengers().get(0);

                if (pass.containerMenu != null) {
                    pass.closeContainer();
                }

                pass.getPersistentData().putBoolean(BeyondEarth.MODID + ":planet_selection_menu_open", true);
                pass.getPersistentData().putInt(BeyondEarth.MODID + ":rocket_tier", this.getTier());

                /** SAVE ITEMS IN THE PLAYER */
                ListTag tag = new ListTag();

                tag.add(this.getInventory().getStackInSlot(0).save(new CompoundTag()));
                tag.add(new ItemStack(this.getRocketItem().getItem()).save(new CompoundTag()));

                pass.getPersistentData().put(BeyondEarth.MODID + ":rocket_item_list", tag);
                pass.setNoGravity(true);

                /** STOP ROCKET SOUND */
                if (pass instanceof ServerPlayer) {
                    Methods.stopSound((ServerPlayer) pass, SoundsRegistry.ROCKET_SOUND.getId(), SoundSource.AMBIENT);
                }

                MinecraftForge.EVENT_BUS.post(new SetPlanetSelectionMenuNeededNbtEvent(pass, this));

                if (!this.level.isClientSide) {
                    this.remove(RemovalReason.DISCARDED);
                }
            }
        } else if (this.getY() > 600 && this.getPassengers().isEmpty()) {
            if (!this.level.isClientSide) {
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    public void rocketExplosion() {
        if (this.entityData.get(START_TIMER) == 200) {
            if (this.getDeltaMovement().y < -0.07) {
                if (!this.level.isClientSide) {
                    this.level.explode(this, this.getX(), this.getBoundingBox().maxY, this.getZ(), 10, true, Explosion.BlockInteraction.BREAK);

                    this.remove(RemovalReason.DISCARDED);
                }
            }
        }
    }

    public void burnEntities() {
        if (this.entityData.get(START_TIMER) == 200) {
            AABB aabb = AABB.ofSize(new Vec3(this.getX(), this.getY() - 2, this.getZ()), 2, 2, 2);
            List<LivingEntity> entities = this.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class, aabb);

            for (LivingEntity entity : entities) {
                if (!Methods.isLivingInNetheriteSpaceSuit(entity)) {
                    entity.setSecondsOnFire(15);
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        this.checkOnBlocks();
        this.fillUpRocket();
        this.rocketExplosion();
        this.burnEntities();

        if (this.entityData.get(ROCKET_START)) {
            this.particleSpawn();
            this.rocketAnimation();
            this.startTimerAndFlyMovement();
            this.openPlanetSelectionMenu();
        }
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity livingEntity) {
        Vec3[] avector3d = new Vec3[]{getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), livingEntity.getYRot()), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), livingEntity.getYRot() - 22.5F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), livingEntity.getYRot() + 22.5F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), livingEntity.getYRot() - 45.0F), getCollisionHorizontalEscapeVector((double)this.getBbWidth(), (double)livingEntity.getBbWidth(), livingEntity.getYRot() + 45.0F)};
        Set<BlockPos> set = Sets.newLinkedHashSet();
        double d0 = this.getBoundingBox().maxY;
        double d1 = this.getBoundingBox().minY - 0.5D;
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

        for(Vec3 vector3d : avector3d) {
            blockpos$mutable.set(this.getX() + vector3d.x, d0, this.getZ() + vector3d.z);

            for(double d2 = d0; d2 > d1; --d2) {
                set.add(blockpos$mutable.immutable());
                blockpos$mutable.move(Direction.DOWN);
            }
        }

        for(BlockPos blockpos : set) {
            if (!this.level.getFluidState(blockpos).is(FluidTags.LAVA)) {
                double d3 = this.level.getBlockFloorHeight(blockpos);
                if (DismountHelper.isBlockFloorValid(d3)) {
                    Vec3 vector3d1 = Vec3.upFromBottomCenterOf(blockpos, d3);

                    for(Pose pose : livingEntity.getDismountPoses()) {
                        if (DismountHelper.isBlockFloorValid(this.level.getBlockFloorHeight(blockpos))) {
                            livingEntity.setPose(pose);
                            return vector3d1;
                        }
                    }
                }
            }
        }

        return new Vec3(this.getX(), this.getBoundingBox().maxY, this.getZ());
    }
}
