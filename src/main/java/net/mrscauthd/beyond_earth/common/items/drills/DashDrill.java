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
    /**
    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        System.out.println("1");

        return super.use(p_41432_, p_41433_, p_41434_);
    }
    */
}
