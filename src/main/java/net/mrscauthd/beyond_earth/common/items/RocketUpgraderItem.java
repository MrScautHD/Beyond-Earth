package net.mrscauthd.beyond_earth.common.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class RocketUpgraderItem extends Item {
    int fuelCapacityModifier;

    public RocketUpgraderItem(Properties pProperties, int fuelCapacityModifier) {
        super(pProperties);
        this.fuelCapacityModifier = fuelCapacityModifier;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        super.inventoryTick(stack, level, entity, p_41407_, p_41408_);

        CompoundTag modifier = stack.getOrCreateTagElement("rocket_upgrade");
        modifier.putInt("fuelCapacity", fuelCapacityModifier);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Fuel : " + fuelCapacityModifier));
    }

}