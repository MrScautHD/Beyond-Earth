package net.mrscauthd.beyond_earth;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.mrscauthd.beyond_earth.compats.CompatibleManager;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.entities.alien.AlienTrade;
import net.mrscauthd.beyond_earth.config.Config;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGui;
import net.mrscauthd.beyond_earth.keybinds.KeyBindings;
import net.mrscauthd.beyond_earth.machines.tile.OxygenBubbleDistributorBlockEntity;

import net.mrscauthd.beyond_earth.registries.*;
import net.mrscauthd.beyond_earth.world.oregen.OreGeneration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.MinecraftForge;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod(BeyondEarthMod.MODID)
public class BeyondEarthMod {

	/** LOGGER */
	public static final Logger LOGGER = LogManager.getLogger();

	/** MODID */
	public static final String MODID = "beyond_earth";

	/** PACKET HANDLER */
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(BeyondEarthMod.MODID, BeyondEarthMod.MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID;

	public BeyondEarthMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		bus.register(this);

		/**Config*/
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "beyond_earth-common.toml");

		/** Old REGISTRIES */
		ModInit.ITEMS.register(bus);
		ModInit.BLOCKS.register(bus);
		ModInit.TILE_ENTITIES.register(bus);
		ModInit.FLUIDS.register(bus);
		ModInit.ROCKET_PARTS.register(bus);

		/** NEW REGISTRIES */
		EntitiesRegistry.ENTITIES.register(bus);
		PaintingsRegistry.PAINTINGS.register(bus);
		SensorsRegistry.SENSOR.register(bus);
		RecipeSerializersRegistry.RECIPE_SERIALIZERS.register(bus);
		SoundsRegistry.SOUNDS.register(bus);
		EffectsRegistry.EFFECTS.register(bus);
		ParticlesRegistry.PARTICLES.register(bus);
		ScreensRegistry.SCREENS.register(bus);
		StructuresRegistry.STRUCTURES.register(bus);
		FeatureRegistry.FEATURES.register(bus);

		//TODO CLEAN UP KEYBIND NETWORKER
		KeyBindings.registerMessages();

		BeyondEarthMod.addNetworkMessage(OxygenBubbleDistributorBlockEntity.ChangeRangeMessage.class, OxygenBubbleDistributorBlockEntity.ChangeRangeMessage::encode, OxygenBubbleDistributorBlockEntity.ChangeRangeMessage::decode, OxygenBubbleDistributorBlockEntity.ChangeRangeMessage::handle);
		BeyondEarthMod.addNetworkMessage(OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage.class, OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage::encode, OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage::decode, OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage::handle);
		BeyondEarthMod.addNetworkMessage(PlanetSelectionGui.NetworkHandler.class, PlanetSelectionGui.NetworkHandler::encode, PlanetSelectionGui.NetworkHandler::decode, PlanetSelectionGui.NetworkHandler::handle);

		CompatibleManager.visit();
	}

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
}
