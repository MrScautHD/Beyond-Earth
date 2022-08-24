package net.mrscauthd.beyond_earth.common.util;

import com.mojang.datafixers.util.Pair;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.network.NetworkHooks;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.armors.JetSuit;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.entities.VehicleEntity;
import net.mrscauthd.beyond_earth.common.events.forge.LivingSetFireInHotPlanetEvent;
import net.mrscauthd.beyond_earth.common.events.forge.LivingSetVenusRainEvent;
import net.mrscauthd.beyond_earth.common.events.forge.ResetPlanetSelectionMenuNeededNbtEvent;
import net.mrscauthd.beyond_earth.common.events.forge.TeleportAndCreateLanderEvent;
import net.mrscauthd.beyond_earth.common.registries.*;
import net.mrscauthd.beyond_earth.common.menus.planetselection.PlanetSelectionMenu;
import net.mrscauthd.beyond_earth.common.items.VehicleItem;

import java.util.function.Function;

public class Methods {
    public static final ResourceLocation SPACE_STATION = new ResourceLocation(BeyondEarth.MODID, "space_station");

    public static Entity teleportTo(Entity entity, ResourceKey<Level> levelKey, double yPos) {
        if (!isLevel(entity.level, levelKey)) {
            if (entity.canChangeDimensions()) {

                if (entity.getServer() == null) {
                    return entity;
                }

                ServerLevel nextLevel = entity.getServer().getLevel(levelKey);

                if (nextLevel == null) {
                    BeyondEarth.LOGGER.error(levelKey.registry() + " not existing!");
                    return entity;
                }

                return entity.changeDimension(nextLevel, new ITeleporter() {

                    @Override
                    public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
                        Vec3 pos = new Vec3(entity.position().x, yPos, entity.position().z);

                        return new PortalInfo(pos, Vec3.ZERO, entity.getYRot(), entity.getXRot());
                    }

                    @Override
                    public boolean isVanilla() {
                        return false;
                    }

                    @Override
                    public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld) {
                        return false;
                    }
                });
            }
        } else {
            entity.teleportTo(entity.getX(), yPos, entity.getZ());
            return entity;
        }

        return entity;
    }

    public static boolean isLivingInNetheriteSpaceSuit(LivingEntity entity) {
        if (!isLivingInArmor(entity, 3, ItemsRegistry.NETHERITE_OXYGEN_MASK.get())) return false;
        if (!isLivingInArmor(entity, 2, ItemsRegistry.NETHERITE_SPACE_SUIT.get())) return false;
        if (!isLivingInArmor(entity, 1, ItemsRegistry.NETHERITE_SPACE_PANTS.get())) return false;
        if (!isLivingInArmor(entity, 0, ItemsRegistry.NETHERITE_SPACE_BOOTS.get())) return false;

        return true;
    }

    public static boolean isLivingInJetSuit(LivingEntity entity) {
        if (!isLivingInArmor(entity, 3, ItemsRegistry.JET_SUIT_OXYGEN_MASK.get())) return false;
        if (!isLivingInArmor(entity, 2, ItemsRegistry.JET_SUIT.get())) return false;
        if (!isLivingInArmor(entity, 1, ItemsRegistry.JET_SUIT_PANTS.get())) return false;
        if (!isLivingInArmor(entity, 0, ItemsRegistry.JET_SUIT_BOOTS.get())) return false;

        return true;
    }

    public static boolean isLivingInSpaceSuit(LivingEntity entity) {
        if (!isLivingInArmor(entity, 3, ItemsRegistry.OXYGEN_MASK.get())) return false;
        if (!isLivingInArmor(entity, 2, ItemsRegistry.SPACE_SUIT.get())) return false;
        if (!isLivingInArmor(entity, 1, ItemsRegistry.SPACE_PANTS.get())) return false;
        if (!isLivingInArmor(entity, 0, ItemsRegistry.SPACE_BOOTS.get())) return false;

        return true;
    }

    public static boolean isLivingInAnySpaceSuits(LivingEntity entity) {
        boolean item3 = isLivingInArmor(entity, 3, ItemsRegistry.OXYGEN_MASK.get());
        boolean item3b = isLivingInArmor(entity, 3, ItemsRegistry.NETHERITE_OXYGEN_MASK.get());
        boolean item3c = isLivingInArmor(entity, 3, ItemsRegistry.JET_SUIT_OXYGEN_MASK.get());

        if (!item3 && !item3b && !item3c) {
            return false;
        }

        boolean item2 = isLivingInArmor(entity, 2, ItemsRegistry.SPACE_SUIT.get());
        boolean item2b = isLivingInArmor(entity, 2, ItemsRegistry.NETHERITE_SPACE_SUIT.get());
        boolean item2c = isLivingInArmor(entity, 2, ItemsRegistry.JET_SUIT.get());

        if (!item2 && !item2b && !item2c) {
            return false;
        }

        boolean item1 = isLivingInArmor(entity, 1, ItemsRegistry.SPACE_PANTS.get());
        boolean item1b = isLivingInArmor(entity, 1, ItemsRegistry.NETHERITE_SPACE_PANTS.get());
        boolean item1c = isLivingInArmor(entity, 1, ItemsRegistry.JET_SUIT_PANTS.get());

        if (!item1 && !item1b && !item1c) {
            return false;
        }

        boolean item0 = isLivingInArmor(entity, 0, ItemsRegistry.SPACE_BOOTS.get());
        boolean item0b = isLivingInArmor(entity, 0, ItemsRegistry.NETHERITE_SPACE_BOOTS.get());
        boolean item0c = isLivingInArmor(entity, 0, ItemsRegistry.JET_SUIT_BOOTS.get());

        if (!item0 && !item0b && !item0c) {
            return false;
        }

        return true;
    }

    public static boolean isLivingInArmor(LivingEntity entity, int armorSlot, Item item) {
        return entity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, armorSlot)).getItem() == item;
    }

    public static boolean isSpaceLevel(Level level) {
        return LevelRegistry.SPACE_LEVELS.contains(level.dimension());
    }

    public static boolean isSpaceLevelWithoutOxygen(Level level) {
        return LevelRegistry.LEVELS_WITHOUT_OXYGEN.contains(level.dimension());
    }

    public static boolean isOrbitLevel(Level level) {
        return LevelRegistry.ORBIT_LEVELS.contains(level.dimension());
    }

    public static boolean isLevel(Level level, ResourceKey<Level> loc) {
        return level.dimension() == loc;
    }

    public static void hurtLivingWithOxygenSource(LivingEntity entity) {
        entity.hurt(DamageSourceRegistry.DAMAGE_SOURCE_OXYGEN, 1.0F);
    }

    public static void hurtLivingWithAcidRainSource(LivingEntity entity) {
        entity.hurt(DamageSourceRegistry.DAMAGE_SOURCE_ACID_RAIN, 1.0F);
    }

    public static boolean isRocket(Entity entity) {
        return entity instanceof IRocketEntity;
    }

    public static boolean isVehicle(Entity entity) {
        return entity instanceof VehicleEntity;
    }

    public static boolean isVehicleItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof VehicleItem;
    }

    public static void boostWithJetSuit(Player player, double boost, boolean flashParticle) {
        Vec3 vec31 = player.getLookAngle();

        if (Methods.isLivingInJetSuit(player) && player.isFallFlying()) {
            Vec3 vec32 = player.getDeltaMovement();
            player.setDeltaMovement(vec32.add(vec31.x * 0.1D + (vec31.x * boost - vec32.x) * 0.5D, vec31.y * 0.1D + (vec31.y * boost - vec32.y) * 0.5D, vec31.z * 0.1D + (vec31.z * boost - vec32.z) * 0.5D));

            if (flashParticle) {
                Vec3 vec33 = player.getLookAngle().scale(6.5D);

                if (player.level instanceof ServerLevel) {
                    for (ServerPlayer p : ((ServerLevel) player.level).getServer().getPlayerList().getPlayers()) {
                        ((ServerLevel) player.level).sendParticles(p, ParticleTypes.FLASH, true, player.getX() - vec33.x, player.getY() - vec33.y, player.getZ() - vec33.z, 1, 0, 0, 0, 0.001);
                    }
                }
            }
        }
    }

    public static void setJetSuitHoverPose(Player player) {
        if (Methods.isLivingInJetSuit(player)) {
            ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);

            if (itemStack.getOrCreateTag().getInt(JetSuit.Suit.TAG_MODE) == JetSuit.Suit.ModeType.HOVER.getMode()) {
                if (player.isShiftKeyDown() && !player.isOnGround() && !player.hasEffect(MobEffects.SLOW_FALLING) && !player.getAbilities().flying && !player.isSleeping() && !player.isSwimming() && !player.isAutoSpinAttack() && !player.isSpectator() && !player.isPassenger()) {
                    player.setPose(Pose.STANDING);
                }
            }
        }
    }

    public static void dropOffHandVehicle(LivingEntity livingEntity) {
        ItemStack itemStack1 = livingEntity.getMainHandItem();
        ItemStack itemStack2 = livingEntity.getOffhandItem();

        if (isVehicleItem(itemStack1) && isVehicleItem(itemStack2)) {

            /** DROP ITEM */
            if (!livingEntity.level.isClientSide) {
                double d0 = livingEntity.getEyeY() - 0.3;
                ItemEntity itementity = new ItemEntity(livingEntity.level, livingEntity.getX(), d0, livingEntity.getZ(), itemStack2.copy());
                itementity.setPickUpDelay(0);
                livingEntity.level.addFreshEntity(itementity);
            }

            /** DELETE ITEM */
            itemStack2.shrink(1);
        }
    }

    //TODO REWORK
    /** IF A ENTITY SHOULD NOT GET ON FIRE ADD IT TO TAG "venus_fire" */
    public static void planetFire(LivingEntity entity, ResourceKey<Level> planet) {
        Level level = entity.level;

        if (!isLevel(level, planet)) {
            return;
        }

        if ((entity instanceof Mob || entity instanceof Player) && (isLivingInNetheriteSpaceSuit(entity) || entity.hasEffect(MobEffects.FIRE_RESISTANCE) || entity.fireImmune())) {
            return;
        }

        if (entity instanceof Player) {
            Player player = (Player) entity;

            if (player.isSpectator() || player.getAbilities().instabuild) {
                return;
            }
        }

        if (MinecraftForge.EVENT_BUS.post(new LivingSetFireInHotPlanetEvent(entity, planet))) {
            return;
        }

        if (entity.getType().is(TagRegistry.ENTITY_PLANET_FIRE_TAG)) {
            return;
        }

        entity.setSecondsOnFire(10);
    }

    //TODO REWORK
    /** IF A ENTITY SHOULD NOT GET DAMAGE FROM ACID RAIN ADD IT TO TAG "venus_rain" */
    public static void venusRain(LivingEntity entity, ResourceKey<Level> planet) {
        if (!isLevel(entity.level, planet)) {
            return;
        }

        if (entity.isPassenger() && (isRocket(entity.getVehicle()) || entity.getVehicle() instanceof LanderEntity)) {
            return;
        }

        if (entity instanceof Player) {
            Player player = (Player) entity;

            if (player.isSpectator() || player.getAbilities().instabuild) {
                return;
            }
        }

        if (MinecraftForge.EVENT_BUS.post(new LivingSetVenusRainEvent(entity, planet))) {
            return;
        }

        if (entity.getType().is(TagRegistry.ENTITY_VENUS_RAIN_TAG)) {
            return;
        }

        if (entity.level.getLevelData().isRaining() && entity.level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Math.floor(entity.getX()), (int) Math.floor(entity.getZ())) <= Math.floor(entity.getY()) + 1) {
            if (!entity.level.isClientSide) {
                hurtLivingWithAcidRainSource(entity);
            }
        }
    }

    //TODO REWORK
    /** IF A ENTITY SHOULD GET DAMAGE BECAUSE NO OXYGEN IN SPACE ADD IT TO TAG "oxygen" */
    public static void entityOxygen(LivingEntity entity, Level level) {
        if (entity instanceof Player) {
            return;
        }

        if (Config.ENTITY_OXYGEN_SYSTEM.get() && isSpaceLevelWithoutOxygen(level) && entity.getType().is(TagRegistry.ENTITY_OXYGEN_TAG)) {

            if (!entity.hasEffect(EffectRegistry.OXYGEN_EFFECT.get())) {

                entity.getPersistentData().putDouble(BeyondEarth.MODID + ":oxygen_tick", entity.getPersistentData().getDouble(BeyondEarth.MODID + ":oxygen_tick") + 1);

                if (entity.getPersistentData().getDouble(BeyondEarth.MODID + ":oxygen_tick") > 15) {

                    if (!level.isClientSide) {
                        hurtLivingWithOxygenSource(entity);
                    }

                    entity.getPersistentData().putDouble(BeyondEarth.MODID + ":oxygen_tick", 0);
                }
            }
        }

        //Out of Space
        if (Config.ENTITY_OXYGEN_SYSTEM.get() && entity.hasEffect(EffectRegistry.OXYGEN_EFFECT.get())) {
            entity.setAirSupply(300);
        }
    }

    public static void setEntityRotation(Entity vehicle, float rotation) {
        vehicle.setYRot(vehicle.getYRot() + rotation);
        vehicle.setYBodyRot(vehicle.getYRot());
        vehicle.yRotO = vehicle.getYRot();
    }

    public static void teleportWithEntityTo(Entity entity, Entity vehicle, ResourceKey<Level> levelKey, int yPos) {
        Vec3 vec3 = entity.position();
        entity.dismountTo(vec3.x, vec3.y, vec3.z);

        Entity newEntity = Methods.teleportTo(entity, levelKey, yPos);
        Entity newVehicle = Methods.teleportTo(vehicle, levelKey, yPos);

        newEntity.startRiding(newVehicle);
    }

    public static void createLanderAndTeleportTo(ServerPlayer serverPlayer, ResourceKey<Level> levelKey, int yPos, boolean placeSpaceStation) {
        Methods.teleportTo(serverPlayer, levelKey, yPos);

        Level newLevel = serverPlayer.level;

        if (!newLevel.isClientSide) {
            LanderEntity landerEntity = new LanderEntity(EntityRegistry.LANDER.get(), newLevel);
            landerEntity.moveTo(serverPlayer.position());

            CompoundTag playerTag = serverPlayer.getPersistentData();

            /** SET ITEMS IN SLOT */
            for (int i = 0; i <= 1; i++) {
                CompoundTag compoundTag = playerTag.getList(BeyondEarth.MODID + ":rocket_item_list", Tag.TAG_COMPOUND).getCompound(i);

                if (!compoundTag.isEmpty()) {
                    landerEntity.getInventory().setStackInSlot(i, ItemStack.of(compoundTag));
                }
            }

            newLevel.addFreshEntity(landerEntity);

            if (placeSpaceStation) {
                placeSpaceStation(serverPlayer, (ServerLevel) newLevel);
            }

            /** CALL START RIDE LANDER EVENT */
            MinecraftForge.EVENT_BUS.post(new TeleportAndCreateLanderEvent(landerEntity, serverPlayer));

            resetPlanetSelectionMenuNeededNbt(serverPlayer);

            serverPlayer.startRiding(landerEntity);
        }
    }

    public static void placeSpaceStation(Player player, ServerLevel serverLevel) {
        StructureTemplate structureTemplate = serverLevel.getStructureManager().getOrCreate(SPACE_STATION);
        BlockPos pos = new BlockPos(player.getX() - (structureTemplate.getSize().getX() / 2), 100, player.getZ() - (structureTemplate.getSize().getZ() / 2));

        structureTemplate.placeInWorld(serverLevel, pos, pos, new StructurePlaceSettings(), serverLevel.random, 2);
    }

    public static void resetPlanetSelectionMenuNeededNbt(Player player) {
        player.getPersistentData().putBoolean(BeyondEarth.MODID + ":planet_selection_menu_open", false);
        player.getPersistentData().putInt(BeyondEarth.MODID + ":rocket_tier", 0);
        player.getPersistentData().put(BeyondEarth.MODID + ":rocket_item_list", new CompoundTag());

        MinecraftForge.EVENT_BUS.post(new ResetPlanetSelectionMenuNeededNbtEvent(player));
    }

    public static void openPlanetGui(Player player) {
        if (!(player.containerMenu instanceof PlanetSelectionMenu.GuiContainer) && player.getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_menu_open")) {
            if (player instanceof ServerPlayer) {
                ServerPlayer serverPlayer = (ServerPlayer) player;

                /** OPEN MENU */
                NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Planet Selection");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                        packetBuffer.writeInt(player.getPersistentData().getInt(BeyondEarth.MODID + ":rocket_tier"));
                        return new PlanetSelectionMenu.GuiContainer(id, inventory, packetBuffer);
                    }
                }, buf -> {
                    buf.writeInt(player.getPersistentData().getInt(BeyondEarth.MODID + ":rocket_tier"));
                });
            }
        }
    }

    public static void entityFallWithLanderToPlanet(Entity entity, Level level) {
        if (entity.getVehicle().getY() < 1) {

            for (Pair<ResourceKey<Level>, ResourceKey<Level>> levelPair : LevelRegistry.LEVELS_WITH_ORBIT) {
                if (Methods.isLevel(level, levelPair.getSecond())) {
                    teleportWithEntityTo(entity, entity.getVehicle(), levelPair.getFirst(), 700);
                }
            }
        }
    }

    public static void entityFallToPlanet(Entity entity, Level level) {
        if (entity.getY() < 1) {

            for (Pair<ResourceKey<Level>, ResourceKey<Level>> levelPair : LevelRegistry.LEVELS_WITH_ORBIT) {
                if (Methods.isLevel(level, levelPair.getSecond())) {
                    Methods.teleportTo(entity, levelPair.getFirst(), 550);
                }
            }
        }
    }

    public static void disableFlyAntiCheat(Player player, boolean condition) {
        if (player instanceof ServerPlayer) {
            if (condition) {
                ((ServerPlayer) player).connection.aboveGroundTickCount = 0;
            }
        }
    }

    public static void stopSound(ServerPlayer serverPlayer, ResourceLocation sound, SoundSource source) {
        ClientboundStopSoundPacket stopSoundS2CPacket = new ClientboundStopSoundPacket(sound, source);
        serverPlayer.connection.send(stopSoundS2CPacket);
    }

    public static void sendVehicleHasNoFuelMessage(Player player) {
        if (!player.level.isClientSide) {
            player.displayClientMessage(Component.translatable("message." + BeyondEarth.MODID + ".no_fuel"), false);
        }
    }
}
