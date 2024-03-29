package net.mrscauthd.beyond_earth.common.blocks.machines;

import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.AbstractMachineBlockEntity;

public abstract class AbstractMachineBlock<T extends AbstractMachineBlockEntity> extends Block implements EntityBlock {
        public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
        public static final BooleanProperty LIT = BlockStateProperties.LIT;

        public AbstractMachineBlock(Properties properties) {
                super(properties.lightLevel(new ToIntFunction<BlockState>() {
                        @Override
                        public int applyAsInt(BlockState state) {
                                Block block = state.getBlock();

                                if (block instanceof AbstractMachineBlock<?>) {
                                        return ((AbstractMachineBlock<?>) block).getLightLevel(state);
                                }

                                return 0;
                        }
                }));

                this.registerDefaultState(this.buildDefaultState());
        }

        protected BlockState buildDefaultState() {
                BlockState any = this.stateDefinition.any();

                if (this.useFacing()) {
                        any = any.setValue(FACING, Direction.NORTH);
                }

                if (this.useLit()) {
                        any = any.setValue(LIT, false);
                }

                return any;
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
                super.createBlockStateDefinition(builder);

                if (this.useFacing()) {
                        builder.add(FACING);
                }

                if (this.useLit()) {
                        builder.add(LIT);
                }

        }

        @Override
        public BlockState rotate(BlockState state, Rotation rotation) {
                if (this.useFacing()) {
                        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
                } else {
                        return state;
                }

        }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
                BlockState state = this.defaultBlockState();

                if (this.useFacing()) {
                        return state.setValue(FACING, context.getHorizontalDirection().getOpposite());
                } else {
                        return state;
                }

        }

        protected int getLightLevel(BlockState state) {
                if (this.useLit()) {
                        return state.getValue(LIT) ? 12 : 0;
                } else {
                        return 0;
                }

        }

        protected boolean useFacing() {
                return false;
        }

        protected boolean useLit() {
                return false;
        }

        @Override
        public InteractionResult use(BlockState state, Level level, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult raytrace) {
                if (entity instanceof ServerPlayer) {
                        T blockEntity = this.getBlockEntity(level, pos);

                        if (blockEntity != null) {
                                NetworkHooks.openScreen((ServerPlayer) entity, blockEntity, pos);
                        }

                        return InteractionResult.CONSUME;
                } else {
                        return InteractionResult.SUCCESS;
                }

        }

        @SuppressWarnings("unchecked")
        public T getBlockEntity(Level level, BlockPos pos) {
                BlockEntity blockEntity = level.getBlockEntity(pos);

                if (blockEntity instanceof AbstractMachineBlockEntity) {
                        return (T) blockEntity;
                }

                return null;
        }

        @SuppressWarnings("deprecation")
        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
                BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);

                if (blockEntity instanceof AbstractMachineBlockEntity) {
                        ItemStack itemStack = new ItemStack(this);
                        blockEntity.saveToItem(itemStack);
                        return Collections.singletonList(itemStack);
                } else {
                        return super.getDrops(state, builder);
                }
        }

        @Override
        public PushReaction getPistonPushReaction(BlockState state) {
                return PushReaction.BLOCK;
        }

        @Override
        @Nullable
        public abstract T newBlockEntity(BlockPos pos, BlockState state);

        @Override
        public boolean hasAnalogOutputSignal(BlockState state) {
                return true;
        }

        @Override
        public <T2 extends BlockEntity> BlockEntityTicker<T2> getTicker(Level level, BlockState state, BlockEntityType<T2> type) {
                return (l, p, s, e) -> {
                        if (e instanceof AbstractMachineBlockEntity) {
                                ((AbstractMachineBlockEntity) e).tick();
                        }
                };
        }

        @Override
        public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
                BlockEntity blockEntity = level.getBlockEntity(pos);

                if (blockEntity instanceof AbstractMachineBlockEntity) {
                        return AbstractContainerMenu.getRedstoneSignalFromContainer((AbstractMachineBlockEntity) blockEntity);
                } else {
                        return 0;
                }

        }

}
