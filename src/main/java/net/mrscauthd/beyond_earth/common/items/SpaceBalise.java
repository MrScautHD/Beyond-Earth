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
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeaconBlock;

import net.minecraft.world.level.block.state.BlockState;
import net.mrscauthd.beyond_earth.BeyondEarth;

import javax.annotation.Nullable;
import java.util.List;

public class SpaceBalise extends Item {

    Boolean coordsSet = false;
    int baliseX;
    int baliseZ;
    String baliseLevel;

    public SpaceBalise(Properties pProperties) {
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
            baliseZ = coords.getInt("z");
            baliseLevel = coords.getString("level");

            BeyondEarth.LOGGER.debug("x: " + coords.getInt("x") + " y: " + coords.getInt("y") + " z: " + coords.getInt("z"));
            BeyondEarth.LOGGER.debug("Level: " + coords.getString("level"));


            stack.setHoverName(Component.literal("Balise: " + coords.getInt("x") + " " + coords.getInt("y") + " " + coords.getInt("z")));

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
        }
        BeyondEarth.LOGGER.info("x: " + coords.getInt("x") + " y: " + coords.getInt("y") + " z: " + coords.getInt("z"));
        BeyondEarth.LOGGER.info("Level: " + coords.getString("level"));


        return InteractionResultHolder.success(stack);

    }

    @Override
    public Component getDescription() {
        return Component.literal("Right click on a beacon to set the coords");
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (!coordsSet) {
            pTooltipComponents.add(Component.literal("Right click on a beacon to set the coords"));

        } else {
            pTooltipComponents.add(Component.literal("Balise: x: " + baliseX + ", y: " + baliseZ + ", world: " + baliseLevel));

        }

    }




    public int getBeaconX(ItemStack stack) {
        CompoundTag coords = stack.getTagElement("coords");
        return coords.getInt("x");
    }

    public int getBeaconZ(ItemStack stack) {
        CompoundTag coords = stack.getTagElement("coords");
        return coords.getInt("z");
    }
    public String getBeaconLevel(ItemStack stack) {
        CompoundTag coords = stack.getTagElement("coords");
        return coords.getString("level");
    }

}
