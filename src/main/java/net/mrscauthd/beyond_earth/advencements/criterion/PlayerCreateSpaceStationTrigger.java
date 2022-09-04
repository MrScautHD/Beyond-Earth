package net.mrscauthd.beyond_earth.advencements.criterion;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.Level;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

public class PlayerCreateSpaceStationTrigger extends SimpleCriterionTrigger<PlayerCreateSpaceStationTrigger.Instance> {
	private static final ResourceLocation ID = new ResourceLocation(BeyondEarthMod.MODID, "player_create_space_station");

	public ResourceLocation getId() {
		return ID;
	}

	public PlayerCreateSpaceStationTrigger.Instance createInstance(JsonObject json, EntityPredicate.Composite player, DeserializationContext context) {
		ResourceKey<Level> at = json.has("at") ? ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(GsonHelper.getAsString(json, "from"))) : null;
		return new PlayerCreateSpaceStationTrigger.Instance(player, at);
	}

	public void trigger(ServerPlayer player) {
		this.trigger(player, trigger -> {
			return trigger.matches(player.getLevel());
		});
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		@Nullable
		private final ResourceKey<Level> at;

		public Instance(EntityPredicate.Composite player, @Nullable ResourceKey<Level> at) {
			super(PlayerCreateSpaceStationTrigger.ID, player);
			this.at = at;
		}

		public static PlayerCreateSpaceStationTrigger.Instance playerCreateSpaceStation(@Nullable ResourceKey<Level> at) {
			return new PlayerCreateSpaceStationTrigger.Instance(EntityPredicate.Composite.ANY, at);
		}

		public boolean matches(ServerLevel level) {
			return this.at == null || this.at == level.dimension();
		}

		@Override
		public JsonObject serializeToJson(SerializationContext context) {
			JsonObject json = super.serializeToJson(context);

			if (this.at != null) {
				json.addProperty("at", this.at.location().toString());
			}

			return json;
		}
	}
}
