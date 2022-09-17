package net.mrscauthd.beyond_earth.datagen.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

/*
        ShapedRecipeBuilder.shaped(ModBlocks.CITRINE_BLOCK.get())
                .define('C', ModItems.CITRINE.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .unlockedBy("has_citrine", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CITRINE.get()).build()))
                .save(pFinishedRecipeConsumer);

        nineBlockStorageRecipes(consumer, ItemsRegistry.STEEL_INGOT.get(), ItemsRegistry.STEEL_BLOCK_ITEM.get());
        nineBlockStorageRecipes(consumer, ItemsRegistry.DESH_INGOT.get(), ItemsRegistry.DESH_BLOCK_ITEM.get());
        nineBlockStorageRecipes(consumer, ItemsRegistry.OSTRUM_INGOT.get(), ItemsRegistry.OSTRUM_BLOCK_ITEM.get());
        nineBlockStorageRecipes(consumer, ItemsRegistry.CALORITE_INGOT.get(), ItemsRegistry.CALORITE_BLOCK_ITEM.get());**/
    }
}
