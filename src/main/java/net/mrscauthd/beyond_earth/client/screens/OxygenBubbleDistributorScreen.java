package net.mrscauthd.beyond_earth.client.screens;

//@OnlyIn(Dist.CLIENT)
public class OxygenBubbleDistributorScreen/* extends AbstractContainerScreen<OxygenBubbleDistributorMenu.GuiContainer> */{
/*
	public static final ResourceLocation texture = new ResourceLocation(BeyondEarth.MODID, "textures/screens/oxygen_bubble_distributor.png");

	public static final int INPUT_TANK_LEFT = 9;
	public static final int INPUT_TANK_TOP = 21;

	public static final int OUTPUT_TANK_LEFT = 75;
	public static final int OUTPUT_TANK_TOP = 21;

	public static final int ENERGY_LEFT = 144;
	public static final int ENERGY_TOP = 21;

	public static final int ARROW_LEFT = 48;
	public static final int ARROW_TOP = 36;

	private boolean cachedWorkingAreaVisible = true;

	// Buttons
	public ImageButtonPlacer workingAreaVisibleButton;
	public ImageButtonPlacer button_plus;
	public ImageButtonPlacer button_minus;

	private static final ResourceLocation TECHNIK_BUTTON = new ResourceLocation(BeyondEarth.MODID, "textures/buttons/technik_button.png");
	private static final ResourceLocation LIGHT_TECHNIK_BUTTON = new ResourceLocation(BeyondEarth.MODID, "textures/buttons/technik_button_2.png");

	private static final ResourceLocation TECHNIK_BUTTON_PLUS = new ResourceLocation(BeyondEarth.MODID, "textures/buttons/technik_button_plus.png");
	private static final ResourceLocation LIGHT_TECHNIK_BUTTON_PLUS = new ResourceLocation(BeyondEarth.MODID, "textures/buttons/technik_button_plus_2.png");

	private static final ResourceLocation TECHNIK_BUTTON_MINUS = new ResourceLocation(BeyondEarth.MODID, "textures/buttons/technik_button_minus.png");
	private static final ResourceLocation LIGHT_TECHNIK_BUTTON_MINUS = new ResourceLocation(BeyondEarth.MODID, "textures/buttons/technik_button_minus_2.png");

	public OxygenBubbleDistributorScreen(OxygenBubbleDistributorMenu.GuiContainer container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 177;
		this.imageHeight = 172;
		this.inventoryLabelY = this.imageHeight - 92;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		this.updateWorkingAreaVisibleButton();
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);

		OxygenBubbleDistributorBlockEntity blockEntity = (OxygenBubbleDistributorBlockEntity) this.getMenu().getBlockEntity();

		if (ScreenHelper.isHover(this.getInputTankBounds(), mouseX, mouseY)) {

			this.renderTooltip(ms, GaugeTextHelper.getStorageText(GaugeValueHelper.getFluid(blockEntity.getInputTank())).build(), mouseX, mouseY);
		} else if (ScreenHelper.isHover(this.getOutputTankBounds(), mouseX, mouseY)) {

			this.renderTooltip(ms, GaugeTextHelper.getStorageText(GaugeValueHelper.getOxygen(blockEntity.getOutputTank())).build(), mouseX, mouseY);
		} else if (ScreenHelper.isHover(this.getEnergyBounds(), mouseX, mouseY)) {

			this.renderTooltip(ms, GaugeTextHelper.getStorageText(GaugeValueHelper.getEnergy(blockEntity)).build(), mouseX, mouseY);
		}
	}

	@Override
	protected void renderBg(PoseStack ms, float p_97788_, int p_97789_, int p_97790_) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

		RenderSystem.setShaderTexture(0, texture);
		GuiComponent.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		OxygenBubbleDistributorBlockEntity blockEntity = this.getMenu().getBlockEntity();
		ScreenHelper.drawEnergy(ms, this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP, blockEntity.getPrimaryEnergyStorage());
		ScreenHelper.drawFluidTank(ms, this.leftPos + INPUT_TANK_LEFT, this.topPos + INPUT_TANK_TOP, blockEntity.getInputTank());
		ScreenHelper.drawOxygenTank(ms, this.leftPos + OUTPUT_TANK_LEFT, this.topPos + OUTPUT_TANK_TOP, blockEntity.getOutputTank());
	}

	@Override
	protected void init() {
		super.init();

		button_plus = this.addRenderableWidget(new ImageButtonPlacer(this.leftPos - 20, this.topPos + 5, 0, 20, 20, 0, 0, 0, TECHNIK_BUTTON_PLUS, LIGHT_TECHNIK_BUTTON_PLUS, 20, 20, (p_2130901) -> {
			BlockPos pos = this.getMenu().getBlockEntity().getBlockPos();
			NetworksRegistry.PACKET_HANDLER.sendToServer(new OxygenBubbleDistributorBlockEntity.ChangeRangeMessage(pos, true));
		}));

		button_minus = this.addRenderableWidget(new ImageButtonPlacer(this.leftPos - 20, this.topPos + 25, 0, 20, 20, 0, 0, 0, TECHNIK_BUTTON_MINUS, LIGHT_TECHNIK_BUTTON_MINUS, 20, 20, (p_2130901) -> {
			BlockPos pos = this.getMenu().getBlockEntity().getBlockPos();
			NetworksRegistry.PACKET_HANDLER.sendToServer(new OxygenBubbleDistributorBlockEntity.ChangeRangeMessage(pos, false));
		}));

		workingAreaVisibleButton = this.addRenderableWidget(new ImageButtonPlacer(this.leftPos - 20, this.topPos - 22, 0, 34, 20, 0, 0, 0, TECHNIK_BUTTON, LIGHT_TECHNIK_BUTTON, 34, 20, e -> {
			BlockPos pos = this.getMenu().getBlockEntity().getBlockPos();
			NetworksRegistry.PACKET_HANDLER.sendToServer(new OxygenBubbleDistributorBlockEntity.ChangeWorkingAreaVisibleMessage(pos, !this.cachedWorkingAreaVisible));
		}));
	}

	@Override
	protected void renderLabels(PoseStack ms, int mouseX, int mouseY) {
		super.renderLabels(ms, mouseX, mouseY);

		OxygenBubbleDistributorBlockEntity blockEntity = this.getMenu().getBlockEntity();
		double range = blockEntity.getRange();
		NumberFormat numberInstance = NumberFormat.getNumberInstance();
		numberInstance.setMaximumFractionDigits(2);
		String rangeToString = numberInstance.format((range * 2.0D) + 1.0D);
		Component workingAreaText = Component.translatable("gui." + BeyondEarth.MODID + ".oxygen_bubble_distributor.workingarea.text", rangeToString, rangeToString, rangeToString);

		int sideWidth = 2;
		int sidePadding = 2;
		int workingAreaHeight = 25;
		int workingAreaLeft = this.workingAreaVisibleButton.x + this.workingAreaVisibleButton.getWidth() - this.leftPos;
		int workingAreaTop = -workingAreaHeight;
		int workingAreaOffsetX = workingAreaLeft;

		int textwidth = 12;

		if ((range * 2) + 1 > 9) {
			RenderSystem.setShaderTexture(0, new ResourceLocation(BeyondEarth.MODID, "textures/buttons/oxygen_range_layer.png"));
			GuiComponent.blit(ms, workingAreaOffsetX + 1, workingAreaTop, 0, 0, 150, 25, 150, 25);
			textwidth = 13;
		} else {
			RenderSystem.setShaderTexture(0, new ResourceLocation(BeyondEarth.MODID, "textures/buttons/oxygen_range_small_layer.png"));
			GuiComponent.blit(ms, workingAreaOffsetX + 1, workingAreaTop, 0, 0, 140, 25, 140, 25);
			textwidth = 17;
		}

		this.font.draw(ms, workingAreaText, workingAreaLeft + sideWidth + sidePadding + textwidth, workingAreaTop + 9, 0x339900);

		ms.pushPose();
		float oyxgenScale = 0.8F;
		ms.scale(oyxgenScale, oyxgenScale, oyxgenScale);
		Component oxygenText = GaugeTextHelper.getUsingText2(GaugeValueHelper.getOxygen(blockEntity.getOxygenUsing(range)), blockEntity.getMaxTimer()).build();
		int oxygenWidth = this.font.width(oxygenText);
		this.font.draw(ms, oxygenText, (int) ((this.imageWidth - 5) / oyxgenScale) - oxygenWidth, (int) (this.inventoryLabelY / oyxgenScale), 0x333333);
		ms.popPose();

		String prefix = "gui." + BeyondEarth.MODID + ".oxygen_bubble_distributor.workingarea.";
		String method = this.cachedWorkingAreaVisible ? "hide" : "show";
		this.font.draw(ms, Component.translatable(prefix + method), workingAreaLeft + sideWidth + sidePadding + (this.cachedWorkingAreaVisible ? -30 : -32), workingAreaTop + 9, 0x339900);
	}

	private void updateWorkingAreaVisibleButton() {
		boolean next = this.getMenu().getBlockEntity().isWorkingAreaVisible();

		if (this.cachedWorkingAreaVisible != next) {
			this.cachedWorkingAreaVisible = next;
		}
	}

	public Rectangle2d getInputTankBounds() {
		return ScreenHelper.getFluidTankBounds(this.leftPos + INPUT_TANK_LEFT, this.topPos + INPUT_TANK_TOP);
	}

	public Rectangle2d getOutputTankBounds() {
		return ScreenHelper.getFluidTankBounds(this.leftPos + OUTPUT_TANK_LEFT, this.topPos + OUTPUT_TANK_TOP);
	}

	public Rectangle2d getEnergyBounds() {
		return ScreenHelper.getEnergyBounds(this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP);
	}*/
}
