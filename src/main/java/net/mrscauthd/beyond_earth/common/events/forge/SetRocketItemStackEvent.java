package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;

public class SetRocketItemStackEvent extends EntityEvent {

    private final ItemStack itemStack;

    public SetRocketItemStackEvent(IRocketEntity entity, ItemStack itemStack) {
        super(entity);
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
