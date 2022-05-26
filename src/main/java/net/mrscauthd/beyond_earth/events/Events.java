package net.mrscauthd.beyond_earth.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.entities.*;
import net.mrscauthd.beyond_earth.events.forge.EntityTickEvent;
import net.mrscauthd.beyond_earth.events.forge.ItemEntityTickEndEvent;
import net.mrscauthd.beyond_earth.events.forge.LivingEntityTickEndEvent;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID)
public class Events {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level world = player.level;

            /** LANDER ORBIT TELEPORT SYSTEM */
            if (player.getVehicle() instanceof LanderEntity) {
                Methods.landerTeleportOrbit(player, world);
            }

            /** DISABLE CLOSE PLANET GUI SYSTEM */
            Methods.openPlanetGui(player);

            /** ENTITY OXYGEN SYSTEM */
            OxygenSystem.OxygenSystem(player);

            /** DROP OFF HAND VEHICLE ITEM */
            Methods.dropRocket(player);
        }
    }

    @SubscribeEvent
    public static void livingEntityTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;

        /** ENTITY OXYGEN SYSTEM */
        Methods.entityOxygen(entity, level);

        /** VENUS RAIN SYSTEM */
        Methods.venusRain(entity, Methods.venus);

        /** PLANET FIRE SYSTEM */
        Methods.planetFire(entity, Methods.venus);
        Methods.planetFire(entity, Methods.mercury);
    }

    @SubscribeEvent
    public static void livingEntityEndTick(LivingEntityTickEndEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;

        /** ENTITY GRAVITY SYSTEM */
        EntityGravity.gravity(entity, level);
    }

    @SubscribeEvent
    public static void itemEntityEndTick(ItemEntityTickEndEvent event) {
        ItemEntity entity = event.getEntityItem();
        Level level = entity.level;

        /** ITEM ENTITY GRAVITY SYSTEM */
        ItemGravity.gravity(entity, level);
    }

    @SubscribeEvent
    public static void entityTick(EntityTickEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level;

        /** ORBIT TELEPORT SYSTEM */
        if (entity.getY() < 1 && !(entity.getVehicle() instanceof LanderEntity)) {

            if ((entity instanceof LanderEntity) && entity.isVehicle()) {
                return;
            }

            Methods.entityFallToPlanet(level, entity);
        }
    }

    @SubscribeEvent
    public static void worldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Level world = event.world;

            if (Methods.worldsWithoutRain.contains(world.dimension())) {
                world.thunderLevel = 0;
                world.rainLevel = 0;
            } else if (Methods.isWorld(world, Methods.venus)) {
                world.thunderLevel = 0;
            }
        }
    }

    @SubscribeEvent
    public static void livingEntityAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (!event.getSource().isFire()) {
            return;
        }

        Player entity = (Player) event.getEntity();

        if (!Methods.netheriteSpaceSuitCheck(entity)) {
            return;
        }

        entity.setRemainingFireTicks(0);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void projectileImpact(ProjectileImpactEvent event) {
        if (event.getRayTraceResult().getType() != HitResult.Type.ENTITY) {
            return;
        }

        Entity entity = ((EntityHitResult) event.getRayTraceResult()).getEntity();

        if (Methods.isVehicle(entity)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void livingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player && event.getEntity().getPersistentData().getBoolean(BeyondEarthMod.MODID + ":planet_selection_gui_open")) {
            Player player = (Player) event.getEntity();

            player.closeContainer();
            Methods.cleanUpPlayerNBT(player);
            player.setNoGravity(false);
            player.getAbilities().mayfly = false;
        }
    }

    @SubscribeEvent
    public static void livingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;

        if (Methods.isWorld(level, Methods.moon)) {
            event.setDistance(event.getDistance() - 5.5F);
        } else if (Methods.isWorld(level, Methods.mars)) {
            event.setDistance(event.getDistance() - 5.0F);
        } else if (Methods.isWorld(level, Methods.glacio)) {
            event.setDistance(event.getDistance() - 5.0F);
        } else if (Methods.isWorld(level, Methods.mercury)) {
            event.setDistance(event.getDistance() - 5.5F);
        } else if (Methods.isOrbitWorld(level)) {
            event.setDistance(event.getDistance() - 8.5F);
        }
    }
}
