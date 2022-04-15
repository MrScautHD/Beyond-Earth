package net.mrscauthd.beyond_earth.events.forgeevents;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;

public class RocketPickResultEvent extends EntityEvent {

	private final IRocketEntity rocket;
	private final ItemStack itemStack;

	public RocketPickResultEvent(IRocketEntity rocket, ItemStack itemStack) {
		super(rocket);
		this.rocket = rocket;
		this.itemStack = itemStack;
	}

	public IRocketEntity getRocket() {
		return this.rocket;
	}

	public ItemStack getItemStack() {
		return this.itemStack;
	}
}