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
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenCapability;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class OxygenTankOverlay implements IGuiOverlay {

    public static final ResourceLocation OXYGEN_TANK_EMPTY_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/oxygen_tank_empty.png");
    public static final ResourceLocation OXYGEN_TANK_FULL_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/oxygen_tank_full.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Player player = Minecraft.getInstance().player;
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        //TODO CHECK EVERY IF CHECK (IF THAT WORKS LIKE THIS)
        if (chest.getItem() == ItemsRegistry.SPACE_SUIT.get() || chest.getItem() == ItemsRegistry.NETHERITE_SPACE_SUIT.get() || chest.getItem() == ItemsRegistry.JET_SUIT.get()) {
            Minecraft mc = Minecraft.getInstance();

            /** OXYGEN TANK IMAGE */
            IOxygenStorage oxygenStorage = chest.getCapability(OxygenCapability.OXYGEN).orElse(null);
            if (oxygenStorage != null) {

                int x = 5;
                int y = 5;

                int textureWidth = 62;
                int textureHeight = 52;

                ScreenHelper.drawTexture(poseStack, x, y, textureWidth, textureHeight, OXYGEN_TANK_EMPTY_TEXTURE);
                ScreenHelper.drawVertical(poseStack, x, y, textureWidth, textureHeight, oxygenStorage.getOxygenStored(), oxygenStorage.getMaxOxygenStored(), OXYGEN_TANK_FULL_TEXTURE);

                /** OXYGEN AMOUNT TEXT */
                Font font = mc.font;
                Component text = Component.translatable("general." + BeyondEarth.MODID + ".oxygen").append(": ").withStyle(ChatFormatting.BLUE).append("\u00A77" + oxygenStorage.getOxygenStored() / 48 + "%");
                font.drawShadow(poseStack, text, (x + (textureWidth - font.width(text)) / 2), y + textureHeight + 3, 0xFFFFFF);
            }
        }
    }
}
