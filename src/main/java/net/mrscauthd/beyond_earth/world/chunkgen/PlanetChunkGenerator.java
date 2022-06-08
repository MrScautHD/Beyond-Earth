package net.mrscauthd.beyond_earth.world.chunkgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

//TODO MAYBE GET REMOVED WITH THE 1.19 UPDATE (MC FIXED THAT I THINK)
public class PlanetChunkGenerator extends NoiseBasedChunkGenerator {

    public static final Codec<PlanetChunkGenerator> CODEC = RecordCodecBuilder.create((p_224323_) -> {
        return commonCodec(p_224323_).and(p_224323_.group(RegistryOps.retrieveRegistry(Registry.NOISE_REGISTRY).forGetter((p_188716_) -> {
            return p_188716_.noises;
        }), BiomeSource.CODEC.fieldOf("biome_source").forGetter((p_188711_) -> {
            return p_188711_.biomeSource;
        }), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((p_224278_) -> {
            return p_224278_.settings;
        }))).apply(p_224323_, p_224323_.stable(PlanetChunkGenerator::new));
    });

    public PlanetChunkGenerator(Registry<StructureSet> p_224206_, Registry<NormalNoise.NoiseParameters> p_224207_, BiomeSource p_224208_, Holder<NoiseGeneratorSettings> p_224209_) {
        super(p_224206_, p_224207_, p_224208_, p_224209_);
    }

    @Override
    public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState, ChunkAccess chunkAccess) {
        BlockState bedrock = Blocks.BEDROCK.defaultBlockState();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int x;
        int y;
        int z;

        /** GEN BEDROCK LAYER */
        if (!defaultBlock.isAir()) {
            for (x = 0; x < 16; x++) {
                for (z = 0; z < 16; z++) {
                    chunkAccess.setBlockState(pos.set(x, getMinY(), z), bedrock, false);
                }
            }
        }

        /** GEN LAVA LAYER */
        if (!defaultBlock.isAir()) {
            for (x = 0; x < 16; x++) {
                for (z = 0; z < 16; z++) {
                    for (y = 1; y < 9; y++) {
                        if (chunkAccess.getBlockState(new BlockPos(x, getMinY() + y, z)).isAir()) {
                            chunkAccess.setBlockState(pos.set(x, getMinY() + y, z), Blocks.LAVA.defaultBlockState(), false);
                        }
                    }
                }
            }
        }

        super.buildSurface(worldGenRegion, structureManager, randomState, chunkAccess);
    }
}
