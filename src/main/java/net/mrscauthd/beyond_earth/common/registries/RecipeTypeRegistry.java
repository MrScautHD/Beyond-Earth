package net.mrscauthd.beyond_earth.common.registries;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.data.recipes.AlienTradingRecipeDyedItem;
import net.mrscauthd.beyond_earth.common.data.recipes.AlienTradingRecipeEnchantedBook;
import net.mrscauthd.beyond_earth.common.data.recipes.AlienTradingRecipeEnchantedItem;
import net.mrscauthd.beyond_earth.common.data.recipes.AlienTradingRecipeItemStack;
import net.mrscauthd.beyond_earth.common.data.recipes.AlienTradingRecipeMap;
import net.mrscauthd.beyond_earth.common.data.recipes.AlienTradingRecipePotionedItem;
import net.mrscauthd.beyond_earth.common.data.recipes.AlienTradingRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.CompressingRecipe;
import net.mrscauthd.beyond_earth.common.data.recipes.FuelRefiningRecipe;
import net.mrscauthd.beyond_earth.common.data.recipes.GeneratingRecipe;
import net.mrscauthd.beyond_earth.common.data.recipes.ItemStackToItemStackRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.OxygenBubbleDistributorRecipe;
import net.mrscauthd.beyond_earth.common.data.recipes.OxygenLoaderRecipe;
import net.mrscauthd.beyond_earth.common.data.recipes.SpaceStationRecipe;
import net.mrscauthd.beyond_earth.common.data.recipes.WorkbenchingRecipe;

public class RecipeTypeRegistry {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Keys.RECIPE_TYPES,
            BeyondEarth.MODID);

    public static final RegistryObject<ItemStackToItemStackRecipeType<CompressingRecipe>> COMPRESSING;
    public static final RegistryObject<BeyondEarthRecipeType<GeneratingRecipe>> COAL_GENERATING;
    public static final RegistryObject<BeyondEarthRecipeType<OxygenLoaderRecipe>> OXYGEN_LOADING;
    public static final RegistryObject<BeyondEarthRecipeType<OxygenBubbleDistributorRecipe>> OXYGEN_BUBBLE_DISTRIBUTING;
    public static final RegistryObject<BeyondEarthRecipeType<WorkbenchingRecipe>> NASA_WORKBENCHING;
    public static final RegistryObject<BeyondEarthRecipeType<FuelRefiningRecipe>> FUEL_REFINING;
    public static final RegistryObject<AlienTradingRecipeType<AlienTradingRecipeItemStack>> ALIEN_TRADING_ITEMSTACK;
    public static final RegistryObject<AlienTradingRecipeType<AlienTradingRecipeEnchantedBook>> ALIEN_TRADING_ENCHANTED_BOOK;
    public static final RegistryObject<AlienTradingRecipeType<AlienTradingRecipeEnchantedItem>> ALIEN_TRADING_ENCHANTED_ITEM;
    public static final RegistryObject<AlienTradingRecipeType<AlienTradingRecipeMap>> ALIEN_TRADING_MAP;
    public static final RegistryObject<AlienTradingRecipeType<AlienTradingRecipePotionedItem>> ALIEN_TRADING_POTIONED_ITEM;
    public static final RegistryObject<AlienTradingRecipeType<AlienTradingRecipeDyedItem>> ALIEN_TRADING_DYED_ITEM;
    public static final RegistryObject<BeyondEarthRecipeType<SpaceStationRecipe>> SPACE_STATION;

    static {
        COMPRESSING = RECIPE_TYPES.register("compressing",
                () -> new ItemStackToItemStackRecipeType<>("compressing"));
        COAL_GENERATING = RECIPE_TYPES.register("coal_generating",
                () -> new BeyondEarthRecipeType<>("coal_generating"));
        OXYGEN_LOADING = RECIPE_TYPES.register("oxygen_loading",
                () -> new BeyondEarthRecipeType<>("oxygen_loading"));
        OXYGEN_BUBBLE_DISTRIBUTING = RECIPE_TYPES.register("oxygen_bubble_distributing",
                () -> new BeyondEarthRecipeType<>("oxygen_bubble_distributing"));
        NASA_WORKBENCHING = RECIPE_TYPES.register("nasa_workbenching",
                () -> new BeyondEarthRecipeType<>("nasa_workbenching"));
        FUEL_REFINING = RECIPE_TYPES.register("fuel_refining",
                () -> new BeyondEarthRecipeType<>("fuel_refining"));
        ALIEN_TRADING_ITEMSTACK = RECIPE_TYPES.register("alien_trading_itemstack",
                () -> new AlienTradingRecipeType<>("alien_trading_itemstack"));
        ALIEN_TRADING_ENCHANTED_BOOK = RECIPE_TYPES.register("alien_trading_enchanted_book",
                () -> new AlienTradingRecipeType<>("alien_trading_enchanted_book"));
        ALIEN_TRADING_ENCHANTED_ITEM = RECIPE_TYPES.register("alien_trading_enchanted_item",
                () -> new AlienTradingRecipeType<>("alien_trading_enchanted_item"));
        ALIEN_TRADING_MAP = RECIPE_TYPES.register("alien_trading_map",
                () -> new AlienTradingRecipeType<>("alien_trading_map"));
        ALIEN_TRADING_POTIONED_ITEM = RECIPE_TYPES.register("alien_trading_potioned_item",
                () -> new AlienTradingRecipeType<>("alien_trading_potioned_item"));
        ALIEN_TRADING_DYED_ITEM = RECIPE_TYPES.register("alien_trading_dyed_item",
                () -> new AlienTradingRecipeType<>("alien_trading_dyed_item"));
        SPACE_STATION = RECIPE_TYPES.register("space_station",
                () -> new BeyondEarthRecipeType<>("space_station"));
    }
}
