package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.energy.IEnergyStorage;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.NamedComponentRegistry;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemEnergyCommon;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemRegistry;
import net.mrscauthd.beyond_earth.common.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.ChunkOxygen;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.OxygenMakingRecipeAbstract;
import net.mrscauthd.beyond_earth.common.menus.OxygenBubbleDistributorMenu;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.CapabilityRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;

public class OxygenDistributorBlockEntity extends OxygenMakingBlockEntity {

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

    public OxygenDistributorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected boolean canActivated() {
        if (this.getOutputTank().getOxygen() >= Config.OXYGEN_BUBBLE_DISTRIBUTOR_RATE_OUTPUT.get()) {
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
        return new OxygenBubbleDistributorMenu.GuiContainer(id, inventory, this);
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

        ChunkOxygen chunkO2 = ((LevelChunk) level.getChunk(this.getBlockPos()))
                .getCapability(CapabilityRegistry.CHUNK_OXYGEN).orElse(null);
        if (chunkO2.hasInfiniteO2()) {
            oxygenStorage.receiveOxygen(Config.OXYGEN_BUBBLE_DISTRIBUTOR_RATE_INPUT.get(), false);
            return;
        }

        int oxygenUsing = Config.OXYGEN_BUBBLE_DISTRIBUTOR_RATE_OUTPUT.get();
        int O2Provided = oxygenStorage.extractOxygen(oxygenUsing, true);
        if (level instanceof ServerLevel serverlevel) {
            int remaining = chunkO2.addO2(this.getBlockPos(), (byte) O2Provided, true);
            int removed = O2Provided - remaining;
            oxygenStorage.extractOxygen(removed, false);
            Vec3 center = new AABB(this.getBlockPos()).getCenter();
            if (removed > 0) {
                serverlevel.sendParticles(ParticleTypes.CLOUD, center.x, center.y + 0.5D, center.z, 1, 0.1D, 0.1D, 0.1D,
                        0.001D);
                this.setProcessedInThisTick();
            }
        }
    }

    public int getMaxTimer() {
        return MAX_TIMER;
    }

    public int getTimer() {
        return this.getPersistentData().getInt(KEY_TIMER);
    }

    public void setTimer(int timer) {
        timer = Math.max(timer, 0);

        if (this.getTimer() != timer) {
            this.getPersistentData().putInt(KEY_TIMER, timer);
            this.setChanged();
        }
    }

    @Override
    protected void createPowerSystems(PowerSystemRegistry map) {
        super.createPowerSystems(map);

        map.put(new PowerSystemEnergyCommon(this) {
            @Override
            public int getBasePowerForOperation() {
                return OxygenDistributorBlockEntity.this.getBasePowerForOperation();
            }
        });
    }

    public int getBasePowerForOperation() {
        return Config.OXYGEN_BUBBLE_DISTRIBUTOR_ENERGY_USAGE.get();
    }

    @Override
    public BeyondEarthRecipeType<? extends OxygenMakingRecipeAbstract> getRecipeType() {
        return RecipeTypeRegistry.OXYGEN_BUBBLE_DISTRIBUTING.get();
    }
}