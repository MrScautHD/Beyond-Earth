package net.mrscauthd.beyond_earth.client.screens;

import java.util.ArrayList;
import java.util.List;

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
import net.mrscauthd.beyond_earth.common.menus.RoverMenu;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

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
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);

        int fx = 50;
        int fy = 25;

        if (ScreenHelper.isInArea(mouseX, mouseY, this.leftPos + fx, this.topPos + fy, 15, 49)) {
            List<Component> toolTip = new ArrayList<>();
            toolTip.add(GaugeTextHelper.buildFuelStorageTooltip(this.getFuel(), ChatFormatting.WHITE));

            this.renderComponentTooltip(ms, toolTip, mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(PoseStack ms, float p_97788_, int p_97789_, int p_97790_) {
        /** BACKGROUND */
        ScreenHelper.drawTexture(ms, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, TEXTURE, false);

        int fx = 50;
        int fy = 25;

        /** FUEL RENDERER */
        IGaugeValue fuel = this.getFuel();
        FluidStack fluidStack = new FluidStack(ItemsRegistry.FUEL_BUCKET.get().getFluid(), fuel.getAmount());
        ScreenHelper.renderFluid.drawFluidVertical(ms, fluidStack, this.leftPos + fx + 1, this.topPos + fy + 1, 12, 46, 
                fuel.getCapacity());

        /** FUEL TANK OVERLAY */
        ScreenHelper.drawTexture(ms, this.leftPos + fx, this.topPos + fy, 14, 48, FLUID_TANK_OVERLAY, false);
    }

    @Override
    protected void renderLabels(PoseStack ms, int p_97809_, int p_97810_) {
        this.font.draw(ms, title.getString(), (float) (this.imageWidth / 2) - 14, (float) this.titleLabelY, 4210752);
        this.font.draw(ms, this.playerInventoryTitle, (float) this.inventoryLabelX, (float) this.inventoryLabelY,
                4210752);
    }

    public IGaugeValue getFuel() {
        return menu.rover.getFuelGauge();
    }
}
