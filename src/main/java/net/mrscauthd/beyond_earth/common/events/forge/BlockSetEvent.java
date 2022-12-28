package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class BlockSetEvent extends BlockEvent {

    private final int p_46607_;
    private final int p_46608_;

    public BlockSetEvent(Level level, BlockPos blockPos, BlockState blockState, int p_46607_, int p_46608_) {
        super(level, blockPos, blockState);
        this.p_46607_ = p_46607_;
        this.p_46608_ = p_46608_;
    }

    public int getP_46607_() {
        return p_46607_;
    }

    public int getP_46608_() {
        return p_46608_;
    }
}
