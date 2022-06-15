package net.mrscauthd.beyond_earth.guis.helper;

import net.mrscauthd.beyond_earth.utils.Rectangle2d;

@FunctionalInterface
public interface IPlacer {
	Rectangle2d place(int index, int left, int top, int mod);
}
