package net.mrscauthd.beyond_earth.common.items;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class ModifiedBucketItem extends BucketItem {

    private final boolean explodeNether;

    public ModifiedBucketItem(Supplier<? extends Fluid> p_40689_, Properties p_40690_, boolean explodeNether) {
        super(p_40689_, p_40690_);
        this.explodeNether = explodeNether;
    }

    @Override
    public boolean emptyContents(@Nullable Player player, Level level, BlockPos blockPos, @Nullable BlockHitResult hitResult) {
        if (!(this.getFluid() instanceof FlowingFluid)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(blockPos);
            Block block = blockstate.getBlock();
            Material material = blockstate.getMaterial();

            boolean flag = blockstate.canBeReplaced(this.getFluid());
            boolean flag1 = blockstate.isAir() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(level, blockPos, blockstate, this.getFluid());

            if (!flag1) {
                return hitResult != null && this.emptyContents(player, level, hitResult.getBlockPos().relative(hitResult.getDirection()), null);
            } else if (level.dimensionType().ultraWarm() && this.getFluid().getFluidType().getBlockForFluidState(level, blockPos, this.getFluid().defaultFluidState()).getMaterial() == Material.WATER) {
                if (this.explodeNether) {
                    if (!level.isClientSide) {
                        level.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10, true, Level.ExplosionInteraction.BLOCK);
                    }
                } else {
                    int i = blockPos.getX();
                    int j = blockPos.getY();
                    int k = blockPos.getZ();
                    level.playSound(player, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; l++) {
                        level.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                return true;
            } else if (block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(level, blockPos, blockstate, getFluid())) {
                ((LiquidBlockContainer) block).placeLiquid(level, blockPos, blockstate, ((FlowingFluid) this.getFluid()).getSource(false));
                this.playEmptySound(player, level, blockPos);
                return true;
            } else {
                if (!level.isClientSide && flag && !material.isLiquid()) {
                    level.destroyBlock(blockPos, true);
                }

                if (!level.setBlock(blockPos, this.getFluid().defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
                    return false;
                } else {
                    this.playEmptySound(player, level, blockPos);
                    return true;
                }
            }
        }
    }
}
