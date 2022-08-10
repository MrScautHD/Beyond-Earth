package net.mrscauthd.beyond_earth.common.registries;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.entities.*;
import net.mrscauthd.beyond_earth.common.entities.alien.AlienEntity;
import net.mrscauthd.beyond_earth.common.entities.pygro.PygroEntity;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeRegistry {

    @SubscribeEvent
    public static void register(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.ALIEN.get(), AlienEntity.setCustomAttributes().build());
        event.put(EntityRegistry.PYGRO.get(), PygroEntity.setCustomAttributes().build());
        event.put(EntityRegistry.PYGRO_BRUTE.get(), PygroBruteEntity.setCustomAttributes().build());
        event.put(EntityRegistry.MOGLER.get(), MoglerEntity.setCustomAttributes().build());
        event.put(EntityRegistry.MARTIAN_RAPTOR.get(), MartianRaptor.setCustomAttributes().build());
        event.put(EntityRegistry.ALIEN_ZOMBIE.get(), AlienZombieEntity.setCustomAttributes().build());
        event.put(EntityRegistry.STAR_CRAWLER.get(), StarCrawlerEntity.setCustomAttributes().build());
    }
}
