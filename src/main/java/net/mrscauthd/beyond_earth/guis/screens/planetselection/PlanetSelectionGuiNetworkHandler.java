package net.mrscauthd.beyond_earth.guis.screens.planetselection;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.PlanetSelectionGuiNetworkHandlerHelper;

import java.util.function.Supplier;

public class PlanetSelectionGuiNetworkHandler extends PlanetSelectionGuiNetworkHandlerHelper {
    private int integer = 0;

    public PlanetSelectionGuiNetworkHandler(int integer) {
        this.setInteger(integer);
    }

    public PlanetSelectionGuiNetworkHandler(FriendlyByteBuf buffer) {
        this.setInteger(buffer.readInt());
    }

    public int getInteger() {
        return this.integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public static PlanetSelectionGuiNetworkHandler decode(FriendlyByteBuf buffer) {
        return new PlanetSelectionGuiNetworkHandler(buffer);
    }

    public static void encode(PlanetSelectionGuiNetworkHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.getInteger());
    }

    public static void handle(PlanetSelectionGuiNetworkHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        ServerPlayer player = context.getSender();

        /** (SUN CATEGORY) TELEPORT BUTTONS */
        if (message.getInteger() == 0) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.overworld, false);
        }
        if (message.getInteger() == 1) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.moon, false);
        }
        if (message.getInteger() == 2) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.mars, false);
        }
        if (message.getInteger() == 3) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.mercury, false);
        }
        if (message.getInteger() == 4) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.venus, false);
        }

        /** (SUN CATEGORY) TELEPORT ORBIT BUTTONS */
        if (message.getInteger() == 5) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.earth_orbit, false);
        }
        if (message.getInteger() == 6) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.moon_orbit, false);
        }
        if (message.getInteger() == 7) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.mars_orbit, false);
        }
        if (message.getInteger() == 8) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.mercury_orbit, false);
        }
        if (message.getInteger() == 9) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.venus_orbit, false);
        }

        /** (SUN CATEGORY) TELEPORT ORBIT AND CREATE A SPACE STATION BUTTON */
        if (message.getInteger() == 10) {
            message.defaultOptions(player);
            message.deleteItems(player);
            Methods.teleportButton(player, Methods.earth_orbit, true);
        }
        if (message.getInteger() == 11) {
            message.defaultOptions(player);
            message.deleteItems(player);
            Methods.teleportButton(player, Methods.moon_orbit, true);
        }
        if (message.getInteger() == 12) {
            message.defaultOptions(player);
            message.deleteItems(player);
            Methods.teleportButton(player, Methods.mars_orbit, true);
        }
        if (message.getInteger() == 13) {
            message.defaultOptions(player);
            message.deleteItems(player);
            Methods.teleportButton(player, Methods.mercury_orbit, true);
        }
        if (message.getInteger() == 14) {
            message.defaultOptions(player);
            message.deleteItems(player);
            Methods.teleportButton(player, Methods.venus_orbit, true);
        }

        /** (PROXIMA CENTAURI) TELEPORT BUTTONS */
        if (message.getInteger() == 15) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.glacio, false);
        }

        /** (PROXIMA CENTAURI) TELEPORT ORBIT BUTTONS */
        if (message.getInteger() == 16) {
            message.defaultOptions(player);
            Methods.teleportButton(player, Methods.glacio_orbit, false);
        }

        /** (PROXIMA CENTAURI) TELEPORT ORBIT AND CREATE A SPACE STATION BUTTON */
        if (message.getInteger() == 17) {
            message.defaultOptions(player);
            message.deleteItems(player);
            Methods.teleportButton(player, Methods.glacio_orbit, true);
        }

        context.setPacketHandled(true);
    }
}
