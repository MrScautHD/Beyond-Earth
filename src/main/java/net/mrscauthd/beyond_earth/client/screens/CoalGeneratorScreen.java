package net.mrscauthd.beyond_earth.client.screens;

//@OnlyIn(Dist.CLIENT)
public class CoalGeneratorScreen/* extends AbstractContainerScreen<CoalGeneratorMenu.GuiContainer>*/ {
/*
	public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/screens/coal_generator.png");

	public static final int FIRE_LEFT = 77;
	public static final int FIRE_TOP = 49;

	public static final int ENERGY_LEFT = 144;
	public static final int ENERGY_TOP = 21;

	public CoalGeneratorScreen(CoalGeneratorMenu.GuiContainer container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 176;
		this.imageHeight = 166;
		this.inventoryLabelY = this.imageHeight - 92;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);

		if (ScreenHelper.isHover(this.getEnergyBounds(), mouseX, mouseY)) {
			this.renderTooltip(ms, GaugeTextHelper.getStorageText(GaugeValueHelper.getEnergy(this.getMenu().getBlockEntity().getGeneratingEnergyStorage())).build(), mouseX, mouseY);
		}
	}

	@Override
	protected void renderBg(PoseStack ms, float p_97788_, int p_97789_, int p_97790_) {

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		GuiComponent.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		CoalGeneratorBlockEntity blockEntity = this.getMenu().getBlockEntity();
		ScreenHelper.drawFire(ms, this.leftPos + FIRE_LEFT, this.topPos + FIRE_TOP, blockEntity.getPowerSystemGenerating().getStoredRatio());
		ScreenHelper.drawEnergy(ms, this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP, blockEntity.getGeneratingEnergyStorage());
	}

	public Rectangle2d getEnergyBounds() {
		return ScreenHelper.getEnergyBounds(this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP);
	}*/
}
