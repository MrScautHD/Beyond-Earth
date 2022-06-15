package net.mrscauthd.beyond_earth.registries;

import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.world.structures.*;

public class StructuresRegistry {

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, BeyondEarthMod.MODID);

    /** STRUCTURES */
    public static final RegistryObject<StructureFeature<?>> ALIEN_VILLAGE = STRUCTURES.register("alien_village", AlienVillage::new);
    public static final RegistryObject<StructureFeature<?>> METEOR = STRUCTURES.register("meteor", Meteor::new);
    public static final RegistryObject<StructureFeature<?>> OIL_WELL = STRUCTURES.register("oil_well", OilWell::new);
    public static final RegistryObject<StructureFeature<?>> PYGRO_TOWER = STRUCTURES.register("pygro_tower", PygroTower::new);
    public static final RegistryObject<StructureFeature<?>> PYGRO_VILLAGE = STRUCTURES.register("pygro_village", PygroVillage::new);
    public static final RegistryObject<StructureFeature<?>> VENUS_BULLET = STRUCTURES.register("venus_bullet", VenusBullet::new);
}
