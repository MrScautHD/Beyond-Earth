package net.mrscauthd.beyond_earth.world.cavers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.ModInit;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlanetCaver {

    protected static final Supplier<Set<Block>> replaceableBlocks = () -> ImmutableSet.of(
            ModInit.MOON_STONE.get(),
            ModInit.MARS_STONE.get(),
            ModInit.MERCURY_STONE.get(),
            ModInit.VENUS_STONE.get(), ModInit.VENUS_SANDSTONE.get(),
            ModInit.GLACIO_STONE.get(), ModInit.PERMAFROST_STONE.get());

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WorldCarver.CAVE.replaceableBlocks = new ImmutableSet.Builder<Block>().addAll(WorldCarver.CAVE.replaceableBlocks).addAll(replaceableBlocks.get()).build();
            WorldCarver.CANYON.replaceableBlocks = new ImmutableSet.Builder<Block>().addAll(WorldCarver.CANYON.replaceableBlocks).addAll(replaceableBlocks.get()).build();
        });
    }
}