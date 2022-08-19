package net.mrscauthd.beyond_earth.compats.crafttweaker.bracket;

import net.minecraft.resources.ResourceLocation;

public class CTBracketUtils {
	public static String toBracketString(String bracket, ResourceLocation name) {
		return new StringBuilder("<").append(bracket).append(":").append(name).append(">").toString();
	}
}
