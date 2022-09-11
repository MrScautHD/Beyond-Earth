package net.mrscauthd.beyond_earth.common.events;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.events.forge.*;
import net.mrscauthd.beyond_earth.common.registries.SoundRegistry;
import net.mrscauthd.beyond_earth.common.util.*;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID)
public class Events {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level;

            /** OPEN AUTOMATIC PLANET GUI */
            Methods.openPlanetGui(player);

            /** PLAYER OXYGEN SYSTEM */
            OxygenSystem.oxygenSystem(player, level);

            /** JET SUIT HOVER POSE */
            Methods.setJetSuitHoverPose(player);

            /** DISABLE KICK BY FLYING IF IN PLANET GUI */
            Methods.disableFlyAntiCheat(player, player.getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_menu_open"));
        }
    }

    @SubscribeEvent
    public static void livingEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Level level = livingEntity.level;

        /** DROP OFF HAND VEHICLE ITEM */
        Methods.dropOffHandVehicle(livingEntity);

        /** ENTITY OXYGEN SYSTEM */
        Methods.entityOxygen(livingEntity, level);

        /** VENUS RAIN SYSTEM */
        Methods.venusRain(livingEntity, LevelRegistry.VENUS);

        /** PLANET FIRE SYSTEM */
        Methods.planetFire(livingEntity, LevelRegistry.VENUS);
        Methods.planetFire(livingEntity, LevelRegistry.MERCURY);
    }

    @SubscribeEvent
    public static void itemEntityEndTick(ItemEntityTickAtEndEvent event) {
        ItemEntity itemEntity = event.getEntity();
        Level level = itemEntity.level;

        /** ITEM ENTITY GRAVITY SYSTEM */
        ItemGravity.setGravities(itemEntity, level);
    }

    @SubscribeEvent
    public static void entityTick(EntityTickEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level;

        /** LANDER ORBIT TELEPORT SYSTEM */
        if (entity.getVehicle() instanceof LanderEntity) {
            Methods.entityFallWithLanderToPlanet(entity, level);
        }

        /** ORBIT TELEPORT SYSTEM */
        if (!(entity.getVehicle() instanceof LanderEntity)) {
            if ((entity instanceof LanderEntity) && entity.isVehicle()) {
                return;
            }

            Methods.entityFallToPlanet(entity, level);
        }
    }

    @SubscribeEvent
    public static void worldTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Level level = event.level;

            if (LevelRegistry.LEVELS_WITHOUT_RAIN.contains(level.dimension())) {
                level.thunderLevel = 0;
                level.rainLevel = 0;
            }
        }
    }

    @SubscribeEvent
    public static void livingEntityAttack(LivingAttackEvent event) {
        if (!event.getSource().isFire()) {
            return;
        }

        LivingEntity entity = event.getEntity();

        if (!Methods.isLivingInNetheriteSpaceSuit(entity) && !Methods.isLivingInJetSuit(entity)) {
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

        LivingEntity entity = event.getEntity();

        /** RESET PLANET GUI PARAMETERS */
        if (entity instanceof Player && entity.getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_menu_open")) {
            Player player = (Player) entity;

            player.closeContainer();
            Methods.resetPlanetSelectionMenuNeededNbt(player);
            player.setNoGravity(false);
        }

        /** JET SUIT EXPLODE */
        if (Methods.isLivingInJetSuit(entity) && entity.isFallFlying() && (event.getSource() == DamageSource.FLY_INTO_WALL)) {
            if (!entity.level.isClientSide) {
                entity.level.explode(null, entity.getX(), entity.getY(), entity.getZ(), 10, true, Explosion.BlockInteraction.BREAK);
            }
        }
    }

    @SubscribeEvent
    public static void livingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level;

        /** PLANET FALL DISTANCE */
        if (Methods.isLevel(level, LevelRegistry.MOON)) {
            event.setDistance(event.getDistance() - 5.5F);
        }
        else if (Methods.isLevel(level, LevelRegistry.MARS)) {
            event.setDistance(event.getDistance() - 5.0F);
        }
        else if (Methods.isLevel(level, LevelRegistry.GLACIO)) {
            event.setDistance(event.getDistance() - 5.0F);
        }
        else if (Methods.isLevel(level, LevelRegistry.MERCURY)) {
            event.setDistance(event.getDistance() - 5.5F);
        }
        else if (Methods.isOrbitLevel(level)) {
            event.setDistance(event.getDistance() - 8.5F);
        }
    }

    @SubscribeEvent
    public static void entityJoinLevel(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();

        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;

            /** ENTITY GRAVITY SYSTEM */
            EntityGravity.setGravities(livingEntity, level);
        }
    }

    @SubscribeEvent
    public static void fireworkRocketUse(FireworkRocketUseEvent event) {
        Player player = event.getEntity();

        /** CANCEL BOOST BY FLYING JET SUIT */
        if (Methods.isLivingInJetSuit(player) && player.isFallFlying()) {
            event.getCallbackInfoReturnable().setReturnValue(InteractionResultHolder.pass(player.getItemInHand(event.getInteractionHand())));
        }
    }

    @SubscribeEvent
    public static void tryStartFallFlying(TryStartFallFlyingEvent event) {
        Player player = event.getEntity();

        /** CANCEL FALL FLYING IF PRESS AGAIN SPACE BY JET SUIT */
        if (Methods.isLivingInJetSuit(player) && player.isFallFlying()) {
            if (!player.level.isClientSide) {
                event.getCallbackInfoReturnable().setReturnValue(false);
            } else {
                event.getCallbackInfoReturnable().setReturnValue(true);
            }
        }

        /** JET SUIT SONIC BOOM SOUND */
        if (Methods.isLivingInJetSuit(player) && !player.isFallFlying()) {
            if (player.isSprinting()) {
                player.level.playSound(null, player, SoundRegistry.SONIC_BOOM_SOUND.get(), SoundSource.NEUTRAL, 0.8F, 1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void livingSprinting(LivingSprintingEvent event) {
        LivingEntity entity = event.getEntity();

        /** JET SUIT SONIC BOOM SOUND */
        if (Methods.isLivingInJetSuit(entity) && event.getSprinting() && entity.isFallFlying()) {
            entity.level.playSound(null, entity, SoundRegistry.SONIC_BOOM_SOUND.get(), SoundSource.NEUTRAL, 0.8F, 1.0F);
        }
    }
}
