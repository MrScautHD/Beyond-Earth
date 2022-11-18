package net.mrscauthd.beyond_earth.common.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.FuelRefineryBlockEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

public class FuelRefineryMenu {

    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            BlockPos pos = extraData.readBlockPos();
            FuelRefineryBlockEntity blockEntity = (FuelRefineryBlockEntity) inv.player.level.getBlockEntity(pos);
            return new GuiContainer(id, inv, blockEntity);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final FuelRefineryBlockEntity blockEntity;

        public GuiContainer(int id, Inventory inv, FuelRefineryBlockEntity blockEntity) {
            super(ContainerRegistry.FUEL_REFINERY_GUI.get(), id);
            this.blockEntity = blockEntity;

            IItemHandlerModifiable itemHandler = blockEntity.getItemHandler();
            this.addSlot(new SlotItemHandler(itemHandler, FuelRefineryBlockEntity.SLOT_INPUT_SOURCE, 12, 22));
            this.addSlot(new SlotItemHandler(itemHandler, FuelRefineryBlockEntity.SLOT_OUTPUT_SINK, 127, 52));
            this.addSlot(new SlotItemHandler(itemHandler, FuelRefineryBlockEntity.SLOT_INPUT_SINK, 12, 52));
            this.addSlot(new SlotItemHandler(itemHandler, FuelRefineryBlockEntity.SLOT_OUTPUT_SOURCE, 127, 22));

            MenuHelper.createInventorySlots(inv, this::addSlot, 8, 102);
        }

        public FuelRefineryBlockEntity getBlockEntity() {
            return this.blockEntity;
        }

        @Override
        public boolean stillValid(Player player) {
            return !this.getBlockEntity().isRemoved();
        }

        @Override
        public ItemStack quickMoveStack(Player playerIn, int index) {
            return MenuHelper.transferStackInSlot(this, playerIn, index, this.getBlockEntity(), this::moveItemStackTo);
        }
    }
}
