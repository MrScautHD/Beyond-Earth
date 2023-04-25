package net.mrscauthd.beyond_earth.common.compats.theoneprobe;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.AbstractMachineBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValuesProvider;

import java.util.Collection;

public class ProbeInfoBlockProvider implements IProbeInfoProvider {

	public static final ProbeInfoBlockProvider INSTANCE = new ProbeInfoBlockProvider();

	public ProbeInfoBlockProvider() {

	}

	@Override
	public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, Player player, Level level, BlockState blockState, IProbeHitData hitData) {
		BlockEntity blockEntity = level.getBlockEntity(hitData.getPos());

		if (blockEntity instanceof AbstractMachineBlockEntity machineBlockEntity) {
			if (probeMode != ProbeMode.EXTENDED) {
				machineBlockEntity.getFluidHandlers().values().stream().map(machineBlockEntity::getFluidHandlerGaugeValues).flatMap(Collection::stream).forEach(g -> probeInfo.element(new GaugeValueElement(g)));
			}
		}

		if (blockEntity instanceof IGaugeValuesProvider provider) {
			provider.getDisplayGaugeValues().forEach(g -> probeInfo.element(new GaugeValueElement(g)));
		}

	}

	@Override
	public ResourceLocation getID() {
		return new ResourceLocation(BeyondEarth.MODID, "top_block");
	}

}
