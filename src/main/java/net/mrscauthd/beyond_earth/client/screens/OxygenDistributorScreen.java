package net.mrscauthd.beyond_earth.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.util.GuiHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.OxygenDistributorBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.menus.OxygenBubbleDistributorMenu;
import net.mrscauthd.beyond_earth.common.util.Rectangle2d;

@OnlyIn(Dist.CLIENT)
public class OxygenDistributorScreen extends AbstractContainerScreen<OxygenBubbleDistributorMenu.GuiContainer> {

    public static final ResourceLocation texture = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/oxygen_distributor.png");

    public static final int INPUT_TANK_LEFT = 51;
    public static final int INPUT_TANK_TOP = 58;

    public static final int OUTPUT_TANK_LEFT = 115;
    public static final int OUTPUT_TANK_TOP = 58;

    public static final int ENERGY_LEFT = 147;
    public static final int ENERGY_TOP = 58;

    public static final int ARROW_LEFT = 48;
    public static final int ARROW_TOP = 36;

    // Buttons

    public OxygenDistributorScreen(OxygenBubbleDistributorMenu.GuiContainer container, Inventory inventory,
            Component text) {
        super(container, inventory, text);
        this.imageWidth = 177;
        this.imageHeight = 220;
        this.inventoryLabelY = this.imageHeight - 92;
        this.titleLabelY += 34;
    }

    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);

        OxygenDistributorBlockEntity blockEntity = this.getMenu()
                .getBlockEntity();

        if (GuiHelper.isHover(this.getInputTankBounds(), mouseX, mouseY)) {

            this.renderTooltip(ms,
                    GaugeTextHelper.getStorageText(GaugeValueHelper.getFluid(blockEntity.getInputTank())).build(),
                    mouseX, mouseY);
        } else if (GuiHelper.isHover(this.getOutputTankBounds(), mouseX, mouseY)) {

            this.renderTooltip(ms,
                    GaugeTextHelper.getStorageText(GaugeValueHelper.getOxygen(blockEntity.getOutputTank())).build(),
                    mouseX, mouseY);
        } else if (GuiHelper.isHover(this.getEnergyBounds(), mouseX, mouseY)) {

            this.renderTooltip(ms, GaugeTextHelper.getStorageText(GaugeValueHelper.getEnergy(blockEntity)).build(),
                    mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(PoseStack ms, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.setShaderTexture(0, texture);
        GuiComponent.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth,
                this.imageHeight);

        OxygenDistributorBlockEntity blockEntity = this.getMenu().getBlockEntity();
        GuiHelper.drawEnergy(ms, this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP,
                blockEntity.getPrimaryEnergyStorage());
        GuiHelper.drawFluidTank(ms, this.leftPos + INPUT_TANK_LEFT, this.topPos + INPUT_TANK_TOP,
                blockEntity.getInputTank());
        GuiHelper.drawOxygenTank(ms, this.leftPos + OUTPUT_TANK_LEFT, this.topPos + OUTPUT_TANK_TOP,
                blockEntity.getOutputTank());
    }

    @Override
    protected void renderLabels(PoseStack ms, int mouseX, int mouseY) {
        super.renderLabels(ms, mouseX, mouseY);
        OxygenDistributorBlockEntity blockEntity = this.getMenu().getBlockEntity();
        ms.pushPose();
        float oyxgenScale = 0.8F;
        ms.scale(oyxgenScale, oyxgenScale, oyxgenScale);
        Component oxygenText = GaugeTextHelper
                .getUsingText2(GaugeValueHelper.getOxygen(Config.OXYGEN_BUBBLE_DISTRIBUTOR_RATE_OUTPUT.get()), blockEntity.getMaxTimer())
                .build();
        int oxygenWidth = this.font.width(oxygenText);
        this.font.draw(ms, oxygenText, (int) ((this.imageWidth - 5) / oyxgenScale) - oxygenWidth,
                (int) (this.inventoryLabelY / oyxgenScale), 0x333333);
        ms.popPose();
    }

    public Rectangle2d getInputTankBounds() {
        return GuiHelper.getFluidTankBounds(this.leftPos + INPUT_TANK_LEFT, this.topPos + INPUT_TANK_TOP);
    }

    public Rectangle2d getOutputTankBounds() {
        return GuiHelper.getFluidTankBounds(this.leftPos + OUTPUT_TANK_LEFT, this.topPos + OUTPUT_TANK_TOP);
    }

    public Rectangle2d getEnergyBounds() {
        return GuiHelper.getEnergyBounds(this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP);
    }
}
