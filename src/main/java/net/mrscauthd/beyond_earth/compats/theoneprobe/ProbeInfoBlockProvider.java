package net.mrscauthd.beyond_earth.compats.theoneprobe;

import java.util.Collection;

import mcjty.theoneprobe.api.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.gauge.IGaugeValuesProvider;
import net.mrscauthd.beyond_earth.machines.tile.AbstractMachineBlockEntity;

public class ProbeInfoBlockProvider implements IProbeInfoProvider {

	public static final ProbeInfoBlockProvider INSTANCE = new ProbeInfoBlockProvider();

	public ProbeInfoBlockProvider() {

	}

	@Override
	public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, Player player, Level level, BlockState blockState, IProbeHitData hitData) {
		BlockEntity blockEntity = level.getBlockEntity(hitData.getPos());

		if (blockEntity instanceof AbstractMachineBlockEntity) {
			AbstractMachineBlockEntity machineBlockEntity = (AbstractMachineBlockEntity) blockEntity;

			if (probeMode != ProbeMode.EXTENDED) {
				machineBlockEntity.getFluidHandlers().values().stream().map(machineBlockEntity::getFluidHandlerGaugeValues).flatMap(Collection::stream).forEach(g -> probeInfo.element(new GaugeValueElement(g)));

				/*
				if (CompatibleManager.MEKANISM.isLoaded()) {
					List<? extends IElement> elements = MekanismHelper.createGasGaugeDataElement(machineBlockEntity.getCapability(MekanismHelper.getGasHandlerCapability()).orElse(null));
					elements.forEach(element -> probeInfo.element(element));
				}
				*/
			}
		}

		if (blockEntity instanceof IGaugeValuesProvider) {
			((IGaugeValuesProvider) blockEntity).getGaugeValues().forEach(g -> probeInfo.element(new GaugeValueElement(g)));
		}

	}

	@Override
	public ResourceLocation getID() {
		return new ResourceLocation(BeyondEarth.MODID, "top_block");
	}

}
