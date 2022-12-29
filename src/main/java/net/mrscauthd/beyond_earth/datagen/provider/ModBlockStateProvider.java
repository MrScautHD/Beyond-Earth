package net.mrscauthd.beyond_earth.datagen.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;

public class ModBlockStateProvider extends BlockStateProvider {

    public String path;
    public String pathAfter;

    public ModBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        this.setPath("", "");
        simpleBlock(BlockRegistry.STEEL_BLOCK.get());
        simpleBlock(BlockRegistry.DESH_BLOCK.get());
        simpleBlock(BlockRegistry.OSTRUM_BLOCK.get());
        simpleBlock(BlockRegistry.CALORITE_BLOCK.get());
        simpleBlock(BlockRegistry.RAW_DESH_BLOCK.get());
        simpleBlock(BlockRegistry.RAW_OSTRUM_BLOCK.get());
        simpleBlock(BlockRegistry.RAW_CALORITE_BLOCK.get());
        simpleBlock(BlockRegistry.METEORITE.get());

        this.setPath("decoration/", "");
        simpleBlock(BlockRegistry.IRON_PLATING_BLOCK.get());
        axisBlock(BlockRegistry.BLUE_IRON_PILLAR.get());
        axisBlock(BlockRegistry.BARRICADE_BLOCK.get());
        axisBlock(BlockRegistry.IRON_MARK_BLOCK.get());
        axisBlock(BlockRegistry.DESH_PILLAR_BLOCK.get());
        simpleBlock(BlockRegistry.DESH_PLATING_BLOCK.get());

        this.setPath("globes/", "");
        singleTextureWithoutParent(BlockRegistry.EARTH_GLOBE_BLOCK.get());
        singleTextureWithoutParent(BlockRegistry.MOON_GLOBE_BLOCK.get());
        singleTextureWithoutParent(BlockRegistry.MARS_GLOBE_BLOCK.get());
        singleTextureWithoutParent(BlockRegistry.MERCURY_GLOBE_BLOCK.get());
        singleTextureWithoutParent(BlockRegistry.VENUS_GLOBE_BLOCK.get());
        singleTextureWithoutParent(BlockRegistry.GLACIO_GLOBE_BLOCK.get());

        this.setPath("moon/", "");
        simpleBlock(BlockRegistry.MOON_SAND.get());
        simpleBlock(BlockRegistry.MOON_STONE.get());
        simpleBlock(BlockRegistry.MOON_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.CRACKED_MOON_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.MOON_CHEESE_ORE.get());
        simpleBlock(BlockRegistry.MOON_DESH_ORE.get());
        simpleBlock(BlockRegistry.MOON_IRON_ORE.get());
        simpleBlock(BlockRegistry.MOON_ICE_SHARD_ORE.get());
        slabBlock(BlockRegistry.MOON_STONE_BRICK_SLAB.get(), key(BlockRegistry.MOON_STONE_BRICKS.get()), blockTexture(BlockRegistry.MOON_STONE_BRICKS.get()));
        stairsBlock(BlockRegistry.MOON_STONE_BRICK_STAIRS.get(), blockTexture(BlockRegistry.MOON_STONE_BRICKS.get()));

        this.setPath("mars/", "");
        simpleBlock(BlockRegistry.MARS_SAND.get());
        simpleBlock(BlockRegistry.MARS_STONE.get());
        simpleBlock(BlockRegistry.MARS_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.CRACKED_MARS_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.MARS_IRON_ORE.get());
        simpleBlock(BlockRegistry.MARS_DIAMOND_ORE.get());
        simpleBlock(BlockRegistry.MARS_OSTRUM_ORE.get());
        simpleBlock(BlockRegistry.MARS_ICE_SHARD_ORE.get());
        slabBlock(BlockRegistry.MARS_STONE_BRICK_SLAB.get(), key(BlockRegistry.MARS_STONE_BRICKS.get()), blockTexture(BlockRegistry.MARS_STONE_BRICKS.get()));
        stairsBlock(BlockRegistry.MARS_STONE_BRICK_STAIRS.get(), blockTexture(BlockRegistry.MARS_STONE_BRICKS.get()));

        this.setPath("mercury/", "");
        simpleBlock(BlockRegistry.MERCURY_STONE.get());
        simpleBlock(BlockRegistry.MERCURY_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.CRACKED_MERCURY_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.MERCURY_IRON_ORE.get());
        slabBlock(BlockRegistry.MERCURY_STONE_BRICK_SLAB.get(), key(BlockRegistry.MERCURY_STONE_BRICKS.get()), blockTexture(BlockRegistry.MERCURY_STONE_BRICKS.get()));
        stairsBlock(BlockRegistry.MERCURY_STONE_BRICK_STAIRS.get(), blockTexture(BlockRegistry.MERCURY_STONE_BRICKS.get()));

        this.setPath("venus/", "");
        simpleBlock(BlockRegistry.VENUS_SAND.get());
        cubeTop(BlockRegistry.VENUS_STONE.get());
        simpleBlock(BlockRegistry.VENUS_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.CRACKED_VENUS_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.VENUS_COAL_ORE.get());
        simpleBlock(BlockRegistry.VENUS_GOLD_ORE.get());
        simpleBlock(BlockRegistry.VENUS_DIAMOND_ORE.get());
        simpleBlock(BlockRegistry.VENUS_CALORITE_ORE.get());
        slabBlock(BlockRegistry.VENUS_STONE_BRICK_SLAB.get(), key(BlockRegistry.VENUS_STONE_BRICKS.get()), blockTexture(BlockRegistry.VENUS_STONE_BRICKS.get()));
        stairsBlock(BlockRegistry.VENUS_STONE_BRICK_STAIRS.get(), blockTexture(BlockRegistry.VENUS_STONE_BRICKS.get()));
        simpleBlock(BlockRegistry.VENUS_SANDSTONE.get());
        simpleBlock(BlockRegistry.VENUS_SANDSTONE_BRICKS.get());
        simpleBlock(BlockRegistry.CRACKED_VENUS_SANDSTONE_BRICKS.get());
        slabBlock(BlockRegistry.VENUS_SANDSTONE_BRICK_SLAB.get(), key(BlockRegistry.VENUS_SANDSTONE_BRICKS.get()), blockTexture(BlockRegistry.VENUS_SANDSTONE_BRICKS.get()));
        stairsBlock(BlockRegistry.VENUS_SANDSTONE_BRICK_STAIRS.get(), blockTexture(BlockRegistry.VENUS_SANDSTONE_BRICKS.get()));
        cubeTop(BlockRegistry.INFERNAL_SPIRE.get());

        this.setPath("glacio/", "");
        simpleBlock(BlockRegistry.GLACIO_STONE.get());
        simpleBlock(BlockRegistry.GLACIO_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.CRACKED_GLACIO_STONE_BRICKS.get());
        simpleBlock(BlockRegistry.GLACIO_ICE_SHARD_ORE.get());
        simpleBlock(BlockRegistry.GLACIO_COAL_ORE.get());
        simpleBlock(BlockRegistry.GLACIO_COPPER_ORE.get());
        simpleBlock(BlockRegistry.GLACIO_IRON_ORE.get());
        simpleBlock(BlockRegistry.GLACIO_LAPIS_ORE.get());
        slabBlock(BlockRegistry.GLACIO_STONE_BRICK_SLAB.get(), key(BlockRegistry.GLACIO_STONE_BRICKS.get()), blockTexture(BlockRegistry.GLACIO_STONE_BRICKS.get()));
        stairsBlock(BlockRegistry.GLACIO_STONE_BRICK_STAIRS.get(), blockTexture(BlockRegistry.GLACIO_STONE_BRICKS.get()));
        simpleBlock(BlockRegistry.PERMAFROST.get());

        this.setPath("fluids/", "_still");
        simpleBlock(BlockRegistry.FUEL_BLOCK.get());
        simpleBlock(BlockRegistry.OIL_BLOCK.get());
    }

    public void cubeTop(Block block) {
        simpleBlock(block, new ConfiguredModel(models().cubeTop(key(block).getPath(), new ResourceLocation(blockTexture(block) + "_side"), new ResourceLocation(blockTexture(block) + "_top"))));
    }

    public void singleTextureWithoutParent(Block block) {
        simpleBlock(block, models().getBuilder(key(block).getPath()).texture("particle", blockTexture(block)));
    }

    public BlockModelBuilder templateModel(Block block, String parentPath) {
        return models().withExistingParent(key(block).getPath(), modLoc("template/" + parentPath)).texture("particle", blockTexture(block));
    }

    public void templateState(Block block, ModelFile modelFile) {
        simpleBlock(block, modelFile);
    }

    public void setPath(String path, String pathAfter) {
        this.path = path;
        this.pathAfter = pathAfter;
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    @Override
    public ResourceLocation blockTexture(Block block) {
        ResourceLocation name = key(block);
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + this.path + name.getPath() + this.pathAfter);
    }
}
