package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class TryStartFallFlyingEvent extends PlayerEvent {

    private final CallbackInfoReturnable<Boolean> callbackInfoReturnable;

    public TryStartFallFlyingEvent(Player entity, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        super(entity);
        this.callbackInfoReturnable = callbackInfoReturnable;
    }

    public CallbackInfoReturnable<Boolean> getCallbackInfoReturnable() {
        return callbackInfoReturnable;
    }
}
