package net.mrscauthd.beyond_earth.common.keybinds;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KeyHandler {
    public String key;
    public boolean condition;

    public KeyHandler(String key, boolean condition) {
        this.key = key;
        this.condition = condition;
    }

    public KeyHandler(FriendlyByteBuf buffer) {
        this.key = buffer.readUtf();
        this.condition = buffer.readBoolean();
    }

    public static KeyHandler decode(FriendlyByteBuf buffer) {
        return new KeyHandler(buffer);
    }

    public static void encode(KeyHandler message, FriendlyByteBuf buffer) {
        buffer.writeUtf(message.key);
        buffer.writeBoolean(message.condition);
    }

    public static void handle(KeyHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            Player player = context.getSender();

            switch (message.key) {
                case "key_up":
                    KeyVariables.KEY_UP.put(player.getUUID(), message.condition);
                    break;

                case "key_down":
                    KeyVariables.KEY_DOWN.put(player.getUUID(), message.condition);
                    break;

                case "key_right":
                    KeyVariables.KEY_RIGHT.put(player.getUUID(), message.condition);
                    break;

                case "key_left":
                    KeyVariables.KEY_LEFT.put(player.getUUID(), message.condition);
                    break;

                case "key_jump":
                    KeyVariables.KEY_JUMP.put(player.getUUID(), message.condition);
                    break;

                case "rocket_start":
                    KeyMethods.startRocket(player);
                    break;

                case "switch_jet_suit_mode":
                    KeyMethods.switchJetSuitMode(player);
                    break;
            }
        });

        context.setPacketHandled(true);
    }
}
