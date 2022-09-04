package net.mrscauthd.beyond_earth.advencements;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.events.forge.ItemEntityTickEndEvent;
import net.mrscauthd.beyond_earth.events.forge.PlayerCreateSpaceStationEvent;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AdvancementEventsForge {
	private static final String NBT_KEY_THROW_ON_ORBIT = new ResourceLocation(BeyondEarthMod.MODID, "throw_on_orbit").toString();

	@SubscribeEvent
	public static void onItemToss(ItemTossEvent event) {
		Player player = event.getPlayer();
		Level level = player.level;

		if (level.isClientSide()) {
			return;
		}

		ItemEntity item = event.getEntityItem();

		if (player.getUUID().equals(item.getThrower()) == true && Methods.isOrbitWorld(level) == true) {
			CompoundTag persistent = item.getPersistentData();
			persistent.putBoolean(NBT_KEY_THROW_ON_ORBIT, true);
		}
	}

	@SubscribeEvent
	public static void onItemTick(ItemEntityTickEndEvent event) {
		ItemEntity item = event.getEntityItem();
		Level level = item.level;

		if (level.isClientSide()) {
			return;
		}

		CompoundTag persistent = item.getPersistentData();

		if (persistent.getBoolean(NBT_KEY_THROW_ON_ORBIT) == true) {
			if (Methods.isOrbitWorld(level) == true) {
				if (item.position().y < 0.0D) {
					Player player = level.getPlayerByUUID(item.getThrower());

					if (player != null) {
						BeyondEarthCriteriaTriggers.THROW_ON_ORBIT.trigger((ServerPlayer) player, item.getItem());
					}

					persistent.remove(NBT_KEY_THROW_ON_ORBIT);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerCreateSpaceStation(PlayerCreateSpaceStationEvent event) {
		if (event.getPlayer()instanceof ServerPlayer player) {
			BeyondEarthCriteriaTriggers.PLAYER_CREATE_SPACE_STATION.trigger(player);
		}
	}
}
