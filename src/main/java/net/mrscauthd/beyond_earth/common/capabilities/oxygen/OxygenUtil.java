package net.mrscauthd.beyond_earth.common.capabilities.oxygen;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public class OxygenUtil {

    public static <T> LazyOptional<T> getOxygenCapability(Capability<T> capability, @Nullable NonNullSupplier<IOxygenStorage> oxygenStorage) {
        if (capability == null) {
            return LazyOptional.empty();
        } else if (capability == OxygenProvider.OXYGEN) {
            return LazyOptional.of(oxygenStorage).cast();
        }

        return LazyOptional.empty();
    }

    public static IOxygenStorage getItemStackOxygenStorage(ItemStack itemStack) {
        IOxygenStorage oxygenStorage = itemStack.getCapability(OxygenProvider.OXYGEN).orElse(null);

        if (oxygenStorage != null) {
            return oxygenStorage;
        }

        return null;
    }

    /**
     * test receive oxygen to itemstack
     * 
     * @param itemStack
     * @return
     */
    public static boolean canReceive(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }

        IOxygenStorage storageInItemStack = getItemStackOxygenStorage(itemStack);
        return storageInItemStack != null && storageInItemStack.receiveOxygen(1, true) > 0;
    }

    /**
     * test extract oxygen from itemstack
     * 
     * @param itemStack
     * @return
     */
    public static boolean canExtract(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return false;
        }

        IOxygenStorage storageInItemStack = getItemStackOxygenStorage(itemStack);
        return storageInItemStack != null && storageInItemStack.extractOxygen(1, true) > 0;
    }

    public static ItemStack makeFull(ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return itemStack;
        }

        IOxygenStorage storageInItemStack = getItemStackOxygenStorage(itemStack);

        if (storageInItemStack != null) {
            storageInItemStack.receiveOxygen(storageInItemStack.getMaxCapacity(), false);
        }

        return itemStack;
    }

    public static boolean fillSink(IItemHandlerModifiable itemHandler, int sinkItemSlot, IOxygenStorage source,
            int transfer) {
        ItemStack sinkItemStack = itemHandler.getStackInSlot(sinkItemSlot);

        return fillSinkCapability(source, sinkItemStack, transfer) > 0;
    }

    public static int fillSinkCapability(IOxygenStorage source, ItemStack sinkItemStack, int transfer) {
        IOxygenStorage sink = getItemStackOxygenStorage(sinkItemStack);
        return tryTransfer(sink, source, transfer);
    }

    public static boolean drainSource(IItemHandlerModifiable itemHandler, int sourceItemSlot, IOxygenStorage sink,
            int transfer) {
        ItemStack sourceItemStack = itemHandler.getStackInSlot(sourceItemSlot);

        return drainSourceCapability(sink, sourceItemStack, transfer) > 0;
    }

    public static int drainSourceCapability(IOxygenStorage sink, ItemStack sourceItemStack, int transfer) {
        IOxygenStorage source = getItemStackOxygenStorage(sourceItemStack);
        return tryTransfer(sink, source, transfer);
    }

    public static int tryTransfer(IOxygenStorage sink, IOxygenStorage source, int transfer) {
        int received = 0;

        if (sink != null && source != null && transfer > 0) {
            int extractableAmount = source.extractOxygen(transfer, true);

            if (extractableAmount > 0) {
                int receivableAmount = sink.receiveOxygen(extractableAmount, true);

                if (receivableAmount > 0) {
                    int extracted = source.extractOxygen(receivableAmount, false);

                    if (extracted > 0) {
                        received = sink.receiveOxygen(extracted, false);
                    }
                }
            }
        }

        return received;
    }

    private OxygenUtil() {

    }
}
