package net.mrscauthd.beyond_earth.common.menus;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.NASAWorkbenchBlockEntity;
import net.mrscauthd.beyond_earth.common.blocks.entities.machines.RocketUpgraderBlockEntity;

public class RocketUpgraderResultSlot extends Slot {

    private final RocketUpgraderBlockEntity blockEntity;

    public RocketUpgraderResultSlot(Container inventory, int slotIndex, int xPos, int yPos,
                                    RocketUpgraderBlockEntity blockEntity) {
        super(inventory, slotIndex, xPos, yPos);
        this.blockEntity = blockEntity;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return RocketUpgraderBlockEntity.isRocketUpgrade(stack);
    }

    public RocketUpgraderBlockEntity getBlockEntity() {
        return this.blockEntity;
    }
}