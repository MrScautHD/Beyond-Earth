package net.mrscauthd.beyond_earth.client.renderers.armors;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public abstract class ISpaceArmorModel<T extends LivingEntity> extends HumanoidModel<T> {

    public ISpaceArmorModel(ModelPart modelPart) {
        super(modelPart);
    }

    public VertexConsumer getVertex(RenderType p_115186_, boolean p_115187_, boolean p_115188_) {
        MultiBufferSource p_115185_ = Minecraft.getInstance().renderBuffers().bufferSource();
        return p_115188_ ? VertexMultiConsumer.create(p_115185_.getBuffer(p_115187_ ? RenderType.armorGlint() : RenderType.armorEntityGlint()), p_115185_.getBuffer(p_115186_)) : p_115185_.getBuffer(p_115186_);
    }
}
