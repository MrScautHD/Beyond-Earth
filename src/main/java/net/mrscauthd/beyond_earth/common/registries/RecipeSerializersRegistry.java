package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.data.recipes.*;

public class RecipeSerializersRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BeyondEarth.MODID);

    /** RECIPES */
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_COMPRESSOR = RECIPE_SERIALIZERS.register("compressor", () -> new CompressingRecipeSerializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_COAL_GENERATOR = RECIPE_SERIALIZERS.register("coal_generator", () -> new GeneratingRecipeSerializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_OXYGEN_LOADER = RECIPE_SERIALIZERS.register("oxygen_loader", () -> new OxygenLoaderRecipeSerializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_OXYGEN_BUBBLE_DISTRIBUTOR = RECIPE_SERIALIZERS.register("oxygen_bubble_distributor", () -> new OxygenBubbleDistributorRecipeSerializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_NASA_WORKBENCH = RECIPE_SERIALIZERS.register("nasa_workbench", () -> new WorkbenchingRecipeSerializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_FUEL_REFINERY = RECIPE_SERIALIZERS.register("fuel_refinery", () -> new FuelRefiningRecipeSerializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_ITEMSTACK = RECIPE_SERIALIZERS.register("alien_trading_itemstack", () -> new AlienTradingRecipeItemStack.Serializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_ENCHANTED_BOOK = RECIPE_SERIALIZERS.register("alien_trading_enchanted_book", () -> new AlienTradingRecipeEnchantedBook.Serializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_ENCHANTED_ITEM = RECIPE_SERIALIZERS.register("alien_trading_enchanted_item", () -> new AlienTradingRecipeEnchantedItem.Serializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_MAP = RECIPE_SERIALIZERS.register("alien_trading_map", () -> new AlienTradingRecipeMap.Serializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_POTIONED_ITEM = RECIPE_SERIALIZERS.register("alien_trading_potioned_item", () -> new AlienTradingRecipePotionedItem.Serializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_ALIEN_TRADING_DYED_ITEM = RECIPE_SERIALIZERS.register("alien_trading_dyed_item", () -> new AlienTradingRecipeDyedItem.Serializer());
    public static final RegistryObject<RecipeSerializer<?>> RECIPE_SERIALIZER_SPACE_STATION = RECIPE_SERIALIZERS.register("space_station", () -> new SpaceStationRecipeSerializer());


}
