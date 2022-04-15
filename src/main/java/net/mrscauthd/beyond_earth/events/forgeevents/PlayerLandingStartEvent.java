package net.mrscauthd.beyond_earth.events.forgeevents;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.mrscauthd.beyond_earth.entities.LanderEntity;

public class PlayerLandingStartEvent extends PlayerEvent {

	private final LanderEntity lander;

	public PlayerLandingStartEvent(Player player, LanderEntity lander) {
		super(player);
		this.lander = lander;
	}

	public LanderEntity getLander() {
		return this.lander;
	}
}