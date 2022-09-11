package net.mrscauthd.beyond_earth.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.client.events.forge.RenderHandItemEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandLayer.class)
public class RenderHandItem {

    @Inject(at = @At(value = "HEAD"), method = "renderArmWithItem", cancellable = true)
    private void setRotationAnglesPre(LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117191_, CallbackInfo info) {

        if (MinecraftForge.EVENT_BUS.post(new RenderHandItemEvent.Pre(livingEntity, itemStack, transformType, humanoidArm, poseStack, multiBufferSource, p_117191_))) {
            info.cancel();
        }
    }

    @Inject(at = @At(value = "RETURN"), method = "renderArmWithItem")
    private void setRotationAnglesPost(LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_117191_, CallbackInfo info) {

        MinecraftForge.EVENT_BUS.post(new RenderHandItemEvent.Post(livingEntity, itemStack, transformType, humanoidArm, poseStack, multiBufferSource, p_117191_));
    }
}