package net.mrscauthd.beyond_earth.guis.screens.planetselection;

import com.mojang.blaze3d.vertex.PoseStack;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.crafting.IngredientStack;
import net.mrscauthd.beyond_earth.crafting.SpaceStationRecipe;
import net.mrscauthd.beyond_earth.guis.helper.ImageButtonPlacer;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.PlanetSelectionGuiHelper;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PlanetSelectionGuiWindow extends AbstractContainerScreen<PlanetSelectionGui.GuiContainer> {

	/** TEXTURES */
	public static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/screens/planet_selection.png");

	public static final ResourceLocation GREEN_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/green_button.png");
	public static final ResourceLocation GREEN_LIGHT_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/green_button_2.png");

	public static final ResourceLocation RED_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/red_button.png");
	public static final ResourceLocation RED_LIGHT_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/red_button_2.png");

	public static final ResourceLocation DARK_BLUE_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/dark_blue_button.png");
	public static final ResourceLocation DARK_BLUE_LIGHT_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/dark_blue_button_2.png");

	public static final ResourceLocation BLUE_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/blue_button.png");
	public static final ResourceLocation BLUE_LIGHT_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/blue_button_2.png");

	public static final ResourceLocation SMALL_BLUE_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/small_blue_button.png");
	public static final ResourceLocation SMALL_BLUE_LIGHT_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/small_blue_button_2.png");

	public static final ResourceLocation LARGE_GREEN_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/big_green_button.png");
	public static final ResourceLocation LARGE_GREEN_LIGHT_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/big_green_button_2.png");

	public static final ResourceLocation LARGE_RED_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/big_red_button.png");
	public static final ResourceLocation LARGE_RED_LIGHT_BUTTON_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/big_red_button_2.png");

	public static final ResourceLocation MILKY_WAY_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/milky_way.png");

	public static final ResourceLocation SUN_SOLAR_SYSTEM_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/solar_system.png");
	public static final ResourceLocation PROXIMA_CENTAURI_SOLAR_SYSTEM_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/proxima_centauri.png");

	public static final ResourceLocation SUN_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/sun.png");
	public static final ResourceLocation BLUE_SUN_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/blue_sun.png");
	public static final ResourceLocation MARS_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/mars.png");
	public static final ResourceLocation EARTH_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/earth.png");
	public static final ResourceLocation VENUS_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/venus.png");
	public static final ResourceLocation MERCURY_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/mercury.png");
	public static final ResourceLocation GLACIO_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/glacio.png");

	public static final ResourceLocation SMALL_MENU_LIST = new ResourceLocation(BeyondEarthMod.MODID, "textures/rocket_menu_list.png");
	public static final ResourceLocation LARGE_MENU_TEXTURE = new ResourceLocation(BeyondEarthMod.MODID, "textures/rocket_menu_list_2.png");

	/** TEXT */
	public static final Component CATALOG_TEXT = PlanetSelectionGuiHelper.tl("catalog");
	public static final Component BACK_TEXT = PlanetSelectionGuiHelper.tl("back");

	public static final Component SUN_TEXT = PlanetSelectionGuiHelper.tl("sun");
	public static final Component PROXIMA_CENTAURI_TEXT = PlanetSelectionGuiHelper.tl("proxima_centauri");

	public static final Component SOLAR_SYSTEM_TEXT = PlanetSelectionGuiHelper.tl("solar_system");

	public static final Component SOLAR_SYSTEM_SUN_TEXT = PlanetSelectionGuiHelper.tl("solar_system_sun");
	public static final Component SOLAR_SYSTEM_PROXIMA_CENTAURI_TEXT = PlanetSelectionGuiHelper.tl("solar_system_proxima_centauri");

	public static final Component EARTH_TEXT = PlanetSelectionGuiHelper.tl("earth");
	public static final Component MARS_TEXT = PlanetSelectionGuiHelper.tl("mars");
	public static final Component MERCURY_TEXT = PlanetSelectionGuiHelper.tl("mercury");
	public static final Component VENUS_TEXT = PlanetSelectionGuiHelper.tl("venus");
	public static final Component GLACIO_TEXT = PlanetSelectionGuiHelper.tl("glacio");

	public static final Component PLANET_TEXT = PlanetSelectionGuiHelper.tl("planet");
	public static final Component MOON_TEXT = PlanetSelectionGuiHelper.tl("moon");

	public static final Component ORBIT_TEXT = PlanetSelectionGuiHelper.tl("orbit");

	public static final Component NO_GRAVITY_TEXT = PlanetSelectionGuiHelper.tl("no_gravity");

	public static final Component SPACE_STATION_TEXT = PlanetSelectionGuiHelper.tl("space_station");

	public static final Component CATEGORY_TEXT = PlanetSelectionGuiHelper.tl("category");
	public static final Component PROVIDED_TEXT = PlanetSelectionGuiHelper.tl("provided");
	public static final Component TYPE_TEXT = PlanetSelectionGuiHelper.tl("type");
	public static final Component GRAVITY_TEXT = PlanetSelectionGuiHelper.tl("gravity");
	public static final Component OXYGEN_TEXT = PlanetSelectionGuiHelper.tl("oxygen");
	public static final Component TEMPERATURE_TEXT = PlanetSelectionGuiHelper.tl("temperature");
	public static final Component OXYGEN_TRUE_TEXT = PlanetSelectionGuiHelper.tl("oxygen.true");
	public static final Component OXYGEN_FALSE_TEXT = PlanetSelectionGuiHelper.tl("oxygen.false");
	public static final Component ITEM_REQUIREMENT_TEXT = PlanetSelectionGuiHelper.tl("item_requirement");

	public static final Component ROCKET_TIER_1_TEXT = new TranslatableComponent("entity." + BeyondEarthMod.MODID + ".rocket_t" + 1);
	public static final Component ROCKET_TIER_2_TEXT = new TranslatableComponent("entity." + BeyondEarthMod.MODID + ".rocket_t" + 2);
	public static final Component ROCKET_TIER_3_TEXT = new TranslatableComponent("entity." + BeyondEarthMod.MODID + ".rocket_t" + 3);
	public static final Component ROCKET_TIER_4_TEXT = new TranslatableComponent("entity." + BeyondEarthMod.MODID + ".rocket_t" + 4);

	/** CATEGORY */
	public int category = 0; //IF YOU DO A ADDON MOD SET THIS CATEGORY TO -1 AND DO A OWN CATEGORY VARIABLE! (-1 == nothing)

	/** ROTATIONS */
	public float rotationMilkyWay = 0;
	public float rotationMars = 0;
	public float rotationEarth = 90;
	public float rotationVenus = 180;
	public float rotationMercury = 270;
	public float rotationGlacio = 180;

	/** SOLAR SYSTEM BUTTONS */
	public ImageButtonPlacer solarSystemButton;
	public ImageButtonPlacer proximaCentauriButton;

	/** SUN CATEGORY BUTTONS */
	public ImageButtonPlacer earthCategoryButton;
	public ImageButtonPlacer marsCategoryButton;
	public ImageButtonPlacer mercuryCategoryButton;
	public ImageButtonPlacer venusCategoryButton;

	/** PROXIMA CENTAURI CATEGORY BUTTONS */
	public ImageButtonPlacer glacioCategoryButton;

	/** SUN CATEGORY TELEPORT BUTTONS */
	public ImageButtonPlacer earthButton;
	public ImageButtonPlacer moonButton;
	public ImageButtonPlacer marsButton;
	public ImageButtonPlacer mercuryButton;
	public ImageButtonPlacer venusButton;

	/** PROXIMA CENTAURI CATEGORY TELEPORT BUTTONS */
	public ImageButtonPlacer glacioButton;

	/** BACK BUTTONS */
	public ImageButtonPlacer backButton;

	/** SUN CATEGORY ORBIT TELEPORT BUTTONS */
	public ImageButtonPlacer earthOrbitButton;
	public ImageButtonPlacer moonOrbitButton;
	public ImageButtonPlacer marsOrbitButton;
	public ImageButtonPlacer mercuryOrbitButton;
	public ImageButtonPlacer venusOrbitButton;

	/** PROXIMA CENTAURI CATEGORY ORBIT TELEPORT BUTTONS */
	public ImageButtonPlacer glacioOrbitButton;

	/** SUN CATEGORY SPACE-STATION TELEPORT BUTTONS */
	public ImageButtonPlacer earthSpaceStationButton;
	public ImageButtonPlacer moonSpaceStationButton;
	public ImageButtonPlacer marsSpaceStationButton;
	public ImageButtonPlacer mercurySpaceStationButton;
	public ImageButtonPlacer venusSpaceStationButton;

	/** PROXIMA CENTAURI CATEGORY SPACE-STATION TELEPORT BUTTONS */
	public ImageButtonPlacer glacioSpaceStationButton;

	/** SPACE STATION RECIPE SYSTEM */
	private SpaceStationRecipe recipe;
	private boolean spaceStationItemList;

	public PlanetSelectionGuiWindow(PlanetSelectionGui.GuiContainer container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 512;
		this.imageHeight = 512;
	}

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);

		/** SOLAR SYSTEM VISIBLE LOGIC */
		this.visibleButton(solarSystemButton, category == 0);
		this.visibleButton(proximaCentauriButton, category == 0);

		/** BACK BUTTON */
		this.visibleButton(backButton, PlanetSelectionGuiHelper.categoryRange(category, 1, 5));

		/** SUN CATEGORY VISIBLE LOGIC */
		this.visibleButton(earthCategoryButton, category == 1);
		this.visibleButton(marsCategoryButton, category == 1);
		this.visibleButton(mercuryCategoryButton, category == 1);
		this.visibleButton(venusCategoryButton, category == 1);

		/** CATALOG TEXT RENDERER */
		this.font.draw(ms, CATALOG_TEXT, 24, (this.height / 2) - 143 / 2, -1);
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTicks, int p_97789_, int p_97790_) {
		PlanetSelectionGuiHelper.enableRenderSystem();

		/** BACKGROUND RENDERER */
		PlanetSelectionGuiHelper.addTexture(poseStack, 0, 0, this.width, this.height, BACKGROUND_TEXTURE);

		/** SUN SOLAR SYSTEM RENDERER */
		if (PlanetSelectionGuiHelper.categoryRange(category, 1, 3)) {
			PlanetSelectionGuiHelper.addTexture(poseStack, (this.width - 185) / 2, (this.height - 185) / 2, 185, 185, SUN_SOLAR_SYSTEM_TEXTURE);
		}

		/** PROXIMA CENTAURI SOLAR SYSTEM RENDERER */
		if (PlanetSelectionGuiHelper.categoryRange(category, 5, 6)) {
			PlanetSelectionGuiHelper.addTexture(poseStack, (this.width - 185) / 2, (this.height - 185) / 2, 185, 185, PROXIMA_CENTAURI_SOLAR_SYSTEM_TEXTURE);
		}

		/** OBJECT ROTATIONS */
		this.rotateObjects(partialTicks);

		/** ROTATED OBJECTS RENDERER */
		this.renderRotatedObjects(poseStack);

		/** SUN RENDERER */
		if (PlanetSelectionGuiHelper.categoryRange(category, 1, 3)) {
			PlanetSelectionGuiHelper.addTexture(poseStack, (this.width - 15) / 2, (this.height - 15) / 2, 15, 15, SUN_TEXTURE);
		}

		/** BLUE SUN RENDERER */
		if (PlanetSelectionGuiHelper.categoryRange(category, 5, 6)) {
			PlanetSelectionGuiHelper.addTexture(poseStack, (this.width - 15) / 2, (this.height - 15) / 2, 15, 15, BLUE_SUN_TEXTURE);
		}

		/** SMALL MENU RENDERER */
		if (PlanetSelectionGuiHelper.categoryRange(category, 0, 3)) { // MultiRange or something
			PlanetSelectionGuiHelper.addTexture(poseStack, 0, (this.height / 2) - 177 / 2, 105, 177, SMALL_MENU_LIST);
		}

		/** LARGE MENU RENDERER */
		if (PlanetSelectionGuiHelper.categoryRange(category, 2, 3)) { // MultiRange or something
			PlanetSelectionGuiHelper.addTexture(poseStack, 0, (this.height / 2) - 177 / 2, 215, 177, LARGE_MENU_TEXTURE);
		}

		PlanetSelectionGuiHelper.disableRenderSystem();
	}

	@Override
	protected void init() {
		super.init();

		/** SPACE STATION RECIPE SYSTEM */
		this.recipe = (SpaceStationRecipe) this.minecraft.level.getRecipeManager().byKey(SpaceStationRecipe.KEY).orElse(null);
		this.spaceStationItemList = this.recipe.getIngredientStacks().stream().allMatch(this::getSpaceStationItemCheck);

		/** CATEGORY -1 */
		solarSystemButton = PlanetSelectionGuiHelper.addCategoryButton(this,10, -68, 70, 20, 1, true, ImageButtonPlacer.Types.MILKY_WAY_CATEGORY, List.of(SUN_TEXT.getString()), null, BLUE_BUTTON_TEXTURE, BLUE_LIGHT_BUTTON_TEXTURE, SOLAR_SYSTEM_SUN_TEXT);
		proximaCentauriButton = PlanetSelectionGuiHelper.addCategoryButton(this, 10, -24, 70, 20, 5, true, ImageButtonPlacer.Types.MILKY_WAY_CATEGORY, List.of(PROXIMA_CENTAURI_TEXT.getString()), null, BLUE_BUTTON_TEXTURE, BLUE_LIGHT_BUTTON_TEXTURE, SOLAR_SYSTEM_PROXIMA_CENTAURI_TEXT);

		/** BACK BUTTON */
		backButton = PlanetSelectionGuiHelper.addBackButton(this, 10, -68, 70, 20, null, null, null, DARK_BLUE_BUTTON_TEXTURE, DARK_BLUE_LIGHT_BUTTON_TEXTURE, BACK_TEXT, (onPress) -> {
			if (this.category >= 2 && this.category <= 5) {
				this.category = 1;
			}
			else if (this.category == 6) {
				this.category = 0;
			}
			else if (this.category == 7) {
				this.category = 6;
			}
			else {
				this.category = this.category - 1;
			}
		});
		backButton.visible = false;

		/** CATEGORY 1 */
		earthCategoryButton = PlanetSelectionGuiHelper.addCategoryButton(this, 10, -24, 70, 20, 1, PlanetSelectionGuiHelper.checkTier(menu.getRocket(), 1), ImageButtonPlacer.Types.SOLAR_SYSTEM_CATEGORY, List.of(EARTH_TEXT.getString(), ROCKET_TIER_1_TEXT.getString()), new Pair<>(menu.getRocket(), 1), RED_BUTTON_TEXTURE, RED_LIGHT_BUTTON_TEXTURE, EARTH_TEXT);
		earthCategoryButton.visible = false;

		marsCategoryButton = PlanetSelectionGuiHelper.addCategoryButton(this, 10, 21, 70, 20, 2, PlanetSelectionGuiHelper.checkTier(menu.getRocket(), 2), ImageButtonPlacer.Types.SOLAR_SYSTEM_CATEGORY, List.of(MARS_TEXT.getString(), ROCKET_TIER_2_TEXT.getString()), new Pair<>(menu.getRocket(), 2), RED_BUTTON_TEXTURE, RED_LIGHT_BUTTON_TEXTURE, MARS_TEXT);
		marsCategoryButton.visible = false;

		mercuryCategoryButton = PlanetSelectionGuiHelper.addCategoryButton(this, 10, 65, 70, 20, 3, PlanetSelectionGuiHelper.checkTier(menu.getRocket(), 3), ImageButtonPlacer.Types.SOLAR_SYSTEM_CATEGORY, List.of(MERCURY_TEXT.getString(), ROCKET_TIER_3_TEXT.getString()), new Pair<>(menu.getRocket(), 3), RED_BUTTON_TEXTURE, RED_LIGHT_BUTTON_TEXTURE, MERCURY_TEXT);
		mercuryCategoryButton.visible = false;

		venusCategoryButton = PlanetSelectionGuiHelper.addCategoryButton(this, 10, 109, 70, 20, 4, PlanetSelectionGuiHelper.checkTier(menu.getRocket(), 3), ImageButtonPlacer.Types.SOLAR_SYSTEM_CATEGORY, List.of(VENUS_TEXT.getString(), ROCKET_TIER_3_TEXT.getString()), new Pair<>(menu.getRocket(), 3), RED_BUTTON_TEXTURE, RED_LIGHT_BUTTON_TEXTURE, VENUS_TEXT);
		venusCategoryButton.visible = false;

		/** PROXIMA CENTAURI CATEGORY */
		glacioCategoryButton = PlanetSelectionGuiHelper.addCategoryButton(this, 10, -24, 70, 20, 5, PlanetSelectionGuiHelper.checkTier(menu.getRocket(), 4), ImageButtonPlacer.Types.SOLAR_SYSTEM_CATEGORY, List.of(GLACIO_TEXT.getString(), ROCKET_TIER_4_TEXT.getString()), new Pair<>(menu.getRocket(), 4), RED_BUTTON_TEXTURE, RED_LIGHT_BUTTON_TEXTURE, GLACIO_TEXT);
		glacioCategoryButton.visible = false;

		/** TELEPORT BUTTONS */
		earthButton = PlanetSelectionGuiHelper.addHandlerButton(this, 10, (this.height / 2) - 24 / 2, 70, 20,true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(0), null, null, null, BLUE_BUTTON_TEXTURE, BLUE_LIGHT_BUTTON_TEXTURE, EARTH_TEXT);
		earthButton.visible = false;

		moonButton = PlanetSelectionGuiHelper.addHandlerButton(this, 10, (this.height / 2) + 21 / 2, 70, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(1), null, null, null, BLUE_BUTTON_TEXTURE, BLUE_LIGHT_BUTTON_TEXTURE, MOON_TEXT);
		moonButton.visible = false;

		marsButton = PlanetSelectionGuiHelper.addHandlerButton(this, 10, (this.height / 2) - 24 / 2, 70, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(2), null, null, null, BLUE_BUTTON_TEXTURE, BLUE_LIGHT_BUTTON_TEXTURE, MARS_TEXT);
		marsButton.visible = false;

		mercuryButton = PlanetSelectionGuiHelper.addHandlerButton(this, 10, (this.height / 2) - 24 / 2, 70, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(3), null, null, null, BLUE_BUTTON_TEXTURE, BLUE_LIGHT_BUTTON_TEXTURE, MERCURY_TEXT);
		mercuryButton.visible = false;

		venusButton = PlanetSelectionGuiHelper.addHandlerButton(this, 10, (this.height / 2) - 24 / 2, 70, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(4), null, null, null, BLUE_BUTTON_TEXTURE, BLUE_LIGHT_BUTTON_TEXTURE, VENUS_TEXT);
		venusButton.visible = false;

		/** PROXIMA CENTAURI CATEGORY */
		glacioButton = PlanetSelectionGuiHelper.addHandlerButton(this, 10, (this.height / 2) - 24 / 2, 70, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(5), null, null, null, BLUE_BUTTON_TEXTURE, BLUE_LIGHT_BUTTON_TEXTURE, GLACIO_TEXT);
		glacioButton.visible = false;

		/** ORBIT BUTTONS */
		earthOrbitButton = PlanetSelectionGuiHelper.addHandlerButton(this, 84, (this.height / 2) - 24 / 2, 37, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(6), null, null, null, SMALL_BLUE_BUTTON_TEXTURE, SMALL_BLUE_LIGHT_BUTTON_TEXTURE, ORBIT_TEXT);
		earthOrbitButton.visible = false;

		moonOrbitButton = PlanetSelectionGuiHelper.addHandlerButton(this, 84, (this.height / 2) + 21 / 2, 37, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(7), null, null, null, SMALL_BLUE_BUTTON_TEXTURE, SMALL_BLUE_LIGHT_BUTTON_TEXTURE, ORBIT_TEXT);
		moonOrbitButton.visible = false;

		marsOrbitButton = PlanetSelectionGuiHelper.addHandlerButton(this, 84, (this.height / 2) - 24 / 2, 37, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(8), null, null, null, SMALL_BLUE_BUTTON_TEXTURE, SMALL_BLUE_LIGHT_BUTTON_TEXTURE, ORBIT_TEXT);
		marsOrbitButton.visible = false;

		mercuryOrbitButton = PlanetSelectionGuiHelper.addHandlerButton(this, 84, (this.height / 2) - 24 / 2, 37, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(9), null, null, null, SMALL_BLUE_BUTTON_TEXTURE, SMALL_BLUE_LIGHT_BUTTON_TEXTURE, ORBIT_TEXT);
		mercuryOrbitButton.visible = false;

		venusOrbitButton = PlanetSelectionGuiHelper.addHandlerButton(this, 84, (this.height / 2) - 24 / 2, 37, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(10), null, null, null, SMALL_BLUE_BUTTON_TEXTURE, SMALL_BLUE_LIGHT_BUTTON_TEXTURE, ORBIT_TEXT);
		venusOrbitButton.visible = false;

		/** PROXIMA CENTAURI CATEGORY */
		glacioOrbitButton = PlanetSelectionGuiHelper.addHandlerButton(this, 84, (this.height / 2) - 24 / 2, 37, 20, true, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(11), null, null, null, SMALL_BLUE_BUTTON_TEXTURE, SMALL_BLUE_LIGHT_BUTTON_TEXTURE, ORBIT_TEXT);
		glacioOrbitButton.visible = false;

		/** SPACE STATION BUTTONS */
		earthSpaceStationButton = PlanetSelectionGuiHelper.addHandlerButton(this, 125, (this.height / 2) - 24 / 2, 75, 20, this.spaceStationItemList, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(12), null, null, null, LARGE_RED_BUTTON_TEXTURE, LARGE_RED_LIGHT_BUTTON_TEXTURE, SPACE_STATION_TEXT);
		earthSpaceStationButton.visible = false;

		moonSpaceStationButton = PlanetSelectionGuiHelper.addHandlerButton(this, 125, (this.height / 2) + 21 / 2, 75, 20, this.spaceStationItemList, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(13), null, null, null, LARGE_RED_BUTTON_TEXTURE, LARGE_RED_LIGHT_BUTTON_TEXTURE, SPACE_STATION_TEXT);
		moonSpaceStationButton.visible = false;

		marsSpaceStationButton = PlanetSelectionGuiHelper.addHandlerButton(this, 125, (this.height / 2) - 24 / 2, 75, 20, this.spaceStationItemList, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(14), null, null, null, LARGE_RED_BUTTON_TEXTURE, LARGE_RED_LIGHT_BUTTON_TEXTURE, SPACE_STATION_TEXT);
		marsSpaceStationButton.visible = false;

		mercurySpaceStationButton = PlanetSelectionGuiHelper.addHandlerButton(this, 125, (this.height / 2) - 24 / 2, 75, 20, this.spaceStationItemList, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(15), null, null, null, LARGE_RED_BUTTON_TEXTURE, LARGE_RED_LIGHT_BUTTON_TEXTURE, SPACE_STATION_TEXT);
		mercurySpaceStationButton.visible = false;

		venusSpaceStationButton = PlanetSelectionGuiHelper.addHandlerButton(this, 125, (this.height / 2) - 24 / 2, 75, 20, this.spaceStationItemList, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(16), null, null, null, LARGE_RED_BUTTON_TEXTURE, LARGE_RED_LIGHT_BUTTON_TEXTURE, SPACE_STATION_TEXT);
		venusSpaceStationButton.visible = false;

		/** PROXIMA CENTAURI CATEGORY */
		glacioSpaceStationButton = PlanetSelectionGuiHelper.addHandlerButton(this, 125, (this.height / 2) - 24 / 2, 75, 20, this.spaceStationItemList, BeyondEarthMod.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(17), null, null, null, LARGE_RED_BUTTON_TEXTURE, LARGE_RED_LIGHT_BUTTON_TEXTURE, SPACE_STATION_TEXT);
		glacioSpaceStationButton.visible = false;
	}

	@Override
	protected void renderLabels(PoseStack p_97808_, int p_97809_, int p_97810_) {

	}

	public void rotateObjects(float partialTicks) {

		/** SOLAR SYSTEM CATEGORY */
		rotationMilkyWay = (rotationMilkyWay + partialTicks * 0.4f) % 360;

		/** SUN CATEGORY */
		rotationMars = (rotationMars + partialTicks * 0.4f) % 360;
		rotationEarth = (rotationEarth + partialTicks * 0.8f) % 360;
		rotationVenus = (rotationVenus + partialTicks * 0.7f) % 360;
		rotationMercury = (rotationMercury + partialTicks * 0.7f) % 360;

		/** PROXIMA CENTAURI CATEGORY */
		rotationGlacio = (rotationGlacio + partialTicks * 0.7f) % 360;
	}

	public void renderRotatedObjects(PoseStack poseStack) {

		/** SOLAR SYSTEM CATEGORY */
		if (category == 0) {
			PlanetSelectionGuiHelper.addRotatedObject(this, poseStack, MILKY_WAY_TEXTURE, -125, -125, 250, 250, rotationMilkyWay);
		}

		/** SUN CATEGORY */
		if (PlanetSelectionGuiHelper.categoryRange(category, 1, 3)) {
			PlanetSelectionGuiHelper.addRotatedObject(this, poseStack, MARS_TEXTURE, -70, -70, 10, 10, rotationMars);
			PlanetSelectionGuiHelper.addRotatedObject(this, poseStack, EARTH_TEXTURE, -54, -54, 10, 10, rotationEarth);
			PlanetSelectionGuiHelper.addRotatedObject(this, poseStack, VENUS_TEXTURE, -37, -37, 10, 10, rotationVenus);
			PlanetSelectionGuiHelper.addRotatedObject(this, poseStack, MERCURY_TEXTURE, -20.5F, -20.5F, 10, 10, rotationMercury);
		}

		/** PROXIMA CENTAURI CATEGORY */
		if (PlanetSelectionGuiHelper.categoryRange(category, 5, 6)) {
			PlanetSelectionGuiHelper.addRotatedObject(this, poseStack, GLACIO_TEXTURE, -20.5F, -20.5F, 10, 10, rotationGlacio);
		}
	}

	public boolean getSpaceStationItemCheck(IngredientStack ingredientStack) {
		Player player = this.menu.player;

		if (player.getAbilities().instabuild || player.isSpectator()) {
			return true;
		}

		Inventory inv = player.getInventory();
		int itemStackCount = 0;

		for (int i = 0; i < inv.getContainerSize(); ++i) {
			ItemStack itemStack = inv.getItem(i);

			if (ingredientStack.testWithoutCount(itemStack)) {
				itemStackCount += itemStack.getCount();
			}
		}

		return itemStackCount >= ingredientStack.getCount();
	}

	public void visibleButton(ImageButtonPlacer button, Boolean condition) {
		if (condition) {
			button.visible = true;
		} else {
			button.visible = false;
		}
	}

	public ImageButtonPlacer addButton(int x, int y, int width, int height, ImageButtonPlacer.Types type, List<String> list, Pair<String, Integer> tier, ResourceLocation buttonTexture, ResourceLocation hoverButtonTexture, Component title, Button.OnPress onPress) {
		ImageButtonPlacer button = this.addRenderableWidget(new ImageButtonPlacer(x, y, width, height, 0, 0, 0, type, list, tier, buttonTexture, hoverButtonTexture, width, height, onPress, title));
		return button;
	}
}
