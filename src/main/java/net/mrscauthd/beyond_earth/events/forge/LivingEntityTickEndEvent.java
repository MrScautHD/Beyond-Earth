package net.mrscauthd.beyond_earth.events.forge;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class LivingEntityTickEndEvent extends LivingEvent {

    public LivingEntityTickEndEvent(LivingEntity entity) {
        super(entity);
    }
}