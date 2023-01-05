package net.mrscauthd.beyond_earth.client.renderers.entities.flag;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.Direction;
import net.minecraft.core.UUIDUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mrscauthd.beyond_earth.common.blocks.FlagBlock;
import net.mrscauthd.beyond_earth.common.blocks.entities.FlagBlockEntity;

@OnlyIn(Dist.CLIENT)
public class FlagHeadRenderer implements BlockEntityRenderer<FlagBlockEntity> {

	public FlagHeadRenderer(BlockEntityRendererProvider.Context context) {

	}

	public boolean shouldRenderOffScreen(FlagBlockEntity p_112306_) {
		return true;
	}

	public int getViewDistance() {
		return 256;
	}

	@Override
	public boolean shouldRender(FlagBlockEntity p_173568_, Vec3 p_173569_) {
		return Vec3.atCenterOf(p_173568_.getBlockPos()).multiply(1.0D, 0.0D, 1.0D).closerThan(p_173569_.multiply(1.0D, 0.0D, 1.0D), this.getViewDistance());
	}

	private static final Map<FlagBlock.ISkullType, FlagHeadModel> MODELS = Util.make(Maps.newHashMap(), (p_209262_0_) -> {

		Minecraft minecraft = Minecraft.getInstance();
		Map<String, ModelPart> map = Map.of("head", new FlagHeadModel(minecraft.getEntityModels().bakeLayer(FlagHeadModel.LAYER_LOCATION)).head);
		ModelPart modelPart = new ModelPart(Collections.emptyList(), map);

		FlagHeadModel genericheadmodel = new FlagHeadModel(modelPart);

		p_209262_0_.put(FlagBlock.Types.PLAYER, genericheadmodel);
	});

	private static final Map<FlagBlock.ISkullType, ResourceLocation> SKINS = Util.make(Maps.newHashMap(), (p_209263_0_) -> {
		p_209263_0_.put(FlagBlock.Types.PLAYER, DefaultPlayerSkin.getDefaultSkin());
	});

	@Override
	public void render(FlagBlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if (tileEntityIn.getBlockState().getValue(FlagBlock.HALF) == DoubleBlockHalf.UPPER) {

			BlockState blockstate = tileEntityIn.getBlockState();
			boolean flag = blockstate.getBlock() instanceof FlagBlock;
			Direction direction = flag ? blockstate.getValue(FlagBlock.FACING) : null;
			render(direction, blockstate.getValue(FlagBlock.FACING).toYRot(), ((FlagBlock) blockstate.getBlock()).getSkullType(), tileEntityIn.getPlayerProfile(), 0, matrixStackIn, bufferIn, combinedLightIn);
		}
	}

	public static void render(@Nullable Direction directionIn, float p_228879_1_, FlagBlock.ISkullType skullType, @Nullable GameProfile gameProfileIn, float animationProgress, PoseStack matrixStackIn, MultiBufferSource buffer, int combinedLight) {
		FlagHeadModel genericheadmodel = MODELS.get(skullType);
		matrixStackIn.pushPose();
		if (directionIn.get2DDataValue() == 0) {
			matrixStackIn.translate(1D, 0.031D, 0.25D);
		}
		else if (directionIn.get2DDataValue() == 1) {
			matrixStackIn.translate(0.75D, 0.031D, 1D);
		}
		else if (directionIn.get2DDataValue() == 2) {
			matrixStackIn.translate(0D, 0.031D, 0.75D);
		}
		else if (directionIn.get2DDataValue() == 3) {
			matrixStackIn.translate(0.25D, 0.031D, 0D);
		}

		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);

		VertexConsumer ivertexbuilder = buffer.getBuffer(getRenderType(skullType, gameProfileIn));
		genericheadmodel.setupAnim(animationProgress, p_228879_1_, 0.0F);
		genericheadmodel.renderToBuffer(matrixStackIn, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getInstance().renderBuffers().bufferSource().endBatch();
		matrixStackIn.popPose();
	}

	private static RenderType getRenderType(FlagBlock.ISkullType skullType, @Nullable GameProfile gameProfileIn) {
		ResourceLocation resourcelocation = SKINS.get(skullType);
		if (skullType == FlagBlock.Types.PLAYER && gameProfileIn != null) {
			Minecraft minecraft = Minecraft.getInstance();
			Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().getInsecureSkinInformation(gameProfileIn);
			return map.containsKey(MinecraftProfileTexture.Type.SKIN) ? RenderType.entityTranslucent(minecraft.getSkinManager().registerTexture(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN)) : RenderType.entityCutoutNoCull(DefaultPlayerSkin.getDefaultSkin(UUIDUtil.getOrCreatePlayerUUID(gameProfileIn)));
		} else {
			return RenderType.entityCutoutNoCullZOffset(resourcelocation);
		}
	}
}
