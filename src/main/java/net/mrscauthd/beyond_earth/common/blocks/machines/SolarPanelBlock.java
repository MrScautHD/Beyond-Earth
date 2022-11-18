package net.mrscauthd.beyond_earth.common.blocks.machines;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.SolarPanelBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.common.config.Config;

public class SolarPanelBlock extends AbstractMachineBlock<SolarPanelBlockEntity> {

        public SolarPanelBlock(BlockBehaviour.Properties properties) {
                super(properties);
        }

        @Override
        public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
                super.appendHoverText(itemstack, level, list, flag);
                list.add(GaugeTextHelper.buildBlockTooltip(GaugeTextHelper.getGeneratingPerTickText(GaugeValueHelper.getEnergy(Config.SOLAR_PANEL_ENERGY_GENERATION.get()))));
        }

        @Override
        public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
                return true;
        }

        @Override
        public SolarPanelBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                return new SolarPanelBlockEntity(pos, state);
        }
}
