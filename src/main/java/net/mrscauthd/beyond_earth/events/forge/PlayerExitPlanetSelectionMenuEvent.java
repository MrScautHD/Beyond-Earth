package net.mrscauthd.beyond_earth.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerExitPlanetSelectionMenuEvent extends PlayerEvent {

    public PlayerExitPlanetSelectionMenuEvent(Player player) {
        super(player);
    }
}