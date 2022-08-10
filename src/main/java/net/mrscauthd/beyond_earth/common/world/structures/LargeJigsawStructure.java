package net.mrscauthd.beyond_earth.common.world.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.mrscauthd.beyond_earth.common.registries.StructureRegistry;

import java.util.Optional;

public class LargeJigsawStructure extends Structure {

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;

    public static final Codec<LargeJigsawStructure> CODEC = RecordCodecBuilder.<LargeJigsawStructure>mapCodec(instance ->
            instance.group(LargeJigsawStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, LargeJigsawStructure::new)).codec();

    public LargeJigsawStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    private boolean extraSpawningChecks(Structure.GenerationContext context) {
        ChunkPos chunkpos = context.chunkPos();

        return context.chunkGenerator().getFirstOccupiedHeight(chunkpos.getMinBlockX(), chunkpos.getMinBlockZ(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, context.heightAccessor(), context.randomState()) < 150;
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        if (!this.extraSpawningChecks(context)) {
            return Optional.empty();
        }

        int startY = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));

        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), startY, chunkPos.getMinBlockZ());

        Optional<Structure.GenerationStub> structurePiecesGenerator = JigsawPlacement.addPieces(context, this.startPool, this.startJigsawName, this.size, blockPos, false, this.projectStartToHeightmap, this.maxDistanceFromCenter);

        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> type() {
        return StructureRegistry.LARGE_JIGSAW_STRUCTURE.get();
    }
}
