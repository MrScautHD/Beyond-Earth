package net.mrscauthd.beyond_earth.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeaconBlock;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.BeyondEarth;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceBaliseItem extends Item {

    Boolean coordsSet = false;
    int baliseX;
    int baliseY;
    int baliseZ;
    String baliseLevel;

    public SpaceBaliseItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        BlockPos blockPos = pContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);

        if (blockState.getBlock() instanceof BeaconBlock beaconBlock) {

            ItemStack stack = pContext.getItemInHand();

            CompoundTag coords = stack.getOrCreateTagElement("coords");
            coords.putInt("x", blockPos.getX());
            coords.putInt("y", blockPos.getY());
            coords.putInt("z", blockPos.getZ());
            coords.putString("level", level.dimension().location().toString());
            coordsSet = true;

            baliseX = coords.getInt("x");
            baliseY = coords.getInt("y");
            baliseZ = coords.getInt("z");
            baliseLevel = coords.getString("level");

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        CompoundTag coords = stack.getTagElement("coords");

        if (coords == null) {

            player.sendSystemMessage(Component.literal("No coords found"));
            return InteractionResultHolder.fail(stack);
        } else if (!pLevel.getBlockState(new BlockPos(coords.getInt("x"), coords.getInt("y"), coords.getInt("z"))).is(Blocks.BEACON)) {

            player.sendSystemMessage(Component.translatable("message.beyond_earth.space_balise.no_beacon", coords.getInt("x"), coords.getInt("y"), coords.getInt("z"), coords.getString("level")));
            coordsSet = false;
        }


        return InteractionResultHolder.success(stack);

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (!coordsSet) {
            pTooltipComponents.add(Component.translatable("message.beyond_earth.space_balise.right_click"));

        } else {
            pTooltipComponents.add(Component.translatable("message.beyond_earth.space_balise.beacon_coordinates", baliseX, baliseY, baliseZ, baliseLevel));

        }

    }

}