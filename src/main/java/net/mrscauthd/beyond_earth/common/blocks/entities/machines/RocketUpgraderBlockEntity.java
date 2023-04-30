package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrscauthd.beyond_earth.common.blocks.machines.RocketUpgraderBlock;
import net.mrscauthd.beyond_earth.common.data.recipes.BeyondEarthRecipeType;
import net.mrscauthd.beyond_earth.common.data.recipes.RocketPart;
import net.mrscauthd.beyond_earth.common.data.recipes.WorkbenchingRecipe;
import net.mrscauthd.beyond_earth.common.items.Tier4RocketItem;
import net.mrscauthd.beyond_earth.common.menus.RocketUpgraderMenu;
import net.mrscauthd.beyond_earth.common.menus.helper.MenuHelper;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.NasaWorkbenchMenu;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.RocketPartsItemHandler;
import net.mrscauthd.beyond_earth.common.menus.nasaworkbench.StackCacher;
import net.mrscauthd.beyond_earth.common.registries.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RocketUpgraderBlockEntity extends AbstractMachineBlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    public RocketUpgraderBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.ROCKET_UPGRADER_BLOCK_ENTITY.get(), pos, state);
    }

    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new RocketUpgraderMenu.GuiContainer(id, inventory, this);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("upgrader.inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("upgrader.inventory"));
    }

    @Override
    protected void tickProcessing() {
        if (itemHandler.getStackInSlot(1).is(TagRegistry.ROCKET_UPGRADE)) {
            itemHandler.setStackInSlot(2, ItemsRegistry.TIER_4_ROCKET_ITEM.get().getDefaultInstance());
        }
    }

    @Override
    public boolean hasSpaceInOutput() {
        return false;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.beyond_earth.rocket_upgrader");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new RocketUpgraderMenu.GuiContainer(id, inventory, this);
    }
}