package net.mrscauthd.beyond_earth;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.mrscauthd.beyond_earth.common.config.Config;

import net.mrscauthd.beyond_earth.common.registries.*;
import net.mrscauthd.beyond_earth.registries.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
		NetworksRegistry.register();

		/** DEFAULT REGISTRIES */
		ItemsRegistry.ITEMS.register(bus);
		BlocksRegistry.BLOCKS.register(bus);
		FluidTypesRegistry.FLUID_TYPES.register(bus);
		FluidsRegistry.FLUIDS.register(bus);
		EntitiesRegistry.ENTITIES.register(bus);
		BlockEntitiesRegistry.BLOCK_ENTITIES.register(bus);
		PaintingsRegistry.PAINTINGS.register(bus);
		SensorsRegistry.SENSOR.register(bus);
		SoundsRegistry.SOUNDS.register(bus);
		EffectsRegistry.EFFECTS.register(bus);
		ParticlesRegistry.PARTICLES.register(bus);
		ContainersRegistry.CONTAINERS.register(bus);
		StructuresRegistry.STRUCTURES.register(bus);
		FeatureRegistry.FEATURES.register(bus);
	}
}
