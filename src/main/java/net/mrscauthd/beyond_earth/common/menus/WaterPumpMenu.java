package net.mrscauthd.beyond_earth.common.menus;

public class WaterPumpMenu {

	/*
	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			BlockPos pos = extraData.readBlockPos();
			WaterPumpBlockEntity blockEntity = (WaterPumpBlockEntity) inv.player.level.getBlockEntity(pos);
			return new GuiContainer(id, inv, blockEntity);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		private final WaterPumpBlockEntity blockEntity;

		public GuiContainer(int id, Inventory inv, WaterPumpBlockEntity blockEntity) {
			super(ScreensRegistry.WATER_PUMP_GUI.get(), id);
			this.blockEntity = blockEntity;

			ContainerHelper.addInventorySlots(this, inv, 8, 90, this::addSlot);
		}

		public WaterPumpBlockEntity getBlockEntity() {
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
