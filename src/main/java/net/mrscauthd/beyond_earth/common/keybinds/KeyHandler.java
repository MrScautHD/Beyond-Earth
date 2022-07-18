package net.mrscauthd.beyond_earth.common.keybinds;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KeyHandler {
    public int key;

    public KeyHandler(int integer) {
        this.key = integer;
    }

    public KeyHandler(FriendlyByteBuf buffer) {
        this.key = buffer.readInt();
    }

    public static KeyHandler decode(FriendlyByteBuf buffer) {
        return new KeyHandler(buffer);
    }

    public static void encode(KeyHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.key);
    }

    public static void handle(KeyHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            Player player = context.getSender();

            switch (message.key) {
                case 2:
                    KeyMethods.rotateRocket(player, 1);
                    KeyMethods.rotateRover(player, 1, -1);
                    break;

                case 3:
                    KeyMethods.rotateRocket(player, -1);
                    KeyMethods.rotateRover(player, -1, 1);
                    break;

                case 4:
                    KeyMethods.slowDownLander(player);
                    break;

                case 6:
                    KeyMethods.startRocket(player);
                    break;
            }
        });

        context.setPacketHandled(true);
    }
}
