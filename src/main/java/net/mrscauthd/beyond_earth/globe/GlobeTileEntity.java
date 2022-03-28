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

    @Override
    public void load(CompoundTag p_155245_) {
        super.load(p_155245_);
        this.rotationalInertia = p_155245_.getFloat("inertia");
        this.yaw = p_155245_.getFloat("yaw");
    }

    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        super.saveAdditional(p_187471_);
        p_187471_.putFloat("inertia", this.rotationalInertia);
        p_187471_.putFloat("yaw", this.yaw);
    }

    public void tick() {
        if (this.rotationalInertia > 0) {
            this.rotationalInertia -= 0.0075f;

            this.yaw -= this.rotationalInertia;
        }
    }

    public float getRotationalInertia() {
        return this.rotationalInertia;
    }

    public void setRotationalInertia(float value) {
        this.rotationalInertia = value;
    }

    public float getYaw() {
        return this.yaw;
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
}
