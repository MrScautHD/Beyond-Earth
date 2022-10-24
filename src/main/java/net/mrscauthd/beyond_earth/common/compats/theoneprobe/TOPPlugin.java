package net.mrscauthd.beyond_earth.common.compats.theoneprobe;

import java.util.function.Function;

import mcjty.theoneprobe.api.ITheOneProbe;

public class TOPPlugin implements Function<ITheOneProbe, Void> {

	@Override
	public Void apply(ITheOneProbe top) {
		top.registerProvider(ProbeInfoBlockProvider.INSTANCE);
		top.registerEntityProvider(ProbeInfoEntityProvider.INSTANCE);
		top.registerProbeConfigProvider(ProbeConfigProvider.INSTANCE);
		top.registerElementFactory(GaugeValueElementFactory.INSTANCE);

		return null;
	}

}
