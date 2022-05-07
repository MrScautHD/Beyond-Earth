package net.mrscauthd.beyond_earth.events.forge;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.item.ItemEvent;

public class ItemEntityTickEndEvent extends ItemEvent {

    public ItemEntityTickEndEvent(ItemEntity entity) {
        super(entity);
    }
}