package net.mrscauthd.beyond_earth.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.common.events.forge.FireworkRocketUseEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketItemUse {

    @Inject(at = @At(value = "HEAD"), method = "use", cancellable = true)
    private void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        FireworkRocketItem fireworkRocketItem = (FireworkRocketItem) ((Object) this);

        MinecraftForge.EVENT_BUS.post(new FireworkRocketUseEvent(player, fireworkRocketItem, level, interactionHand, cir));
    }
}