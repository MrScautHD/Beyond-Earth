package net.mrscauthd.beyond_earth.advencements;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.mrscauthd.beyond_earth.advencements.criterion.PlayerCreateSpaceStationTrigger;
import net.mrscauthd.beyond_earth.advencements.criterion.ThrowOnOrbitTrigger;

public class BeyondEarthCriteriaTriggers {
	public static final ThrowOnOrbitTrigger THROW_ON_ORBIT = register(new ThrowOnOrbitTrigger());
	public static final PlayerCreateSpaceStationTrigger PLAYER_CREATE_SPACE_STATION = register(new PlayerCreateSpaceStationTrigger());

	public static void visit() {

	}

	public static <T extends CriterionTrigger<?>> T register(T trigger) {
		return CriteriaTriggers.register(trigger);
	}
}
