package net.mrscauthd.beyond_earth.client.screens;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValue;
import net.mrscauthd.beyond_earth.common.menus.RoverMenu;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class RoverScreen extends AbstractContainerScreen<RoverMenu.GuiContainer> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/gui/rover.png");
    public static final ResourceLocation FLUID_TANK_OVERLAY = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/fluid_tank_overlay.png");

    public RoverScreen(RoverMenu.GuiContainer container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = 177;
        this.imageHeight = 181;
        this.inventoryLabelY = this.imageHeight - 93;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);

        int fx = 50;
        int fy = 25;

        if (ScreenHelper.isInArea(mouseX, mouseY, this.leftPos + fx, this.topPos + fy, 15, 49)) {
            List<FormattedCharSequence> toolTip = new ArrayList<>();
            toolTip.add(GaugeTextHelper.buildFuelStorageTooltip(this.getFuel(), ChatFormatting.WHITE).getVisualOrderText());
            this.setTooltipForNextRenderPass(toolTip);
            this.renderTooltip(graphics, mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float p_97788_, int p_97789_, int p_97790_) {
        /** BACKGROUND */
        ScreenHelper.drawTexture(this.leftPos, this.topPos, this.imageWidth, this.imageHeight, TEXTURE, false);

        int fx = 50;
        int fy = 25;

        /** FUEL RENDERER */
        IGaugeValue fuel = this.getFuel();
        FluidStack fluidStack = new FluidStack(ItemsRegistry.FUEL_BUCKET.get().getFluid(), fuel.getAmount());
        ScreenHelper.renderFluid.drawFluidVertical(graphics.pose(), fluidStack, this.leftPos + fx + 1, this.topPos + fy + 1, 12, 46,
                fuel.getCapacity());

        /** FUEL TANK OVERLAY */
        ScreenHelper.drawTexture(this.leftPos + fx, this.topPos + fy, 14, 48, FLUID_TANK_OVERLAY, false);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int p_97809_, int p_97810_) {
        graphics.drawString(this.font, title.getString(), (this.imageWidth / 2) - 14, this.titleLabelY, 4210752);
        graphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752);
    }

    public IGaugeValue getFuel() {
        return menu.rover.getFuelGauge();
    }
}
