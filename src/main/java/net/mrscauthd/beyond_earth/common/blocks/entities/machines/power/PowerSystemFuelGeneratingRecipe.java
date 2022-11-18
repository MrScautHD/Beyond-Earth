package net.mrscauthd.beyond_earth.common.blocks.entities.machines.power;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.AbstractMachineBlockEntity;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.GeneratingRecipe;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;

public class PowerSystemFuelGeneratingRecipe extends PowerSystemFuelBurnTime {

    public PowerSystemFuelGeneratingRecipe(AbstractMachineBlockEntity blockEntity, int slot) {
        super(blockEntity, slot);
    }

    public BeyondEarthRecipeType<? extends GeneratingRecipe> getRecipeType() {
        return RecipeTypeRegistry.COAL_GENERATING.get();
    }

    @Override
    protected int getFuelInternal(ItemStack fuel) {
        if (fuel == null || fuel.isEmpty()) {
            return -1;
        }

        GeneratingRecipe recipe = this.getRecipeType().findFirst(this.getBlockEntity().getLevel(), f -> f.test(fuel));
        return recipe != null ? recipe.getBurnTime() : -1;
    }

    @Override
    public ResourceLocation getName() {
        ResourceLocation name = super.getName();
        return new ResourceLocation(name.getNamespace(), name.getPath() + "/generating");
    }

}
