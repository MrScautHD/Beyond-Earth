package net.mrscauthd.beyond_earth.events.forgeevents;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;

public class RocketPickResultEvent extends EntityEvent {

    private final ItemStack itemStack;

    public RocketPickResultEvent(IRocketEntity entity, ItemStack itemStack) {
        super(entity);
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
