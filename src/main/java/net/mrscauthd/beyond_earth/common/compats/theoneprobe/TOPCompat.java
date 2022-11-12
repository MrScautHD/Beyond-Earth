package net.mrscauthd.beyond_earth.common.compats.theoneprobe;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;

@EventBusSubscriber(bus = Bus.MOD, modid = BeyondEarth.MODID)
public class TOPCompat {
	public static final String MODID = "theoneprobe";

	public static ResourceLocation rl(String path) {
		return new ResourceLocation(MODID, path);
	}

	@SubscribeEvent
	public static void imcQueue(InterModEnqueueEvent event) {
	        if (ModList.get().isLoaded(MODID))
	            InterModComms.sendTo(MODID, "getTheOneProbe", TOPPlugin::new);
	}
}
