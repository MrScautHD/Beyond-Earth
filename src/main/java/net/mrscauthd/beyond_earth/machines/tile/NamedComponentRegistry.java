package net.mrscauthd.beyond_earth.machines.tile;

import java.util.LinkedHashMap;

import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.BeyondEarth;

public class NamedComponentRegistry<T> extends LinkedHashMap<ResourceLocation, T> {
	public static final ResourceLocation UNNAMED = new ResourceLocation(BeyondEarth.MODID, "unnmaed");
	private static final long serialVersionUID = 1L;

	public void put(T value) {
		if (this.containsKey(UNNAMED)) {
			throw new IllegalArgumentException("UNNAMED component is already added");
		}

		this.put(UNNAMED, value);
	}
}
