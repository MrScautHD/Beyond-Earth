package net.mrscauthd.beyond_earth.compats.crafttweaker;

import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.compats.CompatibleMod;

public class CraftTweakerCompat extends CompatibleMod {
	public static final String MODID = "crafttweaker";

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
