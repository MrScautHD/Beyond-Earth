package net.mrscauthd.beyond_earth.client.renderers.entities.lander;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.renderers.entities.VehicleRenderer;
import net.mrscauthd.beyond_earth.common.entities.LanderEntity;

@OnlyIn(Dist.CLIENT)
public class LanderRenderer extends VehicleRenderer<LanderEntity, LanderModel<LanderEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(BeyondEarth.MODID, "textures/vehicle/lander.png");

    public LanderRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new LanderModel<>(renderManagerIn.bakeLayer(LanderModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(LanderEntity p_114482_) {
        return TEXTURE;
    }

    @Override
    public boolean shouldRender(LanderEntity p_114491_, Frustum p_114492_, double p_114493_, double p_114494_, double p_114495_) {
        return p_114492_.isVisible(p_114491_.getBoundingBox().inflate(3));
    }
}