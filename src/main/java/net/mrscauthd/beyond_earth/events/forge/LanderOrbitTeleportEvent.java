package net.mrscauthd.beyond_earth.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;
import net.mrscauthd.beyond_earth.entities.LanderEntity;

public class LanderOrbitTeleportEvent extends Event {

    private final LanderEntity landerEntity;
    private final Player player;

    public LanderOrbitTeleportEvent(LanderEntity landerEntity, Player player) {
        this.landerEntity = landerEntity;
        this.player = player;
    }

    public LanderEntity getLanderEntity() {
        return landerEntity;
    }

    public Player getPlayer() {
        return player;
    }

    public static class Pre extends LanderOrbitTeleportEvent {
        public Pre(LanderEntity entity, Player player) {
            super(entity, player);
        }
    }

    public static class Post extends LanderOrbitTeleportEvent {
        public Post(LanderEntity entity, Player player) {
            super(entity, player);
        }
    }
}
