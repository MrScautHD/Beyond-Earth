package net.mrscauthd.beyond_earth.client.registries;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.client.entities.renderers.alien.AlienModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.alienzombie.AlienZombieModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.armors.JetSuitModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.armors.SpaceSuitModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.flag.FlagHeadModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.globe.GlobeModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.lander.LanderModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.martianraptor.MartianRaptorModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.mogler.MoglerModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.pygro.PygroModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.rockettier1.RocketTier1Model;
import net.mrscauthd.beyond_earth.client.entities.renderers.rockettier2.RocketTier2Model;
import net.mrscauthd.beyond_earth.client.entities.renderers.rockettier3.RocketTier3Model;
import net.mrscauthd.beyond_earth.client.entities.renderers.rockettier4.RocketTier4Model;
import net.mrscauthd.beyond_earth.client.entities.renderers.rover.RoverModel;
import net.mrscauthd.beyond_earth.client.entities.renderers.starcrawler.StarCrawlerModel;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EntityLayersRegistry {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        /** MOBS */
        event.registerLayerDefinition(AlienModel.LAYER_LOCATION, AlienModel::createBodyLayer);
        event.registerLayerDefinition(AlienZombieModel.LAYER_LOCATION, AlienZombieModel::createBodyLayer);
        event.registerLayerDefinition(StarCrawlerModel.LAYER_LOCATION, StarCrawlerModel::createBodyLayer);
        event.registerLayerDefinition(PygroModel.LAYER_LOCATION, PygroModel::createBodyLayer);
        event.registerLayerDefinition(MoglerModel.LAYER_LOCATION, MoglerModel::createBodyLayer);
        event.registerLayerDefinition(MartianRaptorModel.LAYER_LOCATION, MartianRaptorModel::createBodyLayer);

        /** VEHICLES */
        event.registerLayerDefinition(RocketTier1Model.LAYER_LOCATION, RocketTier1Model::createBodyLayer);
        event.registerLayerDefinition(RocketTier2Model.LAYER_LOCATION, RocketTier2Model::createBodyLayer);
        event.registerLayerDefinition(RocketTier3Model.LAYER_LOCATION, RocketTier3Model::createBodyLayer);
        event.registerLayerDefinition(RocketTier4Model.LAYER_LOCATION, RocketTier4Model::createBodyLayer);
        event.registerLayerDefinition(LanderModel.LAYER_LOCATION, LanderModel::createBodyLayer);
        event.registerLayerDefinition(RoverModel.LAYER_LOCATION, RoverModel::createBodyLayer);

        /** ARMORS **/
        event.registerLayerDefinition(SpaceSuitModel.SPACE_SUIT_P1.LAYER_LOCATION, SpaceSuitModel.SPACE_SUIT_P1::createBodyLayer);
        event.registerLayerDefinition(SpaceSuitModel.SPACE_SUIT_P2.LAYER_LOCATION, SpaceSuitModel.SPACE_SUIT_P2::createBodyLayer);
        event.registerLayerDefinition(JetSuitModel.JET_SUIT_P1.LAYER_LOCATION, JetSuitModel.JET_SUIT_P1::createBodyLayer);
        event.registerLayerDefinition(JetSuitModel.JET_SUIT_P2.LAYER_LOCATION, JetSuitModel.JET_SUIT_P2::createBodyLayer);

        /** BLOCK ENTITIES */
        event.registerLayerDefinition(FlagHeadModel.LAYER_LOCATION, FlagHeadModel::createHumanoidHeadLayer);
        event.registerLayerDefinition(GlobeModel.LAYER_LOCATION, GlobeModel::createLayer);
    }
}
