package net.mrscauthd.beyond_earth.compats.crafttweaker;

import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

public class CTConstants {
	public static final ResourceLocation SOURCE_LOADER_ID = new ResourceLocation(BeyondEarthMod.MODID, "ct_source");
	public static final String SOURCE_LOADER_NAME = BeyondEarthMod.MODID + "ct_source_loader";

    public static final String BRACKET_HANDLER = "mods." + BeyondEarthMod.MODID + ".BracketHandler";
    
    public static final String BRACKET_ROCKET_PART = "rocket_part";

	public static final String RECIPE_MANAGER = "mods." + BeyondEarthMod.MODID + ".recipe.manager";
	public static final String RECIPE_MANAGER_IS2IS = RECIPE_MANAGER + ".IS2IS";
	public static final String RECIPE_MANAGER_COMPRESSING = RECIPE_MANAGER + ".Compressing";
	public static final String RECIPE_MANAGER_FUEL_REFINING = RECIPE_MANAGER + ".FuelRefining";
	public static final String RECIPE_MANAGER_COAL_GENERATING = RECIPE_MANAGER + ".CoalGenerating";
	public static final String RECIPE_MANAGER_OXYGEN = RECIPE_MANAGER + ".Oxygen";
	public static final String RECIPE_MANAGER_OXYGEN_LOADING = RECIPE_MANAGER_OXYGEN + ".Loading";
	public static final String RECIPE_MANAGER_OXYGEN_BUBBLE_DISTRIBUTING = RECIPE_MANAGER_OXYGEN + ".BubbleDistributing";
	public static final String RECIPE_MANAGER_NASA_WORKBENCHING = RECIPE_MANAGER + ".NASAWorkbenching";
	public static final String RECIPE_MANAGER_SPACE_STATION = RECIPE_MANAGER + ".SpaceStation";
}
