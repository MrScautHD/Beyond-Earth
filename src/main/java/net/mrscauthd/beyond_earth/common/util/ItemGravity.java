package net.mrscauthd.beyond_earth.common.util;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.common.events.forge.ItemGravityEvent;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;

public class ItemGravity {

    /** GRAVITIES */
    public static final float MOON_GRAVITY = 0.05F;
    public static final float MARS_GRAVITY = 0.06F;
    public static final float MERCURY_GRAVITY = 0.05F;
    public static final float GLACIO_GRAVITY = 0.06F;
    public static final float ORBIT_GRAVITY = 0.05F;

    public static void setGravities(ItemEntity itemEntity, Level level) {
        if (Methods.isLevel(level, LevelRegistry.MOON)) {
            setGravity(itemEntity, MOON_GRAVITY);
        }
        else if (Methods.isLevel(level, LevelRegistry.MARS)) {
            setGravity(itemEntity, MARS_GRAVITY);
        }
        else if (Methods.isLevel(level, LevelRegistry.MERCURY)) {
            setGravity(itemEntity, MERCURY_GRAVITY);
        }
        else if (Methods.isLevel(level, LevelRegistry.GLACIO)) {
            setGravity(itemEntity, GLACIO_GRAVITY);
        }
        else if (Methods.isOrbitLevel(level)) {
            setGravity(itemEntity, ORBIT_GRAVITY);
        }
    }

    public static void setGravity(ItemEntity entity, double gravity) {
        if (!getCondition(entity)) {
            return;
        }

        if (MinecraftForge.EVENT_BUS.post(new ItemGravityEvent(entity, gravity))) {
            return;
        }

        entity.setDeltaMovement(entity.getDeltaMovement().x, entity.getDeltaMovement().y / 0.98 + 0.08 - gravity, entity.getDeltaMovement().z);
    }

    /** GRAVITY CHECK */
    private static boolean getCondition(ItemEntity entity) {
        return !entity.isInFluidType() && !entity.isInLava() && !entity.isNoGravity();
    }
}
