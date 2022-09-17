package net.mrscauthd.beyond_earth.client.renderers.entities.rover;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.renderers.entities.VehicleRenderer;
import net.mrscauthd.beyond_earth.common.entities.RoverEntity;

@OnlyIn(Dist.CLIENT)
public class RoverRenderer extends VehicleRenderer<RoverEntity, RoverModel<RoverEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicle/rover.png");

    public RoverRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RoverModel<>(renderManagerIn.bakeLayer(RoverModel.LAYER_LOCATION)), 0f);
    }

    @Override
    public ResourceLocation getTextureLocation(RoverEntity p_114482_) {
        return TEXTURE;
    }

    @Override
    public boolean shouldRender(RoverEntity p_114491_, Frustum p_114492_, double p_114493_, double p_114494_, double p_114495_) {
        return p_114492_.isVisible(p_114491_.getBoundingBox().inflate(4));
    }
}