package net.mrscauthd.beyond_earth.common.data.recipes;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.RecipeSerializersRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;
import net.mrscauthd.beyond_earth.common.util.EnumUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Locale;

public class AlienTradingRecipeMap extends AlienTradingRecipeItemStackBase {

	private final ResourceLocation structureName;
	private final MapDecoration.Type mapDecorationType;

	public AlienTradingRecipeMap(ResourceLocation id, JsonObject json) {
		super(id, json);

		JsonObject result = GsonHelper.getAsJsonObject(json, "result");
		this.structureName = new ResourceLocation(GsonHelper.getAsString(result, "structureName"));
		this.mapDecorationType = EnumUtils.valueOfIgnoreCase(MapDecoration.Type.class, GsonHelper.getAsString(result, "mapDecorationType"));
	}

	public AlienTradingRecipeMap(ResourceLocation id, FriendlyByteBuf buffer) {
		super(id, buffer);

		this.structureName = buffer.readResourceLocation();
		this.mapDecorationType = buffer.readEnum(MapDecoration.Type.class);
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		super.write(buffer);

		buffer.writeResourceLocation(this.structureName);
		buffer.writeEnum(this.mapDecorationType);
	}

	public static final TagKey<EntityType<?>> OXYGEN_TAG = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(BeyondEarth.MODID, "entities/oxygen"));
	
	@Override
	public Triple<ItemStack, ItemStack, ItemStack> getTrade(Entity trader, RandomSource rand) {
		Level level = trader.level;
                TagKey<Structure> key = TagKey.create(Registries.STRUCTURE, this.getStructureName());
		ItemStack itemstack = new ItemStack(Items.FILLED_MAP);

		if (level instanceof ServerLevel serverWorld) {
			BlockPos blockpos = serverWorld.findNearestMapStructure(key, trader.blockPosition(), 100, true);

			if (blockpos != null) {
				itemstack = MapItem.create(level, blockpos.getX(), blockpos.getZ(), (byte) 2, true, true);
				MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.getMapDecorationType());
			}

			MapItem.renderBiomePreviewMap(serverWorld, itemstack);
		}

		itemstack.setHoverName(Component.translatable("filled_map." + this.getStructureName().toString().toLowerCase(Locale.ROOT)));
		return Triple.of(this.getCostA(), this.getCostB(), itemstack);
	}

	public ResourceLocation getStructureName() {
		return this.structureName;
	}

	public MapDecoration.Type getMapDecorationType() {
		return this.mapDecorationType;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return RecipeSerializersRegistry.RECIPE_SERIALIZER_ALIEN_TRADING_MAP.get();
	}

	@Override
	public RecipeType<?> getType() {
		return RecipeTypeRegistry.ALIEN_TRADING_MAP.get();
	}

	public static class Serializer extends BeyondEarthRecipeSerializer<AlienTradingRecipeMap> {
		@Override
		public AlienTradingRecipeMap fromJson(ResourceLocation id, JsonObject json) {
			return new AlienTradingRecipeMap(id, json);
		}

		@Override
		public AlienTradingRecipeMap fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
			return new AlienTradingRecipeMap(id, buffer);
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, AlienTradingRecipeMap recipe) {
			recipe.write(buffer);
		}

	}

}
