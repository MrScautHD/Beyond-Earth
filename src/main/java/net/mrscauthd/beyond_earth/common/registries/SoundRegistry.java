package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;

public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BeyondEarth.MODID);

    /** SOUNDS */
    public static final RegistryObject<SoundEvent> ROCKET_SOUND = SOUNDS.register("rocket_fly",() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BeyondEarth.MODID, "rocket_fly")));
    public static final RegistryObject<SoundEvent> BOOST_SOUND = SOUNDS.register("boost",() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BeyondEarth.MODID, "boost")));
    public static final RegistryObject<SoundEvent> BEEP_SOUND = SOUNDS.register("beep",() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BeyondEarth.MODID, "beep")));
    public static final RegistryObject<SoundEvent> SONIC_BOOM_SOUND = SOUNDS.register("sonic_boom",() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BeyondEarth.MODID, "sonic_boom")));
}
