package net.mrscauthd.beyond_earth.events.forgeevents;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.event.entity.EntityEvent;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;

public class RocketPlaceEvent extends EntityEvent {

	private final IRocketEntity rocket;
	private final UseOnContext useOnContext;

	public RocketPlaceEvent(IRocketEntity rocket, UseOnContext useOnContext) {
		super(rocket);
		this.rocket = rocket;
		this.useOnContext = useOnContext;
	}

	public IRocketEntity getRocket() {
		return this.rocket;
	}

	public UseOnContext getUseOnContext() {
		return this.useOnContext;
	}
}