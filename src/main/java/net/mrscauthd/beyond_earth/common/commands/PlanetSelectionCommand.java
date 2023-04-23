package net.mrscauthd.beyond_earth.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.mrscauthd.beyond_earth.client.screens.planetselection.PlanetSelectionScreen;
import net.mrscauthd.beyond_earth.client.util.ClientMethods;
import net.mrscauthd.beyond_earth.common.util.Methods;

public class PlanetSelectionCommand {

    public PlanetSelectionCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("beyond_earth")
                .then(Commands.literal("openPlanetSelection").requires(c -> c.hasPermission(2)).executes((Command) -> {

                    return openScreen(Command.getSource());
                })));



    }

    private int openScreen(CommandSourceStack source)  throws CommandSyntaxException {

        Entity entity = source.getEntity();

        if (entity instanceof Player player) {
            player.inventoryMenu.removed(player);
            Methods.openPlanetGui(player);
        }
    ;
        return 1;
    }


}
