package net.mrscauthd.beyond_earth.jei.jeiguihandlers;

public class CompressorGuiContainerHandler /*implements IGuiContainerHandler<CompressorGuiWindow>*/ {

	/*
	public CompressorGuiContainerHandler() {

	}

	@Override
	public Collection<IGuiClickableArea> getGuiClickableAreas(CompressorGuiWindow containerScreen, double mouseX, double mouseY) {
		return Collections.singleton(new IGuiClickableArea() {
			@Override
			public Rect2i getArea() {
				return GuiHelper.getArrowBounds(CompressorGuiWindow.ARROW_LEFT, CompressorGuiWindow.ARROW_TOP).toRect2i();
			}

			@Override
			public void onClick(IFocusFactory focusFactory, IRecipesGui recipesGui) {
				recipesGui.showTypes(Arrays.asList(CompressorJeiCategory.recipeType));
			}

			@Override
			public List<Component> getTooltipStrings() {
				List<Component> list = new ArrayList<>();
				list.add(GaugeTextHelper.getStorageText(containerScreen.getMenu().getBlockEntity().getCookTimeGaugeValue()).build());
				list.add(new TranslatableComponent("jei.tooltip.show.recipes"));
				return list;
			}
		});
	}*/
}
