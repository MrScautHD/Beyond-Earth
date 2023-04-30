package net.mrscauthd.beyond_earth.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;
import org.jetbrains.annotations.NotNull;

public class GlacioTreeSapling extends SaplingBlock {
    public GlacioTreeSapling(AbstractTreeGrower abstractTreeGrower, Properties properties) {
        super(abstractTreeGrower, properties);

    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, @NotNull BlockGetter pGetter, BlockPos pPos) {
        return pState.is(BlockTags.DIRT) || pState.is(Blocks.FARMLAND) || pState.is(Blocks.SNOW) || pState.is(Blocks.SNOW_BLOCK) || pState.is(BlockRegistry.PERMAFROST_GRASS.get());
    }
}
