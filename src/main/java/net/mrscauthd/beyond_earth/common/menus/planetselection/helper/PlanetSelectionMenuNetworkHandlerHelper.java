package net.mrscauthd.beyond_earth.common.menus.planetselection.helper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.mrscauthd.beyond_earth.common.data.recipes.IngredientStack;
import net.mrscauthd.beyond_earth.common.data.recipes.SpaceStationRecipe;

public abstract class PlanetSelectionMenuNetworkHandlerHelper {
    /** SET EVERYTHING BACK AS BEFORE THE SCREEN OPEN */
    public void defaultOptions(Player player) {
        if (!player.level().isClientSide) {
            player.setNoGravity(false);
        }

        player.closeContainer();
    }

    /** DELETE ITEMS FOR THE SPACE STATION */
    public void deleteItems(Player player) {
        if (player.getAbilities().instabuild || player.isSpectator()) {
            return;
        }

        Inventory inv = player.getInventory();
        SpaceStationRecipe recipe = (SpaceStationRecipe) player.level().getRecipeManager().byKey(SpaceStationRecipe.KEY).orElse(null);

        for (IngredientStack ingredientStack : recipe.getIngredientStacks()) {
            inv.clearOrCountMatchingItems(ingredientStack::testWithoutCount, ingredientStack.getCount(), inv);
        }
    }
}
