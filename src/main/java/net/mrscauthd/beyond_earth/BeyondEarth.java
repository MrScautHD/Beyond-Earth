package net.mrscauthd.beyond_earth;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.registries.*;
import net.mrscauthd.beyond_earth.common.world.ModPlacedFeature;
import net.mrscauthd.beyond_earth.common.world.modifiers.ModBiomeModifiers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(BeyondEarth.MODID)
public class BeyondEarth {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "beyond_earth";

	public BeyondEarth() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.register(this);

		/** CONFIG REGISTRIES */
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "beyond_earth-common.toml");

		/** NETWORK REGISTRIES */
		NetworkRegistry.register();

		/** DEFAULT REGISTRIES */
		ItemsRegistry.ITEMS.register(bus);
		BlockRegistry.BLOCKS.register(bus);
		FluidTypeRegistry.FLUID_TYPES.register(bus);
		FluidRegistry.FLUIDS.register(bus);
		EntityRegistry.ENTITIES.register(bus);
		BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
		PaintingRegistry.PAINTINGS.register(bus);
		SensorRegistry.SENSOR.register(bus);
                RecipeSerializersRegistry.RECIPE_SERIALIZERS.register(bus);
                RecipeTypeRegistry.RECIPE_TYPES.register(bus);
                RocketPartsRegistry.ROCKET_PARTS.register(bus);
		SoundRegistry.SOUNDS.register(bus);
		ParticleRegistry.PARTICLES.register(bus);
		ContainerRegistry.CONTAINERS.register(bus);
		StructureRegistry.STRUCTURES.register(bus);
		FeatureRegistry.FEATURES.register(bus);
		ModPlacedFeature.PLACED_FEATURE.register(bus);
		ModBiomeModifiers.BIOME_MODIFIERS.register(bus);
	}
}
