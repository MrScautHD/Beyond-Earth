package net.mrscauthd.beyond_earth.client.renderers.entities.rockettier4;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.renderers.entities.VehicleRenderer;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.RocketEntity;

public class RocketTier4Renderer extends VehicleRenderer<RocketEntity, RocketTier4Model<RocketEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicle/rocket_t4.png");

    public RocketTier4Renderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RocketTier4Model<>(renderManagerIn.bakeLayer(RocketTier4Model.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketEntity p_114482_) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(RocketEntity rocket) {
        return rocket.getEntityData().get(IRocketEntity.ROCKET_START);
    }
}