package net.mrscauthd.beyond_earth;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.mrscauthd.beyond_earth.compats.CompatibleManager;
import net.mrscauthd.beyond_earth.config.Config;

import net.mrscauthd.beyond_earth.registries.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(BeyondEarthMod.MODID)
public class BeyondEarthMod {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "beyond_earth";

	public BeyondEarthMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.register(this);

		/** CONFIG REGISTRIES */
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "beyond_earth-common.toml");

		/** NETWORK REGISTRIES */
		NetworksRegistry.register();

		/** COMPAT REGISTRIES */
		CompatibleManager.visit();

		/** DEFAULT REGISTRIES */
		ItemsRegistry.ITEMS.register(bus);
		BlocksRegistry.BLOCKS.register(bus);
		FluidsRegistry.FLUIDS.register(bus);
		EntitiesRegistry.ENTITIES.register(bus);
		BlockEntitiesRegistry.BLOCK_ENTITIES.register(bus);
		RocketPartsRegistry.ROCKET_PARTS.register(bus);
		PaintingsRegistry.PAINTINGS.register(bus);
		SensorsRegistry.SENSOR.register(bus);
		RecipeSerializersRegistry.RECIPE_SERIALIZERS.register(bus);
		SoundsRegistry.SOUNDS.register(bus);
		EffectsRegistry.EFFECTS.register(bus);
		ParticlesRegistry.PARTICLES.register(bus);
		ScreensRegistry.SCREENS.register(bus);
		StructuresRegistry.STRUCTURES.register(bus);
		FeatureRegistry.FEATURES.register(bus);
		FeatureRegistry.CONFIGURED_FEATURES.register(bus);
		FeatureRegistry.PLACED_FEATURES.register(bus);
	}
}
