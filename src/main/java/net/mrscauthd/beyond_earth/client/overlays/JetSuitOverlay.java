package net.mrscauthd.beyond_earth.client.overlays;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.armors.JetSuit;
import net.mrscauthd.beyond_earth.common.util.Methods;

public class JetSuitOverlay implements IGuiOverlay {

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Player player = Minecraft.getInstance().player;

        if (Methods.isLivingInJetSuit(player)) {
            Minecraft mc = Minecraft.getInstance();
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            int itemMode = chest.getOrCreateTag().getInt(JetSuit.Suit.TAG_MODE);

            int x = 5;
            int y = 5;

            Component modeText;
            ChatFormatting chatFormatting;

            if (itemMode == JetSuit.Suit.ModeType.NORMAL.getMode()) {
                modeText = JetSuit.Suit.ModeType.NORMAL.getTranslationKey();
                chatFormatting = JetSuit.Suit.ModeType.NORMAL.getChatFormatting();
            }
            else if (itemMode == JetSuit.Suit.ModeType.ELYTRA.getMode()) {
                modeText = JetSuit.Suit.ModeType.ELYTRA.getTranslationKey();
                chatFormatting = JetSuit.Suit.ModeType.ELYTRA.getChatFormatting();
            }
            else {
                modeText = JetSuit.Suit.ModeType.DISABLED.getTranslationKey();
                chatFormatting = JetSuit.Suit.ModeType.DISABLED.getChatFormatting();
            }

            /** TEXT */
            Font font = mc.font;
            Component text = Component.translatable("general." + BeyondEarth.MODID + ".jet_suit_mode").append(": ").withStyle(chatFormatting).append(modeText.copy().withStyle(ChatFormatting.GRAY));
            font.drawShadow(poseStack, text, (x + (80 - font.width(text)) / 2), y + 80 + 3, 0xFFFFFF);
        }
    }
}
