package net.mrscauthd.beyond_earth.events.forgeevents;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class EntityTickEvent extends Event {

    private Entity entity;

    public EntityTickEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}