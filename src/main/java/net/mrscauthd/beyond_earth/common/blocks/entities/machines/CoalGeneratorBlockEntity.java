package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.IEnergyStorage;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemFuelGeneratingRecipe;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemRegistry;
import net.mrscauthd.beyond_earth.common.blocks.machines.CoalGeneratorBlock;
import net.mrscauthd.beyond_earth.common.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.menus.CoalGeneratorMenu;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;

public class CoalGeneratorBlockEntity extends GeneratorBlockEntity {

    public static final int SLOT_FUEL = 0;
    public static final int DEFAULT_ENERGY_USAGE = 2;

    private PowerSystemFuelGeneratingRecipe powerSystemGenerating;

    public CoalGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COAL_GENERATOR_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new CoalGeneratorMenu.GuiContainer(id, inventory, this);
    }

    @Override
    public int getMaxGeneration() {
        return Config.COAL_GENERATOR_ENERGY_GENERATION.get();
    }

    protected int getGenerationInTick() {
        return this.getMaxGeneration();
    }

    @Override
    protected boolean canGenerateEnergy() {
        return true;
    }

    @Override
    protected void generateEnergy() {
        this.generateEnergy(this.getGenerationInTick());
    }

    @Override
    protected List<Direction> getEjectDirections() {
        List<Direction> list = super.getEjectDirections();
        list.add(Direction.UP);
        list.add(Direction.DOWN);

        Direction backwards = this.getBlockState().getValue(CoalGeneratorBlock.FACING).getOpposite();
        list.add(backwards);

        return list;
    }

    @Override
    protected void createPowerSystems(PowerSystemRegistry map) {
        super.createPowerSystems(map);
        map.put(this.powerSystemGenerating = new PowerSystemFuelGeneratingRecipe(this, this.getFuelSlot()));
    }

    @Override
    protected IEnergyStorage createGeneratingEnergyStorage() {
        int capacity = Config.COAL_GENERATOR_ENERGY_CAPACITY.get();
        int maxExtract = Config.COAL_GENERATOR_ENERGY_TRANSFER.get();
        return new EnergyStorageBasic(this, capacity, capacity, maxExtract);
    }

    public PowerSystemFuelGeneratingRecipe getPowerSystemGenerating() {
        return this.powerSystemGenerating;
    }

    public int getFuelSlot() {
        return SLOT_FUEL;
    }
}