package net.mrscauthd.beyond_earth.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.gauge.GaugeValueHelper;
import net.mrscauthd.beyond_earth.common.config.Config;
import net.mrscauthd.beyond_earth.common.entities.RoverEntity;
import net.mrscauthd.beyond_earth.common.registries.EntityRegistry;
import net.mrscauthd.beyond_earth.common.util.FluidUtil2;
import net.mrscauthd.beyond_earth.client.registries.ItemRendererRegistry;

import java.util.List;
import java.util.function.Consumer;

public class RoverItem extends VehicleItem {
    public static String FUEL_TAG = BeyondEarth.MODID + ":fuel";

    public RoverItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);

        int fuel = itemStack.getOrCreateTag().getInt(FUEL_TAG);
        int capacity = Config.ROVER_FUEL_BUCKETS.get() * FluidUtil2.BUCKET_SIZE;
        list.add(GaugeTextHelper.buildFuelStorageTooltip(GaugeValueHelper.getFuel(fuel, capacity), ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        
        if (world.isClientSide()) {
        	return InteractionResult.PASS;
        }
        
        BlockPos pos = context.getClickedPos();
        InteractionHand hand = context.getHand();
        ItemStack itemStack = context.getItemInHand();
        Level level = context.getLevel();

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        BlockPlaceContext blockplacecontext = new BlockPlaceContext(context);
        BlockPos blockpos = blockplacecontext.getClickedPos();
        Vec3 vec3 = Vec3.atBottomCenterOf(blockpos);
        AABB aabb = EntityRegistry.ROVER.get().getDimensions().makeBoundingBox(vec3.x(), vec3.y(), vec3.z());

        if (level.noCollision(aabb)) {

            AABB scanAbove = new AABB(x, y, z, x + 1, y + 1, z + 1);
            List<Entity> entities = player.getCommandSenderWorld().getEntitiesOfClass(Entity.class, scanAbove);

            if (entities.isEmpty()) {
                RoverEntity rover = new RoverEntity(EntityRegistry.ROVER.get(), world);

                rover.setPos((double) pos.getX() + 0.5D,  pos.getY() + 1, (double) pos.getZ() + 0.5D);
                double d0 = getYOffset(world, pos, true, rover.getBoundingBox());

                /** ROTATION */
                float f = (float) Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 5.626F) / 11.25F) * 11.25F;

                rover.moveTo((double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D, f + 180.0F, 0.0F);

                rover.yRotO = rover.getYRot();

                world.addFreshEntity(rover);

                rover.getEntityData().set(RoverEntity.FUEL, itemStack.getOrCreateTag().getInt(FUEL_TAG));

                if (!player.getAbilities().instabuild) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                } else {
                    player.swing(context.getHand(), true);
                }

                roverPlaceSound(pos, world);
            }
        }

        return super.useOn(context);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ItemRendererRegistry.ROVER_ITEM_RENDERER;
            }
        });
    }

    public static void roverPlaceSound(BlockPos pos, Level world) {
        world.playSound(null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1,1);
    }
}
