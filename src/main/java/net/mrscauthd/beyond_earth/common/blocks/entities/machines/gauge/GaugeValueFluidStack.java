package net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

public class GaugeValueFluidStack implements IGaugeValue {

    private FluidStack stack;
    private int capacity;

    public GaugeValueFluidStack() {

    }

    public GaugeValueFluidStack(FluidStack stack, int capacity) {
        this.setStack(stack);
        this.setCapacity(capacity);
    }

    @Override
    public void deserializeNBT(CompoundTag compound) {
        this.setStack(FluidStack.loadFluidStackFromNBT(compound.getCompound("stack")));
        this.setCapacity(compound.getInt("capacity"));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        CompoundTag stackCompound = new CompoundTag();
        this.getStack().writeToNBT(stackCompound);
        compound.put("stack", stackCompound);
        compound.putInt("capacity", this.getCapacity());

        return compound;
    }

    @Override
    @OnlyIn(value = Dist.CLIENT)
    public int getColor() {
        FluidStack fluidStack = this.getStack();
        Fluid fluid = fluidStack.getFluid();
        if (fluid == Fluids.EMPTY
                || !(fluid.getFluidType().getRenderPropertiesInternal() instanceof IClientFluidTypeExtensions props)) {
            return 0;
        }
        return props.getTintColor(fluidStack);
    }

    public FluidStack getStack() {
        return this.stack;
    }

    public void setStack(FluidStack stack) {
        this.stack = stack;
    }

    @Override
    public Component getDisplayName() {
        return this.getStack().getDisplayName();
    }

    @Override
    public String getUnit() {
        return GaugeValueHelper.FLUID_UNIT;
    }

    @Override
    public int getAmount() {
        return this.getStack().getAmount();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean isReverse() {
        return false;
    }

}
