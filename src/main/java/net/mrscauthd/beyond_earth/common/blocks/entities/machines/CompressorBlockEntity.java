package net.mrscauthd.beyond_earth.common.blocks.entities.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.IEnergyStorage;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.NamedComponentRegistry;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemEnergyCommon;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.power.PowerSystemRegistry;
import net.mrscauthd.beyond_earth.common.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.data.recipes.ItemStackToItemStackRecipeType;
import net.mrscauthd.beyond_earth.common.menus.CompressorMenu;
import net.mrscauthd.beyond_earth.common.registries.BlockEntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.RecipeTypeRegistry;

public class CompressorBlockEntity extends ItemStackToItemStackBlockEntity {

        public static final int DEFAULT_ENERGY_USAGE = 1;

        public CompressorBlockEntity(BlockPos pos, BlockState state) {
                super(BlockEntityRegistry.COMPRESSOR_BLOCK_ENTITY.get(), pos, state);
        }

        @Override
        public AbstractContainerMenu createMenu(int id, Inventory inventory) {
                return new CompressorMenu.GuiContainer(id, inventory, this);
        }

        @Override
        public ItemStackToItemStackRecipeType<?> getRecipeType() {
                return RecipeTypeRegistry.COMPRESSING.get();
        }

        @Override
        protected void createEnergyStorages(NamedComponentRegistry<IEnergyStorage> registry) {
                super.createEnergyStorages(registry);
                int capacity = Config.COMPRESSOR_ENERGY_CAPACITY.get();
                int maxTransfer = Config.COMPRESSOR_ENERGY_TRANSFER.get();
                registry.put(new EnergyStorageBasic(this, capacity, maxTransfer, capacity));
        }

        @Override
        protected void createPowerSystems(PowerSystemRegistry map) {
                super.createPowerSystems(map);
                map.put(new PowerSystemEnergyCommon(this) {
                        @Override
                        public int getBasePowerForOperation() {
                                return CompressorBlockEntity.this.getBasePowerForOperation();
                        }
                });
        }

        public int getBasePowerForOperation() {
                return Config.COMPRESSOR_ENERGY_USAGE.get();
        }
}