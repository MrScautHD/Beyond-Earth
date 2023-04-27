package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.RocketPart;
import net.mrscauthd.beyond_earth.common.data.recipes.WorkbenchingRecipe;
import net.mrscauthd.beyond_earth.common.menus.RocketUpgraderMenu;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.NasaWorkbenchMenu;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.RocketPartsItemHandler;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.StackCacher;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;
import net.mrscauthd.beyond_earth.common.registries.RocketPartsRegistry;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RocketUpgraderBlockEntity extends AbstractMachineBlockEntity {
    public RocketUpgraderBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.ROCKET_UPGRADER_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new RocketUpgraderMenu.GuiContainer(id, inventory, this);
    }

    @Override
    protected void tickProcessing() {

    }

    @Override
    public boolean hasSpaceInOutput() {
        return false;
    }
}