package net.mrscauthd.beyond_earth.common.events;

import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.mrscauthd.beyond_earth.common.events.forge.FireworkRocketUseEvent;
import net.mrscauthd.beyond_earth.common.events.forge.TryStartFallFlyingEvent;
import net.mrscauthd.beyond_earth.common.util.*;
import net.mrscauthd.beyond_earth.common.events.forge.EntityTickEvent;
import net.mrscauthd.beyond_earth.common.events.forge.ItemEntityTickAtEndEvent;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID)
public class Events {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level;

            /** DISABLE CLOSE PLANET GUI SYSTEM */
            Methods.openPlanetGui(player);

            /** PLAYER OXYGEN SYSTEM */
            OxygenSystem.OxygenSystem(player, level);

            /** JET SUIT FAST BOOST */
            if (player.isSprinting()) {
                Methods.boostWithJetSuit(player, 1.3, true);
            }

            /** JET SUIT SLOW BOOST */
            if (player.zza > 0 && !player.isSprinting()) {
                Methods.boostWithJetSuit(player, 0.9, false);
            }

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
            } else if (Methods.isLevel(level, LevelRegistry.VENUS)) {
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

        if (!Methods.isLivingInNetheriteSpaceSuit(entity)) {
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
        if (event.getEntity() instanceof Player && event.getEntity().getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_menu_open")) {
            Player player = (Player) event.getEntity();

            player.closeContainer();
            Methods.resetPlanetSelectionMenuNeededNbt(player);
            player.setNoGravity(false);
        }
    }

    @SubscribeEvent
    public static void livingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level;

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
    }
}
