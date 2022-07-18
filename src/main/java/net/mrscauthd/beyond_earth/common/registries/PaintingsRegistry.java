package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;

public class PaintingsRegistry {

	public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, BeyondEarth.MODID);

	/** PAINTINGS */
	public static final RegistryObject<PaintingVariant> PAINTING_THE_MILKY_WAY = PAINTINGS.register("the_milky_way", () -> new PaintingVariant(64, 48));
	public static final RegistryObject<PaintingVariant> PAINTING_SUN = PAINTINGS.register("sun", () -> new PaintingVariant(80, 80));
	public static final RegistryObject<PaintingVariant> PAINTING_EARTH = PAINTINGS.register("earth", () -> new PaintingVariant(32, 32));
	public static final RegistryObject<PaintingVariant> PAINTING_MOON = PAINTINGS.register("moon", () -> new PaintingVariant(16, 16));
	public static final RegistryObject<PaintingVariant> PAINTING_MARS = PAINTINGS.register("mars", () -> new PaintingVariant(32, 32));
	public static final RegistryObject<PaintingVariant> PAINTING_MERCURY = PAINTINGS.register("mercury", () -> new PaintingVariant(16, 16));
	public static final RegistryObject<PaintingVariant> PAINTING_VENUS = PAINTINGS.register("venus", () -> new PaintingVariant(32, 32));
	public static final RegistryObject<PaintingVariant> PAINTING_NEPTUNE = PAINTINGS.register("neptune", () -> new PaintingVariant(48, 48));
	public static final RegistryObject<PaintingVariant> PAINTING_SATURN = PAINTINGS.register("saturn", () -> new PaintingVariant(64, 48));
	public static final RegistryObject<PaintingVariant> PAINTING_JUPITER = PAINTINGS.register("jupiter", () -> new PaintingVariant(48, 48));
	public static final RegistryObject<PaintingVariant> PAINTING_PLUTO = PAINTINGS.register("pluto", () -> new PaintingVariant(16, 16));
	public static final RegistryObject<PaintingVariant> PAINTING_URANUS = PAINTINGS.register("uranus", () -> new PaintingVariant(48, 48));
	public static final RegistryObject<PaintingVariant> PAINTING_PROXIMA_CENTAURY = PAINTINGS.register("proxima_centaury", () -> new PaintingVariant(64, 64));
	public static final RegistryObject<PaintingVariant> PAINTING_GLACIO = PAINTINGS.register("glacio", () -> new PaintingVariant(32, 32));
}