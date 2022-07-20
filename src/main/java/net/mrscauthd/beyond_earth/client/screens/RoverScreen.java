package net.mrscauthd.beyond_earth.client.screens;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.common.entities.RoverEntity;
import net.mrscauthd.beyond_earth.common.menus.RoverMenu;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

@OnlyIn(Dist.CLIENT)
public class RoverScreen extends AbstractContainerScreen<RoverMenu.GuiContainer> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/screens/rover.png");
	public static final ResourceLocation FLUID_TANK_TOP = new ResourceLocation(BeyondEarth.MODID, "textures/fluid_tank_top.png");

	public RoverScreen(RoverMenu.GuiContainer container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 176;
		this.imageHeight = 176;
		this.inventoryLabelY = this.imageHeight - 93;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);

		if (ScreenHelper.isInArea(mouseX, mouseY, this.leftPos + 8, this.topPos + 10, 15,49)) {
			List<Component> toolTip = new ArrayList<>();
			toolTip.add(Component.translatable("general." + BeyondEarth.MODID + ".fuel").append(": ").withStyle(ChatFormatting.BLUE).append("\u00A77" + this.getFuel() / 30 + "%"));

			this.renderComponentTooltip(ms, toolTip, mouseX, mouseY);
		}
	}

	@Override
	protected void renderBg(PoseStack ms, float p_97788_, int p_97789_, int p_97790_) {

		/** DEFAULT RENDER SETTINGS */
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

		/** BACKGROUND */
		ScreenHelper.drawTexture(ms, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, TEXTURE);

		/** FUEL RENDERER */
		FluidStack fluidStack = new FluidStack(ItemsRegistry.FUEL_BUCKET.get().getFluid(), this.getFuel());
		ScreenHelper.renderFluid.drawFluidVertical(ms, fluidStack, this.leftPos + 10, this.topPos + 12, 12, 46, 3000);

		/** FUEL TANK TOP */
		ScreenHelper.drawTexture(ms, this.leftPos + 9, this.topPos + 11, 14, 48, FLUID_TANK_TOP);
	}

	@Override
	protected void renderLabels(PoseStack ms, int p_97809_, int p_97810_) {
		this.font.draw(ms, title.getString(), (float) (this.imageWidth / 2) - 14, (float) this.titleLabelY, 4210752);
		this.font.draw(ms, this.playerInventoryTitle, (float) this.inventoryLabelX, (float) this.inventoryLabelY, 4210752);
	}

	public int getFuel() {
		return menu.rover.getEntityData().get(RoverEntity.FUEL);
	}
}
