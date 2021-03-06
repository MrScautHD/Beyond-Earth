package net.mrscauthd.beyond_earth.client.events;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.NetworksRegistry;
import net.mrscauthd.beyond_earth.client.registries.KeyMappingsRegistry;
import net.mrscauthd.beyond_earth.common.keybinds.KeyHandler;
import org.lwjgl.glfw.GLFW;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, value = Dist.CLIENT)
public class ClientKeyEvents {

	@SubscribeEvent
	public static void clientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Minecraft mc = Minecraft.getInstance();

			if (mc.options.keyRight.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(0));
			}

			if (mc.options.keyLeft.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(1));
			}

			if (mc.options.keyJump.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(2));
			}
		}
	}

	@SubscribeEvent
	public static void keyPressed(InputEvent.Key event) {
		/** ROCKET START KEY */
		if (event.getKey() == KeyMappingsRegistry.ROCKET_START.getKey().getValue()) {
			if (event.getAction() == GLFW.GLFW_PRESS) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(3));
			}
		}

		/** SWITCH JET SUIT MODE KEY */
		if (event.getKey() == KeyMappingsRegistry.SWITCH_JET_SUIT_MODE.getKey().getValue()) {
			if (event.getAction() == GLFW.GLFW_PRESS) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(4));
			}
		}
	}
}