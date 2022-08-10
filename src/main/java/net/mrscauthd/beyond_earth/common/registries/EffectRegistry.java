package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.effects.OxygenEffect;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BeyondEarth.MODID);

    /** EFFECTS */
    public static final RegistryObject<MobEffect> OXYGEN_EFFECT = EFFECTS.register("oxygen_bubble_effect", () -> new OxygenEffect(MobEffectCategory.BENEFICIAL,3035801));
}
