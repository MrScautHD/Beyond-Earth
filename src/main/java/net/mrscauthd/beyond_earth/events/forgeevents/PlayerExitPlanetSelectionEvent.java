package net.mrscauthd.beyond_earth.events.forgeevents;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerExitPlanetSelectionEvent extends PlayerEvent {

	public PlayerExitPlanetSelectionEvent(Player player) {
		super(player);
	}

}