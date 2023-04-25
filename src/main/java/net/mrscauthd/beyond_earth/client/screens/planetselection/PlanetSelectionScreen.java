package net.mrscauthd.beyond_earth.client.screens.planetselection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetSelectionScreenBackgroundRenderEvent;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetSelectionScreenButtonVisibilityEvent;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetSelectionScreenInitEvent;
import net.mrscauthd.beyond_earth.client.events.forge.PlanetSelectionScreenRenderEvent;
import net.mrscauthd.beyond_earth.client.screens.buttons.ModifiedButton;
import net.mrscauthd.beyond_earth.client.screens.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.client.screens.planetselection.helper.CategoryHelper;
import net.mrscauthd.beyond_earth.client.screens.planetselection.helper.PlanetSelectionScreenHelper;
import net.mrscauthd.beyond_earth.common.menus.planetselection.PlanetSelectionMenu;
import net.mrscauthd.beyond_earth.common.registries.NetworkRegistry;
import net.mrscauthd.beyond_earth.common.util.Planets;
import net.mrscauthd.beyond_earth.common.util.Planets.Planet;
import net.mrscauthd.beyond_earth.common.util.Planets.StarSystem;

@OnlyIn(Dist.CLIENT)
public class PlanetSelectionScreen extends Screen implements MenuAccess<PlanetSelectionMenu.GuiContainer> {

    /** TEXTURES */
    public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/planet_selection.png");

    public static final ResourceLocation SCROLLER_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/scroller.png");

    public static final ResourceLocation SMALL_BUTTON_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/buttons/small_button.png");
    public static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/buttons/button.png");
    public static final ResourceLocation LARGE_BUTTON_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/buttons/large_button.png");

    public static final ResourceLocation MILKY_WAY_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/milky_way.png");

    public static final ResourceLocation SMALL_MENU_LIST = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/planet_menu.png");
    public static final ResourceLocation LARGE_MENU_TEXTURE = new ResourceLocation(BeyondEarth.MODID,
            "textures/gui/util/large_planet_menu.png");

    /** TEXT */
    public static final Component CATALOG_TEXT = PlanetSelectionScreenHelper.tl("catalog");
    public static final Component BACK_TEXT = PlanetSelectionScreenHelper.tl("back");

    public static final Component SOLAR_SYSTEM_TEXT = PlanetSelectionScreenHelper.tl("solar_system");

    public static final Component PLANET_TEXT = PlanetSelectionScreenHelper.tl("planet");
    public static final Component MOON_TEXT = PlanetSelectionScreenHelper.tl("moon");

    public static final Component ORBIT_TEXT = PlanetSelectionScreenHelper.tl("orbit");

    public static final Component NO_GRAVITY_TEXT = PlanetSelectionScreenHelper.tl("no_gravity");

    public static final Component SPACE_STATION_TEXT = PlanetSelectionScreenHelper.tl("space_station");

    public static final Component CATEGORY_TEXT = PlanetSelectionScreenHelper.tl("category");
    public static final Component PROVIDED_TEXT = PlanetSelectionScreenHelper.tl("provided");
    public static final Component TYPE_TEXT = PlanetSelectionScreenHelper.tl("type");
    public static final Component GRAVITY_TEXT = PlanetSelectionScreenHelper.tl("gravity");
    public static final Component OXYGEN_TEXT = PlanetSelectionScreenHelper.tl("oxygen");
    public static final Component TEMPERATURE_TEXT = PlanetSelectionScreenHelper.tl("temperature");
    public static final Component OXYGEN_TRUE_TEXT = PlanetSelectionScreenHelper.tl("oxygen.true");
    public static final Component OXYGEN_FALSE_TEXT = PlanetSelectionScreenHelper.tl("oxygen.false");
    public static final Component ITEM_REQUIREMENT_TEXT = PlanetSelectionScreenHelper.tl("item_requirement");

    public static final Component ROCKET_TIER_1_TEXT = Component
            .translatable("entity." + BeyondEarth.MODID + ".rocket_t" + 1);
    public static final Component ROCKET_TIER_2_TEXT = Component
            .translatable("entity." + BeyondEarth.MODID + ".rocket_t" + 2);
    public static final Component ROCKET_TIER_3_TEXT = Component
            .translatable("entity." + BeyondEarth.MODID + ".rocket_t" + 3);
    public static final Component ROCKET_TIER_4_TEXT = Component
            .translatable("entity." + BeyondEarth.MODID + ".rocket_t" + 4);

    public static final float MOON_ORBIT_SPEED = 2.5f;
    public static final float PLANET_ORBIT_SPEED = 2.5f;

    /** MENU */
    private final PlanetSelectionMenu.GuiContainer menu;

    /** CATEGORY */
    public CategoryHelper category; // IF YOU DO A ADDON MOD SET THIS CATEGORY TO -1 AND CREATE A OWN WITH
                                    // "AbstractCategoryHelper"
    // Index of current star
    public CategoryHelper starIndex = new CategoryHelper();

    /** BUTTON LISTS */
    public List<ModifiedButton> visibleButtons;

    /** ROTATIONS */
    public float rotationMilkyWay;
    public float planetTimer;

    /** BACK BUTTONS */
    public ModifiedButton backButton;

    /** SPACE STATION RECIPE SYSTEM */
    // public SpaceStationRecipe recipe;
    public boolean spaceStationItemList;

    /** SCROLL SYSTEM */
    public int scrollIndex;

    /** BUTTON ROW END */
    public int rowEnd;

    public PlanetSelectionScreen(PlanetSelectionMenu.GuiContainer menu, Inventory inventory, Component p_96550_) {
        super(p_96550_);
        this.menu = menu;
        this.starIndex.set(0);
    }

    @Override
    public PlanetSelectionMenu.GuiContainer getMenu() {
        return menu;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBg(poseStack, partialTicks, mouseX, mouseY);
        super.render(poseStack, mouseX, mouseY, partialTicks);

        /** RENDER PRE EVENT FOR ADDONS */
        if (MinecraftForge.EVENT_BUS
                .post(new PlanetSelectionScreenRenderEvent.Pre(this, poseStack, partialTicks, mouseX, mouseY))) {
            return;
        }

        /** CATALOG TEXT RENDERER */
        this.font.draw(poseStack, CATALOG_TEXT, 24, (this.height / 2) - 143 / 2, -1);

        /** RENDER POST EVENT FOR ADDONS */
        MinecraftForge.EVENT_BUS
                .post(new PlanetSelectionScreenRenderEvent.Post(this, poseStack, partialTicks, mouseX, mouseY));
    }

    public StarSystem getStar() {
        return Planets.getStarsList().get(starIndex.get());
    }

    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {

        /** RENDER BACKGROUND PRE EVENT FOR ADDONS */
        if (MinecraftForge.EVENT_BUS.post(
                new PlanetSelectionScreenBackgroundRenderEvent.Pre(this, poseStack, partialTicks, mouseX, mouseY))) {
            return;
        }

        /** BACKGROUND RENDERER */
        ScreenHelper.drawTexture(poseStack, 0, 0, this.width, this.height, BACKGROUND_TEXTURE, false);

        ResourceLocation starTexture = getStar().texture;

        /** SUN RENDERER */
        if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 1, 7)) {
            ScreenHelper.drawTexture(poseStack, (this.width - 15) / 2, (this.height - 15) / 2, 15, 15, starTexture,
                    false);
        }

        /** OBJECT ROTATIONS */
        this.rotationCalculator(partialTicks);

        /** RINGS */
        this.drawRings();

        /** ROTATED OBJECTS RENDERER */
        this.drawPlanets(poseStack);

        /** SMALL MENU RENDERER */
        if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 0, 1)
                || PlanetSelectionScreenHelper.categoryRange(this.category.get(), 6, 6)) {
            ScreenHelper.drawTexture(poseStack, 0, (this.height / 2) - 177 / 2, 105, 177, SMALL_MENU_LIST, true);
            this.drawScroller(poseStack, 92);
        }

        /** LARGE MENU RENDERER */
        if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 2, 5)
                || PlanetSelectionScreenHelper.categoryRange(this.category.get(), 7, 7)) {
            ScreenHelper.drawTexture(poseStack, 0, (this.height / 2) - 177 / 2, 215, 177, LARGE_MENU_TEXTURE, true);
            this.drawScroller(poseStack, 210);
        }

        /** RENDER BACKGROUND POST EVENT FOR ADDONS */
        MinecraftForge.EVENT_BUS.post(
                new PlanetSelectionScreenBackgroundRenderEvent.Post(this, poseStack, partialTicks, mouseX, mouseY));
    }

    @Override
    protected void init() {
        super.init();

        /** INIT PRE EVENT FOR ADDONS */
        if (MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenInitEvent.Pre(this))) {
            return;
        }

        /** ROW END */
        this.rowEnd = 5;

        /** SET CATEGORY */
        this.category = new CategoryHelper();

        /** SET PLANET ROTATIONS */
        this.rotationMilkyWay = 0;
        this.planetTimer = 0;

        /** SET SCROLL */
        this.scrollIndex = 0;

        /** SPACE STATION RECIPE SYSTEM */
        this.spaceStationItemList = true;

        /** SET BUTTON LISTS */
        this.visibleButtons = Lists.newArrayList();

        /** BACK BUTTON */
        backButton = PlanetSelectionScreenHelper.addBackButton(this, 10, 1, 70, 20, false, BUTTON_TEXTURE,
                ModifiedButton.ColorTypes.BLUE, BACK_TEXT, (onPress) -> {
                    if (this.category.get() == 1) {
                        this.category.set(0);
                        this.scrollIndex = 0;
                        this.updateButtonVisibility();
                    } else if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 2, 5)) {
                        this.category.set(1);
                        this.scrollIndex = 0;
                        this.updateButtonVisibility();
                    } else if (this.category.get() == 6) {
                        this.category.set(0);
                        this.scrollIndex = 0;
                        this.updateButtonVisibility();
                    } else if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), 7, 7)) {
                        this.category.set(6);
                        this.scrollIndex = 0;
                        this.updateButtonVisibility();
                    }
                });
        backButton.isVisible = i -> PlanetSelectionScreenHelper.categoryRange(i, 1, 5)
                || PlanetSelectionScreenHelper.categoryRange(i, 6, 7);

        /** CATEGORY 1 */
        AtomicInteger intholder = new AtomicInteger(1);

        for (StarSystem system : Planets.getStarsList()) {

            if (system.description == null) {
                system.description = PlanetSelectionScreenHelper.tl("solar_system_" + system.name);
            }
            /** MAIN CATEGORY BUTTON FOR STAR */
            ModifiedButton starButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10, 1, 70,
                    20, intholder.get(), true, false, ModifiedButton.ButtonTypes.MILKY_WAY_CATEGORY,
                    List.of(system.description.getString()), BUTTON_TEXTURE, ModifiedButton.ColorTypes.LIGHT_BLUE,
                    system.description);

            // category 0 for stars.
            starButton.isVisible = i -> i == 0;
            int starCategory = intholder.getAndIncrement();
            system.planets.forEach(p -> {
                int planetCategory = intholder.getAndIncrement();
                addPlanetButtons(null, p, planetCategory, starCategory);
            });
        }
        /** INIT POST EVENT FOR ADDONS */
        MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenInitEvent.Post(this));

        /** UPDATE BUTTON VISIBILITY */
        this.updateButtonVisibility();
    }

    public void addPlanetButtons(Planet parent, Planet p, int planetCategory, int starCategory) {
        if (p.description == null) {
            p.description = PlanetSelectionScreenHelper.tl(p.name);
        }

        // Planet button
        if (parent == null) {
            Component tierText = Component.translatable("entity." + BeyondEarth.MODID + ".rocket_t" + p.tier);
            ModifiedButton planetCategoryButton = PlanetSelectionScreenHelper.addCategoryButton(this, this.category, 10,
                    1, 70, 20, planetCategory, this.checkTier(p.tier), false,
                    ModifiedButton.ButtonTypes.SOLAR_SYSTEM_CATEGORY,
                    List.of(p.description.getString(), tierText.getString()), BUTTON_TEXTURE,
                    ModifiedButton.ColorTypes.GREEN, p.description);
            planetCategoryButton.isVisible = i -> i == starCategory;
        }

        // Teleport buttons
        List<String> buttonText = Lists.newArrayList();
        buttonText.add(p.description.getString());
        buttonText.add(String.format("%.3f m/s²", p.g * 9.807));
        buttonText.add(p.hasOxygen ? "a" + OXYGEN_TRUE_TEXT.getString() : "c" + OXYGEN_FALSE_TEXT.getString());

        boolean niceT = p.temperature < 100 && p.temperature > -100;
        buttonText.add(niceT ? "a" + p.temperature : "c" + p.temperature);

        ModifiedButton planetButton = PlanetSelectionScreenHelper.addHandlerButton(this, 10, 1, 70, 20, true, false,
                true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(p.planetID),
                ModifiedButton.ButtonTypes.PLANET_CATEGORY, buttonText, BUTTON_TEXTURE,
                ModifiedButton.ColorTypes.LIGHT_BLUE, p.description);
        planetButton.isVisible = i -> i == planetCategory;

        // Orbit teleport buttons
        buttonText = List.of(ORBIT_TEXT.getString(), NO_GRAVITY_TEXT.getString(), "c" + OXYGEN_FALSE_TEXT.getString(),
                "c" + "-270");

        ModifiedButton orbitButton = PlanetSelectionScreenHelper.addHandlerButton(this, 84, 2, 37, 20, true, false,
                true, NetworkRegistry.PACKET_HANDLER, PlanetSelectionScreenHelper.getNetworkHandler(p.orbitID),
                ModifiedButton.ButtonTypes.PLANET_CATEGORY, buttonText, SMALL_BUTTON_TEXTURE,
                ModifiedButton.ColorTypes.LIGHT_BLUE, ORBIT_TEXT);
        orbitButton.isVisible = i -> i == planetCategory;

        // Space station teleport buttons
        ModifiedButton stationButton = PlanetSelectionScreenHelper.addHandlerButton(this, 125, 3, 75, 20,
                this.spaceStationItemList, false, true, NetworkRegistry.PACKET_HANDLER,
                PlanetSelectionScreenHelper.getNetworkHandler(p.stationID),
                ModifiedButton.ButtonTypes.PLANET_SPACE_STATION_CATEGORY, buttonText, LARGE_BUTTON_TEXTURE,
                ModifiedButton.ColorTypes.GREEN, SPACE_STATION_TEXT);
        stationButton.isVisible = i -> i == planetCategory;

        p.moons.forEach(p2 -> {
            addPlanetButtons(p, p2, planetCategory, starCategory);
        });
    }

    @Override
    public void onClose() {
        this.menu.getPlayer().closeContainer();
        super.onClose();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseScrolled(double p_99314_, double p_99315_, double p_99316_) {
        if (this.getVisibleButtons(1).size() > this.rowEnd) {
            if (p_99316_ == 1) {
                if (this.scrollIndex != 0) {
                    this.scrollIndex = this.scrollIndex + 1;
                    this.updateButtonVisibility();
                    return true;
                }
            } else {
                if (this.scrollIndex != -(this.getVisibleButtons(1).size() - this.rowEnd)) {
                    this.scrollIndex = this.scrollIndex - 1;
                    this.updateButtonVisibility();
                    return true;
                }
            }
        }

        return false;
    }

    public void updateButtonVisibility() {

        /** BUTTON VISIBILITY PRE EVENT FOR ADDONS */
        if (MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenButtonVisibilityEvent.Pre(this))) {
            return;
        }

        this.visibleButtons.clear();

        // Each button should say if it is visible in the given category, if so, this
        // will enable the button. Addons should be using -1 for their category checks,
        // so this should never be true for them.
        this.children().forEach(s -> {
            if (s instanceof ModifiedButton button) {
                this.visibleButton(button, button.isVisible.test(this.category.get()));
            }
        });

        /** BUTTON VISIBILITY POST EVENT FOR ADDONS */
        MinecraftForge.EVENT_BUS.post(new PlanetSelectionScreenButtonVisibilityEvent.Post(this));
    }

    public void rotationCalculator(float partialTicks) {

        /** ROTATE THE GALAXY DISPLAY */
        this.rotationMilkyWay = (this.rotationMilkyWay + partialTicks * 0.4f);

        /** ROTATE THE PLANETS/MOONS */
        this.planetTimer += partialTicks;
        for (StarSystem system : Planets.getStarsList()) {

            float minPeriod = Float.MAX_VALUE;
            for (var planet : system.planets) {
                minPeriod = (float) Math.min(minPeriod, planet.orbitT);
            }
            float planetRotationSpeedScale = PLANET_ORBIT_SPEED / minPeriod;
            system.planets.forEach(planet -> {
                planet.rotation = Planets.getRotation(planet, planetTimer, planetRotationSpeedScale);

                float minMoonPeriod = Float.MAX_VALUE;
                for (Planet moon : planet.moons) {
                    minMoonPeriod = (float) Math.min(minMoonPeriod, moon.orbitT);
                }
                float moonRotationSpeedScale = MOON_ORBIT_SPEED / minMoonPeriod;
                for (Planet moon : planet.moons) {
                    moon.rotation = Planets.getRotation(moon, planetTimer, moonRotationSpeedScale);
                }
            });
        }
    }

    public void drawRings() {
        int start = 1;
        int x = this.width / 2;
        int y = this.height / 2;

        for (StarSystem system : Planets.getStarsList()) {
            int end = start + system.planets.size();
            if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), start, end)) {

                float maxR = 0;
                for (Planet planet : system.planets) {
                    maxR = (float) Math.max(maxR, planet.orbitRadius);
                }
                float rScale = Math.min(1.15f / maxR, 1);
                system.planets.forEach(planet -> {
                    drawPlanetRing(planet, rScale, x, y, 0, 0, 10, 10);
                });
            }
            start += end;
        }
    }

    private void drawPlanetRing(Planet planet, float ringScale, float x, float y, float dx, float dy, float width,
            float height) {
        if (planet.description == null) {
            planet.description = PlanetSelectionScreenHelper.tl(planet.name);
        }
        float distance = (float) (90 * planet.orbitRadius * ringScale);
        float rotation = planet.rotation;
        float sinTick = (float) Math.sin(rotation);
        float cosTick = (float) Math.cos(rotation);

        float x0 = ((this.width / 2) - width / 2);
        float y0 = ((this.height / 2) - height / 2);

        planet._xPos = x0 + sinTick * distance + dx;
        planet._yPos = y0 + cosTick * distance + dy;

        float dxm = sinTick * distance;
        float dym = cosTick * distance;

        boolean isMoon = planet._parent instanceof Planet;

        Vec3 colour = new Vec3(planet.orbitColour[0], planet.orbitColour[1], planet.orbitColour[2]);
        PlanetSelectionScreenHelper.drawCircle(x, y, distance, 180, isMoon ? 0.5f : 1, colour);

        // Find a reasonable ring scale
        float maxR = 0;
        for (Planet moon : planet.moons) {
            maxR = (float) Math.max(maxR, moon.orbitRadius);
        }
        maxR *= 5;
        float newScale = Math.min(1.15f / maxR, 1);

        planet.moons.forEach(moon -> {
            drawPlanetRing(moon, newScale, planet._xPos + width / 2, planet._yPos + width / 2, dxm, dym, width / 2,
                    height / 2);
        });
    }

    public void drawPlanets(PoseStack poseStack) {

        /** SOLAR SYSTEM CATEGORY */
        if (this.category.get() == 0) {
            PlanetSelectionScreenHelper.drawGalaxy(this, poseStack, MILKY_WAY_TEXTURE, -125, -125, 250, 250,
                    this.rotationMilkyWay);
        }
        int start = 1;
        for (StarSystem system : Planets.getStarsList()) {
            int end = start + system.planets.size();
            if (PlanetSelectionScreenHelper.categoryRange(this.category.get(), start, end)) {
                system.planets.forEach(planet -> {
                    drawPlanet(poseStack, planet, 10, 10, true);
                });
            }
            start += end;
        }
    }

    private void drawPlanet(PoseStack poseStack, Planet planet, int height, int width, boolean showName) {
        if (height / 2 > 0)
            planet.moons.forEach(moon -> drawPlanet(poseStack, moon, height / 2, width / 2, false));
        if (planet.description == null) {
            planet.description = PlanetSelectionScreenHelper.tl(planet.name);
        }
        if (planet.texture == null) {
            planet.texture = new ResourceLocation("missing_planet_texture_" + planet.name);
        }
        PlanetSelectionScreenHelper.drawPlanet(poseStack, planet, height, width, showName);
    }

    public void drawScroller(PoseStack poseStack, int x) {
        if (this.getVisibleButtons(1).size() > this.rowEnd) {

            int buttonStartY = (this.height / 2) - 67 / 2;
            int scrollSize = this.getVisibleButtons(1).size() - this.rowEnd;

            float y = buttonStartY + ((97.0F / scrollSize) * -this.scrollIndex);

            ScreenHelper.drawTexture(poseStack, x, (int) y, 4, 8, SCROLLER_TEXTURE, false);
        }
    }

    public void handleButtonPos(int rowStart, int rowEnd) {
        /** SET POS OF VISIBLE BUTTONS */
        for (int f1 = rowStart; f1 <= rowEnd; f1++) {
            for (int f2 = 0; f2 < this.getVisibleButtons(f1).size(); f2++) {
                ModifiedButton button = this.getVisibleButtons(f1).get(f2);

                int buttonStartY = (this.height / 2) - 68 / 2;

                int extraPos = 0;

                if (f1 >= 2 && rowEnd <= 3) {
                    extraPos = 1;
                }

                int y = buttonStartY + (22 * (f2 + extraPos + this.scrollIndex));

                if (button.getX() != y) {
                    button.setPosition(button.getX(), y);
                }
            }
        }
    }

    public boolean buttonScrollVisibility(ModifiedButton button) {
        int buttonStartY = (this.height / 2) - 68 / 2;
        int buttonEndY = buttonStartY + 22 * this.rowEnd;

        /** IF BUTTON ABOVE THE MENU */
        if (button.getY() < buttonStartY && button.row != 0) {
            return false;
        }

        /** IF BUTTON UNDER THE MENU */
        return button.getY() < buttonEndY || button.row == 0;
    }

    public List<ModifiedButton> getVisibleButtons(int row) {
        /** VISIBLE BUTTON LIST */
        List<ModifiedButton> listVisible = new ArrayList<>();

        /** ADD VISIBLE BUTTONS TO THE LIST */
        for (int f1 = 0; f1 < this.visibleButtons.size(); f1++) {
            ModifiedButton button = this.visibleButtons.get(f1);

            if (button.row == row) {
                listVisible.add(button);
            }
        }
        return listVisible;
    }

    public void visibleButton(ModifiedButton button, Boolean condition) {
        /** HANDLE VISIBLE BUTTON LIST */
        if (condition && !visibleButtons.contains(button)) {
            visibleButtons.add(button);
        }

        /** POS HANDLER */
        this.handleButtonPos(1, 3);

        /** BUTTON VISIBILITY */
        button.visible = condition && this.buttonScrollVisibility(button);
    }

    public boolean getSpaceStationItemCheck(/* IngredientStack ingredientStack */) {
        /*
         * Player player = this.menu.player;
         * 
         * if (player.getAbilities().instabuild || player.isSpectator()) { return true;
         * }
         * 
         * Inventory inv = player.getInventory(); int itemStackCount = 0;
         * 
         * for (int i = 0; i < inv.getContainerSize(); ++i) { ItemStack itemStack =
         * inv.getItem(i);
         * 
         * if (ingredientStack.testWithoutCount(itemStack)) { itemStackCount +=
         * itemStack.getCount(); } }
         */

        return true; // itemStackCount >= ingredientStack.getCount();
    }

    public boolean checkTier(int tier) {
        return this.menu.getTier() >= tier;
    }

    /**
     * USE THIS METHOD TO ADD A OWN BUTTON SYSTEM (YOU SHOULD USE THE
     * PlanetGuiHelper ONE, IF YOU NEED NOT A OWN SYSTEM)
     */
    public ModifiedButton addButton(int x, int y, int row, int width, int height, boolean rocketCondition,
            ModifiedButton.ButtonTypes type, List<String> list, ResourceLocation buttonTexture,
            ModifiedButton.ColorTypes colorType, Component title, Button.OnPress onPress) {
        ModifiedButton button = this.addRenderableWidget(new ModifiedButton(x, y, row, width, height, 0, 0, 0,
                rocketCondition, type, list, buttonTexture, colorType, width, height, onPress, title));
        return button;
    }
}
