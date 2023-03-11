package net.mrscauthd.beyond_earth.common.blocks.entities;

import com.mojang.authlib.GameProfile;

import java.util.UUID;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.StringUtil;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;

public class FlagBlockEntity extends BlockEntity {

    @Nullable
    private GameProfile owner;

    public FlagBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLAG_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (this.owner != null) {
            CompoundTag compoundtag = new CompoundTag();
            NbtUtils.writeGameProfile(compoundtag, this.owner);
            tag.put("FlagOwner", compoundtag);
        }

    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("FlagOwner", 10)) {
            this.setOwner(NbtUtils.readGameProfile(tag.getCompound("FlagOwner")));
        } else if (tag.contains("ExtraType", 8)) {
            String s = tag.getString("ExtraType");
            if (!StringUtil.isNullOrEmpty(s)) {
                this.setOwner(new GameProfile(null, s));
            }
        }
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public GameProfile getPlayerProfile() {
        return this.owner;
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

    public void setOwner(@Nullable GameProfile p_59770_) {
        synchronized(this) {
            this.owner = p_59770_;
        }

        this.updateOwnerProfile();
    }

    private void updateOwnerProfile() {
        SkullBlockEntity.updateGameprofile(this.owner, (p_155747_) -> {
            this.owner = p_155747_;
            this.setChanged();
        });
    }
}