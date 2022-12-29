package net.mrscauthd.beyond_earth.common.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.SolarPanelBlockEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

public class SolarPanelMenu {

    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            BlockPos pos = extraData.readBlockPos();
            SolarPanelBlockEntity blockEntity = (SolarPanelBlockEntity) inv.player.level.getBlockEntity(pos);
            return new GuiContainer(id, inv, blockEntity);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final SolarPanelBlockEntity blockEntity;

        public GuiContainer(int id, Inventory inv, SolarPanelBlockEntity blockEntity) {
            super(ContainerRegistry.SOLAR_PANEL_GUI.get(), id);
            this.blockEntity = blockEntity;

            MenuHelper.createInventorySlots(inv, this::addSlot, 8, 146);
        }

        public SolarPanelBlockEntity getBlockEntity() {
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
