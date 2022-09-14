package net.mrscauthd.beyond_earth.client.renderers.armors;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;

public class ISpaceArmorModel<T extends LivingEntity> extends HumanoidModel<T> {

    public ISpaceArmorModel(ModelPart modelPart) {
        super(modelPart);
    }

    public VertexConsumer getVertex(RenderType p_115186_, boolean p_115187_, boolean p_115188_) {
        MultiBufferSource p_115185_ = Minecraft.getInstance().renderBuffers().bufferSource();
        return p_115188_ ? VertexMultiConsumer.create(p_115185_.getBuffer(p_115187_ ? RenderType.armorGlint() : RenderType.armorEntityGlint()), p_115185_.getBuffer(p_115186_)) : p_115185_.getBuffer(p_115186_);
    }

    public void updateProperties(HumanoidModel<?> model, LivingEntity entity) {
        HumanoidModel<?> livingModel = (HumanoidModel<LivingEntity>) ((LivingEntityRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity)).getModel();

        model.attackTime = livingModel.attackTime;
        model.riding = livingModel.riding;
        model.young = livingModel.young;
        model.leftArmPose = livingModel.leftArmPose;
        model.rightArmPose = livingModel.rightArmPose;
        model.crouching = livingModel.crouching;
        model.head.copyFrom(livingModel.head);
        model.body.copyFrom(livingModel.body);
        model.rightArm.copyFrom(livingModel.rightArm);
        model.leftArm.copyFrom(livingModel.leftArm);
        model.rightLeg.copyFrom(livingModel.rightLeg);
        model.leftLeg.copyFrom(livingModel.leftLeg);
    }
}
