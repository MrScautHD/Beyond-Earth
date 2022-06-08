package net.mrscauthd.beyond_earth.crafting;

import javax.annotation.Nonnull;

public class RocketPart {

    @Nonnull
    public static final RocketPart EMPTY = new RocketPart(0);
    
	private final int slots;

	public RocketPart(int slots) {
		this.slots = slots;
	}

	public int getSlots() {
		return this.slots;
	}
}
