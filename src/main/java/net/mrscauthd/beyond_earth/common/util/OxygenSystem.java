package net.mrscauthd.beyond_earth.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.ChunkOxygen;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.IOxygenStorage;
import net.mrscauthd.beyond_earth.common.capabilities.oxygen.OxygenUtil;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.registries.CapabilityRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagRegistry;

public class OxygenSystem {

    public record AirCheckResult(int O2) {
    }

    private static final AirCheckResult ALWAYS_YES = new AirCheckResult(100);
    private static final AirCheckResult IN_FLUID = new AirCheckResult(-1);

    /**
     * 
     * @param mob          - The mob to check for breathing
     * @param applyChunkO2 - Whether to consume O2 this check.
     * @return - A holder containing the amount of O2 present here. -1 means inside
     *         a fluid.
     */
    public static AirCheckResult canBreatheWithoutSuit(LivingEntity mob, boolean applyChunkO2) {
        // Check if we are in the tag of can breath without O2.
        if (mob.getType().is(TagRegistry.ENTITY_NO_OXYGEN_NEEDED_TAG))
            return ALWAYS_YES;
        boolean inWater = !mob.getEyeInFluidType().isAir();
        if (inWater)
            return IN_FLUID;
        Level level = mob.getLevel();
        BlockPos pos = mob.blockPosition();
        ChunkOxygen chunkO2 = ((LevelChunk) level.getChunk(pos)).getCapability(CapabilityRegistry.CHUNK_OXYGEN)
                .orElse(null);
        int O2 = chunkO2.getO2(pos);
        if (applyChunkO2)
            chunkO2.addO2(pos, (byte) -Config.OXYGEN_BREATHE_AMOUNT.get(), true);
        return new AirCheckResult(O2);
    }

    /**
     * 
     * @param mob       - Mob to check for has O2 in a suit.
     * @param consumeO2 - Wether to consume O2 from said suit.
     * @return - Whether the mob had O2 in the suit.
     */
    public static boolean canBreatheFromSuit(LivingEntity mob, boolean consumeO2) {
        boolean isInSuit = Methods.isLivingInAnySpaceSuits(mob);
        if (!isInSuit)
            return false;
        ItemStack itemstack = mob.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, 2));
        IOxygenStorage oxygenStorage = OxygenUtil.getItemStackOxygenStorage(itemstack);
        boolean hasO2 = oxygenStorage.getOxygen() > 0;
        if (consumeO2 && hasO2) {
            oxygenStorage.setOxygen(oxygenStorage.getOxygen() - 1);
        }
        return hasO2;
    }

    /**
     * IF A ENTITY SHOULD NOT GET DAMAGE BECAUSE NO OXYGEN IN SPACE ADD IT TO TAG
     * "beyond_earth:no_oxygen_needed"
     */
    public static void entityOxygen(LivingEntity entity, Level level) {
        // This only runs server side anyway.
        if (level.isClientSide())
            return;
        boolean creativePlayer = entity instanceof Player player
                && (player.getAbilities().instabuild || player.isSpectator());

        AirCheckResult noSuitCheck = canBreatheWithoutSuit(entity,
                !creativePlayer && entity.tickCount % Config.OXYGEN_BREATHE_RATE.get() == 0);

        // If this is set true, we will re-fill air to 300
        boolean shouldFillAirSupply = ChunkOxygen.isBreatheable(noSuitCheck.O2());
        // After checking breathable air, then or in the check for the suit. This will
        // only be done if we were not already in a breathable area.
        shouldFillAirSupply = shouldFillAirSupply || canBreatheFromSuit(entity,
                !creativePlayer && entity.tickCount % Config.SUIT_BREATHE_RATE.get() == 0);

        if (shouldFillAirSupply) {
            entity.setAirSupply(300);
        } else if (ChunkOxygen.isVacuum(noSuitCheck.O2())) {
            entity.setAirSupply(-4);

            // We then check if we need to apply extra vacuum damage.
            entity.getPersistentData().putDouble(BeyondEarth.MODID + ":oxygen_tick",
                    entity.getPersistentData().getDouble(BeyondEarth.MODID + ":oxygen_tick") + 1);

            // Apply this at a rate based on the config option.
            if (entity.getPersistentData().getDouble(BeyondEarth.MODID + ":oxygen_tick") > Config.VACUUM_DAMAGE_RATE
                    .get()) {
                Methods.hurtLivingWithOxygenSource(entity);
                entity.getPersistentData().putDouble(BeyondEarth.MODID + ":oxygen_tick", 0);
            }
        }
    }
}
