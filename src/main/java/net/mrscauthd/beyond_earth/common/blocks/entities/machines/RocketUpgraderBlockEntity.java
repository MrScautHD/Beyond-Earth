package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.RocketUpgraderRecipe;
import net.mrscauthd.beyond_earth.common.menus.RocketUpgraderMenu;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagRegistry;

import java.util.ArrayList;
import java.util.List;

public class RocketUpgraderBlockEntity extends AbstractMachineBlockEntity {
    public static final int ROCKET_INPUT_SLOT = 0;
    public static final int ROCKET_UPGRADE_SLOT = 1;
    public static final int ROCKET_OUTPUT_SLOT = 2;

    private final List<RocketUpgraderRecipe> possibleRecipes;

    public RocketUpgraderBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.ROCKET_UPGRADER_BLOCK_ENTITY.get(), pos, state);

        this.possibleRecipes = new ArrayList<>();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new RocketUpgraderMenu.GuiContainer(id, inventory, this);
    }

    @Override
    protected void tickProcessing() {
        /**if (getItemHandler().getStackInSlot(ROCKET_UPGRADE_SLOT).is(TagRegistry.ROCKET_UPGRADE)) {
            getItemHandler().setStackInSlot(ROCKET_OUTPUT_SLOT, ItemsRegistry.TIER_4_ROCKET_ITEM.get().getDefaultInstance());
        }*/

        this.spawnParticles();
    }

    protected void spawnParticles() {
        if (this.possibleRecipes.size() > 0 && !MenuHelper.isEmpty(this.getItemHandler())) {

            Level level = this.getLevel();

            if (level instanceof ServerLevel serverLevel) {
                BlockPos pos = this.getBlockPos();
                serverLevel.sendParticles(ParticleTypes.CRIT, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D,
                        10, 0.1D, 0.1D, 0.1D, 0.1D);
            }
        }
    }

    @Override
    public void setItem(int p_59616_, ItemStack p_59617_) {
        super.setItem(p_59616_, p_59617_);
    }

    @Override
    protected boolean onCanPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == ROCKET_UPGRADE_SLOT) {
            return isRocketUpgrade(stack);
        }

        return super.onCanPlaceItemThroughFace(index, stack, direction);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (index == ROCKET_UPGRADE_SLOT) {
            return !isRocketUpgrade(stack);
        }

        return super.canTakeItemThroughFace(index, stack, direction);
    }

    public BeyondEarthRecipeType<? extends RocketUpgraderRecipe> getRecipeType() {
        return RecipeTypeRegistry.ROCKET_UPGRADER.get();
    }

    public static boolean isRocketUpgrade(ItemStack stack) {
        return stack.is(TagRegistry.ROCKET_UPGRADE);
    }

    @Override
    public boolean hasSpaceInOutput() {
        return false;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.beyond_earth.rocket_upgrader");
    }
}