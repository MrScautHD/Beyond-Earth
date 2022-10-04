package net.mrscauthd.beyond_earth.common.blocks;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.entities.OxygenBubbleDistributorBlockEntity;
import net.mrscauthd.beyond_earth.common.config.Config;

public class OxygenBubbleDistributorBlock extends AbstractMachineBlock<OxygenBubbleDistributorBlockEntity> {

    public OxygenBubbleDistributorBlock(BlockBehaviour.Properties properties) {
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

        int min = Config.OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MIN.get() * 2 + 1;
        int max = Config.OXYGEN_BUBBLE_DISTRIBUTOR_RANGE_MAX.get() * 2 + 1;
        list.add(Component.translatable("tooltip." + BeyondEarth.MODID + ".oxygen_bubble_distributor", min, max)
                .setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
    }

    @Override
    public OxygenBubbleDistributorBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OxygenBubbleDistributorBlockEntity(pos, state);
    }

}
