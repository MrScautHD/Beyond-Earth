package net.mrscauthd.beyond_earth.registries;

import net.minecraft.world.entity.decoration.Motive;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

public class PaintingsRegistry {

	public static final DeferredRegister<Motive> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, BeyondEarthMod.MODID);

	/** PAINTINGS */
	public static final RegistryObject<Motive> PAINTING_EARTH = PAINTINGS.register("painting_earth", () -> new Motive(32, 16));
	public static final RegistryObject<Motive> PAINTING_GALAXY = PAINTINGS.register("painting_galaxy", () -> new Motive(32, 32));
	public static final RegistryObject<Motive> PAINTING_MATH = PAINTINGS.register("painting_math", () -> new Motive(32, 16));
	public static final RegistryObject<Motive> PAINTING_PLANET_MATH = PAINTINGS.register("painting_planet_math", () -> new Motive(64, 32));
	public static final RegistryObject<Motive> PAINTING_VENUS = PAINTINGS.register("painting_venus", () -> new Motive(16, 16));
}