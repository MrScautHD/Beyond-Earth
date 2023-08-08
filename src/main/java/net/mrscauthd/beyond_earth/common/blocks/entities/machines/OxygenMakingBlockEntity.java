package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValue;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.NamedComponentRegistry;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorageHolder;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenUtil;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.OxygenMakingRecipeAbstract;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.StackCacher;
import net.mrscauthd.beyond_earth.common.util.FluidUtil2;

public abstract class OxygenMakingBlockEntity extends AbstractMachineBlockEntity {
    public static final ResourceLocation TANK_INPUT = new ResourceLocation(BeyondEarth.MODID, "input");
    public static final ResourceLocation TANK_OUTPUT = new ResourceLocation(BeyondEarth.MODID, "output");
    public static final int SLOT_INPUT_SOURCE = 0;
    public static final int SLOT_INPUT_SINK = 1;

    private FluidTank inputTank;
    private OxygenStorage outputTank;

    private final StackCacher recipeCacher;
    private OxygenMakingRecipeAbstract cachedRecipe;

    public OxygenMakingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        this.recipeCacher = new StackCacher();
        this.cachedRecipe = null;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        this.getOutputTank().deserializeNBT(compound.getCompound("outputTank"));
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);

        compound.put("outputTank", this.getOutputTank().serializeNBT());
    }

    @Override
    public List<IGaugeValue> getDisplayGaugeValues() {
        List<IGaugeValue> list = super.getDisplayGaugeValues();

        return list;
    }

    @Override
    protected void createFluidHandlers(NamedComponentRegistry<IFluidHandler> registry) {
        super.createFluidHandlers(registry);
        this.inputTank = (FluidTank) registry.computeIfAbsent(this.getInputTankName(), k -> this.creatFluidTank(k));
        this.outputTank = this.createOxygenTank(this.getOutputTankName());
    }

    protected int getInitialTankCapacity(ResourceLocation name) {
        return DEFAULT_TANK_CAPACITY;
    }

    protected FluidTank creatFluidTank(ResourceLocation name) {
        return new FluidTank(this.getInitialTankCapacity(name)) {
            @Override
            protected void onContentsChanged() {
                super.onContentsChanged();
                OxygenMakingBlockEntity.this.setChanged();
            }
        };
    }

    protected OxygenStorage createOxygenTank(ResourceLocation name) {
        OxygenStorage storage = new OxygenStorage(new IOxygenStorageHolder() {
            @Override
            public void onOxygenChanged(IOxygenStorage oxygenStorage, int oxygenDelta) {
                OxygenMakingBlockEntity.this.setChanged();
            }
        });
        storage.setMaxCapacity(this.getInitialTankCapacity(name));
        return storage;
    }

    @Override
    protected void tickProcessing() {
        this.drainSources();
        this.consumeIngredients();
        this.fillSinks();
    }

    public boolean consumeIngredients() {
        OxygenMakingRecipeAbstract recipe = this.cacheRecipe();

        if (recipe != null) {
            int oxygen = recipe.getOxygen();

            if (this.hasSpaceInOutput(oxygen)) {
                if (this.consumePowerForOperation() != null) {
                    this.getInputTank().drain(recipe.getInput().getAmount(), FluidAction.EXECUTE);
                    this.getOutputTank().receiveOxygen(oxygen, false);
                    this.setProcessedInThisTick();
                    return true;
                }
            }
        }

        return false;
    }

    protected void drainSources() {
        FluidUtil2.drainSource(this.getItemHandler(), this.getInputSourceSlot(), this.getInputTank(),
                this.getTransferPerTick());
    }

    protected void fillSinks() {
        FluidUtil2.fillSink(this.getItemHandler(), this.getInputSinkSlot(), this.getInputTank(),
                this.getTransferPerTick());
    }

    @Override
    public <T> LazyOptional<T> getCapabilityFluidHandler(Capability<T> capability, @Nullable Direction facing) {
        return LazyOptional.of(this::getInputTank).cast();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
        LazyOptional<T> oxygenCapability = OxygenUtil.getOxygenCapability(capability, this::getOutputTank);

        if (oxygenCapability.isPresent()) {
            return oxygenCapability;
        }

        return super.getCapability(capability, facing);
    }

    @Override
    protected void getSlotsForFace(Direction direction, List<Integer> slots) {
        super.getSlotsForFace(direction, slots);
        slots.add(this.getInputSourceSlot());
    }

    @Override
    protected boolean onCanPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getInputSourceSlot()) {
            return FluidUtil2.canDrain(stack);
        } else if (index == this.getInputSinkSlot()) {
            FluidTank tank = this.slotToFluidTank(index);
            return FluidUtil2.canFill(stack, tank.getFluid().getFluid());
        }

        return super.onCanPlaceItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == this.getInputSourceSlot()) {
            return !FluidUtil2.canDrain(stack);
        } else if (index == this.getInputSinkSlot()) {
            FluidTank tank = this.slotToFluidTank(index);
            return !FluidUtil2.canFill(stack, tank.getFluid().getFluid());
        }

        return super.canTakeItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean hasSpaceInOutput() {
        OxygenMakingRecipeAbstract recipe = this.cacheRecipe();
        return recipe != null && this.hasSpaceInOutput(recipe.getOxygen());
    }

    public boolean hasSpaceInOutput(int oxygen) {
        return hasSpaceInOutput(oxygen, this.getOutputTank());
    }

    public boolean hasSpaceInOutput(int oxygen, IOxygenStorage storage) {
        return (oxygen + storage.getOxygen()) <= storage.getMaxCapacity();
    }

    public OxygenMakingRecipeAbstract cacheRecipe() {
        FluidStack fluidStack = this.getInputTank().getFluid();

        if (fluidStack.isEmpty()) {
            this.recipeCacher.set(fluidStack);
            this.cachedRecipe = null;
        } else if (!this.recipeCacher.test(fluidStack)) {
            this.recipeCacher.set(fluidStack);
            this.cachedRecipe = this.getRecipeType().findFirst(this.getLevel(), r -> r.test(fluidStack));
        }

        return this.cachedRecipe;
    }

    public abstract BeyondEarthRecipeType<? extends OxygenMakingRecipeAbstract> getRecipeType();

    @Override
    protected int getInitialInventorySize() {
        return super.getInitialInventorySize() + 2;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    public int getInputSourceSlot() {
        return SLOT_INPUT_SOURCE;
    }

    public int getInputSinkSlot() {
        return SLOT_INPUT_SINK;
    }

    public boolean isSourceSlot(int slot) {
        return slot == this.getInputSourceSlot();
    }

    public boolean isSinkSlot(int slot) {
        return slot == this.getInputSinkSlot();
    }

    public FluidTank slotToFluidTank(int slot) {
        if (slot == this.getInputSourceSlot() || slot == this.getInputSinkSlot()) {
            return this.getInputTank();
        } else {
            return null;
        }
    }

    public IOxygenStorage slotToOxygenTank(int slot) {
        return null;
    }

    public ResourceLocation slotToTankName(int slot) {
        if (slot == this.getInputSourceSlot() || slot == this.getInputSinkSlot()) {
            return this.getInputTankName();
        } else {
            return null;
        }
    }

    public ResourceLocation getInputTankName() {
        return TANK_INPUT;
    }

    public FluidTank getInputTank() {
        return this.inputTank;
    }

    public ResourceLocation getOutputTankName() {
        return TANK_OUTPUT;
    }

    public OxygenStorage getOutputTank() {
        return this.outputTank;
    }

    public int getTransferPerTick() {
        return DEFAULT_TANK_TRANSFER;
    }

}
