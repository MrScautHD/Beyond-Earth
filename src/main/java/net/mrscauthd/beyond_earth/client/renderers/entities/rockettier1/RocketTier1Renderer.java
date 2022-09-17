package net.mrscauthd.beyond_earth.client.renderers.entities.rockettier1;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.renderers.entities.VehicleRenderer;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.RocketTier1Entity;

@OnlyIn(Dist.CLIENT)
public class RocketTier1Renderer extends VehicleRenderer<RocketTier1Entity, RocketTier1Model<RocketTier1Entity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicle/rocket_t1.png");

    public RocketTier1Renderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RocketTier1Model<>(renderManagerIn.bakeLayer(RocketTier1Model.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketTier1Entity p_114482_) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(RocketTier1Entity p_115304_) {
        return p_115304_.getEntityData().get(IRocketEntity.ROCKET_START);
    }
}