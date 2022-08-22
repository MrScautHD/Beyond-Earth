package net.mrscauthd.beyond_earth.items;

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
import net.minecraftforge.client.IItemRenderProperties;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.entities.RoverEntity;
import net.mrscauthd.beyond_earth.events.ClientEventBusSubscriber;
import net.mrscauthd.beyond_earth.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.gauge.IGaugeValue;
import net.mrscauthd.beyond_earth.registries.EntitiesRegistry;

import java.util.List;
import java.util.function.Consumer;

public class RoverItem extends VehicleItem implements IFuelVehicleItem {
    public static String fuelTag = BeyondEarthMod.MODID + ":fuel";

    public RoverItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);

        IGaugeValue fuelGauge = this.getFuelGauge(p_41421_);
        p_41423_.add(GaugeTextHelper.buildBlockTooltip(GaugeTextHelper.getStorageText(fuelGauge)));
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
        AABB aabb = EntitiesRegistry.ROVER.get().getDimensions().makeBoundingBox(vec3.x(), vec3.y(), vec3.z());

        if (level.noCollision(aabb)) {

            AABB scanAbove = new AABB(x - 0, y - 0, z - 0, x + 1, y + 1, z + 1);
            List<Entity> entities = player.getCommandSenderWorld().getEntitiesOfClass(Entity.class, scanAbove);

            if (entities.isEmpty()) {
                RoverEntity rover = new RoverEntity(EntitiesRegistry.ROVER.get(), world);

                rover.setPos((double) pos.getX() + 0.5D,  pos.getY() + 1, (double) pos.getZ() + 0.5D);
                double d0 = this.getYOffset(world, pos, true, rover.getBoundingBox());

                /** ROTATION */
                float f = (float) Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 5.626F) / 11.25F) * 11.25F;

                rover.moveTo((double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D, f + 180.0F, 0.0F);

                rover.yRotO = rover.getYRot();

                world.addFreshEntity(rover);

                rover.setFuel(this.getFuel(itemStack));

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
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return ClientEventBusSubscriber.ROVER_ITEM_RENDERER;
            }
        });
    }

    @Override
    public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        super.fillItemCategory(p_41391_, p_41392_);
        if (this.allowdedIn(p_41391_)) {
            ItemStack itemStack = new ItemStack(this);
            this.setFuel(itemStack, this.getFuelCapacity(itemStack));
            p_41392_.add(itemStack);
        }
    }

    public static void roverPlaceSound(BlockPos pos, Level world) {
        world.playSound(null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1,1);
    }

    @Override
    public int getFuel(ItemStack itemStack) {
    	return itemStack.getOrCreateTag().getInt(fuelTag);
    }

    @Override
    public void setFuel(ItemStack itemStack, int fuel) {
    	itemStack.getOrCreateTag().putInt(fuelTag, Mth.clamp(fuel, 0, this.getFuelCapacity(itemStack)));
    }

    @Override
    public int getFuelCapacity(ItemStack itemStack) {
    	return RoverEntity.FUEL_CAPACITY;
    }
}
