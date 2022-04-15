package net.mrscauthd.beyond_earth.items;

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
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.blocks.RocketLaunchPad;
import net.mrscauthd.beyond_earth.entities.IRocketEntity;
import net.mrscauthd.beyond_earth.events.forgeevents.RocketPlaceEvent;
import net.mrscauthd.beyond_earth.gauge.GaugeTextHelper;
import net.mrscauthd.beyond_earth.gauge.GaugeValueHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class IRocketItem extends VehicleItem {

    public static final String fuelTag = BeyondEarthMod.MODID + ":fuel";
    public static final String bucketTag = BeyondEarthMod.MODID + ":buckets";

    public IRocketItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, @Nullable Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);

        int fuel = itemstack.getOrCreateTag().getInt(fuelTag) / 3;
        list.add(GaugeTextHelper.buildBlockTooltip(GaugeTextHelper.getPercentText(GaugeValueHelper.getFuel(fuel, 100))));
    }

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = world.getBlockState(pos);
		InteractionHand hand = context.getHand();
		ItemStack itemStack = context.getItemInHand();

		if (world.isClientSide()) {
			return InteractionResult.PASS;
		}

		if (this.testLaunchPad(state) && this.testAirHeight(world, pos)) {
			AABB scanAbove = new AABB(pos);
			List<Entity> entities = player.getCommandSenderWorld().getEntitiesOfClass(Entity.class, scanAbove);

			if (entities.isEmpty()) {
				IRocketEntity rocket = this.createRocketEntity(context);

				rocket.setPos((double) pos.getX() + 0.5D, pos.getY() + 1, (double) pos.getZ() + 0.5D);
				double d0 = getYOffset(world, pos, true, rocket.getBoundingBox());

				// ROTATION
				float f = (float) Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 45.0F) / 90.0F) * 90.0F;

				rocket.moveTo((double) pos.getX() + 0.5D, (double) pos.getY() + d0, (double) pos.getZ() + 0.5D, f, 0.0F);

				rocket.yRotO = rocket.getYRot();

				world.addFreshEntity(rocket);

				rocket.getEntityData().set(IRocketEntity.FUEL, itemStack.getOrCreateTag().getInt(fuelTag));
				rocket.getEntityData().set(IRocketEntity.BUCKETS, itemStack.getOrCreateTag().getInt(bucketTag));
				MinecraftForge.EVENT_BUS.post(new RocketPlaceEvent(rocket, context));

				if (!player.getAbilities().instabuild) {
					player.setItemInHand(hand, ItemStack.EMPTY);
				} else {
					player.swing(context.getHand(), true);
				}

				rocketPlaceSound(pos, world);
			}
		}

		return super.useOn(context);
	}

	public abstract IRocketEntity createRocketEntity(UseOnContext context);
	
	public boolean testLaunchPad(BlockState state) {
		return state.getBlock() instanceof RocketLaunchPad && state.getValue(RocketLaunchPad.STAGE);
	}

	public boolean testAirHeight(Level world, BlockPos placePos) {
		int airHeight = this.getRequireAirHeight();
		int x = placePos.getX();
		int y = placePos.getY();
		int z = placePos.getZ();

		for (int i = 0; i < airHeight; i++) {
			BlockPos checkPos = new BlockPos(x, y + i + 1, z);
			if (!world.getBlockState(checkPos).isAir()) {
				return false;
			}
		}
		return true;
	}

	public int getRequireAirHeight() {
		return 4;
	}

    public static void rocketPlaceSound(BlockPos pos, Level world) {
        world.playSound(null, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1,1);
    }
}
