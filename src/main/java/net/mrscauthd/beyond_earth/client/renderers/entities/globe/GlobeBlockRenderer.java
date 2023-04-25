package net.mrscauthd.beyond_earth.client.renderers.entities.globe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.common.blocks.GlobeBlock;
import net.mrscauthd.beyond_earth.common.blocks.entities.GlobeTileEntity;

@OnlyIn(Dist.CLIENT)
public class GlobeBlockRenderer<T extends GlobeTileEntity> implements BlockEntityRenderer<GlobeTileEntity>, BlockEntityRendererProvider<T> {

    private GlobeModel model;

    public GlobeBlockRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(GlobeTileEntity p_112307_, float particleTicks, PoseStack matrixStackIn, MultiBufferSource buffer, int combinedLight, int p_112312_) {
        BlockState state = p_112307_.getLevel().getBlockState(p_112307_.getBlockPos());

        if (!(state.getBlock() instanceof GlobeBlock)) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        BlockState blockstate = p_112307_.getBlockState();
        Direction direction = blockstate.getValue(GlobeBlock.FACING);

        matrixStackIn.pushPose();

        matrixStackIn.translate(0.5D, 1.5D, 0.5D);
        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(direction.toYRot()));

        if (this.model == null) {
            this.model = new GlobeModel(mc.getEntityModels().bakeLayer(GlobeModel.LAYER_LOCATION));
        }

        /** Animation */
        this.model.setupAnim(p_112307_, particleTicks);

        VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityTranslucent(((GlobeBlock) state.getBlock()).texture));

        this.model.renderToBuffer(matrixStackIn, vertexBuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderBuffers().bufferSource().endBatch();

        matrixStackIn.popPose();
    }

    @Override
    public BlockEntityRenderer<T> create(Context p_173571_) {
        return this::render;
    }
}