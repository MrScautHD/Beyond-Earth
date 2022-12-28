package net.mrscauthd.beyond_earth.common.keybinds;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.common.armors.JetSuit;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.util.Methods;

public class KeyMethods {

    public static void startRocket(Player player) {
        if (player.isPassenger() && player.getVehicle() instanceof IRocketEntity rocket) {

            rocket.startRocket();
        }
    }

    public static void switchJetSuitMode(Player player) {
        if (Methods.isLivingInJetSuit(player)) {
            ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
            JetSuit.Suit item = (JetSuit.Suit) itemStack.getItem();

            item.switchJetSuitMode(player, itemStack);
        }
    }
}
