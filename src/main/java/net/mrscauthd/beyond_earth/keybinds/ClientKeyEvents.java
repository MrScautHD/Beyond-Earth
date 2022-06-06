package net.mrscauthd.beyond_earth.keybinds;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarthMod;
import net.mrscauthd.beyond_earth.events.ClientEventBusSubscriber;
import net.mrscauthd.beyond_earth.registries.NetworksRegistry;
import org.lwjgl.glfw.GLFW;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;

@Mod.EventBusSubscriber(modid = BeyondEarthMod.MODID, value = Dist.CLIENT)
public class ClientKeyEvents {

	@SubscribeEvent
	public static void clientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Minecraft mc = Minecraft.getInstance();

			if (mc.options.keyUp.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(0));
			}

			if (mc.options.keyDown.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(1));
			}

			if (mc.options.keyRight.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(2));
			}

			if (mc.options.keyLeft.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(3));
			}

			if (mc.options.keyJump.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(4));
			}

			if (mc.options.keyShift.isDown()) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(5));
			}
		}
	}

	@SubscribeEvent
	public static void keyPressed(InputEvent.KeyInputEvent event) {
		if (event.getKey() == ClientEventBusSubscriber.key1.getKey().getValue()) {
			if (event.getAction() == GLFW.GLFW_PRESS) {
				NetworksRegistry.PACKET_HANDLER.sendToServer(new KeyHandler(6));
			}
		}
	}
}