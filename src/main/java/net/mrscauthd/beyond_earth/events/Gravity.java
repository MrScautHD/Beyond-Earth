package net.mrscauthd.beyond_earth.events;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.events.forgeevents.LivingGravityEvent;

public class Gravity {
    public static void Gravity(LivingEntity entity, GravityType type, Level world) {
        if (Methods.isWorld(world, Methods.overworld)) return;

        double moon = 0.03D;
        double mars = 0.04D;
        double mercury = 0.03D;
        double venus = 0.04D;
        double orbit = 0.02D;

        if (Methods.isWorld(world, Methods.moon)) {
            gravityMath(type, entity, moon, -2.5f);
        }
        else if (Methods.isWorld(world, Methods.mars)) {
            gravityMath(type, entity, mars, -2.0f);
        }
        else if (Methods.isWorld(world, Methods.glacio)) {
            gravityMath(type, entity, mars, -2.0f);
        }
        else if (Methods.isWorld(world, Methods.mercury)) {
            gravityMath(type, entity, mercury, -2.5f);
        }
        else if (Methods.isWorld(world, Methods.venus)) {
            gravityMath(type, entity, venus, -2.0f);
        }
        else if (Methods.isOrbitWorld(world)) {
            gravityMath(type, entity, orbit, -2.5f);
        }
    }

    public enum GravityType {
        PLAYER, LIVING
    }

    public static boolean playerGravityCheck(Player player) {
        return !player.getAbilities().flying && !player.isFallFlying() && !player.isInWater() && !player.isInLava() && !player.isNoGravity() && !player.hasEffect(MobEffects.SLOW_FALLING) && !player.hasEffect(MobEffects.LEVITATION);
    }

    public static boolean livingGravityCheck(LivingEntity entity) {
        return !(entity instanceof Player) && !entity.isFallFlying() && !entity.isInWater() && !entity.isInLava() && !entity.isNoGravity() && !Methods.isVehicle(entity) && !entity.hasEffect(MobEffects.SLOW_FALLING) && !entity.hasEffect(MobEffects.LEVITATION);
    }

    public static void gravityMath(GravityType type, LivingEntity entity, double gravity, float fallDistance) {
    	if (!checkType(type, entity)) {
    		return;
    	}

    	if (MinecraftForge.EVENT_BUS.post(new LivingGravityEvent(entity, gravity, fallDistance, type))) {
    		return;
    	}

    	entity.setDeltaMovement(entity.getDeltaMovement().x(), entity.getDeltaMovement().y() / 0.98D + 0.08D - gravity, entity.getDeltaMovement().z());
    	fallDamage(entity, fallDistance);
	}

    public static boolean checkType(GravityType type, LivingEntity entity) {
    	return (type == GravityType.PLAYER && playerGravityCheck((Player) entity)) || (type == GravityType.LIVING && livingGravityCheck(entity));
    }

    public static void fallDamage(LivingEntity entity, float fallDistance) {
        if ((float) entity.getDeltaMovement().y() < -0.1F) {
            entity.fallDistance = (float) entity.getDeltaMovement().y() * fallDistance;
        }
    }
}