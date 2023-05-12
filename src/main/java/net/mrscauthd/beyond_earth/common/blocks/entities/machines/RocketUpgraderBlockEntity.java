package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenUtil;
import net.mrscauthd.beyond_earth.common.menus.RocketUpgraderMenu;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RocketUpgraderBlockEntity extends AbstractMachineBlockEntity {
    public static final int ROCKET_INPUT_SLOT = 0;
    public static final int ROCKET_UPGRADE_SLOT = 1;
    public static final int ROCKET_OUTPUT_SLOT = 2;

    public RocketUpgraderBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.ROCKET_UPGRADER_BLOCK_ENTITY.get(), pos, state);
    }

    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new RocketUpgraderMenu.GuiContainer(id, inventory, this);
    }

    @Override
    protected void tickProcessing() {
        //if (getItemHandler().getStackInSlot(ROCKET_UPGRADE_SLOT).is(TagRegistry.ROCKET_UPGRADE)) {
        //    getItemHandler().setStackInSlot(ROCKET_OUTPUT_SLOT, ItemsRegistry.TIER_4_ROCKET_ITEM.get().getDefaultInstance());
        //}
    }

    @Override
    protected boolean onCanPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == ROCKET_UPGRADE_SLOT) {
            return isRocketUpgrade(stack);
        }

        return super.onCanPlaceItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == ROCKET_UPGRADE_SLOT) {
            return !isRocketUpgrade(stack);
        }

        return super.canTakeItemThroughFace(index, stack, direction);
    }

    @Override
    protected void getSlotsForFace(Direction direction, List<Integer> slots) {
        super.getSlotsForFace(direction, slots);
        slots.add(ROCKET_INPUT_SLOT);
        slots.add(ROCKET_UPGRADE_SLOT);
        slots.add(ROCKET_OUTPUT_SLOT);
    }

    private static boolean isRocketUpgrade(ItemStack stack) {
        return stack.is(TagRegistry.ROCKET_UPGRADE);
    }

    @Override
    public boolean hasSpaceInOutput() {
        return false;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.beyond_earth.rocket_upgrader");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new RocketUpgraderMenu.GuiContainer(id, inventory, this);
    }
}