package net.mrscauthd.beyond_earth.registries.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyMappingsRegistry {

    public static final KeyMapping ROCKET_START = new KeyMapping("key." + BeyondEarth.MODID + ".rocket_start", GLFW.GLFW_KEY_SPACE, "key.categories." + BeyondEarth.MODID);

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(ROCKET_START);
    }
}
