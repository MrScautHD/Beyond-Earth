package net.mrscauthd.beyond_earth.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.client.events.forge.RenderViewEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class RenderBobView {

    @Inject(at = @At(value = "HEAD"), method = "bobView", cancellable = true)
    private void bobView(PoseStack poseStack, float tick, CallbackInfo info) {

        if (MinecraftForge.EVENT_BUS.post(new RenderViewEvent(poseStack, tick))) {
            info.cancel();
        }
    }
}
