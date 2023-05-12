package net.mrscauthd.beyond_earth.client.registries;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.client.renderers.entities.globe.GlobeItemRenderer;
import net.mrscauthd.beyond_earth.client.renderers.entities.rocket.RocketItemRenderer;
import net.mrscauthd.beyond_earth.client.renderers.entities.rover.RoverItemRenderer;

@OnlyIn(Dist.CLIENT)
public class ItemRendererRegistry {

    /** RENDERERS */
    public static final RocketItemRenderer<?> ROCKET_ITEM_RENDERER = new RocketItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final RoverItemRenderer<?> ROVER_ITEM_RENDERER = new RoverItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    public static final GlobeItemRenderer<?> GLOBE_ITEM_RENDERER = new GlobeItemRenderer<>(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
}
