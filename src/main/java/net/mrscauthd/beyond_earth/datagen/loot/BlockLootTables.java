package net.mrscauthd.beyond_earth.datagen.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

import java.util.stream.Collectors;

public class BlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {

        /** SELF DROP */
        this.dropSelf(BlockRegistry.ROCKET_LAUNCH_PAD.get());
        this.dropSelf(BlockRegistry.COAL_TORCH_BLOCK.get());
        this.dropSelf(BlockRegistry.COAL_LANTERN_BLOCK.get());
        this.dropSelf(BlockRegistry.EARTH_GLOBE_BLOCK.get());
        this.dropSelf(BlockRegistry.MOON_GLOBE_BLOCK.get());
        this.dropSelf(BlockRegistry.MARS_GLOBE_BLOCK.get());
        this.dropSelf(BlockRegistry.MERCURY_GLOBE_BLOCK.get());
        this.dropSelf(BlockRegistry.VENUS_GLOBE_BLOCK.get());
        this.dropSelf(BlockRegistry.GLACIO_GLOBE_BLOCK.get());
        this.dropSelf(BlockRegistry.FLAG_BLOCK.get());
        this.dropSelf(BlockRegistry.MOON_SAND.get());
        this.dropSelf(BlockRegistry.MARS_SAND.get());
        this.dropSelf(BlockRegistry.VENUS_SAND.get());
        this.dropSelf(BlockRegistry.STEEL_BLOCK.get());
        this.dropSelf(BlockRegistry.DESH_BLOCK.get());
        this.dropSelf(BlockRegistry.OSTRUM_BLOCK.get());
        this.dropSelf(BlockRegistry.CALORITE_BLOCK.get());
        this.dropSelf(BlockRegistry.RAW_DESH_BLOCK.get());
        this.dropSelf(BlockRegistry.RAW_OSTRUM_BLOCK.get());
        this.dropSelf(BlockRegistry.RAW_CALORITE_BLOCK.get());
        this.dropSelf(BlockRegistry.IRON_PLATING_BLOCK.get());
        this.dropSelf(BlockRegistry.DESH_PILLAR_BLOCK.get());
        this.dropSelf(BlockRegistry.DESH_PLATING_BLOCK.get());
        this.dropSelf(BlockRegistry.BLUE_IRON_PILLAR.get());
        this.dropSelf(BlockRegistry.BARRICADE_BLOCK.get());
        this.dropSelf(BlockRegistry.IRON_MARK_BLOCK.get());
        this.dropSelf(BlockRegistry.METEORITE.get());
        this.dropSelf(BlockRegistry.INFERNAL_SPIRE.get());
        this.dropSelf(BlockRegistry.MOON_STONE.get());
        this.dropSelf(BlockRegistry.CRACKED_MOON_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.MOON_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.MOON_STONE_BRICK_SLAB.get());
        this.dropSelf(BlockRegistry.MOON_STONE_BRICK_STAIRS.get());
        this.dropSelf(BlockRegistry.MARS_STONE.get());
        this.dropSelf(BlockRegistry.CRACKED_MARS_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.MARS_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.MARS_STONE_BRICK_SLAB.get());
        this.dropSelf(BlockRegistry.MARS_STONE_BRICK_STAIRS.get());
        this.dropSelf(BlockRegistry.MERCURY_STONE.get());
        this.dropSelf(BlockRegistry.CRACKED_MERCURY_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.MERCURY_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.MERCURY_STONE_BRICK_SLAB.get());
        this.dropSelf(BlockRegistry.MERCURY_STONE_BRICK_STAIRS.get());
        this.dropSelf(BlockRegistry.VENUS_STONE.get());
        this.dropSelf(BlockRegistry.CRACKED_VENUS_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.VENUS_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.VENUS_STONE_BRICK_SLAB.get());
        this.dropSelf(BlockRegistry.VENUS_STONE_BRICK_STAIRS.get());
        this.dropSelf(BlockRegistry.VENUS_SANDSTONE.get());
        this.dropSelf(BlockRegistry.CRACKED_VENUS_SANDSTONE_BRICKS.get());
        this.dropSelf(BlockRegistry.VENUS_SANDSTONE_BRICKS.get());
        this.dropSelf(BlockRegistry.VENUS_SANDSTONE_BRICK_SLAB.get());
        this.dropSelf(BlockRegistry.VENUS_SANDSTONE_BRICK_STAIRS.get());
        this.dropSelf(BlockRegistry.GLACIO_STONE.get());
        this.dropSelf(BlockRegistry.PERMAFROST.get());
        this.dropSelf(BlockRegistry.CRACKED_GLACIO_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.GLACIO_STONE_BRICKS.get());
        this.dropSelf(BlockRegistry.GLACIO_STONE_BRICK_SLAB.get());
        this.dropSelf(BlockRegistry.GLACIO_STONE_BRICK_STAIRS.get());
        this.dropSelf(BlockRegistry.FUEL_REFINERY_BLOCK.get());
        this.dropSelf(BlockRegistry.COMPRESSOR_BLOCK.get());
        this.dropSelf(BlockRegistry.COAL_GENERATOR_BLOCK.get());
        this.dropSelf(BlockRegistry.OXYGEN_LOADER_BLOCK.get());
        this.dropSelf(BlockRegistry.SOLAR_PANEL_BLOCK.get());
        this.dropSelf(BlockRegistry.NASA_WORKBENCH_BLOCK.get());
        this.dropSelf(BlockRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK.get());
        this.dropSelf(BlockRegistry.WATER_PUMP_BLOCK.get());

        /** ORE DROP */
        this.add(BlockRegistry.MOON_CHEESE_ORE.get(), (block) -> createOreDrop(block, ItemsRegistry.CHEESE.get()));
        this.add(BlockRegistry.MOON_DESH_ORE.get(), (block) -> createOreDrop(block, ItemsRegistry.RAW_DESH.get()));
        this.add(BlockRegistry.MOON_IRON_ORE.get(), (block) -> createOreDrop(block, Items.RAW_IRON));
        this.add(BlockRegistry.MOON_ICE_SHARD_ORE.get(), (block) -> createOreDrop(block, ItemsRegistry.ICE_SHARD.get()));
        this.add(BlockRegistry.MARS_IRON_ORE.get(), (block) -> createOreDrop(block, Items.RAW_IRON));
        this.add(BlockRegistry.MARS_DIAMOND_ORE.get(), (block) -> createOreDrop(block, Items.DIAMOND));
        this.add(BlockRegistry.MARS_OSTRUM_ORE.get(), (block) -> createOreDrop(block, ItemsRegistry.RAW_OSTRUM.get()));
        this.add(BlockRegistry.MARS_ICE_SHARD_ORE.get(), (block) -> createOreDrop(block, ItemsRegistry.ICE_SHARD.get()));
        this.add(BlockRegistry.MERCURY_IRON_ORE.get(), (block) -> createOreDrop(block, Items.RAW_IRON));
        this.add(BlockRegistry.VENUS_COAL_ORE.get(), (block) -> createOreDrop(block, Items.COAL));
        this.add(BlockRegistry.VENUS_GOLD_ORE.get(), (block) -> createOreDrop(block, Items.RAW_GOLD));
        this.add(BlockRegistry.VENUS_DIAMOND_ORE.get(), (block) -> createOreDrop(block, Items.DIAMOND));
        this.add(BlockRegistry.VENUS_CALORITE_ORE.get(), (block) -> createOreDrop(block, ItemsRegistry.RAW_CALORITE.get()));
        this.add(BlockRegistry.GLACIO_ICE_SHARD_ORE.get(), (block) -> createOreDrop(block, ItemsRegistry.ICE_SHARD.get()));
        this.add(BlockRegistry.GLACIO_COAL_ORE.get(), (block) -> createOreDrop(block, Items.COAL));
        this.add(BlockRegistry.GLACIO_COPPER_ORE.get(), (block) -> createOreDrop(block, Items.RAW_COPPER));
        this.add(BlockRegistry.GLACIO_IRON_ORE.get(), (block) -> createOreDrop(block, Items.RAW_IRON));
        this.add(BlockRegistry.GLACIO_LAPIS_ORE.get(), (block) -> createOreDrop(block, Items.LAPIS_LAZULI));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }
}
