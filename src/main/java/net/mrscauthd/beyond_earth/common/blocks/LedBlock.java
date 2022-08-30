package net.mrscauthd.beyond_earth.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;


public class LedBlock extends Block {
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public static final BooleanProperty END = BooleanProperty.create("end");

    public LedBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(ON, false)
                        .setValue(END, false)

        );
    }


    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {

        if(!p_60504_.isClientSide() && p_60507_ == InteractionHand.MAIN_HAND) {
            if(p_60506_.isShiftKeyDown()){
                p_60504_.setBlock(p_60505_, p_60503_.cycle(END), 3);
            } else {
                p_60504_.setBlock(p_60505_, p_60503_.cycle(ON), 3);
            }
        }

        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }


    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        if(block.toString().equals("Block{beyond_earth:white_led_block}")) {
            System.out.println(1);
            if(!level.getBlockState(blockPos1).getValue(END)) {
                if(level.getBlockState(blockPos1).getValue(ON)) {
                    level.setBlock(blockPos, blockState.setValue(ON, true), 3);
                }
                if(!level.getBlockState(blockPos1).getValue(ON)) {
                    level.setBlock(blockPos, blockState.setValue(ON, false), 3);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(ON);
        p_49915_.add(END);

    }
}
