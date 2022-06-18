package net.mrscauthd.beyond_earth.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;

public class SetPlanetSelectionMenuNeededNbtEvent extends PlayerEvent {

    private IRocketEntity rocket;

    public SetPlanetSelectionMenuNeededNbtEvent(Player player, IRocketEntity rocket) {
        super(player);
        this.rocket = rocket;
    }

    public IRocketEntity getRocket() {
        return rocket;
    }
}