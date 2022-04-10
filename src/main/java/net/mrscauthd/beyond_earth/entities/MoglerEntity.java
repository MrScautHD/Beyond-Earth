package net.mrscauthd.beyond_earth.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import net.mrscauthd.beyond_earth.config.Config;
import net.mrscauthd.beyond_earth.registries.EntitiesRegistry;

import javax.annotation.Nullable;

public class MoglerEntity extends Hoglin {
    public MoglerEntity(EntityType<MoglerEntity> type, Level world) {
        super(type, world);
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6)
                .add(Attributes.ATTACK_KNOCKBACK, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 6);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_149900_, AgeableMob p_149901_) {
        MoglerEntity moglerentity = EntitiesRegistry.MOGLER.get().create(p_149900_);
        if (moglerentity != null) {
            moglerentity.setPersistenceRequired();
        }
        return moglerentity;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor p_21686_, MobSpawnType p_21687_) {
        BlockState blockState = level.getBlockState(new BlockPos(this.getX(), this.getY() - 1, this.getZ()));

        if (blockState.is(Blocks.LAVA) || blockState.is(Blocks.AIR)) {
            return false;
        }

        return super.checkSpawnRules(p_21686_, p_21687_);
    }

    @Override
    public void tick() {
        super.tick();
        if (!Config.MOGLER_SPAWN.get()) {
            if (!this.level.isClientSide) {
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }
}
