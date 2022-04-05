package net.mrscauthd.beyond_earth.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.world.chunkgen.PlanetChunkGenerator;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChunkGeneratorsRegistry {

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            /** PLANET CHUNK GENERATOR */
            Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(BeyondEarthMod.MODID, "planet_noise"), PlanetChunkGenerator.CODEC);
        });
    }
}
