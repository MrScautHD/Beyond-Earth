package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class LivingSprintingEvent extends LivingEvent {

    private final boolean sprinting;

    public LivingSprintingEvent(LivingEntity entity, boolean sprinting) {
        super(entity);
        this.sprinting = sprinting;
    }

    public boolean getSprinting() {
        return this.sprinting;
    }
}
