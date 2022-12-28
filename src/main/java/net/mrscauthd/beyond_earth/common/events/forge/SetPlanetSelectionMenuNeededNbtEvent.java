package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;

public class SetPlanetSelectionMenuNeededNbtEvent extends PlayerEvent {

    private final IRocketEntity rocket;

    public SetPlanetSelectionMenuNeededNbtEvent(Player player, IRocketEntity rocket) {
        super(player);
        this.rocket = rocket;
    }

    public IRocketEntity getRocket() {
        return rocket;
    }
}
