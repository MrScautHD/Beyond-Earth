package net.mrscauthd.beyond_earth.events;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.events.forge.EntityGravityEvent;

public class EntityGravity {

    /** GRAVITIES */
    public static final float MOON_GRAVITY = 0.03F;
    public static final float MARS_GRAVITY = 0.04F;
    public static final float MERCURY_GRAVITY = 0.03F;
    public static final float GLACIO_GRAVITY = 0.04F;
    public static final float ORBIT_GRAVITY = 0.02F;

    public static void gravity(LivingEntity entity, Level level) {
        if (Methods.isWorld(level, Methods.moon)) {
            gravitySystem(entity, MOON_GRAVITY);
        }
        else if (Methods.isWorld(level, Methods.mars)) {
            gravitySystem(entity, MARS_GRAVITY);
        }
        else if (Methods.isWorld(level, Methods.mercury)) {
            gravitySystem(entity, MERCURY_GRAVITY);
        }
        else if (Methods.isWorld(level, Methods.glacio)) {
            gravitySystem(entity, GLACIO_GRAVITY);
        }
        else if (Methods.isOrbitWorld(level)) {
            gravitySystem(entity, ORBIT_GRAVITY);
        }
    }

    public static void gravitySystem(LivingEntity entity, float gravity) {
    	if (!getCondition(entity)) {
            return;
        }

        if (MinecraftForge.EVENT_BUS.post(new EntityGravityEvent(entity, gravity))) {
            return;
        }

        entity.setDeltaMovement(entity.getDeltaMovement().x, entity.getDeltaMovement().y / 0.98 + 0.08 - gravity, entity.getDeltaMovement().z);
	}

    /** MAIN GRAVITY CHECK */
    private static boolean getCondition(LivingEntity entity) {
        if (entity instanceof Player) {
            return getPlayerEntityCondition((Player) entity);
        }

        return getLivingEntityCondition(entity);
    }

    /** LIVING ENTITY GRAVITY CHECK */
    private static boolean getPlayerEntityCondition(Player player) {
        return getLivingEntityCondition(player) && !player.getAbilities().flying;
     }

    /** LIVING ENTITY GRAVITY CHECK */
    private static boolean getLivingEntityCondition(LivingEntity entity) {
        return getEntityCondition(entity) && !entity.isFallFlying() && !entity.hasEffect(MobEffects.SLOW_FALLING) && !entity.hasEffect(MobEffects.LEVITATION);
    }

    /** ENTITY GRAVITY CHECK */
    private static boolean getEntityCondition(Entity entity) {
        return !entity.isInWater() && !entity.isInLava() && !entity.isNoGravity() && !Methods.isVehicle(entity);
    }
}