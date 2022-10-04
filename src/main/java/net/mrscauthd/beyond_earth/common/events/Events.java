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
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.config.data.PlanetData;
import net.mrscauthd.beyond_earth.common.config.data.PlanetData.PlanetDataHandler;
import net.mrscauthd.beyond_earth.common.config.data.PlanetData.StarEntry;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.events.forge.*;
import net.mrscauthd.beyond_earth.common.registries.SoundRegistry;
import net.mrscauthd.beyond_earth.common.util.*;
import net.mrscauthd.beyond_earth.common.util.Planets.Planet;
import net.mrscauthd.beyond_earth.common.util.Planets.StarSystem;
import net.mrscauthd.beyond_earth.common.registries.LevelRegistry;
import net.mrscauthd.beyond_earth.common.registries.NetworkRegistry;

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
            Methods.disableFlyAntiCheat(player,
                    player.getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_menu_open"));
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

            if (Planets.LEVELS_WITHOUT_RAIN.contains(level.dimension())) {
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
        if (entity instanceof Player
                && entity.getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_menu_open")) {
            Player player = (Player) entity;

            player.closeContainer();
            Methods.resetPlanetSelectionMenuNeededNbt(player);
            player.setNoGravity(false);
        }

        /** JET SUIT EXPLODE */
        if (Methods.isLivingInJetSuit(entity) && entity.isFallFlying()
                && (event.getSource() == DamageSource.FLY_INTO_WALL)) {
            if (!entity.level.isClientSide) {
                entity.level.explode(null, entity.getX(), entity.getY(), entity.getZ(), 10, true,
                        Explosion.BlockInteraction.BREAK);
            }
        }
    }

    @SubscribeEvent
    public static void livingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level;
        event.setDistance(event.getDistance() - Planets.getFallModifier(level));
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
            event.getCallbackInfoReturnable()
                    .setReturnValue(InteractionResultHolder.pass(player.getItemInHand(event.getInteractionHand())));
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
                player.level.playSound(null, player, SoundRegistry.SONIC_BOOM_SOUND.get(), SoundSource.NEUTRAL, 1.0F,
                        1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void livingSprinting(LivingSprintingEvent event) {
        LivingEntity entity = event.getEntity();

        /** JET SUIT SONIC BOOM SOUND */
        if (Methods.isLivingInJetSuit(entity) && event.getSprinting() && entity.isFallFlying()) {
            entity.level.playSound(null, entity, SoundRegistry.SONIC_BOOM_SOUND.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    public static void onDataSync(OnDatapackSyncEvent event) {
        PlanetData data = new PlanetData();
        Planets.ORDERED_STARS.forEach(s -> data.stars.add(new StarEntry(s)));
        PlanetDataHandler holder = new PlanetDataHandler();
        holder.data = data;
        NetworkRegistry.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> event.getPlayer()), holder);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    /**
     * Here we register default planets. We are set to HIGHEST so that we fire
     * first, and then addons can adjust things.
     * 
     * @param event
     */
    public static void onPlanetsGenerate(PlanetRegisterEvent.Generate event) {
        Planets.registerPlanet(Level.OVERWORLD, LevelRegistry.EARTH_ORBIT);
        Planets.registerPlanet(LevelRegistry.MOON, LevelRegistry.MOON_ORBIT, 0.05f, 0.02f);
        Planets.registerPlanet(LevelRegistry.MARS, LevelRegistry.MARS_ORBIT, 0.06f, 0.05f);
        Planets.registerPlanet(LevelRegistry.MERCURY, LevelRegistry.MERCURY_ORBIT, 0.05f, 0.02f);
        Planets.registerPlanet(LevelRegistry.VENUS, LevelRegistry.VENUS_ORBIT);
        Planets.registerPlanet(LevelRegistry.GLACIO, LevelRegistry.GLACIO_ORBIT, 0.05f, 0.03f);

        Planets.registerFallModifier(LevelRegistry.MOON, 5.5f);
        Planets.registerFallModifier(LevelRegistry.MARS, 5.0f);
        Planets.registerFallModifier(LevelRegistry.MERCURY, 5.5f);
        Planets.registerFallModifier(LevelRegistry.GLACIO, 5.0f);

        Planets.registerPlanetBar(LevelRegistry.MOON, Planets.MOON_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MARS, Planets.MARS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.MERCURY, Planets.MERCURY_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.VENUS, Planets.VENUS_PLANET_BAR);
        Planets.registerPlanetBar(LevelRegistry.GLACIO, Planets.GLACIO_PLANET_BAR);

        StarSystem sol = new StarSystem();
        sol.name = "sun";
        sol.texture = Planets.SUN_TEXTURE;
        Planet mercury = Planets.BY_DIMENSION.get(LevelRegistry.MERCURY);
        mercury.orbitRadius = 0.39f;
        mercury.mass = 0.055f;
        mercury.texture = Planets.MERCURY_TEXTURE;
        mercury.rotation = 270;
        mercury.tier = 3;
        mercury.g = 0.38f;
        mercury.temperature = 430;
        mercury.orbitColour = new int[] { 179, 49, 44 };
        Planet venus = Planets.BY_DIMENSION.get(LevelRegistry.VENUS);
        venus.orbitRadius = 0.72f;
        venus.mass = 0.81f;
        venus.texture = Planets.VENUS_TEXTURE;
        venus.rotation = 180;
        venus.tier = 3;
        venus.g = 0.904f;
        venus.temperature = 482;
        venus.orbitColour = new int[] { 235, 136, 68 };
        Planet earth = Planets.BY_DIMENSION.get(LevelRegistry.EARTH);
        earth.texture = Planets.EARTH_TEXTURE;
        earth.rotation = 90;
        earth.tier = 1;
        earth.hasOxygen = true;
        earth.spaceLevel = false;
        earth.hasRain = true;
        earth.orbitColour = new int[] { 53, 163, 79 };
        Planet mars = Planets.BY_DIMENSION.get(LevelRegistry.MARS);
        mars.orbitRadius = 1.52f;
        mars.mass = 0.107f;
        mars.texture = Planets.MARS_TEXTURE;
        mars.tier = 2;
        mars.g = 0.3794f;
        mars.temperature = -63;
        mars.hasRain = true;
        mars.orbitColour = new int[] { 37, 49, 146 };

        Planet moon = Planets.BY_DIMENSION.get(LevelRegistry.MOON);
        moon.g = 0.1654f;
        moon.temperature = -160;
        earth.moons.add(moon);
        sol.planets.add(mercury);
        sol.planets.add(venus);
        sol.planets.add(earth);
        sol.planets.add(mars);
        sol.register();

        StarSystem proxima_centauri = new StarSystem();
        proxima_centauri.name = "proxima_centauri";
        proxima_centauri.location[0] = 4.25f;
        proxima_centauri.mass = 0.122f;
        Planet glacio = Planets.BY_DIMENSION.get(LevelRegistry.GLACIO);
        glacio.texture = Planets.GLACIO_TEXTURE;
        glacio.mass = 0.08f;
        glacio.orbitRadius = 0.39f;
        glacio.rotation = 180;
        glacio.tier = 4;
        glacio.g = 0.3794f;
        glacio.temperature = -20;
        glacio.hasRain = true;
        glacio.orbitColour = new int[] { 37, 49, 146 };
        proxima_centauri.planets.add(glacio);
        proxima_centauri.register();
    }
}
