package net.mrscauthd.beyond_earth.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ModifiedBucketItem extends BucketItem {

    public ModifiedBucketItem(Fluid p_40689_, Properties p_40690_) {
        super(p_40689_, p_40690_);
    }

    @Override
    public boolean emptyContents(@javax.annotation.Nullable Player p_150716_, Level p_150717_, BlockPos p_150718_, @Nullable BlockHitResult p_150719_) {
        if (!(this.content instanceof FlowingFluid)) {
            return false;
        } else {
            BlockState blockstate = p_150717_.getBlockState(p_150718_);
            Block block = blockstate.getBlock();
            Material material = blockstate.getMaterial();

            boolean flag = blockstate.canBeReplaced(this.content);
            boolean flag1 = blockstate.isAir() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(p_150717_, p_150718_, blockstate, this.content);

            if (!flag1) {
                return p_150719_ != null && this.emptyContents(p_150716_, p_150717_, p_150719_.getBlockPos().relative(p_150719_.getDirection()), null);
            } else if (p_150717_.dimensionType().ultraWarm() && this.content.getFluidType().getBlockForFluidState(p_150717_, p_150718_, this.content.defaultFluidState()).getMaterial() == Material.WATER) {
                int i = p_150718_.getX();
                int j = p_150718_.getY();
                int k = p_150718_.getZ();
                p_150717_.playSound(p_150716_, p_150718_, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (p_150717_.random.nextFloat() - p_150717_.random.nextFloat()) * 0.8F);

                for(int l = 0; l < 8; ++l) {
                    p_150717_.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                }
                return true;
            } else if (block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(p_150717_, p_150718_, blockstate, content)) {
                ((LiquidBlockContainer) block).placeLiquid(p_150717_, p_150718_, blockstate, ((FlowingFluid) this.content).getSource(false));
                this.playEmptySound(p_150716_, p_150717_, p_150718_);
                return true;
            } else {
                if (!p_150717_.isClientSide && flag && !material.isLiquid()) {
                    p_150717_.destroyBlock(p_150718_, true);
                }

                if (!p_150717_.setBlock(p_150718_, this.content.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
                    return false;
                } else {
                    this.playEmptySound(p_150716_, p_150717_, p_150718_);
                    return true;
                }
            }
        }
    }
}
