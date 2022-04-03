package net.mrscauthd.beyond_earth.compats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.mrscauthd.beyond_earth.compats.mekanism.MekanismCompat;
import net.mrscauthd.beyond_earth.compats.theoneprobe.TOPCompat;
import net.mrscauthd.beyond_earth.compats.waila.WailaCompat;
import net.mrscauthd.beyond_earth.jei.JeiCompat;

public class CompatibleManager {

	public static final List<CompatibleMod> MODS;
	public static final JeiCompat JEI;
	//TODO ADD BACK IF TINKERS PORT
	//public static final TinkersCompat TINKERS;
	public static final TOPCompat TOP;
	public static final WailaCompat WAILA;
	public static final MekanismCompat MEKANISM;

	static {
		List<CompatibleMod> mods = new ArrayList<>();
		mods.add(JEI = new JeiCompat());
		//TODO ADD BACK IF TINKERS PORT
		//mods.add(TINKERS = new TinkersCompat());
		mods.add(TOP = new TOPCompat());
		mods.add(WAILA = new WailaCompat());
		mods.add(MEKANISM = new MekanismCompat());

		for (CompatibleMod mod : mods) {
			mod.tryLoad();
		}

		MODS = Collections.unmodifiableList(mods);
	}

	public static void visit() {

	}

}
