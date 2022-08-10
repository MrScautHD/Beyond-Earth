package net.mrscauthd.beyond_earth.client.registries;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyMappingRegistry {

    public static final KeyMapping ROCKET_START = new KeyMapping("key." + BeyondEarth.MODID + ".rocket_start", GLFW.GLFW_KEY_SPACE, "key.categories." + BeyondEarth.MODID);
    public static final KeyMapping SWITCH_JET_SUIT_MODE = new KeyMapping("key." + BeyondEarth.MODID + ".switch_jet_suit_mode", GLFW.GLFW_KEY_V, "key.categories." + BeyondEarth.MODID);

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        registerProperties();

        event.register(ROCKET_START);
        event.register(SWITCH_JET_SUIT_MODE);
    }

    public static void registerProperties() {
        ROCKET_START.setKeyConflictContext(KeyConflictContext.IN_GAME);
        SWITCH_JET_SUIT_MODE.setKeyConflictContext(KeyConflictContext.IN_GAME);
    }
}
