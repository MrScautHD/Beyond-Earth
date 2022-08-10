package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.damagesource.DamageSource;

public class DamageSourceRegistry {
    public static final DamageSource DAMAGE_SOURCE_OXYGEN = new DamageSource("oxygen").bypassArmor();
    public static final DamageSource DAMAGE_SOURCE_ACID_RAIN = new DamageSource("venus.acid").bypassArmor();
}
