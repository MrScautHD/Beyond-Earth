package net.mrscauthd.beyond_earth.entities.renderer.rockettier4;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.entities.RocketTier4Entity;
import net.mrscauthd.beyond_earth.entities.renderer.VehicleRenderer;

public class RocketTier4Renderer extends VehicleRenderer<RocketTier4Entity, RocketTier4Model<RocketTier4Entity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicles/rocket_t4.png");

    public RocketTier4Renderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RocketTier4Model<>(renderManagerIn.bakeLayer(RocketTier4Model.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketTier4Entity p_114482_) {
        return TEXTURE;
    }
}