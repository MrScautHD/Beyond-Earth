package net.mrscauthd.beyond_earth.client.events;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
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
import net.mrscauthd.beyond_earth.client.util.ClientMethods;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.util.Methods;
import net.mrscauthd.beyond_earth.client.util.SpaceSoundSystem;
import net.mrscauthd.beyond_earth.client.util.TickableSpaceSoundSystem;
import net.mrscauthd.beyond_earth.client.events.forge.RenderHandItemEvent;
import net.mrscauthd.beyond_earth.client.events.forge.RenderViewEvent;
import net.mrscauthd.beyond_earth.client.events.forge.SetupLivingBipedAnimEvent;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void spaceSounds(PlaySoundEvent event) {
        if (event.getSound() == null) {
            return;
        }

        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.level != null && ClientMethods.isSoundSource(event.getSound().getSource()) && Methods.isSpaceLevelWithoutOxygen(Minecraft.getInstance().player.level)) {

            if (!(event.getSound() instanceof TickableSoundInstance)) {
                event.setSound(new SpaceSoundSystem(event.getSound()));

            } else if (event.getSound() instanceof TickableSoundInstance) {
                event.setSound(new TickableSpaceSoundSystem((TickableSoundInstance) event.getSound()));
            }
        }
    }

    @SubscribeEvent
    public static void itemRender(RenderHandItemEvent.Pre event) {
        if (!(event.getLivingEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getLivingEntity();

        /** DISABLE TO HOLD ITEMS IN ROCKET */
        if (Methods.isRocket(player.getVehicle())) {
            event.setCanceled(true);
        }

        /** DISABLE TO HOLD ITEMS IF YOU HAS IN ONE HAND A VEHICLE ITEM */
        if (event.getHandSide() == HumanoidArm.LEFT) {
            ItemStack itemStack = player.getMainHandItem();

            if (Methods.isVehicleItem(itemStack)) {
                event.setCanceled(true);
            }
        } else {
            ItemStack itemStack = player.getOffhandItem();

            if (Methods.isVehicleItem(itemStack)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void cameraPos(ViewportEvent.ComputeCameraAngles event) {
        Entity ridding = event.getCamera().getEntity().getVehicle();

        if (Methods.isRocket(ridding) || ridding instanceof LanderEntity) {
            CameraType cameraType = Minecraft.getInstance().options.getCameraType();

            if (cameraType.equals(CameraType.THIRD_PERSON_FRONT) || cameraType.equals(CameraType.THIRD_PERSON_BACK)) {
                event.getCamera().move(-event.getCamera().getMaxZoom(12d), 0d, 0);
            }
        }
    }

    @SubscribeEvent
    public static void bobViewer(RenderViewEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Entity ridding = mc.player.getVehicle();

        if (Methods.isRocket(ridding)) {
            CameraType cameraType = mc.options.getCameraType();

            if (cameraType.equals(CameraType.THIRD_PERSON_FRONT) || cameraType.equals(CameraType.THIRD_PERSON_BACK)) {
                event.setCanceled(true);

                if (ridding.getEntityData().get(IRocketEntity.ROCKET_START)) {
                    ClientMethods.setBobView(event.getPoseStack(), event.getTick());
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

        if (Methods.isVehicleItem(itemStack1) || Methods.isVehicleItem(itemStack2)) {
            event.setCanceled(true);
            return;
        }

        event.setCanceled(ClientMethods.renderArmWithProperties(player, event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight(), playerModel, renderer, event.getArm() == HumanoidArm.RIGHT));
    }

    @SubscribeEvent
    public static void render(RenderPlayerEvent event) {
        if (event.getEntity().getVehicle() instanceof LanderEntity) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void setupPlayerAngles(SetupLivingBipedAnimEvent.Post event) {
        if (!(event.getLivingEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getLivingEntity();
        HumanoidModel model = event.getModel();

        // Player Rocket Sit Rotations, Player Hold Rotation
        if (Methods.isRocket(player.getVehicle())) {
            model.rightLeg.xRot = 0F;
            model.leftLeg.xRot = 0F;
            model.rightLeg.yRot = 0F;
            model.leftLeg.yRot = 0F;
            model.rightLeg.zRot = 0F;
            model.leftLeg.zRot = 0F;

            // Arms
            model.rightArm.xRot = -0.07f;
            model.leftArm.xRot = -0.07f;
        }
        else if (!Methods.isRocket(player.getVehicle())) {
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
