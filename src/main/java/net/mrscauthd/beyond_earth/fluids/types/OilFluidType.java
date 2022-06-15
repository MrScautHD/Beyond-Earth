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

public class OilFluidType extends FluidType {
    public OilFluidType(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockPathTypes getBlockPathType(FluidState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, boolean canFluidLog) {
        return canFluidLog ? super.getBlockPathType(state, level, pos, mob, true) : null;
    }

    @Override
    public void initializeClient(Consumer<IFluidTypeRenderProperties> consumer) {
        consumer.accept(new IFluidTypeRenderProperties() {
            private static final ResourceLocation UNDERWATER_LOCATION = new ResourceLocation(BeyondEarth.MODID, "textures/blocks/under_oil.png");
            private static final ResourceLocation WATER_STILL = new ResourceLocation(BeyondEarth.MODID, "textures/blocks/fluid_oil_still");
            private static final ResourceLocation WATER_FLOW = new ResourceLocation(BeyondEarth.MODID, "textures/blocks/fluid_oil_flow");
            private static final ResourceLocation WATER_OVERLAY = new ResourceLocation(BeyondEarth.MODID, "textures/blocks/oil_overlay");

            @Override
            public ResourceLocation getStillTexture() {
                return WATER_STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return WATER_FLOW;
            }

            @Nullable
            @Override
            public ResourceLocation getOverlayTexture() {
                return WATER_OVERLAY;
            }

            @Override
            public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                return UNDERWATER_LOCATION;
            }

            @Override
            public int getColorTint() {
                return 0x00000000;
            }

            @Override
            public int getColorTint(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                return 0xFF000000;
            }
        });
    }
}
