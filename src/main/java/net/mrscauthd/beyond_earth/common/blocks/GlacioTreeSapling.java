package net.mrscauthd.beyond_earth.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class GlacioTreeSapling extends SaplingBlock {
    public GlacioTreeSapling(AbstractTreeGrower p_55978_, Properties p_55979_) {
        super(p_55978_, p_55979_);

    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.is(BlockTags.DIRT) || p_51042_.is(Blocks.FARMLAND) || p_51042_.is(Blocks.SNOW) || p_51042_.is(Blocks.SNOW_BLOCK);
    }
}
