package net.mrscauthd.beyond_earth.client.sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.keybinds.KeyVariables;
import net.mrscauthd.beyond_earth.common.registries.SoundRegistry;

@OnlyIn(Dist.CLIENT)
public class TickableBeepSound extends AbstractTickableSoundInstance {

    private final LanderEntity landerEntity;

    public TickableBeepSound(LanderEntity landerEntity) {
        super(SoundRegistry.BEEP_SOUND.get(), SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.landerEntity = landerEntity;
        this.looping = true;
        this.pitch = 1.0F;
        this.volume = 0.0F;
        this.x = this.landerEntity.getX();
        this.y = this.landerEntity.getY();
        this.z = this.landerEntity.getZ();
    }

    @Override
    public boolean canPlaySound() {
        return !this.landerEntity.isSilent();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    @Override
    public void tick() {
        if (this.landerEntity.isRemoved()) {
            this.stop();
        } else {
            this.x = this.landerEntity.getX();
            this.y = this.landerEntity.getY();
            this.z = this.landerEntity.getZ();

            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;

            if (this.landerEntity.isOnGround() || this.landerEntity.isEyeInFluid(FluidTags.WATER) || !this.landerEntity.hasPassenger(player) || KeyVariables.isHoldingJump(player)) {
                this.volume = Math.max(0, this.volume - 0.05F);
            } else {
                this.volume = Math.min(0.5F, this.volume + 0.05F);
            }
        }
    }
}
