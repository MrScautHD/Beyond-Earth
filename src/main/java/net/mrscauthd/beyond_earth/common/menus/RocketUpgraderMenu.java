package net.mrscauthd.beyond_earth.common.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.NASAWorkbenchBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.RocketUpgraderBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.machines.RocketUpgraderBlock;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.NasaWorkbenchMenu;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

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

        private final ResultContainer resultInventory;
        private final Slot resultSlot;
        public GuiContainer(int id, Inventory inv, RocketUpgraderBlockEntity blockEntity) {
            super(ContainerRegistry.ROCKET_UPGRADER_GUI.get(), id);
            this.blockEntity = blockEntity;

            this.resultInventory = new ResultContainer() {
                @Override
                public ItemStack removeItem(int p_40149_, int p_40150_) {
                    ItemStack stack = super.removeItem(p_40149_, p_40150_);
                    return stack;
                }

                @Override
                public ItemStack removeItemNoUpdate(int p_40160_) {
                    ItemStack stack = super.removeItemNoUpdate(p_40160_);
                    return stack;
                }
            };

            IItemHandlerModifiable itemHandler = blockEntity.getItemHandler();
            this.addSlot(new SlotItemHandler(itemHandler, 0, 40, 57));
            this.addSlot(new SlotItemHandler(itemHandler, 1, 67, 57));

            this.resultSlot = this.addSlot(new RocketUpgraderResultSlot(this.resultInventory, 2, 135, 57, blockEntity));

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
            return MenuHelper.transferStackInSlot(this, playerIn, index, this.getBlockEntity(),
                    this::moveItemStackTo);
        }


        public ResultContainer getResultInventory() {
            return this.resultInventory;
        }

        public Slot getResultSlot() {
            return this.resultSlot;
        }
    }
}
