package net.mrscauthd.beyond_earth.common.menus;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.CompressorBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.ItemStackToItemStackBlockEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

public class CompressorMenu {
    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            BlockPos pos = extraData.readBlockPos();
            CompressorBlockEntity blockEntity = (CompressorBlockEntity) inv.player.level.getBlockEntity(pos);
            return new GuiContainer(id, inv, blockEntity);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final CompressorBlockEntity blockEntity;

        public GuiContainer(int id, Inventory inv, CompressorBlockEntity blockEntity) {
            super(ContainerRegistry.COMPRESSOR_GUI.get(), id);
            this.blockEntity = blockEntity;

            IItemHandlerModifiable itemHandler = blockEntity.getItemHandler();
            this.addSlot(new SlotItemHandler(itemHandler, CompressorBlockEntity.SLOT_INGREDIENT, 40, 62));

            this.addSlot(new SlotItemHandler(itemHandler, ItemStackToItemStackBlockEntity.SLOT_OUTPUT, 92, 62) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }
            });

            MenuHelper.createInventorySlots(inv, this::addSlot, 8, 114);
        }

        public CompressorBlockEntity getBlockEntity() {
            return this.blockEntity;
        }

        @Override
        public boolean stillValid(Player p_38874_) {
            return !this.getBlockEntity().isRemoved();
        }

        @Override
        public ItemStack quickMoveStack(Player playerIn, int index) {
            return MenuHelper.transferStackInSlot(this, playerIn, index, this.getBlockEntity(),
                    this::moveItemStackTo);
        }
    }
}
