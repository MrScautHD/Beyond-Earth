package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;

public class EntityTickEvent extends EntityEvent {

    public EntityTickEvent(Entity entity) {
        super(entity);
    }
}
