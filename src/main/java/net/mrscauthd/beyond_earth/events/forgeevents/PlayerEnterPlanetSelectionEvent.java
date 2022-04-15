package net.mrscauthd.beyond_earth.events.forgeevents;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;

public class PlayerEnterPlanetSelectionEvent extends PlayerEvent {

	private final IRocketEntity rocket;

	public PlayerEnterPlanetSelectionEvent(Player player, IRocketEntity rocket) {
		super(player);
		this.rocket = rocket;
	}

	public IRocketEntity getRocket() {
		return this.rocket;
	}

}