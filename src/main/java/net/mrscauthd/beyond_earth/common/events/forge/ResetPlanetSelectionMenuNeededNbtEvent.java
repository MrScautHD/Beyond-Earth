package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ResetPlanetSelectionMenuNeededNbtEvent extends PlayerEvent {

    public ResetPlanetSelectionMenuNeededNbtEvent(Player player) {
        super(player);
    }
}
