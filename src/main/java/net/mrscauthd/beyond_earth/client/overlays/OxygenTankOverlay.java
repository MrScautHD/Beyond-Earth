package net.mrscauthd.beyond_earth.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenUtil;
import net.mrscauthd.beyond_earth.common.util.Methods;

public class OxygenTankOverlay implements IGuiOverlay {

    public static final ResourceLocation OXYGEN_TANK = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/oxygen_tank.png");
    public static final ResourceLocation OXYGEN_TANK_FULL = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/oxygen_tank_full.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Player player = Minecraft.getInstance().player;

        if (Methods.isLivingInAnySpaceSuits(player)) {
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            Minecraft mc = Minecraft.getInstance();

            /** OXYGEN TANK IMAGE */
            IOxygenStorage oxygenStorage = OxygenUtil.getItemStackOxygenStorage(chest);
            if (oxygenStorage != null) {

                int x = 5;
                int y = 5;

                int textureWidth = 62;
                int textureHeight = 52;

                /** DRAW OXYGEN TANK */
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                ScreenHelper.drawTexture(poseStack, x, y, textureWidth, textureHeight, OXYGEN_TANK, false);
                ScreenHelper.drawVertical(poseStack, x, y, textureWidth, textureHeight, oxygenStorage.getOxygen(), oxygenStorage.getMaxCapacity(), OXYGEN_TANK_FULL, false);

                /** OXYGEN AMOUNT TEXT */
                Font font = mc.font;
                Component text = Component.translatable("general." + BeyondEarth.MODID + ".oxygen").append(": ").withStyle(ChatFormatting.BLUE).append("\u00A77" + oxygenStorage.getOxygen() / (oxygenStorage.getMaxCapacity() / 100) + "%");
                font.drawShadow(poseStack, text, (x + (textureWidth - font.width(text)) / 2), y + textureHeight + 3, 0xFFFFFF);
            }
        }
    }
}
