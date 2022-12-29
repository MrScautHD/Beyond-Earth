package net.mrscauthd.beyond_earth.common.menus.planetselection;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.IContainerFactory;
import net.mrscauthd.beyond_earth.common.registries.ContainerRegistry;

public class PlanetSelectionMenu {

    public static class GuiContainerFactory implements IContainerFactory<GuiContainer> {
        public GuiContainer create(int id, Inventory inv, FriendlyByteBuf extraData) {
            return new GuiContainer(id, inv, extraData);
        }
    }

    public static class GuiContainer extends AbstractContainerMenu {
        private final int tier;
        private final Player player;

        public GuiContainer(int id, Inventory inv, FriendlyByteBuf extraData) {
            super(ContainerRegistry.PLANET_SELECTION_GUI.get(), id);
            this.tier = extraData.readInt();
            this.player = inv.player;
        }

        public ItemStack quickMoveStack(Player p_219987_, int p_219988_) {
            return ItemStack.EMPTY;
        }

        @Override
        public boolean stillValid(Player player) {
            return !player.isDeadOrDying();
        }

        public int getTier() {
            return tier;
        }

        public Player getPlayer() {
            return player;
        }
    }
}
