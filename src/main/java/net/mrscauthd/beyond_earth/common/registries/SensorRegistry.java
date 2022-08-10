package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.pygro.PygroMobsSensor;

public class SensorRegistry {

    public static final DeferredRegister<SensorType<?>> SENSOR = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, BeyondEarth.MODID);

    /** SENSORS */
    public static final RegistryObject<SensorType<PygroMobsSensor>> PYGRO_SENSOR = SENSOR.register("pygro_sensor", ()-> new SensorType<>(PygroMobsSensor::new));
}
