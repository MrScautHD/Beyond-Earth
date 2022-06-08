package net.mrscauthd.beyond_earth.entities.renderer.rockettier2;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.entities.RocketTier2Entity;
import net.mrscauthd.beyond_earth.entities.renderer.VehicleRenderer;

@OnlyIn(Dist.CLIENT)
public class RocketTier2Renderer extends VehicleRenderer<RocketTier2Entity, RocketTier2Model<RocketTier2Entity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicles/rocket_t2.png");

    public RocketTier2Renderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RocketTier2Model<>(renderManagerIn.bakeLayer(RocketTier2Model.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketTier2Entity p_114482_) {
        return TEXTURE;
    }
}