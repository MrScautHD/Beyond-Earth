package net.mrscauthd.beyond_earth.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.common.events.forge.LivingSprintingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingSprinting {

    @Inject(at = @At(value = "HEAD"), method = "setSprinting", cancellable = true)
    private void setSprinting(boolean p_21284_, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) ((Object) this);

        if (MinecraftForge.EVENT_BUS.post(new LivingSprintingEvent(livingEntity, p_21284_))) {
            ci.cancel();
        }
    }
}
