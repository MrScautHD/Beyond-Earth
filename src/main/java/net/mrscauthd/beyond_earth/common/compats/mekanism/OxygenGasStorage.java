package net.mrscauthd.beyond_earth.common.compats.mekanism;

import mekanism.api.Action;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasHandler;
import mekanism.common.registries.MekanismGases;
import net.minecraft.nbt.CompoundTag;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;

public class OxygenGasStorage implements IOxygenStorage {

    private final IGasHandler gasHandler;
    private final boolean canExtract;
    private final boolean canReceive;

    public OxygenGasStorage(IGasHandler gasHandler) {
        this(gasHandler, true, true);
    }

    public OxygenGasStorage(IGasHandler gasHandler, boolean canExtract, boolean canReceive) {
        this.gasHandler = gasHandler;
        this.canExtract = canExtract;
        this.canReceive = canReceive;
    }

    public Gas getGas() {
        return MekanismGases.OXYGEN.get();
    }

    protected GasStack createGasStack(int amount) {
        return new GasStack(this.getGas(), amount);
    }

    @Override
    public int receiveOxygen(int maxReceive, boolean simulate) {
        if (!this.isCanReceive()) {
            return 0;
        }

        GasStack toInsert = this.createGasStack(maxReceive);
        GasStack remain = this.getGasHandler().insertChemical(toInsert, Action.get(!simulate));

        if (remain.isEmpty()) {
            return maxReceive;
        } else {
            return (int) (maxReceive - remain.getAmount());
        }

    }

    @Override
    public int extractOxygen(int maxExtract, boolean simulate) {
        if (!this.isCanExtract()) {
            return 0;
        }

        return (int) this.getGasHandler().extractChemical(maxExtract, Action.get(!simulate)).getAmount();
    }

    @Override
    public int getOxygen() {
        long stored = 0;
        IGasHandler gasHandler = this.getGasHandler();
        int tanks = gasHandler.getTanks();

        for (int i = 0; i < tanks; i++) {
            GasStack stack = gasHandler.getChemicalInTank(i);

            if (stack.getType() == this.getGas()) {
                stored = Math.max(stored + Math.max(stack.getAmount(), Integer.MAX_VALUE), Integer.MAX_VALUE);
            }
        }

        return (int) stored;
    }

    @Override
    public void setOxygen(int oxygen) {
        IGasHandler gasHandler = this.getGasHandler();
        int tanks = gasHandler.getTanks();

        for (int i = 0; i < tanks; i++) {
            GasStack stack = gasHandler.getChemicalInTank(i);

            if (stack.getType() == this.getGas()) {
                gasHandler.setChemicalInTank(i, GasStack.EMPTY);
            }
        }

        gasHandler.insertChemical(this.createGasStack(tanks), Action.EXECUTE);
    }

    @Override
    public int getMaxCapacity() {
        long capacity = 0;
        IGasHandler gasHandler = this.getGasHandler();
        int tanks = gasHandler.getTanks();

        for (int i = 0; i < tanks; i++) {
            if (gasHandler.isValid(i, this.createGasStack(1))) {
                capacity = Math.max(capacity + Math.max(gasHandler.getTankCapacity(i), Integer.MAX_VALUE), Integer.MAX_VALUE);
            }
        }

        return (int) capacity;
    }

    public IGasHandler getGasHandler() {
        return this.gasHandler;
    }

    public boolean isCanExtract() {
        return this.canExtract;
    }

    public boolean isCanReceive() {
        return this.canReceive;
    }

    @Override
    public CompoundTag serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}