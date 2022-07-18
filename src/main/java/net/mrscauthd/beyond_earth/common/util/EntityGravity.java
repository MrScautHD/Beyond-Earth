package net.mrscauthd.beyond_earth.common.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.events.forge.LivingGravityEvent;
import net.mrscauthd.beyond_earth.common.events.forge.PlaceRocketEvent;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;

public class EntityGravity {

    public static final String TAG = BeyondEarth.MODID + ":space_gravity";

    /** GRAVITIES */
    public static final float MOON_GRAVITY = 0.02F;
    public static final float MARS_GRAVITY = 0.05F;
    public static final float MERCURY_GRAVITY = 0.02F;
    public static final float GLACIO_GRAVITY = 0.03F;
    public static final float ORBIT_GRAVITY = 0.01F;

    public static void setGravities(LivingEntity entity, Level level) {
        Attribute attribute = ForgeMod.ENTITY_GRAVITY.get();
        AttributeInstance attributeInstance = entity.getAttribute(attribute);

        /** SET TO DEFAULT */
        if (entity.getPersistentData().getBoolean(TAG)) {
            setGravity(entity, attributeInstance, attribute.getDefaultValue(), false);
        }

        /** SET GRAVITIES */
        if (!entity.getPersistentData().getBoolean(TAG)) {
            if (Methods.isLevel(level, LevelRegistry.MOON)) {
                setGravity(entity, attributeInstance, MOON_GRAVITY, true);
            } else if (Methods.isLevel(level, LevelRegistry.MARS)) {
                setGravity(entity, attributeInstance, MARS_GRAVITY, true);
            } else if (Methods.isLevel(level, LevelRegistry.MERCURY)) {
                setGravity(entity, attributeInstance, MERCURY_GRAVITY, true);
            } else if (Methods.isLevel(level, LevelRegistry.GLACIO)) {
                setGravity(entity, attributeInstance, GLACIO_GRAVITY, true);
            } else if (Methods.isOrbitLevel(level)) {
                setGravity(entity, attributeInstance, ORBIT_GRAVITY, true);
            } else {
                MinecraftForge.EVENT_BUS.post(new LivingGravityEvent(entity, level, attribute, attributeInstance));
            }
        }
    }

    /** SET GRAVITY */
    public static void setGravity(LivingEntity entity, AttributeInstance attributeInstance, double gravity, boolean condition) {
        attributeInstance.setBaseValue(gravity);
        entity.getPersistentData().putBoolean(TAG, condition);
    }
}
