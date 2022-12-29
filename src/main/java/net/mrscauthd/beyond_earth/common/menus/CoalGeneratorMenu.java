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
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.CoalGeneratorBlockEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

public class CoalGeneratorMenu {

    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            BlockPos pos = extraData.readBlockPos();
            CoalGeneratorBlockEntity blockEntity = (CoalGeneratorBlockEntity) inv.player.level.getBlockEntity(pos);
            return new GuiContainer(id, inv, blockEntity);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final CoalGeneratorBlockEntity blockEntity;

        public GuiContainer(int id, Inventory inv, CoalGeneratorBlockEntity blockEntity) {
            super(ContainerRegistry.COAL_GENERATOR_GUI.get(), id);
            this.blockEntity = blockEntity;

            IItemHandlerModifiable itemHandler = blockEntity.getItemHandler();
            this.addSlot(new SlotItemHandler(itemHandler, CoalGeneratorBlockEntity.SLOT_FUEL, 80, 29));

            MenuHelper.createInventorySlots(inv, this::addSlot, 8, 84);
        }

        @Override
        public boolean stillValid(Player p_38874_) {
            return !this.getBlockEntity().isRemoved();
        }

        public CoalGeneratorBlockEntity getBlockEntity() {
            return this.blockEntity;
        }

        @Override
        public ItemStack quickMoveStack(final Player player, final int index) {
            return MenuHelper.transferStackInSlot(this, player, index, this.getBlockEntity(),
                    this::moveItemStackTo);
        }
    }
}
