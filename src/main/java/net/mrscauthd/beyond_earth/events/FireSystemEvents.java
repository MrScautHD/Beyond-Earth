package net.mrscauthd.beyond_earth.events;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.blocks.CoalLanternBlock;
import net.mrscauthd.beyond_earth.blocks.WallCoalTorchBlock;
import net.mrscauthd.beyond_earth.events.forge.BlockSetEvent;
import net.mrscauthd.beyond_earth.registries.BlocksRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID)
public class FireSystemEvents {

    @SubscribeEvent
    public static void onBlockPlace(BlockSetEvent event) {
        Level level = (Level) event.getWorld();

        if (Methods.isSpaceWorldWithoutOxygen(level)) {

            BlockPos pos = event.getPos();
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            /** FIRE */
            if (block == Blocks.FIRE) {
                level.destroyBlock(pos, false);
            }

            /** WALL TORCH */
            else if (block == Blocks.WALL_TORCH) {

                level.setBlock(pos, BlocksRegistry.WALL_COAL_TORCH_BLOCK.get().defaultBlockState().setValue(WallCoalTorchBlock.FACING, state.getValue(WallTorchBlock.FACING)), 3);

                playFireExtinguish(pos, level);
            }

            /** TORCH */
            else if (block == Blocks.TORCH) {
                level.setBlock(pos, BlocksRegistry.COAL_TORCH_BLOCK.get().defaultBlockState(), 3);

                playFireExtinguish(pos, level);
            }

            /** LANTERN */
            else if (block == Blocks.LANTERN) {
                if (state.getValue(LanternBlock.HANGING)) {
                    level.setBlock(pos, BlocksRegistry.COAL_LANTERN_BLOCK.get().defaultBlockState().setValue(CoalLanternBlock.HANGING, true), 3);
                } else {
                    level.setBlock(pos, BlocksRegistry.COAL_LANTERN_BLOCK.get().defaultBlockState(), 3);
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