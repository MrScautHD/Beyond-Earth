package net.mrscauthd.beyond_earth.common.util;

import java.util.function.Function;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
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
import net.minecraft.tags.TagKey;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.armors.JetSuit;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.ChunkOxygen;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenUtil;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.IVehicleEntity;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.events.forge.LivingSetFireInHotPlanetEvent;
import net.mrscauthd.beyond_earth.common.events.forge.LivingSetVenusRainEvent;
import net.mrscauthd.beyond_earth.common.events.forge.ResetPlanetSelectionMenuNeededNbtEvent;
import net.mrscauthd.beyond_earth.common.events.forge.TeleportAndCreateLanderEvent;
import net.mrscauthd.beyond_earth.common.items.VehicleItem;
import net.mrscauthd.beyond_earth.common.menus.planetselection.PlanetSelectionMenu;
import net.mrscauthd.beyond_earth.common.registries.DamageSourceRegistry;
import net.mrscauthd.beyond_earth.common.registries.EntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagRegistry;
import net.mrscauthd.beyond_earth.common.util.Planets.Planet;

public class Methods {
    public static final ResourceLocation SPACE_STATION = new ResourceLocation(BeyondEarth.MODID, "space_station");
    public static final TagKey<Item> SPACE_SUIT_PART = TagKey.create(Keys.ITEMS, new ResourceLocation(BeyondEarth.MODID, "space_suit"));

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

    public static boolean isLivingInSpaceSuit(LivingEntity entity) {
        if (!isLivingInArmor(entity, EquipmentSlot.HEAD, ItemsRegistry.SPACE_HELMET.get())) return false;
        if (!isLivingInArmor(entity, EquipmentSlot.CHEST, ItemsRegistry.SPACE_SUIT.get())) return false;
        if (!isLivingInArmor(entity, EquipmentSlot.LEGS, ItemsRegistry.SPACE_PANTS.get())) return false;
        return isLivingInArmor(entity, EquipmentSlot.FEET, ItemsRegistry.SPACE_BOOTS.get());
    }

    public static boolean isLivingInNetheriteSpaceSuit(LivingEntity entity) {
        if (!isLivingInArmor(entity, EquipmentSlot.HEAD, ItemsRegistry.NETHERITE_SPACE_HELMET.get())) return false;
        if (!isLivingInArmor(entity, EquipmentSlot.CHEST, ItemsRegistry.NETHERITE_SPACE_SUIT.get())) return false;
        if (!isLivingInArmor(entity, EquipmentSlot.LEGS, ItemsRegistry.NETHERITE_SPACE_PANTS.get())) return false;
        return isLivingInArmor(entity, EquipmentSlot.FEET, ItemsRegistry.NETHERITE_SPACE_BOOTS.get());
    }

    public static boolean isLivingInJetSuit(LivingEntity entity) {
        if (!isLivingInArmor(entity, EquipmentSlot.HEAD, ItemsRegistry.JET_HELMET.get())) return false;
        if (!isLivingInArmor(entity, EquipmentSlot.CHEST, ItemsRegistry.JET_SUIT.get())) return false;
        if (!isLivingInArmor(entity, EquipmentSlot.LEGS, ItemsRegistry.JET_PANTS.get())) return false;
        return isLivingInArmor(entity, EquipmentSlot.FEET, ItemsRegistry.JET_BOOTS.get());
    }

    public static boolean isLivingInAnySpaceSuits(LivingEntity entity) {
        if (!(entity.getItemBySlot(EquipmentSlot.HEAD).is(SPACE_SUIT_PART))) return false;
        if (!(entity.getItemBySlot(EquipmentSlot.CHEST).is(SPACE_SUIT_PART))) return false;
        if (!(entity.getItemBySlot(EquipmentSlot.LEGS).is(SPACE_SUIT_PART))) return false;
        return entity.getItemBySlot(EquipmentSlot.FEET).is(SPACE_SUIT_PART);
    }

    public static boolean isLivingInArmor(LivingEntity entity, EquipmentSlot slot, Item item) {
        return entity.getItemBySlot(slot).getItem() == item;
    }

    public static boolean isSpaceLevel(Level level) {
        return Planets.SPACE_LEVELS.contains(level.dimension());
    }

    public static boolean isSpaceLevelWithoutOxygen(Level level) {
        return Planets.LEVELS_WITHOUT_OXYGEN.contains(level.dimension());
    }

    public static boolean isOrbitLevel(Level level) {
        return Planets.PLANETS_BY_ORBIT.containsKey(level.dimension());
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
        return entity instanceof IVehicleEntity;
    }

    public static boolean isVehicleItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof VehicleItem;
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

        if ((entity instanceof Mob || entity instanceof Player) && (Methods.isLivingInNetheriteSpaceSuit(entity) || Methods.isLivingInJetSuit(entity) || entity.hasEffect(MobEffects.FIRE_RESISTANCE) || entity.fireImmune())) {
            return;
        }

        if (entity instanceof Player player) {

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
        
        // If we are inside an area with air provided by the oxygen distributor, assume
        // it is cooled enough to not burn things.
        if (ChunkOxygen.isBreatheable(OxygenSystem.canBreatheWithoutSuit(entity, false).O2())) {
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

        if (entity instanceof Player player) {

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
            for (int i = 0; i <= landerEntity.getInventory().getSlots() - 1; i++) {
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
            if (player instanceof ServerPlayer serverPlayer) {

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
        if (entity.getVehicle().getY() < level.getMinBuildHeight() + 1) {

            Planet planet = Planets.getLocationForOrbit(level);
            if(planet!=null) {
                teleportWithEntityTo(entity, entity.getVehicle(), planet.planet, 700);
            }
        }
    }

    public static void entityFallToPlanet(Entity entity, Level level) {
        if (entity.getY() < level.getMinBuildHeight() + 1) {
            Planet planet = Planets.getLocationForOrbit(level);
            if(planet!=null) {
                Methods.teleportTo(entity, planet.planet, 550);
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
