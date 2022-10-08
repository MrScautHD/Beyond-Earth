package net.mrscauthd.beyond_earth.common.util;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.ChunkOxygen;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenProvider;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenStorage;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.registries.CapabilityRegistry;
import net.mrscauthd.beyond_earth.common.registries.EffectRegistry;

public class OxygenSystem {

    public static void oxygenSystem(Player entity, Level level) {
        if (level.isClientSide())
            return;
        boolean isInSuit = Methods.isLivingInAnySpaceSuits(entity);
        boolean hasO2Effect = entity.hasEffect(EffectRegistry.OXYGEN_EFFECT.get());
        boolean checkNoO2 = Config.ENTITY_OXYGEN_SYSTEM.get();

        if (checkNoO2 && !isInSuit && !hasO2Effect && entity.tickCount % Config.OXYGEN_BREATHE_RATE.get() == 0) {
            ChunkOxygen chunkO2 = ((LevelChunk) level.getChunk(entity.blockPosition()))
                    .getCapability(CapabilityRegistry.CHUNK_OXYGEN).orElse(null);
            int O2 = chunkO2.getO2(entity.blockPosition());
            if (O2 > 5) {
                checkNoO2 = false;
            }
            chunkO2.addO2(entity.blockPosition(), (byte) -Config.OXYGEN_BREATHE_AMOUNT.get(), true);
        }

        if (checkNoO2 && Methods.isSpaceLevelWithoutOxygen(level) && !entity.isSpectator()
                && !entity.getAbilities().instabuild) {

            if (entity.getAirSupply() < 1) {
                Methods.hurtLivingWithOxygenSource(entity);
            }

            if (isInSuit && !hasO2Effect) {

                ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, 2));
                OxygenStorage oxygenStorage = itemstack.getCapability(OxygenProvider.OXYGEN).orElse(null);

                if (oxygenStorage.getOxygen() == 0) {
                    entity.setAirSupply(-4);
                }

                if (oxygenStorage.getOxygen() > 0) {
                    entity.setAirSupply(300);
                }
            }

            if (hasO2Effect
                    || entity.getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_menu_open")) {
                entity.setAirSupply(300);
            }
        }

        // Out of Space
        if (isInSuit && !hasO2Effect && entity.isEyeInFluid(FluidTags.WATER)) {

            ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, 2));
            OxygenStorage oxygenStorage = itemstack.getCapability(OxygenProvider.OXYGEN).orElse(null);
            if (oxygenStorage.getOxygen() > 0) {
                entity.setAirSupply(300);
            }
        }

        if (entity.hasEffect(EffectRegistry.OXYGEN_EFFECT.get())) {
            entity.setAirSupply(300);
        }
    }
}
