package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.server.command.ConfigCommand;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.commands.PlanetSelectionCommand;
import net.mrscauthd.beyond_earth.common.world.processors.StructureVoidProcessor;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID)
public class CommandsRegistry {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new PlanetSelectionCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }
}
