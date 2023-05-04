package net.mrscauthd.beyond_earth.client.renderers.entities.rocket;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.renderers.entities.VehicleRenderer;
import net.mrscauthd.beyond_earth.common.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.common.entities.RocketEntity;

@OnlyIn(Dist.CLIENT)
public class RocketRenderer extends VehicleRenderer<RocketEntity, RocketModel<RocketEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicle/rocket.png");

    public RocketRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RocketModel<>(renderManagerIn.bakeLayer(RocketModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RocketEntity p_114482_) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(RocketEntity p_115304_) {
        return p_115304_.getEntityData().get(IRocketEntity.ROCKET_START);
    }
}