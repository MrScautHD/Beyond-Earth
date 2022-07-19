package net.mrscauthd.beyond_earth.common.menus.planetselection;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.common.util.Methods;
import net.mrscauthd.beyond_earth.common.menus.planetselection.helper.PlanetSelectionMenuNetworkHandlerHelper;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;

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
                    Methods.createLanderAndTeleportTo(player, Level.OVERWORLD, 700, false);
                    break;

                case 1:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MOON, 700, false);
                    break;

                case 2:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MARS, 700, false);
                    break;

                case 3:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MERCURY, 700, false);
                    break;

                case 4:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.VENUS, 700, false);
                    break;

                /** (SUN CATEGORY) TELEPORT ORBIT BUTTONS */
                case 5:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.EARTH_ORBIT, 700, false);
                    break;

                case 6:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MOON_ORBIT, 700, false);
                    break;

                case 7:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MARS_ORBIT, 700, false);
                    break;

                case 8:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MERCURY_ORBIT, 700, false);
                    break;

                case 9:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.VENUS_ORBIT, 700, false);
                    break;

                /** (SUN CATEGORY) TELEPORT ORBIT AND CREATE A SPACE STATION BUTTON */
                case 10:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.EARTH_ORBIT, 700, true);
                    break;

                case 11:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MOON_ORBIT, 700, true);
                    break;

                case 12:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MARS_ORBIT, 700, true);
                    break;

                case 13:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.MERCURY_ORBIT, 700, true);
                    break;

                case 14:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.VENUS_ORBIT, 700, true);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT BUTTONS */
                case 15:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.GLACIO, 700, false);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT ORBIT BUTTONS */
                case 16:
                    message.defaultOptions(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.GLACIO_ORBIT, 700, false);
                    break;

                /** (PROXIMA CENTAURI) TELEPORT ORBIT AND CREATE A SPACE STATION BUTTON */
                case 17:
                    message.defaultOptions(player);
                    message.deleteItems(player);
                    Methods.createLanderAndTeleportTo(player, LevelRegistry.GLACIO_ORBIT, 700, true);
                    break;
            }
        });

        context.setPacketHandled(true);
    }
}
