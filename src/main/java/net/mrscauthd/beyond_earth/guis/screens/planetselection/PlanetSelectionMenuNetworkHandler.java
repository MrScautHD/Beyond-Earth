package net.mrscauthd.beyond_earth.guis.screens.planetselection;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.PlanetSelectionMenuNetworkHandlerHelper;
import net.mrscauthd.beyond_earth.registries.LevelRegistry;

import java.util.function.Supplier;

public class PlanetSelectionMenuNetworkHandler extends PlanetSelectionMenuNetworkHandlerHelper {
    public int integer;

    public PlanetSelectionMenuNetworkHandler(int integer) {
        this.integer = integer;
    }

    public PlanetSelectionMenuNetworkHandler(FriendlyByteBuf buffer) {
        this.integer = buffer.readInt();
    }

    public static PlanetSelectionMenuNetworkHandler decode(FriendlyByteBuf buffer) {
        return new PlanetSelectionMenuNetworkHandler(buffer);
    }

    public static void encode(PlanetSelectionMenuNetworkHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.integer);
    }

    public static void handle(PlanetSelectionMenuNetworkHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            switch (message.integer) {

                /** (SUN CATEGORY) TELEPORT BUTTONS */
                case 0:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.EARTH, false);
                    break;

                case 1:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.MOON, false);
                    break;

                case 2:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.MARS, false);
                    break;

                case 3:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.MERCURY, false);
                    break;

                case 4:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.VENUS, false);
                    break;

                /** (SUN CATEGORY) TELEPORT ORBIT BUTTONS */
                case 5:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.EARTH_ORBIT, false);
                    break;

                case 6:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.MOON_ORBIT, false);
                    break;

                case 7:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.MARS_ORBIT, false);
                    break;

                case 8:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.MERCURY_ORBIT, false);
                    break;

                case 9:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.VENUS_ORBIT, false);
                    break;

                /** (SUN CATEGORY) TELEPORT ORBIT AND CREATE A SPACE STATION BUTTON */
                case 10:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, LevelRegistry.EARTH_ORBIT, true);
                    break;

                case 11:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, LevelRegistry.MOON_ORBIT, true);
                    break;

                case 12:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, LevelRegistry.MARS_ORBIT, true);
                    break;

                case 13:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, LevelRegistry.MERCURY_ORBIT, true);
                    break;

                case 14:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, LevelRegistry.VENUS_ORBIT, true);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT BUTTONS */
                case 15:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.GLACIO, false);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT ORBIT BUTTONS */
                case 16:
                    message.defaultOptions(player);
                    Methods.teleportButton(player, LevelRegistry.GLACIO_ORBIT, false);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT ORBIT AND CREATE A SPACE STATION BUTTON */
                case 17:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.teleportButton(player, LevelRegistry.GLACIO_ORBIT, true);
                    break;
            }
        });

        context.setPacketHandled(true);
    }
}
