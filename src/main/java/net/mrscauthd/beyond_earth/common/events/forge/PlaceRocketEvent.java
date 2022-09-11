package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.event.entity.EntityEvent;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;

public class PlaceRocketEvent extends EntityEvent {

    private final UseOnContext context;

    public PlaceRocketEvent(IRocketEntity entity, UseOnContext context) {
        super(entity);
        this.context = context;
    }

    public UseOnContext getContext() {
        return context;
    }
}
