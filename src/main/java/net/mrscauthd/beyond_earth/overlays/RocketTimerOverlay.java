package net.mrscauthd.beyond_earth.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.events.Methods;

public class RocketTimerOverlay implements IGuiOverlay {

    public static final ResourceLocation TIMER_1_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer1.png");
    public static final ResourceLocation TIMER_2_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer2.png");
    public static final ResourceLocation TIMER_3_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer3.png");
    public static final ResourceLocation TIMER_4_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer4.png");
    public static final ResourceLocation TIMER_5_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer5.png");
    public static final ResourceLocation TIMER_6_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer6.png");
    public static final ResourceLocation TIMER_7_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer7.png");
    public static final ResourceLocation TIMER_8_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer8.png");
    public static final ResourceLocation TIMER_9_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer9.png");
    public static final ResourceLocation TIMER_10_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/timer/timer10.png");

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
                RenderSystem.setShaderTexture(0, TIMER_10_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 20 && timer < 40) {
                RenderSystem.setShaderTexture(0, TIMER_9_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 40 && timer < 60) {
                RenderSystem.setShaderTexture(0, TIMER_8_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 60 && timer < 80) {
                RenderSystem.setShaderTexture(0, TIMER_7_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 80 && timer < 100) {
                RenderSystem.setShaderTexture(0, TIMER_6_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 100 && timer < 120) {
                RenderSystem.setShaderTexture(0, TIMER_5_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 120 && timer < 140) {
                RenderSystem.setShaderTexture(0, TIMER_4_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 140 && timer < 160) {
                RenderSystem.setShaderTexture(0, TIMER_3_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 160 && timer < 180) {
                RenderSystem.setShaderTexture(0, TIMER_2_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            } else if (timer > 180 && timer < 200) {
                RenderSystem.setShaderTexture(0, TIMER_1_TEXTURE);
                gui.blit(poseStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
        }
    }
}
