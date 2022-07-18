package net.mrscauthd.beyond_earth.client.registries;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.mrscauthd.beyond_earth.BeyondEarth;
import net.mrscauthd.beyond_earth.common.registries.BlocksRegistry;
import net.mrscauthd.beyond_earth.common.registries.FluidsRegistry;

@Mod.EventBusSubscriber(modid = BeyondEarth.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlockRenderTypesRegistry {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(FluidsRegistry.FLOWING_FUEL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(FluidsRegistry.FUEL_STILL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(FluidsRegistry.FLOWING_OIL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(FluidsRegistry.OIL_STILL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlocksRegistry.COAL_LANTERN_BLOCK.get(), RenderType.translucent());
        //TODO DON'T FORGOT TO ADD IT BACK
        //TODO DO IT FOR JSON
        //ItemBlockRenderTypes.setRenderLayer(BlocksRegistry.NASA_WORKBENCH_BLOCK.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(BlocksRegistry.WATER_PUMP_BLOCK.get(), RenderType.cutout());
    }
}
