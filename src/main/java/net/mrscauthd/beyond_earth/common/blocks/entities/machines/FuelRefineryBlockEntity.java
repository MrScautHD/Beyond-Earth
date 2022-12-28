package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.NamedComponentRegistry;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemEnergyCommon;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemRegistry;
import net.mrscauthd.beyond_earth.common.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.common.capabilities.fluid.FluidMultiTank;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.FluidIngredient;
import net.mrscauthd.beyond_earth.common.data.recipes.FuelRefiningRecipe;
import net.mrscauthd.beyond_earth.common.menus.FuelRefineryMenu;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.StackCacher;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;
import net.mrscauthd.beyond_earth.common.util.FluidUtil2;

public class FuelRefineryBlockEntity extends AbstractMachineBlockEntity {

    public static final int DEFAULT_ENERGY_USAGE = 1;
    public static final ResourceLocation TANK_INPUT = new ResourceLocation(BeyondEarth.MODID, "input");
    public static final ResourceLocation TANK_OUTPUT = new ResourceLocation(BeyondEarth.MODID, "output");
    public static final int SLOT_INPUT_SOURCE = 0;
    public static final int SLOT_OUTPUT_SINK = 1;
    public static final int SLOT_INPUT_SINK = 2;
    public static final int SLOT_OUTPUT_SOURCE = 3;

    private FluidTank inputTank;
    private FluidTank outputTank;
    private FluidMultiTank tanks;

    private final StackCacher recipeCacher;
    private FuelRefiningRecipe cachedRecipe;

    public FuelRefineryBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FUEL_REFINERY_BLOCK_ENTITY.get(), pos, state);

        this.recipeCacher = new StackCacher();
        this.cachedRecipe = null;
    }

    @Override
    protected void createEnergyStorages(NamedComponentRegistry<IEnergyStorage> registry) {
        super.createEnergyStorages(registry);
        int capacity = Config.FUEL_REFINERY_ENERGY_CAPACITY.get();
        int maxTransfer = Config.FUEL_REFINERY_ENERGY_TRANSFER.get();
        registry.put(new EnergyStorageBasic(this, capacity, maxTransfer, capacity));
    }

    @Override
    protected void createFluidHandlers(NamedComponentRegistry<IFluidHandler> registry) {
        super.createFluidHandlers(registry);
        this.inputTank = (FluidTank) registry.computeIfAbsent(this.getInputTankName(), k -> this.creatTank(k));
        this.outputTank = (FluidTank) registry.computeIfAbsent(this.getOutputTankName(), k -> this.creatTank(k));
        this.tanks = new FluidMultiTank(Arrays.asList(this.getInputTank(), this.getOutputTank()));
    }

    protected int getInitialTankCapacity(ResourceLocation name) {
        if (name.equals(this.getInputTankName())) {
            return Config.FUEL_REFINERY_TANK_INPUT_CAPACITY.get();
        } else if (name.equals(this.getOutputTankName())) {
            return Config.FUEL_REFINERY_TANK_OUTPUT_CAPACITY.get();
        } else {
            return DEFAULT_TANK_CAPACITY;
        }
    }

    protected FluidTank creatTank(ResourceLocation name) {
        return new FluidTank(this.getInitialTankCapacity(name)) {
            @Override
            protected void onContentsChanged() {
                super.onContentsChanged();
                FuelRefineryBlockEntity.this.setChanged();
            }
        };
    }

    @Override
    protected void createPowerSystems(PowerSystemRegistry map) {
        super.createPowerSystems(map);
        map.put(new PowerSystemEnergyCommon(this) {
            @Override
            public int getBasePowerForOperation() {
                return FuelRefineryBlockEntity.this.getBasePowerForOperation();
            }
        });
    }

    public int getBasePowerForOperation() {
        return Config.FUEL_REFINERY_ENERGY_USAGE.get();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new FuelRefineryMenu.GuiContainer(id, inventory, this);
    }

    @Override
    protected void tickProcessing() {
        this.drainSources();
        this.consumeIngredients();
        this.fillSinks();
    }

    public boolean consumeIngredients() {
        FuelRefiningRecipe recipe = this.cacheRecipe();

        if (recipe != null) {
            FluidIngredient recipeOutput = recipe.getOutput();

            if (this.hasSpaceInOutput(recipeOutput)) {
                if (this.consumePowerForOperation() != null) {
                    this.getInputTank().drain(recipe.getInput().getAmount(), FluidAction.EXECUTE);
                    this.getOutputTank().fill(recipeOutput.toStack(), FluidAction.EXECUTE);
                    this.setProcessedInThisTick();
                    return true;
                }
            }
        }

        return false;
    }

    protected void drainSources() {
        IItemHandlerModifiable itemHandler = this.getItemHandler();
        int transferPerTick = this.getTransferPerTick();
        FluidUtil2.drainSource(itemHandler, this.getInputSourceSlot(), this.getInputTank(), transferPerTick);
        FluidUtil2.drainSource(itemHandler, this.getOutputSourceSlot(), this.getOutputTank(), transferPerTick);
    }

    protected void fillSinks() {
        IItemHandlerModifiable itemHandler = this.getItemHandler();
        int transferPerTick = this.getTransferPerTick();
        FluidUtil2.fillSink(itemHandler, this.getInputSinkSlot(), this.getInputTank(), transferPerTick);
        FluidUtil2.fillSink(itemHandler, this.getOutputSinkSlot(), this.getOutputTank(), transferPerTick);
    }

    @Override
    public <T> LazyOptional<T> getCapabilityFluidHandler(Capability<T> capability, @Nullable Direction facing) {
        if (facing == null) {
            return LazyOptional.of(this::getTanks).cast();
        }
        if (facing == Direction.DOWN) {
            return LazyOptional.of(this::getOutputTank).cast();
        } else {
            return LazyOptional.of(this::getInputTank).cast();
        }
    }

    @Override
    protected void getSlotsForFace(Direction direction, List<Integer> slots) {
        super.getSlotsForFace(direction, slots);
        slots.add(this.getOutputSourceSlot());
        slots.add(this.getInputSourceSlot());
        slots.add(this.getOutputSinkSlot());
    }

    @Override
    protected boolean onCanPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (this.isSourceSlot(index)) {
            return FluidUtil2.canDrain(stack);
        } else if (this.isSinkSlot(index)) {
            FluidTank tank = this.slotToTank(index);
            return FluidUtil2.canFill(stack, tank.getFluid().getFluid());
        }

        return super.onCanPlaceItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (this.isSourceSlot(index)) {
            return !FluidUtil2.canDrain(stack);
        } else if (this.isSinkSlot(index)) {
            FluidTank tank = this.slotToTank(index);
            return !FluidUtil2.canFill(stack, tank.getFluid().getFluid());
        }

        return super.canTakeItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean hasSpaceInOutput() {
        FuelRefiningRecipe recipe = this.cacheRecipe();
        return recipe != null && this.hasSpaceInOutput(recipe.getOutput());
    }

    public boolean hasSpaceInOutput(FluidIngredient recipeOutput) {
        return hasSpaceInOutput(recipeOutput, this.getOutputTank());
    }

    public FuelRefiningRecipe cacheRecipe() {
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

    public BeyondEarthRecipeType<? extends FuelRefiningRecipe> getRecipeType() {
        return RecipeTypeRegistry.FUEL_REFINING.get();
    }

    @Override
    protected int getInitialInventorySize() {
        return super.getInitialInventorySize() + 4;
    }

    public int getInputSourceSlot() {
        return SLOT_INPUT_SOURCE;
    }

    public int getInputSinkSlot() {
        return SLOT_INPUT_SINK;
    }

    public int getOutputSourceSlot() {
        return SLOT_OUTPUT_SOURCE;
    }

    public int getOutputSinkSlot() {
        return SLOT_OUTPUT_SINK;
    }

    public boolean isSourceSlot(int slot) {
        return slot == this.getInputSourceSlot() || slot == this.getOutputSourceSlot();
    }

    public boolean isSinkSlot(int slot) {
        return slot == this.getInputSinkSlot() || slot == this.getOutputSinkSlot();
    }

    public FluidTank slotToTank(int slot) {
        if (slot == this.getInputSourceSlot() || slot == this.getInputSinkSlot()) {
            return this.getInputTank();
        } else if (slot == this.getOutputSourceSlot() || slot == this.getOutputSinkSlot()) {
            return this.getOutputTank();
        } else {
            return null;
        }
    }

    public ResourceLocation slotToTankName(int slot) {
        if (slot == this.getInputSourceSlot() || slot == this.getInputSinkSlot()) {
            return this.getInputTankName();
        } else if (slot == this.getOutputSourceSlot() || slot == this.getOutputSinkSlot()) {
            return this.getOutputTankName();
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

    public FluidTank getOutputTank() {
        return this.outputTank;
    }

    public int getTransferPerTick() {
        return Config.FUEL_REFINERY_TANK_TRANSFER.get();
    }

    public FluidMultiTank getTanks() {
        return this.tanks;
    }

}