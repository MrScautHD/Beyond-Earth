package net.mrscauthd.beyond_earth.common.events.forge;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class FireworkRocketUseEvent extends PlayerEvent {

    private final FireworkRocketItem fireworkRocketItem;
    private final Level level;
    private final InteractionHand interactionHand;
    private final CallbackInfoReturnable<InteractionResultHolder<ItemStack>> callbackInfoReturnable;

    public FireworkRocketUseEvent(Player player, FireworkRocketItem fireworkRocketItem, Level level, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> callbackInfoReturnable) {
        super(player);
        this.fireworkRocketItem = fireworkRocketItem;
        this.level = level;
        this.interactionHand = interactionHand;
        this.callbackInfoReturnable = callbackInfoReturnable;
    }

    public FireworkRocketItem getFireworkRocketItem() {
        return fireworkRocketItem;
    }

    public Level getLevel() {
        return level;
    }

    public InteractionHand getInteractionHand() {
        return interactionHand;
    }

    public CallbackInfoReturnable<InteractionResultHolder<ItemStack>> getCallbackInfoReturnable() {
        return callbackInfoReturnable;
    }
}
