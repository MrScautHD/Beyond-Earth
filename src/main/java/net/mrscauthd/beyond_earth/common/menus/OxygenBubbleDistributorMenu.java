package net.mrscauthd.beyond_earth.common.menus;

public class OxygenBubbleDistributorMenu {
/*
	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			BlockPos pos = extraData.readBlockPos();
			OxygenBubbleDistributorBlockEntity blockEntity = (OxygenBubbleDistributorBlockEntity) inv.player.level.getBlockEntity(pos);
			return new GuiContainer(id, inv, blockEntity);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		private OxygenBubbleDistributorBlockEntity blockEntity;

		public GuiContainer(int id, Inventory inv, OxygenBubbleDistributorBlockEntity blockEntity) {
			super(ScreensRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_GUI.get(), id);
			this.blockEntity = blockEntity;

			IItemHandlerModifiable internal = blockEntity.getItemHandler();
			this.addSlot(new SlotItemHandler(internal, OxygenMakingBlockEntity.SLOT_INPUT_SOURCE, 26, 22));
			this.addSlot(new SlotItemHandler(internal, OxygenMakingBlockEntity.SLOT_INPUT_SINK, 26, 52));

			ContainerHelper.addInventorySlots(this, inv, 8, 90, this::addSlot);
		}

		public OxygenBubbleDistributorBlockEntity getBlockEntity() {
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
