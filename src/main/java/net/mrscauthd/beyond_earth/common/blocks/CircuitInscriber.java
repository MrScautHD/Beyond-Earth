package net.mrscauthd.beyond_earth.common.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class CircuitInscriber extends DirectionalBlock {
    public CircuitInscriber(Properties p_49795_) {
        super(p_49795_);
    }
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p53105) {
        p53105.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p53087) {
        Direction direction = p53087.getHorizontalDirection();
        return defaultBlockState().setValue(FACING, direction);
    }
}
