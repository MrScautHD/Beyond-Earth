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

import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, value = Dist.CLIENT)
public class ClientKeyEvents {

	@SubscribeEvent
	public static void keyPressed(InputEvent.Key event) {
		Minecraft mc = Minecraft.getInstance();
		Player player = mc.player;

		/** UP */
		sendKeyToServerAndClientHashMap(event, player, mc.options.keyUp, KeyVariables.KEY_UP, "key_up", KeyVariables.isHoldingUp(player));

		/** DOWN */
		sendKeyToServerAndClientHashMap(event, player, mc.options.keyDown, KeyVariables.KEY_DOWN, "key_down", KeyVariables.isHoldingDown(player));

		/** RIGHT */
		sendKeyToServerAndClientHashMap(event, player, mc.options.keyRight, KeyVariables.KEY_RIGHT, "key_right", KeyVariables.isHoldingRight(player));

		/** LEFT */
		sendKeyToServerAndClientHashMap(event, player, mc.options.keyLeft, KeyVariables.KEY_LEFT, "key_left", KeyVariables.isHoldingLeft(player));

		/** JUMP */
		sendKeyToServerAndClientHashMap(event, player, mc.options.keyJump, KeyVariables.KEY_JUMP, "key_jump", KeyVariables.isHoldingJump(player));

		/** ROCKET START KEY */
		sendKeyToServerMethod(event, player, KeyMappingRegistry.ROCKET_START, "rocket_start");

		/** SWITCH JET SUIT MODE KEY */
		sendKeyToServerMethod(event, player, KeyMappingRegistry.SWITCH_JET_SUIT_MODE, "switch_jet_suit_mode");
	}

	/**
	 *
	 * Send to server and client side!
	 * Save key press in KeyVariables
	 *
	 * */
	public static void sendKeyToServerAndClientHashMap(InputEvent.Key event, Player player, KeyMapping key, Map<UUID, Boolean> variableKey, String keyString, boolean isPressed) {
		if (player == null) {
			return;
		}

		if ((key.getKey().getValue() == event.getKey() && event.getAction() == GLFW.GLFW_RELEASE && isPressed) || (!key.isConflictContextAndModifierActive() && isPressed)) {
			variableKey.put(player.getUUID(), false);
			NetworkRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, false));
		}

		if (key.getKey().getValue() == event.getKey() && event.getAction() == GLFW.GLFW_PRESS && key.isConflictContextAndModifierActive() && !isPressed) {
			variableKey.put(player.getUUID(), true);
			NetworkRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, true));
		}
	}

	/**
	 *
	 * Send to server side only!
	 * Call a Method in KeyHandler.
	 *
	 * */
	public static void sendKeyToServerMethod(InputEvent.Key event, Player player, KeyMapping key, String keyString) {
		if (player == null || !key.isConflictContextAndModifierActive()) {
			return;
		}

		if (key.getKey().getValue() == event.getKey() && event.getAction() == GLFW.GLFW_PRESS) {
			NetworkRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, true));
		}
	}
}