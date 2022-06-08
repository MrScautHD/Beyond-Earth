package net.mrscauthd.beyond_earth.jei.jeiguihandlers;

public class RocketGuiContainerHandler /*implements IGuiContainerHandler<RocketGuiWindow>*/ {

    /*
    public RocketGuiContainerHandler() {

    }

    @Override
    public Collection<IGuiClickableArea> getGuiClickableAreas(RocketGuiWindow containerScreen, double mouseX, double mouseY) {
        return Collections.singleton(new IGuiClickableArea() {
            @Override
            public Rect2i getArea() {
                return containerScreen.getFluidBounds().toRect2i();
            }

            @Override
            public void onClick(IFocusFactory focusFactory, IRecipesGui recipesGui) {
                if (containerScreen.getRocket() instanceof RocketTier1Entity) {
                    recipesGui.showTypes(Arrays.asList(JeiPlugin.RocketTier1JeiCategory.recipeType));
                }
                if (containerScreen.getRocket() instanceof RocketTier2Entity) {
                    recipesGui.showTypes(Arrays.asList(JeiPlugin.RocketTier2JeiCategory.recipeType));
                }
                if (containerScreen.getRocket() instanceof RocketTier3Entity) {
                    recipesGui.showTypes(Arrays.asList(JeiPlugin.RocketTier3JeiCategory.recipeType));
                }
                if (containerScreen.getRocket() instanceof RocketTier4Entity) {
                    recipesGui.showTypes(Arrays.asList(JeiPlugin.RocketTier4JeiCategory.recipeType));
                }
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