package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.world.processors.StructureVoidProcessor;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StructureProcessorRegistry {

    /** STRUCTURE VOID PROCESSOR */
    public static final StructureProcessorType<StructureVoidProcessor> STRUCTURE_VOID_PROCESSOR = () -> StructureVoidProcessor.CODEC;

    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            /** STRUCTURE VOID PROCESSOR */
            Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, new ResourceLocation(BeyondEarth.MODID, "structure_void_processor"), STRUCTURE_VOID_PROCESSOR);
        });
    }
}
