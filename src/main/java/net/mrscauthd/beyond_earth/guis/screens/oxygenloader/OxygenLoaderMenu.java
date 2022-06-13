package net.mrscauthd.beyond_earth.guis.screens.oxygenloader;

public class OxygenLoaderMenu {
/*
	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			BlockPos pos = extraData.readBlockPos();
			OxygenLoaderBlockEntity blockEntity = (OxygenLoaderBlockEntity) inv.player.level.getBlockEntity(pos);
			return new GuiContainer(id, inv, blockEntity);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		private OxygenLoaderBlockEntity blockEntity;

		public GuiContainer(int id, Inventory inv, OxygenLoaderBlockEntity blockEntity) {
			super(ScreensRegistry.OXYGEN_LOADER_GUI.get(), id);
			this.blockEntity = blockEntity;

			IItemHandlerModifiable internal = blockEntity.getItemHandler();
			this.addSlot(new SlotItemHandler(internal, OxygenMakingBlockEntity.SLOT_INPUT_SOURCE, 26, 22));
			this.addSlot(new SlotItemHandler(internal, OxygenLoaderBlockEntity.SLOT_OUTPUT_SINK, 92, 52));
			this.addSlot(new SlotItemHandler(internal, OxygenMakingBlockEntity.SLOT_INPUT_SINK, 26, 52));
			this.addSlot(new SlotItemHandler(internal, OxygenLoaderBlockEntity.SLOT_OUTPUT_SOURCE, 92, 22));

			ContainerHelper.addInventorySlots(this, inv, 8, 90, this::addSlot);
		}

		public OxygenLoaderBlockEntity getBlockEntity() {
			return this.blockEntity;
		}

		@Override
		public boolean stillValid(Player p_38874_) {
			return !this.getBlockEntity().isRemoved();
		}

		@Override
		public ItemStack quickMoveStack(Player playerIn, int index) {
			return ContainerHelper.transferStackInSlot(this, playerIn, index, this.getBlockEntity(), this::moveItemStackTo);
		}
	}*/
}
