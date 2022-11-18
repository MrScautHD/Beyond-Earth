package net.mrscauthd.beyond_earth.common.menus.nasaworkbench;

import net.mrscauthd.beyond_earth.common.util.Rectangle2d;

@FunctionalInterface
public interface IPlacer {
        Rectangle2d place(int index, int left, int top, int mod);
}
