package net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge;

import java.util.List;

public interface IGaugeValuesProvider {

	List<IGaugeValue> getDisplayGaugeValues();
}
