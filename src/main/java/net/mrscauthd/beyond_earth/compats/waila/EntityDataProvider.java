package net.mrscauthd.beyond_earth.compats.waila;

public class EntityDataProvider /*implements IServerDataProvider<Entity>, IEntityComponentProvider*/ {
/*
	public static final EntityDataProvider INSTANCE = new EntityDataProvider();

	@Override
	public void appendServerData(CompoundTag data, ServerPlayer player, Level level, Entity entity, boolean b) {

		List<IGaugeValue> list = new ArrayList<>();

		if (entity instanceof IGaugeValuesProvider) {
			((IGaugeValuesProvider) entity).getGaugeValues().forEach(list::add);
		}

		WailaPlugin.put(data, WailaPlugin.write(list));
	}

	@Override
	public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
		WailaPlugin.appendTooltip(tooltip, accessor.getServerData());
	}
*/
}
