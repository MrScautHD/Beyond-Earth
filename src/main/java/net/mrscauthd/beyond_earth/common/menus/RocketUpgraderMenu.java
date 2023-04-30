package net.mrscauthd.beyond_earth.common.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.RocketUpgraderBlockEntity;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;
import org.jetbrains.annotations.NotNull;

public class RocketUpgraderMenu {
    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            BlockPos pos = extraData.readBlockPos();
            RocketUpgraderBlockEntity blockEntity = (RocketUpgraderBlockEntity) inv.player.level.getBlockEntity(pos);
            return new GuiContainer(id, inv, blockEntity);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final RocketUpgraderBlockEntity blockEntity;

        public GuiContainer(int id, Inventory inv, RocketUpgraderBlockEntity blockEntity) {
            super(ContainerRegistry.ROCKET_UPGRADER_GUI.get(), id);
            this.blockEntity = blockEntity;

            this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                this.addSlot(new SlotItemHandler(handler, 0, 40, 57));
                this.addSlot(new SlotItemHandler(handler, 1, 67, 57));

                this.addSlot(new SlotItemHandler(handler, 2, 135, 57)
                {@Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }});
            });

            MenuHelper.createInventorySlots(inv, this::addSlot, 8, 142);
        }

        public RocketUpgraderBlockEntity getBlockEntity() {
            return this.blockEntity;
        }

        @Override
        public boolean stillValid(Player player) {
            return !this.getBlockEntity().isRemoved();
        }

        @Override
        public ItemStack quickMoveStack(Player playerIn, int index) {
            return MenuHelper.transferStackInSlot(this, playerIn, index, (Container) this.getBlockEntity(),
                    this::moveItemStackTo);
        }
    }
}
