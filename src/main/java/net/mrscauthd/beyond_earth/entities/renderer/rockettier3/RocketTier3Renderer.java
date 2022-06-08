package net.mrscauthd.beyond_earth.entities.renderer.rockettier3;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.entities.RocketTier3Entity;
import net.mrscauthd.beyond_earth.entities.renderer.VehicleRenderer;

@OnlyIn(Dist.CLIENT)
public class RocketTier3Renderer extends VehicleRenderer<RocketTier3Entity, RocketTier3Model<RocketTier3Entity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicles/rocket_t3.png");

    public RocketTier3Renderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RocketTier3Model<>(renderManagerIn.bakeLayer(RocketTier3Model.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketTier3Entity p_114482_) {
        return TEXTURE;
    }
}