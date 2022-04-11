package net.mrscauthd.beyond_earth.guis.screens.planetselection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.crafting.IngredientStack;
import net.mrscauthd.beyond_earth.crafting.SpaceStationRecipe;
import net.mrscauthd.beyond_earth.guis.helper.GuiHelper;
import net.mrscauthd.beyond_earth.guis.helper.ImageButtonPlacer;
import net.mrscauthd.beyond_earth.utils.Rectangle2d;

@OnlyIn(Dist.CLIENT)
public class PlanetSelectionGuiWindow extends AbstractContainerScreen<PlanetSelectionGui.GuiContainer> {

	public static final ResourceLocation texture = new ResourceLocation(BeyondEarthMod.MODID, "textures/screens/planet_selection.png");

	public static final ResourceLocation defaultButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/red_button.png");
	public static final ResourceLocation gbButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/green_button.png");
	public static final ResourceLocation gb2ButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/green_button_2.png");
	public static final ResourceLocation rbButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/red_button.png");
	public static final ResourceLocation rb2ButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/red_button_2.png");

	public static final ResourceLocation dbbButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/dark_blue_button.png");
	public static final ResourceLocation dbb2ButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/dark_blue_button_2.png");

	public static final ResourceLocation bbButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/blue_button.png");
	public static final ResourceLocation bb2ButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/blue_button_2.png");

	public static final ResourceLocation sbbButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/small_blue_button.png");
	public static final ResourceLocation sbb2ButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/small_blue_button_2.png");

	public static final ResourceLocation bgbButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/big_green_button.png");
	public static final ResourceLocation bgb2ButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/big_green_button_2.png");

	public static final ResourceLocation brbButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/big_red_button.png");
	public static final ResourceLocation brb2ButtonTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/buttons/big_red_button_2.png");

	public static final ResourceLocation solarSystemTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/solar_system.png");
	public static final ResourceLocation proximaCentauriTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/proxima_centauri.png");

	public static final ResourceLocation milkyWayTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/milky_way.png");

	public static final ResourceLocation sunTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/sun.png");
	public static final ResourceLocation blueSunTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/blue_sun.png");
	public static final ResourceLocation marsTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/mars.png");
	public static final ResourceLocation earthTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/earth.png");
	public static final ResourceLocation venusTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/venus.png");
	public static final ResourceLocation mercuryTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/mercury.png");
	public static final ResourceLocation glacioTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/sky/gui/glacio.png");

	public static final ResourceLocation rocketMenuListTex = new ResourceLocation(BeyondEarthMod.MODID, "textures/rocket_menu_list.png");
	public static final ResourceLocation rocketMenuListTex2 = new ResourceLocation(BeyondEarthMod.MODID, "textures/rocket_menu_list_2.png");

	public static final Component catalogTEXT = tl("catalog");
	public static final Component backTEXT = tl("back");

	public static final Component sunTEXT = tl("sun");
	public static final Component proxima_centauriTEXT = tl("proxima_centauri");

	public static final Component solar_systemTEXT = tl("solar_system");

	public static final Component solar_system_sunTEXT = tl("solar_system_sun");
	public static final Component solar_system_proxima_centauriTEXT = tl("solar_system_proxima_centauri");

	public static final Component earthTEXT = tl("earth");
	public static final Component marsTEXT = tl("mars");
	public static final Component mercuryTEXT = tl("mercury");
	public static final Component venusTEXT = tl("venus");
	public static final Component glacioTEXT = tl("glacio");

	public static final Component planetTEXT = tl("planet");
	public static final Component moonTEXT = tl("moon");

	public static final Component orbitTEXT = tl("orbit");

	public static final Component no_gravityTEXT = tl("no_gravity");

	public static final Component space_stationTEXT = tl("space_station");

	public static final Component categoryTEXT = tl("category");
	public static final Component providedTEXT = tl("provided");
	public static final Component typeTEXT = tl("type");
	public static final Component gravityTEXT = tl("gravity");
	public static final Component oxygenTEXT = tl("oxygen");
	public static final Component temperatureTEXT = tl("temperature");
	public static final Component oxygenTrueTEXT = tl("oxygen.true");
	public static final Component oxygenFalseTEXT = tl("oxygen.false");
	public static final Component item_requirementTEXT = tl("item_requirement");

	public static final Component rocket_t1TEXT = new TranslatableComponent("entity." + BeyondEarthMod.MODID + ".rocket_t" + 1);
	public static final Component rocket_t2TEXT = new TranslatableComponent("entity." + BeyondEarthMod.MODID + ".rocket_t" + 2);
	public static final Component rocket_t3TEXT = new TranslatableComponent("entity." + BeyondEarthMod.MODID + ".rocket_t" + 3);
	public static final Component rocket_t4TEXT = new TranslatableComponent("entity." + BeyondEarthMod.MODID + ".rocket_t" + 4);

	public int Category = -1;

	public float milkyWay = 0;
	public float rotationMars = 0;
	public float rotationEarth = 90;
	public float rotationVenus = 180;
	public float rotationMercury = 270;

	public float rotationGlacio = 180;

	// CATEGORY BUTTONS (-1)
	public ImageButtonPlacer solarSystemButton;
	public ImageButtonPlacer proximaCentauriButton;

	// CATEGORY BUTTONS (0)
	public ImageButtonPlacer earthCategoryButton;
	public ImageButtonPlacer marsCategoryButton;
	public ImageButtonPlacer mercuryCategoryButton;
	public ImageButtonPlacer venusCategoryButton;

	// CATEGORY BUTTONS (5)
	public ImageButtonPlacer glacioCategoryButton;

	// TELEPORT BUTTONS
	public ImageButtonPlacer earthButton;
	public ImageButtonPlacer moonButton;
	public ImageButtonPlacer marsButton;
	public ImageButtonPlacer mercuryButton;
	public ImageButtonPlacer venusButton;
	// Category 5 Teleport Buttons
	public ImageButtonPlacer glacioButton;

	// BACK BUTTON
	public ImageButtonPlacer backButton;

	// ORBIT BUTTONS
	public ImageButtonPlacer earthOrbitButton;
	public ImageButtonPlacer moonOrbitButton;
	public ImageButtonPlacer marsOrbitButton;
	public ImageButtonPlacer mercuryOrbitButton;
	public ImageButtonPlacer venusOrbitButton;
	// Category 5 Teleport Buttons
	public ImageButtonPlacer glacioOrbitButton;

	// SPACE STATION BUTTONS
	public ImageButtonPlacer earthSpaceStationButton;
	public ImageButtonPlacer moonSpaceStationButton;
	public ImageButtonPlacer marsSpaceStationButton;
	public ImageButtonPlacer mercurySpaceStationButton;
	public ImageButtonPlacer venusSpaceStationButton;
	// Category 5 Teleport Buttons
	public ImageButtonPlacer glacioSpaceStationButton;

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

		milkyWay = (milkyWay + partialTicks * 0.4f) % 360;

		rotationMars = (rotationMars + partialTicks * 0.4f) % 360;
		rotationEarth = (rotationEarth + partialTicks * 0.8f) % 360;
		rotationVenus = (rotationVenus + partialTicks * 0.7f) % 360;
		rotationMercury = (rotationMercury + partialTicks * 0.7f) % 360;

		rotationGlacio = (rotationGlacio + partialTicks * 0.7f) % 360;

		String rocketType = menu.rocket;

		// CATEGORY -1 BUTTON
		if (Category == -1) {
			solarSystemButton.visible = true;
			proximaCentauriButton.visible = true;

			this.categoryButtonManager(bbButtonTex, bb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) - 68 / 2, 70, 20, ms, solarSystemButton, sunTEXT, solar_systemTEXT);

			this.categoryButtonManager(bbButtonTex, bb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) - 24 / 2, 70, 20, ms, proximaCentauriButton, proxima_centauriTEXT, solar_systemTEXT);
		} else {
			solarSystemButton.visible = false;
			proximaCentauriButton.visible = false;
		}

		// CATEGORY 0 BUTTON
		if (Category == 0) {
			earthCategoryButton.visible = true;
			marsCategoryButton.visible = true;
			mercuryCategoryButton.visible = true;
			venusCategoryButton.visible = true;

			this.buttonManager(rocketType, gb2ButtonTex, rb2ButtonTex, gbButtonTex, rbButtonTex, mouseX, mouseY, 10, (this.height / 2) - 24 / 2, 70, 20, ms, earthCategoryButton, earthTEXT, 1);

			this.buttonManager(rocketType, gb2ButtonTex, rb2ButtonTex, gbButtonTex, rbButtonTex, mouseX, mouseY, 10, (this.height / 2) + 21 / 2, 70, 20, ms, marsCategoryButton, marsTEXT, 2);

			this.buttonManager(rocketType, gb2ButtonTex, rb2ButtonTex, gbButtonTex, rbButtonTex, mouseX, mouseY, 10, (this.height / 2) + 65 / 2, 70, 20, ms, mercuryCategoryButton, mercuryTEXT, 3);

			this.buttonManager(rocketType, gb2ButtonTex, rb2ButtonTex, gbButtonTex, rbButtonTex, mouseX, mouseY, 10, (this.height / 2) + 109 / 2, 70, 20, ms, venusCategoryButton, venusTEXT, 3);
		} else {
			earthCategoryButton.visible = false;
			marsCategoryButton.visible = false;
			mercuryCategoryButton.visible = false;
			venusCategoryButton.visible = false;
		}

		// BACK BUTTON
		if (Category >= 0) {
			backButton.visible = true;

			// back
			this.backButtonManager(dbbButtonTex, dbb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) - 68 / 2, 70, 20, backButton);
		} else {
			backButton.visible = false;
		}

		// CATEGORY 1 BUTTON
		if (Category == 1) {
			earthButton.visible = true;
			earthOrbitButton.visible = true;
			earthSpaceStationButton.visible = true;
			moonButton.visible = true;
			moonOrbitButton.visible = true;
			moonSpaceStationButton.visible = true;

			// Planets
			this.teleportButtonManager(bbButtonTex, bb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) - 24 / 2, 70, 20, ms, earthButton, planetTEXT, "9.807 m/s", true, "a", 14);
			this.teleportButtonManager(bbButtonTex, bb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) + 21 / 2, 70, 20, ms, moonButton, moonTEXT, "1.62 m/s", false, "c", -160);

			// Orbits
			this.teleportButtonManager(sbbButtonTex, sbb2ButtonTex, mouseX, mouseY, 84, (this.height / 2) - 24 / 2, 37, 20, ms, earthOrbitButton, orbitTEXT, no_gravityTEXT, false, "c", -270);
			this.teleportButtonManager(sbbButtonTex, sbb2ButtonTex, mouseX, mouseY, 84, (this.height / 2) + 21 / 2, 37, 20, ms, moonOrbitButton, orbitTEXT, no_gravityTEXT, false, "c", -270);

			// Space Station
			this.spaceStationCreatorButtonManager(brbButtonTex, brb2ButtonTex, bgbButtonTex, bgb2ButtonTex, mouseX, mouseY, 125, (this.height / 2) - 24 / 2, 75, 20, ms, earthSpaceStationButton, orbitTEXT, no_gravityTEXT, false, "c", -270);
			this.spaceStationCreatorButtonManager(brbButtonTex, brb2ButtonTex, bgbButtonTex, bgb2ButtonTex, mouseX, mouseY, 125, (this.height / 2) + 21 / 2, 75, 20, ms, moonSpaceStationButton, orbitTEXT, no_gravityTEXT, false, "c", -270);

		} else {
			earthButton.visible = false;
			earthOrbitButton.visible = false;
			earthSpaceStationButton.visible = false;
			moonButton.visible = false;
			moonOrbitButton.visible = false;
			moonSpaceStationButton.visible = false;
		}

		// CATEGORY 2 BUTTON
		if (Category == 2) {
			marsButton.visible = true;
			marsOrbitButton.visible = true;
			marsSpaceStationButton.visible = true;

			// Planets
			this.teleportButtonManager(bbButtonTex, bb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) - 24 / 2, 70, 20, ms, marsButton, planetTEXT, "3.721 m/s", false, "a", -63);

			// Orbits
			this.teleportButtonManager(sbbButtonTex, sbb2ButtonTex, mouseX, mouseY, 84, (this.height / 2) - 24 / 2, 37, 20, ms, marsOrbitButton, orbitTEXT, no_gravityTEXT, false, "c", -270);

			// Space Station
			this.spaceStationCreatorButtonManager(brbButtonTex, brb2ButtonTex, bgbButtonTex, bgb2ButtonTex, mouseX, mouseY, 125, (this.height / 2) - 24 / 2, 75, 20, ms, marsSpaceStationButton, orbitTEXT, no_gravityTEXT, false, "c", -270);
		} else {
			marsButton.visible = false;
			marsOrbitButton.visible = false;
			marsSpaceStationButton.visible = false;
		}

		// CATEGORY 3 BUTTON
		if (Category == 3) {
			mercuryButton.visible = true;
			mercuryOrbitButton.visible = true;
			mercurySpaceStationButton.visible = true;

			// Planets
			this.teleportButtonManager(bbButtonTex, bb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) - 24 / 2, 70, 20, ms, mercuryButton, planetTEXT, "3.7 m/s", false, "a", 430);

			// Orbits
			this.teleportButtonManager(sbbButtonTex, sbb2ButtonTex, mouseX, mouseY, 84, (this.height / 2) - 24 / 2, 37, 20, ms, mercuryOrbitButton, orbitTEXT, no_gravityTEXT, false, "c", -270);

			// Space Station
			this.spaceStationCreatorButtonManager(brbButtonTex, brb2ButtonTex, bgbButtonTex, bgb2ButtonTex, mouseX, mouseY, 125, (this.height / 2) - 24 / 2, 75, 20, ms, mercurySpaceStationButton, orbitTEXT, no_gravityTEXT, false, "c", -270);
		} else {
			mercuryButton.visible = false;
			mercuryOrbitButton.visible = false;
			mercurySpaceStationButton.visible = false;
		}

		// CATEGORY 4 BUTTON
		if (Category == 4) {
			venusButton.visible = true;
			venusOrbitButton.visible = true;
			venusSpaceStationButton.visible = true;

			// Planets
			this.teleportButtonManager(bbButtonTex, bb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) - 24 / 2, 70, 20, ms, venusButton, planetTEXT, "8.87 m/s", false, "a", 482);

			// Orbits
			this.teleportButtonManager(sbbButtonTex, sbb2ButtonTex, mouseX, mouseY, 84, (this.height / 2) - 24 / 2, 37, 20, ms, venusOrbitButton, orbitTEXT, no_gravityTEXT, false, "c", -270);

			// Space Station
			this.spaceStationCreatorButtonManager(brbButtonTex, brb2ButtonTex, bgbButtonTex, bgb2ButtonTex, mouseX, mouseY, 125, (this.height / 2) - 24 / 2, 75, 20, ms, venusSpaceStationButton, orbitTEXT, no_gravityTEXT, false, "c", -270);
		} else {
			venusButton.visible = false;
			venusOrbitButton.visible = false;
			venusSpaceStationButton.visible = false;
		}

		/** CATEGORY 5 BUTTON */ // Proxima Centauri MAIN
		if (Category == 5) {
			glacioCategoryButton.visible = true;

			this.buttonManager(rocketType, gb2ButtonTex, rb2ButtonTex, gbButtonTex, rbButtonTex, mouseX, mouseY, 10, (this.height / 2) - 24 / 2, 70, 20, ms, glacioCategoryButton, glacioTEXT, 4);
		} else {
			glacioCategoryButton.visible = false;
		}

		if (Category == 6) {
			glacioButton.visible = true;
			glacioOrbitButton.visible = true;
			glacioSpaceStationButton.visible = true;

			// Planets
			this.teleportButtonManager(bbButtonTex, bb2ButtonTex, mouseX, mouseY, 10, (this.height / 2) - 24 / 2, 70, 20, ms, glacioButton, planetTEXT, "3.721 m/s", true, "a", -20);

			// Orbits
			this.teleportButtonManager(sbbButtonTex, sbb2ButtonTex, mouseX, mouseY, 84, (this.height / 2) - 24 / 2, 37, 20, ms, glacioOrbitButton, orbitTEXT, no_gravityTEXT, false, "c", -270);

			// Space Station
			this.spaceStationCreatorButtonManager(brbButtonTex, brb2ButtonTex, bgbButtonTex, bgb2ButtonTex, mouseX, mouseY, 125, (this.height / 2) - 24 / 2, 75, 20, ms, glacioSpaceStationButton, orbitTEXT, no_gravityTEXT, false, "c", -270);
		} else {
			glacioButton.visible = false;
			glacioOrbitButton.visible = false;
			glacioSpaceStationButton.visible = false;
		}

		// RENDER FONTS
		this.font.draw(ms, catalogTEXT, 24, (this.height / 2) - 143 / 2, -1);
	}

	@Override
	protected void renderBg(PoseStack ms, float p_97788_, int p_97789_, int p_97790_) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		// BACKGROUND
		RenderSystem.setShaderTexture(0, texture);
		GuiComponent.blit(ms, 0, 0, 0, 0, this.width, this.height, this.width, this.height);

		// Solar System
		if (Category >= 0 && Category <= 4) {
			RenderSystem.setShaderTexture(0, solarSystemTex);
			GuiHelper.blit(ms, (this.width - 185) / 2, (this.height - 185) / 2, 0, 0, 185, 185, 185, 185);
		}

		// Proxima Centauri
		if (Category >= 5 && Category <= 6) {
			RenderSystem.setShaderTexture(0, proximaCentauriTex);
			GuiHelper.blit(ms, (this.width - 185) / 2, (this.height - 185) / 2, 0, 0, 185, 185, 185, 185);
		}

		// SUN
		if (Category >= 0 && Category <= 4) {
			RenderSystem.setShaderTexture(0, sunTex);
			GuiHelper.blit(ms, (this.width - 15) / 2, (this.height - 15) / 2, 0, 0, 15, 15, 15, 15);
		} else if (Category >= 5 && Category <= 6) {
			RenderSystem.setShaderTexture(0, blueSunTex);
			GuiHelper.blit(ms, (this.width - 15) / 2, (this.height - 15) / 2, 0, 0, 15, 15, 15, 15);
		}

		// MILKY WAY
		if (Category == -1) {
			this.addGalaxy(ms, milkyWayTex, -125, -125, 250, 250, milkyWay);
		}

		// PLANETS
		if (Category >= 0 && Category <= 4) {
			this.addPlanet(ms, marsTex, -70, -70, 10, 10, rotationMars);
			this.addPlanet(ms, earthTex, -54, -54, 10, 10, rotationEarth);
			this.addPlanet(ms, venusTex, -37, -37, 10, 10, rotationVenus);
			this.addPlanet(ms, mercuryTex, -20.5F, -20.5F, 10, 10, rotationMercury);
		} else if (Category >= 5 && Category <= 6) {
			this.addPlanet(ms, glacioTex, -20.5F, -20.5F, 10, 10, rotationGlacio);
		}

		// MENU
		if ((Category < 1) || (Category == 5)) {
			RenderSystem.setShaderTexture(0, rocketMenuListTex);
			GuiComponent.blit(ms, 0, (this.height / 2) - 177 / 2, 0, 0, 105, 177, 105, 177);
		} else if ((Category >= 1 && Category <= 4) || (Category >= 5 && Category <= 6)) {
			RenderSystem.setShaderTexture(0, rocketMenuListTex2);
			GuiComponent.blit(ms, 0, (this.height / 2) - 177 / 2, 0, 0, 215, 177, 215, 177);
		}

		RenderSystem.disableBlend();
	}

	public void addPlanet(PoseStack ms, ResourceLocation planet, float x, float y, int width, int height, float rotation) {
		ms.pushPose();

		ms.translate(this.width / 2, this.height / 2, 0);
		ms.mulPose(new Quaternion(Vector3f.ZP, rotation, true));

		RenderSystem.setShaderTexture(0, planet);
		GuiHelper.blit(ms, x, y, 0, 0, width, height, width, height);

		ms.translate(-this.width / 2, -this.height / 2, 0);
		ms.popPose();
	}

	public void addGalaxy(PoseStack ms, ResourceLocation planet, float x, float y, int width, int height, float rotation) {
		ms.pushPose();

		ms.translate(this.width / 2, this.height / 2, 0);
		ms.mulPose(new Quaternion(Vector3f.ZP, rotation, true));

		RenderSystem.setShaderTexture(0, planet);
		GuiHelper.blit(ms, x, y, 0, 0, width, height, width, height);

		ms.translate(-this.width / 2, -this.height / 2, 0);
		ms.popPose();
	}

	@Override
	protected void init() {
		super.init();

		/** SPACE STATION RECIPE SYSTEM */
		this.recipe = (SpaceStationRecipe) this.minecraft.level.getRecipeManager().byKey(SpaceStationRecipe.KEY).orElse(null);
		this.spaceStationItemList = this.recipe.getIngredientStacks().stream().allMatch(this::getSpaceStationItemCheck);

		/** CATEGORY -1 */
		solarSystemButton = this.addImageButtonSetSolarSystem(10, (this.height / 2) - 68 / 2, 70, 20, bbButtonTex, 0, solar_system_sunTEXT);
		proximaCentauriButton = this.addImageButtonSetSolarSystem(10, (this.height / 2) - 24 / 2, 70, 20, bbButtonTex, 5, solar_system_proxima_centauriTEXT);

		/** CATEGORY 0 */
		earthCategoryButton = this.addImageButtonSetCategory(10, (this.height / 2) - 24 / 2, 70, 20, defaultButtonTex, 1, menu.rocket, 1, earthTEXT);
		earthCategoryButton.visible = false;

		marsCategoryButton = this.addImageButtonSetCategory(10, (this.height / 2) + 21 / 2, 70, 20, defaultButtonTex, 2, menu.rocket, 2, marsTEXT);
		marsCategoryButton.visible = false;

		mercuryCategoryButton = this.addImageButtonSetCategory(10, (this.height / 2) + 65 / 2, 70, 20, defaultButtonTex, 3, menu.rocket, 3, mercuryTEXT);
		mercuryCategoryButton.visible = false;

		venusCategoryButton = this.addImageButtonSetCategory(10, (this.height / 2) + 109 / 2, 70, 20, defaultButtonTex, 4, menu.rocket, 3, venusTEXT);
		venusCategoryButton.visible = false;

		/** CATEGORY 5 */
		glacioCategoryButton = this.addImageButtonSetCategory(10, (this.height / 2) - 24 / 2, 70, 20, defaultButtonTex, 6, menu.rocket, 4, glacioTEXT);
		glacioCategoryButton.visible = false;

		/** BACK BUTTON */
		backButton = this.addImageButtonBack(10, (this.height / 2) - 68 / 2, 70, 20, dbbButtonTex, backTEXT);
		backButton.visible = false;

		/** TELEPORT BUTTONS */
		// CATEGORY 0
		earthButton = this.addImageButton(10, (this.height / 2) - 24 / 2, 70, 20, bbButtonTex, 0, earthTEXT);
		earthButton.visible = false;

		moonButton = this.addImageButton(10, (this.height / 2) + 21 / 2, 70, 20, bbButtonTex, 1, moonTEXT);
		moonButton.visible = false;

		marsButton = this.addImageButton(10, (this.height / 2) - 24 / 2, 70, 20, bbButtonTex, 2, marsTEXT);
		marsButton.visible = false;

		mercuryButton = this.addImageButton(10, (this.height / 2) - 24 / 2, 70, 20, bbButtonTex, 3, mercuryTEXT);
		mercuryButton.visible = false;

		venusButton = this.addImageButton(10, (this.height / 2) - 24 / 2, 70, 20, bbButtonTex, 4, venusTEXT);
		venusButton.visible = false;

		// CATEGORY 5
		glacioButton = this.addImageButton(10, (this.height / 2) - 24 / 2, 70, 20, bbButtonTex, 5, glacioTEXT);
		glacioButton.visible = false;

		/** ORBIT BUTTONS */
		// CATEGORY 0
		earthOrbitButton = this.addImageButton(84, (this.height / 2) - 24 / 2, 37, 20, sbbButtonTex, 6, orbitTEXT);
		earthOrbitButton.visible = false;

		moonOrbitButton = this.addImageButton(84, (this.height / 2) + 21 / 2, 37, 20, sbbButtonTex, 7, orbitTEXT);
		moonOrbitButton.visible = false;

		marsOrbitButton = this.addImageButton(84, (this.height / 2) - 24 / 2, 37, 20, sbbButtonTex, 8, orbitTEXT);
		marsOrbitButton.visible = false;

		mercuryOrbitButton = this.addImageButton(84, (this.height / 2) - 24 / 2, 37, 20, sbbButtonTex, 9, orbitTEXT);
		mercuryOrbitButton.visible = false;

		venusOrbitButton = this.addImageButton(84, (this.height / 2) - 24 / 2, 37, 20, sbbButtonTex, 10, orbitTEXT);
		venusOrbitButton.visible = false;
		// CATEGORY 5
		glacioOrbitButton = this.addImageButton(84, (this.height / 2) - 24 / 2, 37, 20, sbbButtonTex, 11, orbitTEXT);
		glacioOrbitButton.visible = false;

		/** SPACE STATION BUTTONS */
		// CATEGORY 0
		earthSpaceStationButton = this.addSpaceStationImageButton(125, (this.height / 2) - 24 / 2, 75, 20, brbButtonTex, 12, space_stationTEXT, this.spaceStationItemList);
		earthSpaceStationButton.visible = false;

		moonSpaceStationButton = this.addSpaceStationImageButton(125, (this.height / 2) + 21 / 2, 75, 20, brbButtonTex, 13, space_stationTEXT, this.spaceStationItemList);
		moonSpaceStationButton.visible = false;

		marsSpaceStationButton = this.addSpaceStationImageButton(125, (this.height / 2) - 24 / 2, 75, 20, brbButtonTex, 14, space_stationTEXT, this.spaceStationItemList);
		marsSpaceStationButton.visible = false;

		mercurySpaceStationButton = this.addSpaceStationImageButton(125, (this.height / 2) - 24 / 2, 75, 20, brbButtonTex, 15, space_stationTEXT, this.spaceStationItemList);
		mercurySpaceStationButton.visible = false;

		venusSpaceStationButton = this.addSpaceStationImageButton(125, (this.height / 2) - 24 / 2, 75, 20, brbButtonTex, 16, space_stationTEXT, this.spaceStationItemList);
		venusSpaceStationButton.visible = false;
		// CATEGORY 5
		glacioSpaceStationButton = this.addSpaceStationImageButton(125, (this.height / 2) - 24 / 2, 75, 20, brbButtonTex, 17, space_stationTEXT, this.spaceStationItemList);
		glacioSpaceStationButton.visible = false;
	}

	@Override
	protected void renderLabels(PoseStack p_97808_, int p_97809_, int p_97810_) {
	}

	public Rectangle2d getBounds(int left, int top, int width, int height) {
		return GuiHelper.getBounds(left, top, width, height);
	}

	public boolean checkTier(String rocketType, int stage) {
		int tier = 0;

		if (rocketType.equals("entity." + BeyondEarthMod.MODID + ".rocket_t1")) {
			tier = 1;
		}
		if (rocketType.equals("entity." + BeyondEarthMod.MODID + ".rocket_t2")) {
			tier = 2;
		}
		if (rocketType.equals("entity." + BeyondEarthMod.MODID + ".rocket_t3")) {
			tier = 3;
		}
		if (rocketType.equals("entity." + BeyondEarthMod.MODID + ".rocket_t4")) {
			tier = 4;
		}

		return tier >= stage;
	}

	public void buttonManager(String rocketType, ResourceLocation gb2, ResourceLocation rb2, ResourceLocation gb, ResourceLocation rb, int mouseX, int mouseY, int left, int top, int width, int height, PoseStack ms, ImageButtonPlacer button, Component dim, int stage) {
		String level = "c";

		if (checkTier(rocketType, stage)) {
			level = "a";
			button.setTexture(gb);
		} else {
			level = "c";
			button.setTexture(rb);
		}

		if (GuiHelper.isHover(this.getBounds(left, top, width, height), mouseX, mouseY)) {

			List<Component> list = new ArrayList<Component>();

			list.add(new TextComponent("\u00A79" + categoryTEXT.getString() + ": \u00A7" + level + dim.getString()));
			list.add(new TextComponent("\u00A79" + providedTEXT.getString() + ": \u00A7b" + this.getRocket(stage).getString()));
			this.renderComponentTooltip(ms, list, mouseX, mouseY);

			if (checkTier(rocketType, stage)) {
				button.setTexture(gb2);
			} else {
				button.setTexture(rb2);
			}
		}
	}

	public void categoryButtonManager(ResourceLocation bb, ResourceLocation bb2, int mouseX, int mouseY, int left, int top, int width, int height, PoseStack ms, ImageButtonPlacer button, Component solarSystem, Component type) {
		button.setTexture(bb);

		if (GuiHelper.isHover(this.getBounds(left, top, width, height), mouseX, mouseY)) {

			List<Component> list = new ArrayList<Component>();

			list.add(new TextComponent("\u00A79" + categoryTEXT.getString() + ": \u00A7b" + solarSystem.getString()));
			list.add(new TextComponent("\u00A79" + typeTEXT.getString() + ": \u00A73" + type.getString()));
			this.renderComponentTooltip(ms, list, mouseX, mouseY);

			button.setTexture(bb2);
		}
	}

	public void teleportButtonManager(ResourceLocation bb, ResourceLocation bb2, int mouseX, int mouseY, int left, int top, int width, int height, PoseStack ms, ImageButtonPlacer button, Component planetType, String gravity, boolean oxygen, String temperatureColor, int temperature) {
		this.teleportButtonManager(bb, bb2, mouseX, mouseY, left, top, width, height, ms, button, planetType, new TextComponent(gravity), oxygen, temperatureColor, temperature);
	}

	public void teleportButtonManager(ResourceLocation bb, ResourceLocation bb2, int mouseX, int mouseY, int left, int top, int width, int height, PoseStack ms, ImageButtonPlacer button, Component planetType, Component gravity, boolean oxygen, String temperatureColor, int temperature) {
		if (GuiHelper.isHover(this.getBounds(left, top, width, height), mouseX, mouseY)) {

			List<Component> list = new ArrayList<Component>();

			list.add(new TextComponent("\u00A79" + typeTEXT.getString() + ": \u00A73" + planetType.getString()));
			list.add(new TextComponent("\u00A79" + gravityTEXT.getString() + ": \u00A73" + gravity.getString()));
			list.add(new TextComponent("\u00A79" + oxygenTEXT.getString() + ": \u00A7" + (oxygen ? "a" + oxygenTrueTEXT.getString() : "c" + oxygenFalseTEXT.getString())));
			list.add(new TextComponent("\u00A79" + temperatureTEXT.getString() + ": \u00A7" + temperatureColor + temperature));
			this.renderComponentTooltip(ms, list, mouseX, mouseY);

			button.setTexture(bb2);

		} else {
			button.setTexture(bb);
		}
	}

	public void spaceStationCreatorButtonManager(ResourceLocation brb, ResourceLocation brb2, ResourceLocation bbb, ResourceLocation bbb2, int mouseX, int mouseY, int left, int top, int width, int height, PoseStack ms, ImageButtonPlacer button, Component orbitType, Component gravity, boolean oxygen, String temperatureColor, int temperature) {

		if (spaceStationItemList) {
			button.setTexture(bbb);
		} else {
			button.setTexture(brb);
		}

		if (GuiHelper.isHover(this.getBounds(left, top, width, height), mouseX, mouseY)) {
			List<Component> list = new ArrayList<Component>();

			list.add(new TextComponent("\u00A79" + item_requirementTEXT.getString()));

			for (IngredientStack ingredientStack : recipe.getIngredientStacks()) {
				boolean check = this.getSpaceStationItemCheck(ingredientStack);
				Component component = Arrays.stream(ingredientStack.getIngredient().getItems()).findFirst().map(ItemStack::getHoverName).orElse(TextComponent.EMPTY);
				list.add(new TextComponent("\u00A78[\u00A76" + ingredientStack.getCount() + "\u00A78]" + (check ? "\u00A7a" : "\u00A7c") + " " + component.getString() + (ingredientStack.getCount() > 1 ? "'s" : "")));
			}

			list.add(new TextComponent("\u00A7c----------------"));
			list.add(new TextComponent("\u00A79" + typeTEXT.getString() + ": \u00A73" + orbitType.getString()));
			list.add(new TextComponent("\u00A79" + gravityTEXT.getString() + ": \u00A73" + gravity.getString()));
			list.add(new TextComponent("\u00A79" + oxygenTEXT.getString() + ": \u00A7" + (oxygen ? "a" + oxygenTrueTEXT.getString() : "c" + oxygenFalseTEXT.getString())));
			list.add(new TextComponent("\u00A79" + temperatureTEXT.getString() + ": \u00A7" + temperatureColor + temperature));
			this.renderComponentTooltip(ms, list, mouseX, mouseY);

			if (spaceStationItemList) {
				button.setTexture(bbb2);
			} else {
				button.setTexture(brb2);
			}
		}
	}

	public boolean getSpaceStationItemCheck(IngredientStack ingredientStack) {

		if (menu.player.getAbilities().instabuild || menu.player.isSpectator()) {
			return true;
		}

		Inventory inv = menu.player.getInventory();
		int itemStackCount = 0;

		for (int i = 0; i < inv.getContainerSize(); ++i) {
			ItemStack itemStack = inv.getItem(i);

			if (ingredientStack.testWithoutCount(itemStack)) {
				itemStackCount += itemStack.getCount();
			}

		}

		return itemStackCount >= ingredientStack.getCount();
	}

	public static Component getRocket(int tier) {
		if (tier == 1) {
			return rocket_t1TEXT;
		}
		else if (tier == 2) {
			return rocket_t2TEXT;
		}
		else if (tier == 3) {
			return rocket_t3TEXT;
		}
		else {
			return rocket_t4TEXT;
		}
	}

	public static Component tl(String string) {
		return new TranslatableComponent("gui." + BeyondEarthMod.MODID + ".planet_selection." + string);
	}

	public void backButtonManager(ResourceLocation bb, ResourceLocation bb2, int mouseX, int mouseY, int left, int top, int width, int height, ImageButtonPlacer button) {
		if (GuiHelper.isHover(this.getBounds(left, top, width, height), mouseX, mouseY)) {
			button.setTexture(bb2);
		} else {
			button.setTexture(bb);
		}
	}

	public ImageButtonPlacer addImageButton(int xIn, int yIn, int width, int height, ResourceLocation texture, int handler, Component title) {
		ImageButtonPlacer button = this.addRenderableWidget(new ImageButtonPlacer(xIn, yIn, width, height, 0, 0, 0, texture, width, height, (p_2130901) -> {
			BeyondEarthMod.PACKET_HANDLER.sendToServer(new PlanetSelectionGui.NetworkHandler(handler));
		}, title));
		return button;
	}

	public ImageButtonPlacer addSpaceStationImageButton(int xIn, int yIn, int width, int height, ResourceLocation texture, int handler, Component title, boolean condition) {
		ImageButtonPlacer button = this.addRenderableWidget(new ImageButtonPlacer(xIn, yIn, width, height, 0, 0, 0, texture, width, height, (p_2130901) -> {
			if (condition) {
				BeyondEarthMod.PACKET_HANDLER.sendToServer(new PlanetSelectionGui.NetworkHandler(handler));
			}
		}, title));
		return button;
	}

	public ImageButtonPlacer addImageButtonSetCategory(int xIn, int yIn, int width, int height, ResourceLocation texture, int newCategory, String rocket, int stage, Component title) {
		ImageButtonPlacer button = this.addRenderableWidget(new ImageButtonPlacer(xIn, yIn, width, height, 0, 0, 0, texture, width, height, (p_2130901) -> {
			if (checkTier(rocket, stage)) {
				this.Category = newCategory;
			}
		}, title));
		return button;
	}

	public ImageButtonPlacer addImageButtonBack(int xIn, int yIn, int width, int height, ResourceLocation texture, Component title) {
		ImageButtonPlacer button = this.addRenderableWidget(new ImageButtonPlacer(xIn, yIn, width, height, 0, 0, 0, texture, width, height, (p_2130901) -> {
			if (this.Category >= 1 && this.Category <= 4) {
				this.Category = 0;
			} else if (this.Category == 5) {
				this.Category = -1;
			} else if (this.Category >= 6 && this.Category <= 6) {
				this.Category = 5;
			} else {
				this.Category = this.Category - 1;
			}
		}, title));
		return button;
	}

	public ImageButtonPlacer addImageButtonSetSolarSystem(int xIn, int yIn, int width, int height, ResourceLocation texture, int newCategory, Component title) {
		ImageButtonPlacer button = this.addRenderableWidget(new ImageButtonPlacer(xIn, yIn, width, height, 0, 0, 0, texture, width, height, (p_2130901) -> {
			this.Category = newCategory;
		}, title));
		return button;
	}
}
