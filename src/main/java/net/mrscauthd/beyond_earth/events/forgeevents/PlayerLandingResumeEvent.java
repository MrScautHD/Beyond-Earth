package net.mrscauthd.beyond_earth.events.forgeevents;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.mrscauthd.beyond_earth.entities.LanderEntity;

public class PlayerLandingResumeEvent extends PlayerEvent {

	private final LanderEntity oldLander;
	private final LanderEntity newLander;

	public PlayerLandingResumeEvent(Player player, LanderEntity oldLander, LanderEntity newLander) {
		super(player);
		this.oldLander = oldLander;
		this.newLander = newLander;
	}

	public LanderEntity getOldLander() {
		return this.oldLander;
	}

	public LanderEntity getNewLander() {
		return this.newLander;
	}
}