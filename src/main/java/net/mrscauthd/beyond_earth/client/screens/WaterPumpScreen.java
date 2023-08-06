package net.mrscauthd.beyond_earth.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.util.GuiHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.WaterPumpBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.common.menus.WaterPumpMenu;
import net.mrscauthd.beyond_earth.common.util.Rectangle2d;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class WaterPumpScreen extends AbstractContainerScreen<WaterPumpMenu.GuiContainer> {

    public static final ResourceLocation texture = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/water_pump.png");

    public static final int WATER_TANK_LEFT = 81;
    public static final int WATER_TANK_TOP = 23;

    public static final int ENERGY_LEFT = 146;
    public static final int ENERGY_TOP = 22;

    public WaterPumpScreen(WaterPumpMenu.GuiContainer container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = 177;
        this.imageHeight = 172;
        this.inventoryLabelY = this.imageHeight - 92;
        this.titleLabelY -= 2;
    }

    public Rectangle2d getOutputTankBounds() {
        return GuiHelper.getFluidTankBounds(this.leftPos + WATER_TANK_LEFT, this.topPos + WATER_TANK_TOP);
    }

    public Rectangle2d getEnergyBounds() {
        return GuiHelper.getEnergyBounds(this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);

        WaterPumpBlockEntity blockEntity = this.getMenu().getBlockEntity();

        if (GuiHelper.isHover(this.getOutputTankBounds(), mouseX, mouseY)) {
            this.setTooltipForNextRenderPass(GaugeTextHelper.getStorageText(GaugeValueHelper.getFluid(blockEntity.getWaterTank())).build());
            this.renderTooltip(graphics, mouseX, mouseY);
        } else if (GuiHelper.isHover(this.getEnergyBounds(), mouseX, mouseY)) {
            this.setTooltipForNextRenderPass( GaugeTextHelper.getStorageText(GaugeValueHelper.getEnergy(blockEntity)).build());
            this.renderTooltip(graphics, mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float p_97788_, int p_97789_, int p_97790_) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        graphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth,
                this.imageHeight);

        WaterPumpBlockEntity blockEntity = this.getMenu().getBlockEntity();
        GuiHelper.drawEnergy(graphics, this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP,
                Objects.requireNonNull(blockEntity.getPrimaryEnergyStorage()));
        GuiHelper.drawFluidTank(graphics, this.leftPos + WATER_TANK_LEFT, this.topPos + WATER_TANK_TOP,
                blockEntity.getWaterTank());
    }
}