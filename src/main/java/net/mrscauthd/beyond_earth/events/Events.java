package net.mrscauthd.beyond_earth.events;

import net.minecraft.server.level.ServerPlayer;
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
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.entities.*;
import net.mrscauthd.beyond_earth.events.forge.EntityTickEvent;
import net.mrscauthd.beyond_earth.events.forge.ItemEntityTickEndEvent;
import net.mrscauthd.beyond_earth.events.forge.LivingEntityTickEndEvent;
import net.mrscauthd.beyond_earth.registries.LevelRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID)
public class Events {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level;

            /** LANDER ORBIT TELEPORT SYSTEM */
            if (player.getVehicle() instanceof LanderEntity) {
                Methods.landerTeleportOrbit(player, level);
            }

            /** DISABLE CLOSE PLANET GUI SYSTEM */
            Methods.openPlanetGui(player);

            /** PLAYER OXYGEN SYSTEM */
            OxygenSystem.OxygenSystem(player, level);

            /** DROP OFF HAND VEHICLE ITEM */
            Methods.dropRocket(player);

            /** JET SUIT MOVEMENT */
            JetSuitMovement.movement(player);

            /** DISABLE KICK BY FLYING IF IN PLANET GUI */
            if (player instanceof ServerPlayer) {
                Methods.disableFlyAntiCheat((ServerPlayer) player, player.getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_gui_open"));
            }
        }
    }

    @SubscribeEvent
    public static void livingEntityTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;

        /** ENTITY OXYGEN SYSTEM */
        Methods.entityOxygen(entity, level);

        /** VENUS RAIN SYSTEM */
        Methods.venusRain(entity, LevelRegistry.VENUS);

        /** PLANET FIRE SYSTEM */
        Methods.planetFire(entity, LevelRegistry.VENUS);
        Methods.planetFire(entity, LevelRegistry.MERCURY);
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
            Level level = event.world;

            if (LevelRegistry.WORLDS_WITHOUT_RAIN.contains(level.dimension())) {
                level.thunderLevel = 0;
                level.rainLevel = 0;
            } else if (Methods.isWorld(level, LevelRegistry.VENUS)) {
                level.thunderLevel = 0;
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
        if (event.getEntity() instanceof Player && event.getEntity().getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_gui_open")) {
            Player player = (Player) event.getEntity();

            player.closeContainer();
            Methods.cleanUpPlayerNBT(player);
            player.setNoGravity(false);
        }
    }

    @SubscribeEvent
    public static void livingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Level level = entity.level;

        if (Methods.isWorld(level, LevelRegistry.MOON)) {
            event.setDistance(event.getDistance() - 5.5F);
        }
        else if (Methods.isWorld(level, LevelRegistry.MARS)) {
            event.setDistance(event.getDistance() - 5.0F);
        }
        else if (Methods.isWorld(level, LevelRegistry.GLACIO)) {
            event.setDistance(event.getDistance() - 5.0F);
        }
        else if (Methods.isWorld(level, LevelRegistry.MERCURY)) {
            event.setDistance(event.getDistance() - 5.5F);
        }
        else if (Methods.isOrbitWorld(level)) {
            event.setDistance(event.getDistance() - 8.5F);
        }
    }
}
