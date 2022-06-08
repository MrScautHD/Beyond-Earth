package net.mrscauthd.beyond_earth.registries;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.world.structures.*;

public class StructuresRegistry {

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, BeyondEarth.MODID);

    /** STRUCTURES */
    public static final RegistryObject<StructureType<?>> ALIEN_VILLAGE = STRUCTURES.register("alien_village", () -> typeConvert(AlienVillage.CODEC));
    public static final RegistryObject<StructureType<?>> METEOR = STRUCTURES.register("meteor", () -> typeConvert(Meteor.CODEC));
    public static final RegistryObject<StructureType<?>> OIL_WELL = STRUCTURES.register("oil_well", () -> typeConvert(OilWell.CODEC));
    public static final RegistryObject<StructureType<?>> PYGRO_TOWER = STRUCTURES.register("pygro_tower", () -> typeConvert(PygroTower.CODEC));
    public static final RegistryObject<StructureType<?>> PYGRO_VILLAGE = STRUCTURES.register("pygro_village", () -> typeConvert(PygroVillage.CODEC));
    public static final RegistryObject<StructureType<?>> VENUS_BULLET = STRUCTURES.register("venus_bullet", () -> typeConvert(VenusBullet.CODEC));

    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }
}
