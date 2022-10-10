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
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.CoalGeneratorBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.common.menus.CoalGeneratorMenu;
import net.mrscauthd.beyond_earth.common.util.Rectangle2d;

@OnlyIn(Dist.CLIENT)
public class CoalGeneratorScreen extends AbstractContainerScreen<CoalGeneratorMenu.GuiContainer> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/coal_generator.png");

    public static final int FIRE_LEFT = 81;
    public static final int FIRE_TOP = 47;

    public static final int ENERGY_LEFT = 146;
    public static final int ENERGY_TOP = 17;

    public CoalGeneratorScreen(CoalGeneratorMenu.GuiContainer container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.titleLabelY -= 2;
        this.titleLabelX -= 4;
        this.inventoryLabelY = this.imageHeight - 92;
    }

    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);

        if (GuiHelper.isHover(this.getEnergyBounds(), mouseX, mouseY)) {
            this.renderTooltip(ms,
                    GaugeTextHelper.getStorageText(
                            GaugeValueHelper.getEnergy(this.getMenu().getBlockEntity().getGeneratingEnergyStorage()))
                            .build(),
                    mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(PoseStack ms, float p_97788_, int p_97789_, int p_97790_) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        GuiComponent.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth,
                this.imageHeight);

        CoalGeneratorBlockEntity blockEntity = this.getMenu().getBlockEntity();
        GuiHelper.drawFire(ms, this.leftPos + FIRE_LEFT, this.topPos + FIRE_TOP,
                blockEntity.getPowerSystemGenerating().getStoredRatio());
        GuiHelper.drawEnergy(ms, this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP,
                blockEntity.getGeneratingEnergyStorage());
    }

    public Rectangle2d getEnergyBounds() {
        return GuiHelper.getEnergyBounds(this.leftPos + ENERGY_LEFT, this.topPos + ENERGY_TOP);
    }
}
