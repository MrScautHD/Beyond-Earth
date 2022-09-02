package net.mrscauthd.beyond_earth.common.blocks;

import com.mojang.brigadier.LiteralMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;


public class LedBlock extends Block {
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public static final IntegerProperty CHANNEL = IntegerProperty.create("channel", 0 , 3);

    public LedBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(ON, false)
                        .setValue(CHANNEL,0)

        );
    }


    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        if(p_60506_.getInventory().getSelected().toString().equals("1 air")) {
            if(!p_60504_.isClientSide() && p_60507_ == InteractionHand.MAIN_HAND) {
                p_60504_.setBlock(p_60505_, p_60503_.cycle(ON), 3);
            }
        } else if(p_60506_.getInventory().getSelected().toString().equals("1 led_configurator")) {
            if(!p_60504_.isClientSide() && p_60507_ == InteractionHand.MAIN_HAND) {
                p_60504_.setBlock(p_60505_, p_60503_.cycle(CHANNEL), 3);
                p_60506_.level.playSound(p_60506_, p_60505_.getX(),p_60505_.getY(), p_60505_.getZ(), SoundEvents.NOTE_BLOCK_CHIME, SoundSource.BLOCKS, 1, 1);
            }
        }
        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }


    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        if(block.toString().equals("Block{beyond_earth:white_led_block}")) {
            if(level.getBlockState(blockPos1).getValue(CHANNEL) != 4) {
                if (level.getBlockState(blockPos1).getValue(CHANNEL) == 0) {
                    if (level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 0 && level.getBlockState(blockPos1).getValue(CHANNEL) == 0) {
                        level.setBlock(blockPos, blockState.setValue(ON, true), 3);
                    }
                    if (!level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 0 && level.getBlockState(blockPos1).getValue(CHANNEL) == 0) {
                        level.setBlock(blockPos, blockState.setValue(ON, false), 3);
                    }
                }
                if (level.getBlockState(blockPos1).getValue(CHANNEL) == 1) {
                    if (level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 1 && level.getBlockState(blockPos1).getValue(CHANNEL) == 1) {
                        level.setBlock(blockPos, blockState.setValue(ON, true), 3);
                    }
                    if (!level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 1 && level.getBlockState(blockPos1).getValue(CHANNEL) == 1) {
                        level.setBlock(blockPos, blockState.setValue(ON, false), 3);
                    }
                }
                if (level.getBlockState(blockPos1).getValue(CHANNEL) == 2) {
                    if (level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 2 && level.getBlockState(blockPos1).getValue(CHANNEL) == 2) {
                        level.setBlock(blockPos, blockState.setValue(ON, true), 3);
                    }
                    if (!level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 2 && level.getBlockState(blockPos1).getValue(CHANNEL) == 2) {
                        level.setBlock(blockPos, blockState.setValue(ON, false), 3);
                    }
                }
                if (level.getBlockState(blockPos1).getValue(CHANNEL) == 3) {
                    if (level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 3 && level.getBlockState(blockPos1).getValue(CHANNEL) == 3) {
                        level.setBlock(blockPos, blockState.setValue(ON, true), 3);
                    }
                    if (!level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 3 && level.getBlockState(blockPos1).getValue(CHANNEL) == 3) {
                        level.setBlock(blockPos, blockState.setValue(ON, false), 3);
                    }
                }
            } else {
                if (level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 4 && level.getBlockState(blockPos1).getValue(CHANNEL) == 4) {
                    level.setBlock(blockPos, blockState.setValue(ON, true), 3);
                }
                if (!level.getBlockState(blockPos1).getValue(ON) && level.getBlockState(blockPos).getValue(CHANNEL) == 4 && level.getBlockState(blockPos1).getValue(CHANNEL) == 4) {
                    level.setBlock(blockPos, blockState.setValue(ON, false), 3);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(ON);
        p_49915_.add(CHANNEL);

    }
}
