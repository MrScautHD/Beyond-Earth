package net.mrscauthd.beyond_earth.common.items;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.screens.planetselection.helper.PlanetSelectionScreenHelper;
import vazkii.patchouli.api.PatchouliAPI;


public class AstroduxItem extends Item {

    public static final Component PATCHOULI_ERROR = Component.translatable("message." + BeyondEarth.MODID + ".error.patchouli");

    public AstroduxItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        if (ModList.get().isLoaded("patchouli")) {
            if (level.isClientSide) {
                vazkii.patchouli.api.PatchouliAPI.get().openBookGUI(new ResourceLocation("beyond_earth:astrodux"));
            }
        } else {
            if (level.isClientSide) {
                //I Know this part is stupid but...
                playerIn.sendSystemMessage(PATCHOULI_ERROR);
            }
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));

    }



}
