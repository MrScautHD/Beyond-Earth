package net.mrscauthd.beyond_earth.client.events;

import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.NetworksRegistry;
import net.mrscauthd.beyond_earth.client.registries.KeyMappingsRegistry;
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
		int pressedKey = event.getKey();
		int action = event.getAction();

		/** UP */
		sendKeyToServerVariable(player, pressedKey, action, mc.options.keyUp, "key_up");

		/** DOWN */
		sendKeyToServerVariable(player, pressedKey, action, mc.options.keyDown, "key_down");

		/** RIGHT */
		sendKeyToServerVariable(player, pressedKey, action, mc.options.keyRight, "key_right");

		/** LEFT */
		sendKeyToServerVariable(player, pressedKey, action, mc.options.keyLeft, "key_left");

		/** JUMP */
		sendKeyToServerVariable(player, pressedKey, action, mc.options.keyJump, "key_jump");

		/** ROCKET START KEY */
		sendKeyToServerMethod(player, pressedKey, action, KeyMappingsRegistry.ROCKET_START, "rocket_start");

		/** SWITCH JET SUIT MODE KEY */
		sendKeyToServerMethod(player, pressedKey, action, KeyMappingsRegistry.SWITCH_JET_SUIT_MODE, "switch_jet_suit_mode");
	}

	public static void sendKeyToServerVariable(Player player, int pressedKey, int action, KeyMapping key, String keyString) {
		if (player == null) {
			return;
		}

		if ((key.getKey().getValue() == pressedKey && action == GLFW.GLFW_RELEASE) || !key.isConflictContextAndModifierActive()) {
			NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, false));
			return;
		}

		if (key.getKey().getValue() == pressedKey && action == GLFW.GLFW_PRESS) {
			NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, true));
		}
	}


	public static void sendKeyToServerMethod(Player player, int pressedKey, int action, KeyMapping key, String keyString) {
		if (player == null || !key.isConflictContextAndModifierActive()) {
			return;
		}

		if (key.getKey().getValue() == pressedKey && action == GLFW.GLFW_PRESS) {
			NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(keyString, true));
			System.out.println("test");
		}
	}
}