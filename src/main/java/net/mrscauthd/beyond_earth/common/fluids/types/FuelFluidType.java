package net.mrscauthd.beyond_earth.common.fluids.types;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.mrscauthd.beyond_earth.BeyondEarth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class FuelFluidType extends FluidType {
    public FuelFluidType(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockPathTypes getBlockPathType(FluidState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, boolean canFluidLog) {
        return canFluidLog ? super.getBlockPathType(state, level, pos, mob, true) : null;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            private static final ResourceLocation UNDER_FLUID = new ResourceLocation(BeyondEarth.MODID, "textures/block/fluids/under_fuel.png");
            private static final ResourceLocation FLUID_STILL = new ResourceLocation(BeyondEarth.MODID, "block/fluids/fuel_still");
            private static final ResourceLocation FLUID_FLOW = new ResourceLocation(BeyondEarth.MODID, "block/fluids/fuel_flow");
            private static final ResourceLocation FLUID_OVERLAY = new ResourceLocation(BeyondEarth.MODID, "block/fluids/fuel_overlay");

            @Override
            public ResourceLocation getStillTexture() {
                return FLUID_STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return FLUID_FLOW;
            }

            @Nullable
            @Override
            public ResourceLocation getOverlayTexture() {
                return FLUID_OVERLAY;
            }

            @Override
            public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                return UNDER_FLUID;
            }

            @Override
            public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return new Vector3f(0.08F, 0.08F, 0.0F);
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                nearDistance = -8.0F;
                farDistance = 96.0F;

                Entity entity = camera.getEntity();

                if (camera.getEntity() instanceof LocalPlayer) {
                    LocalPlayer localplayer = (LocalPlayer) entity;
                    farDistance *= Math.max(0.25F, localplayer.getWaterVision());
                }

                if (farDistance > renderDistance) {
                    farDistance = renderDistance;
                    shape = FogShape.CYLINDER;
                }

                RenderSystem.setShaderFogStart(nearDistance);
                RenderSystem.setShaderFogEnd(farDistance);
                RenderSystem.setShaderFogShape(shape);
            }
        });
    }
}
