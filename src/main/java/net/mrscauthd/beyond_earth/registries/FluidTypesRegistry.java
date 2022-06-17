package net.mrscauthd.beyond_earth.registries;

import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.fluids.types.FuelFluidType;
import net.mrscauthd.beyond_earth.fluids.types.OilFluidType;

public class FluidTypesRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BeyondEarth.MODID);

    public static final RegistryObject<FluidType> FUEL_TYPE = FLUID_TYPES.register("fuel", () -> new FuelFluidType(FluidType.Properties.create()
            .descriptionId("block." + BeyondEarth.MODID + ".fuel")
            .fallDistanceModifier(0F)
            .canExtinguish(true)
            .supportsBoating(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
            .canHydrate(true)
    ));

    public static final RegistryObject<FluidType> OIL_TYPE = FLUID_TYPES.register("oil", () -> new OilFluidType(FluidType.Properties.create()
            .descriptionId("block." + BeyondEarth.MODID + ".oil")
            .fallDistanceModifier(0F)
            .density(3000)
            .viscosity(6000)
            .canSwim(false)
            .canExtinguish(true)
            .supportsBoating(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
            .canHydrate(true)
    ));
}
