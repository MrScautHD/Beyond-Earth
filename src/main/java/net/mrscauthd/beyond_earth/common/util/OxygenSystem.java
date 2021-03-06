package net.mrscauthd.beyond_earth.common.util;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenCapability;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.registries.EffectsRegistry;

public class OxygenSystem {

    public static void OxygenSystem(Player entity, Level level) {
        if (Config.PLAYER_OXYGEN_SYSTEM.get() && Methods.isSpaceLevelWithoutOxygen(level) && !entity.isSpectator() && !entity.getAbilities().instabuild) {

            if (entity.getAirSupply() < 1) {
                Methods.hurtLivingWithOxygenSource(entity);
            }

            if (Methods.isLivingInAnySpaceSuits(entity) && !entity.hasEffect(EffectsRegistry.OXYGEN_EFFECT.get())) {

                ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, 2));
                IOxygenStorage oxygenStorage = itemstack.getCapability(OxygenCapability.OXYGEN).orElse(null);

                if (oxygenStorage.getOxygenStored() == 0) {
                    entity.setAirSupply(-4);
                }

                if (oxygenStorage.getOxygenStored() > 0) {
                    entity.setAirSupply(300);
                }

            }

            if (!Methods.isLivingInAnySpaceSuits(entity) && !entity.hasEffect(EffectsRegistry.OXYGEN_EFFECT.get())) {
                entity.setAirSupply(-4);
            }

            if (entity.hasEffect(EffectsRegistry.OXYGEN_EFFECT.get()) || entity.getPersistentData().getBoolean(BeyondEarth.MODID + ":planet_selection_menu_open")) {
                entity.setAirSupply(300);
            }
        }

        //Out of Space
        if (Methods.isLivingInAnySpaceSuits(entity) && entity.isEyeInFluid(FluidTags.WATER) && !entity.hasEffect(EffectsRegistry.OXYGEN_EFFECT.get())) {

            ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, 2));
            IOxygenStorage oxygenStorage = itemstack.getCapability(OxygenCapability.OXYGEN).orElse(null);
            if (oxygenStorage.getOxygenStored() > 0) {
                entity.setAirSupply(300);
            }
        }

        if (entity.hasEffect(EffectsRegistry.OXYGEN_EFFECT.get())) {
            entity.setAirSupply(300);
        }
    }
}
