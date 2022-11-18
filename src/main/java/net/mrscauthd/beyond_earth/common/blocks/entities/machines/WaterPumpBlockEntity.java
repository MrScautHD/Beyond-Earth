package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import java.util.function.Predicate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.NamedComponentRegistry;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemEnergyCommon;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemRegistry;
import net.mrscauthd.beyond_earth.common.blocks.machines.WaterPump;
import net.mrscauthd.beyond_earth.common.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.common.capabilities.fluid.FluidHandlerWrapper;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.menus.WaterPumpMenu;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;
import net.mrscauthd.beyond_earth.common.util.FluidUtil2;

public class WaterPumpBlockEntity extends AbstractMachineBlockEntity {
    public static final int DEFAULT_ENERGY_USAGE = 1;
    public static final int DEFAULT_TANK_TRANSFER = 10;
    public static final int DEFAULT_WORK_INTERVAL = 10;

    public WaterPumpBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.WATER_PUMP_BLOCK_ENTITY.get(), pos, state);
    }

    public static final ResourceLocation WATER_TANK = new ResourceLocation(BeyondEarth.MODID, "water_tank");
    public static final int DEFAULT_TANK_CAPACITY = 6000;
    public double WATER_TIMER = 0;
    private FluidTank waterTank;

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new WaterPumpMenu.GuiContainer(id, inventory, this);
    }

    @Override
    protected void tickProcessing() {

        BlockPos pos = this.getBlockPos();
        BlockPos pickupPos = pos.below();

        if (this.level.getFluidState(pickupPos) == Fluids.WATER.getSource(false)
                && this.level.getBlockState(pickupPos).is(Blocks.WATER)) {

            if (hasSpaceInWaterTank(this.getWaterTank().getFluid().getAmount())) {

                if (this.consumePowerForOperation() != null) {

                    WATER_TIMER = WATER_TIMER + 1;

                    if (WATER_TIMER > Config.WATER_PUMP_WORK_INTERVAL.get()) {

                        this.level.setBlock(pickupPos, Blocks.AIR.defaultBlockState(), 3);

                        this.getWaterTank().fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);

                        WATER_TIMER = 0;
                    }
                }
            }
        }

        if (this.getWaterTank().getFluid().getAmount() > 1) {
            BlockEntity ejectBlockEntity = level.getBlockEntity(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()));

            if (ejectBlockEntity != null) {

                IFluidHandler fluidHandler = ejectBlockEntity
                        .getCapability(ForgeCapabilities.FLUID_HANDLER, Direction.DOWN).orElse(null);

                if (fluidHandler != null) {
                    int transferPerTick = this.getTransferPerTick();

                    if (FluidUtil.tryFluidTransfer(fluidHandler, this.waterTank, transferPerTick, false)
                            .getAmount() == transferPerTick) {
                        FluidUtil.tryFluidTransfer(fluidHandler, this.waterTank, transferPerTick, true);
                    }
                }
            }
        }
    }

    public int getTransferPerTick() {
        return Config.WATER_PUMP_TANK_TRANSFER.get();
    }

    public boolean hasSpaceInWaterTank(int water) {
        return hasSpaceIn(water, this.getWaterTank().getFluid());
    }

    public boolean hasSpaceIn(int water, FluidStack storage) {
        return water < this.getWaterTank().getCapacity() - 999;
    }

    @Override
    public <T> LazyOptional<T> getCapabilityFluidHandler(Capability<T> capability, Direction facing) {
        if (facing == null || facing == Direction.UP) {
            return super.getCapabilityFluidHandler(capability, facing)
                    .lazyMap(handler -> new FluidHandlerWrapper((IFluidHandler) handler, true, false)).cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public <T> LazyOptional<T> getCapabilityEnergy(Capability<T> capability, Direction facing) {
        BlockState blockState = this.getBlockState();

        if (blockState.hasProperty(WaterPump.FACING)) {
            Direction blockDirection = blockState.getValue(WaterPump.FACING);

            if (facing == null || facing == blockDirection) {
                return super.getCapabilityEnergy(capability, facing);
            }
        }

        return LazyOptional.empty();
    }

    @Override
    protected void createPowerSystems(PowerSystemRegistry map) {
        super.createPowerSystems(map);

        map.put(new PowerSystemEnergyCommon(this) {
            @Override
            public int getBasePowerForOperation() {
                return WaterPumpBlockEntity.this.getBasePowerForOperation();
            }
        });
    }

    @Override
    protected void createEnergyStorages(NamedComponentRegistry<IEnergyStorage> registry) {
        super.createEnergyStorages(registry);
        int capacity = Config.WATER_PUMP_ENERGY_CAPACITY.get();
        int maxTransfer = Config.WATER_PUMP_ENERGY_TRANSFER.get();
        registry.put(new EnergyStorageBasic(this, capacity, maxTransfer, capacity));
    }

    public int getBasePowerForOperation() {
        return Config.WATER_PUMP_ENERGY_USAGE.get();
    }

    @Override
    protected void createFluidHandlers(NamedComponentRegistry<IFluidHandler> registry) {
        super.createFluidHandlers(registry);
        this.waterTank = (FluidTank) registry.computeIfAbsent(this.getTankName(), k -> this.creatTank(k));
    }

    protected int getInitialTankCapacity(ResourceLocation name) {
        return Config.WATER_PUMP_TANK_CAPACITY.get();
    }

    protected Predicate<FluidStack> getInitialTankValidator(ResourceLocation name) {
        Fluid fluid = this.getTankFluid(name);
        return fluid != null ? fs -> FluidUtil2.isSame(fs, fluid) : null;
    }

    protected Fluid getTankFluid(ResourceLocation name) {
        if (name.equals(this.getTankName())) {
            return Fluids.WATER;
        } else {
            return null;
        }
    }

    protected FluidTank creatTank(ResourceLocation name) {
        return new FluidTank(this.getInitialTankCapacity(name), this.getInitialTankValidator(name)) {
            @Override
            protected void onContentsChanged() {
                super.onContentsChanged();
                WaterPumpBlockEntity.this.setChanged();
            }
        };
    }

    public ResourceLocation getTankName() {
        return WATER_TANK;
    }

    public FluidTank getWaterTank() {
        return this.waterTank;
    }

    @Override
    public boolean hasSpaceInOutput() {
        return false;
    }
}
