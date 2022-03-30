package net.mrscauthd.beyond_earth.globe;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.ModInit;

public class GlobeTileEntity extends BlockEntity {
    public GlobeTileEntity(BlockPos pos, BlockState state) {
        super(ModInit.GLOBE.get(), pos, state);
    }

    private float rotationalInertia = 0.0f;
    private float yaw = 0.0f;
    private float yaw0 = 0.0f;

    @Override
    public void load(CompoundTag p_155245_) {
        super.load(p_155245_);
        this.rotationalInertia = p_155245_.getFloat("inertia");
        this.yaw = p_155245_.getFloat("yaw");
        this.yaw0 = p_155245_.getFloat("yaw0");
    }

    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        super.saveAdditional(p_187471_);
        p_187471_.putFloat("inertia", this.rotationalInertia);
        p_187471_.putFloat("yaw", this.yaw);
        p_187471_.putFloat("yaw0", this.yaw0);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    public void tick() {
        if (this.getRotationalInertia() > 0) {
            this.setRotationalInertia(this.getRotationalInertia() - 0.0075f);

            if (this.getRotationalInertia() < 0) {
                this.setRotationalInertia(0);
            }

            this.setYaw0(this.getYaw());
            this.setYaw(this.getYaw() - this.getRotationalInertia());
            this.setChanged();
        }
    }

    public float getRotationalInertia() {
        return this.getTileData().getFloat("inertia");
    }

    public void setRotationalInertia(float value) {
        this.getTileData().putFloat("inertia", value);
    }

    public float getYaw() {
        return this.getTileData().getFloat("yaw");
    }

    public void setYaw(float value) {
        this.getTileData().putFloat("yaw", value);
    }

    public float getYaw0() {
        return this.getTileData().getFloat("yaw0");
    }

    public void setYaw0(float value) {
        this.getTileData().putFloat("yaw0", value);
    }
}
