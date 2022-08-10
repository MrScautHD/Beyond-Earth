package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.fluids.FuelFluid;
import net.mrscauthd.beyond_earth.common.fluids.OilFluid;

public class FluidRegistry {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, BeyondEarth.MODID);

    /** FUEL FLUIDS */
    public static final RegistryObject<FlowingFluid> FLOWING_FUEL = FLUIDS.register("flowing_fuel", () -> new FuelFluid.Flowing());
    public static final RegistryObject<FlowingFluid> FUEL_STILL = FLUIDS.register("fuel", () -> new FuelFluid.Source());

    /** OIL FLUIDS */
    public static final RegistryObject<FlowingFluid> FLOWING_OIL = FLUIDS.register("flowing_oil", () -> new OilFluid.Flowing());
    public static final RegistryObject<FlowingFluid> OIL_STILL = FLUIDS.register("oil", () -> new OilFluid.Source());
}
