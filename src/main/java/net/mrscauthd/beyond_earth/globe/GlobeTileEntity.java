package net.mrscauthd.beyond_earth.globe;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.ModInit;

public class GlobeTileEntity extends BlockEntity {
    public GlobeTileEntity(BlockPos pos, BlockState state) {
        super(ModInit.GLOBE.get(), pos, state);
    }

    public int time = 0;
    public boolean rotate = false;

    @Override
    public void load(CompoundTag p_155245_) {
        super.load(p_155245_);
        this.time = p_155245_.getInt("time");
        this.rotate = p_155245_.getBoolean("rotate");
    }

    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        super.saveAdditional(p_187471_);
        p_187471_.putInt("time", this.time);
        p_187471_.putBoolean("rotate", this.rotate);
    }

    public void tick() {
        if (this.getTileData().getBoolean("rotate")) {
            this.getTileData().putInt("time", this.getTileData().getInt("time") + 1);
        }

        if (this.getTileData().getInt("time") > 30) {
            this.getTileData().putBoolean("rotate", false);
            this.getTileData().putInt("time", 0);
        }
    }
}
