package net.mrscauthd.beyond_earth.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerCreateSpaceStationEvent extends PlayerEvent {
	public PlayerCreateSpaceStationEvent(Player player) {
		super(player);
	}
}
