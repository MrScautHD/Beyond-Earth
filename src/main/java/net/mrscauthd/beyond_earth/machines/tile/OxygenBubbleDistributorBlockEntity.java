package net.mrscauthd.beyond_earth.machines.tile;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.config.Config;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.OxygenMakingRecipeAbstract;
import net.mrscauthd.beyond_earth.guis.screens.oxygenbubbledistributor.OxygenBubbleDistributorGui;
import net.mrscauthd.beyond_earth.registries.BlockEntitiesRegistry;
import net.mrscauthd.beyond_earth.registries.EffectsRegistry;

public class OxygenBubbleDistributorBlockEntity extends OxygenMakingBlockEntity {

	public static final int DEFAULT_ENERGY_USAGE = 1;
	public static final int DEFAULT_ENERGY_USAGE_PER_RANGE = 0;
    public static final int DEFAULT_RANGE_MIN = 1;
    public static final int DEFAULT_RANGE_MAX = 15;
	public static final String KEY_TIMER = "timer";
	public static final String KEY_RANGE = "range";
	public static final String KEY_WORKINGAREA_VISIBLE = "workingAreaVisible";

	/**
	 * Interval Ticks, 4 = every 4 ticks
	 */
	public static final int MAX_TIMER = 4;

	public OxygenBubbleDistributorBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntitiesRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK_ENTITY.get(), pos, state);
		this.setWorkingAreaVisible(false);
	}

	@Override
	protected boolean canActivated() {
		if (this.getOutputTank().getOxygenStored() >= this.getOxygenUsing(this.getRange())) {
			return true;
		}

		return super.canActivated();
	}

	@Override
	public AABB getRenderBoundingBox() {
		return new AABB(this.getBlockPos()).inflate(32, 32, 32);
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new OxygenBubbleDistributorGui.GuiContainer(id, inventory, this);
	}

	@Override
	protected void createEnergyStorages(NamedComponentRegistry<IEnergyStorage> registry) {
		super.createEnergyStorages(registry);
        int capacity = Config.OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_CAPACITY.get();
        int maxTransfer = Config.OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_TRANSFER.get();
        registry.put(new EnergyStorageBasic(this, capacity, maxTransfer, capacity));
	}
	
	@Override
	protected int getInitialTankCapacity(ResourceLocation name) {
        if (name.equals(this.getInputTankName())) {
            return Config.OXYGEN_BUBBLE_DISTRIBUTOR_TANK_FLUID_CAPACITY.get();
        } else if (name.equals(this.getOutputTankName())) {
            return Config.OXYGEN_BUBBLE_DISTRIBUTOR_TANK_OXYGEN_CAPACITY.get();
        } else {
            return super.getInitialTankCapacity(name);
        }
    }
	
	@Override
	public int getTransferPerTick() {
	    return Config.OXYGEN_BUBBLE_DISTRIBUTOR_TANK_TRANSFER.get();
	}

	protected void tickProcessing() {
		super.tickProcessing();

		this.tickDistributeTimer();
	}

	/**
	 * timer will cycle 0, 1, 2, 3
	 */
	private void tickDistributeTimer() {
		if (this.getTimer() >= this.getMaxTimer()) {
			this.setTimer(0);
			this.distribute();
		}
		this.setTimer(this.getTimer() + 1);
	}

	private void distribute() {
		IOxygenStorage oxygenStorage = this.getOutputTank();
		double range = this.getRange();
		int oxygenUsing = this.getOxygenUsing(range);

		if (oxygenStorage.extractOxygen(oxygenUsing, true) == oxygenUsing) {
			if (this.isProcessedInThisTick() || this.consumePowerForOperation() != null) {
				oxygenStorage.extractOxygen(oxygenUsing, false);
				this.spawnOxygenBubble(range);
			}
		}
	}

	private void spawnOxygenBubble(double range) {
		Level level = this.getLevel();
		List<Mob> entities = level.getEntitiesOfClass(Mob.class, this.getWorkingArea(range));

		List<Player> players = level.getEntitiesOfClass(Player.class, this.getWorkingArea(range));

		for (Mob entity : entities) {
			entity.addEffect(new MobEffectInstance(EffectsRegistry.OXYGEN_EFFECT.get(), 2 * 24, 0, false, false));
		}

		for (Player entity : players) {
			entity.addEffect(new MobEffectInstance(EffectsRegistry.OXYGEN_EFFECT.get(), 2 * 24, 0, false, false));
		}

		if (level instanceof ServerLevel) {
			ServerLevel serverLevel = (ServerLevel) level;
			Vec3 center = new AABB(this.getBlockPos()).getCenter();
			serverLevel.sendParticles(ParticleTypes.CLOUD, center.x, center.y + 0.5D, center.z, 1, 0.1D, 0.1D, 0.1D, 0.001D);
		}

		this.setProcessedInThisTick();
	}

	public int getMaxTimer() {
		return MAX_TIMER;
	}

	public int getTimer() {
		return this.getTileData().getInt(KEY_TIMER);
	}

	public void setTimer(int timer) {
		timer = Math.max(timer, 0);

		if (this.getTimer() != timer) {
			this.getTileData().putInt(KEY_TIMER, timer);
			this.setChanged();
		}
	}

	public int getOxygenUsing(double range) {
		return (int) range + 1;
	}

	public int getRange() {
		return Math.max(this.getTileData().getInt(KEY_RANGE), Config.OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MIN.get());
	}

	public void setRange(int range) {
		range = Math.min(Math.max(range, Config.OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MIN.get()), Config.OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MAX.get());

		if (this.getRange() != range) {
			this.getTileData().putInt(KEY_RANGE, range);
			this.setChanged();
		}
	}

	public boolean isWorkingAreaVisible() {
		return this.getTileData().getBoolean(KEY_WORKINGAREA_VISIBLE);
	}

	public void setWorkingAreaVisible(boolean visible) {
		if (this.isWorkingAreaVisible() != visible) {
			this.getTileData().putBoolean(KEY_WORKINGAREA_VISIBLE, visible);
			this.setChanged();
		}
	}

	public AABB getWorkingArea(double range) {
		return this.getWorkingArea(this.getBlockPos(), range);
	}

	public AABB getWorkingArea(BlockPos pos, double range) {
		return new AABB(pos).inflate(range).move(0.0D, range, 0.0D);
	}

	@Override
	protected void createPowerSystems(PowerSystemRegistry map) {
		super.createPowerSystems(map);

		map.put(new PowerSystemEnergyCommon(this) {
			@Override
			public int getBasePowerForOperation() {
				return OxygenBubbleDistributorBlockEntity.this.getBasePowerForOperation();
			}
		});
	}

	public int getBasePowerForOperation() {
		return Config.OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_USAGE.get();
	}

	@Override
	public BeyondEarthRecipeType<? extends OxygenMakingRecipeAbstract> getRecipeType() {
		return BeyondEarthRecipeTypes.OXYGEN_BUBBLE_DISTRIBUTING;
	}

	public static class ChangeRangeMessage {
		public BlockPos blockPos = BlockPos.ZERO;
		public boolean direction = false;

		public ChangeRangeMessage() {

		}

		public ChangeRangeMessage(BlockPos pos, boolean direction) {
			this.blockPos = pos;
			this.direction = direction;
		}

		public ChangeRangeMessage(FriendlyByteBuf buffer) {
			this.blockPos = buffer.readBlockPos();
			this.direction = buffer.readBoolean();
		}

		public static ChangeRangeMessage decode(FriendlyByteBuf buffer) {
			return new ChangeRangeMessage(buffer);
		}

		public static void encode(ChangeRangeMessage message, FriendlyByteBuf buffer) {
			buffer.writeBlockPos(message.blockPos);
			buffer.writeBoolean(message.direction);
		}

		public static void handle(ChangeRangeMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();

			context.enqueueWork(() -> {
				OxygenBubbleDistributorBlockEntity blockEntity = (OxygenBubbleDistributorBlockEntity) context.getSender().level.getBlockEntity(message.blockPos);

				int prev = blockEntity.getRange();
				int next = prev + (message.direction ? +1 : -1);

				blockEntity.setRange(next);
			});

			context.setPacketHandled(true);
		}
	}

	public static class ChangeWorkingAreaVisibleMessage {
		public BlockPos blockPos = BlockPos.ZERO;
		public boolean visible = false;

		public ChangeWorkingAreaVisibleMessage() {

		}

		public ChangeWorkingAreaVisibleMessage(BlockPos pos, boolean visible) {
			this.blockPos = pos;
			this.visible = visible;
		}

		public ChangeWorkingAreaVisibleMessage(FriendlyByteBuf buffer) {
			this.blockPos = buffer.readBlockPos();
			this.visible = buffer.readBoolean();
		}

		public static ChangeWorkingAreaVisibleMessage decode(FriendlyByteBuf buffer) {
			return new ChangeWorkingAreaVisibleMessage(buffer);
		}

		public static void encode(ChangeWorkingAreaVisibleMessage message, FriendlyByteBuf buffer) {
			buffer.writeBlockPos(message.blockPos);
			buffer.writeBoolean(message.visible);
		}

		public static void handle(ChangeWorkingAreaVisibleMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();

			context.enqueueWork(() -> {
				OxygenBubbleDistributorBlockEntity blockEntity = (OxygenBubbleDistributorBlockEntity) context.getSender().level.getBlockEntity(message.blockPos);

				blockEntity.setWorkingAreaVisible(message.visible);
			});

			context.setPacketHandled(true);
		}
	}
}