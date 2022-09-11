package net.mrscauthd.beyond_earth.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.common.events.forge.TryStartFallFlyingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerTryStartFallFlying {

    @Inject(at = @At(value = "HEAD"), method = "tryToStartFallFlying", cancellable = true)
    private void use(CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player) ((Object) this);

        MinecraftForge.EVENT_BUS.post(new TryStartFallFlyingEvent(player, cir));
    }
}