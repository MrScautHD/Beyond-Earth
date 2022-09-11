package net.mrscauthd.beyond_earth.client.sounds;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.registries.SoundRegistry;

@OnlyIn(Dist.CLIENT)
public class TickableLandingSound extends AbstractTickableSoundInstance {

    private final LocalPlayer player;

    public TickableLandingSound(LocalPlayer player) {
        super(SoundRegistry.LANDING_SOUND.get(), SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.player = player;
        this.looping = true;
        this.volume = 0.5F;
    }

    @Override
    public void tick() {
    }
}
