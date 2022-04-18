package net.mrscauthd.beyond_earth.events;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.events.forgeevents.ItemGravityEvent;

public class ItemGravity {

    /** GRAVITIES */
    public static final float MOON_GRAVITY = 0.05F;
    public static final float MARS_GRAVITY = 0.06F;
    public static final float MERCURY_GRAVITY = 0.05F;
    public static final float GLACIO_GRAVITY = 0.06F;
    public static final float ORBIT_GRAVITY = 0.05F;

    public static void gravity(ItemEntity itemEntity, Level level) {
        if (Methods.isWorld(level, Methods.moon)) {
            gravitySystem(itemEntity, MOON_GRAVITY);
        }
        else if (Methods.isWorld(level, Methods.mars)) {
            gravitySystem(itemEntity, MARS_GRAVITY);
        }
        else if (Methods.isWorld(level, Methods.mercury)) {
            gravitySystem(itemEntity, MERCURY_GRAVITY);
        }
        else if (Methods.isWorld(level, Methods.glacio)) {
            gravitySystem(itemEntity, GLACIO_GRAVITY);
        }
        else if (Methods.isOrbitWorld(level)) {
            gravitySystem(itemEntity, ORBIT_GRAVITY);
        }
    }

    public static void gravitySystem(ItemEntity entity, double gravity) {
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
        return !entity.isInWater() && !entity.isInLava() && !entity.isNoGravity();
    }
}
