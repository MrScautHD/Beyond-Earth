package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class LivingSetVenusRainEvent extends LivingEvent {

    /** KEY OF THE PLANET(WORLD) */
    private final ResourceKey<Level> planet;

    public LivingSetVenusRainEvent(LivingEntity entity, ResourceKey<Level> planet) {
        super(entity);
        this.planet = planet;
    }

    public ResourceKey<Level> getPlanet() {
        return planet;
    }
}
