package net.mrscauthd.beyond_earth.guis.screens.planetselection;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.PlanetSelectionGuiNetworkHandlerHelper;

import java.util.function.Supplier;

public class PlanetSelectionGuiNetworkHandler extends PlanetSelectionGuiNetworkHandlerHelper {
    public int integer;

    public PlanetSelectionGuiNetworkHandler(int integer) {
        this.integer = integer;
    }

    public PlanetSelectionGuiNetworkHandler(FriendlyByteBuf buffer) {
        this.integer = buffer.readInt();
    }

    public static PlanetSelectionGuiNetworkHandler decode(FriendlyByteBuf buffer) {
        return new PlanetSelectionGuiNetworkHandler(buffer);
    }

    public static void encode(PlanetSelectionGuiNetworkHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.integer);
    }

    public static void handle(PlanetSelectionGuiNetworkHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            switch (message.integer) {

                /** (SUN CATEGORY) TELEPORT BUTTONS */
                case 0:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.overworld, false);
                    break;

                case 1:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.moon, false);
                    break;

                case 2:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.mars, false);
                    break;

                case 3:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.mercury, false);
                    break;

                case 4:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.venus, false);
                    break;

                /** (SUN CATEGORY) TELEPORT ORBIT BUTTONS */
                case 5:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.earth_orbit, false);
                    break;

                case 6:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.moon_orbit, false);
                    break;

                case 7:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.mars_orbit, false);
                    break;

                case 8:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.mercury_orbit, false);
                    break;

                case 9:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.venus_orbit, false);
                    break;

                /** (SUN CATEGORY) TELEPORT ORBIT AND CREATE A SPACE STATION BUTTON */
                case 10:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, Methods.earth_orbit, true);
                    break;

                case 11:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, Methods.moon_orbit, true);
                    break;

                case 12:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, Methods.mars_orbit, true);
                    break;

                case 13:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, Methods.mercury_orbit, true);
                    break;

                case 14:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, Methods.venus_orbit, true);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT BUTTONS */
                case 15:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.glacio, false);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT ORBIT BUTTONS */
                case 16:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, Methods.glacio_orbit, false);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT ORBIT AND CREATE A SPACE STATION BUTTON */
                case 17:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, Methods.glacio_orbit, true);
                    break;
            }
        });

        context.setPacketHandled(true);
    }
}
