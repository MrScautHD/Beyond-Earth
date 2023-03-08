package net.mrscauthd.beyond_earth.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.events.forge.BlockSetEvent;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public class BlockPlace {

    @Inject(at = @At(value = "RETURN"), method = "setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)Z")
    private void setBlock(BlockPos blockPos, BlockState blockState, int p_46607_, int p_46608_, CallbackInfoReturnable<Boolean> cir) {
        Level level = (Level) ((Object) this);

        if ((level.dimension().equals(LevelRegistry.MOON) && Config.WATER_TO_ICE_MOON.get()) ||
            (level.dimension().equals(LevelRegistry.GLACIO) && Config.WATER_TO_ICE_GLACIO.get())) {
            if (level.getBlockState(blockPos).equals(Blocks.WATER)) {
                level.setBlock(blockPos, Blocks.ICE.defaultBlockState(), 4096);
            }
        }

        if (MinecraftForge.EVENT_BUS.post(new BlockSetEvent(level, blockPos, blockState, p_46607_, p_46608_))) {
            cir.cancel();
        }
    }
}