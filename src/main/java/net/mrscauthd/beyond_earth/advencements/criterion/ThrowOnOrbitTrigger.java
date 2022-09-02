package net.mrscauthd.beyond_earth.advencements.criterion;

import com.google.gson.JsonObject;

import mekanism.api.providers.IItemProvider;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

public class ThrowOnOrbitTrigger extends SimpleCriterionTrigger<ThrowOnOrbitTrigger.Instance> {
	private static final ResourceLocation ID = new ResourceLocation(BeyondEarthMod.MODID, "throw_on_orbit");

	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected ThrowOnOrbitTrigger.Instance createInstance(JsonObject json, EntityPredicate.Composite player, DeserializationContext context) {
		ItemPredicate item = ItemPredicate.fromJson(json.get("item"));
		return new ThrowOnOrbitTrigger.Instance(player, item);
	}

	public void trigger(ServerPlayer player, ItemStack item) {
		this.trigger(player, trigger -> {
			return trigger.matches(item);
		});
	}

	public static class Instance extends AbstractCriterionTriggerInstance {
		private final ItemPredicate item;

		public Instance(EntityPredicate.Composite player, ItemPredicate item) {
			super(ThrowOnOrbitTrigger.ID, player);
			this.item = item;
		}

		public static ThrowOnOrbitTrigger.Instance throwItemOnOrbit(IItemProvider item) {
			return new ThrowOnOrbitTrigger.Instance(EntityPredicate.Composite.ANY, ItemPredicate.Builder.item().of(item).build());
		}

		public boolean matches(ItemStack item) {
			return this.item.matches(item);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext context) {
			JsonObject json = super.serializeToJson(context);
			json.add("item", this.item.serializeToJson());
			return json;
		}
	}
}
