package net.mrscauthd.beyond_earth.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

public class SoundsRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BeyondEarthMod.MODID);

    /** SOUNDS */
    public static final RegistryObject<SoundEvent> ROCKET_SOUND = SOUNDS.register("rocket_fly",() -> new SoundEvent(new ResourceLocation(BeyondEarthMod.MODID, "rocket_fly")));
}
