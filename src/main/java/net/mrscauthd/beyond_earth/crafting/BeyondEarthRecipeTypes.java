package net.mrscauthd.beyond_earth.crafting;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarthMod;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BeyondEarthRecipeTypes {
	public static ItemStackToItemStackRecipeType<CompressingRecipe> COMPRESSING;
	public static BeyondEarthRecipeType<GeneratingRecipe> COAL_GENERATING;
	public static BeyondEarthRecipeType<OxygenLoaderRecipe> OXYGEN_LOADING;
	public static BeyondEarthRecipeType<OxygenBubbleDistributorRecipe> OXYGEN_BUBBLE_DISTRIBUTING;
	public static BeyondEarthRecipeType<WorkbenchingRecipe> NASA_WORKBENCHING;
	public static BeyondEarthRecipeType<FuelRefiningRecipe> FUEL_REFINING;
	public static AlienTradingRecipeType<AlienTradingRecipeItemStack> ALIEN_TRADING_ITEMSTACK;
	public static AlienTradingRecipeType<AlienTradingRecipeEnchantedBook> ALIEN_TRADING_ENCHANTED_BOOK;
	public static AlienTradingRecipeType<AlienTradingRecipeEnchantedItem> ALIEN_TRADING_ENCHANTED_ITEM;
	public static AlienTradingRecipeType<AlienTradingRecipeMap> ALIEN_TRADING_MAP;
	public static AlienTradingRecipeType<AlienTradingRecipePotionedItem> ALIEN_TRADING_POTIONED_ITEM;
	public static AlienTradingRecipeType<AlienTradingRecipeDyedItem> ALIEN_TRADING_DYED_ITEM;
	public static BeyondEarthRecipeType<SpaceStationRecipe> SPACE_STATION;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			COMPRESSING = create(new ItemStackToItemStackRecipeType<>("compressing"));
			COAL_GENERATING = create(new BeyondEarthRecipeType<>("coal_generating"));
			OXYGEN_LOADING = create(new BeyondEarthRecipeType<>("oxygen_loading"));
			OXYGEN_BUBBLE_DISTRIBUTING = create(new BeyondEarthRecipeType<>("oxygen_bubble_distributing"));
			NASA_WORKBENCHING = create(new BeyondEarthRecipeType<>("nasa_workbenching"));
			FUEL_REFINING = create(new BeyondEarthRecipeType<>("fuel_refining"));
			ALIEN_TRADING_ITEMSTACK = create(new AlienTradingRecipeType<>("alien_trading_itemstack"));
			ALIEN_TRADING_ENCHANTED_BOOK = create(new AlienTradingRecipeType<>("alien_trading_enchanted_book"));
			ALIEN_TRADING_ENCHANTED_ITEM = create(new AlienTradingRecipeType<>("alien_trading_enchanted_item"));
			ALIEN_TRADING_MAP = create(new AlienTradingRecipeType<>("alien_trading_map"));
			ALIEN_TRADING_POTIONED_ITEM = create(new AlienTradingRecipeType<>("alien_trading_potioned_item"));
			ALIEN_TRADING_DYED_ITEM = create(new AlienTradingRecipeType<>("alien_trading_dyed_item"));
			SPACE_STATION = create(new BeyondEarthRecipeType<>("space_station"));
		});
	}

	private static <T extends BeyondEarthRecipeType<?>> T create(T value) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(BeyondEarthMod.MODID, value.getName()), value);
		return value;
	}
}
