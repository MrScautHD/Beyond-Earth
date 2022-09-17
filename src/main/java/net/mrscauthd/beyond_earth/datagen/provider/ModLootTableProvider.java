package net.mrscauthd.beyond_earth.datagen.provider;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.mrscauthd.beyond_earth.datagen.loot.BlockLootTables;
import net.mrscauthd.beyond_earth.datagen.loot.ChestLootTables;
import net.mrscauthd.beyond_earth.datagen.loot.EntityLootTables;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
                Pair.of(BlockLootTables::new, LootContextParamSets.BLOCK),
                Pair.of(EntityLootTables::new, LootContextParamSets.ENTITY),
                Pair.of(ChestLootTables::new, LootContextParamSets.CHEST));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext context) {

    }
}
