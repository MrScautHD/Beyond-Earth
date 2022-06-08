package net.mrscauthd.beyond_earth.compats.waila;

public class BlockDataProvider /*implements IServerDataProvider<BlockEntity>, IComponentProvider*/ {
/*
	public static final BlockDataProvider INSTANCE = new BlockDataProvider();

	@Override
	public void appendServerData(CompoundTag data, ServerPlayer player, Level level, BlockEntity blockEntity, boolean b) {

		List<IGaugeValue> list = new ArrayList<>();

		if (blockEntity instanceof IGaugeValuesProvider) {
			((IGaugeValuesProvider) blockEntity).getGaugeValues().forEach(list::add);
		}

		WailaPlugin.put(data, WailaPlugin.write(list));
	}

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
		WailaPlugin.appendTooltip(tooltip, accessor.getServerData());
	}*/
}
