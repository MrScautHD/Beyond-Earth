package net.mrscauthd.beyond_earth.registries;

import net.minecraft.world.entity.decoration.Motive;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

public class PaintingsRegistry {

	public static final DeferredRegister<Motive> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, BeyondEarthMod.MODID);

	/** PAINTINGS */
	public static final RegistryObject<Motive> PAINTING_THE_MILKY_WAY = PAINTINGS.register("the_milky_way", () -> new Motive(64, 48));
	public static final RegistryObject<Motive> PAINTING_SUN = PAINTINGS.register("sun", () -> new Motive(80, 80));
	public static final RegistryObject<Motive> PAINTING_EARTH = PAINTINGS.register("earth", () -> new Motive(32, 32));
	public static final RegistryObject<Motive> PAINTING_MOON = PAINTINGS.register("moon", () -> new Motive(16, 16));
	public static final RegistryObject<Motive> PAINTING_MARS = PAINTINGS.register("mars", () -> new Motive(32, 32));
	public static final RegistryObject<Motive> PAINTING_MERCURY = PAINTINGS.register("mercury", () -> new Motive(16, 16));
	public static final RegistryObject<Motive> PAINTING_VENUS = PAINTINGS.register("venus", () -> new Motive(32, 32));
	public static final RegistryObject<Motive> PAINTING_NEPTUNE = PAINTINGS.register("neptune", () -> new Motive(48, 48));
	public static final RegistryObject<Motive> PAINTING_SATURN = PAINTINGS.register("saturn", () -> new Motive(64, 48));
	public static final RegistryObject<Motive> PAINTING_JUPITER = PAINTINGS.register("jupiter", () -> new Motive(48, 48));
	public static final RegistryObject<Motive> PAINTING_PLUTO = PAINTINGS.register("pluto", () -> new Motive(16, 16));
	public static final RegistryObject<Motive> PAINTING_URANUS = PAINTINGS.register("uranus", () -> new Motive(48, 48));
	public static final RegistryObject<Motive> PAINTING_PROXIMA_CENTAURY = PAINTINGS.register("proxima_centaury", () -> new Motive(64, 64));
	public static final RegistryObject<Motive> PAINTING_GLACIO = PAINTINGS.register("glacio", () -> new Motive(32, 32));
}