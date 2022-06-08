package net.mrscauthd.beyond_earth.jei.jeiguihandlers;

public class CoalGeneratorGuiContainerHandler /*implements IGuiContainerHandler<CoalGeneratorGuiWindow> */ {

	/*
	public CoalGeneratorGuiContainerHandler() {

	}

	@Override
	public Collection<IGuiClickableArea> getGuiClickableAreas(CoalGeneratorGuiWindow containerScreen, double mouseX, double mouseY) {
		return Collections.singleton(new IGuiClickableArea() {
			@Override
			public Rect2i getArea() {
				return GuiHelper.getFireBounds(CoalGeneratorGuiWindow.FIRE_LEFT, CoalGeneratorGuiWindow.FIRE_TOP).toRect2i();
			}

			@Override
			public void onClick(IFocusFactory focusFactory, IRecipesGui recipesGui) {
				recipesGui.showTypes(Arrays.asList(CoalGeneratorJeiCategory.recipeType));
			}

			@Override
			public List<Component> getTooltipStrings() {
				List<Component> list = new ArrayList<>();
				list.add(GaugeTextHelper.getStorageText(GaugeValueHelper.getBurnTime(containerScreen.getMenu().getBlockEntity().getPowerSystemGenerating())).build());
				list.add(new TranslatableComponent("jei.tooltip.show.recipes"));
				return list;
			}
		});
	}*/
}