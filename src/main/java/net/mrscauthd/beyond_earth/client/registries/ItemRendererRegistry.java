package net.mrscauthd.beyond_earth.client.registries;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.client.renderers.entities.globe.GlobeItemRenderer;
import net.mrscauthd.beyond_earth.client.renderers.entities.rockettier1.RocketTier1ItemRenderer;
import net.mrscauthd.beyond_earth.client.renderers.entities.rockettier2.RocketTier2ItemRenderer;
import net.mrscauthd.beyond_earth.client.renderers.entities.rockettier3.RocketTier3ItemRenderer;
import net.mrscauthd.beyond_earth.client.renderers.entities.rockettier4.RocketTier4ItemRenderer;
import net.mrscauthd.beyond_earth.client.renderers.entities.rover.RoverItemRenderer;

@OnlyIn(Dist.CLIENT)
public class ItemRendererRegistry {

    /** RENDERERS */
    public static final RocketTier1ItemRenderer<?> ROCKET_TIER_1_ITEM_RENDERER = new RocketTier1ItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final RocketTier2ItemRenderer<?> ROCKET_TIER_2_ITEM_RENDERER = new RocketTier2ItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final RocketTier3ItemRenderer<?> ROCKET_TIER_3_ITEM_RENDERER = new RocketTier3ItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final RocketTier4ItemRenderer<?> ROCKET_TIER_4_ITEM_RENDERER = new RocketTier4ItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final RoverItemRenderer<?> ROVER_ITEM_RENDERER = new RoverItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final GlobeItemRenderer<?> GLOBE_ITEM_RENDERER = new GlobeItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
}