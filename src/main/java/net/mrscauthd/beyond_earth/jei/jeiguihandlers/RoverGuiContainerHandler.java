package net.mrscauthd.beyond_earth.jei.jeiguihandlers;

public class RoverGuiContainerHandler /*implements IGuiContainerHandler<RoverGuiWindow>*/ {

	/*
	public RoverGuiContainerHandler() {

	}

	@Override
	public Collection<IGuiClickableArea> getGuiClickableAreas(RoverGuiWindow containerScreen, double mouseX, double mouseY) {
		return Collections.singleton(new IGuiClickableArea() {
			@Override
			public Rect2i getArea() {
				return containerScreen.getFluidBounds().toRect2i();
			}

			@Override
			public void onClick(IFocusFactory focusFactory, IRecipesGui recipesGui) {
				recipesGui.showTypes(Arrays.asList(RoverJeiCategory.recipeType));
			}

			@Override
			public List<Component> getTooltipStrings() {
				List<Component> list = new ArrayList<>();
				list.add(containerScreen.getFuelGaugeComponent());
				list.add(new TranslatableComponent("jei.tooltip.show.recipes"));
				return list;
			}
		});
	}*/
}
