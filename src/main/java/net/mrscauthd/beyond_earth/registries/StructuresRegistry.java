package net.mrscauthd.beyond_earth.registries;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.world.structures.*;

public class StructuresRegistry {

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, BeyondEarth.MODID);

    /** STRUCTURES */
    public static final RegistryObject<StructureType<LargeJigsawStructure>> LARGE_JIGSAW_STRUCTURE = STRUCTURES.register("large_jigsaw_structure", () -> () -> LargeJigsawStructure.CODEC);
}
