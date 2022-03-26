package net.mrscauthd.beyond_earth.entity.renderer.globe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.ModInit;
import net.mrscauthd.beyond_earth.globe.GlobeBlock;
import net.mrscauthd.beyond_earth.globe.GlobeTileEntity;

@OnlyIn(Dist.CLIENT)
public class GlobeRenderer<T extends GlobeTileEntity> extends BlockEntityWithoutLevelRenderer implements BlockEntityRenderer<GlobeTileEntity>, BlockEntityRendererProvider<T> {

    /** TEXTURES */
    public static final ResourceLocation EARTH_GLOBE = new ResourceLocation(BeyondEarthMod.MODID, "textures/blocks/globes/earth_globe.png");
    public static final ResourceLocation MOON_GLOBE = new ResourceLocation(BeyondEarthMod.MODID, "textures/blocks/globes/moon_globe.png");
    public static final ResourceLocation MARS_GLOBE = new ResourceLocation(BeyondEarthMod.MODID, "textures/blocks/globes/mars_globe.png");
    public static final ResourceLocation MERCURY_GLOBE = new ResourceLocation(BeyondEarthMod.MODID, "textures/blocks/globes/mercury_globe.png");
    public static final ResourceLocation VENUS_GLOBE = new ResourceLocation(BeyondEarthMod.MODID, "textures/blocks/globes/venus_globe.png");
    public static final ResourceLocation GLACIO_GLOBE = new ResourceLocation(BeyondEarthMod.MODID, "textures/blocks/globes/glacio_globe.png");

    /** MODELS */
    private GlobeModel model;
    private GlobeModel itemModel;

    public GlobeRenderer(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
        super(p_172550_, p_172551_);
    }

    @Override
    public void render(GlobeTileEntity p_112307_, float particleTicks, PoseStack matrixStackIn, MultiBufferSource buffer, int combinedLight, int p_112312_) {
        if (!(p_112307_.getLevel().getBlockState(p_112307_.getBlockPos()).getBlock() instanceof GlobeBlock)) {
            return;
        }

        BlockState blockstate = p_112307_.getBlockState();
        Direction direction = blockstate.getValue(GlobeBlock.FACING);

        matrixStackIn.pushPose();

        matrixStackIn.translate(0.5D, 1.5D, 0.5D);
        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(direction.toYRot()));

        /** Animation */
        this.model.setupAnim(p_112307_, particleTicks);

        VertexConsumer vertexBuilder;

        /** TEXTURE BINDING */
        if (blockstate.is(ModInit.EARTH_GLOBE_BLOCK.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(EARTH_GLOBE));
        } else if (blockstate.is(ModInit.MOON_GLOBE_BLOCK.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(MOON_GLOBE));
        } else if (blockstate.is(ModInit.MARS_GLOBE_BLOCK.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(MARS_GLOBE));
        } else if (blockstate.is(ModInit.MERCURY_GLOBE_BLOCK.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(MERCURY_GLOBE));
        } else if (blockstate.is(ModInit.VENUS_GLOBE_BLOCK.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(VENUS_GLOBE));
        } else {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(GLACIO_GLOBE));
        }

        this.model.renderToBuffer(matrixStackIn, vertexBuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStackIn.popPose();
    }

    @Override
    public void renderByItem(ItemStack p_108830_, ItemTransforms.TransformType p_108831_, PoseStack matrixStackIn, MultiBufferSource buffer, int combinedLight, int p_108835_) {
        matrixStackIn.pushPose();

        matrixStackIn.translate(0.5D, 1.5D, 0.5D);
        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);

        VertexConsumer vertexBuilder;

        /** TEXTURE BINDING */
        if (p_108830_.is(ModInit.EARTH_GLOBE_ITEM.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(EARTH_GLOBE));
        } else if (p_108830_.is(ModInit.MOON_GLOBE_ITEM.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(MOON_GLOBE));
        } else if (p_108830_.is(ModInit.MARS_GLOBE_ITEM.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(MARS_GLOBE));
        } else if (p_108830_.is(ModInit.MERCURY_GLOBE_ITEM.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(MERCURY_GLOBE));
        } else if (p_108830_.is(ModInit.VENUS_GLOBE_ITEM.get())) {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(VENUS_GLOBE));
        } else {
            vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCullZOffset(GLACIO_GLOBE));
        }

        /** The Event that Register the Model is to Slow, so we do it like that */
        if (this.itemModel == null) {
            this.itemModel = new GlobeModel(Minecraft.getInstance().getEntityModels().bakeLayer(GlobeModel.LAYER_LOCATION));
        }
        this.itemModel.renderToBuffer(matrixStackIn, vertexBuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStackIn.popPose();
    }

    @Override
    public BlockEntityRenderer<T> create(Context p_173571_) {
        this.model = new GlobeModel(p_173571_.bakeLayer(GlobeModel.LAYER_LOCATION));
        return this::render;
    }
}