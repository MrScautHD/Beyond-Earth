package net.mrscauthd.beyond_earth.registries.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SpritesRegistry {

    public static final ResourceLocation OXYGEN_BUBBLE = new ResourceLocation(BeyondEarth.MODID, "entities/tile_entity_box_oxygen_generator");

    @SubscribeEvent
    public static void register(TextureStitchEvent.Pre event) {
        event.addSprite(OXYGEN_BUBBLE);
    }
}
