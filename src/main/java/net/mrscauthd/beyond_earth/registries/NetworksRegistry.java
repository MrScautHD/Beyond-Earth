package net.mrscauthd.beyond_earth.registries;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGuiNetworkHandler;
import net.mrscauthd.beyond_earth.keybinds.KeyHandler;
import net.mrscauthd.beyond_earth.machines.tile.OxygenBubbleDistributorBlockEntity;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class NetworksRegistry {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(BeyondEarthMod.MODID, BeyondEarthMod.MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID;

    public static void register() {
        addNetworkMessage(OxygenBubbleDistributorBlockEntity.ChangeRangeMessage.class, OxygenBubbleDistributorBlockEntity.ChangeRangeMessage::encode, OxygenBubbleDistributorBlockEntity.ChangeRangeMessage::decode, OxygenBubbleDistributorBlockEntity.ChangeRangeMessage::handle);
        addNetworkMessage(OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage.class, OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage::encode, OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage::decode, OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage::handle);
        addNetworkMessage(PlanetSelectionGuiNetworkHandler.class, PlanetSelectionGuiNetworkHandler::encode, PlanetSelectionGuiNetworkHandler::decode, PlanetSelectionGuiNetworkHandler::handle);
        addNetworkMessage(KeyHandler.class, KeyHandler::encode, KeyHandler::decode, KeyHandler::handle);
    }

    private static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }
}
