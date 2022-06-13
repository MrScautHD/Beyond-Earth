package net.mrscauthd.beyond_earth.guis.screens.compressor;

public class CompressorMenu {
/*
	public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
		public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
			BlockPos pos = extraData.readBlockPos();
			CompressorBlockEntity blockEntity = (CompressorBlockEntity) inv.player.level.getBlockEntity(pos);
			return new GuiContainer(id, inv, blockEntity);
		}
	}

	public static class GuiContainer extends AbstractContainerMenu {
		private CompressorBlockEntity blockEntity;

		public GuiContainer(int id, Inventory inv, CompressorBlockEntity blockEntity) {
			super(ScreensRegistry.COMPRESSOR_GUI.get(), id);
			this.blockEntity = blockEntity;

			IItemHandlerModifiable itemHandler = blockEntity.getItemHandler();
			this.addSlot(new SlotItemHandler(itemHandler, CompressorBlockEntity.SLOT_INGREDIENT, 40, 37));

			this.addSlot(new SlotItemHandler(itemHandler, ItemStackToItemStackBlockEntity.SLOT_OUTPUT, 92, 36) {
				@Override
				public boolean mayPlace(@NotNull ItemStack stack) {
					return false;
				}
			});

			ContainerHelper.addInventorySlots(this, inv, 8, 86, this::addSlot);
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
			return ContainerHelper.transferStackInSlot(this, playerIn, index, this.getBlockEntity(), this::moveItemStackTo);
		}
	}*/
}
