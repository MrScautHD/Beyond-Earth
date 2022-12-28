package net.mrscauthd.beyond_earth.common.menus.nasaworkbench;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.NASAWorkbenchBlockEntity;
import net.mrscauthd.beyond_earth.common.data.recipes.WorkbenchingRecipe;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;
import net.mrscauthd.beyond_earth.common.registries.RocketPartsRegistry;

//TODO NEED FULL REWORK (GENEREL ALL MACHINE BLOCKS GUIS)
public class NasaWorkbenchMenu {

    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            BlockPos pos = extraData.readBlockPos();
            NASAWorkbenchBlockEntity blockEntity = (NASAWorkbenchBlockEntity) inv.player.level.getBlockEntity(pos);
            return new GuiContainer(id, inv, blockEntity);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final NASAWorkbenchBlockEntity blockEntity;
        private final ResultContainer resultInventory;
        private final Slot resultSlot;
        private int partSlotStart = 0;
        private int partSlotEnd = 0;

        public GuiContainer(int id, Inventory inv, NASAWorkbenchBlockEntity blockEntity) {
            super(ContainerRegistry.NASA_WORKBENCH_GUI.get(), id);
            this.blockEntity = blockEntity;

            this.resultInventory = new ResultContainer() {
                @Override
                public ItemStack removeItem(int p_40149_, int p_40150_) {
                    ItemStack stack = super.removeItem(p_40149_, p_40150_);
                    GuiContainer.this.onExtractResult(stack);
                    return stack;
                }

                @Override
                public ItemStack removeItemNoUpdate(int p_40160_) {
                    ItemStack stack = super.removeItemNoUpdate(p_40160_);
                    GuiContainer.this.onExtractResult(stack);
                    return stack;
                }
            };

            this.resultSlot = this.addSlot(new NasaWorkbenchResultSlot(this.resultInventory, 0, 128, 56, blockEntity));

            this.partSlotStart = this.slots.size();

            RocketPartsItemHandler partsItemHandler = blockEntity.getPartsItemHandler();
            GridPlacer placer = new GridPlacer();

            int dx = 15;
            int dy = 2;

            RocketPartGridPlacer.placeContainer(40 + dx, 18 + dy, 1, placer::placeBottom,
                    RocketPartsRegistry.ROCKET_PART_NOSE.get(), partsItemHandler, this::addSlot);
            RocketPartGridPlacer.placeContainer(31 + dx, 36 + dy, 2, placer::placeBottom,
                    RocketPartsRegistry.ROCKET_PART_BODY.get(), partsItemHandler, this::addSlot);
            RocketPartGridPlacer.placeContainer(31 + dx, 90 + dy, 1, placer::placeRight,
                    RocketPartsRegistry.ROCKET_PART_TANK.get(), partsItemHandler, this::addSlot);
            RocketPartGridPlacer.placeContainer(13 + dx, 90 + dy, 1, placer::placeBottom,
                    RocketPartsRegistry.ROCKET_PART_FIN_LEFT.get(), partsItemHandler, this::addSlot);
            RocketPartGridPlacer.placeContainer(67 + dx, 90 + dy, 1, placer::placeBottom,
                    RocketPartsRegistry.ROCKET_PART_FIN_RIGHT.get(), partsItemHandler, this::addSlot);
            RocketPartGridPlacer.placeContainer(40 + dx, 108 + dy, 1, placer::placeBottom,
                    RocketPartsRegistry.ROCKET_PART_ENGINE.get(), partsItemHandler, this::addSlot);

            this.partSlotEnd = this.slots.size();

            MenuHelper.createInventorySlots(inv, this::addSlot, 8, 142);
        }

        private void onExtractResult(ItemStack stack) {
            NASAWorkbenchBlockEntity blockEntity = this.getBlockEntity();

            if (!stack.isEmpty() && blockEntity.cacheRecipes() != null) {
                blockEntity.consumeIngredient();
            }
        }

        @Override
        public void broadcastChanges() {
            super.broadcastChanges();

            WorkbenchingRecipe recipe = this.getBlockEntity().cacheRecipes();

            this.resultSlot.set(recipe != null ? recipe.getOutput() : ItemStack.EMPTY);
        }

        @Override
        public boolean stillValid(Player p_38874_) {
            return !this.getBlockEntity().isRemoved();
        }

        @Override
        public ItemStack quickMoveStack(Player playerIn, int slotNumber) {
            if (this.partSlotStart <= slotNumber && slotNumber < this.partSlotEnd) {
                return MenuHelper.transferStackInSlot(this, playerIn, slotNumber, slotNumber - this.partSlotStart,
                        this.getBlockEntity(), this::moveItemStackTo);
            } else if (slotNumber == this.resultSlot.index) {
                Slot slot = this.getSlot(slotNumber);
                ItemStack prev = slot.getItem().copy();
                ItemStack itemStack = MenuHelper.transferStackInSlot(this, playerIn, slotNumber, this.getBlockEntity(),
                        this::moveItemStackTo);

                if (slotNumber == this.resultSlot.index) {
                    ItemStack next = slot.getItem().copy();

                    if (!prev.isEmpty()) {
                        int nextSize = next.isEmpty() ? 0 : next.getCount();

                        if (nextSize > 0) {
                            playerIn.drop(next, false);
                            slot.set(ItemStack.EMPTY);
                        }
                    }
                    this.onExtractResult(prev);
                }

                return itemStack;
            } else {
                return MenuHelper.transferStackInSlot(this, playerIn, slotNumber, this.getBlockEntity(),
                        this::moveItemStackTo);
            }
        }

        public NASAWorkbenchBlockEntity getBlockEntity() {
            return this.blockEntity;
        }

        public ResultContainer getResultInventory() {
            return this.resultInventory;
        }

        public Slot getResultSlot() {
            return this.resultSlot;
        }
    }
}
