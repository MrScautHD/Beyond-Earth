package net.mrscauthd.beyond_earth.common.keybinds;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.mrscauthd.beyond_earth.common.armors.JetSuit;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;
import net.mrscauthd.beyond_earth.common.entities.RoverEntity;
import net.mrscauthd.beyond_earth.common.util.Methods;
import net.mrscauthd.beyond_earth.common.registries.SoundsRegistry;

public class KeyMethods {

    public static void rotateRocket(Player player, int rotation) {
        if (player.isPassenger()) {
            if (player.getVehicle() instanceof IRocketEntity) {
                IRocketEntity rocket = (IRocketEntity) player.getVehicle();

                Methods.setEntityRotation(rocket, rotation);
            }
        }
    }

    public static void rotateRover(Player player, int rotationForward, int rotationBackward) {
        if (player.isPassenger()) {
            if (player.getVehicle() instanceof RoverEntity) {
                RoverEntity rover = (RoverEntity) player.getVehicle();
                float forward = player.zza;

                if (player.getVehicle().getEntityData().get(RoverEntity.FUEL) != 0 && !player.getVehicle().isEyeInFluid(FluidTags.WATER)) {

                    if (forward > 0) {
                        Methods.setEntityRotation(rover, rotationForward);
                    } else {
                        Methods.setEntityRotation(rover, rotationBackward);
                    }
                }
            }
        }
    }

    public static void slowDownLander(Player player) {
        if (player.isPassenger()) {
            if (player.getVehicle() instanceof LanderEntity) {
                LanderEntity lander = (LanderEntity) player.getVehicle();
                Level level = lander.level;
                Vec3 vec = lander.getDeltaMovement();

                if (!lander.isOnGround() && !lander.isEyeInFluid(FluidTags.WATER)) {
                    if (vec.y() < -0.05) {
                        lander.setDeltaMovement(vec.x(), vec.y() * 0.85, vec.z());
                    }

                    lander.fallDistance = (float) (vec.y() * (-1) * 4.5);

                    if (level instanceof ServerLevel) {
                        for (ServerPlayer p : ((ServerLevel) player.level).getServer().getPlayerList().getPlayers()) {
                            ((ServerLevel) level).sendParticles(p, ParticleTypes.SPIT, true, lander.getX(), lander.getY() - 0.3, lander.getZ(), 3, 0.1, 0.1, 0.1, 0.001);
                        }
                    }
                }
            }
        }
    }

    public static void startRocket(Player player) {
        if (player.isPassenger()) {
            if (player.getVehicle() instanceof IRocketEntity) {
                IRocketEntity rocket = (IRocketEntity) player.getVehicle();
                SynchedEntityData data = rocket.getEntityData();

                if (data.get(IRocketEntity.FUEL) == 3000) {
                    if (!data.get(IRocketEntity.ROCKET_START)) {
                        data.set(IRocketEntity.ROCKET_START, true);
                        rocket.level.playSound(null, rocket, SoundsRegistry.ROCKET_SOUND.get(), SoundSource.AMBIENT, 1, 1);
                    }
                } else {
                    Methods.sendVehicleHasNoFuelMessage(player);
                }
            }
        }
    }

    public static void switchJetSuitMode(Player player) {
        if (Methods.isLivingInJetSuit(player)) {
            ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
            CompoundTag compoundTag = itemStack.getOrCreateTag();

            if (compoundTag.getInt(JetSuit.Suit.TAG_MODE) < 2) {
                compoundTag.putInt(JetSuit.Suit.TAG_MODE, compoundTag.getInt(JetSuit.Suit.TAG_MODE) + 1);
            } else {
                compoundTag.putInt(JetSuit.Suit.TAG_MODE, 0);
            }
        }
    }
}
