package net.mrscauthd.beyond_earth.client.overlays;

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
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenProvider;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenStorage;
import net.mrscauthd.beyond_earth.common.util.Methods;

public class OxygenTankOverlay implements IGuiOverlay {

    public static final ResourceLocation OXYGEN_TANK_EMPTY_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/oxygen_tank_empty.png");
    public static final ResourceLocation OXYGEN_TANK_FULL_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/oxygen_tank_full.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Player player = Minecraft.getInstance().player;

        if (Methods.isLivingInAnySpaceSuits(player)) {
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            Minecraft mc = Minecraft.getInstance();

            /** OXYGEN TANK IMAGE */
            OxygenStorage oxygenStorage = chest.getCapability(OxygenProvider.OXYGEN).orElse(null);
            if (oxygenStorage != null) {

                int x = 5;
                int y = 5;

                int textureWidth = 62;
                int textureHeight = 52;

                ScreenHelper.drawTexture(poseStack, x, y, textureWidth, textureHeight, OXYGEN_TANK_EMPTY_TEXTURE);
                ScreenHelper.drawVertical(poseStack, x, y, textureWidth, textureHeight, oxygenStorage.getOxygen(), oxygenStorage.getMaxCapacity(), OXYGEN_TANK_FULL_TEXTURE);

                /** OXYGEN AMOUNT TEXT */
                Font font = mc.font;
                Component text = Component.translatable("general." + BeyondEarth.MODID + ".oxygen").append(": ").withStyle(ChatFormatting.BLUE).append("\u00A77" + oxygenStorage.getOxygen() / 480 + "%");
                font.drawShadow(poseStack, text, (x + (textureWidth - font.width(text)) / 2), y + textureHeight + 3, 0xFFFFFF);
            }
        }
    }
}
