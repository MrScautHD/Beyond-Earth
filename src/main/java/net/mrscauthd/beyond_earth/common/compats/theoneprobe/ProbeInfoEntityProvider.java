package net.mrscauthd.beyond_earth.common.compats.theoneprobe;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoEntityProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValuesProvider;

public class ProbeInfoEntityProvider implements IProbeInfoEntityProvider {

	public static final ProbeInfoEntityProvider INSTANCE = new ProbeInfoEntityProvider();

	public ProbeInfoEntityProvider() {

	}

	@Override
	public void addProbeEntityInfo(ProbeMode probeMode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData hitData) {

		if (entity instanceof IGaugeValuesProvider provider) {
			provider.getDisplayGaugeValues().forEach(g -> probeInfo.element(new GaugeValueElement(g)));
		}

	}

	@Override
	public String getID() {
		return new ResourceLocation(BeyondEarth.MODID, "top_entity").toString();
	}

}
