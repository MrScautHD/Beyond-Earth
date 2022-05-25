package net.mrscauthd.beyond_earth.items;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.blocks.RocketLaunchPad;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.entities.RocketTier1Entity;
import net.mrscauthd.beyond_earth.entities.renderer.VehicleRenderer;
import net.mrscauthd.beyond_earth.events.forge.PlaceRocketEvent;
import net.mrscauthd.beyond_earth.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.gauge.GaugeValueHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class IRocketItem extends VehicleItem {

    public static final String fuelTag = BeyondEarthMod.MODID + ":fuel";
    public static final String bucketTag = BeyondEarthMod.MODID + ":buckets";

    public IRocketItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        InteractionHand hand = context.getHand();
        ItemStack itemStack = context.getItemInHand();

        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        /** POS */
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (state.getBlock() instanceof RocketLaunchPad && state.getValue(RocketLaunchPad.STAGE)) {

            if (this.getRocketHighTest(level, pos, this.getRocketHigh())) {

                /** CHECK IF NO ENTITY ON THE LAUNCH PAD */
                AABB scanAbove = new AABB(x - 0, y - 0, z - 0, x + 1, y + 1, z + 1);
                List<Entity> entities = player.getCommandSenderWorld().getEntitiesOfClass(Entity.class, scanAbove);

                if (entities.isEmpty()) {
                    IRocketEntity rocket = this.getRocket(context.getLevel());

                    /** SET PRE POS */
                    rocket.setPos(pos.getX() + 0.5D,  pos.getY() + 1, pos.getZ() + 0.5D);

                    double d0 = this.getYOffset(level, pos, true, rocket.getBoundingBox());
                    float f = (float) Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 45.0F) / 90.0F) * 90.0F;

                    /** SET FINAL POS */
                    rocket.moveTo(pos.getX() + 0.5D, pos.getY() + d0, pos.getZ() + 0.5D, f, 0.0F);

                    rocket.yRotO = rocket.getYRot();

                    level.addFreshEntity(rocket);

                    /** SET TAGS */
                    rocket.getEntityData().set(RocketTier1Entity.FUEL, itemStack.getOrCreateTag().getInt(fuelTag));
                    rocket.getEntityData().set(RocketTier1Entity.BUCKETS, itemStack.getOrCreateTag().getInt(bucketTag));

                    /** CALL PLACE ROCKET EVENT */
                    MinecraftForge.EVENT_BUS.post(new PlaceRocketEvent(rocket, context));

                    /** ITEM REMOVE */
                    if (!player.getAbilities().instabuild) {
                        player.setItemInHand(hand, ItemStack.EMPTY);
                    }

                    /** PLACE SOUND */
                    this.rocketPlaceSound(pos, level);

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return super.useOn(context);
    }

    public Boolean getRocketHighTest(Level level, BlockPos pos, int high) {
        List<Boolean> flag = new ArrayList<>();

        int x = pos.getX();
        int y = pos.getY() + 1;
        int z = pos.getZ();

        /** CHECK IF ALL FREE TO PLACE THE ROCKET */
        for (int f1 = y; f1 < y + high; f1++) {
            BlockPos pos2 = new BlockPos(x, f1, z);


            flag.add(level.getBlockState(pos2).isAir());
        }

        return !flag.contains(false);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);

        int fuel = itemstack.getOrCreateTag().getInt(fuelTag) / 3;
        list.add(GaugeTextHelper.buildBlockTooltip(GaugeTextHelper.getPercentText(GaugeValueHelper.getFuel(fuel, 100))));
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {

            @OnlyIn(Dist.CLIENT)
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return IRocketItem.this.getRenderer();
            }
        });
    }

    @OnlyIn(Dist.CLIENT)
    public abstract BlockEntityWithoutLevelRenderer getRenderer();

    public abstract int getRocketHigh();

    public abstract IRocketEntity getRocket(Level level);

    public void rocketPlaceSound(BlockPos pos, Level world) {
        world.playSound(null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1,1);
    }
}
