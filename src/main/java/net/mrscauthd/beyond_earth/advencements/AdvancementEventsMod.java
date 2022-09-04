package net.mrscauthd.beyond_earth.advencements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AdvancementEventsMod {

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BeyondEarthCriteriaTriggers.visit();
		});
	}
}
