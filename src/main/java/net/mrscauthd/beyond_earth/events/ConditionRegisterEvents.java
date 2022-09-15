package net.mrscauthd.beyond_earth.events;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.crafting.conditions.ConfigSteelManagementCondition;

@EventBusSubscriber(modid = BeyondEarthMod.MODID, bus = Bus.MOD)
public class ConditionRegisterEvents {
	@SubscribeEvent
	public static void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
		CraftingHelper.register(ConfigSteelManagementCondition.Serializer.INSTANCE);
	}
}
