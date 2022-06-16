package net.mrscauthd.beyond_earth.registries;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ColumnFeatureConfiguration;
import net.minecraftforge.registries.*;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.features.InfernalSpireColumn;

public class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BeyondEarth.MODID);

    /** FEATURES */
    public static final RegistryObject<InfernalSpireColumn> INFERNAL_SPIRE_COLUMN = FEATURES.register("infernal_spire_column", () -> new InfernalSpireColumn(ColumnFeatureConfiguration.CODEC));
}