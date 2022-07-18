package net.mrscauthd.beyond_earth.client.screens;

//@OnlyIn(Dist.CLIENT)
public class WaterPumpScreen /*extends AbstractContainerScreen<WaterPumpMenu.GuiContainer>*/ {
/*
	public static final ResourceLocation texture = new ResourceLocation(BeyondEarth.MODID, "textures/screens/water_pump.png");

	public static final int WATER_TANK_LEFT = 75;
	public static final int WATER_TANK_TOP = 21;

	public static final int ENERGY_LEFT = 144;
	public static final int ENERGY_TOP = 21;

	public WaterPumpScreen(WaterPumpMenu.GuiContainer container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 177;
		this.imageHeight = 172;
		this.inventoryLabelY = this.imageHeight - 92;
	}

	public Rectangle2d getOutputTankBounds() {
		return ScreenHelper.getFluidTankBounds(this.leftPos + WATER_TANK_LEFT, this.topPos + WATER_TANK_TOP);
	}

	public Rectangle2d getEnergyBounds() {
		return ScreenHelper.getEnergyBounds(this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP);
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);

		WaterPumpBlockEntity blockEntity = (WaterPumpBlockEntity) this.getMenu().getBlockEntity();

		if (ScreenHelper.isHover(this.getOutputTankBounds(), mouseX, mouseY)) {

			this.renderTooltip(ms, GaugeTextHelper.getStorageText(GaugeValueHelper.getFluid(blockEntity.getWaterTank())).build(), mouseX, mouseY);
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

		WaterPumpBlockEntity blockEntity = this.getMenu().getBlockEntity();
		ScreenHelper.drawEnergy(ms, this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP, blockEntity.getPrimaryEnergyStorage());
		ScreenHelper.drawFluidTank(ms, this.leftPos + WATER_TANK_LEFT, this.topPos + WATER_TANK_TOP, blockEntity.getWaterTank());
	}*/
}