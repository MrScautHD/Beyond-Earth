package net.mrscauthd.beyond_earth.common.blocks.machines;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.OxygenDistributorBlockEntity;

public class OxygenDistributorBlock extends AbstractMachineBlock<OxygenDistributorBlockEntity> {

    public OxygenDistributorBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected boolean useFacing() {
        return true;
    }

    @Override
    protected boolean useLit() {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, level, list, flag);
    }

    @Override
    public OxygenDistributorBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OxygenDistributorBlockEntity(pos, state);
    }

}
