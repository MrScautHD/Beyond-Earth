package net.mrscauthd.beyond_earth.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.CoalLanternBlock;
import net.mrscauthd.beyond_earth.common.blocks.WallCoalTorchBlock;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.ChunkOxygen;
import net.mrscauthd.beyond_earth.common.events.forge.BlockSetEvent;
import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;
import net.mrscauthd.beyond_earth.common.registries.CapabilityRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID)
public class FireSystemEvents {

    @SubscribeEvent
    public static void onBlockPlace(BlockSetEvent event) {
        Level level = (Level) event.getLevel();
        if(level.isClientSide()) return;

        noO2Checks:
        if (Methods.isSpaceLevelWithoutOxygen(level)) {

            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();
            
            ChunkOxygen chunkO2 = ((LevelChunk) level.getChunk(pos))
                    .getCapability(CapabilityRegistry.CHUNK_OXYGEN).orElse(null);
            int O2 = chunkO2.getO2(pos);
            boolean hasOxygen = O2 > 10;
            if(hasOxygen) break noO2Checks;

            /** FIRE */
            if (block == Blocks.FIRE) {
                level.destroyBlock(pos, false);
            }

            /** WALL TORCH */
            else if (block == Blocks.WALL_TORCH) {

                level.setBlock(pos, BlockRegistry.WALL_COAL_TORCH_BLOCK.get().defaultBlockState().setValue(WallCoalTorchBlock.FACING, state.getValue(WallTorchBlock.FACING)), 3);

                playFireExtinguish(pos, level);
            }

            /** TORCH */
            else if (block == Blocks.TORCH) {
                level.setBlock(pos, BlockRegistry.COAL_TORCH_BLOCK.get().defaultBlockState(), 3);

                playFireExtinguish(pos, level);
            }

            /** LANTERN */
            else if (block == Blocks.LANTERN) {
                if (state.getValue(LanternBlock.HANGING)) {
                    level.setBlock(pos, BlockRegistry.COAL_LANTERN_BLOCK.get().defaultBlockState().setValue(CoalLanternBlock.HANGING, true), 3);
                } else {
                    level.setBlock(pos, BlockRegistry.COAL_LANTERN_BLOCK.get().defaultBlockState(), 3);
                }

                playFireExtinguish(pos, level);
            }

            /** CAMPFIRE */
            else if (block == Blocks.CAMPFIRE && state.getValue(CampfireBlock.LIT)) {

                level.setBlock(pos, level.getBlockState(pos).setValue(CampfireBlock.LIT, false), 3);

                playFireExtinguish(pos, level);
            }
        }
    }

    public static void playFireExtinguish(BlockPos pos, Level level) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1, 1);
    }
}