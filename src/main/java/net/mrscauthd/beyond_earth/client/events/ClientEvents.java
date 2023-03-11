package net.mrscauthd.beyond_earth.client.events;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.sounds.ElytraOnPlayerSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.sounds.TickableJetSuitFlySound;
import net.mrscauthd.beyond_earth.client.util.ClientMethods;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.util.Methods;
import net.mrscauthd.beyond_earth.client.sounds.SpaceSoundSystem;
import net.mrscauthd.beyond_earth.client.sounds.TickableSpaceSoundSystem;
import net.mrscauthd.beyond_earth.client.events.forge.RenderHandItemEvent;
import net.mrscauthd.beyond_earth.client.events.forge.RenderViewEvent;
import net.mrscauthd.beyond_earth.client.events.forge.SetupLivingBipedAnimEvent;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void playSounds(PlaySoundEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        SoundInstance instance = event.getSound();

        if (instance == null || player == null) {
            return;
        }

        SoundSource source = instance.getSource();

        /** JET SUIT FLY SOUND */
        if (Methods.isLivingInJetSuit(player) && instance instanceof ElytraOnPlayerSoundInstance) {
            mc.getSoundManager().play(new TickableJetSuitFlySound((LocalPlayer) player));
        }

        /** SPACE SOUND SYSTEM */
        if (ClientMethods.isNotGuiSoundSource(source) && Methods.isSpaceLevelWithoutOxygen(player.level)) {
            if (instance instanceof TickableSoundInstance) {
                event.setSound(new TickableSpaceSoundSystem((TickableSoundInstance) instance));
            } else {
                event.setSound(new SpaceSoundSystem(event.getSound()));
            }
        }
    }

    @SubscribeEvent
    public static void itemRender(RenderHandItemEvent.Pre event) {
        if (!(event.getLivingEntity() instanceof Player)) {
            return;
        }

        LivingEntity entity = event.getLivingEntity();

        /** DISABLE TO HOLD ITEMS IN ROCKET */
        if (Methods.isRocket(entity.getVehicle())) {
            event.setCanceled(true);
        }

        /** DISABLE TO RENDER ITEMS IF YOU HAS IN ONE HAND A VEHICLE ITEM */
        if (event.getHandSide() == HumanoidArm.LEFT) {
            ItemStack itemStack = entity.getMainHandItem();

            if (Methods.isVehicleItem(itemStack)) {
                event.setCanceled(true);
            }
        } else {
            ItemStack itemStack = entity.getOffhandItem();

            if (Methods.isVehicleItem(itemStack)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void cameraPos(ViewportEvent.ComputeCameraAngles event) {
        Entity riddingEntity = event.getCamera().getEntity().getVehicle();

        /** MOVE CAMERA BACK IF IN LANDER OR IN A ROCKET */
        if (Methods.isRocket(riddingEntity) || riddingEntity instanceof LanderEntity) {
            CameraType cameraType = Minecraft.getInstance().options.getCameraType();

            if (cameraType.equals(CameraType.THIRD_PERSON_FRONT) || cameraType.equals(CameraType.THIRD_PERSON_BACK)) {
                event.getCamera().move(-event.getCamera().getMaxZoom(12d), 0d, 0);
            }
        }
    }

    @SubscribeEvent
    public static void bobViewer(RenderViewEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Entity riddingEntity = mc.cameraEntity.getVehicle();

        /** ROCKET START CAMERA WIGGLING */
        if (Methods.isRocket(riddingEntity)) {
            CameraType cameraType = mc.options.getCameraType();

            if (cameraType.equals(CameraType.THIRD_PERSON_FRONT) || cameraType.equals(CameraType.THIRD_PERSON_BACK)) {
                if (riddingEntity.getEntityData().get(IRocketEntity.ROCKET_START)) {
                    ClientMethods.setBobView(event.getPoseStack(), event.getTick());
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void renderPlayerArm(RenderArmEvent event) {
        AbstractClientPlayer player = event.getPlayer();
        PlayerRenderer renderer = (PlayerRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(player);
        PlayerModel<AbstractClientPlayer> playerModel = renderer.getModel();

        ItemStack itemStack1 = player.getOffhandItem();
        ItemStack itemStack2 = player.getMainHandItem();

        /** CANCEL ARM RENDERER IF HOLDING A ROCKET */
        if (Methods.isVehicleItem(itemStack1) || Methods.isVehicleItem(itemStack2)) {
            event.setCanceled(true);
            return;
        }

        /** RENDER SPACE SUIT ARMS */
        event.setCanceled(ClientMethods.renderISpaceArmorArm(player, playerModel, renderer, event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight(), event.getArm()));
    }

    @SubscribeEvent
    public static void render(RenderPlayerEvent event) {
        if (event.getEntity().getVehicle() instanceof LanderEntity) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void setupPlayerAngles(SetupLivingBipedAnimEvent.Post event) {
        if (!(event.getLivingEntity() instanceof Player player)) {
            return;
        }

        HumanoidModel<?> model = event.getModel();

        /** PLAYER STAY IN ROCKET ANIMATION */
        if (Methods.isRocket(player.getVehicle())) {
            model.rightArm.xRot = -0.07f;
            model.leftArm.xRot = -0.07f;

            model.rightLeg.xRot = 0F;
            model.leftLeg.xRot = 0F;

            model.rightLeg.yRot = 0F;
            model.leftLeg.yRot = 0F;

            model.rightLeg.zRot = 0F;
            model.leftLeg.zRot = 0F;
        } else {
            /** ROCKET HOLDING ANIMATION */
            ItemStack itemStack1 = player.getMainHandItem();
            ItemStack itemStack2 = player.getOffhandItem();

            if (Methods.isVehicleItem(itemStack1) || Methods.isVehicleItem(itemStack2)) {
                model.rightArm.xRot = 10F;
                model.leftArm.xRot = 10F;

                model.rightArm.yRot = 0F;
                model.leftArm.yRot = 0F;

                model.rightArm.zRot = 0F;
                model.leftArm.zRot = 0F;
            }
        }
    }
}
