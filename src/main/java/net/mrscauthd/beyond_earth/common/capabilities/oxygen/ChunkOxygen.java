package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.CapabilityRegistry;
import net.mrscauthd.beyond_earth.common.util.Methods;
import net.mrscauthd.beyond_earth.common.util.Planets;
import net.mrscauthd.beyond_earth.common.util.Planets.Planet;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID)
public class ChunkOxygen implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public record AirCheckTest(BlockPos pos, Direction from, ServerLevel level) {
    }

    public record AirCheckResult(boolean solid, boolean pass, int amount) {
    }

    public static class DefaultCheck implements Function<AirCheckTest, AirCheckResult> {

        private static final AirCheckResult OPEN = new AirCheckResult(false, true, 0);
        private static final AirCheckResult BLOCKED = new AirCheckResult(true, true, 0);
        private static final AirCheckResult LEAVES = new AirCheckResult(false, false, 1);

        public static final DefaultCheck DEFAULTS = new DefaultCheck();

        public List<Function<AirCheckTest, AirCheckResult>> children = Lists.newArrayList();

        @Override
        public AirCheckResult apply(AirCheckTest t) {
            for (var child : children) {
                var result = child.apply(t);
                if (!result.pass()) {
                    return result;
                }
            }
            BlockState state = t.level.getBlockState(t.pos());
            if (state.is(BlockTags.LEAVES))
                return LEAVES;
            boolean airTight = state.isCollisionShapeFullBlock(t.level, t.pos());
            return airTight ? BLOCKED : OPEN;
        }
    }

    public class SectionOxygen implements INBTSerializable<ByteArrayTag> {
        byte[] O2 = new byte[16 * 16 * 16];

        public SectionOxygen() {
            Arrays.fill(O2, Byte.MIN_VALUE);
        }

        public SectionOxygen(byte[] O2) {
            this();
            this.O2 = O2;
        }

        @Override
        public ByteArrayTag serializeNBT() {
            return new ByteArrayTag(O2);
        }

        @Override
        public void deserializeNBT(ByteArrayTag nbt) {
            this.O2 = nbt.getAsByteArray();
        }

        private void propagate(Map<SectionPos, SectionOxygen> nearby,
                Function<AirCheckTest, AirCheckResult> shouldSpread, BlockPos origin, Level level, int depth) {
            if (!(level instanceof ServerLevel serverlevel))
                return;
            MutableBlockPos testPoint = new MutableBlockPos();

            Map<BlockPos, SectionOxygen> dirty = Maps.newHashMap();

            int x = origin.getX();
            int y = origin.getY();
            int z = origin.getZ();
            int index = (x & 15) | (y & 15) << 4 | (z & 15) << 8;
            int amt = this.getO2(index);

            Direction minDown = null;
            int dO2max = 1;
            int total = amt;

            directions: for (Direction d : Direction.values()) {
                int x2 = x + d.getStepX();
                int y2 = y + d.getStepY();
                int z2 = z + d.getStepZ();
                testPoint.setWithOffset(origin, d);
                var checkTest = new AirCheckTest(testPoint, minDown, serverlevel);
                var checkResult = shouldSpread.apply(checkTest);
                // Don't try checking inside blocks that report as solid.
                if (checkResult.solid())
                    continue;
                SectionPos pos = SectionPos.of(testPoint);
                SectionOxygen flowTo = this;
                int index2 = (x2 & 15) | (y2 & 15) << 4 | (z2 & 15) << 8;
                // This means we need to check nearby
                if (x2 > 15 || x2 < 0 || y2 > 15 || y2 < 0 || z2 > 15 || z2 < 0) {
                    flowTo = nearby.get(pos);
                }
                // This could possibly happen if we try adding too close to a loaded chunk
                // border!
                if (flowTo == null) {
                    continue directions;
                }
                int oldAmt = flowTo.getO2(index2);
                oldAmt = Math.min(255, checkResult.amount() + oldAmt);
                int dO2 = Math.abs(amt - oldAmt);
                if (dO2 > dO2max) {
                    minDown = d;
                    dO2max = dO2;
                    total = amt + oldAmt;
                }
            }
            if (minDown != null) {
                int newAmt = total / 2;
                int x2 = x + minDown.getStepX();
                int y2 = y + minDown.getStepY();
                int z2 = z + minDown.getStepZ();
                testPoint.setWithOffset(origin, minDown);
                SectionOxygen flowTo = this;
                int index2 = (x2 & 15) | (y2 & 15) << 4 | (z2 & 15) << 8;
                // This means we need to check nearby
                if (x2 > 15 || x2 < 0 || y2 > 15 || y2 < 0 || z2 > 15 || z2 < 0) {
                    flowTo = nearby.get(SectionPos.of(testPoint));
                }
                int old = flowTo.getO2(index2);
                if (old != newAmt) {
                    flowTo.setO2(index2, newAmt);
                    dirty.put(testPoint.immutable(), flowTo);
                    if (Math.abs(amt - newAmt) > 5)
                        serverlevel.sendParticles(ParticleTypes.CLOUD, testPoint.getX(), testPoint.getY() + 0.5D,
                                testPoint.getZ(), 1, 0.0D, 0.1D, 0.0D, 0.001D);
                }
                if (newAmt != amt) {
                    this.setO2(index, (newAmt + total % 2));
                    if (Math.abs(amt - newAmt) > 5)
                        serverlevel.sendParticles(ParticleTypes.CLOUD, origin.getX(), origin.getY() + 0.5D,
                                origin.getZ(), 1, 0.1D, 0.1D, 0.1D, 0.001D);
                }
            }
            if (depth < 10 && !dirty.isEmpty()) {
                dirty.forEach((pos, section) -> {
                    section.propagate(nearby, shouldSpread, pos, level, depth + 1);
                });
                propagate(nearby, shouldSpread, origin, serverlevel, depth + 1);
            }
        }

        public void propagate(Map<SectionPos, SectionOxygen> nearby,
                Function<AirCheckTest, AirCheckResult> shouldSpread, BlockPos origin, Level level) {
            propagate(nearby, shouldSpread, origin, level, 0);
        }

        private void setO2(int index, int amt) {
            byte set = (byte) Mth.clamp(amt + Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MAX_VALUE);
            O2[index] = set;
        }

        private int getO2(int index) {
            return ((int) O2[index] - Byte.MIN_VALUE);
        }

        private int getO2(int x, int y, int z) {
            int index = (x & 15) | (y & 15) << 4 | (z & 15) << 8;
            return getO2(index);
        }

        public void setO2(int x, int y, int z, int amt) {
            int index = (x & 15) | (y & 15) << 4 | (z & 15) << 8;
            setO2(index, amt);
        }
    }

    @SubscribeEvent
    public static void onChunkCapababilityAttach(AttachCapabilitiesEvent<LevelChunk> event) {
        event.addCapability(new ResourceLocation(BeyondEarth.MODID, "chunk_oxygen"),
                new ChunkOxygen(event.getObject().getLevel()));
    }

    /**
     * 
     * @param O2 - O2 amount to check
     * @return Whether this can be breathed
     */
    public static boolean isBreatheable(int O2) {
        return O2 > 30;
    }

    /**
     * 
     * @param O2 - O2 amount to check
     * @return Whether this is considered vacuum, negative values represent under
     *         fluids instead.
     */
    public static boolean isVacuum(int O2) {
        return O2 <= 30 && O2 >= 0;
    }

    private final Int2ObjectArrayMap<SectionOxygen> O2 = new Int2ObjectArrayMap<>();
    private final Level level;
    private final boolean infiniteO2;
    private final Planet planet;

    public ChunkOxygen(Level level) {
        this.level = level;
        infiniteO2 = !Methods.isSpaceLevelWithoutOxygen(level);
        this.planet = Planets.getLocationForPlanet(level);
    }

    public int getO2(BlockPos pos) {
        if (infiniteO2) {
            if (this.planet == null)
                return 100;

            int y = pos.getY();
            int base = 100;
            int dy = level.getSeaLevel();
            float g = planet.g;
            float T = planet.temperature + 273.15f;
            int h = y - dy;

            return (int) (base * Math.exp(-g * 1.5 * h / T));
        }
        int y = SectionPos.blockToSectionCoord(pos.getY());
        SectionOxygen oxygen = O2.computeIfAbsent(y, newY -> new SectionOxygen());
        return oxygen.getO2(pos.getX(), pos.getY(), pos.getZ());
    }

    public boolean hasInfiniteO2() {
        return infiniteO2;
    }

    /**
     * 
     * @param pos    - where to add to
     * @param toAdd  - Maximum amount to add
     * @param spread - whether to try to spread
     * @return the amount not added from toAdd
     */
    public int addO2(BlockPos pos, int toAdd, boolean spread) {
        if (infiniteO2)
            return toAdd > 0 ? toAdd : 0;
        int ret = 0;
        int O2 = this.getO2(pos);
        int newO2 = 0;
        int tmpO2 = O2 + toAdd;
        if (tmpO2 > 255) {
            ret = tmpO2 - 255;
            newO2 = 255;
        } else if (tmpO2 > 0) {
            newO2 = tmpO2;
        }
        int y = SectionPos.blockToSectionCoord(pos.getY());
        SectionOxygen oxygen = this.O2.computeIfAbsent(y, newY -> new SectionOxygen());
        oxygen.setO2(pos.getX(), pos.getY(), pos.getZ(), newO2);
        SectionPos section = SectionPos.of(pos);
        Map<SectionPos, SectionOxygen> nearby = new Object2ObjectOpenHashMap<>();
        nearby.put(section, oxygen);
        for (Direction d : Direction.values()) {
            SectionPos relative = section.offset(d.getStepX(), d.getStepY(), d.getStepZ());
            if (level.hasChunk(relative.getX(), relative.getZ())) {
                LevelChunk chunk = level.getChunk(relative.getX(), relative.getZ());
                ChunkOxygen chunkO2 = chunk.getCapability(CapabilityRegistry.CHUNK_OXYGEN).orElse(null);
                nearby.put(relative, chunkO2.O2.computeIfAbsent(relative.getY(), newY -> new SectionOxygen()));
            }
        }
        if (spread)
            oxygen.propagate(nearby, DefaultCheck.DEFAULTS, pos, level);
        return ret;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        O2.forEach((i, o2) -> {
            tag.put("" + i, o2.serializeNBT());
        });
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        nbt.getAllKeys().forEach(s -> {
            try {
                int i = Integer.parseInt(s);
                byte[] o2 = nbt.getByteArray(s);
                O2.put(i, new SectionOxygen(o2));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });
    }

    private final LazyOptional<ChunkOxygen> cap = LazyOptional.of(() -> this);

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CapabilityRegistry.CHUNK_OXYGEN.orEmpty(cap, this.cap);
    }
}
