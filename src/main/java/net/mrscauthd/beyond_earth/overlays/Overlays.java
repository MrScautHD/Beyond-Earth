package net.mrscauthd.beyond_earth.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.capabilities.oxygen.OxygenCapability;
import net.mrscauthd.beyond_earth.capabilities.oxygen.OxygenStorage;
import net.mrscauthd.beyond_earth.entities.*;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.events.forge.PlanetOverlayEvent;
import net.mrscauthd.beyond_earth.guis.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.registries.LevelRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, value = Dist.CLIENT)
public class Overlays {

    /** WARNING OVERLAY VARIABLES */
    private static boolean check = false;
    private static float counter = 0;

    /** WARNING TEXTURE */
    public static final ResourceLocation WARNING_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/warning.png");

    /** TIMER TEXTURES */
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

    /** OXYGEN TANK TEXTURES */
    public static final ResourceLocation OXYGEN_TANK_EMPTY_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/oxygen_tank_empty.png");
    public static final ResourceLocation OXYGEN_TANK_FULL_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/overlay/oxygen_tank_full.png");

    /** PLANET BAR TEXTURES */
    public static final ResourceLocation MOON_PLANET_BAR_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/moon_planet_bar.png");
    public static final ResourceLocation MARS_PLANET_BAR_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/mars_planet_bar.png");
    public static final ResourceLocation MERCURY_PLANET_BAR_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/mercury_planet_bar.png");
    public static final ResourceLocation VENUS_PLANET_BAR_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/venus_planet_bar.png");
    public static final ResourceLocation GLACIO_PLANET_BAR_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/glacio_planet_bar.png");
    public static final ResourceLocation EARTH_PLANET_BAR_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/earth_planet_bar.png");
    public static final ResourceLocation ORBIT_PLANET_BAR_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/orbit_planet_bar.png");

    /** ROCKET TEXTURE */
    public static final ResourceLocation ROCKET_PLANET_BAR_TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/planet_bar/rocket.png");

    /** OVERLAY ENABLE OR DISABLE EVENT */
    @SubscribeEvent
    public static void overlayEnableOrDisable(RenderGameOverlayEvent.PostLayer event) {
        Player player = Minecraft.getInstance().player;
        Item chestItem = player.getItemBySlot(EquipmentSlot.CHEST).getItem();

        /** WARNING OVERLAY */
        if (player.getVehicle() instanceof LanderEntity && !player.getVehicle().isOnGround() && !player.isEyeInFluid(FluidTags.WATER)) {
                OverlayRegistry.enableOverlay(Overlays.WARNING, true);
        } else {
            OverlayRegistry.enableOverlay(Overlays.WARNING, false);
        }

        /** ROCKET TIMER */
        if (Methods.isRocket(player.getVehicle())) {
            OverlayRegistry.enableOverlay(Overlays.ROCKET_TIMER, true);
        } else {
            OverlayRegistry.enableOverlay(Overlays.ROCKET_TIMER, false);
        }

        /** OXYGEN TANK */
        if (chestItem == ItemsRegistry.SPACE_SUIT.get() || chestItem == ItemsRegistry.NETHERITE_SPACE_SUIT.get()) {
            OverlayRegistry.enableOverlay(Overlays.OXYGEN_TANK, true);
        } else {
            OverlayRegistry.enableOverlay(Overlays.OXYGEN_TANK, false);
        }

        /** ROCKET HEIGHT */
        if (Methods.isRocket(player.getVehicle()) || player.getVehicle() instanceof LanderEntity) {
            OverlayRegistry.enableOverlay(Overlays.ROCKET_HEIGHT, true);
        } else {
            OverlayRegistry.enableOverlay(Overlays.ROCKET_HEIGHT, false);
        }
    }

    /** FLASHING TICK */
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (check) {
                counter = counter + 0.10F;
                if (counter > 1) {
                    check = false;
                }
            } else {
                counter = counter - 0.10F;
                if (counter < 0.2) {
                    check = true;
                }
            }
        }
    }

    /** WARNING OVERLAY */
    public static IIngameOverlay WARNING = new IIngameOverlay() {
        @Override
        public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
            Entity vehicle = Minecraft.getInstance().player.getVehicle();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);

            /** FLASHING */
            RenderSystem.setShaderColor(counter, counter, counter, counter);

            /** WARNING IMAGE */
            RenderSystem.setShaderTexture(0, WARNING_TEXTURE);
            gui.blit(mStack, width / 2 - 58, 50, 0, 0, 116, 21, 116, 21);

            /** SPEED TEXT */
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            double speed = Math.round(100.0 * (vehicle).getDeltaMovement().y()) / 100.0;

            Component message = Component.translatable("message." + BeyondEarth.MODID + ".speed", speed);
            Minecraft.getInstance().font.draw(mStack, message, width / 2 - 29, 80 , -3407872);
        }
    };

    /** ROCKET TIMER OVERLAY */
    public static IIngameOverlay ROCKET_TIMER = new IIngameOverlay() {
        @Override
        public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
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
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 20 && timer < 40) {
                RenderSystem.setShaderTexture(0, TIMER_9_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 40 && timer < 60) {
                RenderSystem.setShaderTexture(0, TIMER_8_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 60 && timer < 80) {
                RenderSystem.setShaderTexture(0, TIMER_7_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 80 && timer < 100) {
                RenderSystem.setShaderTexture(0, TIMER_6_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 100 && timer < 120) {
                RenderSystem.setShaderTexture(0, TIMER_5_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 120 && timer < 140) {
                RenderSystem.setShaderTexture(0, TIMER_4_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 140 && timer < 160) {
                RenderSystem.setShaderTexture(0, TIMER_3_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 160 && timer < 180) {
                RenderSystem.setShaderTexture(0, TIMER_2_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
            else if (timer > 180 && timer < 200) {
                RenderSystem.setShaderTexture(0, TIMER_1_TEXTURE);
                gui.blit(mStack, timerWidth, timerHeight, 0, 0, 60, 38, 60, 38);
            }
        }
    };

    /** OXYGEN TANK OVERLAY */
    public static IIngameOverlay OXYGEN_TANK = new IIngameOverlay() {
        @Override
        public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
            Player player = Minecraft.getInstance().player;
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            Minecraft mc = Minecraft.getInstance();

            /** OXYGEN TANK IMAGE */
            OxygenStorage oxygenStorage = chest.getCapability(OxygenCapability.OXYGEN).orElse(null);
            if (oxygenStorage != null) {

                int x = 5;
                int y = 5;

                int textureWidth = 62;
                int textureHeight = 52;

                ScreenHelper.addTexture(mStack, x, y, textureWidth, textureHeight, OXYGEN_TANK_EMPTY_TEXTURE);
                ScreenHelper.drawVertical(mStack, x, y, textureWidth, textureHeight, oxygenStorage.getOxygenStored(), oxygenStorage.getMaxOxygenStored(), OXYGEN_TANK_FULL_TEXTURE);

                /** OXYGEN AMOUNT TEXT */
                //TODO CHECK AGAIN TEXT
                Font font = mc.font;
                Component text = Component.translatable("general." + BeyondEarth.MODID + ".oxygen").append(": ").withStyle(ChatFormatting.BLUE).append("\u00A77" + oxygenStorage.getOxygenStored() / 48 + "%");
                font.drawShadow(mStack, text, (x + (textureWidth - font.width(text)) / 2), y + textureHeight + 3, 0xFFFFFF);
            }
        }
    };

    /** ROCKET HEIGHT OVERLAY */
    public static IIngameOverlay ROCKET_HEIGHT = new IIngameOverlay() {
        @Override
        public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
            Player player = Minecraft.getInstance().player;
            Level level = Minecraft.getInstance().level;

            float yHeight = (float) player.getY() / 5.3F;

            if (yHeight < 0) {
                yHeight = 0;
            }
            else if (yHeight > 113) {
                yHeight = 113;
            }

            ResourceLocation planet;

            if (Methods.isWorld(level, LevelRegistry.MOON)) {
                planet = MOON_PLANET_BAR_TEXTURE;
            }
            else if (Methods.isWorld(level, LevelRegistry.MARS)) {
                planet = MARS_PLANET_BAR_TEXTURE;
            }
            else if (Methods.isWorld(level, LevelRegistry.MERCURY)) {
                planet = MERCURY_PLANET_BAR_TEXTURE;
            }
            else if (Methods.isWorld(level, LevelRegistry.VENUS)) {
                planet = VENUS_PLANET_BAR_TEXTURE;
            }
            else if (Methods.isWorld(level, LevelRegistry.GLACIO)) {
                planet = GLACIO_PLANET_BAR_TEXTURE;
            }
            else if (Methods.isOrbitWorld(level)) {
                planet = ORBIT_PLANET_BAR_TEXTURE;
            }
            else {
                planet = EARTH_PLANET_BAR_TEXTURE;
            }

            PlanetOverlayEvent event = new PlanetOverlayEvent(gui, planet, mStack, partialTicks, width, height);
            MinecraftForge.EVENT_BUS.post(event);

            if (planet != event.getResourceLocation()) {
                planet = event.getResourceLocation();
            }

            /** ROCKET BAR IMAGE */
            RenderSystem.setShaderTexture(0, planet);
            gui.blit(mStack, 0, (height / 2) - 128 / 2, 0, 0, 16, 128, 16, 128);

            /** ROCKET_Y IMAGE */
            RenderSystem.setShaderTexture(0, ROCKET_PLANET_BAR_TEXTURE);
            ScreenHelper.renderWithFloat.blit(mStack, 4, (height / 2) + (103 / 2) - yHeight, 0, 0, 8, 11, 8, 11);
        }
    };
}
