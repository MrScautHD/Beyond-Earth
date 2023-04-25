package net.mrscauthd.beyond_earth.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.datagen.provider.ModBlockStateProvider;
import net.mrscauthd.beyond_earth.datagen.provider.ModItemModelProvider;
import net.mrscauthd.beyond_earth.datagen.provider.ModLootTableProvider;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        /** SERVER */
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));
        /** CLIENT */
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, BeyondEarth.MODID, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, BeyondEarth.MODID, existingFileHelper));
    }
}
