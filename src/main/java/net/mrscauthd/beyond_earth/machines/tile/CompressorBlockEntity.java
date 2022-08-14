package net.mrscauthd.beyond_earth.machines.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.energy.IEnergyStorage;
import net.mrscauthd.beyond_earth.capabilities.energy.EnergyStorageBasic;
import net.mrscauthd.beyond_earth.config.Config;
import net.mrscauthd.beyond_earth.crafting.BeyondEarthRecipeTypes;
import net.mrscauthd.beyond_earth.crafting.ItemStackToItemStackRecipeType;
import net.mrscauthd.beyond_earth.guis.screens.compressor.CompressorGui;
import net.mrscauthd.beyond_earth.registries.BlockEntitiesRegistry;

public class CompressorBlockEntity extends ItemStackToItemStackBlockEntity {

	public static final int DEFAULT_ENERGY_USAGE = 1;

	public CompressorBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntitiesRegistry.COMPRESSOR_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new CompressorGui.GuiContainer(id, inventory, this);
	}

	@Override
	public ItemStackToItemStackRecipeType<?> getRecipeType() {
		return BeyondEarthRecipeTypes.COMPRESSING;
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