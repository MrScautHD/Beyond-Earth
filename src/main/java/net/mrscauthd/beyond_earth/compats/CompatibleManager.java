package net.mrscauthd.beyond_earth.compats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.mrscauthd.beyond_earth.compats.crafttweaker.CraftTweakerCompat;
import net.mrscauthd.beyond_earth.compats.mekanism.MekanismCompat;
import net.mrscauthd.beyond_earth.compats.theoneprobe.TOPCompat;
import net.mrscauthd.beyond_earth.compats.tinkers.TinkersCompat;
import net.mrscauthd.beyond_earth.compats.waila.WailaCompat;
import net.mrscauthd.beyond_earth.jei.JeiCompat;

public class CompatibleManager {

	public static final List<CompatibleMod> MODS;
	public static final JeiCompat JEI;
	public static final TinkersCompat TINKERS;
	public static final TOPCompat TOP;
	public static final WailaCompat WAILA;
	public static final MekanismCompat MEKANISM;
	public static final CraftTweakerCompat CRAFTTWEAKER;

	static {
		List<CompatibleMod> mods = new ArrayList<>();
		mods.add(JEI = new JeiCompat());
		mods.add(TINKERS = new TinkersCompat());
		mods.add(TOP = new TOPCompat());
		mods.add(WAILA = new WailaCompat());
		mods.add(MEKANISM = new MekanismCompat());
		mods.add(CRAFTTWEAKER = new CraftTweakerCompat());

		for (CompatibleMod mod : mods) {
			mod.tryLoad();
		}

		MODS = Collections.unmodifiableList(mods);
	}

	public static void visit() {

	}
}
