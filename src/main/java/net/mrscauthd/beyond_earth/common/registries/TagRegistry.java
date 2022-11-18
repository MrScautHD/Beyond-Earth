package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.material.Fluid;
import net.mrscauthd.beyond_earth.BeyondEarth;

public class TagRegistry {

    /** ENTITIES */
    public static final TagKey<EntityType<?>> ENTITY_NO_OXYGEN_NEEDED_TAG = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "no_oxygen_needed"));
    public static final TagKey<EntityType<?>> ENTITY_PLANET_FIRE_TAG = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "planet_fire"));
    public static final TagKey<EntityType<?>> ENTITY_VENUS_RAIN_TAG = TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "venus_rain"));

    /** FLUIDS */
    public static final TagKey<Fluid> FLUID_VEHICLE_FUEL_TAG = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "vehicle_fuel"));
    public static final TagKey<Fluid> FLUID_OIL_FLUID_TAG = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(BeyondEarth.MODID, "oil"));
}
