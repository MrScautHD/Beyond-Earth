package net.mrscauthd.beyond_earth.client.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.renderers.armors.JetSuitModel;
import net.mrscauthd.beyond_earth.client.renderers.armors.SpaceSuitModel;
import net.mrscauthd.beyond_earth.common.armors.ISpaceArmor;

@OnlyIn(Dist.CLIENT)
public class ClientMethods {

    public static boolean renderISpaceArmorArm(AbstractClientPlayer player, PlayerModel<AbstractClientPlayer> playerModel, PlayerRenderer renderer, PoseStack poseStack, MultiBufferSource bufferSource, int light, HumanoidArm arm) {
        ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        Item item = itemStack.getItem();

        if (item instanceof ISpaceArmor spaceArmorItem) {
            Model model = ForgeHooksClient.getArmorModel(player, itemStack, itemStack.getEquipmentSlot(), playerModel);

            if (model instanceof SpaceSuitModel.SpaceSuitP1<?> spaceSuitModel) {
                ClientMethods.renderArmWithProperties(poseStack, bufferSource, light, spaceArmorItem.getTexture(itemStack, player), player, playerModel, renderer, arm == HumanoidArm.RIGHT ? spaceSuitModel.rightArm : spaceSuitModel.leftArm);
                return true;
            }

            if (model instanceof JetSuitModel.JetSuitP1<?> jetSuitModel) {
                ClientMethods.renderArmWithProperties(poseStack, bufferSource, light, spaceArmorItem.getTexture(itemStack, player), player, playerModel, renderer, arm == HumanoidArm.RIGHT ? jetSuitModel.rightArm : jetSuitModel.leftArm);
                return true;
            }
        }

        return false;
    }

    public static void renderArmWithProperties(PoseStack poseStack, MultiBufferSource bufferSource, int light, ResourceLocation texture, AbstractClientPlayer player, PlayerModel<AbstractClientPlayer> playermodel, PlayerRenderer renderer, ModelPart arm) {
        renderer.setModelProperties(player);

        playermodel.attackTime = 0.0F;
        playermodel.crouching = false;
        playermodel.swimAmount = 0.0F;
        playermodel.setupAnim(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        arm.xRot = 0.0F;

        ItemStack item = player.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, 2));

        VertexConsumer vertex = ItemRenderer.getArmorFoilBuffer(bufferSource, RenderType.armorCutoutNoCull(texture), false, item.isEnchanted());
        arm.render(poseStack, vertex, light, OverlayTexture.NO_OVERLAY);
    }

    public static boolean isNotGuiSoundSource(SoundSource sound) {
        return sound == SoundSource.BLOCKS || sound == SoundSource.NEUTRAL || sound == SoundSource.RECORDS || sound == SoundSource.WEATHER || sound == SoundSource.HOSTILE || sound == SoundSource.PLAYERS || sound == SoundSource.AMBIENT;
    }

    public static void setBobView(PoseStack poseStack, float tick) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.getCameraEntity() instanceof Player player) {

            float f = player.walkDist - player.walkDistO;
            float f1 = -(player.walkDist + f * tick);
            float f2 = Mth.lerp(tick, 0.075F, -0.075F);
            poseStack.translate(Mth.sin(f1 * (float) Math.PI) * f2 * 0.5F, -Math.abs(Mth.cos(f1 * (float) Math.PI) * f2), 0.0D);
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(f1 * (float) Math.PI) * f2 * 3.0F));
            poseStack.mulPose(Axis.XP.rotationDegrees(Math.abs(Mth.cos(f1 * (float) Math.PI - 0.2F) * f2) * 5.0F));
        }
    }

    public static void sendPressKeyMessage() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        player.displayClientMessage(Component.translatable("message." + BeyondEarth.MODID + ".hold_key").append(" ").append(mc.options.keyJump.getKey().getDisplayName()), false);
    }
}
