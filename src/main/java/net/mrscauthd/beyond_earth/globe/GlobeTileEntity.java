package net.mrscauthd.beyond_earth.globe;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.ModInit;

public class GlobeTileEntity extends BlockEntity {
    public GlobeTileEntity(BlockPos pos, BlockState state) {
        super(ModInit.GLOBE.get(), pos, state);
    }

    public int time = 0;
    public boolean rotate = false;

    public void tick() {
        if (rotate) {
            ++time;
        }

        if (this.time > 30) {
            this.rotate = false;
            this.time = 0;
        }
    }
}
