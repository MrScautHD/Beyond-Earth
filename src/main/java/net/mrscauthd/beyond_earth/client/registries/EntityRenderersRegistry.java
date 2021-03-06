package net.mrscauthd.beyond_earth.client.registries;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.entities.renderers.alien.AlienRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.alienzombie.AlienZombieRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.flag.FlagHeadRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.globe.GlobeBlockRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.lander.LanderRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.martianraptor.MartianRaptorRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.mogler.MoglerRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.pygro.PygroModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.pygro.PygroRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.pygrobrute.PygroBruteRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.rockettier1.RocketTier1Renderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.rockettier2.RocketTier2Renderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.rockettier3.RocketTier3Renderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.rockettier4.RocketTier4Renderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.rover.RoverRenderer;
import net.mrscauthd.beyond_earth.client.entities.renderers.starcrawler.StarCrawlerRenderer;
import net.mrscauthd.beyond_earth.common.registries.BlockEntitiesRegistry;
import net.mrscauthd.beyond_earth.common.registries.EntitiesRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityRenderersRegistry {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        /** MOBS */
        event.registerEntityRenderer(EntitiesRegistry.ALIEN.get(), AlienRenderer::new);
        event.registerEntityRenderer(EntitiesRegistry.ALIEN_ZOMBIE.get(), AlienZombieRenderer::new);
        event.registerEntityRenderer(EntitiesRegistry.STAR_CRAWLER.get(), StarCrawlerRenderer::new);
        event.registerEntityRenderer(EntitiesRegistry.PYGRO.get(), (p_174068_) -> {
            return new PygroRenderer(p_174068_, PygroModel.LAYER_LOCATION, ModelLayers.PIGLIN_INNER_ARMOR, ModelLayers.PIGLIN_OUTER_ARMOR);
        });
        event.registerEntityRenderer(EntitiesRegistry.PYGRO_BRUTE.get(), (p_174068_) -> {
            return new PygroBruteRenderer(p_174068_, PygroModel.LAYER_LOCATION, ModelLayers.PIGLIN_BRUTE_INNER_ARMOR, ModelLayers.PIGLIN_BRUTE_OUTER_ARMOR);
        });
        event.registerEntityRenderer(EntitiesRegistry.MOGLER.get(), MoglerRenderer::new);
        event.registerEntityRenderer(EntitiesRegistry.MARTIAN_RAPTOR.get(), MartianRaptorRenderer::new);

        /** PROJECT TILES */
        event.registerEntityRenderer(EntitiesRegistry.ICE_SPIT_ENTITY.get(), renderManager -> new ThrownItemRenderer(renderManager, 1, true));

        /** VEHICLES */
        event.registerEntityRenderer(EntitiesRegistry.TIER_1_ROCKET.get(), RocketTier1Renderer::new);
        event.registerEntityRenderer(EntitiesRegistry.TIER_2_ROCKET.get(), RocketTier2Renderer::new);
        event.registerEntityRenderer(EntitiesRegistry.TIER_3_ROCKET.get(), RocketTier3Renderer::new);
        event.registerEntityRenderer(EntitiesRegistry.TIER_4_ROCKET.get(), RocketTier4Renderer::new);
        event.registerEntityRenderer(EntitiesRegistry.LANDER.get(), LanderRenderer::new);
        event.registerEntityRenderer(EntitiesRegistry.ROVER.get(), RoverRenderer::new);

        /** BLOCK ENTITIES */
        event.registerBlockEntityRenderer(BlockEntitiesRegistry.FLAG_BLOCK_ENTITY.get(), FlagHeadRenderer::new);
        event.registerBlockEntityRenderer(BlockEntitiesRegistry.GLOBE_BLOCK_ENTITY.get(), GlobeBlockRenderer::new);
        //TODO ADD IT BACK
        //event.registerBlockEntityRenderer(BlockEntitiesRegistry.OXYGEN_BUBBLE_DISTRIBUTOR_BLOCK_ENTITY.get(), TileEntityBoxRenderer::new);
    }
}
