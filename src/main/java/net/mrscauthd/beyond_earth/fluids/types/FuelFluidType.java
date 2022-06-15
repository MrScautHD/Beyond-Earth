package net.mrscauthd.beyond_earth.fluids.types;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.IFluidTypeRenderProperties;
import net.minecraftforge.fluids.FluidType;
import net.mrscauthd.beyond_earth.BeyondEarth;
import org.jetbrains.annotations.Nullable;

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
    public void initializeClient(Consumer<IFluidTypeRenderProperties> consumer) {
        consumer.accept(new IFluidTypeRenderProperties() {
            private static final ResourceLocation UNDER_FLUID = new ResourceLocation(BeyondEarth.MODID, "textures/blocks/under_fuel.png");
            private static final ResourceLocation FLUID_STILL = new ResourceLocation(BeyondEarth.MODID, "textures/blocks/fluid_fuel_still");
            private static final ResourceLocation FLUID_FLOW = new ResourceLocation(BeyondEarth.MODID, "textures/blocks/fluid_fuel_flow");
            private static final ResourceLocation FLUID_OVERLAY = new ResourceLocation(BeyondEarth.MODID, "textures/blocks/fuel_overlay");

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
            public int getColorTint() {
                return 0xF3F700;
            }

            @Override
            public int getColorTint(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                return 0xFF000000;
            }
        });
    }
}
