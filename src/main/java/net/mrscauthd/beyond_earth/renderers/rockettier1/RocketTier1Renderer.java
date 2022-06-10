package net.mrscauthd.beyond_earth.renderers.rockettier1;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.entities.RocketTier1Entity;
import net.mrscauthd.beyond_earth.renderers.VehicleRenderer;

@OnlyIn(Dist.CLIENT)
public class RocketTier1Renderer extends VehicleRenderer<RocketTier1Entity, RocketTier1Model<RocketTier1Entity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicles/rocket_t1.png");

    public RocketTier1Renderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RocketTier1Model<>(renderManagerIn.bakeLayer(RocketTier1Model.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketTier1Entity p_114482_) {
        return TEXTURE;
    }
}