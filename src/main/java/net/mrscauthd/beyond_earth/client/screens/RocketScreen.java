package net.mrscauthd.beyond_earth.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValue;
import net.mrscauthd.beyond_earth.common.menus.RocketMenu;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class RocketScreen extends AbstractContainerScreen<RocketMenu.GuiContainer> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/rocket.png");

	public static final ResourceLocation FLUID_TANK_OVERLAY = new ResourceLocation(BeyondEarth.MODID, "textures/gui/util/fluid_tank_overlay.png");

	public RocketScreen(RocketMenu.GuiContainer container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 177;
		this.imageHeight = 174;
		this.inventoryLabelY = this.imageHeight - 93;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);

		if (ScreenHelper.isInArea(mouseX, mouseY, this.leftPos + 49, this.topPos + 22, 15,49)) {
			List<Component> toolTip = new ArrayList<>();
			toolTip.add(GaugeTextHelper.buildFuelStorageTooltip(this.getFuel(), ChatFormatting.WHITE));

			this.renderComponentTooltip(ms, toolTip, mouseX, mouseY);
		}
	}

	@Override
	protected void renderBg(PoseStack ms, float p_98414_, int p_98415_, int p_98416_) {
		/** BACKGROUND */
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		ScreenHelper.drawTexture(ms, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, TEXTURE, false);

		/** FUEL RENDERER */
		IGaugeValue fuel = this.getFuel();
		FluidStack fluidStack = new FluidStack(ItemsRegistry.FUEL_BUCKET.get().getFluid(), fuel.getAmount());
		ScreenHelper.renderFluid.drawFluidVertical(ms, fluidStack, this.leftPos + 51, this.topPos + 24, 12, 46, fuel.getCapacity());

		/** FUEL TANK OVERLAY */
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		ScreenHelper.drawTexture(ms, this.leftPos + 51, this.topPos + 24, 12, 46, FLUID_TANK_OVERLAY, false);
	}

	@Override
	protected void renderLabels(PoseStack ms, int p_97809_, int p_97810_) {
		this.font.draw(ms, this.title.getString(), (float) (this.imageWidth / 2) - 33, (float) this.titleLabelY, 0xDBDBDB);
		this.font.draw(ms, this.playerInventoryTitle, (float) this.inventoryLabelX, (float) this.inventoryLabelY, 0xDBDBDB);
	}

	public IGaugeValue getFuel() {
		return menu.rocket.getFuelGauge();
	}
}
