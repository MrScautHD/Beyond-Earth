package net.mrscauthd.beyond_earth.common.entities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.mrscauthd.beyond_earth.common.registries.EntityRegistry;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

import java.util.Random;

public class IceSpitEntity extends AbstractArrow implements ItemSupplier {
    public IceSpitEntity(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    public IceSpitEntity(EntityType<? extends AbstractArrow> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemsRegistry.ICE_SHARD.get());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return new ItemStack(ItemsRegistry.ICE_SHARD.get(), 1);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return null;
    }

    @Override
    protected void doPostHurtEffects(LivingEntity p_36744_) {
        super.doPostHurtEffects(p_36744_);
        p_36744_.setArrowCount(p_36744_.getArrowCount() - 1);
    }

    @Override
    public void tick() {
        super.tick();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Vec3 vec = this.getDeltaMovement();

        this.level.addParticle(ParticleTypes.SPIT, x - vec.x, y - vec.y, z - vec.z, 0, 0.001, 0);
        this.level.addParticle(ParticleTypes.ITEM_SNOWBALL, x - vec.x, y - vec.y, z - vec.z, 0, 0.001, 0);

        if (this.inGround) {
            if (!this.level.isClientSide) {
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    public static IceSpitEntity shoot(LivingEntity entity, LivingEntity target, int damage) {
        IceSpitEntity entityArrow = new IceSpitEntity(EntityRegistry.ICE_SPIT_ENTITY.get(), entity, entity.level);

        double d0 = target.getY() + (double) target.getEyeHeight() - 1.1;
        double d1 = target.getX() - entity.getX();
        double d3 = target.getZ() - entity.getZ();

        entityArrow.shoot(d1, d0 - entityArrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 0.7f * 2, 12.0F);
        entityArrow.setSilent(true);
        entityArrow.setBaseDamage(damage);
        entityArrow.setKnockback(1);
        entityArrow.setCritArrow(false);

        entity.level.addFreshEntity(entityArrow);

        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
        return entityArrow;
    }
}
