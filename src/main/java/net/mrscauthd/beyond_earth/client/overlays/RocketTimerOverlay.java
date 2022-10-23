package net.mrscauthd.beyond_earth.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.util.Methods;

public class RocketTimerOverlay implements IGuiOverlay {

    public static final ResourceLocation TIMER_1 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_1.png");
    public static final ResourceLocation TIMER_2 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_2.png");
    public static final ResourceLocation TIMER_3 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_3.png");
    public static final ResourceLocation TIMER_4 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_4.png");
    public static final ResourceLocation TIMER_5 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_5.png");
    public static final ResourceLocation TIMER_6 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_6.png");
    public static final ResourceLocation TIMER_7 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_7.png");
    public static final ResourceLocation TIMER_8 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_8.png");
    public static final ResourceLocation TIMER_9 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_9.png");
    public static final ResourceLocation TIMER_10 = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/timer/timer_10.png");

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (Methods.isRocket(player.getVehicle())) {
            Entity vehicle = Minecraft.getInstance().player.getVehicle();
            int timer = 0;

            /** GET TIMER */
            if (vehicle instanceof IRocketEntity) {
                timer = vehicle.getEntityData().get(IRocketEntity.START_TIMER);
            }

            int timerWidth = width / 2 - 31;
            int timerHeight = height / 2 / 2;

            /** TIMER */
            if (timer > -1 && timer < 20) {
                RenderSystem.setShaderTexture(0, TIMER_10);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 20 && timer < 40) {
                RenderSystem.setShaderTexture(0, TIMER_9);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 40 && timer < 60) {
                RenderSystem.setShaderTexture(0, TIMER_8);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 60 && timer < 80) {
                RenderSystem.setShaderTexture(0, TIMER_7);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 80 && timer < 100) {
                RenderSystem.setShaderTexture(0, TIMER_6);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 100 && timer < 120) {
                RenderSystem.setShaderTexture(0, TIMER_5);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 120 && timer < 140) {
                RenderSystem.setShaderTexture(0, TIMER_4);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 140 && timer < 160) {
                RenderSystem.setShaderTexture(0, TIMER_3);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 160 && timer < 180) {
                RenderSystem.setShaderTexture(0, TIMER_2);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 180 && timer < 200) {
                RenderSystem.setShaderTexture(0, TIMER_1);
                GuiComponent.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
        }
    }
}
