package net.mrscauthd.beyond_earth.compats.waila;

import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.compats.CompatibleMod;

public class WailaCompat extends CompatibleMod {
	public static final String MODID = "waila";

	public static ResourceLocation rl(String path) {
		return new ResourceLocation(MODID, path);
	}

	@Override
	public String getModID() {
		return MODID;
	}

	@Override
	protected void onLoad() {

	}

}