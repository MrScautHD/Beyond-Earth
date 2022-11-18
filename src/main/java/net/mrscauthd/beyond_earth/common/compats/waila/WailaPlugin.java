package net.mrscauthd.beyond_earth.common.compats.waila;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.helper.gauge.GaugeValueRenderer;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueSerializer;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.IGaugeValue;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;

@snownee.jade.api.WailaPlugin
public class WailaPlugin implements IWailaPlugin {

	public static final ResourceLocation DATA_KEY = new ResourceLocation(BeyondEarth.MODID, "waila_datakey");

	public static ListTag write(List<IGaugeValue> list) {
		ListTag nbt = new ListTag();
		list.stream().map(GaugeValueSerializer.Serializer::serialize).forEach(nbt::add);
		return nbt;
	}

	public static List<IGaugeValue> read(ListTag nbt) {
		List<IGaugeValue> list = new ArrayList<>();
		nbt.stream().map(h -> (CompoundTag) h).map(GaugeValueSerializer.Serializer::deserialize).forEach(list::add);
		return list;
	}

	public static ListTag get(CompoundTag compound) {
		return compound.getList(DATA_KEY.toString(), Tag.TAG_COMPOUND);
	}

	public static void put(CompoundTag compound, ListTag nbt) {
		compound.put(DATA_KEY.toString(), nbt);
	}

	public static void appendTooltip(ITooltip tooltip, CompoundTag serverData) {
		ListTag list = WailaPlugin.get(serverData);

		for (int i = 0; i < list.size(); i++) {
			IGaugeValue value = GaugeValueSerializer.Serializer.deserialize(list.getCompound(i));
			tooltip.add(new GaugeValueElement(new GaugeValueRenderer(value)));
		}
	}

	@Override
	public void register(IWailaCommonRegistration registration) {
		registration.registerBlockDataProvider(BlockDataProvider.INSTANCE, BlockEntity.class);
		registration.registerEntityDataProvider(EntityDataProvider.INSTANCE, Entity.class);
	}

	@Override
	public void registerClient(IWailaClientRegistration registration) {
		registration.registerBlockComponent(BlockDataProvider.INSTANCE, Block.class);
		registration.registerEntityComponent(EntityDataProvider.INSTANCE, Entity.class);
	}
}
