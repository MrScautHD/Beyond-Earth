package net.mrscauthd.beyond_earth.common.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.events.forge.LivingGravityEvent;

public class EntityGravity {

    public static final String TAG = BeyondEarth.MODID + ":space_gravity";

    public static void setGravities(LivingEntity entity, Level level) {
        Attribute attribute = ForgeMod.ENTITY_GRAVITY.get();
        AttributeInstance attributeInstance = entity.getAttribute(attribute);

        /** SET TO DEFAULT */
        if (entity.getPersistentData().getBoolean(TAG)) {
            setGravity(entity, attributeInstance, attribute.getDefaultValue(), false);
        }

        /** SET GRAVITIES */
        if (!entity.getPersistentData().getBoolean(TAG)) {
            float entityGravity = Planets.getEntityGravityForLocation(level);
            if (entityGravity != -1) {
                setGravity(entity, attributeInstance, entityGravity, true);
            } else {
                MinecraftForge.EVENT_BUS.post(new LivingGravityEvent(entity, attribute, attributeInstance));
            }
        }
    }

    /** SET GRAVITY */
    public static void setGravity(LivingEntity entity, AttributeInstance attributeInstance, double gravity,
            boolean condition) {
        attributeInstance.setBaseValue(gravity);
        entity.getPersistentData().putBoolean(TAG, condition);
    }
}
