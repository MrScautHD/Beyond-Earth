package net.mrscauthd.beyond_earth.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.datagen.provider.ModBlockStateProvider;
import net.mrscauthd.beyond_earth.datagen.provider.ModItemModelProvider;
import net.mrscauthd.beyond_earth.datagen.provider.ModLootTableProvider;
import net.mrscauthd.beyond_earth.datagen.provider.ModRecipeProvider;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        /** SERVER */
        generator.addProvider(event.includeServer(), new ModLootTableProvider(generator));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(generator));

        /** CLIENT */
        generator.addProvider(event.includeClient(), new ModItemModelProvider(generator, BeyondEarth.MODID, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(generator, BeyondEarth.MODID, existingFileHelper));
    }
}
