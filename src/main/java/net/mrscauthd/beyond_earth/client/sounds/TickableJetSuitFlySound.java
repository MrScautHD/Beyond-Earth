package net.mrscauthd.beyond_earth.client.sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.common.keybinds.KeyVariables;
import net.mrscauthd.beyond_earth.common.registries.SoundRegistry;

@OnlyIn(Dist.CLIENT)
public class TickableJetSuitFlySound extends AbstractTickableSoundInstance {
    private final LocalPlayer player;
    private int time;

    public TickableJetSuitFlySound(LocalPlayer player) {
        super(SoundRegistry.JET_SUIT_FLY_SOUND.get(), SoundSource.PLAYERS, SoundInstance.createUnseededRandom());
        this.player = player;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.0F;
    }

    @Override
    public boolean canPlaySound() {
        return !this.player.isSilent();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    public void tick() {
        this.time++;
        if (!this.player.isRemoved() && (this.time <= 20 || this.player.isFallFlying())) {
            this.x = this.player.getX();
            this.y = this.player.getY();
            this.z = this.player.getZ();

            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;

            if (KeyVariables.isHoldingUp(player) && this.player.isFallFlying()) {
                this.volume = Math.min(1.0F, this.volume + 0.05F);
            } else {
                this.volume = Math.max(0.0F, this.volume - 0.05F);
            }
        } else {
            this.stop();
        }
    }
}