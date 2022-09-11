package net.mrscauthd.beyond_earth.client.sounds;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class TickableSpaceSoundSystem implements TickableSoundInstance {
    private final TickableSoundInstance instance;

    public TickableSpaceSoundSystem(TickableSoundInstance delegate) {
        this.instance = delegate;
    }

    @Override
    public ResourceLocation getLocation() {
        return instance.getLocation();
    }

    @Nullable
    @Override
    public WeighedSoundEvents resolve(SoundManager p_119841_) {
        return instance.resolve(p_119841_);
    }

    @Override
    public Sound getSound() {
        return instance.getSound();
    }

    @Override
    public SoundSource getSource() {
        return instance.getSource();
    }

    @Override
    public boolean isLooping() {
        return instance.isLooping();
    }

    @Override
    public boolean isRelative() {
        return instance.isRelative();
    }

    @Override
    public int getDelay() {
        return instance.getDelay();
    }

    @Override
    public float getVolume() {
        return instance.getVolume() * 0.3F;
    }

    @Override
    public float getPitch() {
        return instance.getPitch() / 10;
    }

    @Override
    public double getX() {
        return instance.getX();
    }

    @Override
    public double getY() {
        return instance.getY();
    }

    @Override
    public double getZ() {
        return instance.getZ();
    }

    @Override
    public Attenuation getAttenuation() {
        return instance.getAttenuation();
    }

    @Override
    public boolean isStopped() {
        return instance.isStopped();
    }

    @Override
    public void tick() {
        instance.tick();
    }
}
