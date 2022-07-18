package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class EntityGravityEvent extends EntityEvent {

    private double gravity;

    public EntityGravityEvent(Entity entity, double gravity) {
        super(entity);
        this.gravity = gravity;
    }

    public double getGravity() {
        return gravity;
    }
}