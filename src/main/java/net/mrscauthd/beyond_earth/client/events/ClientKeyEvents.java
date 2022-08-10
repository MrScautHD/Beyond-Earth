package net.mrscauthd.beyond_earth.client.events;

import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.registries.KeyMappingRegistry;
import net.mrscauthd.beyond_earth.common.keybinds.KeyVariables;
import net.mrscauthd.beyond_earth.common.registries.NetworkRegistry;
import net.mrscauthd.beyond_earth.common.keybinds.KeyHandler;
import org.lwjgl.glfw.GLFW;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, value = Dist.CLIENT)
public class ClientKeyEvents {

	@SubscribeEvent
	public static void keyPressed(InputEvent.Key event) {
		Minecraft mc = Minecraft.getInstance();
		Player player = mc.player;

		/** UP */
		sendKeyToServerVariable(event, player, mc.options.keyUp, "key_up", KeyVariables.isHoldingUp(player));

		/** DOWN */
		sendKeyToServerVariable(event, player, mc.options.keyDown, "key_down", KeyVariables.isHoldingDown(player));

		/** RIGHT */
		sendKeyToServerVariable(event, player, mc.options.keyRight, "key_right", KeyVariables.isHoldingRight(player));

		/** LEFT */
		sendKeyToServerVariable(event, player, mc.options.keyLeft, "key_left", KeyVariables.isHoldingLeft(player));

		/** JUMP */
		sendKeyToServerVariable(event, player, mc.options.keyJump, "key_jump", KeyVariables.isHoldingJump(player));

		/** ROCKET START KEY */
		sendKeyToServerMethod(event, player, KeyMappingRegistry.ROCKET_START, "rocket_start");

		/** SWITCH JET SUIT MODE KEY */
		sendKeyToServerMethod(event, player, KeyMappingRegistry.SWITCH_JET_SUIT_MODE, "switch_jet_suit_mode");
	}

	public static void sendKeyToServerVariable(InputEvent.Key event, Player player, KeyMapping key, String keyString, boolean isPressed) {
		if (player == null) {
			return;
		}

		if ((key.getKey().getValue() == event.getKey() && event.getAction() == GLFW.GLFW_RELEASE && isPressed) || (!key.isConflictContextAndModifierActive() && isPressed)) {
			NetworkRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, false));
		}

		if (key.getKey().getValue() == event.getKey() && event.getAction() == GLFW.GLFW_PRESS && key.isConflictContextAndModifierActive() && !isPressed) {
			NetworkRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, true));
		}
	}

	public static void sendKeyToServerMethod(InputEvent.Key event, Player player, KeyMapping key, String keyString) {
		if (player == null || !key.isConflictContextAndModifierActive()) {
			return;
		}

		if (key.getKey().getValue() == event.getKey() && event.getAction() == GLFW.GLFW_PRESS) {
			NetworkRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, true));
		}
	}
}