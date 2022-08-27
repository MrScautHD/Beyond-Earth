package net.mrscauthd.beyond_earth.common.items.drills;

import com.mojang.brigadier.LiteralMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.network.chat.Component.literal;


public class DashDrill extends Item {


    public DashDrill(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return super.getUseAnimation(p_41452_);
    }
}
